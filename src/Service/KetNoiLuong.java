package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import Moudle.Luong;

public class KetNoiLuong extends KetNoiSQLQuyen {
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	public Vector<Luong> docToanBoDanhMuc()
	{
		Vector<Luong> vec=new Vector<Luong>();
		try
		{
			String sql="select * from Luong";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				Luong dm=new Luong();
				dm.setMaHSL(result.getString(1));
				dm.setMaGV(result.getString(2));
				dm.setTenGV(result.getString(3)); 
				dm.setHsoL(result.getInt(4));
				dm.setLuongCb(result.getInt(5));
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public ArrayList<Luong> LayToanBoLuong()
	{
		ArrayList<Luong> dsGD= new ArrayList<Luong>();
		try 
		{
			String sql ="select * from Luong ORDER BY MaHSL ASC";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				Luong gd = new Luong();
				gd.setMaHSL(result.getString(1));
				gd.setMaGV(result.getString(2));
				gd.setTenGV(result.getString(3)); 
				gd.setHsoL(result.getInt(4));
				gd.setLuongCb(result.getInt(5));
				dsGD.add(gd);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dsGD;
	}
	
	public ArrayList<Luong> TimLuong(String luong)
	{
		ArrayList<Luong> dsGD= new ArrayList<Luong>();
		try
		{
			String sql= "select * from Luong where MaNV like concat('%', ?, '%') collate sql_latin1_general_cp1_ci_as";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, luong);
			result=preStatement.executeQuery();
			while(result.next())
			{
				
				Luong gd=new Luong();
				gd.setMaHSL(result.getString(1));
				gd.setMaGV(result.getString(2));
				gd.setTenGV(result.getString(3)); 
				gd.setHsoL(result.getInt(4));
				gd.setLuongCb(result.getInt(5));
				dsGD.add(gd);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsGD;
	}
	public int  ThemMoiLuong(Luong gd)
	{
		try
		{
			String sql= "insert into Luong VALUES (?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gd.getMaHSL());
			preStatement.setString(2, gd.getMaGV());
			preStatement.setString(3, gd.getTenGV());
			
			preStatement.setLong(4, gd.getHsoL());
			preStatement.setLong(5, gd.getLuongCb());

			
			return preStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  XoaLuong(Luong gd)
	{
		try
		{
			String sql= "delete from Luong where MaHSL=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gd.getMaHSL());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  CapNhatLuong(Luong gd)
	{
		try
		{
			String sql= "update Luong set MaGV=?, HeSoLuong=?, LuongCB=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gd.getMaGV());
			preStatement.setString(2, gd.getTenGV());
			preStatement.setLong(3, gd.getHsoL());
			preStatement.setLong(4, gd.getLuongCb());

			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public Luong LayMaLuong() {
		// TODO Auto-generated method stub
		try 
		{
			String sql = "Select TOP 1 MaHSL from PhanCongLichGiangDay ORDER BY MPC DESC";
			preStatement = conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				Luong gd = new Luong();
				gd.setMaHSL(result.getString(1));
				return gd;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
		
	}
	public int KiemTraTonTai(String MaGV) {
		// TODO Auto-generated method stub
		try 
		{
			String sql= "select * from Luong where MaGV =?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, MaGV);
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
}
