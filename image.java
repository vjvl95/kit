package 창의프로젝트;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class image extends JFrame {
	BufferedImage img=null;

	public image() 
	{	
		
		JLayeredPane layeredPane=new JLayeredPane();
	layeredPane.setLocation(0, 0);
	layeredPane.setSize(300, 820);
	layeredPane.setLayout(null);
	try 
	{
		img=ImageIO.read(new File("C:\\Users\\홍주완\\Desktop\\2017-2018 (2).jpg"));
	}
	catch(IOException e)
	{
		JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
		System.exit(0);
	}
	drow panel=new drow();
	panel.setSize(300,820);
	layeredPane.add(panel);
	
	getContentPane().setLayout(null);
	getContentPane().add(layeredPane);
	
	setSize(300,820);
	setLocation(100,0);
	setVisible(true);
	setResizable(false);
	}
	class drow extends JPanel
	{
		public void paint(Graphics g)
		{
			g.drawImage(img,0,0,null);
		}
		
	}
	
	public static void main(String[] args)
	{		
		new image();
	}

}
