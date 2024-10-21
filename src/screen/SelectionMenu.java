package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionMenu extends JPanel {
    private int total = 0;
    private SelectionListener listener;

    public SelectionMenu(SelectionListener listener) {
        this.listener = listener;

        // Create a JPanel with a black background
        setBackground(Color.BLACK);
        setLayout(new GridLayout(4, 3, 20, 20));  // 4x3 grid, 20px gaps

        // Initialize the panel with the first set of buttons
        initializeCharacterButtons();
    }

    private void initializeCharacterButtons() {
        // Clear all existing components (if any)
        removeAll();
        total = 0; // Reset the counter

        // Add 12 new buttons (square boxes) to represent character or location choices
        for (int i = 1; i <= 12; i++) {
            JButton button = new JButton("New Option " + i);
            button.setPreferredSize(new Dimension(150, 150));
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 16));

            // Add an ActionListener to each button (for handling selection)
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(button.getText() + " selected");
                    total++;
                    // Check if the total selections have reached 3
                    if (total == 3) {
                        // After 3 selections, reset and replace with new buttons
                        initializeMapButtons();  // Reset and reinitialize with new buttons
                        revalidate(); // Revalidate to refresh the panel layout
                        repaint();    // Repaint to show the changes
                    }
                }
            });

            add(button);  // Add the button to the panel
        }
    }
    private void initializeMapButtons() {
        // Clear all existing components (if any)
        removeAll();

        // Add 12 new buttons (square boxes) to represent character or location choices
        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton("New Option " + i);
            button.setPreferredSize(new Dimension(150, 150));
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 16));

            // Add an ActionListener to each button (for handling selection)
            final int finalI = i; // Create a final variable and assign the value of i to it
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(button.getText() + " selected");
                    total++;
                    if (listener != null) {
                        listener.onSelectionMade(finalI); // Notify listener of the selection
                    }
                    

                }
            });

            add(button);  // Add the button to the panel
        }
    }
}
