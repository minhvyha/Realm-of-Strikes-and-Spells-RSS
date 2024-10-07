import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    // Components for the sections
    private JPanel topPanel, bottomPanel;
    private JPanel leftCharacterPanel, rightCharacterPanel;
    private JTextArea gameTextArea;
    private JLabel slimeLabel;
    private Timer movementTimer;
    private int slimeX = 50; // Initial X position of the slime
    private boolean movingRight = true; // Flag for direction
    private Image backgroundImage;

    public Main() {
        // Load background image
        backgroundImage = new ImageIcon(getClass().getResource("/assets/background/battleback1.png")).getImage();

        // Set up the main frame
        setTitle("Masters of MQ RPG");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false); // Make the window non-resizable

        // Main panel with background image
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image that covers the entire frame
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Initialize panels
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
        ImageIcon slimeIcon = new ImageIcon(getClass().getResource("/assets/slime.png"));
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
        mainPanel.add(topPanel, BorderLayout.CENTER); // Top section
        mainPanel.add(bottomPanel, BorderLayout.SOUTH); // Bottom section

        // Adjust the proportions of the top and bottom sections
        bottomPanel.setPreferredSize(new Dimension(800, 200));

        // Add the main panel to the frame
        add(mainPanel);

        // Set up the timer for animation (move the slime every 1000 ms)
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
            }
        });
        movementTimer.start(); // Start the timer

        // Show the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
