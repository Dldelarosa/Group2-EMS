/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop;




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class HRStaff extends JFrame implements ActionListener{
    
    private JLabel lblEmployeeManagement, HRStaff;
    private JFrame f = new JFrame("HR Staff");
    private JButton btnEmployeeRecords, btnreq,  btnEmployeeRequest, btnAddEm, btnLogout;
    
   
    HRStaff(){
    setTitle("EMPLOYEE MANAGEMENT SYSTEM");    
    getContentPane().setBackground(Color.WHITE);
    setSize(800, 500);
    setLocation(380, 150);
    setLayout(null);
    setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    
        HRStaff = new JLabel();
        HRStaff.setIcon(new ImageIcon(new ImageIcon("D:\\Documents\\NetBeansProjects\\Group2-EMS\\OOP\\src\\oop\\HRStaff.jpg").getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH)));
        HRStaff.setBounds(0, 0, 800, 500);
    
    lblEmployeeManagement= new JLabel("Employee Managemant System",SwingConstants.CENTER);
    lblEmployeeManagement.setBounds(0, 50, 800, 40);
    lblEmployeeManagement.setFont(new Font("Arial Black", Font.PLAIN, 20));
    lblEmployeeManagement.setForeground(Color.white);
            
    btnEmployeeRecords = new JButton("Employee Records");
    btnEmployeeRecords.setBounds(290, 120, 200, 40);
    btnEmployeeRecords.setBackground(Color.WHITE);
    btnEmployeeRecords.setForeground(Color.BLACK);
    btnEmployeeRecords.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnEmployeeRecords.addActionListener(this);
         
    btnEmployeeRequest = new JButton("Employee Request");
    btnEmployeeRequest.setBounds(290, 170, 200, 40);
    btnEmployeeRequest.setBackground(Color.WHITE);
    btnEmployeeRequest.setForeground(Color.BLACK);
    btnEmployeeRequest.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnEmployeeRequest.addActionListener(this);
    
    btnAddEm = new JButton("Add Employee");
    btnAddEm.setBounds(290,220, 200, 40);
    btnAddEm.setBackground(Color.WHITE);
    btnAddEm.setForeground(Color.BLACK);
    btnAddEm.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnAddEm.addActionListener(this);
    
    btnreq = new JButton("File Request");
    btnreq.setBounds(290,270, 200, 40);
    btnreq.setBackground(Color.WHITE);
    btnreq.setForeground(Color.BLACK);
    btnreq.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnreq.addActionListener(this);
    
    btnLogout = new JButton("Log out");
    btnLogout.setBounds(580,400, 150, 30);
    btnLogout.setBackground(Color.WHITE);
    btnLogout.setForeground(Color.BLACK);
    btnLogout.setFont(new Font("Arial Black", Font.PLAIN, 15));
    btnLogout.addActionListener(this);
    
    add(btnEmployeeRecords);
    add(btnEmployeeRequest);
    add(btnreq);
    add(lblEmployeeManagement);
    add(btnLogout);
    add(btnAddEm);
    add(HRStaff);
    
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