//ResultForm.java which takes input from users
package GUI;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;
import Models.*;
public class ResultForm extends JPanel {
    private JTextField studentIdField, courseIdField, gradeField;
    private JButton addButton;
    private StudentManager studentManager;
    private ResultTable resultTable;
    public ResultForm(StudentManager studentManager, ResultTable resultTable) {
        this.studentManager = studentManager;
        this.resultTable = resultTable;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        studentIdField = new JTextField(10);
        courseIdField = new JTextField(10);
        gradeField = new JTextField(5);
        addButton = new JButton("Add Result");
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1; add(studentIdField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Course ID:"), gbc);
        gbc.gridx = 1; add(courseIdField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Grade:"), gbc);
        gbc.gridx = 1; add(gradeField, gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(addButton, gbc);
        addButton.addActionListener(e -> addResult());
    }
    private void addResult() {
        String studentId = studentIdField.getText().trim();
        String courseId = courseIdField.getText().trim();
        String gradeStr = gradeField.getText().trim();
        try {
            double grade = Double.parseDouble(gradeStr);
            studentManager.addResult(new Result(studentId, courseId, grade));
            JOptionPane.showMessageDialog(this, "Result added!");
            resultTable.refreshTable();
            studentIdField.setText(""); courseIdField.setText(""); gradeField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Grade must be a number.");
        } catch (InvalidParameterException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
