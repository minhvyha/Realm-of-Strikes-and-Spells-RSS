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
    private JLabel allyNameBox, allyStatus, enemyNameBox, enemyStatus;
    private Random random;
    private SelectionListener listener;
    private Image backgroundImage;

    private int source, target;
    private boolean isPlayerTurn = true;

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

            JProgressBar allyHealthBar = new JProgressBar(0, listener.getAllyHp(i));
            allyHealthBar.setValue(listener.getAllyHp(i)); // Set full health initially
            allyHealthBar.setBounds(x + 20, y + 105, 80, 10); // Position health bar under ally
            allyHealthBar.setForeground(Color.GREEN); // Set bar color to green
            allyHealthBar.setBackground(Color.BLACK);
            leftCharacterPanel.add(allyHealthBar); // Add health bar to left panel for allies
            allyHealthBars[i] = allyHealthBar;

            JLabel allyNameLabel = new JLabel(allyRaces[selectedRace[i]] + " " + classes[selectedClass[i]]); // Set ally
                                                                                                             // name
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

            JProgressBar healthBar = new JProgressBar(0, listener.getEnemyHp(i));
            healthBar.setValue(listener.getEnemyHp(i)); // Set full health initially
            healthBar.setBounds(x + 20, y + 105, 80, 10); // Position under enemyLabel
            healthBar.setForeground(Color.GREEN); // colour to green

            enemyHealthBars[i] = healthBar; // Store health bar
            rightCharacterPanel.add(healthBar); // health bar to panel

            JLabel enemyNameLabel = new JLabel(enemyRaces[enemyRace[i]] + " " + classes[enemyClass[i]]); // Set enemy
                                                                                                         // name);
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
        bottomPanel.add(logPanel, BorderLayout.WEST);
        // Main panel to hold both inner panels

        // Add log panel to the left side of the bottom panel without scroll bars

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

        JPanel enemyNameBoxPanel = new JPanel();
        enemyNameBoxPanel.setBackground(Color.BLACK); // Set background to black
        enemyNameBoxPanel.setPreferredSize(new Dimension(140, 110)); // Set the size
        enemyNameBoxPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        enemyNameBoxPanel.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel("Name", SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE); // Set text color to white
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        enemyNameBoxPanel.setLayout(new BorderLayout());
        enemyNameBoxPanel.add(nameLabel, BorderLayout.CENTER);

        JLabel enemyStatusLabel = new JLabel("Enemy: Defending", SwingConstants.CENTER);
        enemyStatusLabel.setForeground(Color.WHITE); // Set text color to white
        enemyStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enemyStatusLabel.setVerticalAlignment(SwingConstants.CENTER);
        enemyNameBoxPanel.add(enemyStatusLabel, BorderLayout.NORTH);

        enemyNameBox = nameLabel;
        enemyStatus = enemyStatusLabel;

        // Create the panel for ally's name and status
        JPanel allyNameBoxPanel = new JPanel();
        allyNameBoxPanel.setBackground(Color.BLACK); // Set background to black
        allyNameBoxPanel.setPreferredSize(new Dimension(140, 110)); // Set the size
        allyNameBoxPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Set layout only once
        allyNameBoxPanel.setLayout(new BorderLayout());

        // Create and add the name label
        JLabel allyNameLabel = new JLabel("Name", SwingConstants.CENTER);
        allyNameLabel.setForeground(Color.WHITE); // Set text color to white
        allyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        allyNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        allyNameBoxPanel.add(allyNameLabel, BorderLayout.CENTER); // Add name label to the center

        // Create and add the status label
        JLabel allyStatusLabel = new JLabel("Ally: Attacking", SwingConstants.CENTER);
        allyStatusLabel.setForeground(Color.WHITE); // Set text color to white
        allyStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        allyStatusLabel.setVerticalAlignment(SwingConstants.CENTER);
        allyNameBoxPanel.add(allyStatusLabel, BorderLayout.NORTH);

        // Add both the button panel and the name box to the bottom panel
        buttonPanel.add(allyNameBoxPanel); // Add the name box panel
        buttonPanel.add(enemyNameBoxPanel); // Add the name box panel

        allyNameBox = allyNameLabel;
        allyStatus = allyStatusLabel;

        JButton attackButton = new JButton("Strike");
        JButton defendButton = new JButton("Defense Stand");
        JButton specialButton = new JButton("Special Ability");

        Dimension buttonSize = new Dimension(120, 120);
        attackButton.setPreferredSize(buttonSize);
        defendButton.setPreferredSize(buttonSize);
        specialButton.setPreferredSize(buttonSize);

        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        buttonPanel.add(specialButton);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        // Add action listeners to buttons
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPlayerTurn == false) {
                    return;
                }
                isPlayerTurn = false;
                turn += 1;

                logPanel.addMessage("Player attacks!");

                // Roll two dice for both sides
                int leftRoll = rollDice();

                int rightRoll = rollDice();
                int dmg = listener.onCharacterAttack(source, target, leftRoll, rightRoll);

                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        Thread.sleep(1100); // Delay for 1 second
                        return null;
                    }
            
                    @Override
                    protected void done() {

                        
                        alliesLabel[source].setState("attack");
        
                        enemyHealthBars[target].setValue(listener.getEnemyHp(target));
        
                        if (listener.getEnemyHp(target) <= 0) {
                            logPanel.addMessage("Enemy defeated!");
                            enemiesLabel[target].setState("die");
                        } else {
                            logPanel.addMessage("Enemy takes " + dmg + " damage.");
                            enemiesLabel[target].setState("hurt");

                        }
                        updateGame();
                    }
                }.execute();


            }
        });

        defendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPlayerTurn == false) {
                    return;
                }
                isPlayerTurn = false;
                logPanel.addMessage("Player defends!");
            }
        });

        specialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPlayerTurn == false) {
                    return;
                }
                isPlayerTurn = false;
                logPanel.addMessage("Player uses special ability!");
            } 
        });
        updateGame();
    }

    private void updateGame() {
        if (turn % 6 == 0) {
            logPanel.addMessage("Turn " + ((turn / 6) + 1));
            listener.resetAgility();
        }
        int characterTurn = listener.getCharacterTurn();
        System.out.println(characterTurn);

        for (JLabel label : allyNameLabels) {
            label.setForeground(Color.WHITE);
        }
        for (JLabel label : enemyNameLabels) {
            label.setForeground(Color.WHITE);
        }

        if (characterTurn < 3) {
            System.out.println("Player turn");
            isPlayerTurn = true;
            allyNameLabels[characterTurn].setForeground(Color.GREEN);
            source = characterTurn;
            target = random.nextInt(3);
            while(listener.getEnemyHp(target) <= 0) {
                target = random.nextInt(3);
            }
            updateInfoPanel();
            enemyNameLabels[target].setForeground(Color.RED);
        } else {
            isPlayerTurn = false;
            source = characterTurn - 3;

            target = random.nextInt(3);
            while(listener.getAllyHp(target) <= 0) {
                target = random.nextInt(3);
            }
            enemyNameLabels[characterTurn - 3].setForeground(Color.GREEN);
            allyNameLabels[target].setForeground(Color.RED);

            logPanel.addMessage("Enemy turn");
            updateInfoPanel();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.sleep(2000); // Delay for 1 second
                    return null;
                }

                @Override
                protected void done() {
                    int leftRoll = rollDice();

                    int rightRoll = rollDice();
                    int dmg = listener.onCharacterAttack(source + 3, target, leftRoll, rightRoll);
                    
                    enemiesLabel[source].setState("attack");
                    
                    logPanel.addMessage("Enemy attacks!");
                    logPanel.addMessage("Enemy deals " + dmg + " damage.");
                    allyHealthBars[target].setValue(listener.getAllyHp(target));
                    if (listener.getAllyHp(target) <= 0) {
                        logPanel.addMessage("Ally defeated!");
                        alliesLabel[target].setState("die");
                    }
                    else{
                        alliesLabel[target].setState("hurt");
                    }
                    updateGame();
                }
            }.execute();

        }
        // Update game state here
        // For example, update health bars, check for victory conditions, etc.
    }

    private void updateInfoPanel() {
        // Update the information panel with character names, health, etc.
        if(isPlayerTurn) {
            allyNameBox.setText(allyRaces[selectedRace[source]] + " " + classes[selectedClass[source]]);
            allyStatus.setText("Ally: Attacking");
            enemyNameBox.setText(enemyRaces[enemyRace[target]] + " " + classes[enemyClass[target]]);
            enemyStatus.setText("Enemy: Defending");
        } else {
            allyNameBox.setText(allyRaces[selectedRace[target]] + " " + classes[selectedClass[target]]);
            allyStatus.setText("Ally: Defending");
            enemyNameBox.setText(enemyRaces[enemyRace[source]] + " " + classes[enemyClass[source]]);
            enemyStatus.setText("Enemy: Attacking");
        }
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
