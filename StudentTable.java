// StudentTable.java to Display student GUI
package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Models.*;
import Algorithms.SearchAlgorithm;
public class StudentTable extends JPanel {
private StudentManager studentManager;
private JTable studentTable;
private DefaultTableModel tableModel;
private JTextField searchField;
private JButton searchButton, sortByNameButton, sortByIdButton, sortByCgpaButton;
public StudentTable(StudentManager studentManager) {this.studentManager = studentManager; initializeUI();refreshTable(); }
private void initializeUI() {setLayout(new BorderLayout());
// Create table model
String[] columnNames = {"ID", "Name", "Email","CGPA"};
tableModel = new DefaultTableModel(columnNames, 0) { @Override
public boolean isCellEditable(int row, int column) { return false;}};
studentTable = new JTable(tableModel);
JScrollPane scrollPane = new JScrollPane(studentTable);
JPanel controlPanel = new JPanel(new FlowLayout());
searchField = new JTextField(15);
searchButton = new JButton("Search by ID");
sortByNameButton = new JButton("Sort by Name");
sortByIdButton = new JButton("Sort by ID");
sortByCgpaButton = new JButton("Sort by CGPA");
controlPanel.add(new JLabel("Search:"));
controlPanel.add(searchField);
controlPanel.add(searchButton);
controlPanel.add(sortByNameButton);
controlPanel.add(sortByIdButton);
controlPanel.add(sortByCgpaButton);
// Add action listeners to the Buttons
searchButton.addActionListener(e -> searchStudent());
sortByNameButton.addActionListener(e -> sortStudentsByName());
sortByIdButton.addActionListener(e -> sortStudentsById());
sortByCgpaButton.addActionListener(e -> sortStudentsByCGPA());add(controlPanel, BorderLayout.NORTH);add(scrollPane, BorderLayout.CENTER); }
public void refreshTable() {tableModel.setRowCount(0);   List<Student> students = studentManager.getAllStudents();
for (Student student : students) {Object[] rowData = {
    student.getId(),
    student.getName(),
    student.getEmail(),
    student.getCgpa() };
tableModel.addRow(rowData); }}
private void searchStudent() {
String id = searchField.getText().trim();
if (id.isEmpty()) {
JOptionPane.showMessageDialog(this, "Please enter an ID to search",  "Input Error", JOptionPane.ERROR_MESSAGE);return;}
Student student = SearchAlgorithm.linearSearchById(studentManager.getAllStudents(), id);
if (student != null) {
// Select the row with the found student
for (int i = 0; i < tableModel.getRowCount(); i++) {
    if (tableModel.getValueAt(i, 0).equals(id)) { studentTable.setRowSelectionInterval(i, i);
        studentTable.scrollRectToVisible(studentTable.getCellRect(i, 0, true));break;}}
} else {
JOptionPane.showMessageDialog(this, "Student with ID " + id + " not found",  "Not Found", JOptionPane.INFORMATION_MESSAGE);}}
private void sortStudentsByName() {studentManager.sortStudentsByName();refreshTable();}
private void sortStudentsById() {studentManager.sortStudentsById();refreshTable();}
private void sortStudentsByCGPA() {studentManager.sortStudentsByCGPA();refreshTable();}}