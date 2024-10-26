package screen.menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;

import screen.SelectionListener;

public class GuideMenu extends JPanel {
    private JLabel titleLabel = new JLabel("Guide", SwingConstants.CENTER);

    public GuideMenu(SelectionListener listener) {
        // Set up the main layout with a BorderLayout to allow positioning of components
        setLayout(new BorderLayout());

        // Configure the title label with font, color, and alignment properties
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLACK); // Title background
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        // Place the title label at the top (NORTH)
        add(titleLabel, BorderLayout.NORTH);

        // Panel to hold guide text and back button in a BorderLayout for easy placement
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.BLACK);

        // Set up a JTextPane for displaying styled guide text content
        JTextPane guideTextPane = new JTextPane();
        guideTextPane.setEditable(false);
        guideTextPane.setBackground(Color.BLACK);
        guideTextPane.setForeground(Color.WHITE);
        guideTextPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Insert the guide content with styles applied
        insertStyledText(guideTextPane);

        // Make the JTextPane scrollable with a JScrollPane
        JScrollPane scrollPane = new JScrollPane(guideTextPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scrollable text pane to the center of the content panel
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel for the "Back" button, centered with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);

        // Configure the back button with font, size, and action
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setPreferredSize(new Dimension(100, 30)); // Set the button size

        backButton.addActionListener(e -> {
            System.out.println("Back button clicked!");
            if (listener != null) {
                listener.onGuideBack(); // Notify listener when back is pressed
            }
        });

        // Add the back button to the button panel
        buttonPanel.add(backButton);

        // Add the button panel to the bottom of the content panel
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Place the content panel containing guide and button in the center
        add(contentPanel, BorderLayout.CENTER);
    }

    // Method to insert styled text into the JTextPane for displaying the guide
    private void insertStyledText(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();

        // Define styles for the guide text (title, subtitles, body)
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
            // Populate the JTextPane with the game's guide content, applying the styles
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
