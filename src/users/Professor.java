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
 * 1. View course info
 * 2. View student lists
 */

import java.util.*;
import courses.Course;
import files.Intelligence;

/**
 * A Professor is a type of User. "extends User" makes User the parent class of Student, providing
 * Professor with each field and method from User.
 */
public class Professor extends User {

	//instance variables

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
	 * @param intel    the Intelligence object that keeps the lists of students, professors, admins
	 *                 and courses
	 */
	public Professor(String name, String id, String userName, String password, Intelligence intel) {

		//all variables but intel are private values in User so only code there can store them
		//the intel variable is protected so it can be used by child classes
		super(name, id, userName, password, intel);
		//this makes an empty ArrayList of type Courses
		this.courses = new ArrayList<Course>();
	}

	//methods

	/**
	 * Gives back the courses this professor teaches.
	 * @return array list of type Course that the professor teaches
	 */
	public ArrayList<Course> viewCourses() {

		//makes a new empty array list of type course called profCourses
		ArrayList<Course> profCourses = new ArrayList<Course>();

		//asks intel for its list of all courses and saves them as an array list called "all"
		ArrayList<Course> all = this.intel.getCourses();

		//creates a loop running from zero to the total number of classes at the school with index i
		for (int i = 0; i < all.size(); i++) {

			//asks if the lecturer for class in index position i has the same name as the professor
			//the method was called on
			if (all.get(i).getLecturer().equals(this.getName())) {

				//adds classes where the names match to the list profCourses
				profCourses.add(all.get(i));
			}
		}

		//returns the courses taught by the professor
		return profCourses;
	}

	/**
	 * Gives the students of one class, found by courseID.
	 * @param courseID the code for the class
	 * @return the list of all students in that class, or null if no class has that courseID.
	 */
	public ArrayList<Student> viewStudentList(String courseID) {

		//makes a variable of type course that will name the class; starts out with no classes
		Course wanted = null;

		//asks intel for its list of all courses and saves them as an array list called "all"
		ArrayList<Course> all = this.intel.getCourses();

		//creates a loop running from zero to the total number of classes at the school with index i
		for (int i = 0; i < all.size(); i++) {

			//asks if the courseID for class in index position i has the same value as the courseID
			//that was handed in
			if (all.get(i).getCourseId().equals(courseID)) {

				//stores the matching class at place in variable wanted
				wanted = all.get(i);

				//ends the loop once one match is found
				break;
			}
		}

		//if there were no classes found, hand back null
		if (wanted == null) {
			return null;
		}

		//return the list of students
		return wanted.getEnrolledStudents();

	}

	/**
	 * Gives the type of user. Overrides User class
	 *
	 * @return the word "professor"
	 */
	@Override
	public String userType() {
		return "professor";
	}


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