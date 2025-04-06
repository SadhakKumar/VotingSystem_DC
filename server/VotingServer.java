package server;

import common.VotingSystemInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class VotingServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            VotingSystemInterface stub = new VotingSystemImpl();
            Naming.rebind("VotingService", stub);
            System.out.println("Server started...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
