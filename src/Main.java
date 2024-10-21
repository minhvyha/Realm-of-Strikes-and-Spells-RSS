import javax.swing.*;
import java.awt.*;
import java.net.URL;

import screen.SelectionMenu;
import screen.SelectionListener;
import screen.BattleScreen;

public class Main extends JFrame implements SelectionListener {

    private JPanel mainPanel;
    private SelectionMenu selectionMenu;
    private boolean start = false;
    private int map = -1;
    private Image backgroundImage;
    private BattleScreen battleScreen;

    @Override
    public void onSelectionMade(int map) {
        System.out.println("Map " + map + " selected");
        this.map = map;
        setStart(true);
    }

    public void setStart(boolean value) {
        this.start = value;
        System.out.println("Start is now: " + start);

        if (start) {
            updateGameScreen();
        }
    }

    public Main() {
        // Load background image

        setTitle("Masters of MQ RPG");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

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

        if (!start) {
            selectionMenu = new SelectionMenu(this);
            mainPanel.add(selectionMenu, BorderLayout.CENTER);
        }

        setVisible(true);
    }

    private void updateGameScreen() {
        mainPanel.remove(selectionMenu);
        String backgroundPath = "/assets/background/battleback" + map + ".png";
    
        URL resource = getClass().getResource(backgroundPath);
        if (resource != null) {
            backgroundImage = new ImageIcon(resource).getImage();
        } else {
            System.out.println("Error: Background image not found at " + backgroundPath);
        }
        // Create and add the BattleScreen instancesrc/assets/background/battleback1.png
        battleScreen = new BattleScreen(backgroundImage);
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
