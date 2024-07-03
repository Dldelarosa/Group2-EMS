/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class HRStaff extends JFrame implements ActionListener{
    
    private JLabel lblEmployeeManagement;
    private JFrame f = new JFrame("HR Staff");
    private JButton btnEmployeeRecords, btnreq,  btnEmployeeRequest, btnAddEm, btnLogout;
    
   
    HRStaff(){
    setTitle("EMPLOYEE MANAGEMENT SYSTEM");    
    getContentPane().setBackground(Color.WHITE);
    setSize(800, 500);
    setLocation(380, 150);
    setLayout(null);
    setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    
    lblEmployeeManagement= new JLabel("Employee Managemant System",SwingConstants.CENTER);
    lblEmployeeManagement.setBounds(0, 50, 800, 40);
    lblEmployeeManagement.setFont(new Font("Arial Black", Font.PLAIN, 20));
            
    btnEmployeeRecords = new JButton("Employee Records");
    btnEmployeeRecords.setBounds(290, 120, 200, 40);
    btnEmployeeRecords.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnEmployeeRecords.addActionListener(this);
         
    btnEmployeeRequest = new JButton("Employee Request");
    btnEmployeeRequest.setBounds(290, 170, 200, 40);
    btnEmployeeRequest.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnEmployeeRequest.addActionListener(this);
    
    btnAddEm = new JButton("Add Employee");
    btnAddEm.setBounds(290,220, 200, 40);
    btnAddEm.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnAddEm.addActionListener(this);
    
    btnreq = new JButton("File Request");
    btnreq.setBounds(290,270, 200, 40);
    btnreq.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnreq.addActionListener(this);
    
    btnLogout = new JButton("Log out");
    btnLogout.setBounds(550,400, 200, 30);
    btnLogout.setBackground(Color.BLACK);
    btnLogout.setForeground(Color.WHITE);
    btnLogout.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnLogout.addActionListener(this);
    
    add(btnEmployeeRecords);
    add(btnEmployeeRequest);
    add(btnreq);
    add(lblEmployeeManagement);
    add(btnLogout);
    add(btnAddEm);
    
    setVisible(true);
    setResizable(false);
    
    
  }

    public void actionPerformed(ActionEvent ae){
        dispose();
        if (ae.getSource()== btnAddEm) {
            dispose();
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == btnEmployeeRecords) {
            dispose();
            
        } else if (ae.getSource() == btnEmployeeRequest) {
            dispose();
            
        } else if (ae.getSource() == btnreq) {
            dispose();
            new FileReq();
      } else {
            dispose();
            new LogIn();
//            new RemoveEmployee();
        }
    }
        }
     
