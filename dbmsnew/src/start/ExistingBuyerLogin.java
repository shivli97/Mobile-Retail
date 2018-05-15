package start;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ExistingBuyerLogin {
	
	private Vector<String> Buyer = new Vector();

    static String connectionUrl = "jdbc:mysql://localhost:3306/test";
    static String dbUser = "root";
    static String dbPwd = "Shreya";
    private String inModelNo;

    static Connection conn;
    String loginname, loginpass;

    private ResultSet rs;
    private ResultSet rsTemp;

    private JTable table;

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


    private String dialogMessage, dialogs = "WELCOME!!!";

    private JFrame frmDevLogin = new JFrame("BUYER LOGIN");

    private JTextField textField = new JTextField();

    private JPasswordField passwordField = new JPasswordField();

    private JProgressBar progressBar = new JProgressBar(0, 100);
    
    

    private JLabel backImage = new JLabel(new ImageIcon("C:\\Users\\SK\\workspace\\proj\\src\\start\\imgs\\developerlogin.jpg"));
    private JLabel lblUsername = new JLabel("Username");
    private JLabel lblPassword = new JLabel("BuyerId");
    
    

    private JButton btnBack = new JButton("Back");
    private JButton btnSubmit = new JButton("Submit");
    private JButton btnReset = new JButton("Reset");

    public ExistingBuyerLogin() {
        frmDevLogin.setResizable(false);
        frmDevLogin.setBounds(100, 100, 350, 250);
        frmDevLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmDevLogin.setLocationRelativeTo(null);

        frmDevLogin.add(backImage);
        backImage.setLayout(null);

        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Roboto", Font.BOLD, 16));
        lblUsername.setBounds(30, 60, 100, 20);
        backImage.add(lblUsername);

        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Roboto", Font.BOLD, 16));
        lblPassword.setBounds(30, 100, 100, 20);
        backImage.add(lblPassword);

        textField.setBounds(125, 60, 180, 20);
        backImage.add(textField);
        textField.setColumns(10);

        passwordField.setBounds(125, 100, 180, 20);
        backImage.add(passwordField);

        btnBack.setIcon(new ImageIcon("imgs\\back.png"));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmDevLogin.dispose();
                new Start();
            }
        });
        btnBack.setBounds(10, 11, 89, 23);
        backImage.add(btnBack);

        btnSubmit.setIcon(new ImageIcon("imgs\\submit.gif"));
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                loginname = textField.getText();
                loginpass = new String(passwordField.getPassword());
                dialogMessage = "Welcome - " + loginname;
                
                Vector columnNames = new Vector();
                Vector data = new Vector();
                String sql;
                sql = "SELECT * FROM buyer WHERE buyer.BUYERID =  '" + loginpass + "' and buyer.Name= '"+loginname +"' ";

                try {

                    Statement stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);

                    rsTemp = rs;
                    if (!rs.wasNull()) 
                    {
                        new Thread(new PBar(loginname)).start();
                    } else 
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Username and Password", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
                        textField.setText("");
                        passwordField.setText("");
                    }

                    
                } catch (Exception e1) {
                    System.out.println(e1);
                }


                
            }
        });
        btnSubmit.setBounds(38, 145, 110, 35);
        backImage.add(btnSubmit);

        btnReset.setIcon(new ImageIcon("imgs\\reset.png"));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                passwordField.setText("");
            }
        });
        btnReset.setBounds(188, 145, 110, 35);
        backImage.add(btnReset);

        progressBar.setBounds(10, 195, 320, 13);
        backImage.add(progressBar);

        frmDevLogin.setVisible(true);
    }

    class PBar implements Runnable {
        String name;

        public PBar(String s) {

            name = s;
        }

        public void run() {
            for (int i = 0; i <= 100; i++) {
                progressBar.setValue(i);
                progressBar.repaint();
                try {
                    Thread.sleep(15);
                } catch (Exception e) {
                }
            }

            JOptionPane.showMessageDialog(null, dialogMessage, dialogs, JOptionPane.INFORMATION_MESSAGE);
            frmDevLogin.dispose();
            new ExistingBuyer(loginname,loginpass);
        }
    }
}
