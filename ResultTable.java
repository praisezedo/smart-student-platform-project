//ResultTable.java whic display the result of student
package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Models.*;
public class ResultTable extends JPanel {
    private StudentManager studentManager;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JButton sortByIdButton;
    public ResultTable(StudentManager studentManager) {
        this.studentManager = studentManager;
        initializeUI();
        refreshTable();
    }
    private void initializeUI() {
        setLayout(new BorderLayout());
        String[] columnNames = {"Student ID", "Course ID", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        sortByIdButton = new JButton("Sort by Student ID");
        sortByIdButton.addActionListener(e -> {
            studentManager.sortResultsByStudentId();
            refreshTable();
        });
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(sortByIdButton);
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Result> results = studentManager.getAllResults();
        for (Result result : results) {
            Object[] rowData = {
                result.getStudentId(),
                result.getCourseId(),
                result.getGrade()
            };
            tableModel.addRow(rowData);
        }
    }
}
