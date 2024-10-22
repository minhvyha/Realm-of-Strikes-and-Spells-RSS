package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Characters.CharacterLabel; // Import the CharacterLabel class
import Characters.EnemyLabel;

public class BattleScreen extends JPanel {
        private JPanel topPanel, bottomPanel;
        private JPanel leftCharacterPanel, rightCharacterPanel;
        private JTextArea gameTextArea;
        private CharacterLabel orcLabel; // Replace JLabel with CharacterLabel
        private EnemyLabel enemy1, enemy2, enemy3;
        private Image backgroundImage;
        private JPanel menuPanel;
        private JButton option1, option2, option3;

        public BattleScreen(Image backgroundImage) {
                this.backgroundImage = backgroundImage;

                // Load the animation frames (if needed for other characters like orc)
                loadAnimationFrames();

                // Set up the panel
                setLayout(new BorderLayout());

                // Initialize panels
                initializePanels();
        }

        private void loadAnimationFrames() {
                // You can load other character frames like orc here if needed
        }

        private void initializePanels() {
                // Initialize the top panel with character sections
                topPanel = new JPanel(new GridLayout(1, 2));
                topPanel.setOpaque(false); // Make the top panel transparent

                // Bottom panel with a dark transparent box
                bottomPanel = new JPanel(new BorderLayout());
                bottomPanel.setBackground(new Color(0, 0, 0, 180)); // Dark transparent background (RGBA)

                // Initialize character panels
                leftCharacterPanel = new JPanel(null);
                leftCharacterPanel.setOpaque(false); // Transparent left character panel

                rightCharacterPanel = new JPanel();
                rightCharacterPanel.setOpaque(false); // Transparent right character panel

                orcLabel = new CharacterLabel("orc", 18, 12, 15, 12, 50, 110); // Pass the frames and initial position
                leftCharacterPanel.add(orcLabel);

                enemy1 = new EnemyLabel("zombie", 18, 12, 15, 12, 550, 110);
                rightCharacterPanel.add(enemy1);
                // Add character panels to top panel
                topPanel.add(leftCharacterPanel);
                topPanel.add(rightCharacterPanel);

                // Initialize game text area for the bottom section
                gameTextArea = new JTextArea();
                gameTextArea.setOpaque(false); // Make the text area background transparent
                gameTextArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(gameTextArea);
                scrollPane.setOpaque(false);
                scrollPane.getViewport().setOpaque(false); // Ensure the scroll pane is transparent
                bottomPanel.add(scrollPane, BorderLayout.CENTER);

                // Add top and bottom panels to the main panel
                add(topPanel, BorderLayout.CENTER); // Top section
                add(bottomPanel, BorderLayout.SOUTH); // Bottom section

                // Adjust the proportions of the bottom section
                bottomPanel.setPreferredSize(new Dimension(800, 130));

                // Selection menu with transparent background
                menuPanel = new JPanel(new GridLayout(3, 1));
                menuPanel.setBackground(new Color(0, 0, 0, 180)); // 70% transparent background (RGBA)

                // Initialize menu buttons
                option1 = new JButton("Attack");
                option2 = new JButton("Die");
                option3 = new JButton("Hurt");

                // Customize the buttons
                customizeButton(option1);
                customizeButton(option2);
                customizeButton(option3);

                // Add buttons to the menu panel
                menuPanel.add(option1);
                menuPanel.add(option2);
                menuPanel.add(option3);

                // Set size for the selection menu
                menuPanel.setPreferredSize(new Dimension(200, 200));
                add(menuPanel, BorderLayout.EAST);

                // Add action listeners for menu options
                addMenuActions();
        }

        private void customizeButton(JButton button) {
                button.setFocusPainted(false);
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.WHITE);
                button.setPreferredSize(new Dimension(150, 50));
                button.setFont(new Font("Arial", Font.BOLD, 18));
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Square border for selection box
        }

        private void addMenuActions() {
                option1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                orcLabel.setState("attack"); 
                        }
                });

                option2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                orcLabel.setState("die"); // You can implement a defend state
                        }
                });

                option3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                orcLabel.setState("hurt"); // You can implement a run state or action
                        }
                });
        }

        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
}
