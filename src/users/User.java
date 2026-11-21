package users;

/**
 * @author: Hammans Stallings
 * @author: Emma Johnson
 * @author: Yongling Wu
 *
 * Resources used outside course materials: I used Claude for conceptual help around determining
 * what would be shared and what could remain in the child classes. It helped me find relevant
 * examples from class and other sources for methods. ChatGPT helped me get GitHub set up.
 */

/**
 * Describes the characteristics common to all Users.
 */
public abstract class User {

	//instance variables

	/**
	 * The person's full name, such as "StudentName1"
	 */
	private String name;

	/**
	 * The person's ID, such as "001"
	 */
	private String id;

	/**
	 * The person's username typed at login, such as "testStudent01"
	 */
	private String userName;

	/**
	 * The person's password typed at login, such as "password590"
	 */
	private String password;

	//constructor

	/**
	 * This sets the four values each person has
	 *
	 * @param name     the person's full name, such as "StudentName1" or "Hammans Stallings"
	 * @param id       the person's id, such as "001"
	 * @param userName the person's username typed at login, such as "testStudent01"
	 * @param password the persons' password typed at login, such as "password590"
	 */
	public User(String name, String id, String userName, String password) {

		//stores the name, id, username, and password handed in the instance variable of the
		//object being made
		this.name = name;
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	//accessor/mutator methods

	/**
	 * Hands back the person's name
	 *
	 * @return the person's name
	 */
	public String getName() {
		//returns the person's name as a String
		return this.name;
	}

	/**
	 * Hands back the person's id
	 *
	 * @return the person's id
	 */
	public String getID() {
		//returns the person's ID as a String
		return this.id;
	}

	/**
	 * Gets the person's username as a String
	 *
	 * @return the person's username
	 */
	public String getUserName() {
		//returns the person's username as a String
		return this.userName;
	}

	//other methods

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

		return match;
	}
}
