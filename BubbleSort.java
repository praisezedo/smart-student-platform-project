//BubbleSort.java which sorts the Object using cgpa
package Algorithms;
import java.util.List;
import Models.Student;
public class BubbleSort {
    public static void sortByCGPA(List<Student> students) {
        if (students == null || students.size() <= 1) return;
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getCgpa() < students.get(j + 1).getCgpa()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
}