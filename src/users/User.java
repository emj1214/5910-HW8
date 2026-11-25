package users;

/**
 * @author Hammans Stallings
 * @author Emma Johnson
 * @author Yongling Wu
 *
 * Resources used outside course materials: I used Claude for conceptual help around determining
 * what would be shared and what could remain in the child classes. It helped me find relevant
 * examples from class and other sources for methods. ChatGPT helped me get GitHub set up.
 */

import files.Intelligence;

/**
 * Describes the characteristics common to all Users. User is the superclass for Student,
 * Professor, Admin -- i.e., people at the school. Users do not get constructed.
 */
public abstract class User {

	//instance variables

	/**
	 * The person's full name, such as "StudentName1". It is private.
	 */
	private String name;

	/**
	 * The person's ID, such as "001". It is private.
	 */
	private String id;

	/**
	 * The person's username typed at login, such as "testStudent01". It is private.
	 */
	private String userName;

	/**
	 * The person's password typed at login, such as "password590". It is private.
	 */
	private String password;

	/**
	 * The Intelligence object that keeps the lists of students, professors, admins and courses. It is
	 * used by the three child classes so it is kept in the parent class. It is protected so child
	 * classes can use it by name. We chose to have this variable protected because child classes
	 * work with it in most of their methods. The other four variables are facts about one person
	 * which the child classes only read.
	 */
	protected Intelligence intel;

	//constructor

	/**
	 * This sets the five values each kind of person at the school has.
	 *
	 * @param name     the person's full name, such as "StudentName1" or "Hammans Stallings"
	 * @param id       the person's id, such as "001"
	 * @param userName the person's username typed at login, such as "testStudent01"
	 * @param password the person's password typed at login, such as "password590"
	 * @param intel		 the Intelligence object that keeps the lists of students, professors, admins
	 *                 and courses
	 */
	public User(String name, String id, String userName, String password, Intelligence intel) {

		//stores the name, id, username, password, and intel handed in the instance variables of the
		//object being made
		this.name = name;
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.intel = intel;
	}

	//accessor/mutator methods

	/**
	 * Public method that hands back the person's name
	 *
	 * @return the person's name
	 */
	public String getName() {
		//returns the user's name as a String
		return this.name;
	}

	/**
	 * Public method that hands back the person's id
	 *
	 * @return the person's id
	 */
	public String getID() {
		//returns the user's ID as a String
		return this.id;
	}

	/**
	 * Public method that gets the person's username as a String
	 *
	 * @return the person's username
	 */
	public String getUserName() {
		//returns the person's username as a String
		return this.userName;
	}

	//other methods

	/**
	 * Public abstract method that gives the kind of user in one word.
	 * This is abstract so there is no body for this method here.
	 * Each subclass must write the method.
	 *
	 * @return the userType in lower-case such as "student", "admin", or "professor"
	 */
	public abstract String userType();

	/**
	 * Public method that says if this User has the given ID by answering with true or false (boolean).
	 * This is a case-sensitive match.
	 *
	 * @param typedId the String value typed in by the user representing their id
	 * @return boolean of whether the handed-in id (typedId) is the same as the id of the user the
	 * method is called on
	 */
	public boolean idsMatch(String typedId) {

		//returns the comparison of two Strings, letter for letter, as a boolean
		return this.id.equals(typedId);
	}

	/**
	 * Evaluates whether the typed text is the same as the password that is kept.
	 *
	 * @param typedPassword the handed-in password from the person
	 * @return a boolean indicating whether the passwords match or not
	 */
	public boolean passwordsMatch(String typedPassword) {

		//creates a boolean set to false as default
		boolean match = false;

		//logic evaluates if the password that was typed is the same as for the User object that called
		//the method
		if (this.password.equals(typedPassword)) {
			match = true;
		}
		//passes back a boolean called "match" that indicates whether passwords match or not
		return match;
	}

	/**
	 * Gives a line of text including the user's name and id.
	 *
	 * @return the name and ID for the user (such as "Hammans Stallings (id: 141)")
	 */
	@Override
	public String toString() {
		//return the name and id of the User object the method is called about
		return this.name + " (id: " + this.id + ")";
	}

}