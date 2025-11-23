import os
import random
import time
from typing import List
from dotenv import load_dotenv
import streamlit as st

# --- LLM CLIENT IMPORTS ---
try:
    from google import genai
    from google.genai import types
    from google.genai.errors import APIError
except ImportError:
    st.error("Error: The 'google-genai' package is not installed.\nRun: pip install google-genai python-dotenv")
    st.stop()

load_dotenv()
MODEL_NAME = "gemini-2.5-flash"

# ----------------- Questions -----------------
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

# ----------------- Tools -----------------
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

# ----------------- Agents -----------------
class InterviewerAgent:
    def __init__(self, client, role: str):
        self.client = client
        self.role = role
        self.last_question = ""

    def start_interview(self):
        if self.role.lower() == "engineer":
            question = InterviewTools.get_question(self.role, "advanced")
        else:
            question = InterviewTools.get_question(self.role, "beginner")
        self.last_question = question
        return question

    def get_next_question(self, user_response: str):
        confusion_keywords = ["don't understand", "unable to", "explain", "clarify", "not sure"]
        if any(word in user_response.lower() for word in confusion_keywords):
            hint = InterviewTools.get_hint(self.last_question)
            clarification = f"Let me clarify: {self.last_question}\n💡 Hint: {hint}"
            return clarification, self.last_question

        if self.role.lower() == "engineer":
            next_level = random.choices(
                ["advanced", "intermediate", "beginner"], weights=[0.4, 0.4, 0.2], k=1
            )[0]
        else:
            next_level = "intermediate"

        next_question = InterviewTools.get_question(self.role, next_level)
        self.last_question = next_question
        return next_question, next_question

class FeedbackAgent:
    def __init__(self, client, job_description: str):
        self.client = client
        self.job_description = job_description

    def analyze_session(self, conversation_history: List[str]):
        transcript = "\n".join(conversation_history)
        prompt = f"""
        You are a Senior Manager analyzing candidate performance.
        Provide feedback in Markdown format:
        - Overall Score: X/5
        - Strengths
        - Areas for Improvement
        - Example of Better Answer

        Transcript:
        {transcript}
        """
        response = self.client.models.generate_content(
            model=MODEL_NAME,
            contents=[prompt]
        )
        return response.text

# ----------------- Streamlit UI -----------------
st.title("🧑‍💻 Advanced Interview Practice Bot")
api_key = os.getenv("GEMINI_API_KEY")
if not api_key:
    st.error("GEMINI_API_KEY not set in .env file")
    st.stop()

client = genai.Client(api_key=api_key)

if "role" not in st.session_state:
    st.session_state.role = ""
if "conversation" not in st.session_state:
    st.session_state.conversation = []
if "interviewer" not in st.session_state:
    st.session_state.interviewer = None
if "feedback_agent" not in st.session_state:
    st.session_state.feedback_agent = None
if "last_question" not in st.session_state:
    st.session_state.last_question = ""

# Select role
if st.session_state.role == "":
    role = st.selectbox("Select the role you are preparing for:", ["Engineer", "Sales", "Marketing"])
    if st.button("Start Interview"):
        st.session_state.role = role
        st.session_state.interviewer = InterviewerAgent(client, role)
        st.session_state.feedback_agent = FeedbackAgent(client, f"Role: {role}")
        first_question = st.session_state.interviewer.start_interview()
        st.session_state.last_question = st.session_state.interviewer.last_question
        st.session_state.conversation.append(f"Interviewer: {first_question}")
        st.experimental_rerun()

# Display conversation
for msg in st.session_state.conversation:
    st.write(msg)

# User input
if st.session_state.role != "":
    user_answer = st.text_area("Your Answer:", key="answer_box", height=100)
    if st.button("Submit Answer"):
        if user_answer.strip() != "":
            st.session_state.conversation.append(f"Candidate: {user_answer}")

            next_output, last_question = st.session_state.interviewer.get_next_question(user_answer)
            st.session_state.last_question = last_question
            st.session_state.conversation.append(f"Interviewer: {next_output}")
            st.experimental_rerun()

    if st.button("End Interview"):
        st.write("🙏 Thank you for participating in this interview!")
        feedback = st.session_state.feedback_agent.analyze_session(st.session_state.conversation)
        st.markdown("### 📝 Feedback:")
        st.markdown(feedback)
        # Clear session state for next run
        st.session_state.role = ""
        st.session_state.conversation = []
        st.session_state.interviewer = None
        st.session_state.feedback_agent = None
        st.session_state.last_question = ""
        st.experimental_rerun()
