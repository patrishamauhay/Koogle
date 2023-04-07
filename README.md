# Assignment - OOP
Patrisha Mauhay - C21339643
TU857-2

*This program is a Search Engine tool that allows you to search for a term across a set of text files.
When the program is run, a GUI titled "Koogle" will display. This will ask the user to enter their search query and to enter the search path to where they want to search.*

This repository contains:
- README file
- Classes 
- textfile folder (includes unique data to search for a term)



*List of classes

Control
  - Object from the class SearchBar is created to initialise the program.

SearchBar.java
  - This contains the code for the GUI. Which allows the user to input the Search Query and the Search Path, it includes a button to Search and when clicked the output is shown in the text field below. There is also a "Clear" button that clears the ouput when pressed.
  - This class reads through the contents of a file and calculates the number of appeared search queries.

MyFileReader.java
  - This class takes the file path as an input, reads the contents of the file and then returns the contents.

SearchResult.java
  - This class shows the search result, this includes a toString method.

SearchFiles.java
  - A class for searching all files with .txt extension in the given directory and its sub-directories.



*Some features that it includes are:
  - The tool will check the contents of a set of text files and will show the ones that contain the search term.
  - The user is able to search for multiple words (separated by commas)
  - The user is able to pick the search space eg directory, file etc.
  - There is a ranking mechanism, the strongest match is returned first.
  - Spelling correction where it can correct wrong spelling of search terms
  - There is a "Clear" button that clears the output when clicked.



If i had more time to do the assignment I would include a feature that shows suggestions to the user of what to search for. The program will get these suggestions from a collection of most common past searches from the user.
