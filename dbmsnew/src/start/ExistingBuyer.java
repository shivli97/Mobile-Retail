package start;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.border.*;

public class ExistingBuyer implements ActionListener {
    private JFrame frmaddBuyer = new JFrame("Existing Buyer Window");

    private JPanel backImage = new JPanel();

    //private JLabel lbladdBuyer = new JLabel("Form To Add New Buyer");
    private JLabel lblBuyerID = new JLabel("Buyer ID");
    private JLabel lblName = new JLabel("Name");
    private JLabel lblEmail = new JLabel("Email Address");
    private JLabel lblPhone = new JLabel("Phone Number");
    private JLabel lblAddress = new JLabel("Address");
    private JLabel AddMobile= new JLabel("Add Mobile to Buy");
    //private JLabel lblReviews = new JLabel("Reviews");

    private JTextField txtBuyerID = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextArea txtAddress = new JTextArea();
    private JTextField txtAddMobile=new JTextField();
    //private JTextArea txtReviews = new JTextArea();

    private JButton btnBack = new JButton("Back");
    private JButton btnSubmit = new JButton("Submit");
    private JButton btnReset = new JButton("Reset");
    private JButton btnModify = new JButton("Modify");

    private JTable table;

    static String connectionUrl = "jdbc:mysql://localhost:3306/test";
    static String dbUser = "root";
    static String dbPwd = "Shreya";

    static Connection conn;

    private ResultSet rs;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException cnf) {
            System.out.println("Driver could not be loaded: " + cnf);
        }
    }

    public ExistingBuyer(String loginname, String loginpass) {
        frmaddBuyer.setResizable(false);
        frmaddBuyer.setBounds(100, 100, 1200, 700);
        frmaddBuyer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmaddBuyer.setLocationRelativeTo(null);

        frmaddBuyer.add(backImage);
        backImage.setLayout(null);

        //lbladdBuyer.setFont(new Font("ROBOTO", Font.BOLD, 16));
        //lbladdBuyer.setBounds(140, 11, 175, 31);
        //backImage.add(lbladdBuyer);

        lblBuyerID.setBounds(53, 70, 100, 20);
        backImage.add(lblBuyerID);

        lblName.setBounds(53, 100, 100, 20);
        backImage.add(lblName);

        lblEmail.setBounds(53, 130, 100, 20);
        backImage.add(lblEmail);

        lblPhone.setBounds(53, 160, 100, 20);
        backImage.add(lblPhone);

        lblAddress.setBounds(53, 190, 100, 20);
        backImage.add(lblAddress);
        
        AddMobile.setBounds(53, 280, 100, 20);
        backImage.add(AddMobile);

        //lblReviews.setBounds(53, 270, 100, 20);
        //backImage.add(lblReviews);

        txtBuyerID.setColumns(150);
        txtBuyerID.setBounds(160, 70, 180, 20);
        backImage.add(txtBuyerID);

        txtName.setColumns(150);
        txtName.setBounds(160, 100, 180, 20);
        backImage.add(txtName);

        txtEmail.setColumns(150);
        txtEmail.setBounds(160, 130, 180, 20);
        backImage.add(txtEmail);

        txtPhone.setColumns(150);
        txtPhone.setBounds(160, 160, 180, 20);
        backImage.add(txtPhone);

        txtAddress.setLineWrap(true);
        JScrollPane ScrollPane1 = new JScrollPane(txtAddress, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollPane1.setBounds(160, 190, 180, 70);
        backImage.add(ScrollPane1);
        
        txtAddMobile.setColumns(150);
        txtAddMobile.setBounds(160, 280, 180, 20);
        backImage.add(txtAddMobile);

        //txtReviews.setLineWrap(true);
        //JScrollPane ScrollPane2 = new JScrollPane(txtReviews, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //ScrollPane2.setBounds(160, 270, 180, 70);
        //backImage.add(ScrollPane2);

        btnBack.setForeground(Color.BLUE);
        btnBack.setIcon(new ImageIcon("imgs\\back.png"));
        btnBack.addActionListener(this);
        btnBack.setBounds(10, 11, 89, 23);
        backImage.add(btnBack);

        btnSubmit.setIcon(new ImageIcon("imgs\\submit.gif"));
        btnSubmit.addActionListener(this);
        btnSubmit.setBounds(135, 420, 120, 40);
        backImage.add(btnSubmit);

        btnReset.setIcon(new ImageIcon("imgs\\reset.png"));
        btnReset.addActionListener(this);
        btnReset.setBounds(135, 480, 120, 40);
        backImage.add(btnReset);
        
        btnModify.setIcon(new ImageIcon("imgs\\reset.png"));
        btnModify.addActionListener(this);
        btnModify.setBounds(135, 540, 120, 40);
        backImage.add(btnModify);

        table = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDefaultRenderer(new HeaderRenderer(table));
        table.getTableHeader().setResizingAllowed(false);
        JScrollPane ScrollPane3 = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollPane3.setBounds(380, 50, 800, 500);
        table.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
        refreshTable();
        backImage.add(ScrollPane3); 
        
        try {
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM buyer WHERE BUYERID= '" + loginpass + "'");
            String Name, Email,Phone, Address;
            if (rs.next()) {
                Name = rs.getString("NAME");
                Email = rs.getString("EMAIL");
                Phone = rs.getString("PHONE");
                Address = rs.getString("ADDRESS");
                //Reviews = rs.getString("REVIEWS");
                
                
                txtBuyerID.setText(loginpass);
                txtName.setText(Name);
                txtEmail.setText(Email);
                txtPhone.setText(Phone);
                txtAddress.setText(Address);
                //txtReviews.setText(Reviews);
            }
            
        } catch (SQLException sqle) {
            System.out.println("SQL Exception thrown: " + sqle);
        }

        frmaddBuyer.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            Statement stmt = conn.createStatement();

            String BuyerID = txtBuyerID.getText();
            String Name = txtName.getText();
            String Email = txtEmail.getText();
            String Phone = txtPhone.getText();
            String Address = txtAddress.getText();
            String AddMobile=txtAddMobile.getText();
            //String Reviews = txtReviews.getText();

            if (e.getSource() == btnReset) {
                resetAll();
            } else if (e.getSource() == btnBack) {
                frmaddBuyer.dispose();
                new BuyerPage();
            } else if (BuyerID.isEmpty())
                JOptionPane.showMessageDialog(null, "Buyer ID cannot be blank!", "ERROR!!!", JOptionPane.ERROR_MESSAGE);

            else if (e.getSource() == btnSubmit) {

                stmt.executeUpdate("INSERT INTO buys VALUES('" + BuyerID + "','" + AddMobile + "')");
                 

                JOptionPane.showMessageDialog(null, "Successfully inserted into the database", "Success!!!", JOptionPane.INFORMATION_MESSAGE);
                resetAll();
            }else if (e.getSource() == btnModify) {

                String str = "UPDATE buyer SET NAME=?, EMAIL=?, PHONE=?, ADDRESS=? WHERE BUYERID=?";

                PreparedStatement psmt = conn.prepareStatement(str);

                psmt.setString(1, Name);
                psmt.setString(2, Email);
                psmt.setString(3, Phone);
                psmt.setString(4, Address);
                //psmt.setString(5, Reviews);
                psmt.setString(5, BuyerID);
                
                psmt.executeUpdate();
                

                JOptionPane.showMessageDialog(null, "Successfully modified into the database", "Success!!!", JOptionPane.INFORMATION_MESSAGE);
                //resetAll();
            } 
            else if (conn != null) {
                conn.close();
                conn = null;
            }
            
            

            refreshTable();
        } catch (SQLException sqle) {
            System.out.println("SQL Exception thrown: " + sqle);
        }
    }


    public void resetAll() {
        txtBuyerID.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        //txtReviews.setText("");
    }

    public void refreshTable() {
        DefaultTableModel model = new DefaultTableModel();
        try {
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            Statement stmt = conn.createStatement();
            //select * from sells where mobileid =(select mobileid from buys where buyerid=loginpass) 
            String Buy1=txtBuyerID.getText();
            rs = stmt.executeQuery("SELECT * FROM sells WHERE Mobile_Id=(select Mobile_Id from buys where BuyerId='"+Buy1+"')");

            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            for (int i = 1; i <= columns; i++) {
                model.addColumn(md.getColumnName(i));
            }

            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));

                }

                model.addRow(row);
            }

            conn.close();
        } catch (Exception e) {
        }
        table.setModel(model);
    }

    private static class HeaderRenderer implements TableCellRenderer {

        DefaultTableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer)
                    table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, col);
        }
    }
}
