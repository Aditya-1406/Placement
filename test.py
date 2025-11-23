import os
import random
from typing import List, Dict, Any
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

# Load environment variables (to securely get the API key)
load_dotenv()

# =================================================================
# 1. Configuration & Data
# =================================================================

# --- 1.1. LLM Configuration ---
# gemini-2.5-flash is fast, highly capable, and included in the free tier.
MODEL_NAME = "gemini-2.5-flash"

# --- 1.2. Job Description and Question Bank ---

JOB_DESCRIPTION = """
Role: Mid-Level Software Engineer (Python/Django)
Responsibilities: Develop and maintain backend services, design robust APIs, and participate in code reviews.
Key Skills: Python, Django, REST APIs, PostgreSQL, AWS.
"""

# A small bank to ensure the interviewer has some guaranteed questions to start with.
QUESTION_BANK = {
    "beginner": [
        "Tell me about a project you're proud of.",
        "Explain the difference between a list and a tuple in Python.",
    ],
    "intermediate": [
        "How would you optimize a slow database query in Django?",
        "Describe a time you dealt with a technical disagreement.",
    ],
}

# =================================================================
# 2. Tools (Internal to the Agent)
# =================================================================

class InterviewTools:
    """A conceptual tool class for fetching questions."""
    
    @staticmethod
    def get_question(role: str, level: str) -> str:
        """Fetches a relevant question based on role and difficulty level."""
        level = level.lower()
        try:
            questions = QUESTION_BANK.get(level, QUESTION_BANK["beginner"])
            return f"The role is {role}. Question: {random.choice(questions)}"
        except KeyError:
            return "What are your career goals for the next five years?"


# =================================================================
# 3. Agents (Interviewer and Analyst)
# =================================================================

class InterviewerAgent:
    """Agent responsible for conducting the interview using the Gemini API."""
    def __init__(self, client: genai.Client, job_description: str):
        self.client = client
        self.system_prompt = f"""
        You are a professional, conversational, and strict interviewer for a technical role.
        The job description is: {job_description}
        
        INSTRUCTIONS:
        1. Act as the interviewer. Ask only ONE question or follow-up at a time.
        2. Progress the interview naturally. Start with behavioral, then move to technical questions based on the 'Key Skills'.
        3. Ask relevant follow-up questions if the candidate's answer is vague or incomplete.
        4. Do NOT give away the answer, hints, or feedback. Your role is purely to interview.
        5. Use simple formatting like **bold** for emphasis.
        """
        
        # --- FIX APPLIED HERE: Set system instruction using config in chats.create() ---
        self.chat_session = self.client.chats.create(
            model=MODEL_NAME,
            config=types.GenerateContentConfig(
                system_instruction=self.system_prompt
            )
        )
        # -----------------------------------------------------------------------------

    def start_interview(self) -> str:
        """Starts the interview by asking the first question."""
        # The system prompt is already set in __init__. We now send the first question.
        initial_question = "Hello, welcome to the interview! Can you start by telling me a bit about your professional journey and why you are interested in the Mid-Level Software Engineer role?"
        
        response = self.chat_session.send_message(initial_question)
        return response.text

    def get_next_question(self, user_response: str) -> str:
        """Sends the user's answer and gets the next question from the model."""
        response = self.chat_session.send_message(user_response)
        return response.text


class FeedbackAgent:
    """Agent responsible for analyzing the session and generating structured feedback."""
    def __init__(self, client: genai.Client, job_description: str):
        self.client = client
        self.job_description = job_description
        self.system_prompt = f"""
        You are a Senior Engineering Manager and performance analyst.
        Your goal is to provide a comprehensive, objective performance report based on the transcript.
        The job description is: {job_description}

        INSTRUCTIONS:
        1. Analyze the candidate's answers against the 'Key Skills' and 'Responsibilities'.
        2. Evaluate behavioral answers using the **STAR method** (Situation, Task, Action, Result).
        3. Output the feedback in the following STRICT Markdown format:
           - ## 📝 Interview Performance Report
           - **Overall Score:** X/5 (Brief Summary)
           - ### ✅ Strengths
           - * [List 2-3 key strengths]
           - ### 📈 Areas for Improvement
           - * [List 2-3 specific areas for growth]
           - ### 🌟 Example of a Better Answer
           - [Provide one example, focusing on a technical or behavioral answer the candidate struggled with.]
        """

    def analyze_session(self, conversation_history: List[str]) -> str:
        """Generates the final report based on the full transcript."""
        transcript = "\n".join(conversation_history)
        
        prompt = f"{self.system_prompt}\n\nFull Interview Transcript:\n{transcript}\n\nGenerate the Final Feedback Report in the requested format."
        
        # Using a simple generate_content call for the final, single output
        response = self.client.models.generate_content(
            model=MODEL_NAME,
            contents=[prompt]
        )
        return response.text

# =================================================================
# 4. Orchestrator
# =================================================================

def interview_practice_session(job_description: str) -> None:
    """The main execution loop for the interview session."""
    
    # Initialize the Gemini Client
    api_key = os.getenv("GEMINI_API_KEY")
    if not api_key:
        print("\nFATAL ERROR: GEMINI_API_KEY environment variable not set.")
        print("Please create a '.env' file or set the environment variable.")
        return
        
    try:
        client = genai.Client(api_key=api_key)
    except Exception as e:
        print(f"\nFATAL ERROR: Could not initialize Gemini client. Check your API key and network connection. Error: {e}")
        return

    interviewer = InterviewerAgent(client, job_description)
    feedback_agent = FeedbackAgent(client, job_description)
    
    # Session State
    conversation_history: List[str] = []
    
    print("\n" + "="*70)
    print("🚀 **AI INTERVIEW PARTNER SESSION STARTED** 🚀")
    print(f"Role: {job_description.splitlines()[0].replace('Role: ', '')}")
    print("Type 'quit' or 'end' to stop the interview early.")
    print("="*70 + "\n")
    
    # Start the Interview
    try:
        initial_prompt = interviewer.start_interview()
        print(f"\n**Interviewer:** {initial_prompt}")
        conversation_history.append(f"Interviewer: {initial_prompt}")
    except APIError as e:
        print(f"\nAPI ERROR during startup. Check your API key and rate limits. Error: {e}")
        return

    # Main Interview Loop (simulating 5 turns for a structured demo)
    for i in range(5):
        try:
            user_response = input("\n**Your Answer:** ")
        except EOFError:
            print("\nError: Non-interactive session detected. Stopping.")
            break
            
        if user_response.lower() in ["quit", "end"]:
            print("\nEnding interview early.")
            break
            
        conversation_history.append(f"Candidate: {user_response}")
        
        try:
            next_question_text = interviewer.get_next_question(user_response)
            
            print(f"\n**Interviewer:** {next_question_text}")
            conversation_history.append(f"Interviewer: {next_question_text}")
        except APIError as e:
            print(f"\nAPI ERROR during Q&A turn {i+1}. Skipping turn. Error: {e}")
            break
            
    # Generate and Display Feedback
    print("\n" + "="*70)
    print("✨ **Interview Concluded. Generating Feedback...** ✨")
    print("="*70)
    
    try:
        final_report = feedback_agent.analyze_session(conversation_history)
        print(final_report)
    except APIError as e:
        print(f"\nAPI ERROR while generating feedback. Error: {e}")
    except Exception as e:
        print(f"\nAn unexpected error occurred: {e}")

    print("\n" + "="*70)
    print("⭐ **Session End** ⭐")
    print("="*70)

# =================================================================
# 5. Execution
# =================================================================

if __name__ == "__main__":
    interview_practice_session(JOB_DESCRIPTION)
