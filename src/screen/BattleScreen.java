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
        private CharacterLabel orcLabel; // Replace JLabel with CharacterLabel
        private EnemyLabel[] enemyLabels;
        private Image backgroundImage;
        private Character[] allies;
        private Character[] enemies;

        public BattleScreen(Image backgroundImage) {
                this.backgroundImage = backgroundImage;

                // Load the animation frames (if needed for other characters like orc)
                loadGame();

                // Set up the panel
                setLayout(new BorderLayout());

                // Initialize panels
                initializePanels();
        }

        private void loadGame() {
                enemies = new Character[3]; // Array of enemy characters
                String[] enemyRaces = { "zombie", "golem", "reaper" };
                String[] enemyClasses = { "warrior", "mage", "rogue" };
                for(int i = 0; i < 3; i++) {
                        
                }
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

                rightCharacterPanel = new JPanel(null);
                rightCharacterPanel.setOpaque(false); // Transparent right character panel

                orcLabel = new CharacterLabel("orc", 18, 12, 15, 12, 50, 110); // Pass the frames and initial position
                leftCharacterPanel.add(orcLabel);

                enemyLabels = new EnemyLabel[3]; // Array of enemy labels

                EnemyLabel enemy1 = new EnemyLabel("zombie", 18, 12, 15, 12, 220, 150);
                
                EnemyLabel enemy2 = new EnemyLabel("golem", 18, 12, 15, 12, 140, 210);
                
                EnemyLabel enemy3 = new EnemyLabel("reaper", 18, 12, 15, 12, 220, 270);
                
                rightCharacterPanel.add(enemy1);
                rightCharacterPanel.add(enemy2);
                rightCharacterPanel.add(enemy3);
                // Add character panels to top panel
                topPanel.add(leftCharacterPanel);
                topPanel.add(rightCharacterPanel);

  

                // Add top and bottom panels to the main panel
                add(topPanel, BorderLayout.CENTER); // Top section
                add(bottomPanel, BorderLayout.SOUTH); // Bottom section

                // Adjust the proportions of the bottom section
                bottomPanel.setPreferredSize(new Dimension(800, 130));


                // Add action listeners for menu options
        }

        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
}
