package screen.menu;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import screen.Listener;

public class CharacterSelection extends JPanel {
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();// Panel for character selection buttons
    private JPanel navbar = new JPanel();// Navigation bar
    private String[] races = { "Angel", "Orc", "Minotaur" }; // Available character races
    private String[] classes = { "Warrior", "Mage", "Rogue" }; // Available character classes
    // Labels for displaying race and class names
    private JLabel[] raceTextLabels = new JLabel[3];
    private JLabel[] classTextLabels = new JLabel[3];

    public CharacterSelection(Listener listener, int[] selectedRace, int[] selectedClass) {
        setLayout(new BorderLayout()); // Set panel layout as BorderLayout
        configureTitleLabel(); // Configure title label
        configureButtonPanel(); // Set up the button panel layout

        add(titleLabel, BorderLayout.NORTH); // Add title to NORTH position
        add(buttonPanel, BorderLayout.CENTER); // Add character selection panel to CENTER

        addCharacterSelectionColumns(selectedRace, selectedClass); // Add character selection UI
        addNavigationButtons(listener, selectedRace, selectedClass); // Add navigation buttons
        createNavbar(listener); // Initialize navbar
        add(navbar, BorderLayout.NORTH); // Add navbar to the NORTH position
    }

    private void createNavbar(Listener listener) {
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        navbar.setBackground(Color.DARK_GRAY);

        String[] navItems = { "Home", "Map", "Characters", "Battle Log Reader", "Exit" };
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(Color.DARK_GRAY);
            navButton.setBorderPainted(false);
            navButton.setFocusPainted(false);
            navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            navbar.add(navButton);

            // Capture the current item in a final variable for the action listener
            final String currentItem = item;

            // Adding an action for each navigation item
            navButton.addActionListener(e -> {
                switch (currentItem) {
                    case "Home":
                        listener.onGuideBack();
                        break;
                    case "Map":
                        listener.onMenuMapSelected();
                        break;
                    case "Characters":
                        listener.onMenuCharacterSelected();
                        break;
                    case "Battle Log Reader":
                        listener.onMenuBattleLogReaderSelected();
                        break;
                    case "Exit":
                        ImageIcon originalIcon = new ImageIcon(
                                getClass().getResource("/assets/logo.png"));

                        // Scale the image to 50x50 pixels
                        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50,
                                Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);

                        int confirm = JOptionPane.showOptionDialog(
                                null, // Parent component
                                "Are you sure you want to quit?", // Message
                                "Quit Confirmation", // Title
                                JOptionPane.YES_NO_OPTION, // Option type (Yes/No)
                                JOptionPane.QUESTION_MESSAGE, // Message type
                                scaledIcon, // Custom icon
                                null, // No custom button options
                                null); // Default button option
                        if (confirm == JOptionPane.YES_OPTION) {
                            System.exit(0); // Exit the application if confirmed
                        }
                        break;
                }
            });
        }
        add(navbar, BorderLayout.NORTH);// Add the navbar to the top of the menu

    }

    // Configure the title label settings
    private void configureTitleLabel() {
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));
    }

    // Configure the button panel layout and settings
    private void configureButtonPanel() {
        buttonPanel.setLayout(new GridLayout(1, 3, 20, 20)); // 3 columns with spacing
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding
    }

    // Create the character selection columns dynamically
    private void addCharacterSelectionColumns(int[] selectedRace, int[] selectedClass) {
        buttonPanel.removeAll();
        for (int i = 0; i < 3; i++) {
            JPanel columnPanel = createCharacterColumn(selectedRace, selectedClass, i);
            buttonPanel.add(columnPanel); // Add each column to the button panel
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    // Create a single character selection column with race/class options
    private JPanel createCharacterColumn(int[] selectedRace, int[] selectedClass, int index) {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
        columnPanel.setBackground(Color.DARK_GRAY);
        columnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel raceImageLabel = new JLabel();
        loadImage(raceImageLabel, "/assets/" + races[selectedRace[index]].toLowerCase() + "/Idle/0.png", 180, 180);

        JLabel classImageLabel = new JLabel();
        loadImage(classImageLabel, "/assets/weapon/" + classes[selectedClass[index]].toLowerCase() + ".png", 40, 40);

        columnPanel.add(createImagePanel(raceImageLabel)); // Add race image panel
        columnPanel.add(createImagePanel(classImageLabel)); // Add class image panel
        columnPanel.add(createLabelPanel(races[selectedRace[index]], index, true, 16)); // Add race name label
        columnPanel.add(createLabelPanel(classes[selectedClass[index]], index, false, 14)); // Add class name label
        columnPanel.add(createComboBoxPanel(races, selectedRace, index, raceImageLabel, true)); // Add race JComboBox
        columnPanel.add(createComboBoxPanel(classes, selectedClass, index, classImageLabel, false)); // Add class
                                                                                                     // JComboBox

        return columnPanel;
    }

    // Create a panel to display an image
    private JPanel createImagePanel(JLabel imageLabel) {
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.DARK_GRAY);
        imagePanel.add(imageLabel);
        return imagePanel;
    }

    // Create a panel to display a label with the specified text and font size
    private JPanel createLabelPanel(String text, int index, boolean isRace, int fontSize) {
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.DARK_GRAY);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        labelPanel.add(label);
        if (isRace) {
            raceTextLabels[index] = label;
        } else {
            classTextLabels[index] = label;
        }

        return labelPanel;
    }

    // Create a JComboBox panel to select race/class, with image update
    // functionality
    private JPanel createComboBoxPanel(String[] options, int[] selected, int index, JLabel imageLabel, boolean isRace) {
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(Color.DARK_GRAY);

        JComboBox<String> comboBox = new JComboBox<>(options); // Create JComboBox with options
        comboBox.setSelectedIndex(selected[index]); // Set selected index
        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor on hover

        comboBox.addActionListener(e -> {
            selected[index] = comboBox.getSelectedIndex();
            if (isRace) {
                raceTextLabels[index].setText(options[selected[index]]);
                loadImage(imageLabel,
                        "/assets/" + options[selected[index]].toLowerCase()
                                + (options == races ? "/idle/0.png" : ".png"),
                        options == races ? 180 : 40, options == races ? 180 : 40);
            } else {
                classTextLabels[index].setText(options[selected[index]]);
                loadImage(imageLabel,
                        "/assets/weapon/" + options[selected[index]].toLowerCase()
                                + (options == races ? "/idle/0.png" : ".png"),
                        options == races ? 180 : 40, options == races ? 180 : 40);
            }
        });
        comboBoxPanel.add(comboBox);
        return comboBoxPanel;
    }

    // Add navigation buttons at the bottom of the panel
    private void addNavigationButtons(Listener listener, int[] selectedRace, int[] selectedClass) {
        JButton nextButton = createNavigationButton("Next", e -> {
            if (listener != null) {
                listener.onCharacterSelected(selectedRace, selectedClass);
            }
        });

        JButton backButton = createNavigationButton("Back", e -> {
            if (listener != null) {
                listener.onCharacterSelected(new int[] {}, new int[] {});
            }
        });

        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel buttonPanelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        buttonPanelWrapper.setBackground(Color.BLACK);
        buttonPanelWrapper.add(backButton);
        buttonPanelWrapper.add(nextButton);

        add(buttonPanelWrapper, BorderLayout.SOUTH);
    }

    // Helper method to create a navigation button with specified text and action
    private JButton createNavigationButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(100, 30));
        button.addActionListener(action);
        return button;
    }

    // Helper method to load and scale images, with a fallback message if not found
    private void loadImage(JLabel label, String imagePath, int width, int height) {
        URL resource = getClass().getResource(imagePath);
        if (resource != null) {
            ImageIcon icon = new ImageIcon(resource);
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(image));
        } else {
            System.out.println("Image not found: " + imagePath);
        }
    }
}