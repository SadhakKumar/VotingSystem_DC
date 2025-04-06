import React, { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [voterId, setVoterId] = useState("");
  const [candidate, setCandidate] = useState("");
  const [results, setResults] = useState({});
  const [message, setMessage] = useState("");

  const candidates = ["Alice", "Bob", "Charlie"];

  useEffect(() => {
    axios.get("http://localhost:8000/results").then((res) => {
      setResults(res.data.results);
    });
  }, []);

  const register = () => {
    axios
      .post("http://localhost:8000/register", { voter_id: voterId })
      .then((res) => {
        setMessage(res.data.success ? "Registered!" : "Already registered.");
      });
  };

  const vote = () => {
    axios
      .post("http://localhost:8000/vote", { voter_id: voterId, candidate })
      .then((res) => {
        setMessage(res.data.message);
        // Refresh results
        axios.get("http://localhost:8000/results").then((res) => {
          setResults(res.data.results);
        });
      });
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Distributed Voting System</h2>

      <input
        type="text"
        placeholder="Enter Voter ID"
        value={voterId}
        onChange={(e) => setVoterId(e.target.value)}
      />
      <button onClick={register}>Register</button>

      <br />
      <br />

      <select onChange={(e) => setCandidate(e.target.value)}>
        <option value="">Select Candidate</option>
        {candidates.map((c) => (
          <option key={c}>{c}</option>
        ))}
      </select>
      <button onClick={vote}>Vote</button>

      <br />
      <br />
      <strong>{message}</strong>

      <h3>Live Results</h3>
      <ul>
        {Object.entries(results).map(([name, votes]) => (
          <li key={name}>
            {name}: {votes} votes
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
