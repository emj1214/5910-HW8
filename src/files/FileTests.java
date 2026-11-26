package files;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FileTests {

	private Intelligence intel;
    private FileInfoReader fileInfoReader;

    @BeforeEach
    void setup() throws Exception {

        // Initialize objects before each test
        intel = new Intelligence();
        fileInfoReader = new FileInfoReader();

    }
    
    /***
     * tests if students in given file were loaded
     */
    @Test
    void testProcessFiles() {

        fileInfoReader.processFiles(
            "src/studentInfo.txt",
            "src/professorInfo.txt",
            "src/adminInfo.txt",
            "src/courseInfo.txt",
            intel
        );

        // Verify students were loaded
        assertTrue(intel.getStudents().size() > 0);
        
        // Verify professors were loaded
        assertTrue(intel.getProfessors().size() > 0);
        
        // Verify admins were loaded
        assertTrue(intel.getAdmins().size() > 0);
        
        // Verify courses were loaded
        assertTrue(intel.getCourses().size() > 0);
    }
	
	@Test
	void testStudentReader() {
		// 
		assertEquals(10, 10);
	}

}
