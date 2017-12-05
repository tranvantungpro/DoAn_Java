package Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Moudle.LopHoc;
import Moudle.TrangThai;
import Moudle.TrangThai;

public class KetNoiTT extends KetNoiSQL 
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	
	public Vector<TrangThai> docToanBoTrangThai()
	{
		Vector<TrangThai> vec=new Vector<TrangThai>();
		try
		{
			String sql="select * from TrangThai";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				TrangThai dm=new TrangThai();
				dm.setMaTT(result.getString(1));
				dm.setTenTT(result.getString(2)); 
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<TrangThai> HienThiTTTheoTen(String tt)
	{
		Vector<TrangThai> vec=new Vector<TrangThai>();
		try
		{
			String sql="select * from TrangThai where TenTT=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tt);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThai dm=new TrangThai();
				dm.setMaTT(result.getString(1));
				dm.setTenTT(result.getString(2));
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
