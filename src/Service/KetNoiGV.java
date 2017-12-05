package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Moudle.CTH;
import Moudle.GiangVien;
import Moudle.LopHoc;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

 
import java.sql.Connection;
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
				dm.setTienLuong(result.getLong(4));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<GiangVien> hienThicboGVthemMa(String magv)
	{
		Vector<GiangVien> vec=new Vector<GiangVien>();
		try
		{
			String sql="select * from GiangVien where MaGV=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, magv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien dm=new GiangVien();
				dm.setMaGV(result.getString(1));
				dm.setTenGV(result.getString(2)); 
				dm.setDiaChi(result.getString(3));
				dm.setTienLuong(result.getLong(4));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
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
	
}
