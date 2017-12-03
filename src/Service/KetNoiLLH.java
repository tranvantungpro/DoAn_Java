package Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.GiangVien;
import Moudle.LoaiLopHoc;
import Moudle.LopHoc;
public class KetNoiLLH extends KetNoiSQL 
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	
	public Vector<LoaiLopHoc> docToanBoDanhMuc()
	{
		Vector<LoaiLopHoc> vec=new Vector<LoaiLopHoc>();
		try
		{
			String sql="select * from LoaiLH";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				LoaiLopHoc dm=new LoaiLopHoc();
				dm.setMaLoaiLH(result.getString(1));
				dm.setTenLoaiLH(result.getString(2)); 
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<LoaiLopHoc> hienThicboLLHthemMa(String tenloai)
	{
		Vector<LoaiLopHoc> vec=new Vector<LoaiLopHoc>();
		try
		{
			String sql="select * from LoaiLH where TenLoaiLH=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenloai);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LoaiLopHoc dm=new LoaiLopHoc();
				dm.setMaLoaiLH(result.getString(1));
				dm.setTenLoaiLH(result.getString(2)); 
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public LoaiLopHoc LayMaLLH(String tenll)
	{
		try 
		{
			String sql = "Select * from LoaiLH where TenLLH=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenll);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LoaiLopHoc gt = new LoaiLopHoc();
				gt.setTenLoaiLH(result.getString(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
}
