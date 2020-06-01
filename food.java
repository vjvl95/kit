package 창의프로젝트;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import net.miginfocom.swing.MigLayout;
public class food extends JFrame {
		JLabel l1,l2,l3,l4,l5,l6;
		JTextField tf1,tf2,tf3;
		JPanel panel;
		
		Object ob[][]=new Object[0][4];
		DefaultTableModel model;
		JTable table;
		JScrollPane js;
		String str[]={ "이름", "분류", "지역", "소개"};
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection con;
		JPanel panel_1;
		private JComboBox comboBox1;
		private JComboBox comboBox;
		private Listener listener=new Listener();
		private JTextField textField;
		private JButton btnNewButton;
	public food()
	{
			super("음식점 테이블");
			panel=new JPanel();
			model=new DefaultTableModel(ob,str);
			getContentPane().setLayout(new BorderLayout(0, 0));
			getContentPane().add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			table=new JTable(model);
			js=new JScrollPane(table);
			panel.add(js, BorderLayout.SOUTH);
			
			
			
			DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
			celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumn("이름").setPreferredWidth(300);
			table.getColumn("이름").setCellRenderer(celAlignCenter);
			table.getColumn("분류").setCellRenderer(celAlignCenter);
			table.getColumn("지역").setPreferredWidth(150);
			table.getColumn("지역").setCellRenderer(celAlignCenter);
			table.getColumn("소개").setPreferredWidth(1000);

			panel_1 = new JPanel();
			getContentPane().add(panel_1, BorderLayout.NORTH);
			panel_1.setPreferredSize(new Dimension(100, 130));
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/tourandfood?serverTimezone=UTC","root", "1523");
				 String sql="select * from food";
					pstmt = con.prepareStatement(sql);
					select();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 50));
			
			comboBox = new JComboBox();
			panel_1.add(comboBox);
			comboBox.addActionListener(listener);
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "\uAC15\uC6D0\uB3C4", "\uACBD\uAE30\uB3C4", "\uACBD\uC0C1\uB0A8\uB3C4", "\uACBD\uC0C1\uBD81\uB3C4", "\uAD11\uC8FC", "\uB300\uAD6C", "\uB300\uC804", "\uBD80\uC0B0", "\uC11C\uC6B8", "\uC6B8\uC0B0", "\uC778\uCC9C", "\uC804\uB77C\uB0A8\uB3C4", "\uC804\uB77C\uBD81\uB3C4", "\uC81C\uC8FC\uB3C4", "\uCDA9\uCCAD\uB0A8\uB3C4", "\uCDA9\uCCAD\uBD81\uB3C4"}));
			comboBox.setSelectedIndex(0);
			comboBox.setPreferredSize(new Dimension(150,20));
			comboBox1 = new JComboBox();
			panel_1.add(comboBox1);
			comboBox1.addActionListener(listener);
			comboBox1.setPreferredSize(new Dimension(100, 20));
			comboBox1.setModel(new DefaultComboBoxModel(new String[] {"", "\uC11C\uC591\uC2DD", "\uC544\uC2DC\uC544\uC2DD", "\uC77C\uC2DD", "\uC911\uC2DD", "\uCE74\uD398/\uC804\uD1B5\uCC3B\uC9D1", "\uD55C\uC2DD"}));
			
			textField = new JTextField();
			panel_1.add(textField);
			textField.setColumns(10);
			
			btnNewButton = new JButton("\uAC80\uC0C9");
			btnNewButton.addActionListener(listener);
			panel_1.add(btnNewButton);
			
			
			
			setBounds(500,500,1000,600);
			

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(true);
			
	}
	
	class Listener implements ActionListener
	{
		JComboBox cb,cb2;
		JTextField tf;
		String location,category,search;
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==comboBox)
			{
				 cb=(JComboBox)e.getSource();
				 location=(String)cb.getSelectedItem();
			}
			else if(e.getSource()==comboBox1)
			{
				 cb2=(JComboBox)e.getSource();
				 category=(String)cb2.getSelectedItem();
			}
			else 
			{
				
				search=textField.getText();
				System.out.println(search);
				
			}
			String sql="select * from food where category=? and location=? and name LIKE ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,category);
				pstmt.setString(2,location);
				pstmt.setString(3,search+"%");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			model.setNumRows(0);
			select();
		}		
	}
	
	private void select()
	{
		try {
			ResultSet rs=pstmt.executeQuery();
					while(rs.next())
					{
						String name = rs.getString("name");
						String category=rs.getString("category");
						String location=rs.getString("location");
						String summary=rs.getString("summary");
						Object data[]= {name,category,location,summary};
						model.addRow(data);
					}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	public static void main(String[] args)
	{
		new food();
	}	
	
}
