package 창의프로젝트;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;


public class main extends JFrame {
	BufferedImage img=null;
	public main()
	{
		super("방방곡곡");
		JLayeredPane layeredPane=new JLayeredPane();
		layeredPane.setLocation(0, 0);
		layeredPane.setSize(480, 391);
		layeredPane.setLayout(null);
		try 
		{
			img=ImageIO.read(new File("C:\\Users\\홍주완\\Desktop\\image1.png"));
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
			System.exit(0);
		}

		myPanel panel=new myPanel();
		panel.setSize(480,420);
		layeredPane.add(panel);
		
		getContentPane().setLayout(null);
		getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("\uBC29\uBC29\uACE1\uACE1");
		layeredPane.setLayer(lblNewLabel, 1);
		lblNewLabel.setFont(new Font("양재다운명조M", Font.BOLD, 23));
		lblNewLabel.setBounds(273, 29, 128, 32);
		layeredPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\uAD00\uAD11\uC9C0");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				new dd();
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 14));
		layeredPane.setLayer(btnNewButton, 1);
		btnNewButton.setBounds(270, 109, 97, 23);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uC74C\uC2DD\uC810");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new food();
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 14));
		layeredPane.setLayer(btnNewButton_1, 1);
		btnNewButton_1.setBounds(273, 176, 97, 23);
		layeredPane.add(btnNewButton_1);
		
		JButton button = new JButton("\uC778\uAE30 \uAD00\uAD11\uC9C0");
		button.setFont(new Font("굴림", Font.PLAIN, 14));

		layeredPane.setLayer(button, 1);
		button.setBounds(273, 236, 113, 23);
		layeredPane.add(button);
		
		setSize(480,420);
		setLocation(300,300);
		setVisible(true);
		setResizable(false);
	}
	class myPanel extends JPanel
	{
		public void paint(Graphics g)
		{
			g.drawImage(img,0,0,null);
		}
		
	}
	public static void main(String[] args)
	{
		new main();
	}	
}
