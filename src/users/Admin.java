package users;

import java.util.ArrayList;
import courses.Course;
import files.Intelligence;

public class Admin extends User {
	
    /**
     * Creates admin with the info shared by all users.
     * @param name admin's name
     * @param id admin's ID
     * @param userName admin's username
     * @param password admin's password
     */
    public Admin(String name, String id,
                 String userName, String password,Intelligence intel) {
        super(name, id, userName, password, intel);
    }

/**
 * Returns the type of user.
 * @return "admin"
 */
@Override
public String userType() {
    return "admin";
}
	
/**
 * Returns all courses in the system.
 * @return list of courses
 */
public ArrayList<Course> viewCourses() {
    return intel.getCourses();
}

/**
 * Returns all students in the system.
 * @return list of students
 */
public ArrayList<Student> viewStudents() {
    return intel.getStudent();
}

/**
 * Returns all professors in the system.
 * @return list of professors
 */
public ArrayList<Professor> viewProfessors() {
    return intel.getProfessor();
}


/**
 * Adds a new course if the course ID is unique, the professor exists, and there is no time conflict.
 * @param newCourse course to add
 * @return true if the course was added
 */
public boolean addCourse(Course newCourse) {

    if (newCourse == null) {
        return false;
    }

    // Check for duplicate course ID
    for (Course course : intel.getCourses()) {
        if (course.getCourseId().equals(newCourse.getCourseId())) {
            return false;
        }
    }

    // Check if the professor exists
    boolean professorExists = false;

    for (Professor professor : intel.getProfessor()) {
        if (professor.getName().equals(newCourse.getLecturer())) {
            professorExists = true;
            break;
        }
    }

    if (!professorExists) {
        return false;
    }

    // Check the professor's schedule
    for (Course course : intel.getCourses()) {
        if (course.getLecturer().equals(newCourse.getLecturer())
                && course.hasTimeConflict(newCourse)) {
            return false;
        }
    }

    intel.addCourse(newCourse);
    return true;
}

/**
 * Removes a course from the system if it exists and has no enrolled students.
 * @param courseId ID of the course to remove
 * @return true if the course was removed
 */
public boolean deleteCourse(String courseId) {

    if (courseId == null) {
        return false;
    }

    for (Course course : intel.getCourses()) {

        if (course.getCourseId().equals(courseId)) {

            if (!course.getEnrolledStudents().isEmpty()) {
                return false;
            }

            intel.removeCourse(course);
            return true;
        }
    }

    return false;
}

/**
 * Adds a student if the ID and username are not already used.
 * @param newStudent student to add
 * @return true if the student was added
 */
public boolean addStudent(Student newStudent) {

    if (newStudent == null) {
        return false;
    }

    // Check for duplicate student ID or username
    for (Student student : intel.getStudent()) {

        if (student.getID().equals(newStudent.getID())
                || student.getUserName().equals(newStudent.getUserName())) {
            return false;
        }
    }

    // Add the student to the system
    intel.addStudent(newStudent);
    return true;
}

/**
 * Removes a student from all courses and the system.
 * @param studentId ID of the student to delete
 * @return true if the student was deleted
 */
public boolean deleteStudent(String studentId) {

    if (studentId == null) {
        return false;
    }

    for (Student student : intel.getStudent()) {

        if (student.getID().equals(studentId)) {

            // Remove student from all courses
            for (Course course : intel.getCourses()) {
                course.removeStudent(student);
            }

            // Remove student from the system
            intel.removeStudent(student);
            return true;
        }
    }

    return false;
}

/**
 * Adds a professor if the ID and username are not already used.
 * @param newProfessor professor to add
 * @return true if the professor was added
 */
public boolean addProfessor(Professor newProfessor) {

    if (newProfessor == null) {
        return false;
    }

    // Check for duplicate professor ID or username
    for (Professor professor : intel.getProfessor()) {

        if (professor.getID().equals(newProfessor.getID())
                || professor.getUserName().equals(newProfessor.getUserName())) {
            return false;
        }
    }

    // Add the professor to the system
    intel.addProfessor(newProfessor);
    return true;
}

/**
 * Deletes a professor if they are not teaching any courses.
 * @param professorId ID of the professor to delete
 * @return true if the professor was deleted
 */
public boolean deleteProfessor(String professorId) {

    if (professorId == null) {
        return false;
    }

    for (Professor professor : intel.getProfessor()) {

        if (professor.getID().equals(professorId)) {

            // Check if the professor is teaching any courses
            for (Course course : intel.getCourses()) {
                if (course.getLecturer().equals(professor.getName())) {
                    return false;
                }
            }

            // Remove the professor from the system
            intel.removeProfessor(professor);
            return true;
        }
    }

    return false;
	}
}
