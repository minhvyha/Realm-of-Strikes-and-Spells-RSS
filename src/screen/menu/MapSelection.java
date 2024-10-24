package screen.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import screen.SelectionListener;

import java.awt.*;
import java.net.URL;

public class MapSelection extends JPanel {
  private JLabel titleLabel = new JLabel("Select Your Allies", SwingConstants.CENTER);
  private JPanel buttonPanel = new JPanel();
  private String[] battleMapNames = {
    "Enchanted Forest",
    "Frozen Tundra",
    "Desert Dunes",
    "Desert Oasis",
    "Cavern Depths",
    "Autumn Woods",
    "Mystic Grove",
    "Dungeon Chambers",
    "Rocky Plateau"
};

  public MapSelection(SelectionListener listener) {
    setLayout(new BorderLayout());

    titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Title font and size
    titleLabel.setForeground(Color.WHITE); // Title color
    titleLabel.setOpaque(true);
    titleLabel.setBackground(Color.BLACK); // Background color for the title
    titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

    // Add the title label at the top (NORTH)
    add(titleLabel, BorderLayout.NORTH);
    buttonPanel.setLayout(new GridLayout(3, 3, 20, 20));
    buttonPanel.setBackground(Color.BLACK);
    int paddingSize = 20;
    buttonPanel.setBorder(new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize)); // Add padding
    Dimension buttonSize = new Dimension(240, 200); // Define button size

    // Add 9 buttons for map choices
    for (int i = 1; i <= 9; i++) {
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setPreferredSize(buttonSize);

      JButton button = new JButton();
      button.setFocusPainted(false);
      button.setBorderPainted(false);
      button.setBounds(0, 0, buttonSize.width, buttonSize.height);
      button.setHorizontalAlignment(SwingConstants.CENTER);

      titleLabel.setText("Select Your Battle Map");

      // Load image and scale it
      String imagePath = "/assets/background/battleback" + i + ".png";
      // Assuming images are correctly loaded here
      URL resource = getClass().getResource(imagePath);

      if (resource != null) {
        ImageIcon originalIcon = new ImageIcon(resource);
        Image scaledImage = originalIcon.getImage().getScaledInstance(buttonSize.width, buttonSize.height,
            Image.SCALE_SMOOTH);

        button.setIcon(new ImageIcon(scaledImage));
      } else {
        button.setText("New Option " + i);
        button.setBackground(Color.WHITE);
      }

      JPanel overlayPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D) g;
          g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // Transparency
          g2d.setColor(Color.BLACK);
          g2d.fillRect(0, 0, getWidth(), getHeight());
        }
      };
      overlayPanel.setBounds(0, 0, buttonSize.width, buttonSize.height);
      overlayPanel.setOpaque(false);
      // Text label
      JLabel textLabel = new JLabel(battleMapNames[i - 1], SwingConstants.CENTER);
      textLabel.setForeground(Color.WHITE);
      textLabel.setFont(new Font("Arial", Font.BOLD, 18));
      textLabel.setBounds(0, 0, buttonSize.width, buttonSize.height);

      // Add components to the layered pane
      layeredPane.add(button, Integer.valueOf(0));
      layeredPane.add(overlayPanel, Integer.valueOf(1));

      layeredPane.add(textLabel, Integer.valueOf(2));

      // Add the layered pane to the panel
      buttonPanel.add(layeredPane);

      // ActionListener for button
      final int finalI = i;
      button.addActionListener(e -> {
        System.out.println("New Option " + finalI + " selected");
        if (listener != null) {
          listener.onMapSelected(finalI); // Notify listener
        }
      });
    }
    buttonPanel.setBorder(new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize)); // Add padding

    // Add the button panel to the CENTER
    add(buttonPanel, BorderLayout.CENTER);
    revalidate();
    repaint();
  }
}
