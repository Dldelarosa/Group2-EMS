 package oop;

import com.sun.jdi.connect.spi.Connection;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LogIn extends JFrame implements ActionListener{
 
    
    JLabel lbllog = new JLabel("LOGIN");
    JLabel lbluse = new JLabel("USERNAME:");
    JLabel lblpass = new JLabel("PASSWORD:");
    JTextField tfuse = new JTextField();
    JTextField tfpass = new JTextField();
    JButton btnlog = new JButton("LogIn");
    JButton btncan = new JButton("Exit");
    Connection conn;
     
    LogIn()  {
        setTitle("LOGIN");
        setSize(900, 550);
        setLocation(320, 130);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setResizable(false);

        lbllog.setBounds(405, 20, 500, 50);
        lbllog.setFont(new Font("SAN_SERIF", Font.BOLD, 30));
        
        lbluse.setBounds(300, 140, 150, 25);
        lbluse.setFont(new Font("serif", Font.PLAIN, 20));
        
        tfuse.setBounds(350, 180, 200, 25);
        
        lblpass.setBounds(300, 215, 150, 25);
        lblpass.setFont(new Font("serif", Font.PLAIN, 20));
        
        tfpass.setBounds(350, 250, 200, 25);
        
        btncan.setBounds(470, 400, 100, 30);
        btncan.addActionListener(this);
                                
        btnlog.setBounds(320, 400, 100, 30);
        btnlog.addActionListener(this);
        
        add(btnlog);
        add(btncan);
        
        add(tfuse);
        add(tfpass);
        
        add(lbllog);
        add(lbluse);
        add(lblpass);
        
        btnlog.setBackground(Color.BLACK);
        btnlog.setForeground(Color.WHITE);
        btncan.setBackground(Color.BLACK);
        btncan.setForeground(Color.WHITE);
    }
    public void actionPerformed(ActionEvent ae){
        dispose();
        if (ae.getSource()== btnlog){                         
       
            String username = tfuse.getText().trim();
            String password = new String(tfpass.getText());

            
            java.sql.Connection conn = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345");

                String sql = "SELECT * FROM tbl_login WHERE username = ? AND password = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);

                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    dispose(); 
                    HRStaff frame = new HRStaff();  
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error logging in: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
//            }else if(ae.getSource()== btncan){  
            dispose();           
            }
        }else {
            dispose();
        }
    }

 }