package screen.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import screen.SelectionListener;

import java.awt.*;
import java.net.URL;

public class CharacterSelection extends JPanel {
    private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
    private JPanel buttonPanel = new JPanel();
    private int[] selectedRace = { 0, 1, 2 }; // Initial
    private int[] selectedClass = { 0, 1, 2 }; // Initial

    // Arrays to store races and classes
    private String[] races = { "Angel", "Orc", "Minotaur" };
    private String[] classes = { "Warrior", "Mage", "Rogue" };

    public CharacterSelection(SelectionListener listener) {

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

        // Add race and class selection options
        buttonPanel.removeAll();

        for (int i = 0; i < 3; i++) {
            JPanel columnPanel = new JPanel();
            columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
            columnPanel.setBackground(Color.DARK_GRAY); // Set background

            // Panel for race image
            JPanel raceImagePanel = new JPanel();
            raceImagePanel.setBackground(Color.DARK_GRAY);
            JLabel raceImageLabel = new JLabel();
            loadImage(raceImageLabel, "/assets/" + races[selectedRace[i]].toLowerCase() + "/Idle/0" + ".png", 150, 150);
            raceImagePanel.add(raceImageLabel);

            // Panel for class image (weapon)
            JPanel classImagePanel = new JPanel();
            classImagePanel.setBackground(Color.DARK_GRAY);
            JLabel classImageLabel = new JLabel();
            classImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            loadImage(classImageLabel, "/assets/weapon/" + classes[selectedClass[i]].toLowerCase() + ".png", 40, 40);
            classImagePanel.add(classImageLabel);

            // Panel for race name
            JPanel raceLabelPanel = new JPanel();
            raceLabelPanel.setBackground(Color.DARK_GRAY);
            JLabel raceLabel = new JLabel(races[selectedRace[i]], SwingConstants.CENTER);
            raceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            raceLabel.setForeground(Color.WHITE);
            raceLabelPanel.add(raceLabel);

            // Panel for class name
            JPanel classLabelPanel = new JPanel();
            classLabelPanel.setBackground(Color.DARK_GRAY);
            JLabel classLabel = new JLabel(classes[selectedClass[i]], SwingConstants.CENTER);
            classLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            classLabel.setForeground(Color.WHITE);
            classLabelPanel.add(classLabel);

            // Panel for race JComboBox
            JPanel raceComboBoxPanel = new JPanel();
            raceComboBoxPanel.setBackground(Color.DARK_GRAY);
            JComboBox<String> raceComboBox = new JComboBox<>(races);
            raceComboBox.setSelectedIndex(selectedRace[i]);
            final int finalI = i;
            raceComboBox.addActionListener(e -> {
                selectedRace[finalI] = raceComboBox.getSelectedIndex();
                raceLabel.setText(races[selectedRace[finalI]]);
                loadImage(raceImageLabel, "/assets/" + races[selectedRace[finalI]].toLowerCase() + "/idle/" + "0.png",
                        150, 150);
            });
            raceComboBoxPanel.add(raceComboBox);

            // Panel for class JComboBox
            JPanel classComboBoxPanel = new JPanel();
            classComboBoxPanel.setBackground(Color.DARK_GRAY);
            JComboBox<String> classComboBox = new JComboBox<>(classes);
            classComboBox.setSelectedIndex(selectedClass[i]);
            classComboBox.addActionListener(e -> {
                selectedClass[finalI] = classComboBox.getSelectedIndex();
                classLabel.setText(classes[selectedClass[finalI]]);
                loadImage(classImageLabel, "/assets/weapon/" + classes[selectedClass[finalI]].toLowerCase() + ".png",
                        40, 40);
            });
            classComboBoxPanel.add(classComboBox);

            // Add the individual panels to the column panel
            columnPanel.add(raceImagePanel);
            columnPanel.add(classImagePanel);
            columnPanel.add(raceLabelPanel);
            columnPanel.add(classLabelPanel);
            columnPanel.add(raceComboBoxPanel);
            columnPanel.add(classComboBoxPanel);

            // Add some spacing and border
            columnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Add the column panel to the main button panel
            buttonPanel.add(columnPanel);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();

        // Add the button panel to the CENTER
        add(buttonPanel, BorderLayout.CENTER);

        // Create "Next" button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 12)); // Smaller font size for a small fit-text button
        nextButton.setBackground(Color.GREEN);
        nextButton.setPreferredSize(new Dimension(100, 30)); // Smaller button size

        nextButton.addActionListener(e -> {
            if (listener != null) {
                listener.onCharacterSelected(selectedRace, selectedClass); // Notify listener
            }
        });
        // Create a JPanel with FlowLayout to position the button in the middle
        JPanel buttonPanelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30)); // 30px padding from top
        buttonPanelWrapper.setBackground(Color.BLACK); // Match the background color
        buttonPanelWrapper.add(nextButton); // Add the "Next" button to the wrapper panel

        // Add the button panel wrapper to the CENTER, slightly above the bottom
        add(buttonPanelWrapper, BorderLayout.SOUTH);
    }

    // Helper method to load and scale images
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
