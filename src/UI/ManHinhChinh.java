package UI;

import java.awt.BorderLayout;
import java.awt.Color;import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import GUI.MaHinhChuongTrinhHoc;
import GUI.ManHinhGhiDanh;
import GUI.ManHinhPhieuXuat;
import GUI.ManHinhThongKeBaoCao;
import javafx.scene.shape.Box;

public class ManHinhChinh extends JFrame
{
	JMenuBar mnuBar;
	JMenu mnuQLGT, mnuQLHV,mnuQLLH, mnuQLGD,mnuQLNS, mnuQLGV, mnuQLPH, mnuBaoCao,mnuPhieuXuat;
	JMenu mnuCTH;
	JTabbedPane tab;
	JButton btnQLGT, btnQLHV,btnQLLH, btnQLGD,btnQLNS, btnQLGV, btnQLPH, btnBaoCao,btnPhieuXuat;
	JButton btnChamCong, btnCTH, btnTinhLuong, btnXemLich;
	public ManHinhChinh (String Tieude) 
	{
		super(Tieude);
		addContronls();
		addEvents();
	}

	private void addContronls() 
	{

		Container con=getContentPane();
		tab=new JTabbedPane();
		con.add(tab);

		JPanel pnTab1=new JPanel();
		JPanel pnTab2=new JPanel();
		JPanel pnTab3=new JPanel(); 


		ImageIcon a = new ImageIcon("Hinh/fuction1.png");
		tab.addTab("Chức Năng", a, pnTab1, "Does nothing");
		ImageIcon help = new ImageIcon("Hinh/help2.png");
		tab.addTab("Trợ Giúp",help, pnTab2, "Does nothing"); 
		ImageIcon help2 = new ImageIcon("Hinh/info.png");
		tab.addTab("Thông Tin",help2, pnTab3, "Does nothing"); 

		//pnTab1 chức năng
		pnTab1.setLayout(new BorderLayout());
		JPanel pnChucNang1 = new JPanel();
		pnChucNang1.setLayout(new BorderLayout());
		JPanel pnChucNang2 = new JPanel();
		pnChucNang1.setPreferredSize(new Dimension(280, 700));
		pnChucNang1.setLayout(new BoxLayout(pnChucNang1, BoxLayout.Y_AXIS));
		pnChucNang2.setPreferredSize(new Dimension(1200, 800));
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnChucNang1, pnChucNang2);

		JPanel pnGT = new JPanel();
		pnGT.setLayout(new FlowLayout());

		JPanel pnLH = new JPanel();
		pnLH.setLayout(new FlowLayout());
		btnQLGT = new JButton("Quản Lý Giáo Trình");
		btnQLGT.setIcon(new ImageIcon("Hinh/book.png"));
		btnQLGT.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnQLGT);

		JPanel pnHV = new JPanel();
		pnLH.setLayout(new FlowLayout());
		btnQLHV = new JButton("Quản Lý Học Viên   ");
		btnQLHV.setIcon(new ImageIcon("Hinh/student.png"));
		btnQLHV.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnQLHV );


		btnQLLH = new JButton("Quản Lý Lớp Học   ");
		btnQLLH.setIcon(new ImageIcon("Hinh/class.png"));
		btnQLLH.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnQLLH );

		btnCTH = new JButton("  Chương Trình Học");
		btnCTH.setIcon(new ImageIcon("Hinh/CTh.png"));
		btnCTH.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnCTH );

		btnQLNS = new JButton("Quản Lý Nhân Sự   ");
		btnQLNS.setIcon(new ImageIcon("Hinh/nhansu.png"));
		btnQLNS.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnQLNS);


		btnQLGV = new JButton("Quản Lý Giảng Viên");
		btnQLGV.setIcon(new ImageIcon("Hinh/teacher.png"));
		btnQLGV.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnQLGV); 

		btnQLPH = new JButton("Quản Lý Phòng Học");
		btnQLPH.setIcon(new ImageIcon("Hinh/room.png"));
		btnQLPH.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnQLPH);

		btnQLGD = new JButton("Quản Lý Ghi Danh  ");
		btnQLGD.setIcon(new ImageIcon("Hinh/but.png"));
		btnQLGD.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnQLGD);

		btnChamCong = new JButton("Quản Lý Chấm Công");
		btnChamCong.setIcon(new ImageIcon("Hinh/chamcong.png"));
		btnChamCong.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnChamCong);

		btnTinhLuong = new JButton("Quản Lý Tính Lương");
		btnTinhLuong.setIcon(new ImageIcon("Hinh/tinhluong.png"));
		btnTinhLuong.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnTinhLuong);

		btnXemLich = new JButton("     Xem Lịch Dạy     ");
		btnXemLich.setIcon(new ImageIcon("Hinh/view.png"));
		btnXemLich.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnXemLich);


		btnBaoCao = new JButton("Thống Kê Báo Cáo ");
		btnBaoCao.setIcon(new ImageIcon("Hinh/baocao.png"));
		btnBaoCao.setFont(new Font("Arial", Font.PLAIN, 20));
		pnLH.add(btnBaoCao);

		btnPhieuXuat = new JButton("Quản Lý Phiếu Xuất");
		btnPhieuXuat.setIcon(new ImageIcon("Hinh/phieuxuat.png"));
		btnPhieuXuat.setFont(new Font("Arial", Font.PLAIN, 20)); 
		pnLH.add(btnPhieuXuat);

		BufferedImage image1 = null;
		try {
			image1 = ImageIO.read(new File("Hinh/center.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnQLGT.setPreferredSize(btnChamCong.getPreferredSize());
		btnBaoCao.setPreferredSize(btnChamCong.getPreferredSize());
		btnTinhLuong.setPreferredSize(btnChamCong.getPreferredSize());
		btnPhieuXuat.setPreferredSize(btnChamCong.getPreferredSize());
		btnQLGD.setPreferredSize(btnChamCong.getPreferredSize());
		btnQLGV.setPreferredSize(btnChamCong.getPreferredSize());
		btnQLHV.setPreferredSize(btnChamCong.getPreferredSize());
		btnXemLich.setPreferredSize(btnChamCong.getPreferredSize());
		btnQLLH.setPreferredSize(btnChamCong.getPreferredSize());
		btnQLNS.setPreferredSize(btnChamCong.getPreferredSize());
		btnQLPH.setPreferredSize(btnChamCong.getPreferredSize());
		btnCTH.setPreferredSize(btnChamCong.getPreferredSize());


		JPanel pnHAnh = new JPanel();
		JLabel label1 = new JLabel(new ImageIcon(image1));
		pnHAnh.add(label1);
		//pnLH.add(pnHAnh);
//		JScrollPane sc11=new JScrollPane(pnLH,
//				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
//				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 
		pnChucNang1.add(pnLH); 
	
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("./trungtamth.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label = new JLabel(new ImageIcon(image));
		pnChucNang2.add(label); 
		
		 
		 
		pnTab1.add(sp, BorderLayout.WEST);


		//pnTb2 help
		BufferedImage image5 = null;
		try {
			image5 = ImageIO.read(new File("Hinh/lienheuit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label5 = new JLabel(new ImageIcon(image5));
		
		JScrollPane sc9=new JScrollPane(label5,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTab2.add(sc9); 


		//pnTb3 thông tin
		pnTab3.setBackground(Color.GRAY);
		pnTab3.setLayout(new BoxLayout(pnTab3, BoxLayout.Y_AXIS));
		
		//pna  
		BufferedImage image4 = null;
		try {
			image4 = ImageIO.read(new File("Hinh/thongtinuit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label4 = new JLabel(new ImageIcon(image4));
		
		JScrollPane sc1=new JScrollPane(label4,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		pnTab3.add(sc1); 

	}

	private void addEvents() 
	{
		btnQLGT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				MHQuanLyGiaoTrinh gt = new MHQuanLyGiaoTrinh("Quản Lý Giáo Trình");
				gt.showWindow();

			}
		});

		btnQLHV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				MHQuanLyHocVien hv = new MHQuanLyHocVien("Quản Lý Học Viên");
				hv.showWindow();

			}
		});

		btnQLLH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				MHQuanLyLopHoc hv = new MHQuanLyLopHoc("Quản Lý Lớp Học");
				hv.showWindow();

			}
		});

		btnQLNS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhQuanLiNhanVien ns = new ManHinhQuanLiNhanVien("Quản Lý Nhân Viên");
				ns.showWindow();
			}
		});

		btnQLGV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhQuanLiGiangVien gv = new ManHinhQuanLiGiangVien("Quản Lý Giảng Viên");
				gv.showWindow();

			}
		});

		btnQLPH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhPhongHoc ph = new ManHinhPhongHoc("Quản Lý Phòng Học");
				ph.showWindown();

			}
		});

		btnBaoCao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhThongKeBaoCao ui = new ManHinhThongKeBaoCao();
				ui.setVisible(true);
			}
		});

		btnPhieuXuat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhPhieuXuat ui = new ManHinhPhieuXuat();
				ui.setVisible(true);
			}
		});

		//	
		btnChamCong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhQuanLiChamCongGV cc = new ManHinhQuanLiChamCongGV("Chấm Công");
				cc.showWindow();

			}
		});

		btnQLGD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhGhiDanh  ui = new ManHinhGhiDanh();
				ui.setVisible(true);

			}
		});

		btnCTH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				MaHinhChuongTrinhHoc ui = new MaHinhChuongTrinhHoc();
				ui.setVisible(true);

			}
		});

		btnTinhLuong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhTinhLuong tl = new ManHinhTinhLuong("Tính Lương");
				tl.showWindown();
			}
		});

		btnXemLich.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hide();
				ManHinhXemLichGD ui = new ManHinhXemLichGD("Xem Lịch Dạy");
				ui.showWindown();

			}
		});
	}

	public void Hide() 
	{
		this.dispose(); 
	}

	public void showWindows() 
	{
		this.setSize(1280, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
