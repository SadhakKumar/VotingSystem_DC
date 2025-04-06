package client.ui;

import common.VotingSystemInterface;

import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;

public class VoteFrame extends JFrame {
    public VoteFrame(String voterName) {
        setTitle("Vote");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] candidates = { "Alice", "Bob", "Charlie" };
        JComboBox<String> candidateBox = new JComboBox<>(candidates);
        JButton voteButton = new JButton("Vote");

        voteButton.addActionListener(e -> {
            try {
                VotingSystemInterface stub = (VotingSystemInterface) Naming.lookup("rmi://localhost/VotingService");
                String candidate = (String) candidateBox.getSelectedItem();
                boolean voted = stub.vote(voterName, candidate);
                JOptionPane.showMessageDialog(this, voted ? "Vote cast!" : "Vote failed!");
                if (voted) {
                    dispose();
                    new ResultFrame();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select candidate:"));
        panel.add(candidateBox);
        panel.add(voteButton);
        add(panel);

        setVisible(true);
    }
}
