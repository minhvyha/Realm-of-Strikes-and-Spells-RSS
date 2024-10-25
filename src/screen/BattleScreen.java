package screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import screen.log.Log;
import character.CharacterLabel;
import character.EnemyLabel;

public class BattleScreen extends JPanel {
    private JPanel topPanel, bottomPanel;
    private JPanel leftCharacterPanel, rightCharacterPanel;
    private Random random;
    private SelectionListener listener;
    private Image backgroundImage;

    private int source, target;
    
    private JProgressBar[] enemyHealthBars, allyHealthBars; // Add health bars for enemies
    private CharacterLabel[] alliesLabel;
    private EnemyLabel[] enemiesLabel;
    private JLabel[] allyNameLabels, enemyNameLabels;

    private int[] selectedRace, selectedClass, enemyRace, enemyClass;
    private String[] allyRaces = { "angel", "orc", "minotaur" };
    private String[] enemyRaces = { "zombie", "golem", "reaper" };
    private String[] classes = { "warrior", "mage", "rogue" };

    // Declare Log instance
    private Log logPanel;

    private int turn = 0;

    public BattleScreen(Image backgroundImage, int[] selectedRace, int[] selectedClass, int[] enemyRace,
            int[] enemyClass, SelectionListener listener) {
        this.listener = listener;
        this.backgroundImage = backgroundImage;
        this.selectedRace = selectedRace;
        this.selectedClass = selectedClass;
        this.enemyRace = enemyRace;
        this.enemyClass = enemyClass;
        this.random = new Random();

        setLayout(new BorderLayout());
        // Initialize panels
        initializePanels();

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

        allyHealthBars = new JProgressBar[3]; // Initialize array of health bars for allies
        alliesLabel = new CharacterLabel[3];
        allyNameLabels = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            int x = 150 + (i % 2) * 90;
            int y = 210 + (i * 60);
            CharacterLabel allyLabel = new CharacterLabel(allyRaces[selectedRace[i]], 18, 12, 15, 12,
                    x, y); // Set the frame and position for allies
            leftCharacterPanel.add(allyLabel); // Add ally label to left panel
            alliesLabel[i] = allyLabel;

            JProgressBar allyHealthBar = new JProgressBar(0, 100);
            allyHealthBar.setValue(100); // Set full health initially
            allyHealthBar.setBounds(x + 20, y + 105, 80, 10); // Position health bar under ally
            allyHealthBar.setForeground(Color.GREEN); // Set bar color to green
            allyHealthBar.setBackground(Color.BLACK);
            leftCharacterPanel.add(allyHealthBar); // Add health bar to left panel for allies
            allyHealthBars[i] = allyHealthBar;
            
            JLabel allyNameLabel = new JLabel(allyRaces[selectedRace[i]] + " " + classes[selectedClass[i]]); // Set ally name
            allyNameLabel.setBounds(x + 20, y + 110, 120, 20); // Position under the health bar
            allyNameLabel.setForeground(Color.WHITE); // text white
            leftCharacterPanel.add(allyNameLabel); // Add name label to the panel
            allyNameLabels[i] = allyNameLabel;

        }

        enemyHealthBars = new JProgressBar[3]; // Initialize array of health bars for enemies
        enemiesLabel = new EnemyLabel[3];
        enemyNameLabels = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            int x = 240 - (i % 2) * 90;
            int y = 210 + (i * 60);
            EnemyLabel enemyLabel = new EnemyLabel(enemyRaces[enemyRace[i]], 18, 12, 15, 12,
                    x, y);
            rightCharacterPanel.add(enemyLabel);
            enemiesLabel[i] = enemyLabel;

            JProgressBar healthBar = new JProgressBar(0, 100);
            healthBar.setValue(100); // Set full health initially
            healthBar.setBounds(x + 20, y + 105, 80, 10); // Position under enemyLabel
            healthBar.setForeground(Color.GREEN); // colour to green

            enemyHealthBars[i] = healthBar; // Store health bar
            rightCharacterPanel.add(healthBar); // health bar to panel

            JLabel enemyNameLabel = new JLabel(enemyRaces[enemyRace[i]] + " " +classes[enemyClass[i]]); // Set enemy name);
            enemyNameLabel.setBounds(x + 20, y + 110, 120, 20); // Position
            enemyNameLabel.setForeground(Color.WHITE); // text white
            rightCharacterPanel.add(enemyNameLabel); // Add name label to the panel
            enemyNameLabels[i] = enemyNameLabel;

        }

        // Add character panels to top panel
        topPanel.add(leftCharacterPanel);
        topPanel.add(rightCharacterPanel);

        // Initialize log panel
        logPanel = new Log("Battle started!", null);

        // Add log panel to the left side of the bottom panel without scroll bars
        bottomPanel.add(logPanel, BorderLayout.WEST);

        // Add buttons to the bottom panel's center area
        initializeActionButtons();

        // Add top and bottom panels to the main panel
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.setPreferredSize(new Dimension(800, 130));
    }

    private void initializeActionButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
buttonPanel.setOpaque(false);

JButton attackButton = new JButton("Attack");
JButton defendButton = new JButton("Defend");
JButton specialButton = new JButton("Special");

Dimension buttonSize = new Dimension(130, 120);
attackButton.setPreferredSize(buttonSize);
defendButton.setPreferredSize(buttonSize);
specialButton.setPreferredSize(buttonSize);



buttonPanel.add(attackButton);
buttonPanel.add(defendButton);
buttonPanel.add(specialButton);


        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerPanel.setOpaque(false);
        centerPanel.add(buttonPanel);
        bottomPanel.add(centerPanel, BorderLayout.EAST);

        // Add action listeners to buttons
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn += 1;

                logPanel.addMessage("Player attacks!");

                // Roll two dice for both sides
                int leftRoll = rollDice();

                int rightRoll = rollDice();
        
                listener.onCharacterAttack(source, target, leftRoll, rightRoll);


                // Example damage calculation and panel actions (as before)
                int currentHealth = enemyHealthBars[target].getValue();
                enemiesLabel[target].setState("hurt");
                int baseDamage = 10;
                int totalDamage = baseDamage + leftRoll - rightRoll;

                int newHealth = currentHealth - totalDamage;
                newHealth = Math.max(newHealth, 0);

                enemyHealthBars[target].setValue(newHealth);

                if (newHealth <= 0) {
                    logPanel.addMessage("Enemy defeated!");
                } else {
                    logPanel.addMessage("Enemy takes " + totalDamage + " damage.");
                }
                updateGame();
            }
        });

        defendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logPanel.addMessage("Player defends!");
            }
        });

        specialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logPanel.addMessage("Player uses special ability!");
            }
        });
        updateGame();
    }

    private void updateGame() {
        if(turn % 6 == 0) {
            logPanel.addMessage("Turn " + (int) Math.floor(turn));
        }
        int characterTurn = listener.getCharacterTurn();
        System.out.println(characterTurn);

        for(JLabel label : allyNameLabels){
            label.setForeground(Color.WHITE);
        }
        for(JLabel label : enemyNameLabels){
            label.setForeground(Color.WHITE);
        }

        if(characterTurn < 3){
            allyNameLabels[characterTurn].setForeground(Color.GREEN);
            source = characterTurn;
            target = random.nextInt(3);
            enemyNameLabels[target].setForeground(Color.RED);
        }
        else{
            logPanel.addMessage("Enemy turn");
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.sleep(5000); // Delay for 1 second
                    return null;
                }
        
                @Override
                protected void done() {
                    enemyNameLabels[characterTurn - 3].setForeground(Color.GREEN);
                    target = random.nextInt(3);
                    allyNameLabels[target].setForeground(Color.RED);
                    System.out.println("enemy turn");
                }
            }.execute();


        }
        // Update game state here
        // For example, update health bars, check for victory conditions, etc.
    }

    private int rollDice() {
        return random.nextInt(6) + 1; // Roll dice (1-6)
    }

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
