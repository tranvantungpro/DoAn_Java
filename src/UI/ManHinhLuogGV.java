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

import javax.swing.AbstractButton;
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
import Moudle.Luong;
import Moudle.NhanSu;
import Moudle.PhongBan;
import Service.KetNoiGV;
import Service.KetNoiLuong;
import Service.KetNoiNV;
import Service.KetNoiPhongBan;

public class ManHinhLuogGV extends JFrame {
	public ManHinhLuogGV (String title)
	{
		super(title);
		addContronls();
		addEvents();
		showWindow();
		HienThiToanBoLuong();
		hienThiDanhMucLenList();
		hienThicboPB();
	}
	JTextField txtHsl, txtMaGV, txtTenGV, txtHSluong, txtLuongcb ,txtTim;  
	JButton btnThem, btnXoa, btnSua, btnTimKiem, btnTaoMoi, btnIn, btnQuayLai,btnTinhLuong;
	DefaultTableModel dtmNhanSu;
	JTable  tblNhanSu;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<Luong> dsL = null;
	ArrayList<Luong> dsThem = null;
	ArrayList<Luong> dsLTim = null;
	static String MaHSL = "";
	JComboBox<GiangVien>cboPB;
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
							"Ban có chắc muốn xóa không Nhân Viên:"+txtTenGV.getText(),
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
				Luong ns = dsL.get(row);
				txtHsl.setText(ns.getMaHSL());
				txtMaGV.setText(ns.getMaGV());
				hienThicboPBthemMa(ns.getTenGV());
//				(int) txtHSluong.setText( ns.getHsoL());
//				txtLuongcb.setText(ns.getLuongCb());
//				
				
				
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
				txtHsl.setText(LayMaNS());
				txtTenGV.setText(""); 
				txtMaGV.setText("");
				txtHSluong.setText("");
				txtLuongcb.setText("");
				HienThiToanBoLuong();
				hienThiDanhMucLenList();
				hienThicboPB();
				cboPB.setSelectedItem(null);
			}
		});

//		btnTinhLuong.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MHXepLichGV xeplich = new MHXepLichGV("Tính Lương");
////				xeplich.setFocusable(true);
////				xeplich.setVisible(true);
//				//xeplich.ShowWindow();
//			}
//		});

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

	protected void hienThicboPhongBanthemMa(String magv) {
		KetNoiGV knpb=new KetNoiGV();
		Vector<GiangVien>vec1=knpb.hienThicboGVthemMa(magv); 
		cboPB.removeAllItems();
		
		KetNoiGV dmService=new KetNoiGV();
		Vector<GiangVien>vec=dmService.docToanBoDanhMuc();
		GiangVien a = new GiangVien();
		a = (GiangVien) vec1.toArray()[0];
		for(GiangVien dm : vec)
		{
			cboPB.addItem(dm);
			if(dm.getTenGV().equals(a.getTenGV()))
				cboPB.setSelectedItem(dm);
		} 
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
		JLabel lblMaLuong=new JLabel("Mã Hệ Số Lương");
		txtHsl=new JTextField(20);
		pnMa.add(lblMaLuong);
		pnMa.add(txtHsl);
		pnThongTinChiTiet.add(pnMa);
		 
		JPanel pnMaGV=new JPanel();
		pnMaGV.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel lblMaGV=new JLabel("Mã Giảng Viên");
		txtMaGV=new JTextField(20);
		pnMaGV.add(lblMaGV);
		pnMaGV.add(txtMaGV);
		pnThongTinChiTiet.add(pnMaGV);
		
		JPanel pnTenGV = new JPanel();
		pnTenGV.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenGV = new JLabel("Tên Giang vien: ");
		lblTenGV.setFont(new Font("Arial", Font.PLAIN, 15));
		cboPB=new JComboBox<GiangVien>();
		cboPB.setPreferredSize(new Dimension(270, 20));
		pnTenGV.add(lblTenGV);
		pnTenGV.add(cboPB);
		pnThongTinChiTiet.add(pnTenGV);
		
		JPanel pnHSL=new JPanel();
		pnHSL.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblHSL=new JLabel("Hệ số lương");
		txtHSluong=new JTextField(20);
		pnHSL.add(lblHSL);
		pnHSL.add(txtHSluong);
		pnThongTinChiTiet.add(pnHSL);
		 
		JPanel pnLcb=new JPanel();
		pnLcb.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLcb=new JLabel("Lương căn bản");
		txtLuongcb=new JTextField(20);
		pnLcb.add(lblLcb);
		pnLcb.add(txtLuongcb);
		pnThongTinChiTiet.add(pnLcb);


		
		JPanel pnButtonChiTiet=new JPanel();
		pnButtonChiTiet.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnTaoMoi=new JButton("Tạo Mới");
		btnThem=new JButton("Thêm");
		btnSua=new JButton("Cập Nhật");
		btnQuayLai=new JButton("Quay Lại");
		btnXoa=new JButton("Xóa");
		btnTinhLuong = new JButton("Lương");
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
		lblMaLuong.setPreferredSize(lblLcb.getPreferredSize());
		lblMaGV.setPreferredSize(lblLcb.getPreferredSize());
		lblTenGV.setPreferredSize(lblLcb.getPreferredSize());
		lblHSL.setPreferredSize(lblLcb.getPreferredSize());
		lblLcb.setPreferredSize(lblLcb.getPreferredSize());
		
		JPanel pnNorth=new JPanel();
		pnNorth.setLayout(new BorderLayout());
		
		
		//pnTop
		dtmNhanSu = new DefaultTableModel();
		dtmNhanSu.addColumn("Mã Hệ số lương");
		dtmNhanSu.addColumn("Mã Nhân Viên");
		dtmNhanSu.addColumn("Tên Nhân Viên");
		dtmNhanSu.addColumn("Hệ Số Lương");
		dtmNhanSu.addColumn("Lương Căn Bản");
	
		
		
		tblNhanSu = new JTable(dtmNhanSu);
		JScrollPane sptable = new JScrollPane(tblNhanSu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnMain.add(sptable,BorderLayout.CENTER);
		con.add(pnMain,BorderLayout.CENTER);


	}

	public void showWindow()
	{
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void hienThiDanhMucLenList() {
		KetNoiGV dmService=new KetNoiGV();
		Vector<GiangVien>vec=dmService.docToanBoDanhMuc();
		//lisLoaiLH.setListData(vec);
		cboPB.removeAllItems();
		for(GiangVien dm : vec)
		{
			cboPB.addItem(dm);
		}
	}


	public void hienThicboPB() {
		KetNoiGV dmService=new KetNoiGV();
		Vector<GiangVien>vec=dmService.docToanBoDanhMuc();
		cboPB.removeAllItems();
		for(GiangVien dm : vec)
		{
			cboPB.addItem(dm);
		}
	}

	public void hienThicboPBthemMa(String magv) 
	{
		KetNoiGV kngv=new KetNoiGV();
		Vector<GiangVien>vec1=kngv.hienThicboGVthemMa(magv);
		cboPB.removeAllItems();
		
		KetNoiGV knllh1=new KetNoiGV();
		Vector<GiangVien>vec=knllh1.docToanBoDanhMuc();
		GiangVien a = new GiangVien();
		a = (GiangVien) vec1.toArray()[0];
		for(GiangVien dm : vec)
		{
			cboPB.addItem(dm);
			if(dm.getTenGV().equals(a.getTenGV()))
				cboPB.setSelectedItem(dm);
		}
	}

	protected void CapNhatNS() 
	{
		Luong ns = new Luong();
		ns.setMaHSL(txtHsl.getText());
		ns.setMaGV(txtMaGV.getText());
		ns.setTenGV(LayMaGV(cboPB.getSelectedItem().toString()));	
//		ns.setHsoL(txtHSluong.getText());
//		ns.setLuongCb(txtLuongcb.getText());
		
		///
		KetNoiLuong kn =  new KetNoiLuong();
		if(kn.CapNhatLuong(ns) > 0)
		{
			JOptionPane.showMessageDialog(null, "Cập nhật lớp học thành công");
			HienThiToanBoLuong();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Cập nhật lớp học thất bại");
		}

	}

	protected void XoaLopHoc() 
	{
		Luong ns = new Luong();
		ns.setMaHSL(txtHsl.getText());
		KetNoiLuong kn =  new KetNoiLuong();
		if(kn.XoaLuong(ns) > 0)
		{
			JOptionPane.showMessageDialog(null, "Xóa lớp học thành công");
			HienThiToanBoLuong();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa lớp học thất bại");
		}
	}

	protected void LuuMoiLH()  
	{
		 
		Luong ns = new Luong();
		ns.setMaHSL(txtHsl.getText());
		ns.setMaGV(txtMaGV.getText());
		ns.setTenGV(LayMaGV(cboPB.getSelectedItem().toString()));	
//		ns.setHsoL(txtHSluong.getText());
//		ns.setLuongCb(txtLuongcb.getText());
		
		//them
		KetNoiLuong kn =  new KetNoiLuong();
		if(kn.ThemMoiLuong(ns) > 0)
		{
			JOptionPane.showMessageDialog(null, "Lưu lớp học thành công");
			HienThiToanBoLuong();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Lưu lớp học thất bại");
		}

	}
	public String LayMaGV(String tengv)
	{
		KetNoiGV kngv = new KetNoiGV();
		GiangVien a = new GiangVien();
		a = kngv.LayMaGV(tengv);
		 
		return a.getMaGV();
	}
	public String LayMaNS()
	{
		KetNoiLuong knns = new KetNoiLuong();
		Luong a = new Luong();
		a = knns.LayMaLuong();
		MaHSL= a.getMaHSL();

		int ViTriCuoi=MaHSL.lastIndexOf("L");
		String TenMa=MaHSL.substring(ViTriCuoi+1);
		int b = Integer.parseInt(TenMa);
		int c = b+1;
		String MaNS = String.format("L%03d", c);
		return MaNS;
	}

	public void HienThiToanBoLuong()
	{
		KetNoiLuong knnv = new KetNoiLuong();
		dsL = knnv.LayToanBoLuong();
		dtmNhanSu.setRowCount(0);
		for(Luong a : dsL)
		{
			KetNoiGV knlh = new KetNoiGV(); 
			String b  = knlh.LayTenGV(a.getMaGV());	
			Vector<Object> vec = new Vector<Object>();
			vec.add(a.getMaHSL());
			vec.add(a.getMaGV());
			vec.add(b);
			vec.add(a.getHsoL());
			vec.add(a.getLuongCb());
			
			dtmNhanSu.addRow(vec);
		}
	} 

	public void HienThiTim()
	{
		if(txtTim.getText().equals(""))
		{
			HienThiToanBoLuong();
			JOptionPane.showMessageDialog(null, "Vui lòng nhập giáo trình cần tìm!!!");
		}
		else
		{
			String a1 = txtTim.getText();
			KetNoiLuong knns = new KetNoiLuong();
			dsLTim = knns.TimLuong(a1);
			dtmNhanSu.setRowCount(0);
			for(Luong a : dsLTim)
			{
				Vector<Object> vec = new Vector<Object>();
				vec.add(a.getHsoL());
				vec.add(a.getMaGV());
				vec.add(a.getTenGV());
				vec.add(a.getHsoL());
				vec.add(a.getLuongCb());
				
				dtmNhanSu.addRow(vec);
			}
		}
	}

	public int KiemTaCuPhap()
	{	
		if(txtTenGV.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Sai cú pháp. Vui lòng kiểm tra lại");
			return 1;
		}
		
		return 0;
	}

	public int KiemTraTonTai()
	{
		KetNoiLuong knns = new KetNoiLuong();
		return knns.KiemTraTonTai(txtTenGV.getText());
		 
	}

	public String LayNgayHienHanh()
	{
		Date t = cal.getTime();
		return df.format(t);
	}

}
