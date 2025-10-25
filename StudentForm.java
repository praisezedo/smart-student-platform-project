// StudentForm.java which accept input from user
package GUI;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidParameterException;
import Models.*;
public class StudentForm extends JPanel {
    private StudentManager studentManager;
    private JTextField idField, nameField, emailField,cgpaField;
    private JButton addButton, updateButton, clearButton;
    public StudentForm(StudentManager studentManager) {
        this.studentManager = studentManager;
        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Create form fields
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(20);
        add(idField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("CGPA:"), gbc);
        gbc.gridx = 1;
        cgpaField = new JTextField(20);
        add(cgpaField, gbc);
        // Create buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Add Student");
        updateButton = new JButton("Update Student");
        clearButton = new JButton("Clear");
        addButton.addActionListener(event -> addStudent());
        updateButton.addActionListener(event -> updateStudent());
        clearButton.addActionListener(event -> clearForm());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton); 
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }
    private void addStudent() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            double cgpa = Double.parseDouble(cgpaField.getText().trim());
            Student student = new Student(id, name, email, cgpa);
            studentManager.addStudent(student);
            JOptionPane.showMessageDialog(this, "Student added successfully!", 
                                         "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for CGPA", 
                                         "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidParameterException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                                         "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateStudent() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            double cgpa = Double.parseDouble(cgpaField.getText().trim()); 
            Student updatedStudent = new Student(id, name, email,cgpa);
            studentManager.updateStudent(id, updatedStudent);
            JOptionPane.showMessageDialog(this, "Student updated successfully!", 
                                         "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for CGPA", 
                                         "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidParameterException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                                         "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        cgpaField.setText("");
    }
}