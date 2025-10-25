// SummaryPanel.java which displays the number of student , average cgpa and the student with the high cgpa
package GUI;
import javax.swing.*;
import java.awt.*;
import Models.*;
public class SummaryPanel extends JPanel {
    private StudentManager studentManager;
    private JLabel totalStudentsLabel, averageCgpaLabel, topPerformerLabel;
    public SummaryPanel(StudentManager studentManager) {
        this.studentManager = studentManager;
        initializeUI();
        refreshSummary();
    }
    private void initializeUI() {
        setLayout(new GridLayout(3, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        totalStudentsLabel = new JLabel("Total Students: ", JLabel.CENTER);
        averageCgpaLabel = new JLabel("Average CGPA: ", JLabel.CENTER);
        topPerformerLabel = new JLabel("Top Performer: ", JLabel.CENTER);
        totalStudentsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        averageCgpaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPerformerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(totalStudentsLabel);
        add(averageCgpaLabel);
        add(topPerformerLabel);
    }
    public void refreshSummary() {
        int totalStudents = studentManager.getAllStudents().size();
        double averageCgpa = studentManager.calculateClassAverage();
        Student topPerformer = studentManager.getTopPerformer();
        totalStudentsLabel.setText("Total Students: " + totalStudents);
        averageCgpaLabel.setText(String.format("Average CGPA: %.2f", averageCgpa));
        if (topPerformer != null) {
            topPerformerLabel.setText(String.format("Top Performer: %s (ID: %s, CGPA: %.2f)", 
                topPerformer.getName(), topPerformer.getId(), topPerformer.getCgpa()));
        } else {
            topPerformerLabel.setText("Top Performer: No students available");
        }
    }
}