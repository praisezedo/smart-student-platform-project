//QuickSort.java which sorts the Object using name 
package Algorithms;
import java.util.List;
import Models.Student;
public class QuickSort {
public static void sortByName(List<Student> students) {
if (students == null || students.size() <= 1) return;
quickSortByName(students, 0, students.size() - 1);
}
private static void quickSortByName(List<Student> students, int low, int high) {
if (low < high) {
int pi = partitionByName(students, low, high);
quickSortByName(students, low, pi - 1);
quickSortByName(students, pi + 1, high);
}
}
private static int partitionByName(List<Student> students, int low, int high) {
String pivot = students.get(high).getName();
int i = low - 1;
for (int j = low; j < high; j++) {
if (students.get(j).getName().compareToIgnoreCase(pivot) < 0) {
i++;
swap(students, i, j);
}
}
swap(students, i + 1, high);
return i + 1;
}
private static void swap(List<Student> students, int i, int j) {
Student temp = students.get(i);
students.set(i, students.get(j));
students.set(j, temp);
}
}