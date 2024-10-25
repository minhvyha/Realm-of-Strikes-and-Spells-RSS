
import java.awt.*;
import java.net.URL;
import java.util.Random;
import javax.swing.*;

import screen.BattleScreen;
import screen.DiceOverlay;
import screen.LoadingOverlay;
import screen.MainMenu;
import screen.SelectionListener;
import screen.BeginOverlay;

import screen.menu.GuideMenu;
import screen.menu.CharacterSelection;
import screen.menu.MapSelection;

import character.Character;
import character.CharacterClass;
import character.classes.Mage;
import character.classes.Rogue;
import character.classes.Warrior;
import character.enemyRace.Golem;
import character.enemyRace.Reaper;
import character.enemyRace.Zombie;
import character.race.Angel;
import character.race.Minotaur;
import character.race.Orc;
public class Main extends JFrame implements SelectionListener {

    private JPanel mainPanel;

    private boolean start = false;
    private int map = 1;

    private String[] races = { "Angel", "Orc", "Minotaur" };
    private String[] classes = { "Warrior", "Mage", "Rogue" };
    private Character[] allies;
    private Character[] enemies;
    private int[] selectedRace = { 0, 1, 2 }; // Initial
    private int[] selectedClass = { 0, 1, 2 }; // Initial
    private int[] enemyRace; // Initial
    private int[] enemyClass; // Initial

    private CharacterSelection selectionMenu;
    private MapSelection mapSelection;
    private GuideMenu guideMenu;
    private MainMenu mainMenu;


    private Image backgroundImage;
    private BattleScreen battleScreen;

    private LoadingOverlay loadingOverlay; // Loading overlay instance
    private DiceOverlay DiceOverlay;
    private BeginOverlay beginOverlay;


    @Override
    public void onMapSelected(int map) {
        System.out.println("Map " + map + " selected");

        loadingOverlay.turnOn();
        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (map >= 0) {

                this.map = map;
            }
            updateMenuScreen();
            loadingOverlay.turnOff();
        });
    }

    @Override
    public void onCharacterSelected(int[] characters, int[] classes) {
        loadingOverlay.turnOn();

        SwingUtilities.invokeLater(() -> {
            // Simulate loading work by calling updateGameScreen() after a small delay
            try {
                Thread.sleep(500); // Simulate some loading delay (1 second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < characters.length; i++) {
                System.out.println("Character " + characters[i] + " selected with class " + classes[i]);

            }
            if (characters.length > 0 && classes.length > 0) {
                selectedRace = characters;
                selectedClass = classes;
            }
            updateMenuScreen();
            loadingOverlay.turnOff(); // Turn off the loading overlay after the game screen is updated
        });

    }

    @Override
    public void onMenuMapSelected() {
        loadingOverlay.turnOn();

        SwingUtilities.invokeLater(() -> {
            // Simulate loading work by calling updateGameScreen() after a small delay
            try {
                Thread.sleep(500); // Simulate some loading delay (1 second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateMapScreen();
            loadingOverlay.turnOff(); // Turn off the loading overlay after the game screen is updated
        });
    }

    @Override
    public void onMenuCharacterSelected() {
        
        loadingOverlay.turnOn();

        SwingUtilities.invokeLater(() -> {
            // Simulate loading work by calling updateGameScreen() after a small delay
            try {
                Thread.sleep(500); // Simulate some loading delay (1 second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateCharacterScreen();
            loadingOverlay.turnOff(); // Turn off the loading overlay after the game screen is updated
        });
    }

    @Override
    public void onMenuPlaySelected() {
        System.out.println("Starting the game...");
        loadingOverlay.turnOn();
        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            updateGameScreen();

            loadingOverlay.turnOff();
        });
        // Add the game-starting logic here\
    }

    @Override
    public void onMenuGuideSelected() {
        System.out.println("Guide selected");
        loadingOverlay.turnOn();


        SwingUtilities.invokeLater(() -> {
            // Simulate loading work by calling updateGameScreen() after a small delay
            try {
                Thread.sleep(500); // Simulate some loading delay (1 second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateGuideScreen();
            loadingOverlay.turnOff(); // Turn off the loading overlay after the game screen is updated
        });
    }

    @Override
    public void onCharacterAttack(int source, int target, int dice1, int dice2) {
        DiceOverlay.setDice(dice1, dice2);
        DiceOverlay.turnOn();
        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            DiceOverlay.turnOff();

        });
        System.out.println("Character " + source + " attacks character " + target);

    }

    @Override
    public void onCharacterDefend(int source, int dice1) {
        System.out.println("Character " + source + " defends");
    }

    @Override

    public void onCharacterUseAbility(int source, int target, int dice1, int dice2) {
        System.out.println("Character " + source + " uses ability on character " + target);
    }

    @Override
    public int getCharacterTurn(){
        // Implement character turn logic here
        // For now, just simulate a random character turn
        int target = 1;
        int lowestAgility = Integer.MAX_VALUE;

        for (int i = 0; i < allies.length; i++) {
            if (allies[i].getAgility() < lowestAgility && allies[i].isAlive()) {
                target = i;
                lowestAgility = allies[i].getAgility();
            }
        }
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i].getAgility() < lowestAgility && enemies[i].isAlive()) {
                target = i + 3;
                lowestAgility = enemies[i].getAgility();
            }
        }
        for (int i = 0; i < allies.length; i++) {
            allies[i].setAgility(allies[i].getAgility() - lowestAgility);
            if(allies[i].getAgility() <= 0 && allies[i].isAlive()){
                allies[i].setAgility(allies[i].getMaxAgility());
            }
        }
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].setAgility(enemies[i].getAgility() - lowestAgility);
            if(enemies[i].getAgility() <= 0 && enemies[i].isAlive()){
                enemies[i].setAgility(enemies[i].getMaxAgility());
            }
        }
        return target;
    }
    
    @Override
    public boolean isGameOn(){
        boolean alliesAlive = false;
        boolean enemiesAlive = false;
        for (int i = 0; i < allies.length; i++) {
            if (allies[i].isAlive()) {
                alliesAlive = true;
                break;
            }
        }
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i].isAlive()) {
                enemiesAlive = true;
                break;
            }
        }
        return alliesAlive && enemiesAlive;
    }


    public Main() {
        // Initialize the loading overlay
        loadingOverlay = new LoadingOverlay();
        DiceOverlay = new DiceOverlay();
        beginOverlay = new BeginOverlay();

        setTitle("Masters of MQ RPG");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        ImageIcon icon = new ImageIcon("/assets/logo.png");
        Image image = icon.getImage();
        setIconImage(image);

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!start) {
                    g.setColor(Color.BLACK);

                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Add the loading overlay on top of the main panel
        getLayeredPane().add(loadingOverlay, JLayeredPane.POPUP_LAYER);
        getLayeredPane().add(DiceOverlay, JLayeredPane.POPUP_LAYER);
        getLayeredPane().add(beginOverlay, JLayeredPane.POPUP_LAYER);
        DiceOverlay.setBounds(0, 0, getWidth(), getHeight());
        DiceOverlay.turnOff();
        loadingOverlay.setBounds(0, 0, getWidth(), getHeight());
        loadingOverlay.turnOff();
        beginOverlay.setBounds(0, 0, getWidth(), getHeight());
        beginOverlay.turnOff();

        updateMenuScreen();
        setVisible(true);
    }

    private void updateMenuScreen() {
        mainPanel.removeAll();
        mainMenu = new MainMenu(this);
        mainPanel.add(mainMenu, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateCharacterScreen() {
        mainPanel.removeAll();

        selectionMenu = new CharacterSelection(this, selectedRace, selectedClass);
        mainPanel.add(selectionMenu, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateGuideScreen() {
        mainPanel.removeAll();
        guideMenu = new GuideMenu(this);
        mainPanel.add(guideMenu, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateMapScreen() {
        mainPanel.removeAll();
        mapSelection = new MapSelection(this, map);
        mainPanel.add(mapSelection, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void updateGameScreen() {
        mainPanel.removeAll();
        String backgroundPath = "/assets/background/battleback" + map + ".png";

        URL resource = getClass().getResource(backgroundPath);
        if (resource != null) {
            backgroundImage = new ImageIcon(resource).getImage();
        } else {
            System.out.println("Error: Background image not found at " + backgroundPath);
        }
        // Create and add the BattleScreen instance
        updateCharacters();
        battleScreen = new BattleScreen(backgroundImage, selectedRace, selectedClass, enemyRace, enemyClass, this);
        mainPanel.add(battleScreen, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
        beginOverlay.turnOn();
        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            beginOverlay.turnOff();
        });
    }

    private void updateCharacters() {
        allies = new Character[selectedRace.length];
        enemies = new Character[selectedRace.length];
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
                    allies[i] = new Angel("Angle", currClass);
                    break;
                case 1:
                    allies[i] = new Orc("Orc", currClass);
                    break;
                case 2:
                    allies[i] = new Minotaur("Minotaur", currClass);
                    break;
                default:
                    allies[i] = new Angel("Angel", currClass);
                    break;
            }
        }
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
                    enemies[i] = new Zombie("name", currClass);
                    break;
                case 1:
                    enemies[i] = new Golem("name", currClass);
                    break;
                case 2:
                    enemies[i] = new Reaper("name", currClass);
                    break;
                default:
                    enemies[i] = new Zombie("name", currClass);
                    break;
            }
        }
    }


    private int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(3);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
