from collections import defaultdict

class VotingSystem:
    def __init__(self):
        self.voters = {}
        self.candidates = defaultdict(int)
        self.add_default_candidates()

    def add_default_candidates(self):
        for name in ["Alice", "Bob", "Charlie"]:
            self.candidates[name] = 0

    def register_voter(self, voter_id):
        if voter_id in self.voters:
            return False
        self.voters[voter_id] = False
        return True

    def cast_vote(self, voter_id, candidate):
        if voter_id not in self.voters:
            return "Voter not registered."
        if self.voters[voter_id]:
            return "You have already voted."
        if candidate not in self.candidates:
            return "Invalid candidate."

        self.candidates[candidate] += 1
        self.voters[voter_id] = True
        return "Vote cast successfully."

    def get_results(self):
        return dict(self.candidates)
