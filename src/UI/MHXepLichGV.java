package UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Moudle.GiangVien;
import Moudle.LopHoc;
import Service.KetNoiGV;
import Service.KetNoiLH;
import Service.KetNoiSQL;

public class MHXepLichGV extends JFrame
{
	JComboBox<LopHoc> cboLoHoc;
	JComboBox<GiangVien> cboGiangVien;
	JButton btnXepLop,btnQuayLai;

	public MHXepLichGV (String Tieude)
	{
		super(Tieude);
		addControls();
		addEvents();
		hienThicboLH();
		hienThicboGV();
	}

	private void addControls() 
	{
		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		//pnServer
		JPanel pnTop= new JPanel();
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));

		JPanel pnLopHoc = new JPanel();
		JLabel lblLop = new JLabel("Lớp học:");
		lblLop.setFont(new Font("Arial", Font.PLAIN, 15));
		cboLoHoc = new JComboBox<LopHoc>();
		cboLoHoc.setPreferredSize(new Dimension(332, 20));
		pnLopHoc.add(lblLop);
		pnLopHoc.add(cboLoHoc);
		pnTop.add(pnLopHoc);
		con.add(pnTop, BorderLayout.NORTH);

		JPanel pnGv = new JPanel();
		JLabel lblGv = new JLabel("Giảng Viên:");
		lblGv.setFont(new Font("Arial", Font.PLAIN, 15));
		cboGiangVien = new JComboBox<GiangVien>();
		cboGiangVien.setPreferredSize(new Dimension(332, 20));
		pnGv.add(lblGv);
		pnGv.add(cboGiangVien);
		pnTop.add(pnGv);
		con.add(pnTop);

		JPanel pnButton = new JPanel();
		btnXepLop = new JButton("Xếp lớp");
		btnQuayLai = new JButton("Quay lại");
		pnBottom.add(btnXepLop);
		pnBottom.add(btnQuayLai);
		con.add(pnBottom, BorderLayout.SOUTH);
		lblLop.setPreferredSize(lblGv.getPreferredSize());

	}

	private void addEvents() 
	{ 
		btnQuayLai.addMouseListener(new MouseListener() {

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
			public void mouseClicked(MouseEvent e)
			{
				Quaylai();
			}

		});

		btnXepLop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(KiemTraGiangVienCuaLopHoc(cboLoHoc.getSelectedItem().toString()) != 0)
				{
					if(KiemTraSoLopGiangVien(cboGiangVien.getSelectedItem().toString()) != 0)
					{
						XepLop(cboGiangVien.getSelectedItem(), cboLoHoc.getSelectedItem());
					}
				}
			}
		});
	}


	protected int KiemTraGiangVienCuaLopHoc(String tenlh) {
		KetNoiLH a = new KetNoiLH();
		LopHoc lh = new LopHoc();
		lh =  a.LayMalophoc(tenlh);
		
		if(lh.getMaGV()!= null)
		{
			JOptionPane.showMessageDialog(null ,"Lớp học đã có giảng viên, không thể xếp lớp", "Thông báo", JOptionPane.PLAIN_MESSAGE);
			return 0;
		}
		return 1;
	}

	protected int KiemTraSoLopGiangVien(String tengv) {
		KetNoiGV gv = new KetNoiGV();
		GiangVien a = new GiangVien();
		a = gv.LayMaGV(tengv);

		if(a.getSoLop() == 3)
		{
			JOptionPane.showMessageDialog(null, "Giảng viên đã dạy 3 lớp, vui lòng chọn giảng viên khác!");
			return 0;
		}
		return 1;
	}

	protected void XepLop(Object selectedItem, Object selectedItem2) 
	{
		String aa = cboGiangVien.getSelectedItem().toString();
		String bb = cboLoHoc.getSelectedItem().toString();
		String cc =  LayMaGV(aa);
		JOptionPane.showMessageDialog(null, cc);
		KetNoiLH knLH = new KetNoiLH();
		KetNoiGV gv = new  KetNoiGV(); 
		GiangVien a = new GiangVien();
		a = gv.SoLopHT(aa);

		int solop =  a.getSoLop();

		if(knLH.XepLop(cc, bb)> 0)
		{
			if(gv.CapNhatSoLopGiangVien(cc, solop+1) >0)
			{
				JOptionPane.showMessageDialog(null, "Xếp lớp thành công");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Xếp lớp thất bại");
			}
		}

	}

	public void Quaylai() 
	{
		this.dispose(); 
	}

	public String LayMaGV(String tengv)
	{
		KetNoiGV knGV = new KetNoiGV();
		GiangVien a = new GiangVien();
		a = knGV.LayMaGV(tengv);

		return a.getMaGV();
	}

	public void hienThicboGV() {
		KetNoiGV a=new KetNoiGV();
		Vector<GiangVien>vec=a.docToanBoDanhMuc();

		cboGiangVien.removeAllItems();
		for(GiangVien dm : vec)
		{
			cboGiangVien.addItem(dm);
		}
	}

	public void hienThicboLH() {
		KetNoiLH b=new KetNoiLH();
		Vector<LopHoc>vec=b.docToanBoLopHoc();
		cboLoHoc.removeAllItems();
		for(LopHoc dm : vec)
		{
			cboLoHoc.addItem(dm);
		}
	}

	public void ShowWindow()
	{
		this.setSize(450, 150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


}
