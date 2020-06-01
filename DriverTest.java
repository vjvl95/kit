package 창의프로젝트;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DriverTest extends JFrame 
{  
	 private JTable table;
	 private JScrollPane scrollPane;
	 private String[] columnType = { "관광지이름", "전화번호", "대분류", "소분류","지역","주소"};
	 private Object data[][] = new Object[0][6]; 
	 DefaultTableModel model;
	 
	public DriverTest()
	{
		super("dd");
        setTitle("\uC2DC\uC124/\uC7AC\uACE0 \uCD9C\uACE0 \uC870\uD68C");
        getContentPane().setForeground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        model=new DefaultTableModel(data,columnType);
        table = new JTable(data, columnType);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.GRAY);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 133, 846, 77);

        table.setFillsViewportHeight(true);

        table.getModel().addTableModelListener((TableModelListener) this); // 테이블에 소속된 하나의 모델이 셀들을 관리하므로 항상 getModel() 을 호출해야함
        table.setAutoCreateRowSorter(true); //자동 행 정렬기능

        //성별 컬럼에 지정된 선택지만 추가할 수 있도록 설정한다.
        TableColumn genderColumn = table.getColumn("성별");
        JComboBox gender = new JComboBox();
        gender.addItem("여성");
        gender.addItem("남성");
        genderColumn.setCellEditor(new DefaultCellEditor(gender));
        getContentPane().setLayout(null);

        getContentPane().add(scrollPane);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 846, 128);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC785\uACE0"}));
        comboBox.setToolTipText("");
        comboBox.setBounds(293, 23, 97, 23);
        panel.add(comboBox);
        
        JButton btnNewButton_1 = new JButton("\uC0AD\uC81C");
        
        btnNewButton_1.setBounds(293, 79, 97, 23);
        panel.add(btnNewButton_1);
        
        JButton btnNewButton_1_1 = new JButton("\uC218\uC815");
        btnNewButton_1_1.setBounds(412, 79, 97, 23);
        panel.add(btnNewButton_1_1);
        
        JButton btnNewButton = new JButton("\uC870\uD68C");
        btnNewButton.setBounds(412, 23, 97, 23);
        panel.add(btnNewButton);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.GRAY);
        panel_1.setBounds(0, 132, 846, 248);
        getContentPane().add(panel_1);
        
        JButton btnNewButton_2 = new JButton("\uCDE8\uC18C");
        btnNewButton_2.setBounds(737, 390, 97, 23);
        getContentPane().add(btnNewButton_2);
        setVisible(true);
        
    	try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/tourandfood?serverTimezone=UTC","root", "1523");
			String qu="select * from tour";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qu);
			
			while (rs.next()) 
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
			st.close();
		}
		catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}	   
		
	}
	
	 public static void main(String args[]) 
	   {  			
		 	new DriverTest();
	    }
	

}