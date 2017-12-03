package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import Moudle.HocVien;
import Moudle.LopHoc;
import Moudle.HocVien;
import Service.KetNoiHV;
import Service.KetNoiLH;

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

	public MHQuanLyHocVien(String tieude) {
		super("Quản Lý Học Viên");
		addContronls();
		addEvents();
		HienThiToanBoHV();
		}

	private void addContronls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		//create slip
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setPreferredSize(new Dimension(0, 505));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop, pnBottom);
		sp.setOneTouchExpandable(true);
		con.add(sp, BorderLayout.CENTER);

		//pnTim
		JPanel pnTim = new JPanel();
		JLabel lblTenhv = new JLabel("Nhập tên học viên: ");
		lblTenhv.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTim = new JTextField(20);
		btnTim = new JButton("Tìm");
		pnTim.add(lblTenhv);
		pnTim.add(txtTim);
		pnTim.add(btnTim);
		pnTop.add(pnTim, BorderLayout.NORTH);

		//pnTop
		dmHocVien = new DefaultTableModel();
		dmHocVien.addColumn("Mã Học Viên");
		dmHocVien.addColumn("Tên Học Viên");
		dmHocVien.addColumn("Ngày Sinh");
		dmHocVien.addColumn("Địa Chỉ");
		dmHocVien.addColumn("Số Điện Thoại");
		dmHocVien.addColumn("Email");
		dmHocVien.addColumn("Lớp Học"); 
		
		tblHocVien = new JTable(dmHocVien);
		JScrollPane sptable = new JScrollPane(tblHocVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(sptable);
		
		//pnBottom
		JPanel pnTopofBottom = new JPanel();
		pnTopofBottom.setLayout(new BoxLayout(pnTopofBottom, BoxLayout.Y_AXIS));
		//pnMa Ma
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblMahv = new JLabel("Mã học viên:");
		lblMahv.setFont(new Font("Arial", Font.PLAIN, 15));
		txtMaHV = new JTextField(30);
		txtMaHV.setEditable(false);
		JLabel lblSdt = new JLabel("SDT:  ");
		lblSdt.setFont(new Font("Arial", Font.PLAIN, 15));
		txtSDT = new JTextField(30);
		pnMa.add(lblMahv);
		pnMa.add(txtMaHV);
		pnMa.add(lblSdt);
		pnMa.add(txtSDT);
		pnTopofBottom.add(pnMa);
		
		//pnMa Ten
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTenhv1 = new JLabel("Tên học viên:");
		lblTenhv1.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTenHV = new JTextField(30);
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEmail = new JTextField(30);
		pnTen.add(lblTenhv1);
		pnTen.add(txtTenHV);
		pnTen.add(lblEmail);
		pnTen.add(txtEmail);
		pnTopofBottom.add(pnTen);
		
		//pnMa loai
		JPanel pnNS = new JPanel();
		pnNS.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblns = new JLabel("Ngày Sinh:");
		lblns.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNgaySinh = new JTextField(30);
		JLabel lbldiachi = new JLabel("Địa chỉ:");
		lbldiachi.setFont(new Font("Arial", Font.PLAIN, 15)); 
		txtDiaChi = new JTextField(30);
		pnNS.add(lblns);
		pnNS.add(txtNgaySinh);
		pnNS.add(lbldiachi);
		pnNS.add(txtDiaChi);
		pnTopofBottom.add(pnNS);	 
		pnBottom.add(pnTopofBottom);  
		
		lblMahv.setPreferredSize(lblTenhv1.getPreferredSize());
		lblns.setPreferredSize(lblTenhv1.getPreferredSize()); 
		   
		
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
		btnTaoMoi.setFont(new Font("Arial", Font.BOLD,15));
		//btnTaoMoi.setBackground(Color.PINK); 
		btnThem.setFont(new Font("Arial", Font.BOLD,15));
		btnXoa.setFont(new Font("Arial", Font.BOLD,15));
		btnSua.setFont(new Font("Arial", Font.BOLD,15));
		btnChuyenLop.setFont(new Font("Arial", Font.BOLD,15));
		btnBaoLuu.setFont(new Font("Arial", Font.BOLD,15));
		btnQuayLai.setFont(new Font("Arial", Font.BOLD,15));
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
				
			}
		});
		
		 btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LuuHocVien();
				
			}
		});
		 
		 btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				XoaHocVien();
				
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
				txtMaHV.setText(LH.getMaLH());
				txtMaHV.setText(LH.getMaHV());
				
			}
		});
		 
		 btnSua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CapNhatHocVien();
				
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
	 
		KetNoiHV kn =  new KetNoiHV();
		JOptionPane.showMessageDialog(null, kn.CapNhatHocVien(HV));
		if(kn.CapNhatHocVien(HV) > 0)
		{
			JOptionPane.showMessageDialog(null, "Cập nhật lớp học thành công");
			HienThiToanBoHV(); 
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Cập nhật lớp học thất bại");
		}
		
	}

	protected void XoaHocVien()
	{
		 
		HocVien LH = new HocVien();
		LH.setMaHV(txtMaHV.getText());
		KetNoiHV kn =  new KetNoiHV();
		if(kn.XoaHocVien(LH) > 0)
		{
			JOptionPane.showMessageDialog(null, "Xóa lớp học thành công");
			HienThiToanBoHV();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa lớp học thất bại");
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
	 
		KetNoiHV kn =  new KetNoiHV();
		if(kn.ThemMoiHocVien(HV) > 0)
		{
			JOptionPane.showMessageDialog(null, "Lưu lớp học thành công");
			HienThiToanBoHV(); 
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Lưu lớp học thất bại");
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
		this.setSize(900, 700);
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
