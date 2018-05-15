package start;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import start.Start;

public class aboutUS {

    private JFrame frmAboutUs = new JFrame("About Us");
    private JPanel pnlAboutUs = new JPanel();
    private JLabel lblAboutUs = new JLabel("ABOUT US");
    private JLabel lblBackImage = new JLabel("");
    private JLabel lblShivli = new JLabel("Shivli Agrawal - 355/CO/14");
    private JLabel lblShreya = new JLabel("Shreya Kataria - 357/CO/14");

    private JButton btnBack = new JButton("Back");

    public aboutUS() {
        frmAboutUs.add(pnlAboutUs);
        frmAboutUs.setResizable(false);
        frmAboutUs.setBounds(100, 100, 300, 450);
        frmAboutUs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAboutUs.setLocationRelativeTo(null);

        pnlAboutUs.setBackground(Color.WHITE);
        pnlAboutUs.setLayout(null);

        lblAboutUs.setHorizontalAlignment(SwingConstants.CENTER);
        lblAboutUs.setFont(new Font("ROBOTO", Font.BOLD, 35));
        lblAboutUs.setBounds(10, 11, 274, 65);
        pnlAboutUs.add(lblAboutUs);

        lblBackImage.setIcon(new ImageIcon("C:\\Users\\S.K.AGGRAWAL\\Desktop\\projects\\project\\start\\nsit.jpg"));
        lblBackImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblBackImage.setBounds(10, 87, 274, 123);
        pnlAboutUs.add(lblBackImage);

        lblShivli.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        lblShivli.setBounds(65, 230, 275, 25);
        pnlAboutUs.add(lblShivli);

        lblShreya.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        lblShreya.setBounds(65, 260, 275, 25);
        pnlAboutUs.add(lblShreya);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmAboutUs.dispose();
                new Start();
            }
        });
        btnBack.setBounds(100, 350, 100, 35);
        btnBack.setForeground(Color.BLUE);
        btnBack.setIcon(new ImageIcon("imgs\\back.png"));
        pnlAboutUs.add(btnBack);

        frmAboutUs.setVisible(true);

    }
}

