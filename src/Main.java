
import java.awt.*;
import java.net.URL;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;

import screen.BattleScreen;
import screen.DiceOverlay;
import screen.LoadingOverlay;
import screen.MainMenu;
import screen.SelectionListener;
import screen.BeginOverlay;
import screen.GameEnd;

import screen.menu.GuideMenu;
import screen.menu.CharacterSelection;
import screen.menu.MapSelection;

import character.Character;
import character.CharacterClass;
import character.classes.Mage;
import character.classes.Rogue;
import character.classes.Warrior;
import character.enemyRaces.Golem;
import character.enemyRaces.Reaper;
import character.enemyRaces.Zombie;
import character.races.Angel;
import character.races.Minotaur;
import character.races.Orc;

public class Main extends JFrame implements SelectionListener {

    // Declare the main panel
    private JPanel mainPanel;

    // Initialize the map variable
    private int map = 1;

    // Declare the characters and their classes
    private ArrayList<Character> allies;
    private ArrayList<Character> enemies;
    private int[] selectedRace = { 0, 1, 2 };
    private int[] selectedClass = { 1, 2, 0 };
    private int[] enemyRace;
    private int[] enemyClass;

    // Declare the menu screens and its components
    private CharacterSelection selectionMenu;
    private MapSelection mapSelection;
    private GuideMenu guideMenu;
    private MainMenu mainMenu;

    // Declare the battle screen components
    private Image battlebackImage;
    private BattleScreen battleScreen;

    // Declare the screen overlays
    private LoadingOverlay loadingOverlay;
    private DiceOverlay DiceOverlay;
    private BeginOverlay beginOverlay;
    private GameEnd gameEnd;

    @Override
    public void onMapSelected(int map) {
        loadingOverlay.turnOn();
        new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                updateMenuScreen(); // Update the menu screen, bring back the main menu
                loadingOverlay.turnOff();
            }
        }.execute();
        if (map >= 1) {
            this.map = map; // Set the map to the selected map
        }
    }

    @Override
    public void onCharacterSelected(int[] characters, int[] classes) {
        loadingOverlay.turnOn();
        new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                if (characters.length > 0 && classes.length > 0) { // Check if the arrays are not empty
                    selectedRace = characters;
                    selectedClass = classes;
                }
                updateMenuScreen(); // Update the menu screen, bring back the main menu
                loadingOverlay.turnOff();
            }
        }.execute();
    }

    @Override
    public void onMenuMapSelected() { // Method to handle map option selection on the main menu
        loadingOverlay.turnOn();
        new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                updateMapScreen(); // Update the map screen
                loadingOverlay.turnOff();
            }
        }.execute();
    }

    @Override
    public void onMenuCharacterSelected() { // Method to handle character selection on the main menu
        loadingOverlay.turnOn();
        new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                updateCharacterScreen(); // Update the character selection screen
                loadingOverlay.turnOff();
            }
        }.execute();
    }

    @Override
    public void onMenuPlaySelected() { // Method to handle play option selection on the main menu
        loadingOverlay.turnOn();
        new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                updateGameScreen(); // Update the game screen
                loadingOverlay.turnOff();
            }
        }.execute();
    }

    @Override
    public void onMenuGuideSelected() { // Method to handle guide option selection on the main menu
        loadingOverlay.turnOn();
        new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                updateGuideScreen();
                loadingOverlay.turnOff();
            }
        }.execute();
    }

    @Override
    public void onGuideBack() {
        loadingOverlay.turnOn();
        new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                updateMenuScreen(); // Update the menu screen, bring back the main menu
                loadingOverlay.turnOff();
            }
        }.execute();
    }

    @Override
    public int onCharacterAttack(int source, int target, int dice1, int dice2) {
        // Set the dice images and turn on the overlay
        DiceOverlay.setDice(dice1, dice2);
                DiceOverlay.turnOn();// Turn off the overlay after the delay

                new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
                    @Override
                    protected Void doInBackground() throws Exception {
                        Thread.sleep(1000); // Delay for 1 second
                        return null;
                    }

                    @Override
                    protected void done() {
                        DiceOverlay.turnOff(); // Turn off the overlay after the delay
                    }
                }.execute();

        int totalDamge; // Total damage dealt
        if (source < 3) { // Check if the source is an ally
            totalDamge = allies.get(source).attack(enemies.get(target), dice1, dice2); // Calculate the total damage
        } else { // If the source is an enemy
            totalDamge = enemies.get(source - 3).attack(allies.get(target), dice1, dice2); // Calculate the total damage
        }
        return totalDamge; // Return the total damage dealt
    }

    @Override
    public int onCharacterDefend(int source, int dice) {
        if (source < 3) { // Check if the source is an ally
            int newDefense = allies.get(source).getDefense() + dice; // Increase the defense by the dice roll
            allies.get(source).setDefense(newDefense);
            return allies.get(source).getDefense();
        } else { // If the source is an enemy
            int newDefense = enemies.get(source - 3).getDefense() + dice; // Increase the defense by the dice roll
            enemies.get(source - 3).setDefense(newDefense);
            return enemies.get(source - 3).getDefense();
        }
    }

    @Override
    public int onCharacterUseAbility(int source, int target, int dice1, int dice2) {
        DiceOverlay.turnOn();
        new SwingWorker<Void, Void>() { // Create a new SwingWorker to handle the delay
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                DiceOverlay.turnOff();
            }
        }.execute();
        int totalDamage = 0; // Total damage dealt
        if (source < 3) {
            totalDamage = allies.get(source).useClassAbility(enemies.get(target)); // Use the class ability on the target
        } else {
            System.out.println("source: " + source + " target: " + target);
            totalDamage = enemies.get(source - 3).useClassAbility(allies.get(target)); // Use the class ability on the target
        }
        return totalDamage;
    }

    @Override
    public int getCharacterTurn() {
        int highestAgilityCharacter = 0; // Set the character with the highest agility to the first character
        int highestAgility = Integer.MIN_VALUE; // Set the highest agility to the lowest possible value

        for (int i = 0; i < allies.size(); i++) {
            if (allies.get(i).getAgility() > highestAgility && allies.get(i).isAlive()) {
                highestAgilityCharacter = i;
                highestAgility = allies.get(i).getAgility();
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getAgility() > highestAgility && enemies.get(i).isAlive()) {
                highestAgilityCharacter = i + 3;
                highestAgility = enemies.get(i).getAgility();
            }
        }

        if (highestAgilityCharacter < 3) { // Check if the character is an ally
            allies.get(highestAgilityCharacter).setAgility(-1); // Set the agility to -1 to prevent the character from
                                                            // moving again
        } else {
            enemies.get(highestAgilityCharacter - 3).setAgility(-1); // Set the agility to -1 to prevent the character from
                                                                 // moving again
        }
        return highestAgilityCharacter; // Return the character with the highest agility
    }

    @Override
    public boolean isGameOn() { // Check if the game is still running
        boolean alliesAlive = false;
        boolean enemiesAlive = false;
        for (int i = 0; i < allies.size(); i++) { // Check if any allies are still alive
            if (allies.get(i).isAlive()) {
                alliesAlive = true;
                break;
            }
        }
        for (int i = 0; i < enemies.size(); i++) { // Check if any enemies are still alive
            if (enemies.get(i).isAlive()) {
                enemiesAlive = true;
                break;
            }
        }
        return alliesAlive && enemiesAlive; // Return true if both allies and enemies are still alive
    }

    @Override
    public int getAllyHp(int index) { // Helper method to get the HP of an ally
        return allies.get(index).getHp();
    }

    @Override
    public int getEnemyHp(int index) { // Helper method to get the HP of an enemy
        return enemies.get(index).getHp();
    }

    @Override
    public void resetAgility() { // Reset the agility of all characters on new turn
        for (int i = 0; i < allies.size(); i++) {
            int maxAgility = allies.get(i).getMaxAgility();
            allies.get(i).setAgility(maxAgility);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).setAgility(enemies.get(i).getMaxAgility());
        }
    }

    @Override
    public void gameEnd() { // Method to handle the end of the game
        gameEnd.turnOn(); // Turn on the game end overlay
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(8000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                updateMenuScreen(); // Return to the main menu
                gameEnd.turnOff();
            }
        }.execute();

    }

    @Override
    public void resetDefense(int source) { // Reset the defense of a character
        if (source < 3) {
            allies.get(source).resetDefense();
        } else {
            enemies.get(source - 3).resetDefense();
        }
    }

    public Main() { // Constructor

        // Set the title, size, default close operation, location, layout, and resizable
        setTitle("Masters of MQ RPG");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Set the icon of the frame
        ImageIcon icon = new ImageIcon("/assets/logo.png");
        Image image = icon.getImage();
        setIconImage(image);

        // Initialize the loading overlay
        loadingOverlay = new LoadingOverlay();
        DiceOverlay = new DiceOverlay();
        beginOverlay = new BeginOverlay();
        gameEnd = new GameEnd();

        // Create the main panel
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);

                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout()); // Set the layout of the main panel to BorderLayout
        add(mainPanel);

        // Add the overlays to the layered pane
        getLayeredPane().add(loadingOverlay, JLayeredPane.POPUP_LAYER);
        getLayeredPane().add(DiceOverlay, JLayeredPane.POPUP_LAYER);
        getLayeredPane().add(beginOverlay, JLayeredPane.POPUP_LAYER);
        getLayeredPane().add(gameEnd, JLayeredPane.POPUP_LAYER);
        gameEnd.setBounds(0, 0, getWidth(), getHeight());
        gameEnd.turnOff();
        DiceOverlay.setBounds(0, 0, getWidth(), getHeight());
        DiceOverlay.turnOff();
        loadingOverlay.setBounds(0, 0, getWidth(), getHeight());
        loadingOverlay.turnOff();
        beginOverlay.setBounds(0, 0, getWidth(), getHeight());
        beginOverlay.turnOff();

        // Initialize the main menu
        updateMenuScreen();
        setVisible(true);
    }

    // Helper methods to update the screens
    private void updateMenuScreen() {
        mainPanel.removeAll(); // Remove all components from the main panel
        mainMenu = new MainMenu(this);
        mainPanel.add(mainMenu, BorderLayout.CENTER); // Add the main menu to the main panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Helper methods to update the screens
    private void updateCharacterScreen() {
        mainPanel.removeAll(); // Remove all components from the main panel
        selectionMenu = new CharacterSelection(this, selectedRace, selectedClass);
        mainPanel.add(selectionMenu, BorderLayout.CENTER); // Add the character selection screen to the main panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateGuideScreen() {
        mainPanel.removeAll(); // Remove all components from the main panel
        guideMenu = new GuideMenu(this);
        mainPanel.add(guideMenu, BorderLayout.CENTER); // Add the guide screen to the main panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateMapScreen() {
        mainPanel.removeAll(); // Remove all components from the main panel
        mapSelection = new MapSelection(this, map);
        mainPanel.add(mapSelection, BorderLayout.CENTER); // Add the map selection screen to the main panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateGameScreen() {
        mainPanel.removeAll(); // Remove all components from the main panel

        String backgroundPath = "/assets/background/battleback" + map + ".png";
        URL resource = getClass().getResource(backgroundPath);
        if (resource != null) {
            battlebackImage = new ImageIcon(resource).getImage(); // Load the background image
        } else {
            System.out.println("Error: Background image not found at " + backgroundPath);
        }

        // Initialize the characters and the battle screen
        updateCharacters();
        battleScreen = new BattleScreen(battlebackImage, selectedRace, selectedClass, enemyRace, enemyClass, this);

        // Add the battle screen to the main panel
        mainPanel.add(battleScreen, BorderLayout.CENTER);

        beginOverlay.turnOn(); // Turn on the begin overlay
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(3000); // Delay for 1 second
                return null;
            }

            @Override
            protected void done() {
                beginOverlay.turnOff();
            }
        }.execute();

        // Repaint and revalidate the main panel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateCharacters() {
        // Initialize the characters and their classes
        allies = new ArrayList<Character>();

        enemies = new ArrayList<Character>();
        for (int i = 0; i < selectedRace.length; i++) {
            CharacterClass currClass;
            switch (selectedClass[i]) {
                case 0:
                    currClass = new Warrior();
                    break;
                case 1:
                    currClass = new Mage();
                    break;
                case 2:
                    currClass = new Rogue();
                    break;
                default:
                    currClass = new Warrior();
                    break;
            }
            switch (selectedRace[i]) {
                case 0:
                    allies.add( new Angel("Angle", currClass));
                    break;
                case 1:
                    allies.add( new Angel("Orc", currClass));
                    break;
                case 2:
                    allies.add( new Angel("Minotaur", currClass));
                    break;
                default:
                    allies.add( new Angel("Angle", currClass));
                    break;
            }
        }

        // Initialize the enemies and their classes
        enemyRace = new int[3];
        enemyClass = new int[3];
        for (int i = 0; i < enemyRace.length; i++) {
            enemyRace[i] = generateRandomNumber();
            enemyClass[i] = generateRandomNumber();
        }
        for (int i = 0; i < enemyRace.length; i++) {
            CharacterClass currClass;
            switch (enemyClass[i]) {
                case 0:
                    currClass = new Warrior();
                    break;
                case 1:
                    currClass = new Mage();
                    break;
                case 2:
                    currClass = new Rogue();
                    break;
                default:
                    currClass = new Warrior();
                    break;
            }
            switch (enemyRace[i]) {
                case 0:
                    enemies.add( new Zombie("Zombie", currClass)) ;
                    break;
                case 1:
                    enemies.add( new Golem("Golem", currClass)) ;
                    break;
                case 2:
                    enemies.add( new Reaper("Reaper", currClass)) ;
                    break;
                default:
                    enemies.add( new Zombie("Zombie", currClass)) ;
                    break;
            }
        }
    }

    // Helper method to generate a random number
    private int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(3);
    }

    public static void main(String[] args) {
        Main game = new Main(); // Create a new instance of the game window
        game.setVisible(true); // Set the game window to be visible
    }
}
