import java.io.*;
import java.util.*;

public class Control {

    public static void main(String[] args){
    	
    	// Asks user to enter search term
        Scanner toSearch = new Scanner(System.in);
        System.out.print("Enter the search terms (separated by commas): ");
        
        // Allows user to input multiple terms separated by commas
        // Splits the string into an array using the comma as the delimiter
        String[] searchTerms = toSearch.nextLine().split(",");
        
        //Asks user to enter file or directory path
        System.out.print("Enter the search space (file path or directory path): ");
        
        // Reads user input
        String searchSpace = toSearch.nextLine();

        
        List<File> fileList = SearchFiles.getFilesInSearchSpace(searchSpace);

        Map<File, Integer> matchCounts = new HashMap<>();
        
        for (File f : fileList) {
            String fileContents = MyFileReader.readFile(f.getAbsolutePath());
            int matchCount = 0;
            for (String searchTerm : searchTerms) {
                if (fileContents.contains(searchTerm.trim())) {
                    matchCount++;
                }
            }
            if (matchCount > 0) {
                matchCounts.put(f, matchCount);
            }
        }

        if (!matchCounts.isEmpty()) {
            matchCounts.entrySet().stream()
                    .sorted(Map.Entry.<File, Integer>comparingByValue().reversed())
                    .forEach(entry -> {
                        System.out.println("Found " + entry.getValue() + " times in file: " + entry.getKey().getAbsolutePath());
                    });
        } else {
            System.out.println("No txt files found containing the search terms.");
        }
    }



}
