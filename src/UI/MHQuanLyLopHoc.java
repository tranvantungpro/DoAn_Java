package UI;

import javax.swing.JFrame;
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

import Moudle.CTH;
import Moudle.GiangVien;
import Moudle.LoaiLopHoc;
import Moudle.LopHoc;
import Service.KetNoiCTH;
import Service.KetNoiGV;
import Service.KetNoiLH;
import Service.KetNoiLLH;
import Service.KetNoiSQL;

public class MHQuanLyLopHoc extends JFrame
{
	//delare
		JTextField txtMa, txtTen, txtLoai, txtMaCH, txtSoBuoi, txtngaybd, txtngaykt, txtMagv, txtTim;  
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
		Calendar cal = Calendar.getInstance();
		
		 


		public MHQuanLyLopHoc (String tieude)
		{
			super(tieude);
			addContronls();
			addEvents();
			HienThiToanBoLH();
			hienThiDanhMucLenList();
			hienThicboCTH();
			hienThicboGV();
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
					txtSoBuoi.setText(Integer.toString(LH.getSoBuoi()));  
					txtngaybd.setText(df.format(LH.getNgayBD()));
					txtngaykt.setText(df.format(LH.getNgayKT()));
					 hienThicboGVthemMa(LH.getMaGV());
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
					txtSoBuoi.setText("");
					txtngaybd.setText(LayNgayHienHanh());
					txtngaykt.setText(""); 
					HienThiToanBoLH();
					hienThiDanhMucLenList();
					hienThicboCTH();
					hienThicboGV();
					cboGV.setSelectedItem(null);
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
		}

		public void QuayLai() 
		{
			this.dispose(); 
		}

		protected void hienThicboCTHthemMa(String maCTH) {
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

		public  void hienThicboLoaiLHthemMa(String ma) {
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

		protected void NhapTT() {


		}

		private void addContronls() 
		{

			Container con = getContentPane();
			con.setLayout(new BorderLayout());

			//create slip
			JPanel pnTop = new JPanel();
			pnTop.setLayout(new BorderLayout());
			pnTop.setPreferredSize(new Dimension(0, 485));
			JPanel pnBottom = new JPanel();
			pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
			JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnTop, pnBottom);
			//sp.setOneTouchExpandable(true);
			con.add(sp, BorderLayout.CENTER);

			//pnTim
			JPanel pnTim = new JPanel();
			JLabel lblTenLH = new JLabel("Nhập tên lớp học: ");
			lblTenLH.setFont(new Font("Arial", Font.PLAIN, 15));
			txtTim = new JTextField(20);
			btnTimKiem = new JButton("Tìm");
			pnTim.add(lblTenLH);
			pnTim.add(txtTim);
			pnTim.add(btnTimKiem);
			pnTop.add(pnTim, BorderLayout.NORTH);

			//pnTop
			dtmLopHoc = new DefaultTableModel();
			dtmLopHoc.addColumn("Mã Lớp Học");
			dtmLopHoc.addColumn("Tên Lớp Học");
			dtmLopHoc.addColumn("Loại Loại Học");
			dtmLopHoc.addColumn("Chương Trình Học");
			dtmLopHoc.addColumn("Số Buổi");
			dtmLopHoc.addColumn("Ngày Bắt Đầu");
			dtmLopHoc.addColumn("Ngày Kết Thúc");
			dtmLopHoc.addColumn("Tên Giảng Viên"); 
			
			tblLopHoc = new JTable(dtmLopHoc);
			JScrollPane sptable = new JScrollPane(tblLopHoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			pnTop.add(sptable);
			//pnBottom
			JPanel pnTopofBottom = new JPanel();
			pnTopofBottom.setLayout(new BoxLayout(pnTopofBottom, BoxLayout.Y_AXIS));
			
			//
//			JPanel pntrai =  new JPanel();
//			JPanel pnphai = new JPanel();
//			JSplitPane sp1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pntrai, pnphai);
//			//sp1.setOneTouchExpandable(true);
//
//			pntrai.setPreferredSize(new Dimension(100, 0));
//			pnTopofBottom.add(sp1);
			//
			//pnMa Ma
			JPanel pnMa = new JPanel();
			pnMa.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel lblMa = new JLabel("Mã lớp học:");
			lblMa.setFont(new Font("Arial", Font.PLAIN, 15));
			txtMa = new JTextField(20);
			txtMa.setEditable(false);
			JLabel lblsoBuoi = new JLabel("Số Buổi: ");
			lblsoBuoi.setFont(new Font("Arial", Font.PLAIN, 15));
			txtSoBuoi = new JTextField(20);
			pnMa.add(lblMa);
			pnMa.add(txtMa);
			pnMa.add(lblsoBuoi);
			pnMa.add(txtSoBuoi);
			pnTopofBottom.add(pnMa);
			//pnMa Ten
			JPanel pnTen = new JPanel();
			pnTen.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel lblTen = new JLabel("Tên lớp học: ");
			lblTen.setFont(new Font("Arial", Font.PLAIN, 15));
			txtTen = new JTextField(20);
			JLabel lblnbd = new JLabel("Ngày bắt đầu: ");
			lblnbd.setFont(new Font("Arial", Font.PLAIN, 15));
			txtngaybd = new JTextField(20);
		
			
			pnTen.add(lblTen);
			pnTen.add(txtTen);
			pnTen.add(lblnbd);
			pnTen.add(txtngaybd);
			pnTopofBottom.add(pnTen);
			
			//pnMa loai
			JPanel pnLoai = new JPanel();
			pnLoai.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel lblLoai = new JLabel("Loại lớp học: ");
			lblLoai.setFont(new Font("Arial", Font.PLAIN, 15));
			cboLoaiLH=new JComboBox<LoaiLopHoc>();
			cboLoaiLH.setPreferredSize(new Dimension(270, 20));
			JLabel lblnkt = new JLabel("Ngày kết thúc: ");
			lblnkt.setFont(new Font("Arial", Font.PLAIN, 15));
			txtngaykt = new JTextField(20);
			pnLoai.add(lblLoai);
			pnLoai.add(cboLoaiLH);
			pnLoai.add(lblnkt);
			pnLoai.add(txtngaykt);
			pnTopofBottom.add(pnLoai);		
			
			//pnMa mcth
			JPanel pnCTH = new JPanel();
			pnCTH.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel lblcth = new JLabel("Chương trình học: ");
			lblcth.setFont(new Font("Arial", Font.PLAIN, 15));
			cboCTH=new JComboBox<CTH>();
			cboCTH.setPreferredSize(new Dimension(270, 20));
			JLabel lblgv = new JLabel("Giảng viên: ");
			lblgv.setFont(new Font("Arial", Font.PLAIN, 15));
			cboGV=new JComboBox<GiangVien>();
			cboGV.setPreferredSize(new Dimension(270, 20));
			pnCTH.add(lblcth);
			pnCTH.add(cboCTH);
			pnCTH.add(lblgv);
			pnCTH.add(cboGV);
			pnTopofBottom.add(pnCTH);	
			pnBottom.add(pnTopofBottom);
			


			//lblMa.setPreferredSize(lblLoai.getPreferredSize());
//			lblTen.setPreferredSize(lblLoai.getPreferredSize());
//			lblcth.setPreferredSize(lblLoai.getPreferredSize());
			
//			lblNXBa.setPreferredSize(lbl1.getPreferredSize());
//			lbl11.setPreferredSize(lbl1.getPreferredSize());
//			lbl111.setPreferredSize(lbl1.getPreferredSize());
			 
			

			//pnButton
			JPanel pnButton = new JPanel();
			pnButton.setLayout(new FlowLayout(FlowLayout.CENTER));
			btnThem = new JButton("Thêm");
			btnXoa = new JButton("Xóa");
			btnSua = new JButton("Cập nhật");
			btnTaoMoi =new JButton("Tạo Mới");
			btnXepLich = new JButton("Xếp lịch giảng viên");
			btnQuayLai = new JButton("Quay lại");
			pnButton.add(btnTaoMoi);
			pnButton.add(btnThem);
			pnButton.add(btnXoa);
			pnButton.add(btnSua);
			pnButton.add(btnXepLich);
			pnButton.add(btnQuayLai);
			pnBottom.add(pnButton);

		}

		public void showWindow()
		{
			this.setSize(900, 700);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}

		private void hienThiDanhMucLenList() {
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
		
		protected void CapNhatLopHoc() 
		{
			LopHoc LH = new LopHoc();
			LH.setMaLH(txtMa.getText());
			LH.setTenLH(txtTen.getText());
			LH.setLoaiLH(cboLoaiLH.getSelectedItem().toString());
			
			LH.setMaCTH(LayMaCTH(cboCTH.getSelectedItem().toString()));
			LH.setSoBuoi(Integer.parseInt(txtSoBuoi.getText()));
			
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
			LH.setMaGV(LayMaGV(cboGV.getSelectedItem().toString()));
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
			LH.setMaCTH(LayMaCTH(cboCTH.getSelectedItem().toString()));
			LH.setSoBuoi(Integer.parseInt(txtSoBuoi.getText()));
			
			
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
			 
			LH.setMaGV(LayMaGV(cboGV.getSelectedItem().toString()));
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
				KetNoiCTH knlh = new KetNoiCTH(); 
				String b  = knlh.LayTenCTH(a.getMaCTH());
				
				KetNoiGV kngv = new KetNoiGV(); 
				String c  = kngv.LayTenGV(a.getMaGV());
				
				Vector<Object> vec = new Vector<Object>();
				vec.add(a.getMaLH());
				vec.add(a.getTenLH());
				vec.add(a.getLoaiLH());
				vec.add(b);
				vec.add(a.getSoBuoi());
				vec.add(a.getNgayBD());
				vec.add(a.getNgayKT());
				vec.add(c);
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
					vec.add(a.getMaLH());
					vec.add(a.getTenLH());
					vec.add(a.getLoaiLH());
					vec.add(a.getMaCTH());
					vec.add(a.getSoBuoi());
					vec.add(a.getNgayBD());
					vec.add(a.getNgayKT());
					vec.add(a.getMaGV());
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
			 
			
			if(txtSoBuoi.getText().equals(""))
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
}
