import os
import random
import time
from typing import List, Dict
from dotenv import load_dotenv

# --- LLM CLIENT IMPORTS ---
try:
    from google import genai
    from google.genai import types
    from google.genai.errors import APIError
except ImportError:
    print("Error: The 'google-genai' package is not installed.")
    print("Please run: pip install google-genai python-dotenv")
    exit()

load_dotenv()
MODEL_NAME = "gemini-2.5-flash"

# Role-based Question Bank
QUESTION_BANK = {
    "engineer": {
        "beginner": [
            "Tell me about a project you're proud of.",
            "Explain the difference between a list and a tuple in Python."
        ],
        "intermediate": [
            "How would you optimize a slow database query in Django?",
            "Describe a time you dealt with a technical disagreement."
        ],
        "advanced": [
            "Given an array of integers, find the subarray with the maximum sum.",
            "Implement a function to detect if a linked list has a cycle.",
            "Explain how you would implement a stack using queues.",
            "Find the kth largest element in an unsorted array efficiently."
        ]
    },
    "sales": {
        "beginner": [
            "Tell me about a successful sale you closed.",
            "How do you handle rejection from a client?"
        ],
        "intermediate": [
            "Describe a time you exceeded your sales targets.",
            "How would you approach a new market segment?"
        ],
        "advanced": [
            "Simulate a cold call to a challenging client.",
            "Design a strategy to enter a competitive market."
        ]
    },
    "marketing": {
        "beginner": [
            "Explain a marketing campaign you executed successfully.",
            "How do you measure the effectiveness of a campaign?"
        ],
        "intermediate": [
            "How would you increase brand engagement in a competitive market?",
            "Describe a time when you used data to optimize a campaign."
        ],
        "advanced": [
            "Design a 6-month marketing strategy for a new product.",
            "Analyze a failing campaign and suggest improvements."
        ]
    }
}

# =================================================================
# Tools
# =================================================================
class InterviewTools:
    @staticmethod
    def get_question(role: str, level: str) -> str:
        role = role.lower()
        level = level.lower()
        questions = QUESTION_BANK.get(role, QUESTION_BANK["engineer"]).get(
            level, QUESTION_BANK["engineer"]["beginner"]
        )
        return random.choice(questions)

    @staticmethod
    def get_hint(question: str) -> str:
        """Generate a small hint for a technical question."""
        hints = {
            "subarray": "Consider Kadane's algorithm for max sum subarray.",
            "linked list": "Try using two pointers to detect the cycle.",
            "stack using queues": "Use two queues and simulate push/pop operations.",
            "kth largest": "You can use a min-heap of size k for efficiency."
        }
        for key, hint in hints.items():
            if key in question.lower():
                return hint
        return "Think logically and break down the problem step by step."

# =================================================================
# Agents
# =================================================================
class InterviewerAgent:
    def __init__(self, client: genai.Client, job_description: str, role: str):
        self.client = client
        self.role = role
        self.system_prompt = f"""
        You are a professional interviewer for {role} roles.
        INSTRUCTIONS:
        1. Ask one question at a time.
        2. If candidate is confused, provide a short hint and repeat question.
        3. Keep follow-ups shallow.
        """
        self.chat_session = self.client.chats.create(
            model=MODEL_NAME,
            config=types.GenerateContentConfig(system_instruction=self.system_prompt)
        )

    def start_interview(self) -> str:
        if self.role.lower() == "engineer":
            question = InterviewTools.get_question(self.role, "advanced")
        else:
            question = InterviewTools.get_question(self.role, "beginner")
        response = self.chat_session.send_message(question)
        return response.text, question

    def get_next_question(self, user_response: str, last_question: str) -> str:
        confusion_keywords = ["don't understand", "unable to", "explain", "clarify", "not sure"]
        if any(word in user_response.lower() for word in confusion_keywords):
            hint = InterviewTools.get_hint(last_question)
            clarification = f"Sure, let me clarify: {last_question}\nHint: {hint}"
            response = self.chat_session.send_message(clarification)
            return response.text  # Only clarification

        # Next shallow follow-up
        if self.role.lower() == "engineer":
            next_level = random.choices(
                ["advanced", "intermediate", "beginner"], weights=[0.4, 0.4, 0.2], k=1
            )[0]
        else:
            next_level = "intermediate"
        next_question = InterviewTools.get_question(self.role, next_level)
        response = self.chat_session.send_message(user_response + "\n" + next_question)
        return response.text, next_question

# Feedback Agent with scoring
class FeedbackAgent:
    def __init__(self, client: genai.Client, job_description: str):
        self.client = client
        self.job_description = job_description
        self.system_prompt = f"""
        You are a Senior Manager analyzing candidate performance.
        Provide feedback in Markdown format:
        - Overall Score: X/5
        - Strengths
        - Areas for Improvement
        - Example of Better Answer
        """

    def analyze_session(self, conversation_history: List[str]) -> str:
        transcript = "\n".join(conversation_history)
        prompt = f"{self.system_prompt}\nFull Transcript:\n{transcript}\nGenerate feedback."
        response = self.client.models.generate_content(
            model=MODEL_NAME,
            contents=[prompt]
        )
        return response.text

# =================================================================
# Orchestrator
# =================================================================
def interview_practice_session() -> None:
    api_key = os.getenv("GEMINI_API_KEY")
    if not api_key:
        print("GEMINI_API_KEY not set.")
        return
    
    client = genai.Client(api_key=api_key)

    role = ""
    while role.lower() not in ["engineer", "sales", "marketing"]:
        role = input("Which role are you preparing for? (Engineer/Sales/Marketing): ")
    
    job_description = f"Role: {role.capitalize()}"
    interviewer = InterviewerAgent(client, job_description, role)
    feedback_agent = FeedbackAgent(client, job_description)

    conversation_history: List[str] = []

    print("\n🚀 Interview Started! Type 'exit' or 'quit' to end the interview at any time.\n")

    try:
        first_output, last_question = interviewer.start_interview()
        print(f"**Interviewer:** {first_output}")
        conversation_history.append(f"Interviewer: {first_output}")
    except APIError as e:
        print(f"API ERROR: {e}")
        return

    while True:
        start_time = time.time()
        user_response = input("\n**Your Answer:** ")
        if user_response.lower() in ["quit", "exit"]:
            print("\n🙏 Thank you for participating in this interview!")
            break
        conversation_history.append(f"Candidate: {user_response}")

        # Check time for hint if candidate takes too long
        if time.time() - start_time > 60:
            hint = InterviewTools.get_hint(last_question)
            print(f"\n💡 Hint: {hint}")

        try:
            output = interviewer.get_next_question(user_response, last_question)
            if isinstance(output, tuple):
                next_output, last_question = output
            else:
                next_output = output
            print(f"**Interviewer:** {next_output}")
            conversation_history.append(f"Interviewer: {next_output}")
        except APIError as e:
            print(f"API ERROR: {e}")
            break

    print("\n✨ Interview Concluded. Generating Feedback... ✨\n")
    try:
        final_report = feedback_agent.analyze_session(conversation_history)
        print(final_report)
    except APIError as e:
        print(f"API ERROR while generating feedback: {e}")
    except Exception as e:
        print(f"Unexpected error: {e}")

if __name__ == "__main__":
    interview_practice_session()
