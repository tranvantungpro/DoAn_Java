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

import Moudle.BangChamCong;
import Moudle.GiangVien;
import Service.KetNoiBangChamCong;
import Service.KetNoiGV;


public class ManHinhQuanLiChamCongGV extends JFrame {
	public ManHinhQuanLiChamCongGV (String title)
	{
		super(title);
		addContronls();
		addEvents();
		showWindow();
		HienThiToanBoLuong();
		hienThiDanhMucLenList();
		hienThicboGV();
		hienThicboLyDo();
		hienThicboCa();
	}
	JTextField txtMaCC, txtMaGV, txtTenGV, txtNgayCC, txtCa ,txtLyDo,txtTim;  
	JButton btnThem, btnXoa, btnSua, btnTimKiem, btnTaoMoi, btnIn, btnQuayLai,btnTinhLuong;
	DefaultTableModel dtmNhanSu;
	JTable  tblNhanSu;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<BangChamCong> dsL = null;
	ArrayList<BangChamCong> dsThem = null;
	ArrayList<BangChamCong> dsLTim = null;
	static String MaCC = "";
	JComboBox<BangChamCong>cboCa;
	JComboBox<BangChamCong>cboLyDo;
	JComboBox<GiangVien>cboGV;
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
				BangChamCong ns = dsL.get(row);
				txtMaCC.setText(ns.getMaCC());		
				hienThicboGVthemMa(ns.getMaGV());
				hienThicboCathemMa(Float.toString(ns.getCa()));
				hienThicboLyDothemMa(ns.getLyDo());
				//hiện thị tên gv
				txtNgayCC.setText(df.format(ns.getNgayCC()));
			
				
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
				txtTenGV.setText(""); 
				txtMaGV.setText("");
				
				HienThiToanBoLuong();
				hienThiDanhMucLenList();
				hienThicboGV();
				cboGV.setSelectedItem(null);
				hienThicboCa();
				cboCa.setSelectedItem(null);
				hienThicboLyDo();
				cboLyDo.setSelectedItem(null);
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

	protected void hienThicboGiangVienthemMa(String magv) {
		KetNoiGV knpb=new KetNoiGV();
		Vector<GiangVien>vec1=knpb.hienThicboGVthemMa(magv); 
		cboGV.removeAllItems();
		
		KetNoiGV dmService=new KetNoiGV();
		Vector<GiangVien>vec=dmService.docToanBoDanhMuc();
		GiangVien a = new GiangVien();
		a = (GiangVien) vec1.toArray()[0];
		for(GiangVien dm : vec)
		{
			cboGV.addItem(dm);
			if(dm.getTenGV().equals(a.getTenGV()))
				cboGV.setSelectedItem(dm);
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
		
		JPanel pnCa = new JPanel();
		pnCa.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblCa = new JLabel("Ca: ");
		//lblCa.setFont(new Font("Arial", Font.PLAIN, 15));
		cboCa=new JComboBox<BangChamCong>();
		cboCa.setPreferredSize(new Dimension(270, 20));
		pnCa.add(lblCa);
		pnCa.add(cboCa);
		pnThongTinChiTiet.add(pnCa);

		JPanel pnLyDo = new JPanel();
		pnLyDo.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLyDo = new JLabel("Lý Do: ");
		//lblLyDo.setFont(new Font("Arial", Font.PLAIN, 15));
		cboLyDo=new JComboBox<BangChamCong>();
		cboLyDo.setPreferredSize(new Dimension(270, 20));
		pnLyDo.add(lblLyDo);
		pnLyDo.add(cboLyDo);
		pnThongTinChiTiet.add(pnLyDo);
		
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
		lblMaLuong.setPreferredSize(lblTenGV.getPreferredSize());
		lblMaGV.setPreferredSize(lblTenGV.getPreferredSize());
		lblTenGV.setPreferredSize(lblTenGV.getPreferredSize());
		lblCa.setPreferredSize(lblTenGV.getPreferredSize());
		lblLyDo.setPreferredSize(lblTenGV.getPreferredSize());
		
		JPanel pnNorth=new JPanel();
		pnNorth.setLayout(new BorderLayout());
		
		
		//pnTop
		
		dtmNhanSu = new DefaultTableModel();
		dtmNhanSu.addColumn("Mã Chấm Công");
		dtmNhanSu.addColumn("Mã Nhân Viên");
		dtmNhanSu.addColumn("Tên Nhân Viên");
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

	private void hienThiDanhMucLenList() {
		KetNoiGV dmService=new KetNoiGV();
		Vector<GiangVien>vec=dmService.docToanBoDanhMuc();
		//lisLoaiLH.setListData(vec);
		cboGV.removeAllItems();
		for(GiangVien dm : vec)
		{
			cboGV.addItem(dm);
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

	public void hienThicboGVthemMa(String magv) 
	{
		KetNoiGV kngv=new KetNoiGV();
		Vector<GiangVien>vec1=kngv.hienThicboGVthemMa(magv);
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
	public void hienThicboLyDo() {
		KetNoiBangChamCong dmService=new KetNoiBangChamCong();
		Vector<BangChamCong>vec=dmService.docToanBoDanhMuc();
		cboLyDo.removeAllItems();
		for(BangChamCong dm : vec)
		{
			cboLyDo.addItem(dm);
		}
	}
	public void hienThicboCa() {
		KetNoiBangChamCong dmService=new KetNoiBangChamCong();
		Vector<BangChamCong>vec=dmService.docToanBoDanhMuc();
		cboCa.removeAllItems();
		for(BangChamCong dm : vec)
		{
			cboCa.addItem(dm);
		}
	}
	public void hienThicboCathemMa(String macc) 
	{
		KetNoiBangChamCong kngv=new KetNoiBangChamCong();
		Vector<BangChamCong>vec1=kngv.hienThicboCathemMa(macc);
		cboCa.removeAllItems();
		
		KetNoiBangChamCong knllh1=new KetNoiBangChamCong();
		Vector<BangChamCong>vec=knllh1.docToanBoDanhMuc();
		BangChamCong a = new BangChamCong();
		a = (BangChamCong) vec1.toArray()[0];
		for(BangChamCong dm : vec)
		{
			cboCa.addItem(dm);
			//if(dm.getCa().equals(a.getCa()))
				cboCa.setSelectedItem(dm);
		}
	}
	public void hienThicboLyDothemMa(String macc) 
	{
		KetNoiBangChamCong kngv=new KetNoiBangChamCong();
		Vector<BangChamCong>vec1=kngv.hienThicboLyDothemMa(macc);
		cboLyDo.removeAllItems();
		
		KetNoiBangChamCong knllh1=new KetNoiBangChamCong();
		Vector<BangChamCong>vec=knllh1.docToanBoDanhMuc();
		BangChamCong a = new BangChamCong();
		a = (BangChamCong) vec1.toArray()[0];
		for(BangChamCong dm : vec)
		{
			cboLyDo.addItem(dm);
			if(dm.getLyDo().equals(a.getLyDo()))
				cboLyDo.setSelectedItem(dm);
		}
	}
	protected void CapNhatNS() 
	{
		BangChamCong ns = new BangChamCong();
		ns.setMaCC(txtMaCC.getText());
		ns.setMaGV(txtMaGV.getText());
		ns.setMaGV(LayMaGV(cboGV.getSelectedItem().toString()));	
		
		
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
//		ns.setHsoL(txtHSluong.getText());
//		ns.setLuongCb(txtLuongcb.getText());
		
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
	public String LayMaGV(String tengv)
	{
		KetNoiGV kngv = new KetNoiGV();
		GiangVien a = new GiangVien();
		a = kngv.LayMaGV(tengv);
		 
		return a.getMaGV();
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
		String MaNS = String.format("C%03d", c);
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
			Vector<Object> vec = new Vector<Object>();
			vec.add(a.getMaCC());
			vec.add(a.getMaGV());
			vec.add(b);
			
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
				Vector<Object> vec = new Vector<Object>();
				vec.add(a.getMaCC());
				vec.add(a.getMaGV());
				
				
				
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
		KetNoiBangChamCong knns = new KetNoiBangChamCong();
		return knns.KiemTraTonTai(txtTenGV.getText());
		 
	}

	public String LayNgayHienHanh()
	{
		Date t = cal.getTime();
		return df.format(t);
	}

}
