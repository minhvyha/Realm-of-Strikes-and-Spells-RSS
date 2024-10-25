
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
import screen.GameEnd;

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

    // Declare the main panel
    private JPanel mainPanel;

    // Initialize the map variable
    private int map = 1;

    // Declare the characters and their classes
    private Character[] allies;
    private Character[] enemies;
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
        new SwingWorker<Void, Void>() {  // Create a new SwingWorker to handle the delay
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
        if(map >= 1){
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
                for (int i = 0; i < characters.length; i++) {
                }
                if (characters.length > 0 && classes.length > 0) {
                    selectedRace = characters;
                    selectedClass = classes;
                }
                updateMenuScreen();
                loadingOverlay.turnOff();
            }
        }.execute();

    }

    @Override
    public void onMenuMapSelected() {
        loadingOverlay.turnOn();

        
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }
    
            @Override
            protected void done() {

                updateMapScreen();
                loadingOverlay.turnOff(); 
            }
        }.execute();
    }

    @Override
    public void onMenuCharacterSelected() {
        
        loadingOverlay.turnOn();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }
    
            @Override
            protected void done() {

                updateCharacterScreen();
                loadingOverlay.turnOff();
            }
        }.execute();
    }

    @Override
    public void onMenuPlaySelected() {
        loadingOverlay.turnOn();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(1000); // Delay for 1 second
                return null;
            }
    
            @Override
            protected void done() {

                updateGameScreen();

                loadingOverlay.turnOff();

            }
        }.execute();
        // Add the game-starting logic here\
    }

    @Override
    public void onMenuGuideSelected() {
        loadingOverlay.turnOn();
        new SwingWorker<Void, Void>() {
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
    public int onCharacterAttack(int source, int target, int dice1, int dice2) {

        DiceOverlay.setDice(dice1, dice2);
        DiceOverlay.turnOn();
        new SwingWorker<Void, Void>() {
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

        int dmg;
        if(source < 3){
             dmg = allies[source].getStrength() * dice1 - enemies[target].getDefense() * dice2;
            if(dmg > 0){
                enemies[target].takeDamage(dmg);
            }
            else{
                dmg = 0;
            }
        }
        else{
             dmg = enemies[source - 3].getStrength() * dice2 - allies[target].getDefense() * dice1;
            if(dmg > 0){
                allies[target].takeDamage(dmg);
            }
            else{
                dmg = 0;
            }
        }
        return dmg;
    }

    @Override
    public int onCharacterDefend(int source, int dice) {
        if(source < 3){
            allies[source].setDefense(allies[source].getDefense() + dice);
            return allies[source].getDefense();
        }
        else{
            enemies[source - 3].setDefense(enemies[source - 3].getDefense() + dice);
            return enemies[source - 3].getDefense();
        }
    }

    @Override
    public int onCharacterUseAbility(int source, int target, int dice1, int dice2) {
        DiceOverlay.turnOn();
        new SwingWorker<Void, Void>() {
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
        if(source < 3){
            int dmg = allies[source].useClassAbility(enemies[target]);
            return dmg;
        }
        else{
            int dmg = enemies[source - 3].useClassAbility(allies[target]);
            return dmg;
        }
    }

    @Override
    public int getCharacterTurn(){
        int target = 0;
        int highestAgility = Integer.MIN_VALUE;

        for (int i = 0; i < allies.length; i++) {
            if (allies[i].getAgility() > highestAgility && allies[i].isAlive()) {
                target = i;
                highestAgility = allies[i].getAgility();
            }
        }
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i].getAgility() > highestAgility && enemies[i].isAlive()) {
                target = i + 3;
                highestAgility = enemies[i].getAgility();
            }
        }

        for(int i = 0; i < allies.length; i++){
            if(allies[i].isAlive()){
                System.out.println("Ally " + i + " agility: " + allies[i].getAgility());
            }
        }
        for(int i = 0; i < enemies.length; i++){
            if(enemies[i].isAlive()){
                System.out.println("Enemy " + i + " agility: " + enemies[i].getAgility());
            }
        }

        if(target < 3){
            allies[target].setAgility(-1);
        }
        else{
            enemies[target - 3].setAgility(-1);
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

    @Override
    public void resetAgility(){
        System.out.println("Resetting agility");
        for (int i = 0; i < allies.length; i++) {
            allies[i].setAgility(allies[i].getMaxAgility());
        }
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].setAgility(enemies[i].getMaxAgility());
        }
    }

    @Override
    public int getAllyHp(int index) {
        return allies[index].getHp();

    }

    @Override
    public int getEnemyHp(int index) {
        return enemies[index].getHp();
    }

    @Override
    public void gameEnd() {
        gameEnd.turnOn();
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(8000); // Delay for 1 second
                return null;
            }
    
            @Override
            protected void done() {

                updateMenuScreen();
                gameEnd.turnOff();
            }
        }.execute();
        
    }

    @Override
    public void resetDefense(int source) {
        if(source < 3){
            allies[source].resetDefense();
        }
        else{
            enemies[source - 3].resetDefense();
        }
    }


    public Main() {
        // Initialize the loading overlay
        loadingOverlay = new LoadingOverlay();
        DiceOverlay = new DiceOverlay();
        beginOverlay = new BeginOverlay();
        gameEnd = new GameEnd();

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
                    g.setColor(Color.BLACK);

                    g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Add the loading overlay on top of the main panel
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
            battlebackImage = new ImageIcon(resource).getImage();
        } else {
            System.out.println("Error: Background image not found at " + backgroundPath);
        }
        // Create and add the BattleScreen instance
        updateCharacters();
        battleScreen = new BattleScreen(battlebackImage, selectedRace, selectedClass, enemyRace, enemyClass, this);
        mainPanel.add(battleScreen, BorderLayout.CENTER);

        beginOverlay.turnOn();
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
        
        mainPanel.revalidate();
        mainPanel.repaint();

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
            Main game = new Main();
            game.setVisible(true);
    }
}
