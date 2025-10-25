// MainFrame.java which renders the GUI
package GUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.InvalidParameterException;
import Models.*;
public class MainFrame extends JFrame {
    private StudentManager studentManager;
    private JTabbedPane tabbedPane;
    private StudentForm studentForm;
    private StudentTable studentTable;
    private SummaryPanel summaryPanel;
    private CourseTable courseTable;
    private ResultTable resultTable;
    private CourseForm courseForm;
    private ResultForm resultForm;
    public MainFrame() {
        studentManager = new StudentManager();
        setTitle("Smart Student Platform");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set background color
        getContentPane().setBackground(Color.WHITE);
        initializeUI();
        loadSampleData();
    }
    private void initializeUI() {
        setTitle("Smart Student Platform");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadItem = new JMenuItem("Load Data");
        JMenuItem saveItem = new JMenuItem("Save Data");
        JMenuItem exitItem = new JMenuItem("Exit");
        loadItem.addActionListener(e -> loadData());
        saveItem.addActionListener(e -> saveData());
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        studentForm = new StudentForm(studentManager);
        studentTable = new StudentTable(studentManager);
        summaryPanel = new SummaryPanel(studentManager);
        courseTable = new CourseTable(studentManager);
        resultTable = new ResultTable(studentManager);
        courseForm = new CourseForm(studentManager, courseTable);
        resultForm = new ResultForm(studentManager, resultTable);
        tabbedPane.addTab("Add Student", studentForm);
        tabbedPane.addTab("View Students", studentTable);
        tabbedPane.addTab("Add Course", courseForm);      
        tabbedPane.addTab("Courses", courseTable);
        tabbedPane.addTab("Add Result", resultForm);     
        tabbedPane.addTab("Results", resultTable);
        tabbedPane.addTab("Summary", summaryPanel);
        add(tabbedPane);
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedComponent() == studentTable) {
                studentTable.refreshTable();
            } else if (tabbedPane.getSelectedComponent() == courseTable) {
                courseTable.refreshTable();
            } else if (tabbedPane.getSelectedComponent() == resultTable) {
                resultTable.refreshTable();
            } else if (tabbedPane.getSelectedComponent() == summaryPanel) {
                summaryPanel.refreshSummary();
            }
        });
    }
    private void loadSampleData() {
        // Add some sample data for testing
        try {
            studentManager.addStudent(new Student("1", "John", "john@example.com",  3.5));
            studentManager.addStudent(new Student("2", "Jane", "jane@example.com", 3.8));
            studentManager.addStudent(new Student("3", "Bob", "bob@example.com", 3.2)); 
            studentManager.addCourse(new Course("001", "Mathematics", 3));
            studentManager.addCourse(new Course("002", "Computer Science", 4));
            studentManager.addCourse(new Course("003", "Physics", 3));
            studentManager.addResult(new Result("1", "1", 3.7));
            studentManager.addResult(new Result("1", "2", 3.3));
            studentManager.addResult(new Result("2", "1", 4.0));
        } catch (InvalidParameterException e) {
            JOptionPane.showMessageDialog(this, "Error loading sample data: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadData() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                studentManager.loadFromFile(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Data loaded successfully!", 
                                             "Success", JOptionPane.INFORMATION_MESSAGE);
                studentTable.refreshTable();
                summaryPanel.refreshSummary();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), 
                                             "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void saveData() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                studentManager.saveToFile(fileChooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Data saved successfully!", 
                                             "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), 
                                             "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}