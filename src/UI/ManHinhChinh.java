package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class ManHinhChinh extends JFrame
{
	JMenuBar mnuBar;
	JMenu mnuQLGT;
	JMenu mnuQLHV;
	JMenu mnuQLLH;
	JMenu mnuQLGD;
	JMenu mnuQLNS;
	JMenu mnuQLGV;
	JMenu mnuQLPH;
	
	JMenu mnuBaoCao;

	
	public ManHinhChinh (String Tieude)
	{
		super(Tieude);
		addContronls();
		addEvents();
	}

	private void addContronls() 
	{
		mnuBar  = new JMenuBar();
		setJMenuBar(mnuBar);
		
		mnuQLGT = new JMenu("Quản Lý Giáo Trình");
		mnuQLHV = new JMenu("Quản Lý Học Viên");
		mnuQLLH = new JMenu("Quản Lý Lớp Học");
		mnuQLGD = new JMenu("Quản Lý Ghi Danh");
		mnuQLNS=new JMenu("Quản lí Nhân Viên");
		mnuQLGV=new JMenu("Quản lí Giáo Viên");
		mnuQLPH=new JMenu("Quản lí Phòng Học");
		mnuBaoCao = new JMenu("Báo Cáo");
		mnuBar.add(mnuQLGT);
		mnuBar.add(mnuQLHV);
		mnuBar.add(mnuQLLH);
		mnuBar.add(mnuQLGD);
		mnuBar.add(mnuQLNS);
		mnuBar.add(mnuQLGV);
		mnuBar.add(mnuQLPH);
	}

	private void addEvents() 
	{
		mnuQLGT.addMouseListener(new MouseListener() {
			
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
				Hide();
				MHQuanLyGiaoTrinh giaotrinh  = new  MHQuanLyGiaoTrinh("Quản Lý Giáo Trình");
				giaotrinh.showWindow();		
				
			}
		});
	
		mnuQLLH.addMouseListener(new MouseListener() {
			
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
				Hide() ;
				MHQuanLyLopHoc lophoc = new MHQuanLyLopHoc("Quản Lý Lớp Học");
				lophoc.showWindow();
				
			}
		});
		
		mnuQLHV.addMouseListener(new MouseListener() {
			
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
				Hide() ;
				MHQuanLyHocVien hocvien = new MHQuanLyHocVien("Quản Lý Học Viên");
				hocvien.showWindow();
				
			}
		});
		mnuQLNS.addMouseListener(new MouseListener() {
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
				Hide() ;
				ManHinhQuanLiNhanVien nhanvien = new ManHinhQuanLiNhanVien("Quản Lý Nhân Viên");
				nhanvien.showWindow();
				
			}
		});
		mnuQLGV.addMouseListener(new MouseListener() {
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
				Hide() ;
				ManHinhQuanLiGiangVien giaovien = new ManHinhQuanLiGiangVien("Quản Lý Giáo Viên");
				giaovien.showWindow();
				
			}
		});
		mnuQLPH.addMouseListener(new MouseListener() {
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
				Hide() ;
				ManHinhPhongHoc phonghoc = new ManHinhPhongHoc("Quản Lý Phòng Học");
				phonghoc.showWindown();
				
			}
		});
		
		
	}
	
	public void Hide() 
	{
		this.dispose(); 
	}
	
	public void showWindows() 
	{
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
