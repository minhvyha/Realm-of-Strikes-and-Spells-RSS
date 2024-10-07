
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    // Game components
    private JTextArea gameTextArea;
    private JButton attackButton, defendButton;

    public Main() {
        // Setup the frame
        setTitle("Masters of MQ RPG");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize game components
        gameTextArea = new JTextArea();
        gameTextArea.setEditable(false);
        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(gameTextArea);

        attackButton = new JButton("Attack");
        defendButton = new JButton("Defend");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(attackButton);
        buttonPanel.add(defendButton);

        // Add components to frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAttack();
            }
        });

        defendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDefend();
            }
        });

        // Show initial game state
        showInitialState();
    }

    // Method to show the initial state of the game
    private void showInitialState() {
        gameTextArea.setText("Welcome to Masters of MQ!\nThe battle begins now...\n");
    }

    // Method to simulate an attack action
    private void performAttack() {
        gameTextArea.append("You attacked the enemy! The enemy takes damage.\n");
    }

    // Method to simulate a defend action
    private void performDefend() {
        gameTextArea.append("You defended! Your defense increased.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
