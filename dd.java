package 창의프로젝트;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import net.miginfocom.swing.MigLayout;
public class dd extends JFrame {
		JLabel l1,l2,l3,l4,l5,l6;
		JTextField tf1,tf2,tf3;
		JPanel panel;
		
		Object ob[][]=new Object[0][6];
		DefaultTableModel model;
		JTable table;
		JScrollPane js;
		String str[]={ "관광지이름", "전화번호", "대분류", "소분류","지역","주소"};
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Connection con;
		JPanel panel_1;
		private JComboBox comboBox1;
		private JComboBox comboBox;
		private Listener listener=new Listener();
		private JTextField textField;
		private JButton button;
	public dd()
	{
			super("관광지 테이블");
			panel=new JPanel();
			model=new DefaultTableModel(ob,str);
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(0, 0));
			table=new JTable(model);
			js=new JScrollPane(table);
			panel.add(js);
			
			
			
			DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
			celAlignCenter.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumn("관광지이름").setPreferredWidth(300);
			table.getColumn("대분류").setCellRenderer(celAlignCenter);
			table.getColumn("전화번호").setPreferredWidth(170);
			table.getColumn("대분류").setPreferredWidth(150);
			table.getColumn("소분류").setPreferredWidth(150);
			table.getColumn("지역").setPreferredWidth(200);
			table.getColumn("지역").setCellRenderer(celAlignCenter);
			table.getColumn("주소").setPreferredWidth(1000);

			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(300,300));
			getContentPane().add(panel_1, BorderLayout.NORTH);
			
			try 
			{
				con = DriverManager.getConnection("jdbc:mysql://localhost/tourandfood?serverTimezone=UTC","root", "1523");
				 String sql="select * from tour";
					pstmt = con.prepareStatement(sql);
					select();	
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			comboBox1 = new JComboBox();
			comboBox1.addActionListener(listener);
			comboBox1.setModel(new DefaultComboBoxModel(new String[] {"", "\uC778\uBB38", "\uC790\uC5F0"}));
			comboBox1.setBounds(318, 48, 75, 23);
			panel_1.add(comboBox1);
			
			comboBox = new JComboBox();
			comboBox.setBounds(461, 49, 100, 20);
			comboBox.addActionListener(listener);
			panel_1.setLayout(null);
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "\uC11C\uC6B8", "\uAC15\uC6D0\uB3C4", "\uACBD\uAE30\uB3C4", "\uACBD\uC0C1\uB0A8\uB3C4", "\uACBD\uC0C1\uBD81\uB3C4", "\uAD11\uC8FC", "\uB300\uAD6C", "\uB300\uC804", "\uBD80\uC0B0", "\uC11C\uC6B8", "\uC6B8\uC0B0", "\uC778\uCC9C", "\uC804\uB77C\uB0A8\uB3C4", "\uC804\uB77C\uBD81\uB3C4", "\uC81C\uC8FC\uB3C4", "\uCDA9\uCCAD\uB0A8\uB3C4"}));
			comboBox.setSelectedIndex(0);
			comboBox.setPreferredSize(new Dimension(100,20));
			panel_1.add(comboBox);
			
			textField = new JTextField();
			textField.setBounds(631, 49, 96, 21);
			panel_1.add(textField);
			textField.setColumns(10);
			
			button = new JButton("\uAC80\uC0C9");
			button.addActionListener(listener);
			button.setBounds(759, 48, 91, 23);
			panel_1.add(button);
			
			setBounds(500,500,1000,600);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setResizable(true);
			
	}
	
	class Listener implements ActionListener
	{
		JComboBox cb,cb2;
		JTextField tf;
		String name,category,search;
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==comboBox)
			{
				 cb=(JComboBox)e.getSource();
				 name=(String)cb.getSelectedItem();
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

			String sql="select * from tour where location=? and large_category=? and name LIKE ?";
			try {
				if(search=="")
				{
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,category);
				pstmt.setString(3, "*");
				}
				else
				{
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1,name);
					pstmt.setString(2,category);
					pstmt.setString(3,"%"+ search+"%");	
				}
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
						String tel=rs.getString("tel");
						String large_category=rs.getString("large_category");
						String middle_category=rs.getString("middle_category");
						String location=rs.getString("location");
						String address=rs.getString("address"); 
						Object data[]= {name,tel,large_category,middle_category,location,address};
						model.addRow(data);
					}
								
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	public static void main(String[] args)
	{
		new dd();
	}
}
