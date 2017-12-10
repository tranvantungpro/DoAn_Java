package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import Moudle.PhongBan;

public class KetNoiPhongBan extends KetNoiSQLQuyen {
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	public Vector<PhongBan> docToanBoDanhMuc()
	{
		Vector<PhongBan> vec=new Vector<PhongBan>();
		try
		{
			String sql="select * from PhongBan";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				PhongBan dm=new PhongBan();
				dm.setMaPB(result.getString(1));
				dm.setTenPB(result.getString(2)); 
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<PhongBan> hienThicbocththemMa(String mapb)
	{
		Vector<PhongBan> vec=new Vector<PhongBan>();
		try
		{
			String sql="select * from PhongBan where MaPB=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, mapb);
			result=preStatement.executeQuery();
			while(result.next())
			{
				PhongBan dm=new PhongBan();
				dm.setMaPB(result.getString(1));
				dm.setTenPB(result.getString(2));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public PhongBan LayMaPB(String tenpb)
	{
		try 
		{
			String sql = "Select * from PhongBan where TenPB=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenpb);
			result=preStatement.executeQuery();
			while(result.next())
			{
				PhongBan pb = new PhongBan();
				pb.setMaPB(result.getString(1));
				return pb;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}

	public String LayTenPB(String mapb) {
		try 
		{
			String sql = "Select * from PhongBan where MaPB=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, mapb);
			result=preStatement.executeQuery();
			while(result.next())
			{
				PhongBan pb = new PhongBan();
				pb.setMaPB(result.getString(1));
				pb.setTenPB(result.getString(2)); 
				return pb.getTenPB();
			}
		}
		catch (Exception e) 
		{
			  
		}
		return ""; 
	}

	

}
