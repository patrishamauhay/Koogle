/*
 * This class calculates the amount of 
 * times the searched term appears
 */

import java.util.*;

public class Searcher {
    
    public static ArrayList<SearchResult> search(String[] filePaths, String term){
    	
    	// Array to store search results
        ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
        
        // Loops over each file path
        for (String filePath : filePaths) {
        	
        	// Reads contents of file
            String fileContent = MyFileReader.readFile(filePath);
            
            // Calculates the number of appeared terms
            int count = calculate(fileContent, term);
            
            // Adds object to list if term appears more than once
            if (count > 0) {
                searchResults.add(new SearchResult(filePath, count));
            }
        }
        
        // Sorts results in decreasing order by score of each result
        Collections.sort(searchResults, Comparator.comparing(SearchResult::getScore).reversed());
        return searchResults;
    }
    
    // Method that calculates score
    private static int calculate(String fileContent, String term) {
    	
        int count = 0;
        
        //Splits searched terms into separate terms
        String[] queryWords = term.split("\\s+");
        
        // Goes over each search term
        for (String queryWord : queryWords) {
        	
        	// If query word ends with "*", remove it and match beginning of word
            if (queryWord.endsWith("*")) {
                
                String wordPrefix = queryWord.substring(0, queryWord.length() - 1);
                
                if (fileContent.indexOf(wordPrefix) == 0) {
                    count++;
                }
            } else {
            	
                // Check if the word is present in the file content
                if (fileContent.contains(queryWord)) {
                    count++;
                }
            }
        }
        
        return count;
    }

   }
