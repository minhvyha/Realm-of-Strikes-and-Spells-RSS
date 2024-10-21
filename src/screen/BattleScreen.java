package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleScreen extends JPanel {
    private JPanel topPanel, bottomPanel;
    private JPanel leftCharacterPanel, rightCharacterPanel;
    private JTextArea gameTextArea;
    private JLabel slimeLabel;
    private Timer movementTimer;
    private int slimeX = 50; // Initial X position of the slime
    private boolean movingRight = true; // Flag for direction
    private Image backgroundImage;
    private JPanel menuPanel;
    private JButton option1, option2, option3;

    public BattleScreen(Image backgroundImage) {
        this.backgroundImage = backgroundImage;

        // Set up the panel
        setLayout(new BorderLayout());

        // Initialize panels
        initializePanels();

        // Set up the timer for animation (move the slime every 1000 ms)
        setupMovementTimer();
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
        slimeLabel = new JLabel();
        ImageIcon slimeIcon = new ImageIcon(getClass().getResource("/assets/character/slime.png"));
        slimeLabel.setIcon(slimeIcon);
        slimeLabel.setBounds(slimeX, 50, slimeIcon.getIconWidth(), slimeIcon.getIconHeight()); // Set initial position
        leftCharacterPanel.add(slimeLabel);

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

    private void setupMovementTimer() {
        movementTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check direction and move accordingly
                if (movingRight) {
                    slimeX += 10; // Move right
                    if (slimeX > 100) { // If it reaches the right limit, change direction
                        movingRight = false;
                    }
                } else {
                    slimeX -= 10; // Move left
                    if (slimeX < 50) { // If it reaches the left limit, change direction
                        movingRight = true;
                    }
                }
                // Update slime position
                slimeLabel.setLocation(slimeX, 50);
                repaint(); // Redraw the panel to update the slime's position
            }
        });
        movementTimer.start(); // Start the timer
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
