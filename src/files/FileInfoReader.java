package files;

import java.io.*;


class FileInfoReader {
	
	public FileInfoReader() {
		// TODO Auto-generated constructor stub
	}
		
	/***
	 * opens a file if it exists
	 * @param filePath to file to read
	 * @return buffered reader
	 * @implNote WARNING this method opens a BufferedReader but does not close it. Call closeFile() to close the file.
	 */
	protected BufferedReader readFile(String filePath) {
		
		// create file object from file path
		File inputFile = new File(filePath);
		
		// create file reader & buffered reader
		FileReader fileReader = null;
	    BufferedReader bufferedReader = null;
	    
	    // try/catch opening and reading the file
		try {
			
			// create file reader
			fileReader = new FileReader(inputFile);
			// create buffered reader for memory management
			bufferedReader = new BufferedReader(fileReader);
			
		} catch (FileNotFoundException e) { // fileReader couldn't find file
			
			// customized friendly message
			System.out.println("Sorry, " + filePath + " is not a valid file path.\n");
			//prints additional error message and info about which line
			e.printStackTrace();
			
		} catch (IOException e) { // I/O error
			
			// customized friendly message
			System.out.println("Sorry, there was an I/O error.\n");
	        //prints the error message and info about which line
	        e.printStackTrace();
	        
	    } 
		
		return bufferedReader;
		
	}
	
	
	/***
	 * closes buffered reader from readFile. Automatically closes the FileReader too
	 * @param buffered reader to close
	 * returns nothing
	 */
	private void closeFile(BufferedReader bufferedReader) {
		try {
			bufferedReader.close();
		} catch (IOException e) {
			// customized friendly message
			System.out.println("Sorry, there was an I/O error.\n");
	        //prints the error message and info about which line
	        e.printStackTrace();
		}
	}
	
	/***
	 * processes a file of student info
	 */
	private void processStudents(BufferedReader bufferedReader, ArrayList<Student> studentList) {
		// reads lines expecting students
		// confirms student format
		//
	}
	
	/***
	 * reads and processes files
	 */
	protected void processFiles(String studentFile, String profFile, String adminFile, String courseFile, Intelligence intel) {
		// takes in student, prof, admin and course files
		// calls respective read, process, and close methods
		// saves objects to intel.<objlist> for each obj type
	}
	
	
	
	

}
