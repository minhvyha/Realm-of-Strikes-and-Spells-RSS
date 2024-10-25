package screen.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import screen.SelectionListener;
import java.awt.*;
import java.net.URL;

public class MapSelection extends JPanel {
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();
    private JLabel selectedMapLabel = new JLabel("", SwingConstants.CENTER); // Label to display selected map
    private int map;
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

    public MapSelection(SelectionListener listener, int map) {
        setLayout(new BorderLayout());
        this.map = map;
        selectedMapLabel.setText("Selected Map: "+ battleMapNames[map - 1]); 
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Title font and size
        titleLabel.setForeground(Color.WHITE); // Title color
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK); // Background color for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        // Add the title label at the top (NORTH)
        add(titleLabel, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(3, 3, 40, 40));
        buttonPanel.setBackground(Color.BLACK);
        int paddingSize = 20;
        buttonPanel.setBorder(new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize)); // Add padding
        Dimension buttonSize = new Dimension(230, 200); // Define button size

        // Add 9 buttons for map choices
        for (int i = 1; i <= 9; i++) {
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(buttonSize);

            JButton button = new JButton();
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setBounds(0, 0, buttonSize.width, buttonSize.height);
            button.setHorizontalAlignment(SwingConstants.CENTER);

            // Load image and scale it
            String imagePath = "/assets/background/battleback" + i + ".png";
            URL resource = getClass().getResource(imagePath);

            if (resource != null) {
                ImageIcon originalIcon = new ImageIcon(resource);
                Image scaledImage = originalIcon.getImage().getScaledInstance(buttonSize.width, buttonSize.height,
                    Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            } else {
                button.setText("New Option " + i);
                button.setBackground(Color.WHITE);
            }

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
            textLabel.setBounds(0, -40, buttonSize.width, buttonSize.height);

            // Add components to the layered pane
            layeredPane.add(button, Integer.valueOf(0));
            layeredPane.add(overlayPanel, Integer.valueOf(1));
            layeredPane.add(textLabel, Integer.valueOf(2));

            // Add the layered pane to the panel
            buttonPanel.add(layeredPane);

            // ActionListener for button
            final int FINAL_INDEX = i;
            button.addActionListener(e -> {
                System.out.println("New Option " + FINAL_INDEX + " selected");
                this.map = FINAL_INDEX;
                selectedMapLabel.setText("Selected Map: " + battleMapNames[FINAL_INDEX - 1]); // Update the label with selected map
            });
        }

        buttonPanel.setBorder(new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize)); // Add padding

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 12)); // Smaller font size for a small fit-text button
        nextButton.setPreferredSize(new Dimension(100, 30)); // Smaller button size

        nextButton.addActionListener(e -> {
            if (listener != null) {
                listener.onMapSelected(this.map); // Notify listener
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 12)); // Smaller font size for a small fit-text button
        backButton.setPreferredSize(new Dimension(100, 30)); // Smaller button size

        backButton.addActionListener(e -> {
            System.out.println("Back button clicked!");
            if (listener != null) {
                listener.onMapSelected(-1); // Notify listener
            }
        });

        // Create a JPanel with FlowLayout to position the buttons in the middle
        JPanel buttonPanelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); // 30px padding from top
        buttonPanelWrapper.setBackground(Color.BLACK); // Match the background color
        buttonPanelWrapper.add(backButton);
        buttonPanelWrapper.add(nextButton);

        selectedMapLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Title font and size
        selectedMapLabel.setForeground(Color.WHITE); // Title color
        selectedMapLabel.setOpaque(true);
        selectedMapLabel.setBackground(Color.BLACK); // Background color for the title
        selectedMapLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridLayout(2, 1, 20, 0));
        wrapper.setBackground(Color.BLACK);
        wrapper.add(selectedMapLabel);
        wrapper.add(buttonPanelWrapper);
        // Add the button panel wrapper to the SOUTH
        add(wrapper, BorderLayout.SOUTH);

        // Add the button panel to the CENTER
        add(buttonPanel, BorderLayout.CENTER);

        // Add the selected map label to the bottom (SOUTH)

        revalidate();
        repaint();
    }
}
