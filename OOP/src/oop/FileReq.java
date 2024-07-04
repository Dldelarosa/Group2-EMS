/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;

/**
 *
 * @author admin
 */

import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileReq extends JFrame implements ActionListener{
    
    Random ran = new Random();
    int number = ran.nextInt(999999);
    
    JTextField tfname, tfdep, tfaddress, tfrea,  tfemail;
    JDateChooser dcdob;
    JLabel lblreqId;
    JButton add, back;
    JComboBox req;
    
    FileReq() {
        getContentPane().setBackground(new Color(206,235,251));
        setLayout(null);
        
        JLabel heading = new JLabel("File Request",SwingConstants.CENTER);
        heading.setBounds(0, 30, 900, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        JLabel labelname = new JLabel("Name:");
        labelname.setBounds(70, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        add(tfname);
              
        JLabel labeldob = new JLabel("Date:");
        labeldob.setBounds(70, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);
        
        dcdob = new JDateChooser();
        dcdob.setBounds(200, 200, 150, 30);
        add(dcdob);
        
        JLabel labeldep = new JLabel("Department:");
        labeldep.setBounds(470, 150, 150, 30);
        labeldep.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldep);
        
        tfdep = new JTextField();
        tfdep.setBounds(600, 150, 150, 30);
        add(tfdep);
        
        JLabel labelreq = new JLabel("Request:");
        labelreq.setBounds(470, 200, 150, 30);
        labelreq.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelreq);
        
        String courses[] = {"TIME OFF", "EXPENSES"};
        req = new JComboBox(courses);
        req.setBackground(Color.WHITE);
        req.setBounds(600, 200, 150, 30);
        add(req);
                      
        JLabel labelreason = new JLabel("Reason:");
        labelreason.setBounds(470, 250, 150, 30);
        labelreason.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelreason);
        
        tfrea = new JTextField();
        tfrea.setBounds(600, 250, 150, 30);
        add(tfrea);
        
        JLabel labelemail = new JLabel("Email:");
        labelemail.setBounds(70, 300, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);
        
        JLabel labelempId = new JLabel("Request ID:");
        labelempId.setBounds(70, 250, 150, 30);
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelempId);
        
        lblreqId = new JLabel("" + number);
        lblreqId.setBounds(200, 250, 150, 30);
        lblreqId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblreqId);
              
        add = new JButton("Submit");
        add.setBounds(280, 480, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.WHITE);
        add.setForeground(Color.BLACK);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(440, 480, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        add(back);
        
        setSize(900, 600);
        setLocation(350, 50);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText().trim();
            String dep = tfdep.getText().trim();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String request = (String) req.getSelectedItem();
            String reason = tfrea.getText().trim();
            String email = tfemail.getText().trim();
            String reqId = lblreqId.getText().trim();

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345")) {
                String sql = "INSERT INTO tbl_req (Request_ID, Name,  Date, Email,  Department, Request, Reason) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = con.prepareStatement(sql)) {
                    statement.setString(1, reqId);
                    statement.setString(2, name);
                    statement.setString(3, dob);
                    statement.setString(4, email);
                    statement.setString(5, dep);
                    statement.setString(6, request);
                    statement.setString(7, reason);
                    

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(this, "Employee added successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add employee", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (ae.getSource() == back) {
            dispose();
            new HRStaff();
        }
    }
            public static void main(String[] args) {
            new FileReq();
    }
}