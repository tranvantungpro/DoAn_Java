package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.impl.ior.JIDLObjectKeyTemplate;

import Moudle.GiangVien;
import Moudle.LopHoc;
import Moudle.PhongHoc;
import Service.KetNoiCTH;
import Service.KetNoiGV;
import Service.KetNoiLH;
import Service.KetNoiPhongHoc;
import Service.KetNoiSQLQuyen;



public class ManHinhPhongHoc extends JFrame{
	DefaultTableModel dtnPhongHoc,dtnPhongHoc1;
	JTable tblPhongHoc,tblPhongHoc1;
	JButton btnFrist,btnLast,btnNext,btnPrevious;
	JTextArea txtThongTin;
	JTextField txtMaPH,txtTenPH,txtSucChua,txtTrangThai,txtTim;
	JButton btnTimKiem,btnThoat;
	Connection con=null;
	Statement sta=null;
	ResultSet result=null;
	ResultSet result1=null;
	PreparedStatement preStatement=null;
	
public ManHinhPhongHoc (String title)
{
	super(title);
	addcontrols();
	addEvent();
	showWindown();
	ketNoiCoSoDuLieu();
	HienThiToanBoPhongHoc();
	//xuLyTimKiem1();
}


public void addEvent() {
	// TODO Auto-generated method stub

	btnTimKiem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(txtTim.getText().equals(""))
			{
				JOptionPane.showConfirmDialog(null,"bạn chưa nhập thông tin tìm kiếm","Thông báo", JOptionPane.OK_OPTION);
			}
			else {
			xuLyTimKiem();
				xuLyTimKiem1();
			
		}
		}
	});
}
public void HienThiToanBoPhongHoc() {
	try
	{
		CallableStatement callStatement=con.prepareCall("{call QuanLiCoSoVatChat}");
		CallableStatement callStatement1=con.prepareCall("{call QuanLi}");	
		result=callStatement.executeQuery();
		result1=callStatement1.executeQuery();
		dtnPhongHoc.setRowCount(0);
		dtnPhongHoc1.setRowCount(0);
		while(result.next())
		{
			Vector<Object> vec=new Vector<Object>();
			vec.add(result.getString("MaPH"));
			vec.add(result.getString("TenPH"));
			vec.add(result.getString("MaLH"));
			vec.add(result.getString("TenLH"));
			dtnPhongHoc.addRow(vec);
		}
		while(result1.next())
		{
			Vector<Object> vec1=new Vector<Object>();
			vec1.add(result1.getString("TenPH"));
			vec1.add(result1.getInt("solopgiangday"));
			dtnPhongHoc1.addRow(vec1);
		}
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
}

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
		String sql= "{call QuanLiPhongHoc(?) }";
		CallableStatement callStatement=con.prepareCall(sql);
		callStatement.setString(1,txtTim.getText());	
		result=callStatement.executeQuery();
		dtnPhongHoc.setRowCount(0);
		while(result.next())
		{
			Vector<Object> vec=new Vector<Object>();
			vec.add(result.getString("MaPH"));
			vec.add(result.getString("TenPH"));
			vec.add(result.getString("MaLH"));
			vec.add(result.getString("TenLH"));
			dtnPhongHoc.addRow(vec);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
protected void xuLyTimKiem1() {
	// TODO Auto-generated method stub
	try
	{
		String sql1= "{call QLi(?) }";
		CallableStatement callStatement1=con.prepareCall(sql1);
	//	CallableStatement callStatement1=con.prepareCall("{call QuanLi}");	
		callStatement1.setString(1,txtTim.getText());
		result1=callStatement1.executeQuery();
		dtnPhongHoc1.setRowCount(0);
		while(result1.next())
		{
			Vector<Object> vec1=new Vector<Object>();
			vec1.add(result1.getString("TenPH"));
			vec1.add(result1.getString("solopgiangday"));
			dtnPhongHoc1.addRow(vec1);
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
	JLabel lblTim=new JLabel("Nhập dữ liệu:");
	txtTim=new JTextField(20);
	btnTimKiem=new JButton("Tìm");
	pnTim.add(lblTim);
	pnTim.add(txtTim);
	pnTim.add(btnTimKiem);
	pnMain.add(pnTim,BorderLayout.SOUTH);
	pnThongTinChiTiet.add(pnButtonChiTiet);

	dtnPhongHoc=new DefaultTableModel();
	dtnPhongHoc.addColumn("Mã phòng học");
	dtnPhongHoc.addColumn("Tên phòng học");
	dtnPhongHoc.addColumn("Mã lớp học");
	dtnPhongHoc.addColumn("Tên Lớp Học");
	
	dtnPhongHoc1=new DefaultTableModel();
	dtnPhongHoc1.addColumn("Tên Lớp Học");
	dtnPhongHoc1.addColumn("Số Lớp Học");
	
	tblPhongHoc=new JTable(dtnPhongHoc);
	tblPhongHoc1=new JTable(dtnPhongHoc1);
	JScrollPane sc = new JScrollPane(tblPhongHoc, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JScrollPane sc1 = new JScrollPane(tblPhongHoc1, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	pnMain.add(sc,BorderLayout.CENTER);
	pnMain.add(sc1,BorderLayout.CENTER);
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
