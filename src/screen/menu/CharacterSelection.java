package screen.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import screen.SelectionListener;
import java.awt.*;
import java.net.URL;

public class CharacterSelection extends JPanel {
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();
    private String[] races = { "Angel", "Orc", "Minotaur" }; // Available character races
    private String[] classes = { "Warrior", "Mage", "Rogue" }; // Available character classes

    public CharacterSelection(SelectionListener listener, int[] selectedRace, int[] selectedClass) {
        setLayout(new BorderLayout()); // Set panel layout as BorderLayout
        configureTitleLabel();         // Configure title label
        configureButtonPanel();        // Set up the button panel layout

        add(titleLabel, BorderLayout.NORTH);    // Add title to NORTH position
        add(buttonPanel, BorderLayout.CENTER);  // Add character selection panel to CENTER

        addCharacterSelectionColumns(selectedRace, selectedClass); // Add character selection UI

        addNavigationButtons(listener, selectedRace, selectedClass); // Add navigation buttons
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

        columnPanel.add(createImagePanel(raceImageLabel));             // Add race image panel
        columnPanel.add(createImagePanel(classImageLabel));            // Add class image panel
        columnPanel.add(createLabelPanel(races[selectedRace[index]], 16)); // Add race name label
        columnPanel.add(createLabelPanel(classes[selectedClass[index]], 14)); // Add class name label
        columnPanel.add(createComboBoxPanel(races, selectedRace, index, raceImageLabel)); // Add race JComboBox
        columnPanel.add(createComboBoxPanel(classes, selectedClass, index, classImageLabel)); // Add class JComboBox

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
    private JPanel createLabelPanel(String text, int fontSize) {
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.DARK_GRAY);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setForeground(Color.WHITE);
        labelPanel.add(label);
        return labelPanel;
    }

    // Create a JComboBox panel to select race/class, with image update functionality
    private JPanel createComboBoxPanel(String[] options, int[] selected, int index, JLabel imageLabel) {
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(Color.DARK_GRAY);

        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setSelectedIndex(selected[index]);
        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        comboBox.addActionListener(e -> {
            selected[index] = comboBox.getSelectedIndex();
            imageLabel.setText(options[selected[index]]);
            loadImage(imageLabel, "/assets/" + options[selected[index]].toLowerCase() + (options == races ? "/idle/0.png" : ".png"),
                    options == races ? 180 : 40, options == races ? 180 : 40);
        });

        comboBoxPanel.add(comboBox);
        return comboBoxPanel;
    }

    // Add navigation buttons at the bottom of the panel
    private void addNavigationButtons(SelectionListener listener, int[] selectedRace, int[] selectedClass) {
        JButton nextButton = createNavigationButton("Next", e -> {
            if (listener != null) {
                listener.onCharacterSelected(selectedRace, selectedClass);
            }
        });

        JButton backButton = createNavigationButton("Back", e -> {
            if (listener != null) {
                listener.onCharacterSelected(new int[]{}, new int[]{});
            }
        });

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
            System.out.println("Error: Image not found at " + imagePath);
            label.setText("Image Not Found");
        }
    }
}
