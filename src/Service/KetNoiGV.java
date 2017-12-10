package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


import Moudle.GiangVien;



import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
public class KetNoiGV extends KetNoiSQL
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	public Vector<GiangVien> docToanBoDanhMuc()
	{
		Vector<GiangVien> vec=new Vector<GiangVien>();
		try
		{
			String sql="select * from GiangVien";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				GiangVien dm=new GiangVien();
				dm.setMaGV(result.getString(1));
				dm.setTenGV(result.getString(2)); 
				dm.setDiaChi(result.getString(3));
				dm.setSdt(result.getString(4));
				dm.setNgayVL(result.getDate(5));
				dm.setNgayKT(result.getDate(6));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public ArrayList<GiangVien> LayToanBoGV()
	{
		ArrayList<GiangVien> vec=new ArrayList<GiangVien>();
		try
		{
			String sql="select * from GiangVien ";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien dm=new GiangVien();
				dm.setMaGV(result.getString(1));
				dm.setTenGV(result.getString(2)); 
				dm.setSdt(result.getString(3));
				dm.setDiaChi(result.getString(4));			
				dm.setNgayVL(result.getDate(5));
				dm.setNgayKT(result.getDate(6));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public ArrayList<GiangVien> TimGiangVien(String tenGV)
	{
		ArrayList<GiangVien> dsGv= new ArrayList<GiangVien>();
		try
		{
			String sql= "select * from GiangVien where TenGV like concat('%', ?, '%') collate sql_latin1_general_cp1_ci_as";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenGV);
			result=preStatement.executeQuery();
			while(result.next())
			{
				
				GiangVien ns=new GiangVien();
				ns.setMaGV(result.getString(1));
				ns.setTenGV(result.getString(2)); 
				ns.setSdt(result.getString(3));
				ns.setDiaChi(result.getString(4));
				ns.setNgayVL(result.getDate(5));
				ns.setNgayKT(result.getDate(6));
				dsGv.add(ns);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsGv;
	}
	public int  ThemMoiGiangVien(GiangVien gv)
	{
		try
		{
			String sql= "insert into GiangVien VALUES (?,?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gv.getMaGV());
			preStatement.setString(2, gv.getTenGV());
			preStatement.setString(3, gv.getSdt());
			preStatement.setString(4, gv.getDiaChi());
			preStatement.setDate(5,(Date)  gv.getNgayVL());
			preStatement.setDate(6, (Date) gv.getNgayKT());
			
			return preStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  XoaGiangVien(GiangVien gv)
	{
		try
		{
			String sql= "delete from GiangVien where MaGV=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gv.getMaGV());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  CapNhatGiangVien(GiangVien gv)
	{
		try
		{
			
			String sql= "update GiangVien set TenGV=?, Sdt=?, DiaChi=?, NgayVaoLam=?,NgayKetThuc=? where MaGV=?";
			preStatement = conn.prepareStatement(sql);
			
			preStatement.setString(1, gv.getTenGV());
			preStatement.setString(2, gv.getSdt());
			preStatement.setString(3, gv.getDiaChi());
			preStatement.setDate(4,(Date)  gv.getNgayVL());
			preStatement.setDate(5, (Date) gv.getNgayKT());
			preStatement.setString(6, gv.getMaGV());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	
public GiangVien LayMaGV(String tengv)
	{
		try 
		{
			String sql = "Select * from GiangVien where TenGV=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tengv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien gt = new GiangVien();
				gt.setMaGV(result.getString(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
	
	public String LayTenGV(String magv) {
		try 
		{
			String sql = "Select * from GiangVien where MaGV=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, magv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien gt = new GiangVien();
				gt.setMaGV(result.getString(1));
				gt.setTenGV(result.getString(2)); 
				return gt.getTenGV();
			}
		}
		catch (Exception e) 
		{
			  
		}
		return ""; 
	}

	public GiangVien LayMaGV() {
		// TODO Auto-generated method stub
		try 
		{
			String sql = "Select TOP 1 MaGV from GiangVien ORDER BY MaGV DESC";
			preStatement = conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien ns = new GiangVien();
				ns.setMaGV(result.getString(1));
				return ns;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}

	public int KiemTraTonTai(String TenGV) {
		// TODO Auto-generated method stub
		try 
		{
			String sql= "select * from GiangVien where TenGV =?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, TenGV);
			result=preStatement.executeQuery();
			while(result.next())
			{
				return 1;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
		
	}

	public Vector<GiangVien> hienThicboGVthemMa(String magv) {
		// TODO Auto-generated method stub
		Vector<GiangVien> vec=new Vector<GiangVien>();
		try
		{
			String sql="select * from GiangVien where TenGV=?";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				GiangVien dm=new GiangVien();
				dm.setMaGV(result.getString(1));
				dm.setTenGV(result.getString(2)); 
				dm.setDiaChi(result.getString(3));
				dm.setSdt(result.getString(4));
				dm.setNgayVL(result.getDate(5));
				dm.setNgayKT(result.getDate(6));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
		
	}

	

	}
	
	
