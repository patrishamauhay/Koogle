/*
 * This class takes the file path as an input,
 * and reads the contents of the file
 * and then returns the contents.
 */

package searchengine;
 
import java.io.*;
import java.util.*;

public class MyFileReader {

    public static List<String> readFile(String filePath) {
    	
    	// Creates object from the file path
        File file = new File(filePath);
        
        // Object to read file
        Scanner reader;
        
        // List to store file contents
	List<String> fileContents = new ArrayList<>();
        
	try {
		reader = new Scanner(file);
			
		// Loops through each line of file
		while (reader.hasNextLine()) {
			fileContents.addAll(Arrays.asList(reader.nextLine().split("\\W+")));
		}	

		// Close scanner object
	        reader.close();
			
	    // If file is not found, shows error message
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Something wrong has happened");
		}
       
		// Returns file contents string
        return fileContents;
    }
}
