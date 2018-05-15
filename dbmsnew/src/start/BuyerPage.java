package start;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BuyerPage {
    private JFrame frmBuyerPage = new JFrame("BUYER Options");
    private JLabel backImage = new JLabel(new ImageIcon("C:\\Users\\S.K.AGGRAWAL\\Desktop\\projects\\project\\start\\developerlogin.jpg"));
    private JLabel lblWelcome = new JLabel("WELCOME BUYER");
    private JButton btnAddNewModel = new JButton("EXISTING BUYER");
    private JButton btnSignOut = new JButton("BACK");
    private JButton btnAddBuyer = new JButton("REGISTER YOURSELF");

    public BuyerPage() {
        frmBuyerPage.setResizable(false);
        frmBuyerPage.setBounds(100, 100, 500, 300);
        frmBuyerPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmBuyerPage.setLocationRelativeTo(null);

        frmBuyerPage.add(backImage);
        backImage.setLayout(null);

        lblWelcome.setFont(new Font("CALIBRI", Font.CENTER_BASELINE, 38));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(105, 75, 400, 50);
        backImage.add(lblWelcome);

        btnAddNewModel.setIcon(new ImageIcon("imgs\\addmobile.png"));
        btnAddNewModel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmBuyerPage.dispose();
                new ExistingBuyerLogin();
            }
        });
        btnAddNewModel.setBounds(260, 180, 200, 40);
        backImage.add(btnAddNewModel);

        btnAddBuyer.setIcon(new ImageIcon("imgs\\addseller.png"));
        btnAddBuyer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmBuyerPage.dispose();
                new addBuyer();
            }
        });
        btnAddBuyer.setBounds(30, 180, 200, 40);
        backImage.add(btnAddBuyer);

        btnSignOut.setIcon(new ImageIcon("imgs\\back.png"));
        btnSignOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmBuyerPage.dispose();
                new Start();
            }
        });
        btnSignOut.setBounds(15, 15, 125, 30);
        backImage.add(btnSignOut);

        frmBuyerPage.setVisible(true);
    }
}
