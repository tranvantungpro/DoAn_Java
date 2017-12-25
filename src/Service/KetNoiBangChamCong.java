package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import Moudle.BangChamCong;
import Moudle.GiangVien;

public class KetNoiBangChamCong extends KetNoiSQL {
	PreparedStatement preStatement=null;
	ResultSet result=null;
//	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
//	Connection conn = a.getConnect();
	public Vector<BangChamCong> docToanBoDanhMuc()
	{
		Vector<BangChamCong> vec=new Vector<BangChamCong>();
		try
		{
			String sql="select * from BangChamCong";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				BangChamCong dm=new BangChamCong();
				dm.setMaCC(result.getString(1));
				dm.setMaGV(result.getString(2));			
				dm.setNgayCC(result.getDate(3));
				dm.setCa(result.getInt(4)); 
				dm.setLyDo(result.getString(5));
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public ArrayList<BangChamCong> LayToanBoLuong()
	{
		ArrayList<BangChamCong> dsGD= new ArrayList<BangChamCong>();
		try 
		{
			String sql ="select * from BangChamCong ORDER BY MaCC ASC";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				BangChamCong gd = new BangChamCong();
				gd.setMaCC(result.getString(1));
				gd.setMaGV(result.getString(2));
				gd.setNgayCC(result.getDate(3));
				gd.setCa(result.getInt(4)); 
				gd.setLyDo(result.getString(5));
				dsGD.add(gd);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dsGD;
	}
	
	public ArrayList<BangChamCong> TimLuong(String luong)
	{
		ArrayList<BangChamCong> dsGD= new ArrayList<BangChamCong>();
		try
		{
			String sql= "select * from BangChamCong where MaCC like concat('%', ?, '%') collate sql_latin1_general_cp1_ci_as";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, luong);
			result=preStatement.executeQuery();
			while(result.next())
			{
				
				BangChamCong gd=new BangChamCong();
				gd.setMaCC(result.getString(1));
				gd.setMaGV(result.getString(2));
				gd.setNgayCC(result.getDate(3));
				gd.setCa(result.getInt(4)); 
				gd.setLyDo(result.getString(5));
				dsGD.add(gd);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsGD;
	}
	public int  ThemMoiLuong(BangChamCong gd)
	{
		try
		{
			String sql= "insert into BangChamCong VALUES (?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gd.getMaCC());
			preStatement.setString(2, gd.getMaGV());
			preStatement.setDate(3,(Date)  gd.getNgayCC());
			preStatement.setLong(4, gd.getCa());
			preStatement.setString(5, gd.getLyDo());

			
			return preStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  XoaLuong(BangChamCong gd)
	{
		try
		{
			String sql= "delete from BangChamCong where MaCC=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gd.getMaCC());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  CapNhatLuong(BangChamCong gd)
	{
		try
		{
			String sql= "update BangChamCong set MaGV=?, NgayCC=?, Ca=?,LyDo=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gd.getMaCC());
			preStatement.setString(2, gd.getMaGV());
			preStatement.setDate(3,(Date)  gd.getNgayCC());
			preStatement.setLong(4, gd.getCa());
			preStatement.setString(5, gd.getLyDo());;

			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public BangChamCong LayMaCC() {
		// TODO Auto-generated method stub
		try 
		{
			String sql = "Select TOP 1 MaCC from BangChamCong ORDER BY MaCC DESC";
			preStatement = conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				BangChamCong gd = new BangChamCong();
				gd.setMaCC(result.getString(1));
				return gd;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
		
	}
	public int KiemTraTonTai(String MaCC) {
		// TODO Auto-generated method stub
		try 
		{
			String sql= "select * from BangChamCong where MaCC =?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1,MaCC);
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
	public Vector<BangChamCong> hienThicboCathemMa(String macc) {
		// TODO Auto-generated method stub
		Vector<BangChamCong> vec=new Vector<BangChamCong>();
		try
		{
			String sql="select * from BangChamCong ";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				BangChamCong dm=new BangChamCong();
				dm.setMaCC(result.getString(1));
				dm.setMaGV(result.getString(2));			
				dm.setNgayCC(result.getDate(3));
				dm.setCa(result.getInt(4)); 
				dm.setLyDo(result.getString(5));
				vec.add(dm);
				
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
		
	}
	public Vector<BangChamCong> hienThicboLyDothemMa(String macc) {
		// TODO Auto-generated method stub
		Vector<BangChamCong> vec=new Vector<BangChamCong>();
		try
		{
			String sql="select * from BangChamCong";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				BangChamCong dm=new BangChamCong();
				dm.setMaCC(result.getString(1));
				dm.setMaGV(result.getString(2));			
				dm.setNgayCC(result.getDate(3));
				dm.setCa(result.getInt(4)); 
				dm.setLyDo(result.getString(5));
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
