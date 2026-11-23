package files;

import java.util.*;

class Intelligence {
	
	// instance vars
	
	// students array
	// TODO: validate object type
	ArrayList<Student> students;
	// profs array
	// TODO: validate object type
	ArrayList<Professor> profs;
	// admins array
	// TODO: validate object type
	ArrayList<Admin> admins;
	// courses array
	// TODO: validate object type
	ArrayList<Course> courses;
	
	
	/***
	 * Constructor: creates empty array lists for each object type
	 */
	public Intelligence() {
		// students array
		// TODO: validate object type
		students = new ArrayList<Student>();
		// profs array
		// TODO: validate object type
		profs = new ArrayList<Professor>();
		// admins array
		// TODO: validate object type
		admins = new ArrayList<Admin>();
		// courses array
		// TODO: validate object type
		courses = new ArrayList<Course>();
	}
	
	/***
	 * returns the students array list
	 */
	protected ArrayList<Student> getStudent() {
		return students;
	}
	
	/***
	 * returns the professors array list
	 */
	protected ArrayList<Professor> getProfessor() {
		return profs;
	}
	
	/***
	 * returns the admins array list
	 */
	protected ArrayList<Admin> getAdmins() {
		return admins;
	}

	
	/***
	 * returns the courses array list
	 */
	protected ArrayList<Course> getCourses() {
		return courses;
	}


}
