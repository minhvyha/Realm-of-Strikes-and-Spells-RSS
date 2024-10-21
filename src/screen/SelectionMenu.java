package screen;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.swing.border.EmptyBorder;

public class SelectionMenu extends JPanel {
    private int total = 0;
    private SelectionListener listener;
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
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
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20)); 
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
        buttonPanel.removeAll(); // Clear existing components
        titleLabel.setText("Select Your Battlefield");
        Dimension buttonSize = new Dimension(200, 200); // Define button size
        String[] battleMapNames = {
            "Enchanted Forest",   
            "Frozen Tundra",      
            "Desert Dunes",       
            "Desert Oasis",       
            "Cavern Depths",      
            "Autumn Woods",       
            "Mystic Grove",       
            "Dungeon Chambers",   
            "Rocky Plateau"       
        };
        // Add 9 buttons for map choices
        for (int i = 1; i <= 9; i++) {
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(buttonSize);
            layeredPane.setLayout(null); // Manual component bounds
    
            JButton button = new JButton();
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setBounds(0, 0, buttonSize.width, buttonSize.height);
    
            // Load image and scale it
            String imagePath = "/assets/background/battleback" + i + ".png";
            URL resource = getClass().getResource(imagePath);
    
            if (resource != null) {
                ImageIcon originalIcon = new ImageIcon(resource);
                Image scaledImage = originalIcon.getImage().getScaledInstance(buttonSize.width, buttonSize.height, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            } else {
                button.setText(battleMapNames[i - 1] + i);
                button.setBackground(Color.WHITE);
            }
    
            // Transparent overlay panel
            JPanel overlayPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Transparency
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            overlayPanel.setBounds(0, 0, buttonSize.width, buttonSize.height);
            overlayPanel.setOpaque(false);
    
            // Text label
            JLabel textLabel = new JLabel(battleMapNames[i - 1], SwingConstants.CENTER);
            textLabel.setForeground(Color.WHITE);
            textLabel.setFont(new Font("Arial", Font.BOLD, 18));
            textLabel.setBounds(0, 0, buttonSize.width, buttonSize.height);
    
            // Add components to the layered pane
            layeredPane.add(button, Integer.valueOf(0));
            layeredPane.add(overlayPanel, Integer.valueOf(1));
            layeredPane.add(textLabel, Integer.valueOf(2));
    
            // Add the layered pane to the panel
            buttonPanel.add(layeredPane);
    
            // ActionListener for button
            final int finalI = i;
            button.addActionListener(e -> {
                System.out.println("New Option " + finalI + " selected");
                if (listener != null) {
                    listener.onSelectionMade(finalI); // Notify listener
                }
            });
        }
    
        revalidate();
        repaint();
    }
    
    
}
