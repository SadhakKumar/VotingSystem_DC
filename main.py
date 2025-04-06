from fastapi import FastAPI
from pydantic import BaseModel
from voting_logic import VotingSystem
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()
voting = VotingSystem()

# Allow frontend to talk to backend
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)

class VoterRequest(BaseModel):
    voter_id: str

class VoteRequest(BaseModel):
    voter_id: str
    candidate: str

@app.post("/register")
def register(request: VoterRequest):
    success = voting.register_voter(request.voter_id)
    return {"success": success}

@app.post("/vote")
def vote(request: VoteRequest):
    result = voting.cast_vote(request.voter_id, request.candidate)
    return {"message": result}

@app.get("/results")
def get_results():
    return {"results": voting.get_results()}
