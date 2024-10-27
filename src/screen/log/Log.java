package screen.log;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log extends JPanel {

    private static class LogNode {
        String message;
        LogNode next;

        public LogNode(String message, LogNode next) {
            this.message = message;
            this.next = next;
        }
    }

    private LogNode head; // Start of the linked list
    private JTextArea logArea; // Area to display the log

    // Constructor for Log class
    public Log(String initialMessage, LogNode next) {
        setLayout(new BorderLayout());
        head = new LogNode(initialMessage, next);

        // Initialize the JTextArea to display messages with black background and white
        // text
        logArea = new JTextArea(10, 24);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setBackground(Color.BLACK); // Set background to black
        logArea.setForeground(Color.WHITE); // Set text color to white
        logArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 6)); // Padding for readability

        // Customize the scroll pane with hidden scroll bars
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Hide the vertical scroll bar
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0)); // Hide the horizontal scroll bar

        // Add scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);

        // Display the initial message
        updateLogDisplay();
    }

    // Method to add a new message to the log
    public void addMessage(String message) {
        if(message == null || message.isEmpty()) {
            return;
        }
        LogNode newNode = new LogNode(message, null);

        if (head == null) {
            head = newNode;
        } else {
            LogNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        updateLogDisplay();
    }

    // Method to update the JTextArea with the linked list content
    private void updateLogDisplay() {
        StringBuilder logText = new StringBuilder();
        LogNode current = head;

        while (current != null) {
            logText.append(current.message).append("\n");
            current = current.next;
        }

        logArea.setText(logText.toString());
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Auto-scroll to bottom
    }

    public String exportToCSV(String filePath) {
        String uniqueFilePath = getUniqueFilePath(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(uniqueFilePath))) {
            writer.write("Line,Message\n"); // Header for CSV file

            LogNode current = head;
            int line = 1;
            while (current != null && current.message != null) {
                String message = current.message;
                writer.write(line + "," + message + "\n");
                line++;
                current = current.next;
            }
            return uniqueFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error exporting log to CSV file.");
        }
        return uniqueFilePath;
    }

    // Helper method to generate a unique file path if the file already exists
    private String getUniqueFilePath(String filePath) {
        File file = new File(filePath);
        String baseName = file.getName();
        String extension = "";

        int dotIndex = baseName.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = baseName.substring(dotIndex);
            baseName = baseName.substring(0, dotIndex);
        }

        int count = 1;
        String newFilePath = filePath;
        while (file.exists()) {
            newFilePath = file.getParent() + File.separator + baseName + "_" + count + extension;
            file = new File(newFilePath);
            count++;
        }

        return newFilePath;
    }
}
