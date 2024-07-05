package oop;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class EmployeeRecords extends JFrame implements ActionListener {
    private JLabel lblEmployeeRecord;
    private JButton btnexit,btnAdd,btnDelete,btnEdit;
    private JTable table;
    private JScrollPane scrollPane;
    EmployeeRecords(){
        setTitle("Employee Record");
        setSize(900,700);
        setLocation(380, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        lblEmployeeRecord = new JLabel("Employee Record",SwingConstants.CENTER);
        lblEmployeeRecord.setBounds(0, 5, 900, 80);
        lblEmployeeRecord.setFont(new Font("Arial", Font.BOLD, 20));
        
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 100, 750, 450);
        add(scrollPane);
        
        model.addColumn("Name");
        model.addColumn("Employee ID");
        model.addColumn("Date of Birth");
        model.addColumn("Address");
        model.addColumn("Email");
        model.addColumn("Department");
        model.addColumn("Contact");
        model.addColumn("Salary");
        model.addColumn("Performance Review");

        fetchAndDisplayRecords(model);

        setVisible(true);
             
        btnAdd = new JButton("Add");
        btnAdd.setBounds(150, 580, 80, 40);
        
        btnEdit = new JButton("Edit");
        btnEdit.setBounds(250, 580, 80, 40);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(350, 580, 80, 40);

        btnexit=new JButton("Exit");
        btnexit.setBounds(700,580,120,40);
        
        
        add(btnexit);
        add(btnAdd);
        add(btnDelete);
        add(btnEdit);
        add(lblEmployeeRecord);
        
        btnexit.addActionListener(this);
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEdit.addActionListener(this);
       }
     
    private void addRecord(String Name, String Employee_ID, String Date_of_Birth, String Address, String Email, String Department, String Contact, String Salary, String Performance_Review) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345")) {
            String sql = "INSERT INTO tbl_employee_detail (Name, Employee_ID, Date_of_Birth, Address, Email, Department, Contact, Salary, Performance Review) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, Name);
                pstmt.setString(2, Employee_ID);
                pstmt.setString(3, Date_of_Birth);
                pstmt.setString(4, Address);
                pstmt.setString(5, Email);
                pstmt.setString(6, Department);
                pstmt.setString(7, Contact);
                pstmt.setString(8, Salary);
                pstmt.setString(9, Performance_Review);
                
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
     private void updateRecord(int selectedRow, String Name, String Employee_ID, String Date_of_Birth, String Address, String Email, String Department, String Contact, String Salary, String Performance_Review) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345")) {
            String sql = "UPDATE tbl_employee_detail SET Email=?, Name=?, Employee_ID=?, Date_of_Birth=?, Address=?, Department=?, Contact=?, Salary=? WHERE Email=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, Name);
                pstmt.setString(2, Employee_ID);
                pstmt.setString(3, Date_of_Birth);
                pstmt.setString(4, Address);
                pstmt.setString(5, Email);
                pstmt.setString(6, Department);
                pstmt.setString(7, Contact);
                pstmt.setString(8, Salary);
                pstmt.setString(9, Performance_Review);
                pstmt.setString(10, (String) table.getValueAt(selectedRow, 0));
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     private void deleteRecord(int selectedRow) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345");
            String sql = "DELETE FROM tbl_employee_detail WHERE Employee_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, (String) table.getValueAt(selectedRow, 0)); 
            pstmt.executeUpdate();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Record deleted successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

         private void fetchAndDisplayRecords(DefaultTableModel model)  {
         Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employees", "root", "12345");

            stmt = conn.createStatement();
            String sql = "SELECT * FROM tbl_employee_detail";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String Name = rs.getString("Name");
                String Employee_ID = rs.getString("Employee_ID");
                String Date_of_Birth = rs.getString("Date_of_Birth");
                String Address =rs.getString("Address");   
                String Email=rs.getString("Email");
                String Department=rs.getString("Department");
                String Contact=rs.getString("Contact");
                String Salary=rs.getString("Salary");
                String Performance_Review=rs.getString("Performance_Review");
                
                model.addRow(new Object[]{Name, Employee_ID, Date_of_Birth, Address, Email, Department, Contact, Salary, Performance_Review});
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

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==btnexit){
            dispose();
            HRStaff wf=new HRStaff();
            wf.setVisible(true);
         }
            else if(e.getSource()==btnAdd){
            String Name = JOptionPane.showInputDialog(this, "Enter Name:");
            String Employee_ID = JOptionPane.showInputDialog(this, "Enter Employee ID:");
            String Date_of_Birth = JOptionPane.showInputDialog(this, "Enter Date of Birth:");
            String Address = JOptionPane.showInputDialog(this, "Enter Address:");
            String Email = JOptionPane.showInputDialog(this, "Enter Email:");
            String Department = JOptionPane.showInputDialog(this, "Enter Department:");
            String Contact = JOptionPane.showInputDialog(this, "Enter Contact:");
            String Salary = JOptionPane.showInputDialog(this, "Enter Salary:");
            String Performance_Review = JOptionPane.showInputDialog(this, "Enter Performance Review:");
            addRecord(Name, Employee_ID, Date_of_Birth, Address, Email, Department, Contact, Salary, Performance_Review);
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[]{Name, Employee_ID, Date_of_Birth, Address, Email, Department, Contact, Salary, Performance_Review});
        }

         else if (e.getSource() == btnDelete) {
             
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    deleteRecord(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }}
         else if(e.getSource()==btnEdit){
              int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String Name = JOptionPane.showInputDialog(this, "Enter new Name:", table.getValueAt(selectedRow, 0));
                String Employee_ID = JOptionPane.showInputDialog(this, "Enter new Employee_ID:", table.getValueAt(selectedRow, 1));
                String Date_of_Birth = JOptionPane.showInputDialog(this, "Enter Date_of_Birth:", table.getValueAt(selectedRow, 2));
                String Address = JOptionPane.showInputDialog(this, "Enter new Address:", table.getValueAt(selectedRow, 3));
                String Email = JOptionPane.showInputDialog(this, "Enter new Email:", table.getValueAt(selectedRow, 4));
                String Department = JOptionPane.showInputDialog(this, "Enter new Department:", table.getValueAt(selectedRow, 5));
                String Contact = JOptionPane.showInputDialog(this, "Enter new Contact:", table.getValueAt(selectedRow, 6));
                String Salary = JOptionPane.showInputDialog(this, "Enter new Salary:", table.getValueAt(selectedRow, 7));
                String Performance_Review = JOptionPane.showInputDialog(this, "Enter new Performance Review:", table.getValueAt(selectedRow, 8));
                updateRecord(selectedRow, Name, Employee_ID, Date_of_Birth, Address, Email, Department, Contact, Salary, Performance_Review);
             
                table.setValueAt(Email, selectedRow, 0);
                table.setValueAt(Employee_ID, selectedRow, 1);
                table.setValueAt(Date_of_Birth, selectedRow, 2);
                table.setValueAt(Address, selectedRow, 3);
                table.setValueAt(Email, selectedRow, 4);
                table.setValueAt(Department, selectedRow, 5);
                table.setValueAt(Contact, selectedRow, 6);
                table.setValueAt(Salary, selectedRow, 7);
                table.setValueAt(Performance_Review, selectedRow, 8);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update");
            }
        }
    
         }
          public static void main(String[] args) {
        new EmployeeRecords();
    }}