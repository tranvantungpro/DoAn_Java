package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.sun.prism.Graphics;
import com.sun.xml.internal.ws.api.Component;

import Moudle.GiaoTrinh;
import Service.KetNoiGT;
import Service.KetNoiSQL;

public class MHQuanLyGiaoTrinh extends JFrame
{
	//delare
	JTextField txtMa, txtTen, txtLoai, txtNXB, txtTim;  
	JButton btnThem, btnXoa, btnSua, btnTimKiem, btnTaoMoi, btnQuayLai;
	DefaultTableModel dtmGiaoTrinh;
	JTable  tblGiaoTrinh;

	ArrayList<GiaoTrinh> dsGT = null;
	ArrayList<GiaoTrinh> dsThem = null;
	ArrayList<GiaoTrinh> dsGTTim = null;
	static String MaGiaoTrinh = "";


	public MHQuanLyGiaoTrinh (String tieude)
	{
		super(tieude);
		addContronls();
		addEvents();
		HienThiToanBoGT();
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
						LuuMoiGT();
						 
					}
					else
					{
						btnTaoMoi.doClick();
					}
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(null, "Giáo trình đã tồn tại. Vui lòng nhập lại!");
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
					XoaGiaoTrinh();
				btnTaoMoi.doClick();
				}
				else
				{
					btnTaoMoi.doClick();
				}

			}
		});

		tblGiaoTrinh.addMouseListener(new MouseListener() {

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
				int row=tblGiaoTrinh.getSelectedRow();
				if(row==-1)return; 
				GiaoTrinh gt = dsGT.get(row);
				txtMa.setText(gt.getMaGT());
				txtTen.setText(gt.getTenGT());
				txtLoai.setText(gt.getLoaiGT());
				txtNXB.setText(gt.getNXB());
			}
		});

		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CapNhatGiaoTrinh();

			}
		});

		btnTaoMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtMa.setText(LayMaGT());
				txtTen.setText("");
				txtLoai.setText("");
				txtNXB.setText("");
				HienThiToanBoGT();

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

	protected void QuayLai() 
	{
		this.dispose(); 
		
	}

	protected void NhapTT() {


	}

	private void addContronls() 
	{

		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		//create slip
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BorderLayout());
		pnTop.setBackground(Color.red);
		pnTop.setPreferredSize(new Dimension(0, 470));
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop, pnBottom);
		//sp.setOneTouchExpandable(true);
		con.add(sp, BorderLayout.CENTER);
		
		JPanel pnTopOfTop = new JPanel();
		pnTopOfTop.setLayout(new BoxLayout(pnTopOfTop, BoxLayout.Y_AXIS));
		
		//pnTieuDe
		JPanel pnTieuDe = new JPanel();
		JLabel lblTieude = new JLabel("QUẢN LÝ GIÁO TRÌNH");
		lblTieude.setForeground(Color.BLUE);
		lblTieude.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieude.setIcon(new ImageIcon("Hinh/QLGT.png"));
		pnTieuDe.add(lblTieude);
		pnTopOfTop.add(pnTieuDe);
		 
		
		//pnTim
		JPanel pnTim = new JPanel();
		JLabel lblTenGT = new JLabel("Nhập tên giáo trình: ");
		lblTenGT.setFont(new Font("Arial", Font.PLAIN, 17));
		txtTim = new JTextField(20);
		btnTimKiem = new JButton("Tìm");
		pnTim.add(lblTenGT);
		pnTim.add(txtTim);
		pnTim.add(btnTimKiem);
		pnTopOfTop.add(pnTim);
		pnTop.add(pnTopOfTop, BorderLayout.NORTH);

		//pnTop
		dtmGiaoTrinh = new DefaultTableModel();
		dtmGiaoTrinh.addColumn("Mã Giáo Trình");
		dtmGiaoTrinh.addColumn("Tên Giáo Trình");
		dtmGiaoTrinh.addColumn("Loại Giáo Trình");
		dtmGiaoTrinh.addColumn("Nhà Xuất Bản");
		tblGiaoTrinh = new JTable(dtmGiaoTrinh);
		JScrollPane sptable = new JScrollPane(tblGiaoTrinh, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTop.add(sptable);
		
		
		//pnBottom
		JPanel pnTopofBottom = new JPanel();
		pnTopofBottom.setLayout(new BoxLayout(pnTopofBottom, BoxLayout.Y_AXIS));
		//pnMa Ma
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblMa = new JLabel("Mã giáo trình: ");
		lblMa.setFont(new Font("Arial", Font.PLAIN, 17));
		txtMa = new JTextField(30);
		txtMa.setEditable(false);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		pnTopofBottom.add(pnMa);
		//pnMa Ten
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTen = new JLabel("Tên giáo trình: ");
		lblTen.setFont(new Font("Arial", Font.PLAIN, 17));
		txtTen = new JTextField(30);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		pnTopofBottom.add(pnTen);
		//pnMa loai
		JPanel pnLoai = new JPanel();
		pnLoai.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblLoai = new JLabel("Loại giáo trình: ");
		lblLoai.setFont(new Font("Arial", Font.PLAIN, 17));
		txtLoai = new JTextField(30);
		pnLoai.add(lblLoai);
		pnLoai.add(txtLoai);
		pnTopofBottom.add(pnLoai);		
		//pnMa nxb
		JPanel pnNXB = new JPanel();
		pnNXB.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblNXB = new JLabel("Nhà xuất bản: ");
		lblNXB.setFont(new Font("Arial", Font.PLAIN, 17));
		txtNXB = new JTextField(30);
		pnNXB.add(lblNXB);
		pnNXB.add(txtNXB);
		pnTopofBottom.add(pnNXB);	
		pnBottom.add(pnTopofBottom);

		lblMa.setPreferredSize(lblLoai.getPreferredSize());
		lblTen.setPreferredSize(lblLoai.getPreferredSize());
		lblNXB.setPreferredSize(lblLoai.getPreferredSize());

		//pnButton
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnSua = new JButton("Cập nhật");
		btnTaoMoi =new JButton("Tạo Mới");
		btnQuayLai = new JButton("Quay lại");
		
		btnThem.setFont(new Font("Arial", Font.BOLD,17));
		btnXoa.setFont(new Font("Arial", Font.BOLD,17));
		btnSua.setFont(new Font("Arial", Font.BOLD,17));
		btnTaoMoi.setFont(new Font("Arial", Font.BOLD,17));
		btnThem.setFont(new Font("Arial", Font.BOLD,17));
		btnQuayLai.setFont(new Font("Arial", Font.BOLD,17));
		btnTimKiem.setFont(new Font("Arial", Font.BOLD,17));
		
		btnThem.setIcon(new ImageIcon("Hinh/save.png"));
		btnXoa.setIcon(new ImageIcon("Hinh/delete.png"));
		btnTaoMoi.setIcon(new ImageIcon("Hinh/plus.png"));
		btnSua.setIcon(new ImageIcon("Hinh/update.png"));
		btnQuayLai.setIcon(new ImageIcon("Hinh/back.png"));
		btnTimKiem.setIcon(new ImageIcon("Hinh/find.png"));
		
		//btnThem 
		pnButton.add(btnTaoMoi);
		pnButton.add(btnThem);
		pnButton.add(btnXoa);
		pnButton.add(btnSua);
		pnButton.add(btnQuayLai);
		pnBottom.add(pnButton);

	}

	public void showWindow()
	{
		this.setSize(750, 700);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	protected void CapNhatGiaoTrinh() 
	{
		GiaoTrinh gt = new GiaoTrinh();
		gt.setMaGT(txtMa.getText());
		gt.setTenGT(txtTen.getText());
		gt.setLoaiGT(txtLoai.getText());
		gt.setNXB(txtNXB.getText());
		KetNoiGT kn =  new KetNoiGT();
		if(kn.CapNhatGiaoTrinh(gt) > 0)
		{
			JOptionPane.showMessageDialog(null, "Cập nhật giáo trình thành công");
			HienThiToanBoGT();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Cập nhật giáo trình thất bại");
		}

	}

	protected void XoaGiaoTrinh() 
	{
		GiaoTrinh gt = new GiaoTrinh();
		gt.setMaGT(txtMa.getText());
		KetNoiGT kn =  new KetNoiGT();
		if(kn.XoaGiaoTrinh(gt) > 0)
		{
			JOptionPane.showMessageDialog(null, "Xóa giáo trình thành công");
			HienThiToanBoGT();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Xóa giáo trình thất bại");
		}
	}

	protected void LuuMoiGT() 
	{
		GiaoTrinh gt = new GiaoTrinh();
		gt.setMaGT(txtMa.getText());
		gt.setTenGT(txtTen.getText());
		gt.setLoaiGT(txtLoai.getText());
		gt.setNXB(txtNXB.getText());
		KetNoiGT kn =  new KetNoiGT();
		if(kn.ThemMoiGiaoTrinh(gt) > 0)
		{
			JOptionPane.showMessageDialog(null, "Lưu giáo trình thành công");
			HienThiToanBoGT();
			btnTaoMoi.doClick();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Lưu giáo trình thất bại");
		}

	}

	public String LayMaGT()
	{
		KetNoiGT kngt = new KetNoiGT();
		GiaoTrinh a = new GiaoTrinh();
		a = kngt.LayMaGT();
		MaGiaoTrinh=a.getMaGT();

		int ViTriCuoi=MaGiaoTrinh.lastIndexOf("T");
		String TenMa=MaGiaoTrinh.substring(ViTriCuoi+1);
		int b = Integer.parseInt(TenMa);
		int c = b+1;
		String MaGT = String.format("GT%03d", c);
		return MaGT;
	}

	public void HienThiToanBoGT()
	{
		KetNoiGT kngt = new KetNoiGT();
		dsGT = kngt.LayToanBoGiaoTrinh();
		dtmGiaoTrinh.setRowCount(0);
		for(GiaoTrinh a : dsGT)
		{
			Vector<Object> vec = new Vector<Object>();
			vec.add(a.getMaGT());
			vec.add(a.getTenGT());
			vec.add(a.getLoaiGT());
			vec.add(a.getNXB());
			dtmGiaoTrinh.addRow(vec);
		}
	} 

	public void HienThiTim()
	{
		if(txtTim.getText().equals(""))
		{
			HienThiToanBoGT();
			JOptionPane.showMessageDialog(null, "Vui lòng nhập giáo trình cần tìm!!!");
		}
		else
		{
			String a1 = txtTim.getText();
			KetNoiGT kngt = new KetNoiGT();
			dsGTTim = kngt.TimGiaoTrinh(a1);	
			dtmGiaoTrinh.setRowCount(0);
			for(GiaoTrinh a : dsGTTim)
			{
				Vector<Object> vec = new Vector<Object>();
				vec.add(a.getMaGT());
				vec.add(a.getTenGT());
				vec.add(a.getLoaiGT());
				vec.add(a.getNXB());
				dtmGiaoTrinh.addRow(vec);
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
		return 0;
	}

	public int KiemTraTonTai()
	{
		KetNoiGT kngt = new KetNoiGT();
		return kngt.KiemTraTonTai(txtTen.getText());
		 
	}

	 
}
