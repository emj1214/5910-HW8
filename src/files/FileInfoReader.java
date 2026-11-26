package files;

import java.io.*;
import java.util.*;
import users.*;
import courses.*;


public class FileInfoReader {
		
	/***
	 * opens a file if it exists
	 * @param filePath to file to read
	 * @return buffered reader
	 * @implNote WARNING this method opens a BufferedReader but does not close it. Call closeFile() to close the file.
	 */
	private BufferedReader readFile(String filePath) {
		
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
	 * processes a file of student info line by line and create objects 
	 * @param bufferedReader: buffered reader object, which should be an opened user data file
	 * @param intel: the intelligence object containing the students array lists
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
				 * if there are at least 5 inputs, assuming the correct order, creates the student object
				 * if the student doesn't already exist, it will add the student to the database
				 * if the student already exists, it will print a friendly message
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
					
					// confirm no inputs are empty
					if (studentName.length()==0 
							|| studentID.length()==0 
							|| studentUsername.length()==0 
							|| studentPassword.length()==0 ) {
						
						System.out.println("Empty inputs are not accepted. " + line + " could not be created as a student.\n");
						break;
					}
					
					// create a student object
					Student student = new Student(studentName, studentID, studentUsername, studentPassword);
					
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
						
						// TODO: associate courses with student.
						student.addCourseToSchedule();
						
					}
										
					// if student doesn't exist already, add.
					if (!(intel.containsStudent(student))) { // uses Student.equals() method for "contains" comparisons
						intel.addStudent(student);
					}
					else { // student already exists, can't add
						System.out.println("Sorry, " + student.getName() + " already exists in this database. Update or delete the student instead.\n");
						break;
					}
					
				}
				
			}
		}
		catch (IOException e) { // if there's an IO related error
			System.out.println("There was an error reading the lines from the student input file.\n");
		}

	}
	
	/***
	 * processes a file of professor info line by line and create objects 
	 * @param bufferedReader: buffered reader object, which should be an opened user data file
	 * @param intel: the intelligence object containing the profs array lists
	 */
	private void processProfessors(BufferedReader bufferedReader, Intelligence intel) {
		
		// reads lines expecting students
		
		String line;
		
		try {
			while ((line = bufferedReader.readLine()) != null) { // while there is still another line to read...
				
				// set temp vars to null
				String professorID = null;
				String professorName = null;
				String professorUsername = null;
				String professorPassword = null;
				
				/*
				 * confirm format for each line
				 * expected format: "prof name; prof ID; prof username; password"
				 * 
				 * if there are exactly 4 inputs, assuming the correct order, creates the professor object
				 * if the professor doesn't already exist, it will add the professor to the database
				 * if the professor already exists, it will print a friendly message
				 */
				
				// split string into array
				String[] professorData = line.split(";"); 
				
				if (professorData.length != 4) { // not exactly 4 inputs, must have bad data
					System.out.println("Sorry, the input...\n\t" + line + "\n...does not have the right number of inputs. This professor cannot be created.\n");
					break;
				}
				else {
					
					// professor name
					professorName = professorData[0].strip();
					
					// professor ID
					professorID = professorData[1].strip();
					
					// professor username
					professorUsername = professorData[2].strip();
					
					// professor password
					professorPassword = professorData[3].strip();
					
					// confirm no inputs are empty
					if (professorName.length()==0 
							|| professorID.length()==0 
							|| professorUsername.length()==0 
							|| professorPassword.length()==0 ) {
						
						System.out.println("Empty inputs are not accepted. " + line + " could not be created as a professor.\n");
						break;
					}
					
					// create a professor object
					Professor prof = new Professor(professorName, professorID, professorUsername, professorPassword);
					
					// if professor doesn't exist already, add.
					if (!(intel.containsProfessor(prof))) { // uses Professor.equals() method for "contains" comparisons
						intel.addProfessor(prof);
					}
					else { // professor already exists, can't add
						System.out.println("Sorry, " + prof.getName() + " already exists in this database. Update or delete the professor instead.\n");
						break;
					}
				}
				
			}
		}
		catch (IOException e) { // if there's an IO related error
			System.out.println("There was an error reading the lines from the professor input file.\n");
		}

	}
	
	
	/***
	 * processes a file of course info line by line and create objects 
	 * @param bufferedReader: buffered reader object, which should be an opened course data file
	 * @param intel: the intelligence object containing the courses array lists
	 */
	private void processCourses(BufferedReader bufferedReader, Intelligence intel) {
		
		// reads lines expecting students
		
		String line;
		
		try {
			while ((line = bufferedReader.readLine()) != null) { // while there is still another line to read...
				
				// set temp vars to null/impossible value
				String courseID = null;
				String courseName = null;
				String lecturer = null;
				String days = null;
				String startTime = null;
				String endTime = null;
				int capacity = -1;
				
				/*
				 * confirm format for each line
				 * expected format: "course ID; course name; lecturer; days; start time; end time; capacity"
				 * 
				 * if there are exactly 4 inputs, assuming the correct order, creates the course object
				 * if the course doesn't already exist, it will add the course to the database
				 * if the course already exists, it will print a friendly message
				 */
				
				// split string into array
				String[] courseData = line.split(";"); 
				
				if (courseData.length != 7) { // not exactly 7 inputs, must have bad data
					System.out.println("Sorry, the input...\n\t" + line + "\n...does not have the right number of inputs. This course cannot be created.\n");
					break;
				}
				else {
					
					// course ID
					courseID = courseData[0].strip();
					
					// course name
					courseName = courseData[1].strip();
					
					// lecturer
					lecturer = courseData[2].strip();
					
					// days
					days = courseData[3].strip();
					
					// start time
					startTime = courseData[4].strip();
					
					// end time
					endTime = courseData[5].strip();
					
					// confirm no inputs are empty
					if (courseID.length()==0 
							|| courseName.length()==0 
							|| lecturer.length()==0 
							|| days.length()==0
							|| startTime.length()==0
							|| endTime.length()==0 ) {
						
						System.out.println("Empty inputs are not accepted. " + line + " could not be created as a course.\n");
						break;
					}
					
					// capacity
					// try to cast from string to int. If we can't cast, throw error and break this iteration of loop
					try {
					    capacity = Integer.parseInt(courseData[6].strip());
					} catch (NumberFormatException e) {
					    System.out.println("Sorry, " + courseData[6].strip() + " is not a valid integer. This course (" + courseID + ": " + courseName + ") cannot be created.\n");
					    break;
					}
					
					// create a course object
					Course course = new Course(courseID, courseName, lecturer,
			                  days, startTime, endTime, capacity);
					
					// if course doesn't exist already, add.
					if (!(intel.containsCourse(course))) { // uses Course.equals() method for "contains" comparisons
						intel.addCourse(course);
					}
					else { // professor already exists, can't add
						System.out.println("Sorry, " + courseID + ": " + courseName + " already exists in this database. Update or delete the course instead.\n");
						break;
					}
				}
				
			}
		}
		catch (IOException e) { // if there's an IO related error
			System.out.println("There was an error reading the lines from the course input file.\n");
		}

	}
	
	
	/***
	 * processes a file of admin info line by line and create objects 
	 * @param bufferedReader: buffered reader object, which should be an opened user data file
	 * @param intel: the intelligence object containing the admins array lists
	 */
	private void processAdmins(BufferedReader bufferedReader, Intelligence intel) {
		
		// reads lines expecting students
		
		String line;
		
		try {
			while ((line = bufferedReader.readLine()) != null) { // while there is still another line to read...
				
				// set temp vars to null
				String adminID = null;
				String adminName = null;
				String adminUsername = null;
				String adminPassword = null;
				
				/*
				 * confirm format for each line
				 * expected format: "admin ID; admin name; admin username; password"
				 * 
				 * if there are exactly 4 inputs, assuming the correct order, creates the admin object
				 * if the admin doesn't already exist, it will add the admin to the database
				 * if the admin already exists, it will print a friendly message
				 */
				
				// split string into array
				String[] adminData = line.split(";"); 
				
				if (adminData.length != 4) { // not exactly 4 inputs, must have bad data
					System.out.println("Sorry, the input...\n\t" + line + "\n...does not have the right number of inputs. This admin cannot be created.\n");
					break;
				}
				else {
					
					// admin name
					adminName = adminData[0].strip();
					
					// admin ID
					adminID = adminData[1].strip();
					
					// admin username
					adminUsername = adminData[2].strip();
					
					// admin password
					adminPassword = adminData[3].strip();
					
					// create a admin object
					Admin admin = new Admin(adminName, adminID, adminUsername, adminPassword, intel);
					
					// if admin doesn't exist already, add.
					if (!(intel.containsAdmin(admin))) { // uses Admin.equals() method for "contains" comparisons
						intel.addAdmin(admin);
					}
					else { // admin already exists, can't add
						System.out.println("Sorry, " + admin.getName() + " already exists in this database. Update or delete the admin instead.\n");
						break;
					}
				}
				
			}
		}
		catch (IOException e) { // if there's an IO related error
			System.out.println("There was an error reading the lines from the admin input file.\n");
		}

	}
	
	
	/***
	 * reads and processes files
	 */
	public void processFiles(String studentFile, String profFile, String adminFile, String courseFile, Intelligence intel) {
		
		// if any input is null, print message and quit
		if (studentFile==null 
				|| profFile == null
				|| adminFile == null
				|| courseFile == null ) {
			System.out.println("All files must exist.\n");
			return;
		}
		
		// process students - open reader, try to process, close reader
		BufferedReader studentReader = readFile(studentFile);
		if (studentReader!=null) {
			processStudents(studentReader, intel);
		}
		else {
			System.out.println("There is no student data to process.\n");
		}
		closeFile(studentReader);
		
		// process professors - open reader, try to process, close reader
		BufferedReader profReader = readFile(profFile);
		if (profReader!=null) {
			processProfessors(profReader, intel);
		}
		else {
			System.out.println("There is no professor data to process.\n");
		}
		closeFile(profReader);
		
		// process admins - open reader, try to process, close reader
		BufferedReader adminReader = readFile(adminFile);
		if (adminReader!=null) {
			processAdmins(adminReader, intel);
		}
		else {
			System.out.println("There is no admin data to process.\n");
		}
		closeFile(adminReader);
		
		// process courses - open reader, try to process, close reader
		BufferedReader courseReader = readFile(courseFile);
		if (courseReader!=null) {
			processCourses(courseReader, intel);
		}
		else {
			System.out.println("There is no course data to process.\n");
		}
		closeFile(courseReader);
		
	}

}
