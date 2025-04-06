package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface VotingSystemInterface extends Remote {
    boolean register(String voterName) throws RemoteException;

    boolean vote(String voterName, String candidate) throws RemoteException;

    Map<String, Integer> getResults() throws RemoteException;
}
