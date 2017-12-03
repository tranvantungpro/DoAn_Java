package Service;

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

import Moudle.CTH;
import Moudle.GiangVien;
import Moudle.LoaiLopHoc;;
public class KetNoiCTH extends KetNoiSQL
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	
	public Vector<CTH> docToanBoDanhMuc()
	{
		Vector<CTH> vec=new Vector<CTH>();
		try
		{
			String sql="select * from ChuongTrinhHoc";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				CTH dm=new CTH();
				dm.setMaCTH(result.getString(1));
				dm.setTenCTH(result.getString(2)); 
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<CTH> hienThicbocththemMa(String magv)
	{
		Vector<CTH> vec=new Vector<CTH>();
		try
		{
			String sql="select * from ChuongTrinhHoc where MaCTH=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, magv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				CTH dm=new CTH();
				dm.setMaCTH(result.getString(1));
				dm.setTenCTH(result.getString(2));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public CTH LayMaCTH(String tencth)
	{
		try 
		{
			String sql = "Select * from ChuongTrinhHoc where TenCTH=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tencth);
			result=preStatement.executeQuery();
			while(result.next())
			{
				CTH gt = new CTH();
				gt.setMaCTH(result.getString(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
}
