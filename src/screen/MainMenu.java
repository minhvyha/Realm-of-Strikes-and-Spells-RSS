package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

  public MainMenu(SelectionListener listener) {

    // Set layout and properties for the panel
    setLayout(new BorderLayout());

    // Create and configure the title label
    JLabel titleLabel = new JLabel("Realm of Strikes and Spells", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
    titleLabel.setForeground(Color.WHITE); // Title color

    // Add padding using EmptyBorder (top, left, bottom, right)
    titleLabel.setBorder(BorderFactory.createEmptyBorder(140, 10, 0, 10)); // Add 20px padding top/bottom, 10px
                                                                           // left/right

    add(titleLabel, BorderLayout.NORTH);

    // Create a panel for the buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setOpaque(false); // Make the button panel transparent

    // Add some spacing at the top of the button panel
    buttonPanel.add(Box.createVerticalStrut(50));

    // Create and configure buttons
    JButton playButton = createMenuButton("Play");
    JButton chooseAlliesButton = createMenuButton("Choose Allies");
    JButton chooseMapButton = createMenuButton("Choose Map");
    JButton guideButton = createMenuButton("Guide");
    JButton quitButton = createMenuButton("Quit"); // New Quit button

    playButton.setBorder(BorderFactory.createEmptyBorder(10, 95, 10, 95)); 
    chooseAlliesButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
    chooseMapButton.setBorder(BorderFactory.createEmptyBorder(10, 48, 10, 48));
    guideButton.setBorder(BorderFactory.createEmptyBorder(10, 85, 10, 85));
    quitButton.setBorder(BorderFactory.createEmptyBorder(10, 95, 10, 95));
    // Center-align the buttons and add some spacing between them
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    chooseAlliesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    chooseMapButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    guideButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Add buttons to the button panel with spacing
    buttonPanel.add(playButton);
    buttonPanel.add(Box.createVerticalStrut(15)); // Add space between buttons
    buttonPanel.add(chooseAlliesButton);
    buttonPanel.add(Box.createVerticalStrut(15)); // Add space between buttons
    buttonPanel.add(chooseMapButton);
    buttonPanel.add(Box.createVerticalStrut(15)); // Add space between buttons
    buttonPanel.add(guideButton);
    buttonPanel.add(Box.createVerticalStrut(15)); // Add space between buttons
    buttonPanel.add(quitButton);


    // Add some spacing at the bottom of the button panel
    buttonPanel.add(Box.createVerticalStrut(50));

    // Center the button panel
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setOpaque(false); // Transparent background
    centerPanel.add(Box.createVerticalGlue()); // Pushes the buttons to the vertical center
    centerPanel.add(buttonPanel);
    centerPanel.add(Box.createVerticalGlue()); // Pushes the buttons to the vertical center

    add(centerPanel, BorderLayout.CENTER);

    // Add action listeners to buttons (optional)
    playButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Play button pressed");
        listener.onMenuPlaySelected();
      }
    });

    chooseAlliesButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Choose Allies button pressed");
        listener.onMenuCharacterSelected();

      }
    });
    guideButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Guide button pressed");
        listener.onMenuGuideSelected();

      }
    });
    chooseMapButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Choose Map button pressed");
        listener.onMenuMapSelected();

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
                  null,                                      // Parent component
                  "Are you sure you want to quit?",          // Message
                  "Quit Confirmation",                       // Title
                  JOptionPane.YES_NO_OPTION,                 // Option type (Yes/No)
                  JOptionPane.QUESTION_MESSAGE,              // Message type
                  scaledIcon,                                      // Custom icon
                  null,                                      // No custom button options
                  null);                                     // Default button option

          // If the user clicks "Yes", exit the game
          if (confirm == JOptionPane.YES_OPTION) {
              System.exit(0); // Exit the game
          }
      }
  });

  }

  private JButton createMenuButton(String text) {
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

  // Override the paintComponent method to draw the background and darken it
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
