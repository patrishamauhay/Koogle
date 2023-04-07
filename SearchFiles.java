/*
 * A class for searching all files with .txt extension 
 * in the given directory and its sub-directories
 * 
*/

package searchengine;

import java.io.File;
import java.util.ArrayList;

public class SearchFiles {
	
    public static ArrayList<File> getFilesInSearchSpace(String searchSpace) {
    	
    	// Array list to store found files
        ArrayList<File> fileList = new ArrayList<>();
        
        // File object for search space
        File file = new File(searchSpace);
        
        if (file.isDirectory()) {
            // Loop through all the files in the directory and its sub directories
            File[] files = file.listFiles();
            
            for (File f : files) {
                if (f.isDirectory()) {
                	
                    fileList.addAll(getFilesInSearchSpace(f.getAbsolutePath()));
                    
                } else if (f.isFile() && f.getName().endsWith(".txt")) {
                	
                    fileList.add(f);
                }
            }
            
        } else if (file.isFile() && file.getName().endsWith(".txt")) {
            fileList.add(file);
            
        } else {
            System.out.println("Invalid file or directory path.");
        }
        return fileList;
    }

}
