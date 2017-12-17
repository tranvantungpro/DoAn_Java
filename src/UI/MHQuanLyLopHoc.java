package UI;

import javax.swing.JFrame;
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
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Moudle.CTH;
import Moudle.GiangVien;
import Moudle.LoaiLopHoc;
import Moudle.LopHoc;
import Moudle.PhongHoc;
import Moudle.ThoiGian;
import Service.KetNoiCTH;
import Service.KetNoiGV;
import Service.KetNoiLH;
import Service.KetNoiLLH;
import Service.KetNoiPhongHoc;
import Service.KetNoiSQL;
import Service.KetNoiTG;
import sun.net.www.http.ChunkedOutputStream;

public class MHQuanLyLopHoc extends JFrame
{
	//delare
	JTextField txtMa, txtTen, txtLoai,txtGiangVien, txtSiSo,txtMaCH, txtCaHc, txtngaybd, txtPH,txtngaykt, txtMagv, txtTim;  
	JButton btnThem, btnXoa, btnSua, btnTimKiem, btnTaoMoi, btnXepLich, btnQuayLai;
	DefaultTableModel dtmLopHoc;
	JTable  tblLopHoc;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	ArrayList<LopHoc> dsLH = null;
	ArrayList<LopHoc> dsThem = null;
	ArrayList<LopHoc> dsLHTim = null;
	static String MaLopHoc = "";
	JComboBox<LoaiLopHoc>cboLoaiLH;
	JComboBox<CTH>cboCTH;
	JComboBox<GiangVien>cboGV;
	JComboBox<ThoiGian>cboCaHoc;
	JComboBox<PhongHoc>cboPH;
	Calendar cal = Calendar.getInstance();

	public MHQuanLyLopHoc (String tieude)
	{
		super(tieude);
		addContronls();
		addEvents();
		HienThiToanBoLH();
		HienThiLoaiLopHoclencbo();
		hienThicboCTH();
		hienThicboPH();
		hienThiCboTG();
	}


	private void addContronls() 
	{

		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		//create slip
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 425));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop, pnBottom);
		con.add(sp, BorderLayout.CENTER);

		JPanel pnTopOfTop = new JPanel();
		pnTopOfTop.setLayout(new BoxLayout(pnTopOfTop, BoxLayout.Y_AXIS));
 
		//pnTieuDe
		JPanel pnTieuDe = new JPanel();
		JLabel lblTieude = new JLabel("QUẢN LÝ LỚP HỌC");
		lblTieude.setForeground(Color.BLUE);
		lblTieude.setFont(new Font("Arial", Font.BOLD, 25));
		pnTieuDe.add(lblTieude);
		pnTopOfTop.add(pnTieuDe);
		//pnTim
		JPanel pnTim = new JPanel();
		JLabel lblTenLH = new JLabel("Nhập tên lớp học: ");
		lblTenLH.setFont(new Font("Arial", Font.PLAIN, 17));
		txtTim = new JTextField(25);
		btnTimKiem = new JButton("Tìm");
		pnTim.add(lblTenLH);
		pnTim.add(txtTim);
		pnTim.add(btnTimKiem);
		pnTopOfTop.add(pnTim);
		pnTop.add(pnTopOfTop,BorderLayout.NORTH);

		//pnTop
		dtmLopHoc = new DefaultTableModel();
		dtmLopHoc.addColumn("Mã Lớp Học");
		dtmLopHoc.addColumn("Tên Lớp Học");
		dtmLopHoc.addColumn("Loại Loại Học");
		dtmLopHoc.addColumn("Chương Trình Học"); 
		dtmLopHoc.addColumn("Ngày Bắt Đầu");
		dtmLopHoc.addColumn("Ngày Kết Thúc");
		dtmLopHoc.addColumn("Tên Giảng Viên"); 
		dtmLopHoc.addColumn("Ca Học");
		dtmLopHoc.addColumn("Phòng Học");
		dtmLopHoc.addColumn("Sỉ Số");
		dtmLopHoc.addColumn("Sỉ Số Hiện Tại");

		tblLopHoc = new JTable(dtmLopHoc);
		JScrollPane sptable = new JScrollPane(tblLopHoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(sptable);

		//pnBottom
		JPanel pnTopofBottom = new JPanel();
		pnTopofBottom.setLayout(new GridLayout(5, 2));

		//pnMa
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblMa = new JLabel("Mã lớp học:");
		lblMa.setFont(new Font("Arial", Font.PLAIN, 17));
		txtMa = new JTextField(25);
		txtMa.setEditable(false);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		pnTopofBottom.add(pnMa);

		//pnNbd
		JPanel pnnbd = new JPanel();
		pnnbd.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblnbd = new JLabel("Ngày bắt đầu: ");
		lblnbd.setFont(new Font("Arial", Font.PLAIN, 17));
		txtngaybd = new JTextField(25);
		pnnbd.add(lblnbd);
		pnnbd.add(txtngaybd); 
		pnTopofBottom.add(pnnbd);

		//pnTen
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTen = new JLabel("Tên lớp học: ");
		lblTen.setFont(new Font("Arial", Font.PLAIN, 17));
		txtTen = new JTextField(25);
		pnTen.add(lblTen);
		pnTen.add(txtTen); 
		pnTopofBottom.add(pnTen);

		//pnnkt
		JPanel pnnkt = new JPanel();
		pnnkt.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblnkt = new JLabel("Ngày kết thúc: ");
		lblnkt.setFont(new Font("Arial", Font.PLAIN, 17));
		txtngaykt = new JTextField(25);
		pnnkt.add(lblnkt);
		pnnkt.add(txtngaykt);
		pnTopofBottom.add(pnnkt);

		//pnMa loai
		JPanel pnLoai = new JPanel();
		pnLoai.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblLoai = new JLabel("Loại lớp học: ");
		lblLoai.setFont(new Font("Arial", Font.PLAIN, 17));
		cboLoaiLH=new JComboBox<LoaiLopHoc>();
		cboLoaiLH.setPreferredSize(new Dimension(280, 20));
		pnLoai.add(lblLoai);
		pnLoai.add(cboLoaiLH);
		pnTopofBottom.add(pnLoai);		

		//pnGV
		JPanel pnGV = new JPanel();
		pnGV.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblgv = new JLabel("Giảng viên: ");
		lblgv.setFont(new Font("Arial", Font.PLAIN, 17));
		txtGiangVien = new JTextField(25);
		txtGiangVien.setEditable(false);
		pnGV.add(lblgv);
		pnGV.add(txtGiangVien);
		pnTopofBottom.add(pnGV);


		//pnMa mcth
		JPanel pnCTH = new JPanel();
		pnCTH.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblcth = new JLabel("Chương trình học: ");
		lblcth.setFont(new Font("Arial", Font.PLAIN, 17));
		cboCTH=new JComboBox<CTH>();
		cboCTH.setPreferredSize(new Dimension(280, 20)); 
		pnCTH.add(lblcth);
		pnCTH.add(cboCTH); 	
		pnTopofBottom.add(pnCTH);

		//pnMaTG
		JPanel pnMaTG = new JPanel();
		pnMaTG.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblcahc = new JLabel("Ca học: ");
		lblcahc.setFont(new Font("Arial", Font.PLAIN, 17));
		cboCaHoc=new JComboBox<ThoiGian>();
		cboCaHoc.setPreferredSize(new Dimension(280, 20));
		pnMaTG.add(lblcahc);
		pnMaTG.add(cboCaHoc);
		pnTopofBottom.add(pnMaTG);

		//pnMaPH
		JPanel pnMaPH = new JPanel();
		pnMaPH.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblPH = new JLabel("Phòng học: ");
		lblPH.setFont(new Font("Arial", Font.PLAIN, 17));
		cboPH=new JComboBox<PhongHoc>();
		cboPH.setPreferredSize(new Dimension(280, 20));
		pnMaPH.add(lblPH);
		pnMaPH.add(cboPH);
		pnTopofBottom.add(pnMaPH);
		
		//pnMaPH
		JPanel pnSiSo = new JPanel();
		pnSiSo.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblsiso = new JLabel("Sỉ số ");
		lblsiso.setFont(new Font("Arial", Font.PLAIN, 17)); 
		txtSiSo = new JTextField(25);
		pnSiSo.add(lblsiso);
		pnSiSo.add(txtSiSo);
		pnTopofBottom.add(pnSiSo);

		pnBottom.add(pnTopofBottom);

		lblMa.setPreferredSize(lblcth.getPreferredSize());
		lblPH.setPreferredSize(lblcth.getPreferredSize());
		lblTen.setPreferredSize(lblcth.getPreferredSize());
		lblLoai.setPreferredSize(lblcth.getPreferredSize());
		lblnbd.setPreferredSize(lblnkt.getPreferredSize());
		lblgv.setPreferredSize(lblnkt.getPreferredSize());
		lblcahc.setPreferredSize(lblnkt.getPreferredSize());
		lblsiso.setPreferredSize(lblnkt.getPreferredSize());

		//pnButton
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnSua = new JButton("Cập nhật");
		btnTaoMoi =new JButton("Tạo Mới");
		btnXepLich = new JButton("Xếp lịch giảng viên");
		btnQuayLai = new JButton("Quay lại");
		
		btnThem.setFont(new Font("Arial", Font.BOLD,17));
		btnXoa.setFont(new Font("Arial", Font.BOLD,17));
		btnSua.setFont(new Font("Arial", Font.BOLD,17));
		btnTaoMoi.setFont(new Font("Arial", Font.BOLD,17));
		btnThem.setFont(new Font("Arial", Font.BOLD,17));
		btnQuayLai.setFont(new Font("Arial", Font.BOLD,17));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD,17));
		btnXepLich.setFont(new Font("Arial", Font.BOLD,17));
		
		btnThem.setIcon(new ImageIcon("Hinh/save.png"));
		btnXoa.setIcon(new ImageIcon("Hinh/delete.png"));
		btnTaoMoi.setIcon(new ImageIcon("Hinh/plus.png"));
		btnSua.setIcon(new ImageIcon("Hinh/update.png"));
		btnQuayLai.setIcon(new ImageIcon("Hinh/back.png"));
		btnTimKiem.setIcon(new ImageIcon("Hinh/find.png"));
		pnButton.add(btnTaoMoi);
		pnButton.add(btnThem);
		pnButton.add(btnXoa);
		pnButton.add(btnSua);
		pnButton.add(btnXepLich);
		pnButton.add(btnQuayLai);
		pnBottom.add(pnButton);

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

					XoaLopHoc();

				}
				else
				{
					btnTaoMoi.doClick();
				}

			}
		});

		tblLopHoc.addMouseListener(new MouseListener() {

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
				int row=tblLopHoc.getSelectedRow();
				if(row==-1)return; 
				LopHoc LH = dsLH.get(row);
				txtMa.setText(LH.getMaLH());
				txtTen.setText(LH.getTenLH());
				hienThicboLoaiLHthemMa(LH.getLoaiLH());
				hienThicboCTHthemMa(LH.getMaCTH());
				txtngaybd.setText(df.format(LH.getNgayBD()));
				txtngaykt.setText(df.format(LH.getNgayKT())); 
				hienThiCaHocTheoMa(LH.getMaTG());
				hienThiPHThemMa(LH.getMaPH());
				txtSiSo.setText(LH.getSiso()+""); 
				KetNoiGV gv = new KetNoiGV(); 
				String aa = gv.LayTenGV(LH.getMaGV());

				txtGiangVien.setText(aa);
			}
		});

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(KiemTaCuPhap()==0)
				{

					CapNhatLopHoc();

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
				txtMa.setText(LayMaLH());
				txtTen.setText(""); 
				txtngaybd.setText(LayNgayHienHanh());
				txtngaykt.setText(""); 
				HienThiToanBoLH();
				HienThiLoaiLopHoclencbo();
				hienThicboCTH();  
				cboCTH.setSelectedItem(null);
				cboLoaiLH.setSelectedItem(null);
				cboCaHoc.setSelectedItem(null);
				cboPH.setSelectedItem(null);
			}
		});

		btnXepLich.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MHXepLichGV xeplich = new MHXepLichGV("Xếp Lịch Giảng Viên");
				//					xeplich.setFocusable(true);
				//					xeplich.setVisible(true);
				xeplich.ShowWindow();
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
		
		 cboCTH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cboCTH.getSelectedItem()==null)
				{
					txtngaykt.setText("");
				}
				else
					DatNgayKT(cboCTH.getSelectedItem().toString());
			}
		});
			
			 
	}

	protected void DatNgayKT(String tencth) 
	{ 
		if(cboCTH.getSelectedItem()==null)
		{
			return;
		}
		else
		{
		KetNoiCTH cth = new KetNoiCTH();
		CTH d = new CTH();
		d = cth.LayMaCTH(tencth);
		 int  aa= d.getSoBuoi();;
		Date ngth = NgayKT(aa);
		txtngaykt.setText(df.format(ngth));
		}
		
	}


	protected void hienThiPHThemMa(String maPH) {
		if (maPH == null) 
		{
			cboPH.setSelectedItem(null);
		} 
		else 
		{
			KetNoiPhongHoc TG=new KetNoiPhongHoc();
			Vector<PhongHoc>vec1=TG.HienThiPHLenCboThemMa(maPH); 
			cboPH.removeAllItems();

			KetNoiPhongHoc dmService=new KetNoiPhongHoc();
			Vector<PhongHoc>vec=dmService.DocToanBoPhongHoc();
			PhongHoc a = new PhongHoc();
			a = (PhongHoc) vec1.toArray()[0];
			for(PhongHoc dm : vec)
			{
				cboPH.addItem(dm);
				if(dm.getTenPH().equals(a.getTenPH())) 
					cboPH.setSelectedItem(dm);
			} 
		}
				
	}

	protected void hienThiCaHocTheoMa(int MaTG) {
		if (MaTG+"" == null) 
		{
			cboCaHoc.setSelectedItem(null);
		} 
		else 
		{
			KetNoiTG TG=new KetNoiTG();
			Vector<ThoiGian>vec1=TG.HienThiLenCboThemMa(MaTG); 
			cboCaHoc.removeAllItems();

			KetNoiTG dmService=new KetNoiTG();
			Vector<ThoiGian>vec=dmService.HienThiTGCbo();
			ThoiGian a = new ThoiGian();
			a = (ThoiGian) vec1.toArray()[0];
			for(ThoiGian dm : vec)
			{
				cboCaHoc.addItem(dm);
				if(dm.getTenTG().equals(a.getTenTG())) 
					cboCaHoc.setSelectedItem(dm);
			} 
		}
		
	}

	private void hienThicboPH() {
		KetNoiPhongHoc knph=new KetNoiPhongHoc();
		Vector<PhongHoc>vec=knph.HienThiPHCbo();

		cboPH.removeAllItems();
		for(PhongHoc dm : vec)
		{
			cboPH.addItem(dm);
		}	
	}

	private void hienThiCboTG() {
		KetNoiTG kntg=new KetNoiTG();
		Vector<ThoiGian>vec=kntg.HienThiTGCbo();

		cboCaHoc.removeAllItems();
		for(ThoiGian dm : vec)
		{
			cboCaHoc.addItem(dm);
		}

	}

	public void QuayLai() 
	{
		this.dispose(); 
	}

	protected void hienThicboCTHthemMa(String maCTH) {
		if (maCTH == null) 
		{
			cboCTH.setSelectedItem(null);
		} 
		else 
		{
			KetNoiCTH kncth=new KetNoiCTH();
			Vector<CTH>vec1=kncth.hienThicbocththemMa(maCTH); 
			cboCTH.removeAllItems();

			KetNoiCTH dmService=new KetNoiCTH();
			Vector<CTH>vec=dmService.docToanBoDanhMuc();
			CTH a = new CTH();
			a = (CTH) vec1.toArray()[0];
			for(CTH dm : vec)
			{
				cboCTH.addItem(dm);
				if(dm.getTenCTH().equals(a.getTenCTH())) 
					cboCTH.setSelectedItem(dm);
			} 
		}
	}

	public  void hienThicboLoaiLHthemMa(String ma) {
		if(ma== null)
			cboLoaiLH.setSelectedItem(null);
		else
		{
			KetNoiLLH knllh=new KetNoiLLH();
			Vector<LoaiLopHoc>vec1=knllh.hienThicboLLHthemMa(ma);
			cboLoaiLH.removeAllItems();

			KetNoiLLH knllh1=new KetNoiLLH();
			Vector<LoaiLopHoc>vec=knllh1.docToanBoDanhMuc();
			LoaiLopHoc a = new LoaiLopHoc();
			a = (LoaiLopHoc) vec1.toArray()[0];
			for(LoaiLopHoc dm : vec)
			{
				cboLoaiLH.addItem(dm);
				if(dm.getTenLoaiLH().equals(a.getTenLoaiLH())) 
					cboLoaiLH.setSelectedItem(dm);
			}
		}
	}

	public void showWindow()
	{
		this.setSize(950, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void HienThiLoaiLopHoclencbo() {
		KetNoiLLH dmService=new KetNoiLLH();
		Vector<LoaiLopHoc>vec=dmService.docToanBoDanhMuc();
		//lisLoaiLH.setListData(vec);
		cboLoaiLH.removeAllItems();
		for(LoaiLopHoc dm : vec)
		{
			cboLoaiLH.addItem(dm);
		}
	}

	private void hienThicboCTH() {
		KetNoiCTH dmService=new KetNoiCTH();
		Vector<CTH>vec=dmService.docToanBoDanhMuc();

		cboCTH.removeAllItems();
		for(CTH dm : vec)
		{
			cboCTH.addItem(dm);
		}
	}

	 

	public void hienThicboGVthemMa(String magv) 
	{
		if(magv == null)
		{
			cboGV.setSelectedItem(null);
		}
		else
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
	}

	protected void CapNhatLopHoc() 
	{
		LopHoc LH = new LopHoc();
		LH.setMaLH(txtMa.getText());
		LH.setTenLH(txtTen.getText());
		LH.setLoaiLH(cboLoaiLH.getSelectedItem().toString());

		LH.setMaCTH(LayMaCTH(cboCTH.getSelectedItem().toString()));
		//LH.setSoBuoi(Integer.parseInt(txtSoBuoi.getText()));

		//ngaybd
		Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(df.parse(txtngaybd.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LH.setNgayBD(sqlDate);

		//ngaykt
		Date sqlDate1 = null;
		try {
			sqlDate1 = new java.sql.Date(df.parse(txtngaykt.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LH.setNgayKT(sqlDate1);
		LH.setMaPH(LayMaPH(cboPH.getSelectedItem().toString()));
		LH.setMaTG(LayMaTG(cboCaHoc.getSelectedItem().toString()));
		LH.setSiso(Integer.parseInt(txtSiSo.getText()));
		KetNoiLH kn =  new KetNoiLH();
		if(kn.CapNhatLopHoc(LH) > 0)
		{
			JOptionPane.showMessageDialog(null, "Cập nhật lớp học thành công");
			HienThiToanBoLH();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Cập nhật lớp học thất bại");
		}

	}

	protected void XoaLopHoc() 
	{
		LopHoc LH = new LopHoc();
		LH.setMaLH(txtMa.getText());
		KetNoiLH kn =  new KetNoiLH();
		if(kn.XoaLopHoc(LH) > 0)
		{
			JOptionPane.showMessageDialog(null, "Xóa lớp học thành công");
			HienThiToanBoLH();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa lớp học thất bại");
		}
	}

	protected void LuuMoiLH()  
	{

		LopHoc LH = new LopHoc();
		LH.setMaLH(txtMa.getText());
		LH.setTenLH(txtTen.getText());
		LH.setLoaiLH(cboLoaiLH.getSelectedItem().toString()); 
		LH.setMaCTH(LayMaCTH(cboCTH.getSelectedItem().toString()));
		//LH.setSoBuoi(Integer.parseInt(txtSoBuoi.getText()));


		//ngaybd
		Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(df.parse(txtngaybd.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LH.setNgayBD(sqlDate);

		//ngaykt
		Date sqlDate1 = null;
		try {
			sqlDate1 = new java.sql.Date(df.parse(txtngaykt.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LH.setNgayKT(sqlDate1);
		///

		LH.setMaGV(null);
		LH.setMaPH(LayMaPH(cboPH.getSelectedItem().toString()));
		LH.setMaTG(LayMaTG(cboCaHoc.getSelectedItem().toString())); 
		LH.setSiso(Integer.parseInt(txtSiSo.getText()));
		LH.setSiSoHT(0);
		//them
		KetNoiLH kn =  new KetNoiLH();
		if(kn.ThemMoiLopHoc(LH) > 0)
		{
			JOptionPane.showMessageDialog(null, "Lưu lớp học thành công");
			HienThiToanBoLH();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Lưu lớp học thất bại");
		}

	}

	private int LayMaTG(String tg) {
		KetNoiTG tgg = new KetNoiTG();
		ThoiGian a = new ThoiGian();
		a = tgg.LayMaTGG(tg);

		return a.getMaTG();
	}


	public String LayMaLH()
	{
		KetNoiLH knLH = new KetNoiLH();
		LopHoc a = new LopHoc();
		a = knLH.LayMaLH();
		MaLopHoc=a.getMaLH();

		int ViTriCuoi=MaLopHoc.lastIndexOf("H");
		String TenMa=MaLopHoc.substring(ViTriCuoi+1);
		int b = Integer.parseInt(TenMa);
		int c = b+1;
		String MaLH = String.format("LH%03d", c);
		return MaLH;
	}

	public void HienThiToanBoLH()
	{
		KetNoiLH knLH = new KetNoiLH();
		dsLH = knLH.LayToanBoLopHoc();
		dtmLopHoc.setRowCount(0);
		for(LopHoc a : dsLH)
		{
			//Lấy tên chương trình học
			KetNoiCTH knlh = new KetNoiCTH(); 
			String b  = knlh.LayTenCTH(a.getMaCTH());
			
			//Lấy tên GV
			KetNoiGV kngv = new KetNoiGV(); 
			String c  = kngv.LayTenGV(a.getMaGV());
			
			//Lấy tên ca hoc
			KetNoiTG tg = new KetNoiTG(); 
			String d  = tg.LayTenTG(a.getMaTG());
			
			//Lấy tên phong hc
			KetNoiPhongHoc ph = new KetNoiPhongHoc(); 
			String e  = ph.LayTenPH(a.getMaPH());

			Vector<Object> vec = new Vector<Object>();
			vec.add(a.getMaLH());
			vec.add(a.getTenLH());
			vec.add(a.getLoaiLH());
			vec.add(b);
			vec.add(a.getNgayBD());
			vec.add(a.getNgayKT());
			vec.add(c);
			vec.add(d);
			vec.add(e);
			vec.add(a.getSiso());
			vec.add(a.getSiSoHT());
			dtmLopHoc.addRow(vec);
		}
	} 

	public void HienThiTim()
	{
		if(txtTim.getText().equals(""))
		{
			HienThiToanBoLH();
			JOptionPane.showMessageDialog(null, "Vui lòng nhập giáo trình cần tìm!!!");
		}
		else
		{
			String a1 = txtTim.getText();
			KetNoiLH knLH = new KetNoiLH();
			dsLHTim = knLH.TimLopHoc(a1);
			dtmLopHoc.setRowCount(0);
			for(LopHoc a : dsLHTim)
			{
				Vector<Object> vec = new Vector<Object>();
				//Lấy tên chương trình học
				KetNoiCTH knlh = new KetNoiCTH(); 
				String b  = knlh.LayTenCTH(a.getMaCTH());
				
				//Lấy tên GV
				KetNoiGV kngv = new KetNoiGV(); 
				String c  = kngv.LayTenGV(a.getMaGV());
				
				//Lấy tên ca hoc
				KetNoiTG tg = new KetNoiTG(); 
				String d  = tg.LayTenTG(a.getMaTG());
				
				//Lấy tên phong hc
				KetNoiPhongHoc ph = new KetNoiPhongHoc(); 
				String e  = ph.LayTenPH(a.getMaPH());

				
				vec.add(a.getMaLH());
				vec.add(a.getTenLH());
				vec.add(a.getLoaiLH());
				vec.add(b);
				vec.add(a.getNgayBD());
				vec.add(a.getNgayKT());
				vec.add(c);
				vec.add(d);
				vec.add(e);
				vec.add(a.getSiso());
				dtmLopHoc.addRow(vec);
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
		if(txtngaybd.getText().equals(""))
		{
			return 1;
		}

		if(txtngaykt.getText().equals(""))
		{
			return 1;
		}



		 
		return 0;
	}

	public int KiemTraTonTai()
	{
		KetNoiLH knLH = new KetNoiLH();
		return knLH.KiemTraTonTai(txtTen.getText());

	}

	public String LayNgayHienHanh()
	{
		Date t = cal.getTime();
		return df.format(t);
	}

	public String LayMaCTH(String tenllh)
	{
		KetNoiCTH knLLH = new KetNoiCTH();
		CTH a = new CTH();
		a = knLLH.LayMaCTH(tenllh);

		return a.getMaCTH();
	}

	public String LayMaGV(String tengv)
	{
		KetNoiGV knGV = new KetNoiGV();
		GiangVien a = new GiangVien();
		a = knGV.LayMaGV(tengv);

		return a.getMaGV();
	}
	
	public String LayMaPH(String tenPhong)
	{
		KetNoiPhongHoc ph = new KetNoiPhongHoc();
		PhongHoc a = new PhongHoc();
		a = ph.LayMaPHH(tenPhong);

		return a.getMaPH();
	}

	public Date NgayKT ( int b)
	{
		Date t = cal.getTime();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(t);
		cal.add(Calendar.DATE, b);
		return cal.getTime();
	}
	 
}
