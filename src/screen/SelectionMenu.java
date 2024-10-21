package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionMenu extends JPanel {
    private int total = 0;

    public SelectionMenu(SelectionListener listener) {
        // Create a JPanel with a black background

        setBackground(Color.BLACK);
        setLayout(new GridLayout(4, 3, 20, 20));  // 4x3 grid, 20px gaps

        // Add 12 buttons (square boxes) to represent character or location choices
        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton("Option " + i);
            button.setPreferredSize(new Dimension(150, 150));
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 16));

            // Add an ActionListener to each button (for handling selection)
            final int finalI = i; // Create a final variable and assign the value of i to it
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(button.getText() + " selected");
                    total++;
                    // You can add more functionality here, like moving to the battle screen
                    if (listener != null && total == 3) {
                        listener.onSelectionMade(finalI); // Use the final variable instead of i
                    }
                }
            });

            add(button);  // Add the button to the panel
        }
    }
}
