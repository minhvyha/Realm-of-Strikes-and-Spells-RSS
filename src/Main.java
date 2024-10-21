import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import screen.SelectionMenu;
import screen.SelectionListener;

public class Main extends JFrame implements SelectionListener {

    // Components for the sections
    private JPanel mainPanel;
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
    private SelectionMenu selectionMenu;
    
    private boolean start = false; // Flag to start the game
    private int map = -1; // Map selection


    @Override
    public void onSelectionMade(int map) {
        System.out.println("Map " + map + " selected");
        this.map = map;
        // Implement the method logic here
        setStart(true);
    }

    public void setStart(boolean value) {
        this.start = value;
        System.out.println("Start is now: " + start);

        // If start is set to true, remove the selection menu and add game components
        if (start) {
            updateGameScreen();
        }
    }

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
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!start) {
                    // If start is false, fill the screen with black
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    // Otherwise, draw the background image
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Add the main panel to the frame
        add(mainPanel);

        // Add the selection menu when start is false
        if (!start) {
            selectionMenu = new SelectionMenu(this);
            mainPanel.add(selectionMenu, BorderLayout.CENTER);
        }

        // Show the frame
        setVisible(true);
    }

    private void updateGameScreen() {
        // Remove the selection menu from the main panel
        mainPanel.remove(selectionMenu);

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
        mainPanel.add(topPanel, BorderLayout.CENTER); // Top section
        mainPanel.add(bottomPanel, BorderLayout.SOUTH); // Bottom section

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
        mainPanel.add(menuPanel, BorderLayout.EAST);

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

        // Refresh the frame
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Square border for selection box
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
