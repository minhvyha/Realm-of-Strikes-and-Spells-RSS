package screen.menu;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import screen.SelectionListener;

public class GuideMenu extends JPanel {

        public GuideMenu(SelectionListener listener) {
                setLayout(new BorderLayout());
                setBackground(Color.WHITE);

                JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
                navbar.setBackground(Color.DARK_GRAY);

                String[] navItems = { "Home", "Map", "Characters", "Battle Log Reader", "Exit" };
                for (String item : navItems) {
                        JButton navButton = new JButton(item);
                        navButton.setForeground(Color.WHITE);
                        navButton.setBackground(Color.DARK_GRAY);
                        navButton.setBorderPainted(false);
                        navButton.setFocusPainted(false);
                        navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        navbar.add(navButton);

                        // Capture the current item in a final variable for the action listener
                        final String currentItem = item;

                        // Adding an action for each navigation item
                        navButton.addActionListener(e -> {
                                switch (currentItem) {
                                        case "Home":
                                                listener.onGuideBack();
                                                break;
                                        case "Map":
                                                listener.onMenuMapSelected();
                                                break;
                                        case "Characters":
                                                listener.onMenuCharacterSelected();
                                                break;
                                        case "Battle Log Reader":
                                                listener.onMenuBattleLogReaderSelected();
                                        case "Exit":
                                                ImageIcon originalIcon = new ImageIcon(
                                                                getClass().getResource("/assets/logo.png"));

                                                // Scale the image to 50x50 pixels
                                                Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50,
                                                                Image.SCALE_SMOOTH);
                                                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                                                int confirm = JOptionPane.showOptionDialog(
                                                                null, // Parent component
                                                                "Are you sure you want to quit?", // Message
                                                                "Quit Confirmation", // Title
                                                                JOptionPane.YES_NO_OPTION, // Option type (Yes/No)
                                                                JOptionPane.QUESTION_MESSAGE, // Message type
                                                                scaledIcon, // Custom icon
                                                                null, // No custom button options
                                                                null); // Default button option
                                                if (confirm == JOptionPane.YES_OPTION) {
                                                        System.exit(0); // Exit the application if confirmed
                                                }
                                                break;
                                }
                        });
                }
                add(navbar, BorderLayout.NORTH);// Add the navbar to the top of the menu

                // Create a JTextArea for a brief description of the game
                JTextArea sideDescription = new JTextArea(
                                "Realm of Strikes and Spells (RSS) is a turn-based RPG where strategy and stats determine victory.");
                sideDescription.setWrapStyleWord(true);
                sideDescription.setLineWrap(true);
                sideDescription.setEditable(false);
                sideDescription.setBackground(new Color(241, 241, 241));

                // Create a JTextPane for styled content text
                JTextPane contentTextPane = new JTextPane();
                contentTextPane.setEditable(false);
                contentTextPane.setBackground(Color.WHITE);
                contentTextPane.setBorder(new EmptyBorder(20, 20, 20, 20));
                // Insert styled text into the content text pane
                insertStyledText(contentTextPane);

                // Create a scroll pane to contain the text pane
                JScrollPane scrollPane = new JScrollPane(contentTextPane);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                // Create a footer with copyright information
                JPanel footer = new JPanel();
                footer.setBackground(Color.LIGHT_GRAY);
                footer.add(new JLabel("Realm of Strikes and Spells © 2024"));
                add(footer, BorderLayout.SOUTH); // Add the footer to the bottom of the menu

                add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the men
        }

        private void insertStyledText(JTextPane textPane) {
                StyledDocument doc = textPane.getStyledDocument();

                Style titleStyle = doc.addStyle("titleStyle", null);
                StyleConstants.setFontSize(titleStyle, 24);
                StyleConstants.setBold(titleStyle, true);

                Style sectionTitleStyle = doc.addStyle("sectionTitleStyle", null);
                StyleConstants.setFontSize(sectionTitleStyle, 18);
                StyleConstants.setBold(sectionTitleStyle, true);

                Style bodyStyle = doc.addStyle("bodyStyle", null);
                StyleConstants.setFontSize(bodyStyle, 12);
                StyleConstants.setForeground(bodyStyle, Color.BLACK);

                try {
                        doc.insertString(doc.getLength(), "Game Overview\n", sectionTitleStyle);
                        doc.insertString(doc.getLength(),
                                        "Realm of Strikes and Spells (RSS) is a turn-based RPG where players control a team of allies to battle against enemies. Use character stats, abilities, and dice rolls to gain an advantage!\n\n",
                                        bodyStyle);

                        doc.insertString(doc.getLength(), "Character Stats\n", sectionTitleStyle);
                        doc.insertString(doc.getLength(), "Each character has five core stats:\n" +
                                        "- HP (Health Points) The amount of damage a character can withstand before being defeated.\n"
                                        +
                                        "- Attack The base damage a character inflicts when attacking.\n" +
                                        "- Defense Reduces damage taken from enemy attacks.\n" +
                                        "- Agility Determines the turn order in battle.\n" +
                                        "- Intelligence Influences the potency of special abilities.\n\n", bodyStyle);

                        doc.insertString(doc.getLength(), "Races\n", sectionTitleStyle);
                        doc.insertString(doc.getLength(), "Allies:\n", bodyStyle);
                        doc.insertString(doc.getLength(),
                                        "- Minotaur HP 160 Attack 18 Defense 15 Agility 10 Intelligence 8\n",
                                        bodyStyle);
                        doc.insertString(doc.getLength(),
                                        "- Orc HP 170 Attack 22 Defense 12 Agility 10 Intelligence 7\n",
                                        bodyStyle);
                        doc.insertString(doc.getLength(),
                                        "- Angel HP 120 Attack 12 Defense 18 Agility 25 Intelligence 20\n\n",
                                        bodyStyle);

                        doc.insertString(doc.getLength(), "Enemies:\n", bodyStyle);
                        doc.insertString(doc.getLength(),
                                        "- Golem HP 200 Attack 16 Defense 25 Agility 8 Intelligence 6\n",
                                        bodyStyle);
                        doc.insertString(doc.getLength(),
                                        "- Reaper HP 110 Attack 20 Defense 10 Agility 18 Intelligence 12\n",
                                        bodyStyle);
                        doc.insertString(doc.getLength(),
                                        "- Zombie HP 120 Attack 12 Defense 8 Agility 6 Intelligence 5\n\n",
                                        bodyStyle);

                        doc.insertString(doc.getLength(), "Classes and Special Abilities\n", sectionTitleStyle);
                        doc.insertString(doc.getLength(), "- Warrior Power Strike 150% attack damage.\n", bodyStyle);
                        doc.insertString(doc.getLength(),
                                        "- Mage Fireball Deals Intelligence x 2 damage and bypasses defense.\n",
                                        bodyStyle);
                        doc.insertString(doc.getLength(), "- Rogue Shadow Step Boosts agility by 50% for 3 turns.\n\n",
                                        bodyStyle);

                        doc.insertString(doc.getLength(), "Combat System\n", sectionTitleStyle);
                        doc.insertString(doc.getLength(),
                                        "Turns are determined by agility. Players can choose to Strike, Defend, or use a Special Ability. Dice rolls impact attack and defense.\n\n",
                                        bodyStyle);

                        doc.insertString(doc.getLength(), "Strategy Tips\n", sectionTitleStyle);
                        doc.insertString(doc.getLength(), "- High agility enables quicker actions.\n", bodyStyle);
                        doc.insertString(doc.getLength(), "- Use Defense Stand for durability.\n", bodyStyle);
                        doc.insertString(doc.getLength(),
                                        "- Build strong synergies between characters to maximize effectiveness.\n",
                                        bodyStyle);
                } catch (BadLocationException e) {
                        e.printStackTrace();
                }
        }
}
