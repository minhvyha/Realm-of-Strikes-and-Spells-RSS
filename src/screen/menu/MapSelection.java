package screen.menu;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import screen.SelectionListener;

public class MapSelection extends JPanel {
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();
    private JLabel selectedMapLabel = new JLabel("", SwingConstants.CENTER); // Label to display selected map
    private SelectionListener listener; // Declare a listener
    private String[] battleMapNames = { "Enchanted Forest", "Frozen Tundra", "Desert Dunes", "Desert Oasis", 
                                        "Cavern Depths", "Autumn Woods", "Mystic Grove", "Dungeon Chambers", 
                                        "Rocky Plateau" }; // Array of map names
    private int map; // Variable to store selected map

    public MapSelection(SelectionListener listener, int map) {
        this.map = map;
        this.listener = listener;
        setLayout(new BorderLayout());

        configureTitleLabel();           // Configure title label
        configureButtonPanel();          // Configure button panel with map buttons
        addTitleLabel();                 // Add title label to NORTH
        addButtonPanel();                // Add button panel to CENTER
        addSouthPanel();                 // Add selected map label and navigation buttons to SOUTH

        revalidate();
        repaint();
    }

    // Configure the title label
    private void configureTitleLabel() {
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        titleLabel.setForeground(Color.WHITE); 
        titleLabel.setBackground(Color.BLACK); 
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));
    }

    // Add the title label at the top of the panel
    private void addTitleLabel() {
        add(titleLabel, BorderLayout.NORTH);
    }

    // Set up the button panel layout and add map buttons
    private void configureButtonPanel() {
        buttonPanel.setLayout(new GridLayout(3, 3, 40, 40)); // 3x3 grid with spacing
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        Dimension buttonSize = new Dimension(230, 200); // Define button size
        for (int i = 1; i <= 9; i++) {
            JLayeredPane mapButton = createMapButton(i, buttonSize); // Create each map button
            buttonPanel.add(mapButton);
        }
    }

    // Add the button panel to the CENTER of the layout
    private void addButtonPanel() {
        add(buttonPanel, BorderLayout.CENTER);
    }

    // Create and configure the panel with the selected map label and navigation buttons
    private void addSouthPanel() {
        JPanel wrapper = new JPanel(new GridLayout(2, 1, 20, 0));
        wrapper.setBackground(Color.BLACK);

        configureSelectedMapLabel();   // Set up the label to display selected map
        wrapper.add(selectedMapLabel); // Add selected map label to SOUTH panel
        wrapper.add(createButtonPanel()); // Add navigation buttons to SOUTH panel

        add(wrapper, BorderLayout.SOUTH);
    }

    // Configure the label displaying the selected map
    private void configureSelectedMapLabel() {
        selectedMapLabel.setFont(new Font("Arial", Font.BOLD, 14));
        selectedMapLabel.setForeground(Color.WHITE);
        selectedMapLabel.setOpaque(true);
        selectedMapLabel.setBackground(Color.BLACK);
        selectedMapLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));
        selectedMapLabel.setText("Selected Map: " + battleMapNames[map - 1]);
    }

    // Create and configure the panel with navigation buttons
    private JPanel createButtonPanel() {
        JPanel buttonPanelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanelWrapper.setBackground(Color.BLACK);

        JButton nextButton = createButton("Next"); // "Next" button
        JButton backButton = createButton("Back"); // "Back" button

        buttonPanelWrapper.add(backButton);
        buttonPanelWrapper.add(nextButton);

        return buttonPanelWrapper;
    }

    // Create a map selection button with the specified map image and label
    private JLayeredPane createMapButton(int mapIndex, Dimension buttonSize) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(buttonSize);

        JButton button = createMapImageButton(mapIndex, buttonSize); // Button with map image
        JPanel overlayPanel = createOverlayPanel(buttonSize);        // Transparent overlay
        JLabel textLabel = createMapLabel(mapIndex, buttonSize);     // Text overlay with map name

        layeredPane.add(button, Integer.valueOf(0)); // Add components to layered pane
        layeredPane.add(overlayPanel, Integer.valueOf(1));
        layeredPane.add(textLabel, Integer.valueOf(2));

        layeredPane.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for interaction
        return layeredPane;
    }

    // Create a button with the specified map image, handling the selection action
    private JButton createMapImageButton(int mapIndex, Dimension buttonSize) {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBounds(30, 0, buttonSize.width, buttonSize.height);
        button.setHorizontalAlignment(SwingConstants.CENTER);

        // Set the action when this map button is selected
        button.addActionListener(e -> {
            this.map = mapIndex;
            selectedMapLabel.setText("Selected Map: " + battleMapNames[mapIndex - 1]);
        });

        // Load and set the map image
        String imagePath = "/assets/background/battleback" + mapIndex + ".png";
        URL resource = getClass().getResource(imagePath);
        if (resource != null) {
            ImageIcon originalIcon = new ImageIcon(resource);
            Image scaledImage = originalIcon.getImage().getScaledInstance(buttonSize.width, buttonSize.height, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } else {
            button.setText("New Option " + mapIndex);
            button.setBackground(Color.WHITE);
        }

        return button;
    }

    // Create a transparent overlay for each map button
    private JPanel createOverlayPanel(Dimension buttonSize) {
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
        overlayPanel.setBounds(30, 0, buttonSize.width, buttonSize.height);
        overlayPanel.setOpaque(false);

        return overlayPanel;
    }

    // Create a label to display the map name
    private JLabel createMapLabel(int mapIndex, Dimension buttonSize) {
        JLabel textLabel = new JLabel(battleMapNames[mapIndex - 1], SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        textLabel.setBounds(30, -40, buttonSize.width, buttonSize.height);

        return textLabel;
    }

    // Create a navigation button with specified text and action
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(100, 30));

        // Define button action
        button.addActionListener(e -> {
            if (text.equals("Next")) {
                listener.onMapSelected(map);
            } else if (text.equals("Back")) {
                listener.onMapSelected(-1);
            }
        });

        return button;
    }
}
