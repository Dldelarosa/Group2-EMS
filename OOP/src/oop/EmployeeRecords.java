/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;

/**
 *
 * @author admin
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author macaj
 */
public class EmployeeRecords extends JFrame {
    private final JTable employeeTable;
    private final JTextArea infoArea;
    private final JTextArea jobDescArea;
    private final JTextArea reviewArea;
    private final JTable requestTable;

    /**
     *
     */
    public EmployeeRecords() {
        setTitle("Employee Records Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();

        
        JPanel employeesPanel = new JPanel(new BorderLayout());
        employeeTable = new JTable(new DefaultTableModel(new Object[]{"ID", "Name", "Job Description", "Performance Review"}, 0));
        employeesPanel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoArea = new JTextArea("Employee Info");
        jobDescArea = new JTextArea("Job Description");
        reviewArea = new JTextArea("Performance Review");

        

        employeesPanel.add(infoPanel, BorderLayout.EAST);

        tabbedPane.addTab("Employees", employeesPanel);
        JPanel requestsPanel = new JPanel(new BorderLayout());
        requestTable = new JTable(new DefaultTableModel(new Object[]{"Request ID", "Employee", "Type", "Details"}, 0));
        requestsPanel.add(new JScrollPane(requestTable), BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();

        add(tabbedPane);

        loadDummyData();
    }

    private void loadDummyData() {
        DefaultTableModel employeeModel = (DefaultTableModel) employeeTable.getModel();
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
        employeeModel.addRow(new Object[]{"", ""});
    }
    }