package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
public class LoadingOverlay extends JPanel {

    public LoadingOverlay() {
        setOpaque(false); // Make the panel transparent except for its content

        // Add a mouse listener to consume all mouse events and block interaction
        addMouseListener(new MouseAdapter() {});
        addMouseMotionListener(new MouseAdapter() {});
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Darken the screen
        g2d.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Set font and color for the "Loading..." text
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.WHITE);

        // Draw the "Loading..." text
        String loadingText = "Loading...";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(loadingText)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

        g2d.drawString(loadingText, x, y);

        g2d.dispose();
    }

    // Turn on the loading overlay
    public void turnOn() {
        setVisible(true);  // Make sure it's visible
    }

    // Turn off the loading overlay
    public void turnOff() {
        setVisible(false);  // Make it invisible
    }
}
