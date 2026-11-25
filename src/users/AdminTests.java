package users;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import files.Intelligence;
import courses.Course;

public class AdminTests {

	/**
	 * Tests the admin constructor, user type, and view methods.
	 */
	@Test
	public void testAdminAndViewLists() {

		Intelligence intel = new Intelligence();

		Admin admin = new Admin("AdminName1", "001", "testAdmin01","password590", intel);

		assertEquals("admin", admin.userType());
		
		assertEquals("AdminName1", admin.getName());
		assertEquals("001", admin.getID());
		assertEquals("testAdmin01", admin.getUserName());

		assertTrue(admin.viewCourses().isEmpty());
		assertTrue(admin.viewStudents().isEmpty());
		assertTrue(admin.viewProfessors().isEmpty());
	}

	/**
	 * Tests adding courses and checking for duplicates and conflicts.
	 */
	@Test
	public void testAddCourse() {

		Intelligence intel = new Intelligence();

		Admin admin = new Admin("AdminName1", "001", "testAdmin01", "password590", intel);

		Professor professor = new Professor("Brandon L Krakowsky", "029", "testProf01", "password590");

		intel.addProfessor(professor);

		Course course1 = new Course("CIT590", "Programming Languages and Techniques", "Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

		Course duplicateCourse = new Course("CIT590", "Programming Languages and Techniques", "Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

		Course conflictCourse = new Course("CIT591", "Test Course", "Brandon L Krakowsky", "MW", "17:00", "18:30", 30);

		Course unknownProfessor = new Course("CIT592", "Mathematical Foundations of Computer Science", "Clayton Greenberg", "TR", "10:00", "11:00", 72);

		assertTrue(admin.addCourse(course1));
		assertFalse(admin.addCourse(duplicateCourse));
		assertFalse(admin.addCourse(conflictCourse));
		assertFalse(admin.addCourse(unknownProfessor));

		assertEquals(1, admin.viewCourses().size());
	}

	/**
	 * Tests deleting a course from the system.
	 */
	@Test
	public void testDeleteCourse() {

		Intelligence intel = new Intelligence();

		Admin admin = new Admin("AdminName1", "001", "testAdmin01", "password590", intel);

		Course course = new Course("CIT590", "Programming Languages and Techniques", "Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

		Student student = new Student("StudentName1", "001", "testStudent01", "password590");

		// Course does not exist
		assertFalse(admin.deleteCourse("CIT590"));

		// Add course and enroll student
		intel.addCourse(course);
		course.addStudent(student);

		// Cannot delete a course with enrolled students
		assertFalse(admin.deleteCourse("CIT590"));

		// Remove student and delete course
		course.removeStudent(student);
		assertTrue(admin.deleteCourse("CIT590"));

		assertEquals(0, admin.viewCourses().size());
	}

	/**
	 * Tests adding students and checking duplicate IDs and usernames.
	 */
	@Test
	public void testAddStudent() {

		Intelligence intel = new Intelligence();

		Admin admin = new Admin("AdminName1", "001", "testAdmin01","password590", intel);

		Student student1 = new Student("StudentName1", "003", "testStudent01", "password590");

		Student sameId = new Student("StudentName2", "003", "testStudent02", "password590");

		Student sameUsername = new Student("StudentName3", "004", "testStudent01", "password590");

		assertTrue(admin.addStudent(student1));
		assertFalse(admin.addStudent(sameId));
		assertFalse(admin.addStudent(sameUsername));

		assertEquals(1, admin.viewStudents().size());
	}

	/**
	 * Tests deleting a student from the system.
	 */
	@Test
	public void testDeleteStudent() {

		Intelligence intel = new Intelligence();

		Admin admin = new Admin("AdminName1", "001", "testAdmin01", "password590", intel);

		Student student1 = new Student("StudentName1", "003", "testStudent01", "password590");

		assertFalse(admin.deleteStudent("003"));

		assertTrue(admin.addStudent(student1));

		assertTrue(admin.deleteStudent("003"));

		assertFalse(admin.deleteStudent("003"));

		assertEquals(0, admin.viewStudents().size());
	}

	/**
	 * Tests removing a deleted student from a course.
	 */
	@Test
	public void testDeleteStudentFromCourse() {

		Intelligence intel = new Intelligence();

		Admin admin = new Admin("AdminName1", "001", "testAdmin01", "password590", intel);

		Student student = new Student("StudentName1", "001", "testStudent01", "password590");

		Course course = new Course("CIT590", "Programming Languages and Techniques", "Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

		intel.addStudent(student);
		intel.addCourse(course);
		course.addStudent(student);

		assertEquals(1, course.getEnrolledStudents().size());

		assertTrue(admin.deleteStudent("001"));

		assertEquals(0, admin.viewStudents().size());
		assertEquals(0, course.getEnrolledStudents().size());
	}


	/**
	 * Tests adding professors and checking duplicate IDs and usernames.
	 */
	@Test
	public void testAddProfessor() {

		Intelligence intel = new Intelligence();

		Admin admin = new Admin("AdminName1", "001", "testAdmin01", "password590", intel);

		Professor professor1 = new Professor("Brandon L Krakowsky", "029", "testProf01", "password590");

		Professor sameId = new Professor("Clayton Greenberg", "029", "testProf02", "password590");

		Professor sameUsername = new Professor("Arvind Bhusnurmath", "031", "testProf01", "password590");

		assertTrue(admin.addProfessor(professor1));
		assertFalse(admin.addProfessor(sameId));
		assertFalse(admin.addProfessor(sameUsername));

		assertEquals(1, admin.viewProfessors().size());
	}


	/**
	 * Tests deleting a professor from the system.
	 */
	@Test
	public void testDeleteProfessor() {

		Intelligence intel = new Intelligence();

		Admin admin = new Admin("AdminName1", "001", "testAdmin01", "password590", intel);

		Professor professor = new Professor("Brandon L Krakowsky", "029", "Krakowsky", "password590");

		Course course = new Course("CIT590", "Programming Languages and Techniques", "Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

		assertFalse(admin.deleteProfessor("029"));

		assertTrue(admin.addProfessor(professor));

		intel.addCourse(course);
		assertFalse(admin.deleteProfessor("029"));

		intel.removeCourse(course);
		assertTrue(admin.deleteProfessor("029"));

		assertEquals(0, admin.viewProfessors().size());
	}


}
