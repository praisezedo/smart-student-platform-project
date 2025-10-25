package Models;
// Course.java whic handles student course
import java.io.Serializable;
public class Course implements Serializable {
    private String courseId;
    private String courseName;
    private int credits;
    public Course(String courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }
    // Getters and setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    @Override
    public String toString() {
        return courseName + " (" + courseId + ") - " + credits + " credits";
    }
}