package server;

import common.VotingSystemInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class VotingSystemImpl extends UnicastRemoteObject implements VotingSystemInterface {
    private Set<String> registeredVoters = new HashSet<>();
    private Map<String, Integer> votes = new HashMap<>();
    private Set<String> votedVoters = new HashSet<>();

    protected VotingSystemImpl() throws RemoteException {
        votes.put("Alice", 0);
        votes.put("Bob", 0);
        votes.put("Charlie", 0);
    }

    @Override
    public synchronized boolean register(String voterName) {
        return registeredVoters.add(voterName);
    }

    @Override
    public synchronized boolean vote(String voterName, String candidate) {
        if (!registeredVoters.contains(voterName) || votedVoters.contains(voterName) || !votes.containsKey(candidate))
            return false;

        votes.put(candidate, votes.get(candidate) + 1);
        votedVoters.add(voterName);
        return true;
    }

    @Override
    public synchronized Map<String, Integer> getResults() {
        return votes;
    }
}
