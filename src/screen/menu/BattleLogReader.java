package screen.menu;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.io.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import screen.SelectionListener;

public class BattleLogReader extends JPanel {

    private JTable table;

    public BattleLogReader(SelectionListener listener) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Navbar
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navbar.setBackground(Color.DARK_GRAY);

        String[] navItems = { "Home", "Open File", "Exit" };
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setForeground(Color.WHITE);
            navButton.setBackground(Color.DARK_GRAY);
            navButton.setBorderPainted(false);
            navButton.setFocusPainted(false);
            navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            navbar.add(navButton);

            navButton.addActionListener(e -> {
                if ("Exit".equals(item)) {
                    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/assets/logo.png"));

                    // Scale the image to 50x50 pixels
                    Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
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
                        System.exit(0);// Exit the application if confirmed
                    }
                } else if ("Open File".equals(item)) {
                    openFileChooser();
                } else {
                    listener.onBattleLogReaderBack();
                }
            });
        }
        add(navbar, BorderLayout.NORTH);

        // Drag and Drop or File Chooser Area
        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout());
        filePanel.setBackground(new Color(241, 241, 241));

        JLabel dropLabel = new JLabel("Drag and drop a CSV file here or use 'Open File' button", SwingConstants.CENTER);
        dropLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        filePanel.add(dropLabel, BorderLayout.CENTER);

        new DropTarget(filePanel, new DropTargetListener() {
            public void drop(DropTargetDropEvent dtde) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                try {
                    @SuppressWarnings("unchecked")
                    List<File> droppedFiles = (List<File>) dtde.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        if (file.getName().endsWith(".csv")) {
                            readCSVFile(file);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please drop a valid CSV file.");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // // Empty implementations for unused methods of DropTargetListener
            public void dragEnter(DropTargetDragEvent dtde) {
            }

            public void dragExit(DropTargetEvent dte) {
            }

            public void dragOver(DropTargetDragEvent dtde) {
            }

            public void dropActionChanged(DropTargetDragEvent dtde) {
            }
        });

        add(filePanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(Color.LIGHT_GRAY);
        footer.add(new JLabel("Realm of Strikes and Spells © 2024"));
        add(footer, BorderLayout.SOUTH);
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            readCSVFile(file);
        }
    }
    // // Method to read and display data from the selected CSV file
    private void readCSVFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            DefaultTableModel tableModel = new DefaultTableModel();
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (isHeader) {
                    for (String header : data) {
                        tableModel.addColumn(header.trim());
                    }
                    isHeader = false;// false after header is processed
                } else {
                    tableModel.addRow(data);//// Add data rows to the table model
                }
            }

            // If table is not initialized, create a new table
            if (table == null) {
                table = new JTable(tableModel); // Create the table with the data model
                JScrollPane scrollPane = new JScrollPane(table); // Add table to scroll pane
                add(scrollPane, BorderLayout.CENTER); // Add scroll pane to center
            } else {
                table.setModel(tableModel); // Update existing table with new data model
            }
            revalidate();
            repaint();//reflect changes

        }catch (IOException e) {
            // Show error message if file reading fails
            JOptionPane.showMessageDialog(this, "Failed to read file: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}