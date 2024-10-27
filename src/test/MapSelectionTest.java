package test;

import screen.menu.MapSelection;
import screen.SelectionListener;

import javax.swing.*;
import java.awt.*;

public class MapSelectionTest {
    private MapSelection mapSelection;
    private SelectionListener mockListener;

    @BeforeEach
    public void setUp() {
        // Setting up a mock listener with default behavior
        mockListener = new SelectionListener() {
            @Override
            public boolean isUnlocked(int mapIndex) {
                return true; // Default behavior; can be overridden in individual tests
            }
        };
        mapSelection = new MapSelection(mockListener, 1); // Assume map index 1 is relevant for testing
    }

    @Test
    public void testLockedMapLabel() {
        JLayeredPane mapButton = mapSelection.createMapImageButton(1, new Dimension(230, 200));
        JLabel mapLabel = (JLabel) mapButton.getComponentsInLayer(2)[0]; // Retrieve the label component
        assertEquals("Locked", mapLabel.getText(), "testLockedMapLabel failed: Expected 'Locked', got '" + mapLabel.getText() + "'.");
    }

    @Test
    public void testUnlockedMapLabel() {
        JLayeredPane mapButton = mapSelection.createMapImageButton(1, new Dimension(230, 200));
        JLabel mapLabel = (JLabel) mapButton.getComponentsInLayer(2)[0]; // Retrieve the label component
        assertEquals("Enchanted Forest", mapLabel.getText(), "testUnlockedMapLabel failed: Expected 'Enchanted Forest', got '" + mapLabel.getText() + "'.");
    }

    @Test
    public void testMapLockWarningOnNextButton() {
        setUpMap(false); // Lock the map
        JOptionPane.setRootFrame(new JFrame()); // Set up the JOptionPane root frame

        JPanel buttonPanel = mapSelection.createButtonPanel(); // Ensure this method is accessible
        JButton nextButton = (JButton) buttonPanel.getComponent(1); // Assuming the "Next" button is at index 1
        nextButton.doClick(); // Simulate a button click

        // Inform user to check for warning dialog (automated testing for JOptionPane is tricky)
        System.out.println("testMapLockWarningOnNextButton: Please check for the warning dialog manually.");
    }
}
