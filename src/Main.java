import javax.swing.*;


import java.awt.*;
import java.net.URL;

import screen.SelectionListener;
import screen.menu.CharacterSelection;
import screen.menu.MapSelection;
import screen.BattleScreen;
import screen.LoadingOverlay;
import screen.MainMenu;

import character.race.Elf;
import character.race.Human;
import character.race.Orc;
import character.Character;
import character.classes.Mage;

public class Main extends JFrame implements SelectionListener {

    private JPanel mainPanel;
    private CharacterSelection selectionMenu;
    private MapSelection mapSelection;
    private boolean start = false;
    private int map = -1;
    private Image backgroundImage;
    private BattleScreen battleScreen;
    private LoadingOverlay loadingOverlay; // Loading overlay instance
    private MainMenu mainMenu;

    private String[] races = { "Angel", "Orc", "Minotaur" };
    private String[] classes = { "Warrior", "Mage", "Rogue" };
    private Character[] allies;
    private int[] selectedRace, selectedClass; // Initial

    @Override
    public void onMapSelected(int map) {
        System.out.println("Map " + map + " selected");

        loadingOverlay.turnOn();
        SwingUtilities.invokeLater(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.map = map;
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
                Thread.sleep(10); // Simulate some loading delay (1 second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < characters.length; i++) {
                System.out.println("Character " + characters[i] + " selected with class " + classes[i]);

            }
            selectedRace = characters;
            selectedClass = classes;
            updateMenuScreen();
            loadingOverlay.turnOff(); // Turn off the loading overlay after the game screen is updated
        });

    }

    @Override
    public void onMenuMapSelected() {
        System.out.println("Menu Map Selected");
        updateMapScreen();
    }

    @Override
    public void onMenuCharacterSelected() {
        updateCharacterScreen();
    }

    @Override
    public void onMenuPlaySelected() {
        System.out.println("Starting the game...");
        // Add the game-starting logic here\
        updateGameScreen();
    }
    public Main() {
        // Initialize the loading overlay
        loadingOverlay = new LoadingOverlay();

        setTitle("Masters of MQ RPG");
        setSize(800, 600);
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
        loadingOverlay.setBounds(0, 0, getWidth(), getHeight());
        loadingOverlay.turnOff();

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

        selectionMenu = new CharacterSelection(this);
        mainPanel.add(selectionMenu, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    
    private void updateMapScreen() {
        mainPanel.removeAll();
        mapSelection = new MapSelection(this);
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
        battleScreen = new BattleScreen(backgroundImage, selectedRace,selectedClass);
        mainPanel.add(battleScreen, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
