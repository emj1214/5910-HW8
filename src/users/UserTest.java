package users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import files.Intelligence;

/**
 * @author Hammans Stallings
 * @author Emma Johnson
 * @author Yongling Wu
 *
 * Resources used outside course materials: I used Claude for conceptual help around determining
 * what would be shared and what could remain in the child classes. It helped me find relevant
 * examples from class and other sources for methods. ChatGPT helped me get GitHub set up.
 */

class UserTest {

	Student testStudent;

	Professor testProfessor;

	Admin testAdmin;

	@BeforeEach
	void setUp() throws Exception {

		//creates a new Intelligence object named testIntel
		Intelligence testIntel = new Intelligence();

		testStudent = new Student("Hammans Stallings", "101", "HStallings",
						"456123", testIntel);
		testProfessor = new Professor("Emma Johnson", "102", "EJohnson",
						"DEFABC", testIntel);
		testAdmin = new Admin("Yongling Wu", "103", "YWu",
						"OPQLMN", testIntel);
	}

	@org.junit.jupiter.api.Test
	void testUserType() {

		assertEquals("student", testStudent.userType());
		assertEquals("professor", testProfessor.userType());
		assertEquals("admin", testAdmin.userType());

	}

	@org.junit.jupiter.api.Test
	void testIDsMatch() {

		assertTrue(this.testStudent.idsMatch("101"));
		assertFalse(this.testStudent.idsMatch("one hundred and one"));
		assertTrue(this.testProfessor.idsMatch("102"));
		assertTrue(this.testAdmin.idsMatch("103"));
	}

	@org.junit.jupiter.api.Test
	void testPasswordsMatch() {

		assertTrue(this.testStudent.passwordsMatch("456123"));

		assertFalse(this.testStudent.passwordsMatch("four five six " +
						"one two three"));

		assertTrue(this.testProfessor.passwordsMatch("DEFABC"));

		assertTrue(this.testAdmin.passwordsMatch("OPQLMN"));
	}

	@org.junit.jupiter.api.Test
	void testToString() {

		assertEquals("Hammans Stallings (id: 101)", this.testStudent.toString());
		assertEquals("Emma Johnson (id: 102)", this.testProfessor.toString());
		assertEquals("Yongling Wu (id: 103)", this.testAdmin.toString());
	}
}