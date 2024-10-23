package screen;

import Characters.CharacterLabel;
import Characters.EnemyLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class BattleScreen extends JPanel {
    private JPanel topPanel, bottomPanel;
    private JPanel leftCharacterPanel, rightCharacterPanel;
    private JProgressBar[] enemyHealthBars; // Add health bars for enemies
    private CharacterLabel orcLabel; // Replace JLabel with CharacterLabel
    private EnemyLabel[] enemyLabels;
    private Image backgroundImage;
    private Character[] allies;
    private Character[] enemies;

    private int[] selectedRace, selectedClass;
    private String[] allyRaces = { "angel", "orc", "minotaur" };
    private String[] enemyRaces = { "zombie", "golem", "reaper" };
    private String[] classes = { "warrior", "mage", "rogue" };

    public BattleScreen(Image backgroundImage, int[] selectedRace, int[] selectedClass) {
        this.backgroundImage = backgroundImage;
        this.selectedRace = selectedRace;
        this.selectedClass = selectedClass;

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

        enemyHealthBars = new JProgressBar[3]; // Initialize array of health bars for enemies

        for (int i = 0; i < 3; i++) {
            // Adding allies to the leftCharacterPanel
            CharacterLabel allyLabel = new CharacterLabel(allyRaces[selectedRace[i]], 18, 12, 15, 12,
            70 + (i % 2) * 80, 150 + (i * 60)); // Set the frame and position for allies
            leftCharacterPanel.add(allyLabel); // Add ally label to left panel

            //health bar for allies
            JProgressBar allyHealthBar = new JProgressBar(0, 100);
            allyHealthBar.setValue(100); // Set full health initially
            allyHealthBar.setBounds(70 + (i % 2) * 80, 255 + (i * 60), 80, 10); // Position health bar under ally
            allyHealthBar.setForeground(Color.GREEN); // Set to green
            leftCharacterPanel.add(allyHealthBar); // Add health bar to left panel for allies

            JLabel allyNameLabel = new JLabel(allyRaces[selectedRace[i]]); // Set ally name
            allyNameLabel.setBounds(70 + (i % 2) * 80, 265 + (i * 60), 80, 20); // Position under the health bar
            allyNameLabel.setForeground(Color.WHITE); // text white 
            leftCharacterPanel.add(allyNameLabel); //Add name label to the panel
            
        }

        for (int i = 0; i < 3; i++) {
                EnemyLabel enemyLabel = new EnemyLabel(enemyRaces[generateRandomNumber()], 18, 12, 15, 12,
                        220 - (i % 2) * 80, 150 + (i * 60));
                rightCharacterPanel.add(enemyLabel);
    
                JProgressBar healthBar = new JProgressBar(0, 100);
                healthBar.setValue(100); // Set full health initially
                healthBar.setBounds(230 - (i % 2)*80, 255 + (i*60), 80, 10); // Position under enemyLabel
                healthBar.setForeground(Color.GREEN); //colour to green
    
                enemyHealthBars[i] = healthBar; // Store health bar
                rightCharacterPanel.add(healthBar); //health bar to panel

                JLabel enemyNameLabel = new JLabel(enemyRaces[generateRandomNumber()]);
                enemyNameLabel.setBounds(230 - (i % 2) * 80, 265 + (i * 60), 80, 20); // Position
                enemyNameLabel.setForeground(Color.WHITE); //text white 
                rightCharacterPanel.add(enemyNameLabel); // Add name label to the panel

            }

        // Add character panels to top panel
        topPanel.add(leftCharacterPanel);
        topPanel.add(rightCharacterPanel);

        // Add top and bottom panels to the main panel
        add(topPanel, BorderLayout.CENTER); 
        add(bottomPanel, BorderLayout.SOUTH); 

        bottomPanel.setPreferredSize(new Dimension(800, 130));

        // Add action listeners for menu options
        initializeActionButtons();
    }

    private void initializeActionButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 5)); 
        buttonPanel.setOpaque(false); 
        
        JButton attackButton = new JButton("Attack");
        JButton defendButton = new JButton("Defend");
        JButton specialButton = new JButton("Special");

        Dimension buttonSize = new Dimension(130, 40); 
        attackButton.setPreferredSize(buttonSize);
        defendButton.setPreferredSize(buttonSize);
        specialButton.setPreferredSize(buttonSize);

        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        buttonPanel.add(specialButton);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerPanel.setOpaque(false); 
        centerPanel.add(buttonPanel);

        bottomPanel.add(centerPanel, BorderLayout.CENTER);

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Attack button clicked!");
                enemyHealthBars[0].setValue(enemyHealthBars[0].getValue() - 10);
            }
        });

        defendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Defend button clicked!");
            }
        });

        specialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Special button clicked!");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(3); 
    }
}
