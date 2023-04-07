/*
 * This class shows the search result
 * 
 */

package searchengine;
		
public class SearchResult {
    
    private String filePath;
    private int count;
    
    public SearchResult(String filePath, int count) {
        this.filePath = filePath;
        this.count = count;
    }
    
    // Returns results
    public String toString() {
        return "File: " + filePath + ", Score: " + count;
    }
}
