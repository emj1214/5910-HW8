package users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import courses.*;
import files.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {

	//the class that each test uses
	Intelligence course;

	Student student1;

	Student student2;



	@BeforeEach
	void setUp() throws Exception {

		//makes a new Course and stores it in the variable course
		this.course = new Intelligence();

		this.course.addCourse(new Course("CIT590",
						"Programming Languages and Techniques", "Brandon L Krakowsky",
						"MW", "16:30", "18:00", 110)
		);
	}

	@org.junit.jupiter.api.Test
	void getAllCourses() {

		assertEquals(3, this.student1.getAllCourses().size());

	}

	@org.junit.jupiter.api.Test
	void addCourseToSchedule() {
	}

	@org.junit.jupiter.api.Test
	void dropCourse() {
	}

	@org.junit.jupiter.api.Test
	void getEnrolledCourses() {
	}
}