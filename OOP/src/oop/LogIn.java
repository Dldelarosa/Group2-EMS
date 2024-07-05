package oop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class LogIn extends JFrame implements ActionListener {
    
    JLabel lbllog = new JLabel("LOGIN");
    JLabel lbluse = new JLabel("USERNAME:");
    JLabel lblpass = new JLabel("PASSWORD:");
    JLabel lblcr = new JLabel("Create an Account?");
    JButton btnsu = new JButton("SignUp");
    JPasswordField pass = new JPasswordField();
    JTextField tfuse = new JTextField();
    
    JButton btnlog = new JButton("LogIn");
    JButton btncan = new JButton("Exit");

    LogIn()  {
        setTitle("LOGIN");
        setSize(900, 550);
        setLocation(320, 130);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(52, 69, 79));

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
        
        btnsu.setBounds(490, 280, 90, 25);
        btnsu.setFont(new Font("serif", Font.PLAIN, 17));
        
        btncan.setBounds(470, 400, 100, 30);
        btncan.addActionListener(this);
                                
        btnlog.setBounds(350, 400, 100, 30);
        btnlog.addActionListener(this);
        btnsu.addActionListener(this);

        add(btnlog);
        add(btncan);
        add(tfuse);
        add(pass);
        add(lbllog);
        add(lbluse);
        add(lblpass);
        add(lblcr);
        add(btnsu);
        
        lbluse.setForeground(Color.WHITE);
        lblpass.setForeground(Color.WHITE);
        lbllog.setForeground(Color.WHITE);
        lblcr.setForeground(Color.WHITE);
        
        btnlog.setBackground(Color.WHITE);
        btnlog.setForeground(Color.BLACK);
        btncan.setBackground(Color.WHITE);
        btncan.setForeground(Color.BLACK);
        btnsu.setForeground(Color.WHITE);
        btnsu.setBackground(new Color(52, 69, 79));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnlog) {                         
            String username = tfuse.getText().trim();
            String password = new String(pass.getPassword());

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345");
                 PreparedStatement statement = conn.prepareStatement("SELECT * FROM tbl_login WHERE username = ? AND password = ?")) {

                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        dispose(); 
                        new HRStaff();  
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error logging in: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == btnsu) {  
            dispose();
            new SignUp();    
        } else if (ae.getSource() == btncan) {
            dispose();
        }
    }
   public static void main(String[]args){
            new LogIn();
        }
    }