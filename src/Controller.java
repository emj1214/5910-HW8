import files.Intelligence;
import files.FileInfoReader;

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
	
	// CONSTRUCTOR
	protected Controller() {
		intel = new Intelligence();
		
	}
	
	/*
	 * Runs the program
	 */
	private void run() {
		
		// calls fileinforeader, which reads the input files when constructed
		FileInfoReader fileInfoReader = new FileInfoReader();
		fileInfoReader.processFiles("studentInfo.txt", "professorInfo.txt", "adminInfo.txt", "courseInfo.txt", intel);
		
		// calls main menu method, which prints main menu and handles user input
		
	}
	
	private void mainMenu() {
		
		//TODO: print main menu
		
		//TODO: accept user input
		
		//TODO: call appropriate user action
		
		// returns nothing
	}

	public static void main(String[] args) {
		
		Controller controller = new Controller();
		controller.run();

	}

}
