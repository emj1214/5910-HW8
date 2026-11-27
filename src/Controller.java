import files.Intelligence;
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
	
	/*
	 * Runs the program
	 */
	private void run() {
		
		// calls fileinforeader, which reads the input files when constructed
		fileInfoReader.processFiles("studentInfo.txt", "professorInfo.txt", "adminInfo.txt", "courseInfo.txt", intel);
				
		// waiting for valid input flag, for confirming input is valid
		boolean waitForInput = true;
		
		// while we are waiting for a valid input...
		while (waitForInput) {
			
			//print main menu
			printMainMenu();
			
			// read input from user
			String response = scanner.nextLine();
			
			if (response.strip().equalsIgnoreCase("1")) {
				//TODO route to student login
			}
			else if (response.strip().equalsIgnoreCase("2")) {
				//TODO route to prof login
			}
			else if (response.strip().equalsIgnoreCase("3")) {
				//TODO route to admin login
			}
			else if (response.strip().equalsIgnoreCase("Q")) {
				System.out.println("Goodbye!\n");
				waitForInput=false;
			}
			else if (response.strip().equalsIgnoreCase("?")) {
				System.out.println("this is a help message"
						);
			}
			else {
				System.out.println("Sorry, that was not a valid input. Please try again.\n");
			}
			
		}
		
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
				+ "? -- help"
				+ "\n"
				+ "Please enter your option: "
				);
	}
	
	/***
	 * Loops through the give object list to find the object with the matching username and password
	 * @param objectList: the list of objects with usernames and passwords. Use Intelligence object to provide the list of objects
	 * @param username: the username to find
	 * @param password: the password to authenticate with
	 */
	private void login(ArrayList<?> objectList, String username, String password) {
		//loop through objectList for find object with username
		for (int i=0; i<objectList.size(); i++) {
			//TODO do we have an easy way to retrieve usernames and passwords?
		}
	}
	
	/***
	 * Helper function to print student menu. No inputs or outputs.
	 */
	private void printStudentMenu() {
		System.out.println(dash25
				+ "Student Management System\n"
				+ dash25
				+ "1 -- Login as Student\n"
				+ "2 -- Login as Professor\n"
				+ "3 -- Login as Admin\n"
				+ "Q -- quit\n"
				+ "? -- help"
				+ "\n"
				+ "Please enter your option: "
				);
	}
	
	private void studentMenu() {
		//TODO
	}
	
	private void profMenu() {
		//TODO
	}
	
	private void adminMneu() {
		//TODO
	}


	public static void main(String[] args) {
		
		Controller controller = new Controller();
		controller.run();

	}

}
