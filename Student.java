package Models;
import java.io.Serializable;
public class Student implements Serializable , Comparable<Student>{
    private String id;
    private String name;
    private String email;
    private double cgpa;
    public Student(String id, String name, String email, double cgpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cgpa = cgpa;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getCgpa() { return cgpa; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
      @Override
    public int compareTo(Student other) {
        return this.id.compareTo(other.id);
    }
}