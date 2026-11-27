import files.Intelligence;
import users.*;
import files.FileInfoReader;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	
	/*
	 * 1. Start program
	 * 2. Show main menu
	 * 3. Show student menu
	 * 4. Show professor menu
	 * 5. Show admin menu
	 * 6. Log user in
	 * 7. Read what user types
	 */
	
	// INSTANCE VARS
	Intelligence intel;
	FileInfoReader fileInfoReader;
	Scanner scanner;
	private String dash25 = "-------------------------\n";
	
	// CONSTRUCTOR
	protected Controller() {
		intel = new Intelligence();
		fileInfoReader = new FileInfoReader();
		scanner = new Scanner(System.in);
	}
	
	/***
	 * Runs the program
	 */
	private void run() {
		
		// calls fileinforeader, which reads the input files when constructed
		fileInfoReader.processFiles("src/studentInfo.txt", "src/professorInfo.txt", "src/adminInfo.txt", "src/courseInfo.txt", intel);
				
		// waiting for valid input flag, for confirming input is valid
		boolean waitForInput = true;
		
		// while we are waiting for a valid input...
		while (waitForInput) {
			
			//print main menu
			printMainMenu();
			
			// read input from user
			String response = scanner.nextLine();
			
			if (response.strip().equalsIgnoreCase("1")) {
				studentMenu();
			}
			else if (response.strip().equalsIgnoreCase("2")) {
				profMenu();
			}
			else if (response.strip().equalsIgnoreCase("3")) {
				adminMenu();
			}
			else if (response.strip().equalsIgnoreCase("Q")) {
				System.out.println("Goodbye!\n");
				waitForInput=false;
			}
			else {
				System.out.println("Sorry, that was not a valid input. Please try again.\n");
			}
			
		}
		
		return;
		
	}
	
	/***
	 * Helper function to print main menu. No inputs or outputs.
	 */
	private void printMainMenu() {
		System.out.println(dash25
				+ "Student Management System\n"
				+ dash25
				+ "1 -- Login as Student\n"
				+ "2 -- Login as Professor\n"
				+ "3 -- Login as Admin\n"
				+ "Q -- quit\n"
				+ "\n"
				+ "Please enter your option: "
				);
	}
	
	/***
	 * Loops through the give object list to find the object with the matching username and password. Makes use of generics 
	 * @param objectList: the list of objects with usernames and passwords. Use Intelligence object to provide the list of objects
	 * @param username: the username to find
	 * @param password: the password to authenticate with
	 */
	private <Type extends User> Type login(ArrayList<Type> userList) {
		
		// waiting for input? 
		boolean waitForLogin = true;
		Type user = null;
		
		// prompt for username
		while (waitForLogin) {
			System.out.println("Please enter your username, or type 'q' to quit.\n");
			String username = scanner.nextLine();
			username = username.strip();
			
			if (username.equalsIgnoreCase("q")) {
				System.out.println("Exiting login prompt\n");
				return null;
			}
			
			//loop through objectList for find object with username
			for (int i=0; i<userList.size(); i++) {
				
				// if we find a username match, set user and exit loop
				if (userList.get(i).getUserName().equalsIgnoreCase(username)) {
					user = userList.get(i);
					break;
				}
			}
			// if we haven't updated the user with a new match
			if (user==null) {
				System.out.println(username + " is not a valid username.\n");
			}
			else {
				// break out of while loop because we have a username
				waitForLogin = false;
			}
			
		}
		
		// reset login wait
		waitForLogin = true ;
		
		// prompt for password
		while (waitForLogin) {
			System.out.println("Please enter your password, or type 'q' to quit.\n");
			String password = scanner.nextLine();
			password = password.strip();
			
			if (password.equalsIgnoreCase("q")) {
				System.out.println("Exiting login prompt.\n");
				return null;
			}
			else if (user.passwordsMatch(password)) {
				waitForLogin = false;
			}
			else {
				System.out.println("Password was not a match.\n");
			}
			
		}
		
		return user;
	}
	
	/***
	 * Helper function to print student menu. No inputs or outputs.
	 */
	private void printStudentMenu(String name) {
		System.out.println(dash25
				+ "Student Menu for: " + name + "\n"
				+ dash25
				+ "1 -- View all Courses\n"
				+ "2 -- Add courses to your list\n"
				+ "3 -- View enrolled courses\n"
				+ "4 -- Drop courses in your list\n"
				+ "5 -- View grades\n"
				+ "Q -- Return to previous menu"
				+ "\n"
				+ "Please enter your option: "
				);
	}
	
	/***
	 * Helper function to print student menu. No inputs or outputs.
	 */
	private void printProfessorMenu(String name) {
		System.out.println(dash25
				+ "Professor Menu for: " + name + "\n"
				+ dash25
				+ "1 -- View given courses\n"
				+ "2 -- View student list of the given course\n"
				+ "Q -- Return to previous menu\n"
				+ "\n"
				+ "Please enter your option: "
				);
	}
	
	/***
	 * Helper function to print student menu. No inputs or outputs.
	 */
	private void printAdminMenu(String name) {
		System.out.println(dash25
				+ "Admin Menu for: " + name + "\n"
				+ dash25
				+ "1 -- View all courses\n"
				+ "2 -- Add new course\n"
				+ "3 -- Delete course\n"
				+ "4 -- Add new professor\n"
				+ "5 -- Delete professor\n"
				+ "6 -- Add new student\n"
				+ "7 -- Delete student\n"
				+ "Q -- Return to previous menu\n"
				+ "\n"
				+ "Please enter your option: "
				);
	}
	
	private void studentMenu() {		
		
		// prompt login and set current student
		Student currentStudent = login(intel.getStudents());
		
		// if current student returns as null, return from student program
		if (currentStudent==null) {return;}
		
		// print menu and handle input
		// waiting for valid input flag, for confirming input is valid
		boolean waitForInput = true;
		
		// while we are waiting for a valid input...
		while (waitForInput) {
			
			//print main menu
			printStudentMenu(currentStudent.getName());
			
			// read input from user
			String response = scanner.nextLine();
			
			if (response.strip().equalsIgnoreCase("1")) {
				//TODO: view all courses
			}
			else if (response.strip().equalsIgnoreCase("2")) {
				//TODO add courses to list
			}
			else if (response.strip().equalsIgnoreCase("3")) {
				//TODO view enrolled courses
			}
			else if (response.strip().equalsIgnoreCase("4")) {
				//TODO drop course from list
			}
			else if (response.strip().equalsIgnoreCase("5")) {
				//TODO view grades
			}
			else if (response.strip().equalsIgnoreCase("Q")) {
				System.out.println("Logging you out!\n");
				currentStudent = null;
				waitForInput=false;
			}
			else {
				System.out.println("Sorry, that was not a valid input. Please try again.\n");
			}
			
		}
		
		return;
		
	}
	
	private void profMenu() {
		// prompt login and set current student
		Professor currentProf = login(intel.getProfessors());
		
		// if current professor returns as null, return from professor program
		if (currentProf==null) {return;}
		
		// print menu and handle input
		// waiting for valid input flag, for confirming input is valid
		boolean waitForInput = true;
		
		// while we are waiting for a valid input...
		while (waitForInput) {
			
			//print main menu
			printProfessorMenu(currentProf.getName());
			
			// read input from user
			String response = scanner.nextLine();
			
			if (response.strip().equalsIgnoreCase("1")) {
				//TODO: view all courses
			}
			else if (response.strip().equalsIgnoreCase("2")) {
				//TODO add courses to list
			}
			else if (response.strip().equalsIgnoreCase("Q")) {
				System.out.println("Logging you out!\n");
				currentProf = null;
				waitForInput=false;
			}
			else {
				System.out.println("Sorry, that was not a valid input. Please try again.\n");
			}
			
		}
		
		return;
	}
	
	private void adminMenu() {
		// prompt login and set current admin
		Admin currentAdmin = login(intel.getAdmins());
		
		// if current admin returns as null, return from admin program
		if (currentAdmin==null) {return;}
		
		// print menu and handle input
		// waiting for valid input flag, for confirming input is valid
		boolean waitForInput = true;
		
		// while we are waiting for a valid input...
		while (waitForInput) {
			
			//print main menu
			printAdminMenu(currentAdmin.getName());
			
			// read input from user
			String response = scanner.nextLine();
			
			if (response.strip().equalsIgnoreCase("1")) {
				//TODO: view all courses
			}
			else if (response.strip().equalsIgnoreCase("2")) {
				//TODO add courses to list
			}
			else if (response.strip().equalsIgnoreCase("3")) {
				//TODO view enrolled courses
			}
			else if (response.strip().equalsIgnoreCase("4")) {
				//TODO drop course from list
			}
			else if (response.strip().equalsIgnoreCase("5")) {
				//TODO view grades
			}
			else if (response.strip().equalsIgnoreCase("6")) {
				//TODO drop course from list
			}
			else if (response.strip().equalsIgnoreCase("7")) {
				//TODO view grades
			}
			else if (response.strip().equalsIgnoreCase("Q")) {
				System.out.println("Logging you out!\n");
				currentAdmin = null;
				waitForInput=false;
			}
			else {
				System.out.println("Sorry, that was not a valid input. Please try again.\n");
			}
			
		}
		
		return;
	}


	public static void main(String[] args) {
		
		Controller controller = new Controller();
		controller.run();
		return;
	}

}
