//CourseTable.java for displaying student courses.
package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Models.*;
public class CourseTable extends JPanel {
    private StudentManager studentManager;
    private JTable courseTable;
    private DefaultTableModel tableModel;
    private JButton sortByIdButton;
    public CourseTable(StudentManager studentManager) {
        this.studentManager = studentManager;
        initializeUI();
        refreshTable();
    }
    private void initializeUI() {
        setLayout(new BorderLayout());
        String[] columnNames = {"Course ID", "Course Name", "Credits"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        courseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        sortByIdButton = new JButton("Sort by ID");
        sortByIdButton.addActionListener(e -> {
            studentManager.sortCoursesById();
            refreshTable();
        });
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(sortByIdButton);
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Course> courses = studentManager.getAllCourses();
        for (Course course : courses) {
            Object[] rowData = {
                course.getCourseId(),
                course.getCourseName(),
                course.getCredits()
            };
            tableModel.addRow(rowData);
        }
    }
}
