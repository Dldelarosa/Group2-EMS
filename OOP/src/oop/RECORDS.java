package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Color;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.awt.event.*;
import javax.swing.*;

public class RECORDS extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton loadButton, editButton, deleteButton, backButton;

    RECORDS() {
        setSize(800, 600);  // Increased width to accommodate buttons on the right side
        setTitle("Viewing of Records");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);

        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 40, 740, 450);  // Adjusted height to fill the space
        add(scrollPane);

        // Add columns to the table model
        model.addColumn("Name");
        model.addColumn("Employee ID");
        model.addColumn("Date of birth");
        model.addColumn("Address");
        model.addColumn("Email");
        model.addColumn("Department");
        model.addColumn("Salary");
        model.addColumn("Contact No.");
        model.addColumn("Performance Review");

        // Fetch records from database and populate the table
        fetchAndDisplayRecords(model);

        // Create buttons
        loadButton = new JButton("Load");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");

        // Set bounds for buttons
        int buttonWidth = 100;
        int buttonHeight = 30;
        int yPosition = 500;
        int spacing = 20;  // Spacing between buttons
        int totalButtonWidth = (buttonWidth + spacing) * 4 - spacing;
        int xPosition = (getWidth() - totalButtonWidth) / 2;

        loadButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
        editButton.setBounds(xPosition + (buttonWidth + spacing), yPosition, buttonWidth, buttonHeight);
        deleteButton.setBounds(xPosition + 2 * (buttonWidth + spacing), yPosition, buttonWidth, buttonHeight);
        backButton.setBounds(xPosition + 3 * (buttonWidth + spacing), yPosition, buttonWidth, buttonHeight);

        // Add buttons to the frame
        add(loadButton);
        add(editButton);
        add(deleteButton);
        add(backButton);

        // Add action listeners to buttons
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for load button
                fetchAndDisplayRecords(model);
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for edit button
                // Edit record logic here
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for delete button
                // Delete record logic here
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action for back button
                // Back button logic here
            }
        });

        setVisible(true);
    }

    private void fetchAndDisplayRecords(DefaultTableModel model) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345");

            // Create and execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tbl_employee_detail";
            rs = stmt.executeQuery(sql);

            // Process the result set
            while (rs.next()) {
                String username = rs.getString("username");
                String age = rs.getString("age");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String contactnum = rs.getString("contactnum");

                // Add row to table model
                model.addRow(new Object[]{username, age, gender, address, contactnum});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new RECORDS();
    }
}
