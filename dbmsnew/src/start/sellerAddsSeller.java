package start;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.border.*;

public class sellerAddsSeller implements ActionListener {
    private JFrame frmSellerAddsSeller = new JFrame("New Seller Window");

    private JPanel backImage = new JPanel();

    private JLabel lblAddSeller = new JLabel("Form To Add New Seller");
    private JLabel lblSellerID = new JLabel("Seller ID");
    private JLabel lblName = new JLabel("Name");
    private JLabel lblEmail = new JLabel("Email Address");
    private JLabel lblPhone = new JLabel("Phone Number");
    private JLabel lblAddress = new JLabel("Address");
    private JLabel lblReviews = new JLabel("Reviews");

    private JTextField txtSellerID = new JTextField();
    private JTextField txtName = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtPhone = new JTextField();
    private JTextArea txtAddress = new JTextArea();
    private JTextArea txtReviews = new JTextArea();

    private JButton btnBack = new JButton("Back");
    private JButton btnSubmit = new JButton("Submit");
    private JButton btnReset = new JButton("Reset");

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

    public sellerAddsSeller() {
        frmSellerAddsSeller.setResizable(false);
        frmSellerAddsSeller.setBounds(100, 100, 1200, 600);
        frmSellerAddsSeller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSellerAddsSeller.setLocationRelativeTo(null);

        frmSellerAddsSeller.add(backImage);
        backImage.setLayout(null);

        lblAddSeller.setFont(new Font("ROBOTO", Font.BOLD, 16));
        lblAddSeller.setBounds(140, 11, 175, 31);
        backImage.add(lblAddSeller);

        lblSellerID.setBounds(53, 70, 100, 20);
        backImage.add(lblSellerID);

        lblName.setBounds(53, 100, 100, 20);
        backImage.add(lblName);

        lblEmail.setBounds(53, 130, 100, 20);
        backImage.add(lblEmail);

        lblPhone.setBounds(53, 160, 100, 20);
        backImage.add(lblPhone);

        lblAddress.setBounds(53, 190, 100, 20);
        backImage.add(lblAddress);

        lblReviews.setBounds(53, 270, 100, 20);
        backImage.add(lblReviews);

        txtSellerID.setColumns(150);
        txtSellerID.setBounds(160, 70, 180, 20);
        backImage.add(txtSellerID);

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

        txtReviews.setLineWrap(true);
        JScrollPane ScrollPane2 = new JScrollPane(txtReviews, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollPane2.setBounds(160, 270, 180, 70);
        backImage.add(ScrollPane2);

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

        frmSellerAddsSeller.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            Statement stmt = conn.createStatement();

            String SellerID = txtSellerID.getText();
            String Name = txtName.getText();
            String Email = txtEmail.getText();
            String Phone = txtPhone.getText();
            String Address = txtAddress.getText();
            String Reviews = txtReviews.getText();

            if (e.getSource() == btnReset) {
                resetAll();
            } else if (e.getSource() == btnBack) {
                frmSellerAddsSeller.dispose();
                new SellerPage();
            } else if (SellerID.isEmpty())
                JOptionPane.showMessageDialog(null, "Seller ID cannot be blank!", "ERROR!!!", JOptionPane.ERROR_MESSAGE);

            else if (e.getSource() == btnSubmit) {

                stmt.executeUpdate("INSERT INTO seller VALUES('" + SellerID + "','" + Name + "','" + Email + "','" + Phone + "','" + Address + "','" + Reviews + "')");


                JOptionPane.showMessageDialog(null, "Successfully inserted into the database", "Success!!!", JOptionPane.INFORMATION_MESSAGE);
                resetAll();
            } else if (conn != null) {
                conn.close();
                conn = null;
            }

            refreshTable();
        } catch (SQLException sqle) {
            System.out.println("SQL Exception thrown: " + sqle);
        }
    }


    public void resetAll() {
        txtSellerID.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtReviews.setText("");
    }

    public void refreshTable() {
        DefaultTableModel model = new DefaultTableModel();
        try {
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM seller");

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
