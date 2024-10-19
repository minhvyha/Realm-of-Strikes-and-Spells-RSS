package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionMenu extends JPanel {
    private SelectionListener listener;

    public SelectionMenu(SelectionListener listener) {
        // Create a JPanel with a black background
        this.listener = listener;

        setBackground(Color.BLACK);
        setLayout(new GridLayout(4, 3, 20, 20));  // 4x3 grid, 20px gaps

        // Add 12 buttons (square boxes) to represent character or location choices
        for (int i = 1; i <= 12; i++) {
            JButton button = new JButton("Option " + i);
            button.setPreferredSize(new Dimension(150, 150));
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 16));

            // Add an ActionListener to each button (for handling selection)
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(button.getText() + " selected");
                    // You can add more functionality here, like moving to the battle screen
                    if (listener != null) {
                        listener.onSelectionMade();
                    }
                }
            });

            add(button);  // Add the button to the panel
        }
    }
}
