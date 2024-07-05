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


public class SignUp extends JFrame implements ActionListener{
 
    
    JLabel lbllog = new JLabel("SIGN UP");
    JLabel lbluse = new JLabel("USERNAME:");
    JLabel lblpass = new JLabel("PASSWORD:");
    JLabel lblcom = new JLabel("Confirm Password:");
    
    JTextField tfuse = new JTextField();
    JPasswordField pass = new JPasswordField();
    JPasswordField psswrdfldConfirmPassword = new JPasswordField();
    
    JButton btnsu = new JButton("SignUp");
    JButton btnback = new JButton("Back");
    Connection conn;
     
    SignUp()  {
        setTitle("SIGNUP");
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
        
        lbluse.setBounds(320, 135, 150, 25);
        lbluse.setFont(new Font("serif", Font.PLAIN, 20));
        
        tfuse.setBounds(355, 175, 200, 25);
        
        lblpass.setBounds(320, 215, 150, 25);
        lblpass.setFont(new Font("serif", Font.PLAIN, 20));
        
        pass.setBounds(355, 245, 200, 25);
        
        lblcom = new JLabel("CONFIRM PASSWORD:");
        lblcom.setBounds(320, 280, 250, 25);
        lblcom.setFont(new Font("serif", Font.PLAIN, 20));

        psswrdfldConfirmPassword = new JPasswordField();
        psswrdfldConfirmPassword.setBounds(355, 315, 200, 25);
        
        btnback.setBounds(470, 420, 100, 30);
        btnback.addActionListener(this);
                                
        btnsu.setBounds(350, 420, 100, 30);
        btnsu.addActionListener(this);
        
        add(btnsu);
        add(btnback);
        
        add(tfuse);
        add(pass);
        add(psswrdfldConfirmPassword);
        
        add(lbllog);
        add(lbluse);
        add(lblpass);
        add(lblcom);
        
        lbluse.setForeground(Color.WHITE);
        lblpass.setForeground(Color.WHITE);
        lbllog.setForeground(Color.WHITE);
        lblcom.setForeground(Color.WHITE);

        btnback.setBackground(Color.WHITE);
        btnback.setForeground(Color.BLACK);
        btnsu.setBackground(Color.WHITE);
        btnsu.setForeground(Color.BLACK);

        
    }
    public void actionPerformed(ActionEvent ae){
        
        if (ae.getSource() == btnsu){
            String use = tfuse.getText().trim();
            String password = new String(pass.getPassword());
            String confirmPassword = new String(psswrdfldConfirmPassword.getPassword());
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this,"Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            java.sql.Connection conn = null;
            ResultSet resultSet = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345");

                String sql = "INSERT INTO tbl_login(username, password) VALUES ( ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, use);
                statement.setString(2, password);

                    int rowsInserted = statement.executeUpdate();               
                if (rowsInserted > 0) {
                     }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,"Error registering user: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }else {
            dispose();
            new LogIn();
        }
    }
        public static void main(String[]args){
            new SignUp();
        }
    }