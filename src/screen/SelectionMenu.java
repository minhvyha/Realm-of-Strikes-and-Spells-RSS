package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SelectionMenu extends JPanel {
    private int total = 0;
    private SelectionListener listener;
    private JLabel titleLabel = new JLabel("Select Your Battle Map", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();

    public SelectionMenu(SelectionListener listener) {
        this.listener = listener;
    
        // Set the layout to BorderLayout to support NORTH, CENTER, etc.
        setLayout(new BorderLayout());
    
        // Create a JPanel with a black background
        
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Title font and size
        titleLabel.setForeground(Color.WHITE); // Title color
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK); // Background color for the title
    
        // Add the title label at the top (NORTH)
        add(titleLabel, BorderLayout.NORTH);
    
        // Create a panel with GridLayout for the buttons
        buttonPanel.setLayout(new GridLayout(3, 3, 20, 20)); // 3x3 grid, 20px gaps
        buttonPanel.setBackground(Color.BLACK);
    
        // Add buttons to the buttonPanel
        initializeCharacterButtons();
    
        // Add the button panel to the CENTER
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void initializeCharacterButtons() {
        buttonPanel.removeAll();
        total = 0; // Reset the counter
    
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton("New Option " + i);
            button.setPreferredSize(new Dimension(150, 150));
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 16));
    
            // Add action listener to each button
            button.addActionListener(e -> {
                System.out.println(button.getText() + " selected");
                total++;
                if (total == 3) {
                    initializeMapButtons();
                    revalidate();
                    repaint();
                }
            });
    
            buttonPanel.add(button);
        }
    }
    

    private void initializeMapButtons() {
        // Clear all existing components (if any)
        buttonPanel.removeAll();

        // Define the button size
        Dimension buttonSize = new Dimension(200, 200); // Make buttons slightly larger if needed

        // Add 10 new buttons (square boxes) to represent map choices
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton();
            button.setPreferredSize(buttonSize); // Set button size to 200x200 (adjustable)
            button.setFocusPainted(false);
            button.setBorderPainted(false); // Remove borders to fit image better

            // Dummy image file path
            String imagePath = "/assets/background/battleback" + i + ".png"; // Update with the correct file path
            URL resource = getClass().getResource(imagePath);

            if (resource != null) {
                // Load the image and scale it to fit the button size
                ImageIcon originalIcon = new ImageIcon(resource);
                Image originalImage = originalIcon.getImage();

                // Scale the image to fit the button size exactly (no gaps)
                Image scaledImage = originalImage.getScaledInstance(buttonSize.width, buttonSize.height,
                        Image.SCALE_SMOOTH);

                // Set the scaled image as the icon of the button
                button.setIcon(new ImageIcon(scaledImage));
            } else {
                System.out.println("Image file not found at: " + imagePath);
                button.setText("New Option " + i); // Fallback text if the image is not found
                button.setBackground(Color.WHITE); // Fallback background color
            }

            // Text overlay with custom settings
            button.setText("New Option " + i);
            button.setHorizontalTextPosition(SwingConstants.CENTER); // Center text horizontally
            button.setVerticalTextPosition(SwingConstants.CENTER); // Center text vertically
            button.setForeground(Color.WHITE); // Set text color to white (contrast with image)
            button.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size for visibility

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

            buttonPanel.add(button); // Add the button to the panel
        }

        revalidate(); // Revalidate to refresh the panel layout
        repaint(); // Repaint to show the changes
    }

}
