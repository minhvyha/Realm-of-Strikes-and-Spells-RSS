package character;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CharacterLabel extends JLabel {

    private int currentFrame;
    private ImageIcon[] idle, attack, die, hurt;
    private Timer animationTimer;
    private String currentState;

    // Desired aspect ratio (2000:1050) and height
    private final int DESIRED_WIDTH = 1050;
    private final int DESIRED_HEIGHT = 1050;
    private final int TARGET_HEIGHT = 120; // Scale down to this height

    public CharacterLabel(String name, int maxIdleFrame, int maxAttackFrame, int maxDieFrame, int maxHurtFrame,
            int xPosition, int yPosition) {
        this.currentFrame = 0;
        this.idle = new ImageIcon[maxIdleFrame]; // Initialize the idle frames array
        this.attack = new ImageIcon[maxAttackFrame]; // Initialize the attack frames array
        this.die = new ImageIcon[maxDieFrame]; // Initialize the die frames array
        this.hurt = new ImageIcon[maxHurtFrame]; // Initialize the hurt frames array
        this.currentState = "idle"; // Default to idle state

        // Load and scale frames for different states
        for (int i = 0; i < maxIdleFrame; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/Idle/" + i + ".png");
            if (imageURL != null) {
                this.idle[i] = resizeImageIcon(new ImageIcon(imageURL), TARGET_HEIGHT);
            } else {
                System.err.println("Image not found (idle): " + imageURL);
            }
        }

        for (int i = 0; i < maxAttackFrame; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/Attack/" + i + ".png");
            if (imageURL != null) {
                this.attack[i] = resizeImageIcon(new ImageIcon(imageURL), TARGET_HEIGHT);
            } else {
                System.err.println("Image not found: " + imageURL);
            }
        }

        for (int i = 0; i < maxDieFrame; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/Die/" + i + ".png");
            if (imageURL != null) {
                this.die[i] = resizeImageIcon(new ImageIcon(imageURL), TARGET_HEIGHT);
            } else {
                System.err.println("Image not found: " + imageURL);
            }
        }

        for (int i = 0; i < maxHurtFrame; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/Hurt/" + i + ".png");
            if (imageURL != null) {
                this.hurt[i] = resizeImageIcon(new ImageIcon(imageURL), TARGET_HEIGHT);
            } else {
                System.err.println("Image not found: " + imageURL);
            }
        }

        // Set initial frame and position
        this.setBounds(xPosition, yPosition, idle[0].getIconWidth(), idle[0].getIconHeight());

        // Start animation
        setupAnimationTimer();
    }

    // Method to resize ImageIcon to target height while keeping aspect ratio
    private ImageIcon resizeImageIcon(ImageIcon icon, int targetHeight) {
        Image img = icon.getImage();
        int width = (int) (targetHeight * ((double) DESIRED_WIDTH / DESIRED_HEIGHT)); // Maintain aspect ratio 2000:1050
        Image scaledImg = img.getScaledInstance(width, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    private void setupAnimationTimer() {
        animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (currentState) {
                    case "idle":
                        if (currentFrame == 0)
                            currentFrame = idle.length;
                        currentFrame = (currentFrame - 1);
                        setIcon(idle[currentFrame]); // Set die frames
                        break;
                    case "attack":
                        if (currentFrame == 0) {
                            currentState = "idle";
                            break;
                        }
                        currentFrame = (currentFrame - 1);
                        setIcon(attack[currentFrame]); // Set die frames
                        break;
                    case "die":
                        if (currentFrame == 0)
                            break;
                        currentFrame = (currentFrame - 1);
                        setIcon(die[currentFrame]); // Set die frames
                        break;
                    case "hurt":
                        if (currentFrame == 0) {
                            currentState = "idle";
                            break;
                        }
                        currentFrame = (currentFrame - 1);
                        setIcon(hurt[currentFrame]); // Set die frames
                        break;
                }
                repaint();
            }
        });
        animationTimer.start(); // Start the animation timer
    }

    // Method to change character state
    public void setState(String newState) {
        this.currentState = newState;
        if (newState == "die")
            currentFrame = die.length;
        if (newState == "attack")
            currentFrame = attack.length;
        if (newState == "hurt")
            currentFrame = hurt.length;
        if (newState == "idle")
            currentFrame = idle.length;
    }
}