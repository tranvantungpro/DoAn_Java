package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import Moudle.TrangThaiNhanVien;
import Service.KetNoiGV;
import Service.KetNoiTrangThaiNhanVien;

public class ManHinhQuanLiGiangVien extends JFrame{
	public ManHinhQuanLiGiangVien (String title)
	{
		super(title);
		addContronls();
		addEvents();
		showWindow();
		HienThiToanBoNS();
		hienThicboTT();
	}
	JTextField txtMa, txtTen, txtsdt, txtdiachi,txtHS,txtLuongCB,txtNgayBt,txtNgayKT ,txtTim,txtSoLop;  
	JButton btnThem,btnXem, btnXoa, btnSua, btnTimKiem, btnTaoMoi,btnChamCong, btnTinhLuong, btnQuayLai;
	DefaultTableModel dtmNhanSu;
	JTable  tblNhanSu;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<GiangVien> dsGV = null;
	ArrayList<GiangVien> dsThem = null;
	ArrayList<GiangVien> dsGVTim = null;
	JComboBox<TrangThaiNhanVien>cboTT;
	static String MaGV = "";
	Calendar cal = Calendar.getInstance();


	public void addEvents() 
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
					 JOptionPane.showMessageDialog(null, "Giáo viên đã tồn tại. Vui lòng nhập lại!");
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
							"Ban có chắc muốn xóa Giáo Viên:"+txtTen.getText(),
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
				txtHS.setText(Float.toString(ns.getHsl()));
				txtLuongCB.setText(Float.toString(ns.getLuongCb()));
				txtNgayBt.setText(df.format(ns.getNgayVL()));
				txtNgayKT.setText(df.format(ns.getNgayKT()));
				txtSoLop.setText(Integer.toString(ns.getSoLop()));
				hienThicboTrangThaithemMa(ns.getMaTT());
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
				txtHS.setText("");
				txtLuongCB.setText("");
				txtNgayBt.setText(LayNgayHienHanh());
				txtNgayKT.setText(""); 
				txtSoLop.setText("0");
				HienThiToanBoNS();
				cboTT.setSelectedItem("Mới");
			}
		});

		btnTinhLuong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ManHinhTinhLuong ui = new ManHinhTinhLuong("Tính Lương Giảng Viên");
				ui.setFocusable(true);
				ui.setVisible(true);
				ui.showWindown();
				
			}
		});
		btnChamCong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ManHinhQuanLiChamCongGV ui = new ManHinhQuanLiChamCongGV("Bảng chấm công Giảng Viên");
				ui.setFocusable(true);
				ui.setVisible(true);
				ui.showWindow();
				ManHinhQuanLiGiangVien.this.hide();
			}
		});
		btnXem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ManHinhXemLichGD ui = new ManHinhXemLichGD("Xem lịch giảng viên");
				ui.setFocusable(true);
				ui.setVisible(true);
				ui.showWindown();
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

	public void addContronls() 
	{

		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnMain=new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));
		//Tiêu đề màn hình giáo viên
		JPanel pnTieuDe = new JPanel();
		JLabel lblTieude = new JLabel("QUẢN LÝ GIÁO VIÊN");
		lblTieude.setForeground(Color.BLUE);
		lblTieude.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieude.setIcon(new ImageIcon("Hinh/QLGV.png"));
		pnTieuDe.add(lblTieude);
		pnMain.add(pnTieuDe);
		
		JPanel pnThongTinChiTiet=new JPanel();
		//GridLayout(4, 2) là 4 dòng 2 cột
		pnThongTinChiTiet.setLayout(new GridLayout(5, 2));
		pnMain.add(pnThongTinChiTiet,BorderLayout.CENTER);
		
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

		
		JPanel pnHSL=new JPanel();
		pnHSL.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblHSl=new JLabel("Hệ số lương:");
		txtHS=new JTextField(20);
		pnHSL.add(lblHSl);
		pnHSL.add(txtHS);
		pnThongTinChiTiet.add(pnHSL);
		
		JPanel pnLuongCb=new JPanel();
		pnLuongCb.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLuongcb=new JLabel("Lương căn bản");
		txtLuongCB=new JTextField(20);
		pnLuongCb.add(lblLuongcb);
		pnLuongCb.add(txtLuongCB);
		pnThongTinChiTiet.add(pnLuongCb);
		 
		JPanel pnNgaybd=new JPanel();
		pnNgaybd.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgbd=new JLabel("Ngày bắt đầu");
		txtNgayBt=new JTextField(20);
		pnNgaybd.add(lblNgbd);
		pnNgaybd.add(txtNgayBt);
		pnThongTinChiTiet.add(pnNgaybd);
		
		JPanel pnNgaykt=new JPanel();
		pnNgaykt.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgKt=new JLabel("Ngày kết thúc");
		txtNgayKT=new JTextField(20);
		pnNgaykt.add(lblNgKt);
		pnNgaykt.add(txtNgayKT);
		pnThongTinChiTiet.add(pnNgaykt);
		
		JPanel pnHS=new JPanel();
		pnHS.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblHS=new JLabel("Số lớp:");
		txtSoLop=new JTextField(20);
		pnHS.add(lblHS);
		pnHS.add(txtSoLop);
		pnThongTinChiTiet.add(pnHS);
		
		JPanel pnLyDo = new JPanel();
		pnLyDo.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLyDo = new JLabel("Trang Thai: ");
		//lblLyDo.setFont(new Font("Arial", Font.PLAIN, 15));
		cboTT=new JComboBox<TrangThaiNhanVien>();
		cboTT.setPreferredSize(new Dimension(270, 20));
		pnLyDo.add(lblLyDo);
		pnLyDo.add(cboTT);
		pnThongTinChiTiet.add(pnLyDo);
		
		JPanel pnButtonChiTiet=new JPanel();
		pnButtonChiTiet.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnTaoMoi=new JButton("Tạo Mới");
		btnThem=new JButton("Thêm");
		btnSua=new JButton("Cập Nhật");
		btnQuayLai=new JButton("Quay Lại");
		btnXoa=new JButton("Xóa");
		btnChamCong = new JButton("Chấm công");
		btnTinhLuong = new JButton("Tính Lương");
		btnXem=new JButton("Xem lich giảng dạy");
		
		btnThem.setIcon(new ImageIcon("Hinh/save.png"));
		btnXoa.setIcon(new ImageIcon("Hinh/delete.png"));
		btnTaoMoi.setIcon(new ImageIcon("Hinh/plus.png"));
		btnSua.setIcon(new ImageIcon("Hinh/update.png"));
		btnQuayLai.setIcon(new ImageIcon("Hinh/QL.png"));
		btnXem.setIcon(new ImageIcon("Hinh/Xem.png"));
		btnTinhLuong.setIcon(new ImageIcon("Hinh/Luong.png"));
		btnChamCong.setIcon(new ImageIcon("Hinh/CC1.png"));
		
		JPanel pnChucNang=new JPanel();
		pnChucNang.setLayout(new FlowLayout());
		pnChucNang.add(pnButtonChiTiet);
		pnMain.add(pnChucNang);
		Border boderchucnang=BorderFactory
				.createLineBorder(Color.RED);
	 	TitledBorder bodertitlechucnang=new TitledBorder(boderchucnang,"Chức năng");
	 	pnChucNang.setBorder(bodertitlechucnang);
	 	bodertitlechucnang.setTitleColor(Color.RED);

	 	pnChucNang.add(btnTaoMoi);
	 	pnChucNang.add(btnThem);
	 	pnChucNang.add(btnSua);
	 	pnChucNang.add(btnXoa);
	 	pnChucNang.add(btnQuayLai);
	 	pnChucNang.add(btnTinhLuong);
	 	pnChucNang.add(btnChamCong);
	 	pnChucNang.add(btnXem);
		
		
		
		JPanel pnTim=new JPanel();
		pnTim.setLayout(new FlowLayout());
		JLabel lblTim=new JLabel("Nhập dữ liệu:");
		txtTim=new JTextField(20);
		btnTimKiem=new JButton("Tìm");
		btnTimKiem.setIcon(new ImageIcon("Hinh/TK.png"));
		pnTim.add(lblTim);
		pnTim.add(txtTim);
		pnTim.add(btnTimKiem);
		pnMain.add(pnTim,BorderLayout.CENTER);
		
		
		//làm cho đẹp
		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblTen.setPreferredSize(lblTen.getPreferredSize());	
		lblSdt.setPreferredSize(lblTen.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTen.getPreferredSize());
		lblHSl.setPreferredSize(lblTen.getPreferredSize());
		lblLuongcb.setPreferredSize(lblLuongcb.getPreferredSize());
		lblNgbd.setPreferredSize(lblLuongcb.getPreferredSize());
		lblNgKt.setPreferredSize(lblLuongcb.getPreferredSize());
		lblLyDo.setPreferredSize(lblLuongcb.getPreferredSize());
		
		
		//pnTop
		dtmNhanSu = new DefaultTableModel();
		dtmNhanSu.addColumn("Mã giảng viên");
		dtmNhanSu.addColumn("Tên giảng viên");
		dtmNhanSu.addColumn("số điện thoại");
		dtmNhanSu.addColumn("Địa chỉ");
		dtmNhanSu.addColumn("Hệ số lương");
		dtmNhanSu.addColumn("Lương căn bản");
		dtmNhanSu.addColumn("Ngày Bắt Đầu");
		dtmNhanSu.addColumn("Ngày Kết Thúc");
		dtmNhanSu.addColumn("Số Lớp");
		dtmNhanSu.addColumn("Trang Thai");
		
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

	public void hienThicboTrangThaithemMa(String magv) 
	{
		KetNoiTrangThaiNhanVien kngv=new KetNoiTrangThaiNhanVien();
		Vector<TrangThaiNhanVien>vec1=kngv.hienThiTTLenCbo(magv);
		cboTT.removeAllItems();

		KetNoiTrangThaiNhanVien knllh1=new KetNoiTrangThaiNhanVien();
		Vector<TrangThaiNhanVien>vec=knllh1.docToanBoTrangThai();
		TrangThaiNhanVien a = new TrangThaiNhanVien();
		a = (TrangThaiNhanVien) vec1.toArray()[0];
		for(TrangThaiNhanVien dm : vec)
		{
			cboTT.addItem(dm);
			if(dm.getLoaiTT().equals(a.getLoaiTT()))
				cboTT.setSelectedItem(dm);
		}
	}
	public void hienThicboTT() {
		KetNoiTrangThaiNhanVien dmService=new KetNoiTrangThaiNhanVien();
		Vector<TrangThaiNhanVien>vec=dmService.docToanBoTrangThai();
		cboTT.removeAllItems();
		for(TrangThaiNhanVien dm : vec)
		{
			cboTT.addItem(dm);
		}
	}
	public String LayMaLoaiTT(String lydo) {
		KetNoiTrangThaiNhanVien tgg = new KetNoiTrangThaiNhanVien();
		TrangThaiNhanVien a = new TrangThaiNhanVien();
		a = tgg.LayMaTT(lydo);
		return a.getMaTT();
	}

	protected void CapNhatNS() 
	{
		GiangVien gv = new GiangVien();
		gv.setMaGV(txtMa.getText());
		gv.setTenGV(txtTen.getText());
		gv.setSdt(txtsdt.getText());
		gv.setDiaChi(txtdiachi.getText());
		gv.setHsl(Float.parseFloat(txtHS.getText()));
		gv.setLuongCb(Float.parseFloat(txtLuongCB.getText()));
		gv.setSoLop(Integer.parseInt(txtSoLop.getText()));
		gv.setMaTT(LayMaLoaiTT(cboTT.getSelectedItem().toString()));
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
		gv.setHsl(Float.parseFloat(txtHS.getText()));
		gv.setLuongCb(Float.parseFloat(txtLuongCB.getText()));
		gv.setSoLop(Integer.parseInt(txtSoLop.getText()));
		gv.setMaTT(LayMaLoaiTT(cboTT.getSelectedItem().toString()));
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
			KetNoiTrangThaiNhanVien kntt = new KetNoiTrangThaiNhanVien(); 
			String d  = kntt.LayTenTrangThai(a.getMaTT());
			Vector<Object> vec = new Vector<Object>();
			vec.add(a.getMaGV());
			vec.add(a.getTenGV());
			vec.add(a.getSdt());
			vec.add(a.getDiaChi());
			vec.add(a.getHsl());
			vec.add(a.getLuongCb());
			vec.add(a.getNgayVL());
			vec.add(a.getNgayKT());
			vec.add(a.getSoLop());
			vec.add(d);
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
				KetNoiTrangThaiNhanVien kntt = new KetNoiTrangThaiNhanVien(); 
				String d  = kntt.LayTenTrangThai(a.getMaTT());
				Vector<Object> vec = new Vector<Object>();
				vec.add(a.getMaGV());
				vec.add(a.getTenGV());
				vec.add(a.getSdt());
				vec.add(a.getDiaChi());
				vec.add(a.getHsl());
				vec.add(a.getLuongCb());
				vec.add(a.getNgayVL());
				vec.add(a.getNgayKT());
				vec.add(a.getSoLop());
				vec.add(d);
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
