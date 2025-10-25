package Models;
// Result.java which handles student result
import java.io.Serializable;
public class Result implements Serializable {
    private String studentId;
    private String courseId;
    private double grade;
    public Result(String studentId, String courseId, double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }
    // Getters and setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
    @Override
    public String toString() {
        return "Student: " + studentId + ", Course: " + courseId + ", Grade: " + grade;
    }
}

