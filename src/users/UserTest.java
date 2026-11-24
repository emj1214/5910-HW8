package users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	Student testStudent;

	Professor testProfessor;

	Admin testAdmin;


	@BeforeEach
	void setUp() throws Exception {

	testStudent = new Student("Hammans Stallings", "101", "HStallings",
					"456123");
	testProfessor = new Professor("Emma Johnson", "102", "EJohnson",
					"DEFABC");
	testAdmin = new Admin("Yongling Wu", "103", "YWu", "OPQLMN");
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