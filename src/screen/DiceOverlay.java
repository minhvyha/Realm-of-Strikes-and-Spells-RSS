package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.net.URL;

public class DiceOverlay extends JPanel {
    private Image dice1, dice2;
    private int x1, y1, x2, y2; // Coordinates for dice positions

    public DiceOverlay() {
        setOpaque(false); // Make the panel transparent except for its content

        // Add a mouse listener to consume all mouse events and block interaction
        addMouseListener(new MouseAdapter() {
        });
        addMouseMotionListener(new MouseAdapter() {
        });

        // Set default positions for the dice
        x1 = 250;
        y1 = 350;
        x2 = 750;
        y2 = 350;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Darken the screen
        g2d.setColor(new Color(0, 0, 0, 180)); // Semi-transparent black
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the two dice if they are loaded
        if (dice1 != null && dice2 != null) {
            g2d.drawImage(dice1, x1, y1, 100, 100, this);
            g2d.drawImage(dice2, x2, y2, 100, 100, this);
        }

        g2d.dispose();
    }

    // Load dice images based on the rolled numbers
    public void setDice(int roll1, int roll2) {
        URL resource1 = getClass().getResource("/assets/dice/" + roll1 + ".png");
        if (resource1 != null) {
            dice1 = new ImageIcon(resource1).getImage();
        } else {
            System.out.println("Error: Background image not found at " + "/assets/dice/" + roll1 + ".png");
        }
        URL resource = getClass().getResource("/assets/dice/" + roll2 + ".png");
        if (resource != null) {
            dice2 = new ImageIcon(resource).getImage();
        } else {
            System.out.println("Error: Background image not found at " + "/assets/dice/" + roll2 + ".png");
        }
        repaint(); // Redraw the overlay to show the dice
    }

    // Turn on the dice overlay
    public void turnOn() {
        setVisible(true);
    }

    // Turn off the dice overlay
    public void turnOff() {
        setVisible(false);
    }
}
