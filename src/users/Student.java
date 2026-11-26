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
 * 1. View all courses
 * 2. Add courses
 * 3. Drop courses
 * 4. Block adding a course already on list
 * 5. Block adding a course not in system
 * 6. Block adding a course with a time conflict
 * 7. Block dropping a course not on the list
 * 8. Check course schedule
 * 9. View grades
 */

import java.util.*;

import courses.Course;
import files.Intelligence;

/**
 * A Student is a type of User. "extends User" makes User the parent class of Student, providing
 * Student with each field and method from User.
 */
public class Student extends User {

	//instance variables

	/**
	 * A private ArrayList of type Course called "courses" the Student has signed up for.
	 */
	private ArrayList<Course> courses;

	//constructor

	/**
	 * Public method that makes one student with no courses yet but does have an ArrayList of
	 * type Course called "courses" associated with the created Student object.
	 *
	 * @param name     the String full name of the user such as "Hammans Stallings"
	 * @param id       the String id of the user such as "141"
	 * @param userName the String username of the user such as "testStudent01"
	 * @param password the String password of the user such as "password590"
	 * @param intel    the Intelligence object that keeps the lists of students, professors, admins
	 *                 and courses
	 */
	public Student(String name, String id, String userName, String password, Intelligence intel) {

		//hands the User constructor the following values
		//all variables but intel are private values in User so only code there can store them
		//the intel variable is protected so it can be used by child classes
		super(name, id, userName, password, intel);
		//this makes an empty ArrayList of type Courses
		this.courses = new ArrayList<Course>();
	}

	//methods

	/**
	 * Gives the type of user. Overrides User class
	 *
	 * @return the word "student"
	 */
	@Override
	public String userType() {
		return "student";
	}

	/**
	 * Hands back an ArrayList of all courses
	 *
	 * @return Arraylist of all courses
	 */
	public ArrayList<Course> getAllCourses() {
		//returns the array list of all courses
		return intel.getCourses();
	}

	/**
	 * Takes one class and adds it to a student's list of courses if none of four checks say no.
	 * The class cannot already be on the list. It needs to be in the registry system. There cannot
	 * be a time conflict with an existing course that is on the student's list of courses. The
	 * course cannot be full.
	 *
	 * @param courseID the code number for the course, such as "CIT591"
	 * @return a line that says what was done, such as "Course added successfully"
	 */
	public String addCourseToSchedule(String courseID) {

		//defines a course object called wanted with value of null
		Course wanted = null;

		// pulls all courses in the intel object and saves them as an ArrayList of Courses
		// called all
		ArrayList<Course> all = this.intel.getCourses();

		//creates a loop of an index i starting from 0 that runs as long as it is smaller
		// than the size of all
		for (int i = 0; i < all.size(); i++) {
			// asks if the course in all is the same as what was passed in as "courseID"
			// all.get(i) gives the course at place i
			// adding .getCourseID() gives the ID
			if (all.get(i).getCourseId().equals(courseID)) {
				//this keeps the class that was found
				wanted = all.get(i);

				//ends the loop once a match is found
				break;
			}
		}
		//if we did not find the course in the registry
		if (wanted == null) {

			return "No class with ID " + courseID + " found in the course registry.";
		}

		//creates a loop over the class list of the student
		//this.courses.size() shows the number of classes the student has in his/her courses field
		for (int i = 0; i < this.courses.size(); i++) {
			//this saves one of the student's courses (at place i) in a Course object called mine
			Course mine = this.courses.get(i);

			//checks if the student already has this course in his/her lists of courses
			if (mine.getCourseId().equals(courseID)) {

				return "The course you selected is already in your list";
			}

			//checks to see if the wanted class has a time conflict with class on the existing list of
			//classes the student has signed up for
			if (wanted.hasTimeConflict(mine)) {

				return "The course you selected has time conflict with " + mine.getCourseId() + " "
								+ mine.getCourseName() + ".";
			}
		}

		//if the wanted class was not able to add the student
		if (!wanted.addStudent(this)) {

			return wanted.getCourseId() + " is full.";
		}

		//puts the wanted class on the list
		this.courses.add(wanted);

		return "Course added successfully";
	}

	/**
	 * Removes a course from a student's schedule and the student from the class. Checks to make sure
	 * the student already has the class on his/her schedule before removing them.
	 * @param courseID the passed in course ID from the student
	 * @return a line that says what was done, such as "Course dropped successfully"
	 */
	public String dropCourse(String courseID) {

		//defines a variable of type Course called courseToDrop with no objects
		Course courseToDrop = null;

		//creates a loop of an index i starting from 0 that runs as long as it is smaller
		// than the size of all of the courses for this student
		for (int i = 0; i < this.courses.size(); i++) {
			// asks if any course in this.courses has the SAME ID as what was passed
			// in as "courseID"
			// this.courses gives the courses for this student
			// adding .getCourseId() gives the ID
			if (this.courses.get(i).getCourseId().equals(courseID)) {
				//this keeps the class that was found
				courseToDrop = this.courses.get(i);

				//ends the loop once a match is found
				break;
			}
		}
		//if we did not find the course in the student's current schedule
		if (courseToDrop == null) {

			return "The course isn't in your schedule.";
		}
		//removes the course from the student's schedule
		this.courses.remove(courseToDrop);
		//removes the student from the course's list
		courseToDrop.removeStudent(this);

		return "Course dropped successfully";
	}


	/**
	 * Gives the classes of this Student, as pieces, in the order taken.
	 *
	 * @return the class list
	 */
	public ArrayList<Course> getEnrolledCourses() {
		//the class list of this student
		return this.courses;
	}

	/**
	 * Gives a line of text including the user's name and id and their courses.
	 *
	 * @return the name and ID for the user (such as "Hammans Stallings (id: 141)")
	 */
	@Override
	public String toString() {
		//returns the name and id and classes of the Student object the method is called about
		return super.toString() + ", classes: " + this.courses;
	}

}





