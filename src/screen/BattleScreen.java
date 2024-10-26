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
    private Random random;
    private SelectionListener listener;
    private Image backgroundImage;

    private JPanel topPanel, bottomPanel;
    private JPanel leftCharacterPanel, rightCharacterPanel;

    private JLabel allyNameBox, allyStatus, enemyNameBox, enemyStatus;
    private JLabel[] allyNameLabels, enemyNameLabels;

    // Declare character labels for allies and enemies
    private CharacterLabel[] alliesLabel;
    private EnemyLabel[] enemiesLabel;

    // Declare health bars for allies and enemies
    private JProgressBar[] enemyHealthBars, allyHealthBars; // Add health bars for enemies

    // Declare variables for characters
    private int[] selectedRace, selectedClass, enemyRace, enemyClass;
    private String[] allyRaces = { "angel", "orc", "minotaur" };
    private String[] enemyRaces = { "zombie", "golem", "reaper" };
    private String[] classes = { "warrior", "mage", "rogue" };
    private String[] specialAbility = { "Blazing Valor", "Celestial Torrent", "Shadow Dance" };

    // Declare Battle Log instance
    private Log logPanel;

    // Initialize variables for the game state
    private int dead = 0;
    private int turn = 0;
    private int source, target;
    private boolean isPlayerTurn = true;
    private int[] allyWithDefenseStand = { 0, 0, 0 };
    private int[] enemyWithDefenseStand = { 0, 0, 0 };
    private int[] allyWithSpecialAbility = { 0, 0, 0 };
    private int[] enemyWithSpecialAbility = { 0, 0, 0 };
    private boolean wasPlayerTurn = true;

    // Constructor for the BattleScreen
    public BattleScreen(Image backgroundImage, int[] selectedRace, int[] selectedClass, int[] enemyRace,
            int[] enemyClass, SelectionListener listener) {

        // Initialize the listener, background image, and character selections
        this.listener = listener;
        this.backgroundImage = backgroundImage;
        this.selectedRace = selectedRace;
        this.selectedClass = selectedClass;
        this.enemyRace = enemyRace;
        this.enemyClass = enemyClass;
        this.random = new Random();
        this.dead = 0;

        // Set the layout of the BattleScreen
        setLayout(new BorderLayout());

        // Initialize panels and draw the battle screen
        drawBattleScreen();

        // Call the function to update the game for the first time
        updateGame();
    }

    // Function to draw the battle screen
    private void drawBattleScreen() {
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
            createAllyLabel(x, y, i);

        }

        enemyHealthBars = new JProgressBar[3]; // Initialize array of health bars for enemies
        enemiesLabel = new EnemyLabel[3];
        enemyNameLabels = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            int x = 240 - (i % 2) * 90;
            int y = 210 + (i * 60);
            createEnemyLabel(x, y, i);
        }

        // Add character panels to top panel
        topPanel.add(leftCharacterPanel);
        topPanel.add(rightCharacterPanel);

        // Initialize log panel
        logPanel = new Log("=== Battle Log ===", null);
        logPanel.addMessage("Battle started!");

        bottomPanel.add(logPanel, BorderLayout.WEST);

        drawActionButtons();

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.setPreferredSize(new Dimension(800, 130));
    }

    // Function to create ally labels
    private void createAllyLabel(int x, int y, int index) {
        // Create a new character label for the ally
        CharacterLabel allyLabel = new CharacterLabel(allyRaces[selectedRace[index]], 18, 12, 15, 12,
                x, y);

        // Create a new health bar for the ally
        JProgressBar allyHealthBar = new JProgressBar(0, listener.getAllyHp(index));
        allyHealthBar.setValue(listener.getAllyHp(index)); // Set full health initially
        allyHealthBar.setBounds(x + 20, y + 110, 80, 10); // Position health bar under ally

        // Create a new name label for the ally
        String name = allyRaces[selectedRace[index]].substring(0, 1).toUpperCase()
                + allyRaces[selectedRace[index]].substring(1);
        JLabel allyNameLabel = new JLabel(name + " " + classes[selectedClass[index]]); // Set ally name
        allyNameLabel.setBounds(x + 20, y + 120, 120, 20); // Position under the health bar
        allyNameLabel.setForeground(Color.WHITE); // text white

        // Store the ally label, name label, and health bar
        alliesLabel[index] = allyLabel;
        allyNameLabels[index] = allyNameLabel;
        allyHealthBars[index] = allyHealthBar;

        leftCharacterPanel.add(allyLabel); // Add ally label to left panel
        leftCharacterPanel.add(allyNameLabel); // Add name label to the panel
        leftCharacterPanel.add(allyHealthBar); // Add health bar to left panel for allies
    }

    // Function to create enemy labels
    private void createEnemyLabel(int x, int y, int index) {
        EnemyLabel enemyLabel = new EnemyLabel(enemyRaces[enemyRace[index]], 18, 12, 15, 12,
                x, y);

        JProgressBar healthBar = new JProgressBar(0, listener.getEnemyHp(index));
        healthBar.setValue(listener.getEnemyHp(index)); // Set full health initially
        healthBar.setBounds(x + 20, y + 110, 80, 10); // Position under enemyLabel

        String name = enemyRaces[enemyRace[index]].substring(0, 1).toUpperCase()
                + enemyRaces[enemyRace[index]].substring(1);
        JLabel enemyNameLabel = new JLabel(name + " " + classes[enemyClass[index]]); // Set enemy name
        enemyNameLabel.setBounds(x + 20, y + 120, 120, 20); // Position
        enemyNameLabel.setForeground(Color.WHITE); // text white

        enemiesLabel[index] = enemyLabel;
        enemyHealthBars[index] = healthBar; // Store health bar
        enemyNameLabels[index] = enemyNameLabel;

        rightCharacterPanel.add(enemyNameLabel); // Add name label to the panel
        rightCharacterPanel.add(healthBar); // health bar to panel
        rightCharacterPanel.add(enemyLabel); // Add enemy label to right panel
    }

    // Function to draw action buttons for the bottom panel
    private void drawActionButtons() {
        // Create a new panel for the action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setOpaque(false);

        // Add both the button panel and the name box to the bottom panel
        JPanel allyNameBoxPanel = initialiseInfoPanel(true);
        JPanel enemyNameBoxPanel = initialiseInfoPanel(false);
        buttonPanel.add(allyNameBoxPanel); // Add the name box panel
        buttonPanel.add(enemyNameBoxPanel); // Add the name box panel

        // Create action buttons for the player
        JButton attackButton = createActionButton("Strike", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allyAttack();
            }
        });
        JButton defendButton = createActionButton("Defense Stand", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allyDefense();
            }
        });
        JButton specialButton = createActionButton("Special Ability", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allySpecialAbility();
            }
        });

        // Add action buttons to the button panel
        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);
        buttonPanel.add(specialButton);

        // Add the button panel to the bottom panel
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
    }

    private JPanel initialiseInfoPanel(boolean isAlly) {
        // Create the panel for ally's name and status
        JPanel nameBoxPanel = new JPanel();
        nameBoxPanel.setBackground(Color.BLACK); // Set background to black
        nameBoxPanel.setPreferredSize(new Dimension(140, 110)); // Set the size
        nameBoxPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Set layout only once
        nameBoxPanel.setLayout(new BorderLayout());

        // Create and add the name label
        JLabel nameLabel = new JLabel("Name", SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE); // Set text color to white
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Create and add the status label
        JLabel statusLabel = new JLabel("status", SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE); // Set text color to white
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setVerticalAlignment(SwingConstants.CENTER);
        if (isAlly) {
            nameLabel.setText("Ally");
            statusLabel.setText("Ally: Attacking");
            allyNameBox = nameLabel;
            allyStatus = statusLabel;
        } else {
            nameLabel.setText("Enemy");
            statusLabel.setText("Enemy: Defending");
            enemyNameBox = nameLabel;
            enemyStatus = statusLabel;
        }

        nameBoxPanel.add(nameLabel, BorderLayout.CENTER); // Add name label to the center
        nameBoxPanel.add(statusLabel, BorderLayout.NORTH);

        return nameBoxPanel;
    }

    private JButton createActionButton(String text, ActionListener listener) {
        Dimension buttonSize = new Dimension(120, 120);
        JButton button = new JButton(text);
        button.setPreferredSize(buttonSize);
        button.addActionListener(listener);
        return button;
    }

    // Function to update the game state, check for game end, and update the turn
    private void updateGame() {
        if (checkGameEnd()) { // Check if the game has ended
            return;
        }
        checkTurn(); // Check the turn
        resetNameColor(); // Reset the name color of the characters

        // Get the character turn and update the game state
        int characterTurn = listener.getCharacterTurn();

        // Check if last turn was player's turn for battle log
        if (turn % (6 - dead) == 0) {
            if (characterTurn < 3) {
                wasPlayerTurn = false;
            } else {
                wasPlayerTurn = true;
            }
        }

        // Check if the character turn is less than 3 (for allies)
        if (characterTurn < 3) {
            // Set the source to the character turn
            source = characterTurn;
            isPlayerTurn = true;

            // Get a random target for the enemy
            target = random.nextInt(3);
            while (listener.getEnemyHp(target) <= 0) {
                target = random.nextInt(3);
            }

            // Update the information panel
            updateInfoPanel();

            // Add a message to the battle log
            if (!wasPlayerTurn) {
                logPanel.addMessage(" ");
                logPanel.addMessage("[Ally’s Turn]");
            }
        } else {
            // Set the source to the character turn minus 3 (for enemies)
            source = characterTurn - 3;
            turn += 1;
            isPlayerTurn = false;

            // Get a random target for the ally
            target = random.nextInt(3);
            while (listener.getAllyHp(target) <= 0) {
                target = random.nextInt(3);
            }

            // Add a message to the battle log
            if (wasPlayerTurn) {
                logPanel.addMessage(" ");
                logPanel.addMessage("[Enemy’s Turn]");
            }

            // Update the information panel
            updateInfoPanel();

            // Randomize the enemy action
            int action = random.nextInt(3);
            while (action == 2 && enemyWithSpecialAbility[source] > 0) {
                action = random.nextInt(3);
            }
            switch (action) { // Perform the enemy action based on the random number
                case 0:
                    enemyAttack();
                    break;
                case 1:
                    enemyDefense();
                    break;
                case 2:
                    enemySpecialAbility();
                    break;
                default:
                    enemyAttack();
                    break;
            }
        }
    }

    private void updateInfoPanel() {
        // Update the information panel with character names, health, etc.
        if (isPlayerTurn) {
            // Set the name of the ally and enemy
            String name = allyRaces[selectedRace[source]].substring(0, 1).toUpperCase()
                    + allyRaces[selectedRace[source]].substring(1);
            String enemyName = enemyRaces[enemyRace[target]].substring(0, 1).toUpperCase()
                    + enemyRaces[enemyRace[target]].substring(1);

            // Set the ally name and status
            allyNameBox.setText(name + " " + classes[selectedClass[source]]);
            allyStatus.setText("Ally: Attacking");

            // Set the enemy name and status
            enemyNameBox.setText(enemyName + " " + classes[enemyClass[target]]);
            enemyStatus.setText("Enemy: Defending");

            // Set the color of the name labels
            allyNameLabels[source].setForeground(Color.GREEN);
            enemyNameLabels[target].setForeground(Color.RED);
        } else {
            // Set the name of the enemy and ally
            String name = enemyRaces[enemyRace[source]].substring(0, 1).toUpperCase()
                    + enemyRaces[enemyRace[source]].substring(1);
            String allyName = allyRaces[selectedRace[target]].substring(0, 1).toUpperCase()
                    + allyRaces[selectedRace[target]].substring(1);

            // Set the enemy name and status
            enemyNameBox.setText(name + " " + classes[enemyClass[source]]);
            enemyStatus.setText("Enemy: Attacking");

            // Set the ally name and status
            allyNameBox.setText(allyName + " " + classes[selectedClass[target]]);
            allyStatus.setText("Ally: Defending");

            // Set the color of the name labels
            enemyNameLabels[source].setForeground(Color.GREEN);
            allyNameLabels[target].setForeground(Color.RED);
        }
    }

    private void allyAttack() {
        // Disable the button if it's not the player's turn
        if (isPlayerTurn == false) {
            return;
        }
        // Set the player's turn to false
        isPlayerTurn = false;
        turn += 1;

        // Roll two dice for both sides
        int leftRoll = rollDice();
        int rightRoll = rollDice();

        // Call the character attack method and get the total damage
        int totalDamage = listener.onCharacterAttack(source, target, leftRoll, rightRoll);

        new SwingWorker<Void, Void>() { // Create a new SwingWorker for the attack animation
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1100); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                // Update the state of the ally label
                alliesLabel[source].setState("attack");

                // Update the enemy health bar with the new health value
                enemyHealthBars[target].setValue(listener.getEnemyHp(target));

                // Get the name of the ally and Enemy
                String name = allyRaces[selectedRace[source]].substring(0, 1).toUpperCase()
                        + allyRaces[selectedRace[source]].substring(1);
                String enemyName = enemyRaces[enemyRace[target]].substring(0, 1).toUpperCase()
                        + enemyRaces[enemyRace[target]].substring(1);

                // Add a message to the battle log
                logPanel.addMessage("- " + name + " attacks! "
                        + enemyName + " takes " + totalDamage + " damage.");

                // Check if the enemy's health is less than or equal to 0
                if (listener.getEnemyHp(target) <= 0) {
                    logPanel.addMessage("* " + enemyRaces[enemyRace[target]] + " defeated! *");
                    enemiesLabel[target].setState("die");
                    dead += 1;
                    updateTurn();
                } else {
                    enemiesLabel[target].setState("hurt");

                }
                // set wasPlayerTurn to true for battle log
                wasPlayerTurn = true;
                updateGame();
            }
        }.execute();
    }

    // Method for ally defense action, increases defense temporarily
    private void allyDefense() {
        if (!isPlayerTurn) {
            return;
        }
        isPlayerTurn = false;

        int defense = listener.onCharacterDefend(source, rollDice());
        String name = allyRaces[selectedRace[source]].substring(0, 1).toUpperCase()
                + allyRaces[selectedRace[source]].substring(1);

        logPanel.addMessage("- " + name + " defends! " + name
                + "'s defense increases by " + defense + " for 2 rounds.");

        allyWithDefenseStand[source] = 3; // Set defense duration
        turn += 1;
        wasPlayerTurn = true;
        updateGame();
    }

    // Method for ally special ability action with animation delay
    private void allySpecialAbility() {
        if (!isPlayerTurn) {
            return;
        }
        if (allyWithSpecialAbility[source] > 0) { // Check if ability is on cooldown
            logPanel.addMessage("Special ability is on cooldown for " + allyWithSpecialAbility[source] + " rounds.");
            return;
        }

        isPlayerTurn = false;
        wasPlayerTurn = true;
        turn += 1;

        // Roll dice to determine ability effect
        int leftRoll = rollDice();
        int rightRoll = rollDice();
        int dmg = listener.onCharacterUseAbility(source, target, leftRoll, rightRoll);

        // Execute delay before applying ability
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1100); // Delay for 1.1 seconds
                return null;
            }

            @Override
            protected void done() {
                alliesLabel[source].setState("attack");

                // Update enemy health and log the ability effect
                enemyHealthBars[target].setValue(listener.getEnemyHp(target));
                String name = allyRaces[selectedRace[source]].substring(0, 1).toUpperCase()
                        + allyRaces[selectedRace[source]].substring(1);
                String special = specialAbility[selectedClass[source]];
                String enemyName = enemyRaces[enemyRace[target]].substring(0, 1).toUpperCase()
                        + enemyRaces[enemyRace[target]].substring(1);
                logPanel.addMessage("- " + name + " uses " + special + "! " + enemyName + " takes " + dmg + " damage.");

                if (listener.getEnemyHp(target) <= 0) { // Check if enemy is defeated
                    logPanel.addMessage("* " + enemyRaces[enemyRace[target]] + " defeated! *");
                    enemiesLabel[target].setState("die");
                    dead += 1;
                    updateTurn();
                } else {
                    enemiesLabel[target].setState("hurt");
                }

                allyWithSpecialAbility[source] = 3; // Set cooldown duration
                wasPlayerTurn = true;
                updateGame();
            }
        }.execute();
    }

    // Method for enemy attack action with animation delay
    private void enemyAttack() {
        int leftRoll = rollDice();
        int rightRoll = rollDice();

        // Execute delay before attack
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1500); // Delay for 1.5 seconds
                return null;
            }

            @Override
            protected void done() {
                int dmg = listener.onCharacterAttack(source + 3, target, leftRoll, rightRoll);

                // Execute delay after attack effect
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        Thread.sleep(1500); // Delay for 1.5 seconds
                        return null;
                    }

                    @Override
                    protected void done() {
                        enemiesLabel[source].setState("attack");
                        String name = enemyRaces[enemyRace[source]].substring(0, 1).toUpperCase()
                                + enemyRaces[enemyRace[source]].substring(1);
                        logPanel.addMessage("- " + name + " attacks! Deals " + dmg + " damage.");

                        // Update ally's health and state
                        allyHealthBars[target].setValue(listener.getAllyHp(target));
                        if (listener.getAllyHp(target) <= 0) { // Check if ally is defeated
                            logPanel.addMessage("* Ally defeated! *");
                            alliesLabel[target].setState("die");
                            dead += 1;
                            updateTurn();
                        } else {
                            alliesLabel[target].setState("hurt");
                        }
                        wasPlayerTurn = false;
                        updateGame();
                    }
                }.execute();
            }
        }.execute();
    }

    // Method for enemy defense action, increases defense temporarily
    private void enemyDefense() {
        int defense = listener.onCharacterDefend(source + 3, rollDice());
        String name = enemyRaces[enemyRace[source]].substring(0, 1).toUpperCase()
                + enemyRaces[enemyRace[source]].substring(1);
        logPanel.addMessage(
                "- " + name + " defends! " + name + "'s defense increases by " + defense + " for 2 rounds.");

        // Execute delay before effect applies
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1500); // Delay for 1.5 seconds
                return null;
            }

            @Override
            protected void done() {
                enemyWithDefenseStand[source] = 3; // Set defense duration
                wasPlayerTurn = false;
                updateGame();
            }
        }.execute();
    }

    // Method for triggering an enemy's special ability with animation delay
    private void enemySpecialAbility() {
        int leftRoll = rollDice();
        int rightRoll = rollDice();

        // Execute first delay before ability activation
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1500); // Delay for 1.5 seconds
                return null;
            }

            @Override
            protected void done() {
                // Trigger ability and calculate damage
                int dmg = listener.onCharacterUseAbility(source + 3, target, leftRoll, rightRoll);

                // Execute second delay after ability activation
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        Thread.sleep(1500); // Delay for 1.5 seconds
                        return null;
                    }

                    @Override
                    protected void done() {
                        enemiesLabel[source].setState("attack");

                        // Log the enemy's special ability usage
                        String name = enemyRaces[enemyRace[source]].substring(0, 1).toUpperCase()
                                + enemyRaces[enemyRace[source]].substring(1);
                        String special = specialAbility[enemyClass[source]];
                        logPanel.addMessage("- " + name + " uses " + special + "! Deals " + dmg + " damage.");

                        // Update ally's health and state
                        allyHealthBars[target].setValue(listener.getAllyHp(target));
                        if (listener.getAllyHp(target) <= 0) {
                            logPanel.addMessage("* Ally defeated! *");
                            alliesLabel[target].setState("die");
                            dead += 1;
                            updateTurn();
                        } else {
                            alliesLabel[target].setState("hurt");
                        }

                        // Mark enemy's special ability as used and update game state
                        enemyWithSpecialAbility[source] = 3;
                        wasPlayerTurn = false;
                        updateGame();
                    }
                }.execute();
            }
        }.execute();
    }

    // Method to decrement special ability counters and reset defenses if expired
    private void checkSpecialAbility() {
        for (int i = 0; i < allyWithSpecialAbility.length; i++) {
            if (allyWithSpecialAbility[i] > 0) {
                allyWithSpecialAbility[i] -= 1;
            }
        }
        for (int i = 0; i < enemyWithSpecialAbility.length; i++) {
            if (enemyWithSpecialAbility[i] > 0) {
                enemyWithSpecialAbility[i] -= 1;
            }
        }
    }

    // Method to decrement defense counters and reset defenses if expired
    private void checkDefense() {
        for (int i : allyWithDefenseStand) {
            if (i > 0) {
                i -= 1;
                if (i == 0) {
                    listener.resetDefense(source);
                }
            }
        }
        for (int i : enemyWithDefenseStand) {
            if (i > 0) {
                i -= 1;
                if (i == 0) {
                    listener.resetDefense(source + 3);
                }
            }
        }
    }

    // Method to reset the color of ally and enemy name labels
    private void resetNameColor() {
        for (JLabel label : allyNameLabels) {
            label.setForeground(Color.WHITE);
        }
        for (JLabel label : enemyNameLabels) {
            label.setForeground(Color.WHITE);
        }
    }

    // Method to check if the game has ended
    private boolean checkGameEnd() {
        if (!listener.isGameOn()) {
            // Check if all allies are defeated
            boolean isAllyWin = false;
            for (int i = 0; i < 3; i++) {
                if (listener.getAllyHp(i) > 0) {
                    isAllyWin = true;
                    break;
                }
            }

            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/assets/logo.png"));

            // Scale the image to 50x50 pixels
            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            // Show message dialog with icon
            if (!isAllyWin) {
                logPanel.addMessage("=== Enemies Win! ===");
                JOptionPane.showMessageDialog(null, "Enemies Win! Outputting battle log.", "Game Over",
                        JOptionPane.INFORMATION_MESSAGE,
                        scaledIcon);
            } else {
                logPanel.addMessage("=== Allies Win! ===");
                JOptionPane.showMessageDialog(null, "Allies Win! Outputting battle log.", "Game Over",
                        JOptionPane.INFORMATION_MESSAGE,
                        scaledIcon);
            }

            // Export the battle log to a CSV file
            exportLog();

            // Notify listener to handle end-of-game actions
            listener.gameEnd();
            return true;
        }
        return false;
    }

    // Method to manage turn-based logic in the game
    private void checkTurn() {
        if (turn % (6 - dead) == 0) {
            logPanel.addMessage(" ");
            logPanel.addMessage("--- Turn " + ((turn / (6 - dead)) + 1) + " ---");

            // Reset agility, check defense, and special abilities
            listener.resetAgility();
            checkDefense();
            checkSpecialAbility();
        }
    }

    // Method to update the turn based on the number of dead allies
    private void updateTurn() {
        double deadTurn = Math.floor(turn / (7 - this.dead));
        turn -= (int) deadTurn;
    }

    // Method to simulate rolling a six-sided dice
    private int rollDice() {
        return random.nextInt(6) + 1; // Returns a random integer between 1 and 6
    }

    private void exportLog() {
        logPanel.exportToCSV("./battle_log.csv");
    }

    // Override the paintComponent to draw the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}
