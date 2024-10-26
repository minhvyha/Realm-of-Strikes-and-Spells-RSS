package screen.menu;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import screen.SelectionListener;

public class MapSelection extends JPanel {
    // Declare components
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();
    private JLabel selectedMapLabel = new JLabel("", SwingConstants.CENTER); // Label to display selected map

    // Declare a listener
    private SelectionListener listener;

    // Array of map names
    private String[] battleMapNames = {
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

    // Declare a variable to store the selected map
    private int map;

    public MapSelection(SelectionListener listener, int map) {
        // Set the selected map
        this.map = map;
        this.listener = listener;

        // Set the layout to BorderLayout
        setLayout(new BorderLayout());

        // Configure the title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Title font and size
        titleLabel.setForeground(Color.WHITE); // Title color
        titleLabel.setBackground(Color.BLACK); // Background color for the title
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        // Add the title label at the top (NORTH)
        add(titleLabel, BorderLayout.NORTH);

        // Configure the button panel
        buttonPanel.setLayout(new GridLayout(3, 3, 40, 40));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding

        // Define button size
        Dimension buttonSize = new Dimension(230, 200);

        // Add 9 buttons for map choices
        for (int i = 1; i <= 9; i++) {
            JLayeredPane layeredPane = createMapButton(i, buttonSize);
            // Add the layered pane to the panel
            buttonPanel.add(layeredPane, buttonSize);
        }

        // Create Next and Back buttons
        JButton nextButton = createButton("Next");
        JButton backButton = createButton("Back");

        // Create a JPanel with FlowLayout to position the buttons in the middle
        JPanel buttonPanelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); // 30px padding from top
        buttonPanelWrapper.setBackground(Color.BLACK); // Match the background color
        buttonPanelWrapper.add(backButton);
        buttonPanelWrapper.add(nextButton);

        // Configure the selected map label
        selectedMapLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Title font and size
        selectedMapLabel.setForeground(Color.WHITE); // Title color
        selectedMapLabel.setOpaque(true);
        selectedMapLabel.setBackground(Color.BLACK); // Background color for the title
        selectedMapLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));
        selectedMapLabel.setText("Selected Map: " + battleMapNames[map - 1]); // Set the initial selected map

        // Create a wrapper panel for the selected map label and button panel
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridLayout(2, 1, 20, 0));
        wrapper.setBackground(Color.BLACK);
        wrapper.add(selectedMapLabel);
        wrapper.add(buttonPanelWrapper);

        add(wrapper, BorderLayout.SOUTH); // Add the button panel wrapper to the SOUTH
        add(buttonPanel, BorderLayout.CENTER); // Add the button panel to the CENTER

        revalidate();
        repaint();
    }

    private JLayeredPane createMapButton(int mapIndex, Dimension buttonSize) {
        // Create a layered pane to hold the button, overlay, and text label
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(buttonSize);

        // Create a button with the map image
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBounds(30, 0, buttonSize.width, buttonSize.height);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(e -> { // Set the selected map on button click
            this.map = mapIndex;
            selectedMapLabel.setText("Selected Map: " + battleMapNames[mapIndex - 1]);
        });

        // Load image and scale it
        String imagePath = "/assets/background/battleback" + mapIndex + ".png";
        URL resource = getClass().getResource(imagePath);
        if (resource != null) {
            ImageIcon originalIcon = new ImageIcon(resource);
            Image scaledImage = originalIcon.getImage().getScaledInstance(buttonSize.width, buttonSize.height,
                    Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } else {
            button.setText("New Option " + mapIndex);
            button.setBackground(Color.WHITE);
        }

        // Create an overlay panel for transparency
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

        // Text overlay for the map name
        JLabel textLabel = new JLabel(battleMapNames[mapIndex - 1], SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        textLabel.setBounds(30, -40, buttonSize.width, buttonSize.height);

        // Add components to the layered pane
        layeredPane.add(button, Integer.valueOf(0));
        layeredPane.add(overlayPanel, Integer.valueOf(1));
        layeredPane.add(textLabel, Integer.valueOf(2));

        layeredPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return layeredPane;
    }

    private JButton createButton(String text) {
        // Create a button with the specified text
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12)); 
        button.setPreferredSize(new Dimension(100, 30)); 

        button.addActionListener(e -> {
            if (text.equals("Next")) {
                listener.onMapSelected(map); // Notify listener
            }
            if (text.equals("Back")) {
                listener.onMapSelected(-1); // Notify listener
            }
        });

        return button;
    }
}
