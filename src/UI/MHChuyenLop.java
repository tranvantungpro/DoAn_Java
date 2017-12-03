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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Moudle.GiangVien;
import Moudle.HocVien;
import Moudle.LopHoc;
import Service.KetNoiGV;
import Service.KetNoiLH;

public class MHChuyenLop extends JFrame
{
	JComboBox<HocVien> cboHocVien;
	JComboBox<LopHoc> cboLopHoc;
	JButton btnChuyenLop,btnQuayLai;
	
	public  MHChuyenLop(String tieude)
	{
		super(tieude);
		addControls();
		addEvents();
//		hienThicboLH();
//		hienThicboHV();
	}  

	private void addControls() 
	{
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
	
		//pnServer
		JPanel pnTop= new JPanel();
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		JPanel pnhv = new JPanel();
		JLabel lblHV = new JLabel("Học viên:");
		lblHV.setFont(new Font("Arial", Font.PLAIN, 15));
		cboHocVien = new JComboBox<HocVien>();
		cboHocVien.setPreferredSize(new Dimension(332, 20));
		pnhv.add(lblHV);
		pnhv.add(cboHocVien);
		pnTop.add(pnhv);
		con.add(pnTop);
		
		
		JPanel pnHocVien = new JPanel();
		JLabel lblLop = new JLabel("Lớp học:");
		lblLop.setFont(new Font("Arial", Font.PLAIN, 15));
		cboLopHoc = new JComboBox<LopHoc>();
		cboLopHoc.setPreferredSize(new Dimension(332, 20));
		pnHocVien.add(lblLop);
		pnHocVien.add(cboLopHoc);
		pnTop.add(pnHocVien);
		con.add(pnTop );
		
		
		
		JPanel pnButton = new JPanel();
		btnChuyenLop = new JButton("Chuyển lớp");
		btnQuayLai = new JButton("Quay lại");
		pnBottom.add(btnChuyenLop);
		pnBottom.add(btnQuayLai);
		con.add(pnBottom, BorderLayout.SOUTH);
		lblLop.setPreferredSize(lblHV.getPreferredSize());
		
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
		
		 
	}
	

//	protected void XepLop(Object selectedItem, Object selectedItem2) 
//	{
//		 KetNoiLH knLH = new KetNoiLH();
//		 if(knLH.XepLop(LayMaGV(cboGiangVien.getSelectedItem().toString()), cboLoHoc.getSelectedItem().toString())> 0)
//			{
//				JOptionPane.showMessageDialog(null, "Xếp lớp thành công");
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(null, "Xếp lớp thất bại");
//			}
//		
//	}
//
	public void Quaylai() 
	{
		this.dispose(); 
	}
//
//	public String LayMaGV(String tengv)
//	{
//		KetNoiGV knGV = new KetNoiGV();
//		GiangVien a = new GiangVien();
//		a = knGV.LayMaGV(tengv);
//		 
//		return a.getMaGV();
//	}
//	
//	public void hienThicboGV() {
//		KetNoiGV a=new KetNoiGV();
//		Vector<GiangVien>vec=a.docToanBoDanhMuc();
//		
//		cboGiangVien.removeAllItems();
//		for(GiangVien dm : vec)
//		{
//			cboGiangVien.addItem(dm);
//		}
//	}
//	
//	public void hienThicboLH() {
//		KetNoiLH b=new KetNoiLH();
//		Vector<LopHoc>vec=b.docToanBoLopHoc();
//		//cboLoHoc.removeAllItems();
//		for(LopHoc dm : vec)
//		{
//			cboLoHoc.addItem(dm);
//		}
//	}
//	
	public void ShowWindow()
	{
		this.setSize(450, 150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
