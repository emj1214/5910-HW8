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

import java.util.*;

/**
 * A Student is a type of User. "extends User" makes User the parent class of Student, providing
 * Student with each field and method from User.
 */
public class Professor extends User {

	//instance variables

	//TODO align with YW on the use of the Course class

	/**
	 * A private ArrayList of type Course called "courses" the Professor has signed up for.
	 */
	private ArrayList<Course> courses;

	//constructor

	/**
	 * Public method that makes one professor with no courses yet but does have an ArrayList of
	 * type Course called "courses" associated with the created Professor object.
	 *
	 * @param name     the String full name of the user such as "Hammans Stallings"
	 * @param id       the String id of the user such as "141"
	 * @param userName the String username of the user such as "testStudent01"
	 * @param password the String password of the user such as "password590"
	 */
	public Professor(String name, String id, String userName, String password) {

		//hands the User constructor the following values
		//these are private values in User so only code there can store it
		super(name, id, userName, password);
		//this makes an empty ArrayList of type Courses
		this.courses = new ArrayList<Course>();
	}

	//methods

	/**
	 * Gives the type of user. Overrides User class
	 *
	 * @return the word "professor"
	 */
	@Override
	public String userType() {
		return "professor";
	}

	//TODO align with YW on the use of the Course class


	/**
	 * Gives a line of text including the user's name and id and their courses.
	 *
	 * @return the name and ID for the user (such as "Hammans Stallings (id: 141)")
	 */
	@Override
	public String toString() {
		//returns the name and id and classes of the Professor object the method is called about
		return super.toString() + ", classes: " + this.courses;
	}
}