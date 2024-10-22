package Characters;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class EnemyLabel extends JLabel {

    private int currentFrame;
    private ImageIcon[] idle, attack, die, hurt;
    private Timer animationTimer;
    private String currentState;

    // Desired aspect ratio (2000:1050) and height
    private final int desiredWidth = 1050;
    private final int desiredHeight = 1050;
    private final int targetHeight = 120; // Scale down to this height

    public EnemyLabel(String name, int maxIdleFrame, int maxAttackFrame, int maxDieFrame, int maxHurtFrame,
            int xPosition, int yPosition) {
        this.currentFrame = 0;
        this.idle = new ImageIcon[maxIdleFrame]; // Initialize the idle frames array
        this.attack = new ImageIcon[maxAttackFrame]; // Initialize the attack frames array
        this.die = new ImageIcon[maxDieFrame]; // Initialize the die frames array
        this.hurt = new ImageIcon[maxHurtFrame]; // Initialize the hurt frames array
        this.currentState = "idle"; // Default to idle state

        // Load, flip, and scale frames for different states
        for (int i = 0; i < maxIdleFrame; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/Idle/" + i + ".png");
            if (imageURL != null) {
                this.idle[i] = flipImageIcon(resizeImageIcon(new ImageIcon(imageURL), targetHeight));
            } else {
                System.err.println("Image not found (idle): " + imageURL);
            }
        }

        for (int i = 0; i < maxAttackFrame; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/Attack/" + i + ".png");
            if (imageURL != null) {
                this.attack[i] = flipImageIcon(resizeImageIcon(new ImageIcon(imageURL), targetHeight));
            } else {
                System.err.println("Image not found: " + imageURL);
            }
        }

        for (int i = 0; i < maxDieFrame; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/Die/" + i + ".png");
            if (imageURL != null) {
                this.die[i] = flipImageIcon(resizeImageIcon(new ImageIcon(imageURL), targetHeight));
            } else {
                System.err.println("Image not found: " + imageURL);
            }
        }

        for (int i = 0; i < maxHurtFrame; i++) {
            URL imageURL = getClass().getResource("/assets/" + name + "/Hurt/" + i + ".png");
            if (imageURL != null) {
                this.hurt[i] = flipImageIcon(resizeImageIcon(new ImageIcon(imageURL), targetHeight));
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
        int width = (int) (targetHeight * ((double) desiredWidth / desiredHeight)); // Maintain aspect ratio 2000:1050
        Image scaledImg = img.getScaledInstance(width, targetHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    // Method to flip ImageIcon horizontally
    private ImageIcon flipImageIcon(ImageIcon icon) {
        Image img = icon.getImage();
        int width = img.getWidth(null);
        int height = img.getHeight(null);

        // Create a new image with a flipped transformation
        Image flippedImage = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) flippedImage.getGraphics();
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1); // Horizontal flip
        tx.translate(-width, 0); // Shift image back into view
        g2d.drawImage(img, tx, null);
        g2d.dispose();

        return new ImageIcon(flippedImage);
    }

    private void setupAnimationTimer() {
        animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (currentState) {
                    case "idle":
                        currentFrame = (currentFrame + 1) % idle.length;
                        setIcon(idle[currentFrame]); // Set idle frames
                        break;
                    case "attack":
                        currentFrame = (currentFrame + 1) % attack.length;
                        setIcon(attack[currentFrame]); // Set attack frames
                        break;
                    case "die":
                        currentFrame = (currentFrame + 1) % die.length;
                        setIcon(die[currentFrame]); // Set die frames
                        break;
                    case "hurt":
                        currentFrame = (currentFrame + 1) % hurt.length;
                        setIcon(hurt[currentFrame]); // Set hurt frames
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
        this.currentFrame = 0; // Reset frame to start animation from the beginning
    }
}
