package users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import files.Intelligence;

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

	}

	@org.junit.jupiter.api.Test
	void testIDsMatch() {
	}

	@org.junit.jupiter.api.Test
	void testPasswordsMatch() {
	}

	@org.junit.jupiter.api.Test
	void testToString() {
	}
}