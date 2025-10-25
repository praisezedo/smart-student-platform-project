//Courseorm.java for accepting users input
package GUI;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;
import Models.*;
public class CourseForm extends JPanel {
    private JTextField idField, nameField, creditsField;
    private JButton addButton;
    private StudentManager studentManager;
    private CourseTable courseTable;
    public CourseForm(StudentManager studentManager, CourseTable courseTable) {
        this.studentManager = studentManager;
        this.courseTable = courseTable;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        idField = new JTextField(10);
        nameField = new JTextField(15);
        creditsField = new JTextField(5);
        addButton = new JButton("Add Course");
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Course ID:"), gbc);
        gbc.gridx = 1; add(idField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Course Name:"), gbc);
        gbc.gridx = 1; add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Credits:"), gbc);
        gbc.gridx = 1; add(creditsField, gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(addButton, gbc);
        addButton.addActionListener(e -> addCourse());
    }
    private void addCourse() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String creditsStr = creditsField.getText().trim();
        try {
            int credits = Integer.parseInt(creditsStr);
            studentManager.addCourse(new Course(id, name, credits));
            JOptionPane.showMessageDialog(this, "Course added!");
            courseTable.refreshTable();
            idField.setText(""); nameField.setText(""); creditsField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Credits must be a number.");
        } catch (InvalidParameterException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
