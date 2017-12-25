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
import javax.swing.text.JTextComponent;

import Moudle.BangChamCong;
import Moudle.GiangVien;
import Moudle.ThoiGian;
import Moudle.TrangThaiBangChamCong;
import Service.KetNoiBangChamCong;
import Service.KetNoiGV;
import Service.KetNoiTG;
import Service.KetNoiTTBangChamCong;


public class ManHinhQuanLiChamCongGV extends JFrame {
	JTextField txtMaCC, txtMaGV, txtTenGV, txtNgayCC, txtCa ,txtLyDo,txtTim,txtHeSo;  
	JButton btnThem, btnXoa, btnSua, btnTimKiem, btnTaoMoi, btnIn, btnQuayLai,btnTinhLuong;
	DefaultTableModel dtmNhanSu;
	JTable  tblNhanSu;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<BangChamCong> dsL = null;
	ArrayList<BangChamCong> dsThem = null;
	ArrayList<BangChamCong> dsLTim = null;
	static String MaCC = "";
	JComboBox<ThoiGian>cboCa;
	JComboBox<TrangThaiBangChamCong>cboLyDo;
	JComboBox<GiangVien>cboGV;
	Calendar cal = Calendar.getInstance();

	public ManHinhQuanLiChamCongGV (String title)
	{
		super(title);
		addContronls();
		addEvents();
		showWindow();
		hienThicboGV();
		hienThicboLyDo();
		hienThicboCa();
		//hienThiHeSoLyDo();
		HienThiToanBoLuong();
	}

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
							"Ban có chắc muốn xóa không Nhân Viên:"+cboGV.getSelectedItem().toString(),
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
		cboGV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cboGV.getSelectedItem() == null)
				{
					return;
				}
				else
				{
					txtMaGV.setText(LayMaGV(cboGV.getSelectedItem().toString()));
				}

			}
		});
		cboLyDo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cboLyDo.getSelectedItem() == null)
				{
					return;
				}
				else
				{
					//JOptionPane.showMessageDialog(null,cboLyDo.getSelectedItem().toString());
					float a = LayMaHeSoLiDo(cboLyDo.getSelectedItem().toString());
					//			JOptionPane.showMessageDialog(null,a);
					txtHeSo.setText(Float.toString(a));
				}
				//		
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
				BangChamCong ns = dsL.get(row);
				txtMaCC.setText(ns.getMaCC());				
				hienThicboGVthemMa(ns.getMaGV());
				txtMaGV.setText(LayMaGV(cboGV.getSelectedItem().toString()));
				txtNgayCC.setText(df.format(ns.getNgayCC()));
				hienThiCaHocTheoMa((ns.getMaTG()));
				hienThicboLyDothemMa(ns.getMaTTCC());
				//	hienThiHeSoLyDothemMa(Float.toString(ns.getHeSo()));
				//JOptionPane.showConfirmDialog(null, ns.getMaTTCC());
				//				hienThiHeSoLyDothemMa(Float.toString(ns.getHeSo()));
				float a = LayMaHeSoLiDo(cboLyDo.getSelectedItem().toString());
				//JOptionPane.showMessageDialog(null,a);
				//	float a=LayMaHeSoLiDo(cboLyDo.getSelectedItem().toString());
				txtHeSo.setText(Float.toString(a));

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
				txtMaCC.setText(LayMaNS());	
				txtNgayCC.setText(LayNgayHienHanh());
				cboLyDo.removeAllItems();
				hienThicboGV();
				hienThicboCa();
				hienThicboLyDo();
				
					
			}
		});

		btnTinhLuong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ManHinhTinhLuong xeplich = new ManHinhTinhLuong("Tính Lương");
				ManHinhQuanLiChamCongGV.this.hide();
				xeplich.setFocusable(true);
				xeplich.setVisible(true);
				xeplich.showWindown();
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


	private void addContronls() 
	{

		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnMain=new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));

		JPanel pnTieuDe = new JPanel();
		JLabel lblTieude = new JLabel("QUẢN LÝ CHẤM CÔNG");
		lblTieude.setForeground(Color.BLACK);
		lblTieude.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieude.setIcon(new ImageIcon("Hinh/QLCC.png"));
		pnTieuDe.add(lblTieude);
		pnMain.add(pnTieuDe);

		JPanel pnThongTinChiTiet=new JPanel();
		//GridLayout(4, 2) là 4 dòng 2 cột
		pnThongTinChiTiet.setLayout(new GridLayout(5, 2));
		pnMain.add(pnThongTinChiTiet,BorderLayout.NORTH);

		Border boderThongtinchitiet=BorderFactory
				.createLineBorder(Color.RED);
		TitledBorder bodertitleSoThich=new TitledBorder(boderThongtinchitiet,"Thông tin chi tiết");
		pnThongTinChiTiet.setBorder(bodertitleSoThich);
		bodertitleSoThich.setTitleColor(Color.RED);

		JPanel pnMa=new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		//pnMa.setPreferredSize(new Dimension(10,20));
		JLabel lblMaLuong=new JLabel("Mã Chấm công");
		txtMaCC=new JTextField(20);
		pnMa.add(lblMaLuong);
		pnMa.add(txtMaCC);
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
		//	lblTenGV.setFont(new Font("Arial", Font.PLAIN, 15));
		cboGV=new JComboBox<GiangVien>();
		cboGV.setPreferredSize(new Dimension(270, 20));
		pnTenGV.add(lblTenGV);
		pnTenGV.add(cboGV);
		pnThongTinChiTiet.add(pnTenGV);

		JPanel pnNgayCC = new JPanel();
		pnNgayCC.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNgay = new JLabel("Ngày chấm công: ");
		//lblCa.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNgayCC=new JTextField(20);
		txtNgayCC.setPreferredSize(new Dimension(270, 20));

		pnNgayCC.add(lblNgay);
		pnNgayCC.add(txtNgayCC);
		pnThongTinChiTiet.add(pnNgayCC);

		JPanel pnCa = new JPanel();
		pnCa.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblCa= new JLabel("Ca: ");
		//lblCa.setFont(new Font("Arial", Font.PLAIN, 15));
		cboCa=new JComboBox<ThoiGian>();
		cboCa.setPreferredSize(new Dimension(270, 20));
		pnCa.add(lblCa);
		pnCa.add(cboCa);
		pnThongTinChiTiet.add(pnCa);

		JPanel pnLyDo = new JPanel();
		pnLyDo.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLyDo = new JLabel("Lý Do: ");
		//lblLyDo.setFont(new Font("Arial", Font.PLAIN, 15));
		cboLyDo=new JComboBox<TrangThaiBangChamCong>();
		cboLyDo.setPreferredSize(new Dimension(270, 20));
		pnLyDo.add(lblLyDo);
		pnLyDo.add(cboLyDo);
		pnThongTinChiTiet.add(pnLyDo);

		JPanel pnHeSo=new JPanel();
		pnHeSo.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblHeSo=new JLabel("Hệ số:");
		txtHeSo=new JTextField(20);
		pnHeSo.add(lblHeSo);
		pnHeSo.add(txtHeSo);
		pnThongTinChiTiet.add(pnHeSo);

		JPanel pnButtonChiTiet=new JPanel();
		pnButtonChiTiet.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnTaoMoi=new JButton("Tạo Mới");
		btnThem=new JButton("Thêm");
		btnSua=new JButton("Cập Nhật");
		btnQuayLai=new JButton("Quay Lại");
		btnXoa=new JButton("Xóa");
		btnTinhLuong = new JButton("Lương");
		
		btnThem.setIcon(new ImageIcon("Hinh/save.png"));
		btnXoa.setIcon(new ImageIcon("Hinh/delete.png"));
		btnTaoMoi.setIcon(new ImageIcon("Hinh/plus.png"));
		btnSua.setIcon(new ImageIcon("Hinh/update.png"));
		btnQuayLai.setIcon(new ImageIcon("Hinh/QL.png"));
		btnTinhLuong.setIcon(new ImageIcon("Hinh/TL.png"));
		
		
		JPanel pnTim=new JPanel();
		JLabel lblTim=new JLabel("Nhập dữ liệu:");
		txtTim=new JTextField(20);
		btnTimKiem=new JButton("Tìm");
		btnTimKiem.setIcon(new ImageIcon("Hinh/TK.png"));

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
		lblMaLuong.setPreferredSize(lblTenGV.getPreferredSize());
		lblMaGV.setPreferredSize(lblTenGV.getPreferredSize());
		lblTenGV.setPreferredSize(lblTenGV.getPreferredSize());
		lblCa.setPreferredSize(lblTenGV.getPreferredSize());
		lblLyDo.setPreferredSize(lblTenGV.getPreferredSize());
		lblHeSo.setPreferredSize(lblTenGV.getPreferredSize());
		JPanel pnNorth=new JPanel();
		pnNorth.setLayout(new BorderLayout());


		//pnTop

		dtmNhanSu = new DefaultTableModel();
		dtmNhanSu.addColumn("Mã Chấm Công");
		dtmNhanSu.addColumn("Mã Nhân Viên");
		dtmNhanSu.addColumn("Tên Nhân Viên");
		dtmNhanSu.addColumn("Ngày chấm công");
		dtmNhanSu.addColumn("Ca");
		dtmNhanSu.addColumn("Lý Do");



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
	public void hienThicboGVthemMa(String magv) 
	{
		KetNoiGV kngv=new KetNoiGV();
		Vector<GiangVien>vec1=kngv.hienThiGVLenCbo(magv);
		cboGV.removeAllItems();

		KetNoiGV knllh1=new KetNoiGV();
		Vector<GiangVien>vec=knllh1.docToanBoDanhMuc();
		GiangVien a = new GiangVien();
		a = (GiangVien) vec1.toArray()[0];
		for(GiangVien dm : vec)
		{
			cboGV.addItem(dm);
			if(dm.getTenGV().equals(a.getTenGV()))
				cboGV.setSelectedItem(dm);
		}
	}
	protected void hienThiCaHocTheoMa(int MaTG) {
		if (MaTG+"" == null) 
		{
			cboCa.setSelectedItem(null);
		} 
		else 
		{
			KetNoiTG TG=new KetNoiTG();
			Vector<ThoiGian>vec1=TG.HienThiLenCboThemMa(MaTG); 
			cboCa.removeAllItems();

			KetNoiTG dmService=new KetNoiTG();
			Vector<ThoiGian>vec=dmService.HienThiTGCbo();
			ThoiGian a = new ThoiGian();
			a = (ThoiGian) vec1.toArray()[0];
			for(ThoiGian dm : vec)
			{
				cboCa.addItem(dm);
				if(dm.getTenTG().equals(a.getTenTG())) 
					cboCa.setSelectedItem(dm);
			} 
		}

	}

	public void hienThicboLyDothemMa(String MaTTCC) 
	{
		KetNoiTTBangChamCong kngv=new KetNoiTTBangChamCong();
		Vector<TrangThaiBangChamCong>vec1=kngv.HienThiLenCboLyDo(MaTTCC);
		cboLyDo.removeAllItems();
		//HienThiTGCbo
		KetNoiTTBangChamCong dmService=new KetNoiTTBangChamCong();
		Vector<TrangThaiBangChamCong>vec=dmService.LayToanBoTrangThaiBangChamCong();
		TrangThaiBangChamCong a = new TrangThaiBangChamCong();
		a = (TrangThaiBangChamCong) vec1.toArray()[0];
		for(TrangThaiBangChamCong dm : vec)
		{
			cboLyDo.addItem(dm);
			if(dm.getLyDo().equals(a.getLyDo()))
				cboLyDo.setSelectedItem(dm);
		}
	}
	public void hienThiHeSoLyDothemMa(String MaTTCC) 
	{
		if(cboLyDo.getSelectedItem() == null)
		{
			return;
		}
		else
		{

			float b = LayMaHeSoLiDo(cboLyDo.getSelectedItem().toString());
			txtHeSo.setText(Float.toString(b));
		}
	}

	public void hienThicboGV() {
		KetNoiGV dmService=new KetNoiGV();
		Vector<GiangVien>vec=dmService.docToanBoDanhMuc();
		cboGV.removeAllItems();
		for(GiangVien dm : vec)
		{
			cboGV.addItem(dm);
		}
	}
	public void hienThicboLyDo() {
		KetNoiTTBangChamCong dmService=new KetNoiTTBangChamCong();
		Vector<TrangThaiBangChamCong>vec=dmService.HienThiLyDoLenCbo();
		//cboLyDo.removeAllItems();
		for(TrangThaiBangChamCong dm : vec)
		{
			cboLyDo.addItem(dm);
		}
	}
	
	public void hienThicboCa() {
		KetNoiTG dmService=new KetNoiTG();
		Vector<ThoiGian>vec=dmService.HienThiTGCbo();
		cboCa.removeAllItems();
		for(ThoiGian dm : vec)
		{
			cboCa.addItem(dm);
		}
	}

	public String LayMaGV(String tengv)
	{
		KetNoiGV kngv = new KetNoiGV();
		GiangVien a = new GiangVien();
		a = kngv.LayMaGV(tengv);
		return a.getMaGV();
	}
	public float LayMaHeSoLiDo(String lido)
	{
		KetNoiTTBangChamCong kngv = new KetNoiTTBangChamCong();
		TrangThaiBangChamCong a = new TrangThaiBangChamCong();
		a = kngv.LayMaHeSoLiDo(lido);
		return a.getHeSo();
	}
	private int LayMaTG(String tg) {
		KetNoiTG tgg = new KetNoiTG();
		ThoiGian a = new ThoiGian();
		a = tgg.LayMaTGG(tg);

		return a.getMaTG();
	}

	public String LayMaLyDo(String lydo) {
		KetNoiTTBangChamCong tgg = new KetNoiTTBangChamCong();
		TrangThaiBangChamCong a = new TrangThaiBangChamCong();
		a = tgg.LayMaHeSoLiDo(lydo);
		return a.getMaTTCC();
	}
	protected void CapNhatNS() 
	{
		BangChamCong ns = new BangChamCong();
		ns.setMaCC(txtMaCC.getText());	
		ns.setMaGV(LayMaGV(cboGV.getSelectedItem().toString()));	
		ns.setMaGV(txtMaGV.getText());
		Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(df.parse(txtNgayCC.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ns.setNgayCC(sqlDate);
		ns.setMaTG(LayMaTG(cboCa.getSelectedItem().toString())); 		
		ns.setMaTTCC(LayMaLyDo(cboLyDo.getSelectedItem().toString()));
		//ns.setMaTTCC(txtHeSo.getText());
		///
		KetNoiBangChamCong kn =  new KetNoiBangChamCong();
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
		BangChamCong ns = new BangChamCong();
		ns.setMaCC(txtMaCC.getText());
		KetNoiBangChamCong kn =  new KetNoiBangChamCong();
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

		BangChamCong ns = new BangChamCong();
		ns.setMaCC(txtMaCC.getText());
		ns.setMaGV(txtMaGV.getText());
		ns.setMaGV(LayMaGV(cboGV.getSelectedItem().toString()));	
		Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(df.parse(txtNgayCC.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ns.setNgayCC(sqlDate);
		ns.setMaTG(LayMaTG(cboCa.getSelectedItem().toString())); 
		ns.setMaTTCC(LayMaLyDo(cboLyDo.getSelectedItem().toString()));

		//them
		KetNoiBangChamCong kn =  new KetNoiBangChamCong();
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
	public String LayMaNS()
	{
		KetNoiBangChamCong knns = new KetNoiBangChamCong();
		BangChamCong a = new BangChamCong();
		a = knns.LayMaCC();
		MaCC= a.getMaCC();

		int ViTriCuoi=MaCC.lastIndexOf("C");
		String TenMa=MaCC.substring(ViTriCuoi+1);
		int b = Integer.parseInt(TenMa);
		int c = b+1;
		String MaNS = String.format("CC%02d", c);
		return MaNS;
	}

	public void HienThiToanBoLuong()
	{
		KetNoiBangChamCong knnv = new KetNoiBangChamCong();
		dsL = knnv.LayToanBoLuong();
		dtmNhanSu.setRowCount(0);
		for(BangChamCong a : dsL)
		{
			KetNoiGV knlh = new KetNoiGV(); 
			String b  = knlh.LayTenGV(a.getMaGV());	
			KetNoiTG kntg = new KetNoiTG(); 
			String c  = kntg.LayTenTG(a.getMaTG());	
			KetNoiTTBangChamCong kntt = new KetNoiTTBangChamCong(); 
			String d  = kntt.LayTenLyDo(a.getMaTTCC());
			//float e=LayMaHeSoLiDo(d.toString());
			Vector<Object> vec = new Vector<Object>();
			vec.add(a.getMaCC());
			vec.add(a.getMaGV());
			vec.add(b);
			vec.add(a.getNgayCC());	
			vec.add(c);
			vec.add(d);
			//vec.add(e);
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
			KetNoiBangChamCong knns = new KetNoiBangChamCong();
			dsLTim = knns.TimLuong(a1);
			dtmNhanSu.setRowCount(0);
			for(BangChamCong a : dsLTim)
			{
				KetNoiGV knlh = new KetNoiGV(); 
				String b  = knlh.LayTenGV(a.getMaGV());	
				KetNoiTG kntg = new KetNoiTG(); 
				String c  = kntg.LayTenTG(a.getMaTG());	
				KetNoiTTBangChamCong kntt = new KetNoiTTBangChamCong(); 
				String d  = kntt.LayTenLyDo(a.getMaTTCC());	
				Vector<Object> vec = new Vector<Object>();
				vec.add(a.getMaCC());
				vec.add(a.getMaGV());
				vec.add(b);
				vec.add(a.getNgayCC());			
				vec.add(c);
				vec.add(d);
				dtmNhanSu.addRow(vec);
			}
		}
	}

	public int KiemTaCuPhap()
	{	
		if(txtMaCC.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Sai cú pháp. Vui lòng kiểm tra lại");
			return 1;
		}

		return 0;
	}

	public int KiemTraTonTai()
	{
		KetNoiBangChamCong knns = new KetNoiBangChamCong();
		return knns.KiemTraTonTai(txtMaCC.getText());

	}

	public String LayNgayHienHanh()
	{
		Date t = cal.getTime();
		return df.format(t);
	}

}
