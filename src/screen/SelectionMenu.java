package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.border.EmptyBorder;

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
        int paddingSize = 20;
        buttonPanel.setBorder(new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize)); // Add padding

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
        Dimension buttonSize = new Dimension(200, 200); // Button size
    
        // Add 9 new buttons (square boxes) to represent map choices
        for (int i = 1; i <= 9; i++) {
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(buttonSize);
            layeredPane.setLayout(null); // Use null layout to set component bounds manually
    
            JButton button = new JButton();
            button.setFocusPainted(false);
            button.setBorderPainted(false); // Remove borders to fit image better
            button.setBounds(0, 0, buttonSize.width, buttonSize.height); // Set bounds for the button
    
            // Dummy image file path
            String imagePath = "/assets/background/battleback" + i + ".png"; // Update with the correct file path
            URL resource = getClass().getResource(imagePath);
    
            if (resource != null) {
                // Load the image and scale it to fit the button size
                ImageIcon originalIcon = new ImageIcon(resource);
                Image originalImage = originalIcon.getImage();
    
                // Scale the image to fit the button size exactly (no gaps)
                Image scaledImage = originalImage.getScaledInstance(buttonSize.width, buttonSize.height, Image.SCALE_SMOOTH);
    
                // Set the scaled image as the icon of the button
                button.setIcon(new ImageIcon(scaledImage));
            } else {
                System.out.println("Image file not found at: " + imagePath);
                button.setText("New Option " + i); // Fallback text if the image is not found
                button.setBackground(Color.WHITE); // Fallback background color
            }
    
            // Create a semi-transparent panel to overlay the background (but not affect the text)
            JPanel overlayPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Set transparency level
                    g2d.setColor(Color.BLACK); // Set color for the overlay
                    g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill the panel with the color
                }
            };
            overlayPanel.setBounds(0, 0, buttonSize.width, buttonSize.height); // Set bounds for the overlay
            overlayPanel.setOpaque(false); // Make sure the panel is transparent
    
            // Separate text label (so it is not affected by the transparency)
            JLabel textLabel = new JLabel("New Option " + i, SwingConstants.CENTER);
            textLabel.setForeground(Color.WHITE); // Set text color to white for better contrast
            textLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size for visibility
            textLabel.setBounds(0, 0, buttonSize.width, buttonSize.height); // Set bounds for the text label to cover the button area
            textLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
            textLabel.setVerticalAlignment(SwingConstants.CENTER);   // Center text vertically
    
            // Add the button, overlay, and text to the layered pane
            layeredPane.add(button, Integer.valueOf(0));          // Add the button at the base layer
            layeredPane.add(overlayPanel, Integer.valueOf(1));    // Add the transparent overlay on top of the button
            layeredPane.add(textLabel, Integer.valueOf(2));       // Add the text label on top of everything else
    
            // Add the layered pane to the button panel
            buttonPanel.add(layeredPane);
    
            // Add an ActionListener to each button (for handling selection)
            final int finalI = i; // Create a final variable and assign the value of i to it
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("New Option " + finalI + " selected");
                    total++;
                    if (listener != null) {
                        listener.onSelectionMade(finalI); // Notify listener of the selection
                    }
                }
            });
        }
    
        revalidate(); // Revalidate to refresh the panel layout
        repaint(); // Repaint to show the changes
    }
    
}
