// StudentManager.java which manages students data.
package Models;
import java.util.*;
import java.io.*;
import java.security.InvalidParameterException;
import Algorithms.*;
import java.util.Collections;
public class StudentManager {
private List<Student> students;
private Map<String, Course> courses;
private List<Result> results;
public StudentManager() {
students = new ArrayList<>();
courses = new HashMap<>();
results = new ArrayList<>();
}
public void addStudent(Student student) throws InvalidParameterException {
if (student.getId() == null || student.getId().trim().isEmpty()) {
throw new InvalidParameterException("Student ID cannot be empty");
}
if (student.getName() == null || student.getName().trim().isEmpty()) {
throw new InvalidParameterException("Student name cannot be empty");
}
if (student.getCgpa() < 0 || student.getCgpa() > 4.0) {
throw new InvalidParameterException("CGPA must be between 0 and 4.0");
}
students.add(student);
}
public void updateStudent(String id, Student updatedStudent) throws InvalidParameterException {
Student student = getStudentById(id);
if (student == null) {
throw new InvalidParameterException("Student not found with ID: " + id);
}
if (updatedStudent.getName() != null && !updatedStudent.getName().trim().isEmpty()) {
student.setName(updatedStudent.getName());
}
if (updatedStudent.getEmail() != null) {
student.setEmail(updatedStudent.getEmail());
}
if (updatedStudent.getCgpa() >= 0 && updatedStudent.getCgpa() <= 4.0) {
student.setCgpa(updatedStudent.getCgpa());
} else {
throw new InvalidParameterException("CGPA must be between 0 and 4.0");
}
}
public Student getStudentById(String id) {
for (Student student : students) {
if (student.getId().equals(id)) {
    return student;
}
}
return null;
}
public List<Student> getAllStudents() {
return new ArrayList<>(students);
}
public void sortStudentsByName() {
QuickSort.sortByName(students);
}
public void sortStudentsByCGPA() {
BubbleSort.sortByCGPA(students);
}
public void sortStudentsById() {
Collections.sort(students);
}
public void addCourse(Course course) {
courses.put(course.getCourseId(), course);
}
public Course getCourse(String courseId) {
return courses.get(courseId);
}
public List<Course> getAllCourses() {
return new ArrayList<>(courses.values());
}
public void addResult(Result result) {
results.add(result);
}
public List<Result> getResultsForStudent(String studentId) {
List<Result> studentResults = new ArrayList<>();
for (Result result : results) {
if (studentId != null && studentId.equals(result.getStudentId())) {
    studentResults.add(result);
}
}
return studentResults;
}
public List<Result> getAllResults() {
return new ArrayList<>(results);
}
public void sortCoursesById() {
List<Course> courseList = getAllCourses();
courseList.sort((c1, c2) -> c1.getCourseId().compareTo(c2.getCourseId()));
courses.clear();
for (Course c : courseList) {
courses.put(c.getCourseId(), c);
}
}
public void sortResultsByStudentId() {
results.sort((r1, r2) -> r1.getStudentId().compareTo(r2.getStudentId()));
}
public double calculateClassAverage() {
if (students.isEmpty())
return 0;
double total = 0;
for (Student student : students) {
total += student.getCgpa();
}
return total / students.size();
}
public Student getTopPerformer() {
if (students.isEmpty())
return null;
Student topStudent = students.get(0);
for (Student student : students) {
if (student.getCgpa() > topStudent.getCgpa()) {
    topStudent = student;
}
}
return topStudent;
}
public void saveToFile(String filename) throws IOException {
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
oos.writeObject(students);
oos.writeObject(courses);
oos.writeObject(results);
}
}
@SuppressWarnings("unchecked")
public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
students = (List<Student>) ois.readObject();
courses = (Map<String, Course>) ois.readObject();
results = (List<Result>) ois.readObject();
}
}
}
