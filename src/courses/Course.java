package courses;

import java.util.ArrayList;
import users.Student;

/**
 * Represents one course and its enrolled students.
 */
public class Course {

    /** Unique course ID, such as CIT590. */
    private String courseId;
    /** Course title. */
    private String courseName;
    /** Full name of the course lecturer. */
    private String lecturer;
    /** Meeting days, such as MW, TR, or MWF. */
    private String days;
    /** Starting time in HH:mm format. */
    private String startTime;
    /** Ending time in HH:mm format. */
    private String endTime;
    /** Maximum amounts of enrolled students. */
    private int capacity;
    /** Students currently enrolled in this course. */
    private ArrayList<Student> students;

    /**
     * Creates a course from the seven fields in courseInfo.txt.
     * @param courseId course ID
     * @param courseName course name
     * @param lecturer lecturer's full name
     * @param days meeting days
     * @param startTime start time in HH:mm format
     * @param endTime end time in HH:mm format
     * @param capacity maximum student capacity
     */
    public Course(String courseId, String courseName, String lecturer,
                  String days, String startTime, String endTime, int capacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.students = new ArrayList<Student>();
    }

    /** @return course ID */
    public String getCourseId() { return courseId; }

    /** @return course name */
    public String getCourseName() { return courseName; }

    /** @return lecturer's full name */
    public String getLecturer() { return lecturer; }

    /** @return meeting days */
    public String getDays() { return days; }

    /** @return course start time */
    public String getStartTime() { return startTime; }

    /** @return course end time */
    public String getEndTime() { return endTime; }

    /** @return maximum capacity */
    public int getCapacity() { return capacity; }

    /** @return a copy of the enrolled student list */
    public ArrayList<Student> getEnrolledStudents() {
        return new ArrayList<Student>(students);
    }

    /**
     * Checks whether this course overlaps another course.
     * Courses that end exactly when another begins do not conflict.
     * @param otherCourse course to compare
     * @return true if a meeting day and time overlap
     */
    public boolean hasTimeConflict(Course otherCourse) {
        if (otherCourse == null) {
            return false;
        }

        boolean sameDay = false;
        for (int i = 0; i < days.length(); i++) {
            if (otherCourse.days.indexOf(days.charAt(i)) >= 0) {
                sameDay = true;
                break;
            }
        }
        if (!sameDay) {
            return false;
        }

        int start1 = timeToMinutes(startTime);
        int end1 = timeToMinutes(endTime);
        int start2 = timeToMinutes(otherCourse.startTime);
        int end2 = timeToMinutes(otherCourse.endTime);
        return start1 < end2 && start2 < end1;
    }

    /**
     * Converts a 24-hour time to minutes after midnight.
     * @param time time in HH:mm format
     * @return time as an integer number of minutes
     */
    private int timeToMinutes(String time) {
        String[] parts = time.trim().split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    /**
     * Adds a student if not already enrolled and the class is not full.
     * @param student student to enroll
     * @return whether the student was added
     */
    public boolean addStudent(Student student) {
        if (student == null || students.size() >= capacity) {
            return false;
        }
        for (Student existing : students) {
            if (existing.getID().equals(student.getID())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }

    /**
     * Removes a currently enrolled student.
     * @param student student to remove
     * @return whether the student was removed
     */
    public boolean removeStudent(Student student) {
        if (student == null) {
            return false;
        }
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getID().equals(student.getID())) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    /** @return one formatted line of course information */
    @Override
    public String toString() {
        return courseId + "; " + courseName + "; " + lecturer + "; "
                + days + "; " + startTime + "; " + endTime + "; " + capacity;
    }
}
