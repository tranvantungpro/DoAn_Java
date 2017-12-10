package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Moudle.GiangVien;
import Moudle.NhanSu;
import Moudle.PhongBan;
import Service.KetNoiGV;

public class ManHinhQuanLiGiangVien extends JFrame{
	public ManHinhQuanLiGiangVien (String title)
	{
		super(title);
		addContronls();
		addEvents();
		showWindow();
		HienThiToanBoNS();
	}
	JTextField txtMa, txtTen, txtsdt, txtdiachi,txtNgayBt,txtNgayKT ,txtTim;  
	JButton btnThem, btnXoa, btnSua, btnTimKiem, btnTaoMoi, btnTinhLuong, btnQuayLai;
	DefaultTableModel dtmNhanSu;
	JTable  tblNhanSu;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<GiangVien> dsGV = null;
	ArrayList<GiangVien> dsThem = null;
	ArrayList<GiangVien> dsGVTim = null;
	static String MaGV = "";
	Calendar cal = Calendar.getInstance();


	private void addEvents() 
	{
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{ 

				HienThiTim();
			}

		});

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 if(KiemTraTonTai()==0)
				 {
					if(KiemTaCuPhap()==0)
					{
						LuuMoiLH();
						 
					}
					else
					{
						btnTaoMoi.doClick();
					}
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "Lớp học đã tồn tại. Vui lòng nhập lại!");
					 btnTaoMoi.doClick();
				 }
				 


			}
		});

		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(KiemTaCuPhap()==0)
				{
					int ret=JOptionPane.showConfirmDialog(null,
							"Ban có chắc muốn xóa không Nhân Viên:"+txtTen.getText(),
							"xác nhận xóa",
							JOptionPane.OK_CANCEL_OPTION);
					if(ret==JOptionPane.CANCEL_OPTION)
						return;
					//JOptionPane.showMessageDialog(null, "Bạn có chắc muốn xóa Nhân Viên:"+txtTen.getText(),"Thông Báo",JOptionPane.OK_CANCEL_OPTION);
					else
				{
					XoaLopHoc();
					 
				}
				}
				else
				{
					btnTaoMoi.doClick();
				}

			}
		});

		tblNhanSu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=tblNhanSu.getSelectedRow();
				if(row==-1)return; 
				GiangVien ns = dsGV.get(row);
				txtMa.setText(ns.getMaGV());
				txtTen.setText(ns.getTenGV());
				txtsdt.setText(ns.getSdt());
				txtdiachi.setText(ns.getDiaChi());
				txtNgayBt.setText(df.format(ns.getNgayVL()));
				txtNgayKT.setText(df.format(ns.getNgayKT()));
				
			}
		});

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(KiemTaCuPhap()==0)
				{

					CapNhatNS();
					 
				}
				else
				{
					btnTaoMoi.doClick();
				}


			}
		});

		btnTaoMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				txtMa.setText(LayMaNS());
				txtTen.setText(""); 
				txtsdt.setText("");
				txtdiachi.setText("");
				txtNgayBt.setText(LayNgayHienHanh());
				txtNgayKT.setText(""); 
				HienThiToanBoNS();
				
			}
		});

		btnTinhLuong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MHXepLichGV xeplich = new MHXepLichGV("Tính Lương");
//				xeplich.setFocusable(true);
//				xeplich.setVisible(true);
				//xeplich.ShowWindow();
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

	

	protected void NhapTT() {


	}

	private void addContronls() 
	{

		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnMain=new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
		
		
		
		JPanel pnThongTinChiTiet=new JPanel();
		//GridLayout(4, 2) là 4 dòng 2 cột
		pnThongTinChiTiet.setLayout(new GridLayout(4, 2));
		pnMain.add(pnThongTinChiTiet,BorderLayout.NORTH);
		
		Border boderThongtinchitiet=BorderFactory
				.createLineBorder(Color.RED);
	 	TitledBorder bodertitleSoThich=new TitledBorder(boderThongtinchitiet,"Thông tin chi tiết");
	 	pnThongTinChiTiet.setBorder(bodertitleSoThich);
	 	bodertitleSoThich.setTitleColor(Color.RED);

		JPanel pnMa=new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		//pnMa.setPreferredSize(new Dimension(10,20));
		JLabel lblMa=new JLabel("Mã Giảng Viên");
		txtMa=new JTextField(20);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		pnThongTinChiTiet.add(pnMa);
		 
		JPanel pnTen=new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel lblTen=new JLabel("Tên Giảng Viên");
		txtTen=new JTextField(20);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		pnThongTinChiTiet.add(pnTen);
		
		JPanel pnSdt=new JPanel();
		pnSdt.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSdt=new JLabel("Số điện thoại");
		txtsdt=new JTextField(20);
		pnSdt.add(lblSdt);
		pnSdt.add(txtsdt);
		pnThongTinChiTiet.add(pnSdt);
		 
		JPanel pnDiachi=new JPanel();
		pnDiachi.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDiaChi=new JLabel("Địa Chỉ");
		txtdiachi=new JTextField(20);
		pnDiachi.add(lblDiaChi);
		pnDiachi.add(txtdiachi);
		pnThongTinChiTiet.add(pnDiachi);

		
		JPanel pnngSinh=new JPanel();
		pnngSinh.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblVL=new JLabel("Ngày Vào Làm");
		txtNgayBt=new JTextField(20);
		pnngSinh.add(lblVL);
		pnngSinh.add(txtNgayBt);
		pnThongTinChiTiet.add(pnngSinh);
		 
		
		JPanel pnNgaykt=new JPanel();
		pnNgaykt.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgKt=new JLabel("Ngày kết thúc");
		txtNgayKT=new JTextField(20);
		pnNgaykt.add(lblNgKt);
		pnNgaykt.add(txtNgayKT);
		pnThongTinChiTiet.add(pnNgaykt);
		
		
		
		JPanel pnButtonChiTiet=new JPanel();
		pnButtonChiTiet.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnTaoMoi=new JButton("Tạo Mới");
		btnThem=new JButton("Thêm");
		btnSua=new JButton("Cập Nhật");
		btnQuayLai=new JButton("Quay Lại");
		btnXoa=new JButton("Xóa");
		btnTinhLuong = new JButton("Tính Lương");
		JPanel pnTim=new JPanel();
		JLabel lblTim=new JLabel("Nhập dữ liệu:");
		txtTim=new JTextField(20);
		btnTimKiem=new JButton("Tìm");
		pnTim.add(lblTim);
		pnTim.add(txtTim);
		pnTim.add(btnTimKiem);
		pnButtonChiTiet.add(btnTaoMoi);
		pnButtonChiTiet.add(btnThem);
		pnButtonChiTiet.add(btnSua);
		pnButtonChiTiet.add(btnXoa);
		pnButtonChiTiet.add(btnQuayLai);
		pnButtonChiTiet.add(btnTinhLuong);
		pnMain.add(pnTim,BorderLayout.SOUTH);
		pnThongTinChiTiet.add(pnButtonChiTiet);
		//làm cho đẹp
		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblTen.setPreferredSize(lblTen.getPreferredSize());
		lblVL.setPreferredSize(lblTen.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTen.getPreferredSize());
		lblSdt.setPreferredSize(lblTen.getPreferredSize());
		lblNgKt.setPreferredSize(lblTen.getPreferredSize());
		JPanel pnNorth=new JPanel();
		pnNorth.setLayout(new BorderLayout());
		
		
		//pnTop
		dtmNhanSu = new DefaultTableModel();
		dtmNhanSu.addColumn("Mã giảng viên");
		dtmNhanSu.addColumn("Tên giảng viên");
		dtmNhanSu.addColumn("số điện thoại");
		dtmNhanSu.addColumn("Địa chỉ");
		dtmNhanSu.addColumn("Ngày Bắt Đầu");
		dtmNhanSu.addColumn("Ngày Kết Thúc");
		
		
		tblNhanSu = new JTable(dtmNhanSu);
		JScrollPane sptable = new JScrollPane(tblNhanSu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnMain.add(sptable,BorderLayout.CENTER);
		con.add(pnMain,BorderLayout.CENTER);


	}

	public void showWindow()
	{
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	
	protected void CapNhatNS() 
	{
		GiangVien gv = new GiangVien();
		gv.setMaGV(txtMa.getText());
		gv.setTenGV(txtTen.getText());
		gv.setSdt(txtsdt.getText());
		gv.setDiaChi(txtdiachi.getText());
		
		//ngaybd
		Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(df.parse(txtNgayBt.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gv.setNgayVL(sqlDate);
		
		//ngaykt
		Date sqlDate1 = null;
		try {
			sqlDate1 = new java.sql.Date(df.parse(txtNgayKT.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gv.setNgayKT(sqlDate1);
		///
		KetNoiGV kn =  new KetNoiGV();
		if(kn.CapNhatGiangVien(gv) > 0)
		{
			JOptionPane.showMessageDialog(null, "Cập nhật lớp học thành công");
			HienThiToanBoNS();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Cập nhật lớp học thất bại");
		}

	}

	protected void XoaLopHoc() 
	{
		GiangVien gv = new GiangVien();
		gv.setMaGV(txtMa.getText());
		KetNoiGV kn =  new KetNoiGV();
		if(kn.XoaGiangVien(gv) > 0)
		{
			JOptionPane.showMessageDialog(null, "Xóa lớp học thành công");
			HienThiToanBoNS();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa lớp học thất bại");
		}
	}

	protected void LuuMoiLH()  
	{
		 
		GiangVien gv = new GiangVien();
		gv.setMaGV(txtMa.getText());
		gv.setTenGV(txtTen.getText());
		gv.setSdt(txtsdt.getText());
		gv.setDiaChi(txtdiachi.getText());
		
		//ngaybd
	
		///
		 
		//them
		KetNoiGV kn =  new KetNoiGV();
		if(kn.ThemMoiGiangVien(gv) > 0)
		{
			JOptionPane.showMessageDialog(null, "Lưu lớp học thành công");
			HienThiToanBoNS();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Lưu lớp học thất bại");
		}

	}
	
	public String LayMaNS()
	{
		KetNoiGV knns = new KetNoiGV();
		GiangVien a = new GiangVien();
		a = knns.LayMaGV();
		MaGV= a.getMaGV();

		int ViTriCuoi=MaGV.lastIndexOf("V");
		String TenMa=MaGV.substring(ViTriCuoi+1);
		int b = Integer.parseInt(TenMa);
		int c = b+1;
		String MaNS = String.format("GV%03d", c);
		return MaNS;
	}

	public void HienThiToanBoNS()
	{
		KetNoiGV kngv = new KetNoiGV();
		dsGV = kngv.LayToanBoGV();
		dtmNhanSu.setRowCount(0);
		for(GiangVien a : dsGV)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(a.getMaGV());
			vec.add(a.getTenGV());
			vec.add(a.getSdt());
			vec.add(a.getDiaChi());
			vec.add(a.getNgayVL());
			vec.add(a.getNgayKT());
			
			dtmNhanSu.addRow(vec);
		}
	} 

	public void HienThiTim()
	{
		if(txtTim.getText().equals(""))
		{
			HienThiToanBoNS();
			JOptionPane.showMessageDialog(null, "Vui lòng nhập giáo trình cần tìm!!!");
		}
		else
		{
			String a1 = txtTim.getText();
			KetNoiGV knns = new KetNoiGV();
			dsGVTim = knns.TimGiangVien(a1);
			dtmNhanSu.setRowCount(0);
			for(GiangVien a : dsGVTim)
			{
				Vector<Object> vec = new Vector<Object>();
				vec.add(a.getMaGV());
				vec.add(a.getTenGV());
				vec.add(a.getSdt());
				vec.add(a.getDiaChi());
				vec.add(a.getNgayVL());
				vec.add(a.getNgayKT());
				dtmNhanSu.addRow(vec);
			}
		}
	}

	public int KiemTaCuPhap()
	{	
		if(txtTen.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Sai cú pháp. Vui lòng kiểm tra lại");
			return 1;
		}
		if(txtNgayBt.getText().equals(""))
		{
			return 1;
		}
		
		if(txtNgayKT.getText().equals(""))
		{
			return 1;
		}
		
		return 0;
	}

	public int KiemTraTonTai()
	{
		KetNoiGV knns = new KetNoiGV();
		return knns.KiemTraTonTai(txtTen.getText());
		 
	}

	public String LayNgayHienHanh()
	{
		Date t = cal.getTime();
		return df.format(t);
	}
}
