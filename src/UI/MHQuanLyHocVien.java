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
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Moudle.CTH;
import Moudle.GiangVien;
import Moudle.GiaoTrinh;
import Moudle.HocVien;
import Moudle.LoaiLopHoc;
import Moudle.LopHoc;
import Moudle.TrangThai;
import Moudle.HocVien;
import Service.KetNoiCTH;
import Service.KetNoiGT;
import Service.KetNoiHV;
import Service.KetNoiLH;
import Service.KetNoiTT;

public class MHQuanLyHocVien extends JFrame 
{
	 JTextField txtMaHV, txtTenHV, txtNgaySinh, txtDiaChi, txtSDT, txtEmail, txtTim;
	 JButton btnThem, btnXoa, btnSua, btnTim, btnTaoMoi, btnChuyenLop, btnBaoLuu, btnQuayLai;
	 JComboBox<HocVien> cboHocVien;
	 DefaultTableModel dmHocVien;
	 JTable tblHocVien;
	 ArrayList<HocVien> dsHV ;
	 static String Mahocvien = "";
	 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	 JComboBox<LopHoc> cboLopHoc;
	 JComboBox<TrangThai> cboTrangThai;


	public MHQuanLyHocVien(String tieude) {
		super("Quản Lý Học Viên");
		addContronls();
		addEvents();
		HienThiToanBoHV();
		hienThicboMLHa();
		hienThicboTT();
		}

	private void addContronls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		//create slip
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 460));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop, pnBottom); 
		con.add(sp, BorderLayout.CENTER);

		JPanel pnTopOfTop = new JPanel();
		pnTopOfTop.setLayout(new BoxLayout(pnTopOfTop, BoxLayout.Y_AXIS));
		
		//pnTieuDe
		JPanel pnTieuDe = new JPanel();
		JLabel lblTieude = new JLabel("QUẢN LÝ HỌC VIÊN");
		lblTieude.setForeground(Color.BLUE);
		lblTieude.setFont(new Font("Arial", Font.BOLD, 25));
		pnTieuDe.add(lblTieude);
		pnTopOfTop.add(pnTieuDe);
		//pnTim
		JPanel pnTim = new JPanel();
		JLabel lblTenhv = new JLabel("Nhập tên học viên: ");
		lblTenhv.setFont(new Font("Arial", Font.PLAIN, 17));
		txtTim = new JTextField(20); 
		btnTim = new JButton("Tìm"); 
		pnTim.add(lblTenhv);
		pnTim.add(txtTim);
		pnTim.add(btnTim);
		pnTopOfTop.add(pnTim);
		pnTop.add(pnTopOfTop,BorderLayout.NORTH);

		//pnTop
		dmHocVien = new DefaultTableModel();
		dmHocVien.addColumn("Mã Học Viên");
		dmHocVien.addColumn("Tên Học Viên");
		dmHocVien.addColumn("Ngày Sinh");
		dmHocVien.addColumn("Địa Chỉ");
		dmHocVien.addColumn("Số Điện Thoại");
		dmHocVien.addColumn("Email");
		dmHocVien.addColumn("Lớp Học"); 
		dmHocVien.addColumn("Trạng Thái"); 
		
		tblHocVien = new JTable(dmHocVien);
		JScrollPane sptable = new JScrollPane(tblHocVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(sptable);
		
		//pnBottom
		JPanel pnTopofBottom = new JPanel();
		pnTopofBottom.setLayout(new GridLayout(4, 2));
		//pnMa Ma
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblMahv = new JLabel("Mã học viên:");
		lblMahv.setFont(new Font("Arial", Font.PLAIN, 17));
		txtMaHV = new JTextField(30);
		txtMaHV.setEditable(false);
		pnMa.add(lblMahv);
		pnMa.add(txtMaHV);
		pnTopofBottom.add(pnMa);
		
		//pnsdt
		JPanel pnSdt = new JPanel();
		pnSdt.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblSdt = new JLabel("SDT:  ");
		lblSdt.setFont(new Font("Arial", Font.PLAIN, 17));
		txtSDT = new JTextField(30);
		pnSdt.add(lblSdt);
		pnSdt.add(txtSDT);
		pnTopofBottom.add(pnSdt);
		
		//pnMa Ten
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTenhv1 = new JLabel("Tên học viên:");
		lblTenhv1.setFont(new Font("Arial", Font.PLAIN, 17));
		txtTenHV = new JTextField(30);
		pnTen.add(lblTenhv1);
		pnTen.add(txtTenHV);
		pnTopofBottom.add(pnTen);
		
		//pnEmail
		JPanel pnEmail = new JPanel();
		pnEmail.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 17));
		txtEmail = new JTextField(30);
		pnEmail.add(lblEmail);
		pnEmail.add(txtEmail);
		pnTopofBottom.add(pnEmail);
		
		
		//pnMa loai
		JPanel pnNS = new JPanel();
		pnNS.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblns = new JLabel("Ngày Sinh:");
		lblns.setFont(new Font("Arial", Font.PLAIN, 17));
		txtNgaySinh = new JTextField(30);
		pnNS.add(lblns);
		pnNS.add(txtNgaySinh);
		pnTopofBottom.add(pnNS);	  
		
		//pnDiachi
		JPanel pnDiachi = new JPanel();
		JLabel lbldiachi = new JLabel("Địa chỉ:");
		lbldiachi.setFont(new Font("Arial", Font.PLAIN, 17)); 
		txtDiaChi = new JTextField(30);
		pnDiachi.add(lbldiachi);
		pnDiachi.add(txtDiaChi);
		pnTopofBottom.add(pnDiachi);
		
		
		//pnloai
		JPanel pnloai = new JPanel();
		pnloai.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblLoai = new JLabel("Lớp học: ");
		lblLoai.setFont(new Font("Arial", Font.PLAIN, 17));
		cboLopHoc=new JComboBox<LopHoc>();
		cboLopHoc.setPreferredSize(new Dimension(332, 20));
		pnloai.add(lblLoai);
		pnloai.add(cboLopHoc);
		pnTopofBottom.add(pnloai);	 
		
		//pnTT
		JPanel pnTT = new JPanel();
		pnTT.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lbltt = new JLabel("Trạng thái: ");
		lbltt.setFont(new Font("Arial", Font.PLAIN, 17));
		cboTrangThai=new JComboBox<TrangThai>();
		cboTrangThai.setPreferredSize(new Dimension(335, 20));
		pnTT.add(lbltt);
		pnTT.add(cboTrangThai);
		pnTopofBottom.add(pnTT);	 
		pnBottom.add(pnTopofBottom);
		
		
		lblMahv.setPreferredSize(lblTenhv1.getPreferredSize());
		lblns.setPreferredSize(lblTenhv1.getPreferredSize());
		lblLoai.setPreferredSize(lblTenhv1.getPreferredSize());
		
		lblEmail.setPreferredSize(lbltt.getPreferredSize());
		lblSdt.setPreferredSize(lbltt.getPreferredSize());
		lbldiachi.setPreferredSize(lbltt.getPreferredSize());
		//pnButton
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnSua = new JButton("Cập nhật");
 		btnTaoMoi = new JButton("Tạo mới");
		btnChuyenLop = new JButton("Chuyển lớp");
		btnBaoLuu = new JButton("Bảo lưu");
		btnQuayLai = new JButton("Quay lại");
		btnTaoMoi.setFont(new Font("Arial", Font.BOLD,17));
		//btnTaoMoi.setBackground(Color.PINK); 
		btnThem.setFont(new Font("Arial", Font.BOLD,17));
		btnXoa.setFont(new Font("Arial", Font.BOLD,17));
		btnSua.setFont(new Font("Arial", Font.BOLD,17));
		btnChuyenLop.setFont(new Font("Arial", Font.BOLD,17));
		btnBaoLuu.setFont(new Font("Arial", Font.BOLD,17));
		btnQuayLai.setFont(new Font("Arial", Font.BOLD,17));
		
		btnThem.setIcon(new ImageIcon("Hinh/save.png"));
		btnXoa.setIcon(new ImageIcon("Hinh/delete.png"));
		btnTaoMoi.setIcon(new ImageIcon("Hinh/plus.png"));
		btnSua.setIcon(new ImageIcon("Hinh/update.png"));
		btnQuayLai.setIcon(new ImageIcon("Hinh/back.png"));
		btnTim.setIcon(new ImageIcon("Hinh/find.png"));
		
		pnButton.add(btnTaoMoi);
		pnButton.add(btnThem);
		pnButton.add(btnXoa);
		pnButton.add(btnSua);
		pnButton.add(btnChuyenLop);
		pnButton.add(btnBaoLuu);
		pnButton.add(btnQuayLai);
		pnBottom.add(pnButton);
	}

	private void addEvents() {
		 btnTaoMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				txtMaHV.setText(LayMaHV());
				txtTenHV.setText(""); 
				txtNgaySinh.setText("");
				txtDiaChi.setText("");
				txtSDT.setText(""); 
				txtEmail.setText("");  
				cboLopHoc.setSelectedItem(null);
				cboTrangThai.setSelectedItem("Mới");
				HienThiToanBoHV();
			}
		});
		
		 btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(KiemTraTonTai()==0)
				 {
					if(KiemTaCuPhap()==0)
					{
						LuuHocVien();
						 
					}
					else
					{
						btnTaoMoi.doClick();
					}
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "Học viên đã tồn tai. Vui lòng kiểm tra lại!");
					 btnTaoMoi.doClick();
				 }
				 
				
				
			}
		});
		 
		 btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(KiemTaCuPhap()==0)
				{
				int xoa  = JOptionPane.showConfirmDialog(null,
	                    "Bạn có chắc chắn muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
				if(xoa == JOptionPane.YES_OPTION)
					XoaHocVien(); 
				btnTaoMoi.doClick();
				}
				else
				{
					btnTaoMoi.doClick();
				}
				
			}
		});
		 
		 btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HienThiTim();
				
			}
		});
		 
		 tblHocVien.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=tblHocVien.getSelectedRow();
				if(row==-1)return; 
				HocVien LH = dsHV.get(row);
				txtTenHV.setText(LH.getTenHV()); 
				txtNgaySinh.setText(df.format(LH.getNgaySinh()));
				txtDiaChi.setText(LH.getDiaChi());
				txtSDT.setText(LH.getSDT());
				txtEmail.setText(LH.getEmail());
				txtMaHV.setText(LH.getMaHV()); 
				hienThicboLopthemMa(LH.getMaLH());
				hienThiCboTrangThaiTheoMa(LH.getTrangThai());
				
			}
		});
		 
		 btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(KiemTaCuPhap()==0)
				{
					CapNhatHocVien(); 
				}
				else
				{
					btnTaoMoi.doClick();
				}
				
			}
		});
		 
		 btnQuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				QuayLai();
				ManHinhChinh ui = new ManHinhChinh("Quản Lý Trung Tâm Tin Học");
				ui.showWindows();
				
			}
		});
	
		 btnChuyenLop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MHChuyenLop chuyenlop = new MHChuyenLop("Chuyển Lớp");
				chuyenlop.ShowWindow();
			}
		});
		 
		 btnBaoLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(KiemTaCuPhap()==0)
				{
					BaoLuu();
				}
				else
				{
					btnTaoMoi.doClick();
				}
				
			}
		});
	}
	
	protected void BaoLuu() {
		 
		HocVien HV = new HocVien();
		HV.setMaHV(txtMaHV.getText()); 
		HV.setTrangThai("Bảo lưu");
	 
		if(cboLopHoc.getSelectedItem()==null)
		{
			JOptionPane.showMessageDialog(null, "Học viên chưa học tại trung tâm!");
		}
		else
		{
			KetNoiHV kn =  new KetNoiHV();
			if(kn.BaoLuuHoVien(HV) > 0)
			{
				JOptionPane.showMessageDialog(null, "Bảo lưu học viên thành công");
				HienThiToanBoHV(); 
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Bảo lưu học viên thất bại");
			}
		}
	}

	protected void hienThiCboTrangThaiTheoMa(String trangThai) {
		if(trangThai.equals(""))
		{
			cboTrangThai.setSelectedItem(null);
		}
		else
		{
			KetNoiTT kncth=new KetNoiTT();
			Vector<TrangThai>vec1=kncth.HienThiTTTheoTen(trangThai); 
			cboTrangThai.removeAllItems();
			
			KetNoiTT dmService=new KetNoiTT();
			Vector<TrangThai>vec=dmService.docToanBoTrangThai();
			TrangThai a = new TrangThai();
			a = (TrangThai) vec1.toArray()[0];
			for(TrangThai dm : vec)
			{
				cboTrangThai.addItem(dm);
				if(dm.getTenTT().equals(a.getTenTT()))
					cboTrangThai.setSelectedItem(dm);
			} 
		}
	}

	protected void hienThicboLopthemMa(String maLH) {
		if(maLH == null)
		{
			cboLopHoc.setSelectedItem(null);
		}
		else
		{
			KetNoiLH kncth=new KetNoiLH();
			Vector<LopHoc>vec1=kncth.HienThiLopHocTheoMa(maLH); 
			cboLopHoc.removeAllItems();
			
			KetNoiLH dmService=new KetNoiLH();
			Vector<LopHoc>vec=dmService.docToanBoLopHoc();
			LopHoc a = new LopHoc();
			a = (LopHoc) vec1.toArray()[0];
			for(LopHoc dm : vec)
			{
				cboLopHoc.addItem(dm);
				if(dm.getTenLH().equals(a.getTenLH())) 
					cboLopHoc.setSelectedItem(dm);
			} 
		}
		
	}

	protected void HienThiTim() {

		if(txtTim.getText().equals(""))
		{
			HienThiToanBoHV();
			JOptionPane.showMessageDialog(null, "Vui lòng nhập học viên cần tìm!!!");
		}
		else
		{
			String a1 = txtTim.getText();
			KetNoiHV kngt = new KetNoiHV();
			dsHV = kngt.TimHocVien(a1);	
			dmHocVien.setRowCount(0);
			for(HocVien a : dsHV)
			{
				Vector<Object> vec = new Vector<Object>();
				vec.add(a.getMaHV());
				vec.add(a.getTenHV());
				vec.add(a.getNgaySinh());
				vec.add(a.getDiaChi());
				vec.add(a.getSDT());
				vec.add(a.getEmail());
				vec.add(a.getMaLH());
				
				dmHocVien.addRow(vec);
			}
		}
		
	}

	protected int KiemTraTonTai() {
		KetNoiHV knLH = new KetNoiHV();
		return knLH.KiemTraTonTai(txtTenHV.getText());
	}

	protected int KiemTaCuPhap() {
		if(txtTenHV.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Sai cú pháp. Vui lòng kiểm tra lại");
			return 1;
		}
		
		if(txtNgaySinh.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Sai cú pháp. Vui lòng kiểm tra lại");
			return 1;
		}
		
		//if(txtEmail.getText())
		
		return 0;
	}

	protected void QuayLai() {
		this.dispose();
		
	}

	protected void CapNhatHocVien() {
		HocVien HV = new HocVien();
		HV.setMaHV(txtMaHV.getText());
		HV.setTenHV(txtTenHV.getText()); 
		
		Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(df.parse(txtNgaySinh.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HV.setNgaySinh(sqlDate); 
		HV.setDiaChi(txtDiaChi.getText());
		HV.setSDT(txtSDT.getText());
		HV.setEmail(txtEmail.getText());
		HV.setTrangThai(cboTrangThai.getSelectedItem().toString());
		KetNoiHV kn =  new KetNoiHV();
		if(kn.CapNhatHocVien(HV) > 0)
		{
			JOptionPane.showMessageDialog(null, "Cập nhật học viên thành công");
			HienThiToanBoHV(); 
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Cập nhật học viên thất bại");
		}
		
	}

	protected void XoaHocVien()
	{
		 
		HocVien LH = new HocVien();
		LH.setMaHV(txtMaHV.getText());
		KetNoiHV kn =  new KetNoiHV();
		if(kn.XoaHocVien(LH) > 0)
		{
			JOptionPane.showMessageDialog(null, "Xóa học viên thành công");
			HienThiToanBoHV();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa học viên thất bại");
		}
	}

	public void LuuHocVien() 
	{

		HocVien HV = new HocVien();
		HV.setMaHV(txtMaHV.getText()); 
		HV.setTenHV(txtTenHV.getText()); 
		
		Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(df.parse(txtNgaySinh.getText()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		HV.setNgaySinh(sqlDate); 
		HV.setDiaChi(txtDiaChi.getText());
		HV.setSDT(txtSDT.getText());
		HV.setEmail(txtEmail.getText());  
		HV.setMaLH(null);
		HV.setTrangThai("Mới");
	 
		KetNoiHV kn =  new KetNoiHV();
		if(kn.ThemMoiHocVien(HV) > 0)
		{
			JOptionPane.showMessageDialog(null, "Lưu học viên thành công");
			HienThiToanBoHV(); 
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Lưu học viên thất bại");
		}

		
	}

	protected String LayMaHV() 
	{ 
			KetNoiHV knHV = new KetNoiHV(); 
			String a  = knHV.LayMaHVien();
			int ViTriCuoi=a.lastIndexOf("V");
			String TenMa=a.substring(ViTriCuoi+1);
			int b = Integer.parseInt(TenMa);
			int c = b+1;
			String MaHV1 = String.format("HV%03d", c);  
			return MaHV1;
		 
	}

	public void showWindow()
	{
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void HienThiToanBoHV()
	{
		KetNoiHV knHV = new KetNoiHV();
		dsHV = knHV.LayToanBoHocVien();
		dmHocVien.setRowCount(0);
		for(HocVien a : dsHV)
		{ 	
			KetNoiLH knlh = new KetNoiLH(); 
			String b  = knlh.LayTenLopHoc1(a.getMaLH());
			Vector<Object> vec = new Vector<Object>();
			vec.add(a.getMaHV());
			vec.add(a.getTenHV());
			vec.add(a.getNgaySinh());
			vec.add(a.getDiaChi());
			vec.add(a.getSDT());
			vec.add(a.getEmail());
			vec.add(b);
			vec.add(a.getTrangThai());
			dmHocVien.addRow(vec);
		}
	} 
	 

	public void hienThicboMLHa() {
		KetNoiLH dmService=new KetNoiLH();
		Vector<LopHoc>vec=dmService.docToanBoLopHoc();
		
		cboLopHoc.removeAllItems();
		for(LopHoc dm : vec)
		{
			cboLopHoc.addItem(dm);
		}
	}
	
	public void hienThicboTT() {
		KetNoiTT dmService=new KetNoiTT();
		Vector<TrangThai>vec=dmService.docToanBoTrangThai();
		
		cboTrangThai.removeAllItems();
		for(TrangThai dm : vec)
		{
			cboTrangThai.addItem(dm);
		}
	}
	
	 
}
