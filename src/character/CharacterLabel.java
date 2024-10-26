package character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class CharacterLabel extends JLabel {

    private int currentFrame;
    private ImageIcon[] idle, attack, die, hurt;
    private Timer animationTimer;
    private String currentState;

    // Desired image dimensions for scaling and aspect ratio
    private final int DESIRED_WIDTH = 1050;
    private final int DESIRED_HEIGHT = 1050;
    private final int TARGET_HEIGHT = 120;

    public CharacterLabel(String name, int maxIdleFrame, int maxAttackFrame, int maxDieFrame, int maxHurtFrame, int xPosition, int yPosition) {
        this.currentFrame = 0;
        this.currentState = "idle";

        // Initialize animation frames
        this.idle = loadFrames(name, "Idle", maxIdleFrame);
        this.attack = loadFrames(name, "Attack", maxAttackFrame);
        this.die = loadFrames(name, "Die", maxDieFrame);
        this.hurt = loadFrames(name, "Hurt", maxHurtFrame);

        // Set initial label bounds and icon
        this.setBounds(xPosition, yPosition, idle[0].getIconWidth(), idle[0].getIconHeight());
        setupAnimationTimer();
    }

    // Helper method to load and resize frames for each animation state
    private ImageIcon[] loadFrames(String name, String action, int maxFrames) {
        ImageIcon[] frames = new ImageIcon[maxFrames];
        for (int i = 0; i < maxFrames; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/" + action + "/" + i + ".png");
            if (imageURL != null) {
                frames[i] = resizeImageIcon(new ImageIcon(imageURL), TARGET_HEIGHT);
            } else {
                System.err.println("Image not found (" + action + "): " + imageURL);
            }
        }
        return frames;
    }

    // Resize ImageIcon to target height while maintaining aspect ratio
    private ImageIcon resizeImageIcon(ImageIcon icon, int targetHeight) {
        Image img = icon.getImage();
        int width = (int) (targetHeight * ((double) DESIRED_WIDTH / DESIRED_HEIGHT));
        Image scaledImg = img.getScaledInstance(width, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    // Set up the timer to manage animation frames
    private void setupAnimationTimer() {
        animationTimer = new Timer(50, (ActionEvent e) -> {
            ImageIcon[] frames = switch (currentState) {
                case "attack" -> attack;
                case "die" -> die;
                case "hurt" -> hurt;
                default -> idle;
            };
            
            if (currentFrame == 0 && (currentState.equals("attack") || currentState.equals("hurt"))) {
                setState("hurt");
            }
            setIcon(frames[currentFrame = (currentFrame - 1 + frames.length) % frames.length]);
            repaint();
        });
        animationTimer.start();
    }

    // Change the animation state and reset frame counter as needed
    public void setState(String newState) {
        this.currentState = newState;
        switch (newState) {
            case "die" -> currentFrame = die.length - 1;
            case "attack" -> currentFrame = attack.length - 1;
            case "hurt" -> currentFrame = hurt.length - 1;
            case "idle" -> currentFrame = idle.length - 1;
        }
    }
}
