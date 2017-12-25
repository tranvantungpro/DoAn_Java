package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class ManHinhTinhLuong extends JFrame{
	DefaultTableModel dtnLuong;
	JTable tblLuong;
	JTextField txtTim;
	JButton btnLietKe,btnThoat;
	Connection con=null;
	Statement sta=null;
	ResultSet result=null;
	PreparedStatement preStatement=null;
	
	public ManHinhTinhLuong (String title)
	{
		super(title);
		addcontrols();
		addEvent();
		showWindown();
		ketNoiCoSoDuLieu();
		//HienThiToanBoLichGD();
	}
	public void addEvent() {
		// TODO Auto-generated method stub

		btnLietKe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtTim.getText().equals(""))
				{
					JOptionPane.showConfirmDialog(null,"bạn chưa nhập thông tin tìm kiếm","Thông báo", JOptionPane.OK_OPTION);
				}
				else {
				xuLyTimKiem();
				
			}
			}
		});
	}
//	public void HienThiToanBoLichGD() {
//		try
//		{
//			CallableStatement callStatement=con.prepareCall("{call XemLichGD}");
//			result=callStatement.executeQuery();
//			dtnLuong.setRowCount(0);
//			while(result.next())
//			{
//				Vector<Object> vec=new Vector<Object>();
//				vec.add(result.getString("MaGV"));
//				vec.add(result.getString("TenGV"));
//				vec.add(result.getString("SoNgayLam"));
//				vec.add(result.getString("HeSoLuong"));
//				vec.add(result.getString("LuongCB"));
//				vec.add(result.getString(" Luong"));
//				dtnLuong.addRow(vec);
//			}
//			
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}

	public void ketNoiCoSoDuLieu() {
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl=
					"jdbc:sqlserver://DESKTOP-K12EMGJ:1433;databaseName=QuanLyTrungTamTH;integratedSecurity=true;";
			con= DriverManager.getConnection(connectionUrl);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	protected void xuLyTimKiem() {
		// TODO Auto-generated method stub
		try
		{
			String sql= "{call TinhLuong(?) }";
			CallableStatement callStatement=con.prepareCall(sql);
			callStatement.setString(1,txtTim.getText());	
			result=callStatement.executeQuery();		
			dtnLuong.setRowCount(0);
			
			//JOptionPane.showMessageDialog(null, dtnPhongHoc1);
			while(result.next())
			{
				Vector<Object> vec=new Vector<Object>();
				vec.add(result.getString("MaGV"));
				vec.add(result.getString("TenGV"));
				vec.add(result.getString("SoNgayLam"));
				vec.add(result.getString("HeSoLuong"));
				vec.add(result.getString("LuongCB"));
				vec.add(result.getString("Luong"));
				vec.add(result.getString("Thang"));
				dtnLuong.addRow(vec);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


	private void addcontrols() {
		// TODO Auto-generated method stub
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnMain=new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
		
		
		
		JPanel pnThongTinChiTiet=new JPanel();
		pnMain.add(pnThongTinChiTiet);

		
		
		JPanel pnButtonChiTiet=new JPanel();
		pnButtonChiTiet.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnThoat=new JButton("Thoat");
		JPanel pnTim=new JPanel();
		JLabel lblTim=new JLabel("Thang:");
	
		txtTim=new JTextField(20);
		btnLietKe=new JButton("Liệt kê");
		pnTim.add(lblTim);
		pnTim.add(txtTim);
		pnTim.add(btnLietKe);
		pnMain.add(pnTim,BorderLayout.SOUTH);
		pnThongTinChiTiet.add(pnButtonChiTiet);

		dtnLuong=new DefaultTableModel();
		dtnLuong.addColumn("Mã Giáo viên");
		dtnLuong.addColumn("Tên Giáo Viên");
		dtnLuong.addColumn("Số ngày làm");
		dtnLuong.addColumn("Hệ số lương");
		dtnLuong.addColumn("Lương căn bản");
		dtnLuong.addColumn("Lương");
		dtnLuong.addColumn("Tháng");
		tblLuong=new JTable(dtnLuong);
		
		JScrollPane sc = new JScrollPane(tblLuong, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		pnMain.add(sc,BorderLayout.CENTER);
	
		con.add(pnMain,BorderLayout.CENTER);
		
	}
	public void showWindown() {
		// TODO Auto-generated method stub
		this.setSize(800, 550);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// this.setResizable(false);//không cho thay đổi kích cở cữa sổ
		this.setVisible(true);
	}
}
