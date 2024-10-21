package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class BattleScreen extends JPanel {
    private JPanel topPanel, bottomPanel;
    private JPanel leftCharacterPanel, rightCharacterPanel;
    private JTextArea gameTextArea;
    private JLabel elfLabel;
    private Timer animationTimer;
    private int elfX = 50; // Initial X position of the elf
    private Image backgroundImage;
    private JPanel menuPanel;
    private JButton option1, option2, option3;
    private ImageIcon[] elfFrames, orcFrames; // Array of elf animation frames
    private int currentFrame = 0; // Current animation frame

    private ImageIcon scaleImageHighQuality(ImageIcon icon, int width, int height) {
        Image image = icon.getImage(); // Transform the icon into an Image
    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale the image
    return new ImageIcon(scaledImage); // Return the scaled icon
    }

    public BattleScreen(Image backgroundImage) {
        this.backgroundImage = backgroundImage;

        // Load the animation frames
        loadAnimationFrames();

        // Set up the panel
        setLayout(new BorderLayout());

        // Initialize panels
        initializePanels();

        // Set up the timer for frame animation (change the frame every 200 ms)
        setupAnimationTimer();
    }

    private void loadAnimationFrames() {
        // Load the frames from the resources (from 0 to 9)
        elfFrames = new ImageIcon[] {
            scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_000.png")), 285, 150),
            scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_001.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_002.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_003.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_004.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_005.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_006.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_007.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_008.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_009.png")), 285, 150)

        };
        orcFrames = new ImageIcon[] {
            scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_000.png")), 285, 150),
            scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_001.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_002.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_003.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_004.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_005.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_006.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_007.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_008.png")), 285, 150),
                scaleImageHighQuality(new ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_009.png")), 285, 150)

        };
    }


    private void initializePanels() {
        // Initialize the top panel with character sections
        topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setOpaque(false); // Make the top panel transparent

        // Bottom panel with a dark transparent box
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(0, 0, 0, 150)); // Dark transparent background (RGBA)

        // Initialize character panels
        leftCharacterPanel = new JPanel(null);
        leftCharacterPanel.setOpaque(false); // Transparent left character panel

        rightCharacterPanel = new JPanel();
        rightCharacterPanel.setOpaque(false); // Transparent right character panel
        // Add the slime image to the left character panel
        // slimeLabel = new JLabel();
        ImageIcon slimeIcon = new ImageIcon(getClass().getResource("/assets/character/slime.png"));
        System.out.println(slimeIcon.getIconWidth());
        // slimeLabel.setIcon(slimeIcon);
        // slimeLabel.setBounds(slimeX, 50, slimeIcon.getIconWidth(),
        // ImageIcon slimeIcon = new
        // ImageIcon(getClass().getResource("/assets/elf/2/Elf_02__IDLE_000.png"));

        // slimeIcon.getIconHeight()); // Set initial position
        // leftCharacterPanel.add(slimeLabel);

        // Add the elf image to the left character panel
        elfLabel = new JLabel();
        elfLabel.setIcon(elfFrames[currentFrame]); // Set the initial frame
        elfLabel.setBounds(elfX, 50, elfFrames[currentFrame].getIconWidth(), elfFrames[currentFrame].getIconHeight()); // Set
                                                                                                                       // initial
                                                                                                                       // position
        leftCharacterPanel.add(elfLabel);

        // Add character panels to top panel
        topPanel.add(leftCharacterPanel);
        topPanel.add(rightCharacterPanel);

        // Initialize game text area for the bottom section
        gameTextArea = new JTextArea();
        gameTextArea.setOpaque(false); // Make the text area background transparent
        gameTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameTextArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); // Ensure the scroll pane is transparent
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Add top and bottom panels to the main panel
        add(topPanel, BorderLayout.CENTER); // Top section
        add(bottomPanel, BorderLayout.SOUTH); // Bottom section

        // Adjust the proportions of the bottom section
        bottomPanel.setPreferredSize(new Dimension(800, 200));

        // Selection menu with transparent background
        menuPanel = new JPanel(new GridLayout(3, 1));
        menuPanel.setBackground(new Color(0, 0, 0, 180)); // 70% transparent background (RGBA)

        // Initialize menu buttons
        option1 = new JButton("Attack");
        option2 = new JButton("Defend");
        option3 = new JButton("Run");

        // Customize the buttons
        customizeButton(option1);
        customizeButton(option2);
        customizeButton(option3);

        // Add buttons to the menu panel
        menuPanel.add(option1);
        menuPanel.add(option2);
        menuPanel.add(option3);

        // Set size for the selection menu
        menuPanel.setPreferredSize(new Dimension(200, 200));
        add(menuPanel, BorderLayout.EAST);
    }

    private void setupAnimationTimer() {
        animationTimer = new Timer(120, new ActionListener() { // Change frame every 200 ms
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the current frame
                currentFrame = (currentFrame + 1) % elfFrames.length;
                elfLabel.setIcon(elfFrames[currentFrame]); // Change the elf frame
                repaint();
            }
        });
        animationTimer.start(); // Start the animation timer
    }

    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Square border for selection box
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
