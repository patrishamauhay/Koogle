/*
* GUI Search Bar
*/

package searchengine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SearchBar extends JFrame implements ActionListener {

    private JTextArea output;
    private JTextField searchField, pathField;
    private JButton searchButton;

    public SearchBar() {

        // Create title label
        JLabel koogleLabel = new JLabel("Koogle");
        koogleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        koogleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create search fields and labels
        JLabel searchLabel = new JLabel("Search Query:");
        JLabel pathLabel = new JLabel("Search Path:");
        searchField = new JTextField(20);
        pathField = new JTextField(20);

        // Create search button
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        // Create output text area
        output = new JTextArea(10, 20);
        output.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(output);

        // Add components to panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

     // Create clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
            }
        });

        // Add clear button to panel
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(clearButton, c);

        // Add search button to panel
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(searchButton, c);

        
        // Add Koogle label to panel
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(koogleLabel, c);

        // Add search query label and text field to panel
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(searchLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(searchField, c);

        // Add search path label and text field to panel
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(pathLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(pathField, c);

        // Add search button to panel
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(searchButton, c);

        // Add scrollable output text area to panel
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(scrollPane, c);

        // Add panel to frame
        add(panel);

        // Set frame properties
        setTitle("Koogle");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchQuery = searchField.getText();
            String searchPath = pathField.getText();

           ArrayList<File> fileList = SearchFiles.getFilesInSearchSpace(searchPath);

            Map<File, Integer> matchCounts = new HashMap<>();

            for (File f : fileList) {
                String fileContents = MyFileReader.readFile(f.getAbsolutePath());
                int matchCount = 0;
                for (String searchTerm : searchQuery.split(",")) {
                    if (fileContents.contains(searchTerm.trim())) {
                        matchCount++;
                    }
                }
                if (matchCount > 0) {
                    matchCounts.put(f, matchCount);
                }
            }

            if (!matchCounts.isEmpty()) {
                StringBuilder resultBuilder = new StringBuilder();
                matchCounts.entrySet().stream().sorted(Map.Entry.<File, Integer>comparingByValue().reversed())
                        .forEach(entry -> {
                            resultBuilder.append("Found " + entry.getValue() + " times in file: " + entry.getKey().getAbsolutePath() + "\n");
                        });
                output.setText(resultBuilder.toString());
            } else {
                output.setText("No txt files found containing the search terms.");
            }
        }
    }

}
