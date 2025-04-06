package client.ui;

import common.VotingSystemInterface;

import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Register");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField nameField = new JTextField(15);
        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(e -> {
            try {
                VotingSystemInterface stub = (VotingSystemInterface) Naming.lookup("rmi://localhost/VotingService");
                String name = nameField.getText();
                boolean success = stub.register(name);
                JOptionPane.showMessageDialog(this, success ? "Registered!" : "Already registered.");
                if (success) {
                    dispose();
                    new VoteFrame(name);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter your name:"));
        panel.add(nameField);
        panel.add(registerButton);
        add(panel);

        setVisible(true);
    }
}
