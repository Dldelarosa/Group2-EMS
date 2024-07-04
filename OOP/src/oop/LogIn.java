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
    JLabel lblcr = new JLabel("Create an Account?");
    JLabel lblsu = new JLabel("SignUp");
    JPasswordField pass = new JPasswordField();
    JTextField tfuse = new JTextField();
    
    JButton btnlog = new JButton("LogIn");
    JButton btncan = new JButton("Exit");
    Connection conn;
     
    LogIn()  {
        setTitle("LOGIN");
        setSize(900, 550);
        setLocation(320, 130);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color( 39,110,175));

        getContentPane().setBackground(new Color( 52,69,79));

        setLayout(null);
        setResizable(false);

        lbllog.setBounds(405, 40, 500, 50);
        lbllog.setFont(new Font("SAN_SERIF", Font.BOLD, 30));
        
        lbluse.setBounds(320, 145, 150, 25);
        lbluse.setFont(new Font("serif", Font.PLAIN, 20));
        
        tfuse.setBounds(355, 180, 200, 25);
        
        lblpass.setBounds(320, 220, 150, 25);
        lblpass.setFont(new Font("serif", Font.PLAIN, 20));
        
        pass.setBounds(355, 250, 200, 25);
        
        lblcr.setBounds(355, 280, 200, 25);
        lblcr.setFont(new Font("serif", Font.PLAIN, 17));
        
        lblsu.setBounds(490, 280, 150, 25);
        lblsu.setFont(new Font("serif", Font.PLAIN, 17));
        
        btncan.setBounds(470, 400, 100, 30);
        btncan.addActionListener(this);
                                
        btnlog.setBounds(350, 400, 100, 30);
        btnlog.addActionListener(this);
        
        add(btnlog);
        add(btncan);
        
        add(tfuse);
        add(pass);
        
        add(lbllog);
        add(lbluse);
        add(lblpass);
        add(lblcr);
        add(lblsu);
        
        lbluse.setForeground(Color.WHITE);
        lblpass.setForeground(Color.WHITE);
        lbllog.setForeground(Color.WHITE);
        lblcr.setForeground(Color.WHITE);
        lblsu.setForeground(Color.WHITE);

        btnlog.setBackground(Color.BLACK);
        btnlog.setForeground(Color.WHITE);
        btncan.setBackground(Color.BLACK);
        btncan.setForeground(Color.WHITE);

        btnlog.setBackground(Color.WHITE);
        btnlog.setForeground(Color.BLACK);
        btncan.setBackground(Color.WHITE);
        btncan.setForeground(Color.BLACK);
    }
    public void actionPerformed(ActionEvent ae){
        dispose();
        if (ae.getSource()== btnlog){                         
       
            String username = tfuse.getText().trim();
            String password = new String(pass.getPassword());

            
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
        public static void main(String[]args){
        new LogIn();
    }
 }