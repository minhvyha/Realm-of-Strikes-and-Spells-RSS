package screen.menu;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import screen.SelectionListener;

public class MapSelection extends JPanel {
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();
    private JLabel selectedMapLabel = new JLabel("", SwingConstants.CENTER);
    private SelectionListener listener;
    private String[] battleMapNames = {
            "Enchanted Forest", "Frozen Tundra", "Desert Dunes", "Desert Oasis",
            "Cavern Depths", "Autumn Woods", "Mystic Grove", "Dungeon Chambers",
            "Rocky Plateau"
    };
    private int map; // Variable to store selected map
    private boolean[] mapUnlocked; // Array to track unlocked maps

    public MapSelection(SelectionListener listener, int map) {
        this.map = map;
        this.listener = listener;
        this.mapUnlocked = new boolean[battleMapNames.length]; // Initialize map lock array
        this.mapUnlocked[0] = true; // Unlock the first map by default
        setLayout(new BorderLayout());

        configureTitleLabel();
        configureButtonPanel();
        addTitleLabel();
        addButtonPanel();
        addSouthPanel();

        revalidate();
        repaint();
    }

    private void configureTitleLabel() {
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));
    }

    private void addTitleLabel() {
        add(titleLabel, BorderLayout.NORTH);
    }

    private void configureButtonPanel() {
        buttonPanel.setLayout(new GridLayout(3, 3, 40, 40));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        Dimension buttonSize = new Dimension(230, 200);
        for (int i = 1; i <= 9; i++) {
            JLayeredPane mapButton = createMapButton(i, buttonSize);
            buttonPanel.add(mapButton);
        }
    }

    private void addButtonPanel() {
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addSouthPanel() {
        JPanel wrapper = new JPanel(new GridLayout(2, 1, 20, 0));
        wrapper.setBackground(Color.BLACK);

        configureSelectedMapLabel();
        wrapper.add(selectedMapLabel);
        wrapper.add(createButtonPanel());

        add(wrapper, BorderLayout.SOUTH);
    }

    private void configureSelectedMapLabel() {
        selectedMapLabel.setFont(new Font("Arial", Font.BOLD, 14));
        selectedMapLabel.setForeground(Color.WHITE);
        selectedMapLabel.setOpaque(true);
        selectedMapLabel.setBackground(Color.BLACK);
        selectedMapLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));
        selectedMapLabel.setText("Selected Map: " + battleMapNames[map - 1]);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanelWrapper.setBackground(Color.BLACK);

        JButton nextButton = createButton("Next");
        JButton backButton = createButton("Back");

        buttonPanelWrapper.add(backButton);
        buttonPanelWrapper.add(nextButton);

        return buttonPanelWrapper;
    }

    private JLayeredPane createMapButton(int mapIndex, Dimension buttonSize) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(buttonSize);

        JButton button = createMapImageButton(mapIndex, buttonSize);
        JPanel overlayPanel = createOverlayPanel(buttonSize);
        JLabel textLabel = createMapLabel(mapIndex, buttonSize);

        layeredPane.add(button, Integer.valueOf(0));
        layeredPane.add(overlayPanel, Integer.valueOf(1));
        layeredPane.add(textLabel, Integer.valueOf(2));

        layeredPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return layeredPane;
    }

    private JButton createMapImageButton(int mapIndex, Dimension buttonSize) {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBounds(30, 0, buttonSize.width, buttonSize.height);
        button.setHorizontalAlignment(SwingConstants.CENTER);

        button.addActionListener(e -> {
            this.map = mapIndex;
            selectedMapLabel.setText("Selected Map: " + battleMapNames[mapIndex - 1]);
        });

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

    private JPanel createOverlayPanel(Dimension buttonSize) {
        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlayPanel.setBounds(30, 0, buttonSize.width, buttonSize.height);
        overlayPanel.setOpaque(false);
        return overlayPanel;
    }

    private JLabel createMapLabel(int mapIndex, Dimension buttonSize) {
        JLabel textLabel = new JLabel(battleMapNames[mapIndex - 1], SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        textLabel.setBounds(30, -40, buttonSize.width, buttonSize.height);
        return textLabel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(100, 30));

        button.addActionListener(e -> {
            if (text.equals("Next")) {
                if (mapUnlocked[map - 1]) { // Check if the current map is unlocked
                    listener.onMapSelected(map);
                } else {
                    JOptionPane.showMessageDialog(this, "You need to complete the previous map to unlock the next one.", "Map Locked", JOptionPane.WARNING_MESSAGE);
                }
            } else if (text.equals("Back")) {
                listener.onMapSelected(-1);
            }
        });

        return button;
    }

    // Call this method when a map is completed to unlock the next one
    public void unlockNextMap(int completedMapIndex) {
        if (completedMapIndex < mapUnlocked.length - 1) {
            mapUnlocked[completedMapIndex + 1] = true; // Unlock the next map
        }
    }
}
