package courses;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import users.Student;
import files.Intelligence;

public class CourseTests {

	/**
	 * Tests the course constructor and getters.
	 */
	@Test
	public void testConstructorAndGetters() {

		Course course = new Course("CIT590", "Programming Languages and Techniques","Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

		assertEquals("CIT590", course.getCourseId());
		assertEquals("Programming Languages and Techniques", course.getCourseName());
		assertEquals("Brandon L Krakowsky", course.getLecturer());
		assertEquals("MW", course.getDays());
		assertEquals("16:30", course.getStartTime());
		assertEquals("18:00", course.getEndTime());
		assertEquals(110, course.getCapacity());
	}

	/**
	 * Tests if two courses have a time conflict.
	 */
	@Test
	public void testTimeConflict() {

		Course course1 = new Course("CIT592", "Mathematical Foundations of Computer Science","Clayton Greenberg", "TR", "10:00", "11:00", 72);

		Course course2 = new Course("CIT594", "Data Structures and Sofware Design","Eric Noel Fouh Mbindi", "TR", "10:30", "12:00", 72);

		assertTrue(course1.hasTimeConflict(course2));
	}
	
	/**
	 * Tests courses that do not have time conflicts.
	 */
	@Test
	public void testNoTimeConflict() {

	    Course course1 = new Course("CIT596", "Algorithms and Computation","Arvind Bhusnurmath", "MW", "10:30", "12:00", 120);

	    Course course2 = new Course("CIT592", "Mathematical Foundations of Computer Science","Clayton Greenberg", "TR", "10:00", "11:00", 72);

	    Course course3 = new Course("CIT590", "Programming Languages and Techniques","Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

	    assertFalse(course1.hasTimeConflict(course2));
	    assertFalse(course1.hasTimeConflict(course3));
	}

	/**
	 * Tests adding students to a course.
	 */
	@Test
	public void testAddStudent() {

		Intelligence intel = new Intelligence();

	    Course course = new Course("CIT590", "Programming Languages and Techniques", "Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

	    Student student1 = new Student("StudentName1", "001", "testStudent01", "password590", intel);

	    Student duplicateStudent = new Student("StudentName1", "001", "testStudent01", "password590“, intel);

	    assertTrue(course.addStudent(student1));
	    assertFalse(course.addStudent(student1));
	    assertFalse(course.addStudent(duplicateStudent));
	    assertEquals(1, course.getEnrolledStudents().size());
	}
	
	/**
	 * Tests course capacity limit.
	 */
	@Test
	public void testCourseCapacity() {
		
		Intelligence intel = new Intelligence();

	    Course course = new Course("CIT590", "Programming Languages and Techniques","Brandon L Krakowsky", "MW", "16:30", "18:00", 2);

	    Student student1 = new Student("StudentName1", "001", "testStudent01", "password590", intel);

	    Student student2 = new Student("StudentName2", "002", "testStudent02", "password590", intel);

	    Student student3 = new Student("StudentName3", "003", "testStudent03", "password590", intel);

	    assertTrue(course.addStudent(student1));
	    assertTrue(course.addStudent(student2));
	    assertFalse(course.addStudent(student3));

	    assertEquals(2, course.getEnrolledStudents().size());
	}
	
	/**
	 * Tests removing a student from a course.
	 */
	@Test
	public void testRemoveStudent() {

		Intelligence intel = new Intelligence();

	    Course course = new Course("CIT590", "Programming Languages and Techniques","Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

	    Student student1 = new Student("StudentName1", "001", "testStudent01", "password590", intel);
	    assertFalse(course.removeStudent(student1));

	    assertTrue(course.addStudent(student1));

	    assertTrue(course.removeStudent(student1));

	    assertFalse(course.removeStudent(student1));

	    assertEquals(0, course.getEnrolledStudents().size());
	}
	
	/**
	 * Tests the course information returned by toString().
	 */
	@Test
	public void testToString() {

	    Course course = new Course("CIT590", "Programming Languages and Techniques", "Brandon L Krakowsky", "MW", "16:30", "18:00", 110);

	    String expected = "CIT590; Programming Languages and Techniques; "+ "Brandon L Krakowsky; MW; 16:30; 18:00; 110";

	    assertEquals(expected, course.toString());
	}
}
