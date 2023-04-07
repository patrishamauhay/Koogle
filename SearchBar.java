/*
* GUI Search Bar
*/

package searchengine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the search terms and search space entered by the user
        String searchTermsString = searchField.getText();
        String searchSpace = pathField.getText();

        // Split the search terms into an array using commas as the delimiter
        String[] searchTerms = searchTermsString.split(",");

        // Get the list of files in the search space
        List<File> fileList = SearchFiles.getFilesInSearchSpace(searchSpace);

        Map<File, Integer> matchCounts = new HashMap<>();

        // Loop through each file in the list of files
        for (File f : fileList) {
            // Read the contents of the current file
            List<String> fileContents = MyFileReader.readFile(f.getAbsolutePath());
            int matchCount = 0;
            // Loop through each search term
            for (String searchTerm : searchTerms) {
                // Count the number of times the current search term appears in the file
                matchCount += Collections.frequency(fileContents, searchTerm.trim());
            }
            // If any matches were found, add the file to the map with its match count
            if (matchCount > 0) {
                matchCounts.put(f, matchCount);
            }
        }

        // If any matches were found, sort the map entries by match count and print them out
        if (!matchCounts.isEmpty()) {
            List<Map.Entry<File, Integer>> sortedEntries = new ArrayList<>(matchCounts.entrySet());
            Collections.sort(sortedEntries, new Comparator<Map.Entry<File, Integer>>() {
                @Override
                public int compare(Map.Entry<File, Integer> e1, Map.Entry<File, Integer> e2) {
                    return e2.getValue().compareTo(e1.getValue());
                }
            });
            // Loop through the sorted list of Map.Entry<File, Integer> elements
            // and assign each element to the "entry" variable
            for (Map.Entry<File, Integer> entry : sortedEntries) {
                output.append("Found " + entry.getValue() + " times in file: " + entry.getKey().getAbsolutePath() + "\n");
            }
        } else {
            // If no matches were found, print a message to the user
            output.append("No txt files found containing the search terms.\n");
        }
    }
}
