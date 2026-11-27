package users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import courses.*;
import files.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Hammans Stallings
 * @author Emma Johnson
 * @author Yongling Wu
 *
 * Resources used outside course materials: I used Claude for conceptual help around determining
 * what would be shared and what could remain in the child classes. It helped me find relevant
 * examples from class and other sources for methods. ChatGPT helped me get GitHub set up.
 */

class StudentTest {

	//the object that each test uses
	Intelligence testIntel;

	//a first test student
	Student student1;

	//a second test student
	Student student2;


	@BeforeEach
	void setUp() throws Exception {

		//makes a new Intelligence object and stores it in the variable testIntel
		this.testIntel = new Intelligence();

		this.testIntel.addCourse(new Course("CIT590",
						"Programming Languages and Techniques", "Brandon L Krakowsky",
						"MW", "16:30", "18:00", 110));

		this.testIntel.addCourse(new Course("CIT591",
						"Introduction to Software Development", "Arvind Bhusnurmath",
						"MW", "16:30", "18:00", 110));

		this.testIntel.addCourse(new Course("CIS192",
						"Mathematical Foundations of Computer Science", "Clayton Greenberg",
						"TR", "10:00", "12:00", 1));

		this.student1 = new Student("Hammans Stallings", "101", "HStallings",
						"456123", testIntel);

		this.student2 = new Student("Emma Johnson", "102", "EJohnson",
						"DEFABC", testIntel);

		this.student2.addCourseToSchedule("CIS192");
	}

	@org.junit.jupiter.api.Test
	void getAllCourses() {

	}

	@org.junit.jupiter.api.Test
	void addCourseToSchedule() {

		//tests adding a course that should fit
		assertEquals("Course added successfully", this.student1
						.addCourseToSchedule("CIT590"));

		//testing that signing up for one class leads to one class being in a student's schedule
		assertEquals(1, this.student1.getEnrolledCourses().size());

		//tests adding a course that is already full
		assertEquals("CIS192 is full.", this.student1
						.addCourseToSchedule("CIS192"));

		//tests adding a course that is already in a student's list
		assertEquals("The course you selected is already in your list",
						this.student1.addCourseToSchedule("CIT590"));

		//tests adding a course that is not in the school registry system
		assertEquals("No class with ID CIS199 found in the course registry.",
						this.student1.addCourseToSchedule("CIS199"));

		//tests adding a course that has a time conflict with an existing course
		//in the student's schedule
		assertEquals("The course you selected has time conflict with CIT590 Programming " +
						"Languages and Techniques.",
						this.student1.addCourseToSchedule("CIT591"));

		//testing that the previous "no" checks did not add any classes to the student's schedule
		assertEquals(1, this.student1.getEnrolledCourses().size());
	}

	@org.junit.jupiter.api.Test
	void dropCourse() {

		//testing that student already has a class on his/her schedule
		assertEquals(1, this.student2.getEnrolledCourses().size());

		//test case where student drops class that is not in schedule
		assertEquals("The course isn't in your schedule.",
						this.student2.dropCourse("CIT591"));

		//testing that dropping a class that is not on a student's schedule
		//does not change count of classes on schedule
		assertEquals(1, this.student2.getEnrolledCourses().size());

		//test case where student successfully drops a class
		assertEquals("Course dropped successfully",
						this.student2.dropCourse("CIS192"));

		//testing that dropping a class reduces schedule by 1
		assertEquals(0, this.student2.getEnrolledCourses().size());



}

	@org.junit.jupiter.api.Test
	void getEnrolledCourses() {
	}

	@org.junit.jupiter.api.Test
	void pastGrades() {
	}

	@org.junit.jupiter.api.Test
	void addPastCourses() {
	}

}