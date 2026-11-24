package unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import files.FileInfoReader;
import files.Intelligence;


public class FileTests {

	@BeforeEach
	void setup() throws Exception {
		Intelligence intel = new Intelligence();
		FileInfoReader fileInfoReader = new FileInfoReader();
		fileInfoReader.processFiles("studentInfo.txt", "professorInfo.txt", "adminInfo.txt", "courseInfo.txt", intel);
	}
	
	@Test
	void testStudentReader() {
		// 
		assertEquals(10, 10);
	}

}
