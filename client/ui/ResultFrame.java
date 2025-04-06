package client.ui;

import common.VotingSystemInterface;

import javax.swing.*;
import java.awt.*;
import java.rmi.Naming;
import java.util.Map;

public class ResultFrame extends JFrame {
    public ResultFrame() {
        setTitle("Results");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea resultArea = new JTextArea(10, 25);
        resultArea.setEditable(false);

        try {
            VotingSystemInterface stub = (VotingSystemInterface) Naming.lookup("rmi://localhost/VotingService");
            Map<String, Integer> results = stub.getResults();
            StringBuilder sb = new StringBuilder("Live Results:\n\n");
            for (Map.Entry<String, Integer> entry : results.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" votes\n");
            }
            resultArea.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(resultArea));
        setVisible(true);
    }
}
