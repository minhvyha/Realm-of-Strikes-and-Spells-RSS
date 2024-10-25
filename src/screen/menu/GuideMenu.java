package screen.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;

import screen.SelectionListener;

public class GuideMenu extends JPanel {
    private JLabel titleLabel = new JLabel("Guide", SwingConstants.CENTER);

    public GuideMenu(SelectionListener listener) {
        // Set the layout to BorderLayout to support NORTH, CENTER, etc.
        setLayout(new BorderLayout());

        // Set title label properties
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Title font and size
        titleLabel.setForeground(Color.WHITE); // Title color
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK); // Background color for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        // Add the title label at the top (NORTH)
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel to hold both the text and back button, in BorderLayout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.BLACK);

        // Create a JTextPane to support styled text
        JTextPane guideTextPane = new JTextPane();
        guideTextPane.setEditable(false);
        guideTextPane.setBackground(Color.BLACK);
        guideTextPane.setForeground(Color.WHITE);
        guideTextPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Insert styled text into the JTextPane
        insertStyledText(guideTextPane);

        // Create a JScrollPane to make the text area scrollable
        JScrollPane scrollPane = new JScrollPane(guideTextPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the content panel center
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a button panel with a FlowLayout to center the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 12)); // Smaller font size for a small fit-text button
        backButton.setPreferredSize(new Dimension(100, 30)); // Smaller button size

        backButton.addActionListener(e -> {
            System.out.println("Back button clicked!");
            if (listener != null) {
                listener.onGuideBack(); // Notify listener
            }
        });

        buttonPanel.add(backButton); // Add the back button to the panel

        // Add the button panel at the bottom (SOUTH)
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the content panel (guide + button) to the center
        add(contentPanel, BorderLayout.CENTER);
    }

    // Method to insert styled text into the JTextPane
    private void insertStyledText(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();

        // Create different styles for titles, subtitles, and body text
        Style titleStyle = doc.addStyle("titleStyle", null);
        StyleConstants.setFontSize(titleStyle, 18);
        StyleConstants.setBold(titleStyle, true);
        StyleConstants.setForeground(titleStyle, Color.WHITE);

        Style subTitleStyle = doc.addStyle("subTitleStyle", null);
        StyleConstants.setFontSize(subTitleStyle, 16);
        StyleConstants.setBold(subTitleStyle, true);
        StyleConstants.setForeground(subTitleStyle, Color.WHITE);

        Style bodyStyle = doc.addStyle("bodyStyle", null);
        StyleConstants.setFontSize(bodyStyle, 12);
        StyleConstants.setForeground(bodyStyle, Color.WHITE);

        try {
            // Insert styled text
            doc.insertString(doc.getLength(), "Realm of Strikes and Spells (RSS)\n", titleStyle);
            doc.insertString(doc.getLength(), "\nOverview:\n", subTitleStyle);
            doc.insertString(doc.getLength(), "Realm of Strikes and Spells (RSS) is a turn-based RPG where players control a team of allies to battle against enemies. " +
                "The game revolves around strategic use of character stats, abilities, and dice rolls to defeat all enemies before they defeat your team.\n\n", bodyStyle);

            doc.insertString(doc.getLength(), "Character Stats:\n", subTitleStyle);
            doc.insertString(doc.getLength(), "Each character has five core stats:\n" +
                "- HP (Health Points): How much damage a character can take before being defeated.\n" +
                "- Attack: Base damage a character can deal when attacking.\n" +
                "- Defense: Reduces damage taken from enemy attacks.\n" +
                "- Agility: Determines the order of turns in battle.\n" +
                "- Intelligence: Influences the effectiveness of special abilities.\n\n", bodyStyle);

            doc.insertString(doc.getLength(), "Races:\n", subTitleStyle);
            doc.insertString(doc.getLength(), "Allies:\n", bodyStyle);
            doc.insertString(doc.getLength(), "1. Minotaur - HP: 150, Attack: 20, Defense: 12, Agility: 12, Intelligence: 10\n", bodyStyle);
            doc.insertString(doc.getLength(), "2. Orc - HP: 150, Attack: 20, Defense: 12, Agility: 12, Intelligence: 10\n", bodyStyle);
            doc.insertString(doc.getLength(), "3. Angel - HP: 100, Attack: 10, Defense: 20, Agility: 25, Intelligence: 15\n\n", bodyStyle);

            doc.insertString(doc.getLength(), "Enemies:\n", bodyStyle);
            doc.insertString(doc.getLength(), "1. Golem - HP: 150, Attack: 15, Defense: 20, Agility: 20, Intelligence: 10\n", bodyStyle);
            doc.insertString(doc.getLength(), "2. Reaper - HP: 100, Attack: 20, Defense: 10, Agility: 15, Intelligence: 10\n", bodyStyle);
            doc.insertString(doc.getLength(), "3. Zombie - HP: 80, Attack: 10, Defense: 5, Agility: 10, Intelligence: 5\n\n", bodyStyle);

            doc.insertString(doc.getLength(), "Classes and Special Abilities:\n", subTitleStyle);
            doc.insertString(doc.getLength(), "1. Warrior - Special Ability: Power Strike (150% normal damage).\n", bodyStyle);
            doc.insertString(doc.getLength(), "2. Mage - Special Ability: Fireball (Intelligence x 2 damage, ignores defense).\n", bodyStyle);
            doc.insertString(doc.getLength(), "3. Rogue - Special Ability: Shadow Step (Increases agility by 50% for 3 turns).\n\n", bodyStyle);

            doc.insertString(doc.getLength(), "Combat System:\n", subTitleStyle);
            doc.insertString(doc.getLength(), "Turn order is determined by Agility. Characters can perform three actions: Strike, Defense Stand, or Special Ability.\n" +
                "Dice rolls determine bonus attack and defense values during combat. The game ends when all enemies or allies are defeated.\n\n", bodyStyle);

            doc.insertString(doc.getLength(), "Strategy Tips:\n", subTitleStyle);
            doc.insertString(doc.getLength(), "1. Agility is key for quicker actions.\n" +
                "2. Use Defense Stand to prolong survival.\n" +
                "3. Balance your team with tanks, damage dealers, and support characters.\n" +
                "4. Save special abilities for critical moments.\n\n", bodyStyle);

            doc.insertString(doc.getLength(), "Good luck in the Realm of Strikes and Spells!\n", bodyStyle);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

}
