package screen;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionMenu extends JPanel {
    private SelectionListener listener;
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();
    
    // Arrays to store races and classes
    private String[] races = {"Human", "Elf", "Orc"};
    private String[] classes = {"Rogue", "Mage", "Warrior"};
    private int[] selectedRace = {0, 0, 0}; // Race selection tracking per column
    private int[] selectedClass = {0, 0, 0}; // Class selection tracking per column

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
        buttonPanel.setLayout(new GridLayout(1, 3, 20, 20)); // 3 columns
        buttonPanel.setBackground(Color.BLACK);
        int paddingSize = 20;
        buttonPanel.setBorder(new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize)); // Add padding
    
        // Add buttons to the buttonPanel
        initializeCharacterButtons();
    
        // Add the button panel to the CENTER
        add(buttonPanel, BorderLayout.CENTER);
    
        // Create "Next" button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 12)); // Smaller font size for a small fit-text button
        nextButton.setBackground(Color.GREEN);
        nextButton.setPreferredSize(new Dimension(100, 30)); // Smaller button size
    
        // ActionListener for the button to make it disappear when clicked
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeMapButtons();
                nextButton.setVisible(false); // Hide the button after it's clicked
            }
        });
    
        // Create a JPanel with FlowLayout to position the button in the middle
        JPanel buttonPanelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30)); // 30px padding from top
        buttonPanelWrapper.setBackground(Color.BLACK); // Match the background color
        buttonPanelWrapper.add(nextButton); // Add the "Next" button to the wrapper panel
    
        // Add the button panel wrapper to the CENTER, slightly above the bottom
        add(buttonPanelWrapper, BorderLayout.SOUTH);
    }
    
    private void initializeCharacterButtons() {
        buttonPanel.removeAll();

        for (int i = 0; i < 3; i++) {
            JPanel columnPanel = new JPanel();
            columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));

            // Load race image (for now, this uses a placeholder text)
            JLabel raceLabel = new JLabel(races[selectedRace[i]], SwingConstants.CENTER);
            raceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            raceLabel.setForeground(Color.WHITE);

            // Placeholder for class type (weapon)
            JLabel classLabel = new JLabel(classes[selectedClass[i]], SwingConstants.CENTER);
            classLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            classLabel.setForeground(Color.WHITE);

            // Button to switch race
            JButton raceButton = new JButton("Switch Race");
            final int finalI = i;
            raceButton.addActionListener(e -> {
                selectedRace[finalI] = (selectedRace[finalI] + 1) % races.length;
                raceLabel.setText(races[selectedRace[finalI]]);
            });

            // Button to switch class
            JButton classButton = new JButton("Switch Class");
            classButton.addActionListener(e -> {
                selectedClass[finalI] = (selectedClass[finalI] + 1) % classes.length;
                classLabel.setText(classes[selectedClass[finalI]]);
            });

            // Add components to column panel
            columnPanel.add(raceLabel);   // Race image placeholder
            columnPanel.add(classLabel);  // Class weapon placeholder
            columnPanel.add(raceButton);  // Switch race button
            columnPanel.add(classButton); // Switch class button

            // Add some spacing and background
            columnPanel.setBackground(Color.BLACK);
            columnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Add column to the main button panel
            buttonPanel.add(columnPanel);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void initializeMapButtons() {
        buttonPanel.removeAll(); // Clear existing components
        buttonPanel.setLayout(new GridLayout(3, 3, 20, 20)); // 3 columns
        Dimension buttonSize = new Dimension(200, 200); // Define button size
        String[] battleMapNames = {
            "Enchanted Forest",   // New Option 1
            "Frozen Tundra",      // New Option 2
            "Desert Dunes",       // New Option 3
            "Desert Oasis",       // New Option 4
            "Cavern Depths",      // New Option 5
            "Autumn Woods",       // New Option 6
            "Mystic Grove",       // New Option 7
            "Dungeon Chambers",   // New Option 8
            "Rocky Plateau"       // New Option 9
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
    
            titleLabel.setText("Select Your Battle Map");

            // Load image and scale it
            String imagePath = "/assets/background/battleback" + i + ".png";
            URL resource = getClass().getResource(imagePath);
    
            if (resource != null) {
                ImageIcon originalIcon = new ImageIcon(resource);
                Image scaledImage = originalIcon.getImage().getScaledInstance(buttonSize.width, buttonSize.height, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            } else {
                button.setText("New Option " + i);
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
            JLabel textLabel = new JLabel(battleMapNames[i-1], SwingConstants.CENTER);
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
