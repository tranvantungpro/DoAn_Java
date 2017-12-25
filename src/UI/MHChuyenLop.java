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
import java.util.Vector;

import javax.swing.ImageIcon;
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
import Service.KetNoiHV;
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
		hienThicboLH();
		hienThicboHV();
	}  

	private void addControls() 
	{
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
	
		
		//pnServer
		JPanel pnTop= new JPanel();
		JPanel pnBottom = new JPanel();
		pnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel pnTieuDe = new JPanel();
		JLabel lblTieude = new JLabel("CHUYỂN LỚP HỌC VIÊN");
		lblTieude.setForeground(Color.BLUE);
		lblTieude.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieude.setIcon(new ImageIcon("Hinh/MHCL.png"));
		pnTieuDe.add(lblTieude);
		con.add(pnTieuDe,BorderLayout.NORTH);
		
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
		btnChuyenLop.setIcon(new ImageIcon("Hinh/CL.png"));
		btnQuayLai.setIcon(new ImageIcon("Hinh/QL.png"));
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
		
		btnChuyenLop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(KiemTraTT(cboHocVien.getSelectedItem().toString()) != 0)
				{
					if(KiemTraSS(cboLopHoc.getSelectedItem().toString())!=0)
						ChuyenLop(cboHocVien.getSelectedItem(), cboLopHoc.getSelectedItem());
				}
				
				
			}
		});
		 
	}
	

	protected int KiemTraSS(String tenlh) {
		KetNoiLH lh = new KetNoiLH();
		LopHoc a = new LopHoc();
		a = lh.KiemTraSSLH(tenlh);
		if(a.getSiSoHT() == a.getSiso())
		{
			JOptionPane.showMessageDialog(null, "Lớp học đã đầy học viên");
			return 0;
		}
		return 1;
	}

	protected int KiemTraTT(String tenhv) {
		KetNoiHV knLH = new KetNoiHV();
		HocVien a = new HocVien();
		a  = knLH.KiemTraTTHV(tenhv);
		if(a.getTrangThai().equals("Bảo lưu"))
		{
			JOptionPane.showMessageDialog(null, "Học viên đang bảo lưu, không thể chuyển lớp");
			return 0;
		}
		if(a.getTrangThai().equals("Mới"))
		{
			JOptionPane.showMessageDialog(null, "Học viên mới, không thể chuyển lớp");
			return 0;
		}
		
		return 1;
	}

	protected void ChuyenLop(Object selectedItem, Object selectedItem2) 
	{
		 KetNoiHV knLH = new KetNoiHV();
		 if(knLH.ChuyenLop(cboHocVien.getSelectedItem().toString(),LayMaLopHoc(cboLopHoc.getSelectedItem().toString()))> 0)
			{
				JOptionPane.showMessageDialog(null, "Chuyển lớp thành công");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Chuyển lớp thất bại");
			}
		
	}

	public void Quaylai() 
	{
		this.dispose(); 
	}

	public String LayMaLopHoc(String tenlh)
	{
		KetNoiLH knGV = new KetNoiLH();
		LopHoc a = new LopHoc();
		a = knGV.LayMalophoc(tenlh);
		 
		return a.getMaLH();
	}
	
	public void hienThicboLH() {
		KetNoiLH a=new KetNoiLH();
		Vector<LopHoc>vec=a.docToanBoLopHoc();
		
		cboLopHoc.removeAllItems();
		for(LopHoc dm : vec)
		{
			cboLopHoc.addItem(dm);
		}
	}
	
	public void hienThicboHV() {
		KetNoiHV b=new KetNoiHV();
		Vector<HocVien>vec=b.docToanBoHocVien();
		//cboLoHoc.removeAllItems();
		for(HocVien dm : vec)
		{
			cboHocVien.addItem(dm);
		}
	}
	
	public void ShowWindow()
	{
		this.setSize(450, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
