package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Moudle.ThoiGian;
import Moudle.GiangVien;
import Moudle.ThoiGian;
import Moudle.ThoiGian;

public class KetNoiTG extends KetNoiSQL 
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	public Vector<ThoiGian>HienThiTGCbo()
	{
		Vector<ThoiGian> vec=new Vector<ThoiGian>();
		try
		{
			String sql="select * from ThoiGian";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				ThoiGian dm=new ThoiGian();
				dm.setMaTG(result.getInt(1));
				dm.setTenTG(result.getString(2)); 
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<ThoiGian> HienThiLenCboThemMa(int matg)
	{
		Vector<ThoiGian> vec=new Vector<ThoiGian>();
		try
		{
			String sql="select * from ThoiGIan where MaTG=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, matg);
			result=preStatement.executeQuery();
			while(result.next())
			{
				ThoiGian dm=new ThoiGian();
				dm.setMaTG(result.getInt(1));
				dm.setTenTG(result.getString(2));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public String LayTenTG(int i) {
		try 
		{
			String sql = "Select * from ThoiGian where MaTG=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setInt(1, i);
			result=preStatement.executeQuery();
			while(result.next())
			{
				ThoiGian gt = new ThoiGian();
				gt.setMaTG(result.getInt(1));
				gt.setTenTG(result.getString(2)); 
				return gt.getTenTG();
			}
		}
		catch (Exception e) 
		{
			  
		}
		return ""; 
	}
	
	public ThoiGian LayMaTGG(String ph)
	{
		try 
		{
			String sql = "Select * from ThoiGian where TenTG=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, ph);
			result=preStatement.executeQuery();
			while(result.next())
			{
				ThoiGian gt = new ThoiGian();
				gt.setMaTG(result.getInt(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
}
