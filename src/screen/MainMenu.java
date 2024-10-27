package screen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu extends JPanel {
    public MainMenu(Listener listener) {
        setLayout(new BorderLayout()); // Use BorderLayout for the main panel

        // Create a title label
        JLabel titleLabel = new JLabel("Realm of Strikes and Spells", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE); // Title color

        // Add padding using EmptyBorder (top, left, bottom, right)
        titleLabel.setBorder(BorderFactory.createEmptyBorder(140, 10, 0, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel for the menu buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Make the button panel transparent

        // Add some spacing at the top of the button panel
        buttonPanel.add(Box.createVerticalStrut(50));

        // Create and configure buttons
        JButton playButton = createMenuButton("Play", 95, 95);
        JButton chooseAlliesButton = createMenuButton("Choose Allies", 40, 40);
        JButton chooseMapButton = createMenuButton("Choose Map", 48, 48);
        JButton guideButton = createMenuButton("Guide", 85, 85);
        JButton battleLogReaderButton = createMenuButton("Battle Log Reader", 17, 17);
        JButton quitButton = createMenuButton("Quit", 95, 95); // New Quit button

        // Add buttons to the button panel with spacing
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // Add space between buttons
        buttonPanel.add(chooseAlliesButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // Add space between buttons
        buttonPanel.add(chooseMapButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // Add space between buttons
        buttonPanel.add(guideButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // Add space between buttons
        buttonPanel.add(battleLogReaderButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // Add space between buttons
        buttonPanel.add(quitButton);

        // Add some spacing at the bottom of the button panel
        buttonPanel.add(Box.createVerticalStrut(50));

        // Center the button panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false); // Transparent background
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(Box.createVerticalGlue());

        // Add the button panel to the center panel as wrapper
        centerPanel.add(buttonPanel);

        // Add the center panel to the main panel
        add(centerPanel, BorderLayout.CENTER);

        // Add action listeners to the buttons
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.onMenuPlaySelected(); // call the listener method for playing
            }
        });

        chooseAlliesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.onMenuCharacterSelected(); // call the listener method for choosing allies
            }
        });

        guideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.onMenuGuideSelected(); // call the listener method for the guide
            }
        });

        chooseMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.onMenuMapSelected(); // call the listener method for choosing a map
            }
        });

        battleLogReaderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listener.onMenuBattleLogReaderSelected(); // call the listener method for the battle log reader
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Load the custom icon
                ImageIcon originalIcon = new ImageIcon(getClass().getResource("/assets/logo.png"));

                // Scale the image to 50x50 pixels
                Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                // Use showOptionDialog to display a more customized dialog with an icon
                int confirm = JOptionPane.showOptionDialog(
                        null, // Parent component
                        "Are you sure you want to quit?", // Message
                        "Quit Confirmation", // Title
                        JOptionPane.YES_NO_OPTION, // Option type (Yes/No)
                        JOptionPane.QUESTION_MESSAGE, // Message type
                        scaledIcon, // Custom icon
                        null, // No custom button options
                        null); // Default button option

                // If the user clicks "Yes", exit the game
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); // Exit the game
                }
            }
        });

    }

    // Create a custom button with rounded corners
    private JButton createMenuButton(String text, int paddingLeft, int paddingRight) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set the button background with rounded corners
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // 30 is the radius for rounded corners

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the border with rounded corners
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // 30 is the radius for rounded corners

                g2.dispose();
            }

            @Override
            public boolean isContentAreaFilled() {
                return false; // Prevents the default background from being painted
            }
        };

        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.BLACK); // Set text color to black
        button.setBackground(Color.WHITE); // Set background color to white
        button.setFocusPainted(false); // Remove focus border
        button.setPreferredSize(new Dimension(200, 50)); // Set a preferred size for the buttons
        button.setOpaque(false); // Make sure it's transparent to show the custom background
        button.setBorder(BorderFactory.createEmptyBorder(10, paddingLeft, 10, paddingRight)); // Add padding
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add mouse listener to change cursor on hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set hand cursor on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setCursor(Cursor.getDefaultCursor()); // Set back to default cursor when not hovering
            }
        });
        return button;
    }

    // Override the paintComponent method to draw the background image and darken it
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image if it exists
        Image img = new ImageIcon(getClass().getResource("/assets/RSS.png")).getImage();
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        } else {
            System.out.println("Image not found.");
        }

        // Create a dark overlay (semi-transparent black)
        Color darkOverlay = new Color(0, 0, 0, 180); // 150 is the transparency value
        g.setColor(darkOverlay);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
