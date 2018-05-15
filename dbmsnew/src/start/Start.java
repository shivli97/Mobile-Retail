package start;

import javax.swing.*;
import java.awt.event.*;

public class Start implements ActionListener {
	private JFrame frameStart = new JFrame("Mobile Retail System");

	private JLabel backImage = new JLabel(new ImageIcon("C:\\Users\\S.K.AGGRAWAL\\Desktop\\projects\\project\\start\\mainimg2.jpg"));

	private JButton btnSearch = new JButton("Recommend & Buy");
	private JButton btnDevLogin = new JButton("Developer Login");
	private JButton SellerSpace = new JButton("Seller Space");
	private JButton btnAboutUs = new JButton("About Us");
    private JButton btnBuyer = new JButton("Buyer Space");
	
	public static void main(String[] args) {
		try 
		{
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Start();
	}

	public Start() {
		frameStart.setResizable(false);
		frameStart.setBounds(100, 100, 600, 401);
		frameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameStart.setLocationRelativeTo(null);

		frameStart.add(backImage);
		backImage.setLayout(null);

		btnDevLogin.setIcon(new ImageIcon("imgs\\dev.png"));
		btnDevLogin.addActionListener(this);
		btnDevLogin.setBounds(400, 270, 180, 35);
		backImage.add(btnDevLogin);

		btnSearch.setIcon(new ImageIcon("imgs\\search.png"));
		btnSearch.addActionListener(this);
		btnSearch.setBounds(400, 325, 180, 35);
		backImage.add(btnSearch);

		btnAboutUs.setIcon(new ImageIcon("imgs\\about.png"));
		btnAboutUs.addActionListener(this);
		btnAboutUs.setBounds(15, 10, 130, 35);
		backImage.add(btnAboutUs);
		
		SellerSpace.setIcon(new ImageIcon("imgs\\dev.png"));
		SellerSpace.addActionListener(this);
		SellerSpace.setBounds(15, 270, 180, 35);
		backImage.add(SellerSpace);
        
		btnBuyer.setIcon(new ImageIcon("imgs\\about.png"));
		btnBuyer.addActionListener(this);
		btnBuyer.setBounds(15, 325, 180, 35);
		backImage.add(btnBuyer);
		
		frameStart.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		frameStart.dispose();
		if (e.getSource() == btnDevLogin)
			new devLogin();

		if (e.getSource() == btnSearch)
			new search();

		if (e.getSource() == btnAboutUs)
			new aboutUS();
		
		if (e.getSource() == SellerSpace)
			new SellerPage();
		
		if (e.getSource() == btnBuyer)
			new BuyerPage();
			
	}
}
