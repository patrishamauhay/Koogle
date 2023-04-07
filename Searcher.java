import java.util.*;
import java.util.regex.*;

public class Searcher {
    
	//  
    public static ArrayList<SearchResult> search(String[] filePaths, String term){
    	
    	// Array to store search results
        ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
        
        // Loops over each file path
        for (String filePath : filePaths) {
        	
        	// Reads contents of file
            String fileContent = MyFileReader.readFile(filePath);
            
            // Calculates the number of appeared terms
            int count = calculate(fileContent, term);
           
            if (count > 0) {
                searchResults.add(new SearchResult(filePath, count));
            }
        }
        
        searchResults.sort((a, b) -> b.getScore() - a.getScore());
        return searchResults;
    }
    
    
    private static int calculate(String fileContent, String term) {
        int count = 0;
        
        String[] queryWords = term.split("\\s+");
        
        for (String queryWord : queryWords) {
            if (queryWord.endsWith("*")) {
            	
                String regex = queryWord.replace("*", ".*");
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(fileContent);
                
                while (matcher.find()) {
                	count++;
                }
            } else {
                if (fileContent.contains(queryWord)) {
                	count++;
                }
            }
        }
        return count;
    }
}

