package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Connection;

import javax.security.auth.kerberos.KerberosTicket;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sun.glass.events.KeyEvent;

import IO.FileManager;
import Moudle.DangNhap;
import Service.KetNoiDangNhap;
import Service.KetNoiSQL;
import sun.net.www.content.image.jpeg; 

public class ManHinhCSDL extends JFrame
{
	JTextField  txtDatabase,txtUserName,txtServer;
	JButton btnLogin,btnExit;
	JCheckBox chkSave;
	JPasswordField txtPass;
	Connection a = null;
	public ManHinhCSDL (String Tieude)
	{
		super(Tieude);
		addControls();
		addEvents();
		HienThiLaiThongTinDN();
	}

	

	private void addControls() 
	{
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnNorth=new JPanel();
		con.add(pnNorth,BorderLayout.NORTH);
		JPanel pnCenter=new JPanel();
		con.add(pnCenter,BorderLayout.CENTER);
		JPanel pnSouth=new JPanel();
		con.add(pnSouth,BorderLayout.SOUTH);
		
		JLabel lblTitle=new JLabel("Đăng nhập hệ thống");
		lblTitle.setFont(new Font("tahoma",Font.BOLD,15));
		lblTitle.setForeground(Color.BLUE);
		pnNorth.add(lblTitle);
		
		pnCenter.setLayout(new BorderLayout());
		JPanel pnImage=new JPanel();
		JLabel lblIcon=new JLabel(new ImageIcon("Hinh/sql2.png"));
		pnImage.add(lblIcon);
		pnCenter.add(pnImage,BorderLayout.WEST);
		
		JPanel pnUser=new JPanel();
		pnUser.setLayout(new BoxLayout(pnUser, BoxLayout.Y_AXIS));
		pnCenter.add(pnUser,BorderLayout.CENTER);
		
		JPanel pnUserName=new JPanel();
		pnUserName.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblUserName=new JLabel("Tài khoản:");
		txtUserName=new JTextField(20);
		pnUserName.add(lblUserName);
		pnUserName.add(txtUserName);
		pnUser.add(pnUserName);
		
		JPanel pnServer=new JPanel();
		pnServer.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblServer=new JLabel("Server Name:");
		txtServer=new JTextField(20);
		pnServer.add(lblServer);
		pnServer.add(txtServer);
		pnUser.add(pnServer);
		
		JPanel pnPassword=new JPanel();
		pnPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblPassword=new JLabel("Mật khẩu:");
		txtPass =new JPasswordField(20);
		pnPassword.add(lblPassword);
		pnPassword.add(txtPass);
		pnUser.add(pnPassword);
		
		
		
		JPanel pnSave=new JPanel();
		chkSave=new JCheckBox("Lưu thông tin đăng nhập");
		pnSave.add(chkSave);
		pnUser.add(pnSave);
		
		
		btnLogin=new JButton("Đăng nhập");
		btnExit=new JButton("Thoát");
		
		pnSouth.add(btnLogin);
		pnSouth.add(btnExit);
		
		TitledBorder borderUser=
				new TitledBorder(BorderFactory.createLineBorder(Color.BLUE),
						"Thông tin đăng nhập");
		pnUser.setBorder(borderUser);
		pnImage.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		pnCenter.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		btnLogin.setIcon(new ImageIcon("Hinh/action.png"));
		btnExit.setIcon(new ImageIcon("Hinh/exit.png"));
		lblUserName.setPreferredSize(lblServer.getPreferredSize());
		lblPassword.setPreferredSize(lblServer.getPreferredSize());
	}
	
	private void addEvents() 
	{
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String a= txtServer.getText();

				if(a.length() != 0)
				{ 
					int b =XuLyTruyXuatSQL(txtServer.getText());
					if(b == 1 )
					{ 
						xuLyDangNhap();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
					}
					
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Vui lòng nhập Server Name","Thông báo",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		 
	}
	
	private void HienThiLaiThongTinDN() {
		File f=new File("DangNhap.data");
		if(f.exists())
		{
			Object data=FileManager.readData("DangNhap.data");
			if(data!=null)
			{
				DangNhap dn=(DangNhap) data;
				txtUserName.setText(dn.getUserName());
				txtServer.setText(dn.getServer());
				txtPass.setText(dn.getPassword());
				chkSave.setSelected(true);
			}
		}
		
	}
	protected void xuLyDangNhap() {
		KetNoiDangNhap dnService=new KetNoiDangNhap();
		DangNhap  dn=dnService.login(txtUserName.getText(), txtPass.getText(), txtServer.getText());
		if(dn!=null)
		{
			if(chkSave.isSelected())
			{
				FileManager.saveData(dn, "DangNhap.data");
			}
			else
			{
				FileManager.saveData(null, "DangNhap.data");
			}
			dispose();
			ManHinhChinh ui = new ManHinhChinh("Quản Lý Trung Tâm Tin Học"); 
			ui.showWindows();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
		}
	}
	protected int XuLyTruyXuatSQL(String server ) 
	{
		a = KetNoiSQL.getConnect(server);
		if(a==null)
		{
			//JOptionPane.showMessageDialog(null, "Truy Xuất Thất Bại","Thông báo",JOptionPane.WARNING_MESSAGE);
			return 0;
		}
		else
		{
			 //JOptionPane.showMessageDialog(null, "Truy Xuất Thành Công");
//			 this.dispose();
//			 ManHinhChinh manhinh  = new ManHinhChinh("Phần mềm quản lý trung tâm tin học");
//			 manhinh.showWindows();
			 
			 return 1;
		}
			
		
	}


	public void ShowWindow()
	{
		this.setSize(500, 280);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
