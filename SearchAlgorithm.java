// SearchAlgorithm.java which searchs object using id
package Algorithms;
import java.util.List;
import Models.Student;
public class SearchAlgorithm {
    public static Student linearSearchById(List<Student> students, String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
    public static Student binarySearchById(List<Student> students, String id) {
        int left = 0;
        int right = students.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Student midStudent = students.get(mid);
            int comparison = midStudent.getId().compareTo(id);
            if (comparison == 0) {
                return midStudent;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}