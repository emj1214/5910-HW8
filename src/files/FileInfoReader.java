package files;

import java.io.*;
import java.util.*;


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
	 * @return nothing
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
	 * processes a file of user info line by line and create objects 
	 * @param bufferedReader: buffered reader object, which should be an opened user data file
	 * @param intel: the intelligence object containing the students, profs, and admins array lists
	 */
	private void processStudents(BufferedReader bufferedReader, Intelligence intel) {
		
		// reads lines expecting students
		
		String line;
		
		try {
			while ((line = bufferedReader.readLine()) != null) { // while there is still another line to read...
				
				// set temp vars to null
				String studentID = null;
				String studentName = null;
				String studentUsername = null;
				String studentPassword = null;
				ArrayList<ArrayList<String>> courseGrades = new ArrayList<ArrayList<String>>();
				
				/*
				 * confirm format for each line
				 * expected format: "student ID; student name; student username; password; course ID: course grade (could be multiple)"
				 * 
				 * 
				 */
				
				// split string into array
				String[] studentData = line.split(";"); 
				
				if (studentData.length < 5) { // less than 5 inputs, must be missing an input
					System.out.println("Sorry, the input...\n\t" + line + "\n...does not have enough inputs. This student cannot be created.\n");
					break;
				}
				else {
					
					// student ID
					studentID = studentData[0].strip();
					
					// student name
					studentName = studentData[1].strip();
					
					// student username
					studentUsername = studentData[2].strip();
					
					// student password
					studentPassword = studentData[3].strip();
					
					// loop through courses, split by comma the colon
					String[] courses = studentData[4].split(",");
					for (int i=0; i<courses.length; i++) {
						String[] courseData = courses[i].split(":");
						if (courseData.length > 2) {
							System.out.println("Sorry, the input...\n\t" + courses[i] + "\n...has too many values (need format \"course ID: grade\").\nThis course grade cannot be created.\n");
							break;
						}
						else if (courseData.length < 2) {
							System.out.println("Sorry, the input...\n\t" + courses[i] + "\n...does not have enough values (need format \"course ID: grade\").\nThis course grade cannot be created.\n");
							break;
						}
						
						courseData[0] = courseData[0].strip();
						courseData[1] = courseData[1].strip();
						courseGrades.add(new ArrayList<>(Arrays.asList(courseData)));
						
						//TODO: actually create the student object
						Student student = new Student();
						
						// if student doesn't exist already, add.
						
						
					}
					
					
				}
				
				
			}
		}
		catch (IOException e) { // if there's an IO related error
			System.out.println("There was an error reading the lines.\n");
		}

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
