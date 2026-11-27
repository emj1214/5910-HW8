package files;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FileTests {

	private Intelligence intel;
    private FileInfoReader fileInfoReader;
    private FileWriter fw;
    private BufferedWriter bw;

    @BeforeEach
    void setup() throws Exception {

        // Initialize objects before each test
        intel = new Intelligence();
        fileInfoReader = new FileInfoReader();

    }
    
    /***
     * tests if given files are loaded as expected
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
    
    // helper to create test files
    void testFileHleper(String studentTest, String professorTest, String adminTest, String courseTest) {
    	
    	// overwrite test file
    	try {
    		// write student file
    		fw = new FileWriter(new File("src/files/testStudent.txt"), false);
        	bw = new BufferedWriter(fw);
        	
        	// student test case to write
        	bw.write(studentTest);
        	
        	// close writers
        	bw.close();
        	
        	// write prof file
    		fw = new FileWriter(new File("src/files/testProfessor.txt"), false);
        	bw = new BufferedWriter(fw);
        	
        	// prof test case to write
        	bw.write(professorTest);
        	
        	// close writers
        	bw.close();
        	
        	// write admin file
    		fw = new FileWriter(new File("src/files/testAdmin.txt"), false);
        	bw = new BufferedWriter(fw);
        	
        	// admin test case to write
        	bw.write(adminTest);
        	
        	// close writers
        	bw.close();
        	
        	// write course file
    		fw = new FileWriter(new File("src/files/testCourse.txt"), false);
        	bw = new BufferedWriter(fw);
        	
        	// student test case to write
        	bw.write(courseTest);
        	
        	// close writers
        	bw.close();
        	
    	}
    	catch (IOException e) {
    		System.out.println("error creating test file\n");
    	}
    	
    }
	
	@Test
	void testEmptyFiles() {
		// create test files
		testFileHleper("", "", "", "");
		
		// process files
		fileInfoReader.processFiles(
	            "src/files/testStudent.txt",
	            "src/files/testProfessor.txt",
	            "src/files/testAdmin.txt",
	            "src/files/testCourse.txt",
	            intel
	        );
		
		// Verify no students were loaded
		assertEquals(0, intel.getStudents().size());
        
        // Verify no professors were loaded
        assertEquals(0, intel.getProfessors().size());
        
        // Verify no admins were loaded
        assertEquals(0, intel.getAdmins().size());
        
        // Verify no courses were loaded
        assertEquals(0, intel.getCourses().size());
	}
	
	// tests a regular inputs
	@Test
	void testExpectedInputs() {
		// create test files
		testFileHleper("123; student name; username; password; course ID: A"
				, "prof ID; prof name; username; password"
				, "admin ID; admin name; username; password"
				, "course ID; course name; lecturer; days; start time; end time; 1 \n");
		
		// process files
		fileInfoReader.processFiles(
	            "src/files/testStudent.txt",
	            "src/files/testProfessor.txt",
	            "src/files/testAdmin.txt",
	            "src/files/testCourse.txt",
	            intel
	        );
		
        
        // Verify one student was loaded
		assertEquals(1, intel.getStudents().size());
        
		// Verify one professors was loaded
        assertEquals(1, intel.getProfessors().size());
        
        // Verify one admins was loaded
        assertEquals(1, intel.getAdmins().size());
        
        // Verify one courses was loaded
        assertEquals(1, intel.getCourses().size());
        
	}
	
	
	// tests spacey inputs
		@Test
		void testSpaceyInputs() {
			// create test files
			testFileHleper("     123; student name     ; username;     password;     course ID   : A"
					, "     prof ID;     prof name;     username; password    "
					, "     admin ID;     admin name; username    ; password    "
					, "    course ID;    course name; lecturer    ; days;     start time;     end time;    1 \n");
			
			// process files
			fileInfoReader.processFiles(
		            "src/files/testStudent.txt",
		            "src/files/testProfessor.txt",
		            "src/files/testAdmin.txt",
		            "src/files/testCourse.txt",
		            intel
		        );
			
	        
	        // Verify one student was loaded
			assertEquals(1, intel.getStudents().size());
	        
			// Verify one professors was loaded
	        assertEquals(1, intel.getProfessors().size());
	        
	        // Verify one admins was loaded
	        assertEquals(1, intel.getAdmins().size());
	        
	        // Verify one courses was loaded
	        assertEquals(1, intel.getCourses().size());
	        
		}
		
		
		// tests missing inputs (objects will not be created, and error messages will print)
		@Test
		void testMissingInputs() {
			// create test files
			testFileHleper("one"
					, "one, two"
					, "one, two, three"
					, "one, two, three, four");
			
			// process files
			fileInfoReader.processFiles(
		            "src/files/testStudent.txt",
		            "src/files/testProfessor.txt",
		            "src/files/testAdmin.txt",
		            "src/files/testCourse.txt",
		            intel
		        );
			
	        
	        // Verify one student was loaded
			assertEquals(0, intel.getStudents().size());
	        
			// Verify one professors was loaded
	        assertEquals(0, intel.getProfessors().size());
	        
	        // Verify one admins was loaded
	        assertEquals(0, intel.getAdmins().size());
	        
	        // Verify one courses was loaded
	        assertEquals(0, intel.getCourses().size());
	        
		}
}
