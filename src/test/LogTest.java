package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screen.log.Log;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class LogTest {

    private Log logPanel;

    @BeforeEach
    public void setUp() {
        logPanel = new Log("Initial Message", null);
    }

    @Test
    public void testAddMessage() {
        logPanel.addMessage("New Message 1");
        logPanel.addMessage("New Message 2");

        JTextArea logArea = (JTextArea) ((JScrollPane) logPanel.getComponent(0)).getViewport().getView();
        String expectedOutput = "Initial Message\nNew Message 1\nNew Message 2\n";

        assertEquals(expectedOutput, logArea.getText(), "Log area should display all messages in order.");
    }

    @Test
    public void testExportToCSV() throws IOException {
        logPanel.addMessage("First log entry");
        logPanel.addMessage("Second log entry");

        String filePath = "./battle_log_2.csv";
        logPanel.exportToCSV(filePath);

        Path path = Paths.get(filePath);
        assertTrue(Files.exists(path), "CSV file should be created.");

        String expectedCSVContent = "Line,Message\n1,Initial Message\n2,First log entry\n3,Second log entry\n";
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        }

        assertEquals(expectedCSVContent, fileContent.toString(), "CSV file content should match expected output.");

        // Clean up test file after test execution
        Files.deleteIfExists(path);
    }

    @Test
    public void testUniqueFilePath() {
        String filePath = "test_log.csv";

        // Creating an initial file to simulate an existing file
        try {
            Files.createFile(Paths.get(filePath));
        } catch (IOException e) {
            fail("Could not create initial file for test.");
        }

        // Check if unique path is generated
        String uniqueFilePath = logPanel.exportToCSV(filePath);
        assertNotEquals(filePath, uniqueFilePath, "Unique file path should differ from the original if it exists.");

        // Clean up created files after test execution
        new File(filePath).delete();
        new File(uniqueFilePath).delete();
    }
@Test
public void testAddEmptyMessage() {
    // Add empty message
    logPanel.addMessage("");

    JTextArea logArea = (JTextArea) ((JScrollPane) logPanel.getComponent(0)).getViewport().getView();
    String expectedOutput = "Initial Message\n";  // Expect no new lines or additions for empty message

    assertEquals(expectedOutput, logArea.getText(), "Log area should not display an empty message.");
}

@Test
public void testAddNullMessage() {
    // Add null message
    logPanel.addMessage(null);

    JTextArea logArea = (JTextArea) ((JScrollPane) logPanel.getComponent(0)).getViewport().getView();
    String expectedOutput = "Initial Message\n";  // Expect no new lines or additions for null message

    assertEquals(expectedOutput, logArea.getText(), "Log area should not display a null message.");
}

@Test
public void testAddLongMessage() {
    String longMessage = "A".repeat(1000); // Very long message
    logPanel.addMessage(longMessage);

    JTextArea logArea = (JTextArea) ((JScrollPane) logPanel.getComponent(0)).getViewport().getView();
    String expectedOutput = "Initial Message\n" + longMessage + "\n";

    assertEquals(expectedOutput, logArea.getText(), "Log area should display the long message correctly.");
}

@Test
public void testCSVFormatConsistency() throws IOException {
    logPanel.addMessage("Check,comma,format");  // Message containing commas
    String filePath = "./comma_log.csv";
    logPanel.exportToCSV(filePath);

    Path path = Paths.get(filePath);
    assertTrue(Files.exists(path), "CSV file should be created.");

    String expectedCSVContent = "Line,Message\n1,Initial Message\n2,Check,comma,format\n";
    StringBuilder fileContent = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.append(line).append("\n");
        }
    }

    assertEquals(expectedCSVContent, fileContent.toString(), "CSV file content should properly format messages with commas.");

    // Clean up test file after test execution
    Files.deleteIfExists(path);
}

}
