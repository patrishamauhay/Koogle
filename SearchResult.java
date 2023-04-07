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
	
    public String getFilePath() {
        return filePath;
    }

    public int getScore() {
        return count;
    }
	
    // Returns results
    public String toString() {
        return "File: " + filePath + ", Score: " + count;
    }
}
