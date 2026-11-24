package files;

import java.util.*;

public class Intelligence {
	
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
	
	
	// ADDING OBJECTS TO INTEL
	// DOES NOT CHECK IF OBJECT ALREADY EXISTS
	/***
	 * adds a student to the students array list
	 * @param student: the student object to add to the intel list
	 */
	protected void addStudent(Student student) {
		students.add(student);
	}
	
	/***
	 * adds a professor to the professors array list
	 * @param professor: the professor object to add to the intel list
	 */
	protected void addProfessor(Professor prof) {
		profs.add(prof);
	}
	
	/***
	 * adds an admin to the admins array list
	 * @param admin: the admin object to add to the intel list
	 */
	protected void addAdmin(Admin admin) {
		admins.add(admin);
	}

	
	/***
	 * adds a course to the courses array list
	 * @param course: the course object to add to the intel list
	 */
	protected void addCourse(Course course) {
		courses.add(course);
	}
	
	
	// REMOVING OBJECTS FROM INTEL
	// DOES NOT CHECK IF OBJECT ALREADY EXISTS
	/***
	 * removes a student from the students array list
	 * @param student: the student object to remove from the intel list
	 */
	protected void removeStudent(Student student) {
		students.remove(student);
	}
	
	/***
	 * removes a professor from the professors array list
	 * @param professor: the professor object to remove from the intel list
	 */
	protected void removeProfessor(Professor prof) {
		profs.remove(prof);
	}
	
	/***
	 * removes an admin from the admins array list
	 * @param admin: the admin object to remove from the intel list
	 */
	protected void removeAdmin(Admin admin) {
		admins.remove(admin);
	}

	
	/***
	 * removes a course from the courses array list
	 * @param course: the course object to remove from the intel list
	 */
	protected void removeCourse(Course course) {
		courses.remove(course);
	}
	
	
	// CHECK IF OBJECT EXISTS
	/***
	 * checks if a student is in the students array list
	 * @param student: the student object to remove from the intel list
	 * @return true if student is already in intel
	 */
	protected boolean containsStudent(Student student) {
		return students.contains(student);
	}
	
	/***
	 * checks if a professor is in the professors array list
	 * @param professor: the professor object to remove from the intel list
	 * @return true if professor is already in intel
	 */
	protected boolean containsProfessor(Professor prof) {
		return profs.contains(prof);
	}
	
	/***
	 * checks if an admin is in the admins array list
	 * @param admin: the admin object to remove from the intel list
	 * @return true if admin is already in intel
	 */
	protected boolean containsAdmin(Admin admin) {
		return admins.contains(admin);
	}

	
	/***
	 * checks if a course is in the courses array list
	 * @param course: the course object to remove from the intel list
	 * @return true if course is already in intel
	 */
	protected boolean containsCourse(Course course) {
		return courses.contains(course);
	}


}
