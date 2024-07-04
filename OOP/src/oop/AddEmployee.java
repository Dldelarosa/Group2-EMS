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
import java.sql.*;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tfname, tfdep, tfaddress, tfphone,  tfemail, tfsalary;
    JDateChooser dcdob;
    JLabel lblempId;
    JButton add, back;

    AddEmployee() {
        getContentPane().setBackground(new Color(206,235,251));
        setLayout(null);

        JLabel heading = new JLabel("ADD EMPLOYEE", SwingConstants.CENTER);
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

        JLabel labeldob = new JLabel("Date of Birth:");
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

        JLabel labelsalary = new JLabel("Salary:");
        labelsalary.setBounds(470, 200, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(600, 200, 150, 30);
        add(tfsalary);

        JLabel labeladdress = new JLabel("Address:");
        labeladdress.setBounds(70, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Contact:");
        labelphone.setBounds(470, 250, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);

        JLabel labelemail = new JLabel("Email:");
        labelemail.setBounds(70, 300, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel labelempId = new JLabel("Employee ID:");
        labelempId.setBounds(470, 300, 150, 30);
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelempId);

        lblempId = new JLabel("" + number);
        lblempId.setBounds(650, 300, 150, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblempId);

        add = new JButton("Add Details");
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
            String salary = tfsalary.getText().trim();
            String address = tfaddress.getText().trim();
            String Contact = tfphone.getText().trim();
            String email = tfemail.getText().trim();
            String empId = lblempId.getText().trim();

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345")) {
                String sql = "INSERT INTO tbl_employee_detail (Name, Employee_ID, Date_of_Birth, Address, Email, Department, Contact, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = con.prepareStatement(sql)) {
                    statement.setString(1, name);
                    statement.setString(2, empId);
                    statement.setString(3, dob);
                    statement.setString(4, address);
                    statement.setString(5, email);
                    statement.setString(6, dep);
                    statement.setString(7, Contact);
                    statement.setString(8, salary);

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
        new AddEmployee();
    }
}