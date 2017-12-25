package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.ImageIcon;
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
	DefaultTableModel dtnPhongHoc,dtnPhongHoc1,dtnPhongHoc2;
	JTable tblPhongHoc,tblPhongHoc1,tblPhongHoc2;
	JButton btnFrist,btnLast,btnNext,btnPrevious;
	JTextArea txtThongTin;
	JTextField txtMaPH,txtTenPH,txtSucChua,txtTrangThai,txtTim;
	JButton btnTimKiem,btnQuayLai;
	Connection con=null;
	Statement sta=null;
	ResultSet result=null;
	ResultSet result1=null;
	ResultSet result2=null;
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
				xuLyTimKiem2();
			
		}
		}
	});
	btnQuayLai.addActionListener(new ActionListener() 
	{	
		@Override
		public void actionPerformed(ActionEvent e) {
			QuayLai();
			ManHinhChinh ui = new ManHinhChinh("Quản Lý Trung Tâm Tin Học");
			ui.showWindows();
		}
	});
}
public void QuayLai() 
{
	this.dispose(); 
}
public void HienThiToanBoPhongHoc() {
	try
	{
		CallableStatement callStatement=con.prepareCall("{call QuanLiCoSoVatChat}");
		CallableStatement callStatement1=con.prepareCall("{call PhongBan}");	
		CallableStatement callStatement2=con.prepareCall("{call PhongRanh}");	
		result=callStatement.executeQuery();
		result1=callStatement1.executeQuery();
		result2=callStatement2.executeQuery();
		dtnPhongHoc.setRowCount(0);
		dtnPhongHoc1.setRowCount(0);
		dtnPhongHoc2.setRowCount(0);
		while(result.next())
		{
			Vector<Object> vec=new Vector<Object>();
			vec.add(result.getString("MaPH"));
			vec.add(result.getString("TenPH"));
			vec.add(result.getString("MaLH"));
			vec.add(result.getString("TenLH"));
			vec.add(result.getString("SiSoHT"));
			vec.add(result.getString("SucChua"));
			dtnPhongHoc.addRow(vec);
		}
		while(result1.next())
		{
			Vector<Object> vec1=new Vector<Object>();
			vec1.add(result1.getString("TenPH"));
			vec1.add(result1.getInt("SucChua"));
			vec1.add(result1.getInt("SoLopHoc"));
			dtnPhongHoc1.addRow(vec1);
		}
		while(result2.next())
		{
			Vector<Object> vec2=new Vector<Object>();
			vec2.add(result2.getString("TenPH"));
			vec2.add(result2.getInt("SucChua"));
			vec2.add(result2.getInt("SoLopHoc"));
			dtnPhongHoc2.addRow(vec2);
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
		String sql= "{call TimKiemTenPH(?) }";
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
			vec.add(result.getString("SiSoHT"));
			vec.add(result.getString("SucChua"));
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
		String sql1= "{call TKPhongBan(?) }";
		CallableStatement callStatement1=con.prepareCall(sql1);
	//	CallableStatement callStatement1=con.prepareCall("{call QuanLi}");	
		callStatement1.setString(1,txtTim.getText());
		result1=callStatement1.executeQuery();
		dtnPhongHoc1.setRowCount(0);
		while(result1.next())
		{
			Vector<Object> vec1=new Vector<Object>();
			vec1.add(result1.getString("TenPH"));
			vec1.add(result1.getInt("SucChua"));
			vec1.add(result1.getInt("SoLopHoc"));
			dtnPhongHoc1.addRow(vec1);
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
protected void xuLyTimKiem2() {
	// TODO Auto-generated method stub
	try
	{
		String sql1= "{call TKPhongRanh(?) }";
		CallableStatement callStatement2=con.prepareCall(sql1);
	//	CallableStatement callStatement1=con.prepareCall("{call QuanLi}");	
		callStatement2.setString(1,txtTim.getText());
		result2=callStatement2.executeQuery();
		dtnPhongHoc2.setRowCount(0);
		while(result2.next())
		{
			Vector<Object> vec2=new Vector<Object>();
			vec2.add(result2.getString("TenPH"));
			vec2.add(result2.getInt("SucChua"));
			vec2.add(result2.getInt("SoLopHoc"));
			dtnPhongHoc1.addRow(vec2);
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
	
	JPanel pnTieuDe = new JPanel();
	JLabel lblTieude = new JLabel("QUẢN LÝ PHÒNG HỌC");
	lblTieude.setForeground(Color.BLUE);
	lblTieude.setFont(new Font("Times New Roman", Font.BOLD, 25));
	lblTieude.setIcon(new ImageIcon("Hinh/QLPH.png"));
	pnTieuDe.add(lblTieude);
	pnMain.add(pnTieuDe);
	
	
	JPanel pnThongTinChiTiet=new JPanel();
	pnMain.add(pnThongTinChiTiet);
	
	
	
	JPanel pnButtonChiTiet=new JPanel();
	pnButtonChiTiet.setLayout(new FlowLayout(FlowLayout.RIGHT));
	JPanel pnTim=new JPanel();
	JLabel lblTim=new JLabel("Nhập dữ liệu:");
	txtTim=new JTextField(20);
	btnTimKiem=new JButton("Tìm");
	btnTimKiem.setIcon(new ImageIcon("Hinh/TK.png"));
	btnQuayLai=new JButton("Quay Lại");
	btnQuayLai.setIcon(new ImageIcon("Hinh/QL.png"));
	pnTim.add(lblTim);
	pnTim.add(txtTim);
	pnTim.add(btnTimKiem);
	pnTim.add(btnQuayLai);
	pnMain.add(pnTim,BorderLayout.SOUTH);
	
	pnThongTinChiTiet.add(pnButtonChiTiet);

	dtnPhongHoc=new DefaultTableModel();
	dtnPhongHoc.addColumn("Mã phòng học");
	dtnPhongHoc.addColumn("Tên phòng học");
	dtnPhongHoc.addColumn("Mã lớp học");
	dtnPhongHoc.addColumn("Tên Lớp Học");
	dtnPhongHoc.addColumn("Sĩ Số Hiện Tại");
	dtnPhongHoc.addColumn("Sức chứa");
	
	dtnPhongHoc1=new DefaultTableModel();
	dtnPhongHoc1.addColumn("Tên Phòng Học");
	dtnPhongHoc1.addColumn("Sức chứa");
	dtnPhongHoc1.addColumn("Số lớp học");
	
	dtnPhongHoc2=new DefaultTableModel();
	dtnPhongHoc2.addColumn("Tên Phòng Học");
	dtnPhongHoc2.addColumn("Sức chứa");
	dtnPhongHoc2.addColumn("Số lớp học");
	
	tblPhongHoc=new JTable(dtnPhongHoc);
	tblPhongHoc1=new JTable(dtnPhongHoc1);
	tblPhongHoc2=new JTable(dtnPhongHoc2);
	JScrollPane sc = new JScrollPane(tblPhongHoc, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JScrollPane sc1 = new JScrollPane(tblPhongHoc1, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JScrollPane sc2 = new JScrollPane(tblPhongHoc2, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	pnMain.add(sc,BorderLayout.CENTER);
	JPanel pnHSL=new JPanel();
	pnHSL.setLayout(new FlowLayout(FlowLayout.LEFT));
	JLabel lblHSl=new JLabel("Danh Sách Phòng Bận");
	lblHSl.setForeground(Color.BLACK);
	lblHSl.setFont(new Font("Times New Roman", Font.BOLD, 15));
	pnHSL.add(lblHSl);
	pnMain.add(pnHSL,BorderLayout.NORTH);
	pnMain.add(sc1,BorderLayout.CENTER);
	JPanel pnHS=new JPanel();
	pnHS.setLayout(new FlowLayout(FlowLayout.LEFT));
	JLabel lblHS=new JLabel("Danh Sách Phòng Rảnh");
	lblHS.setForeground(Color.BLACK);
	lblHS.setFont(new Font("Times New Roman", Font.BOLD, 15));
	pnHS.add(lblHS);
	pnMain.add(pnHS,BorderLayout.NORTH);
	pnMain.add(sc2,BorderLayout.CENTER);
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
