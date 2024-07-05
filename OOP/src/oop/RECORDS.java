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
        setSize(800, 600);
        setTitle("Viewing of Records");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
 
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 40, 740, 450);
        add(scrollPane);

        model.addColumn("Name");
        model.addColumn("Employee ID");
        model.addColumn("Date of birth");
        model.addColumn("Address");
        model.addColumn("Email");
        model.addColumn("Department");
        model.addColumn("Salary");
        model.addColumn("Contact No.");
        model.addColumn("Performance Review");

        fetchAndDisplayRecords(model);
        
        loadButton = new JButton("Load");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");
   
        int buttonWidth = 100;
        int buttonHeight = 30;
        int yPosition = 500;
        int spacing = 20;
        int totalButtonWidth = (buttonWidth + spacing) * 4 - spacing;
        int xPosition = (getWidth() - totalButtonWidth) / 2;

        loadButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
        editButton.setBounds(xPosition + (buttonWidth + spacing), yPosition, buttonWidth, buttonHeight);
        deleteButton.setBounds(xPosition + 2 * (buttonWidth + spacing), yPosition, buttonWidth, buttonHeight);
        backButton.setBounds(xPosition + 3 * (buttonWidth + spacing), yPosition, buttonWidth, buttonHeight);


        add(loadButton);
        add(editButton);
        add(deleteButton);
        add(backButton);


        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                fetchAndDisplayRecords(model);
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
            }
        });

        setVisible(true);
    }

    private void fetchAndDisplayRecords(DefaultTableModel model) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
       
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345");

           
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tbl_employee_detail";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                String age = rs.getString("age");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String contactnum = rs.getString("contactnum");

                model.addRow(new Object[]{username, age, gender, address, contactnum});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
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
