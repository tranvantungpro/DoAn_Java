package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.GiangVien;
import Moudle.LopHoc;
import Moudle.ThoiGian;
import Moudle.TrangThaiBangChamCong;

public class KetNoiTTBangChamCong extends KetNoiSQLQuyen {
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	public Vector<TrangThaiBangChamCong> LayToanBoTrangThaiBangChamCong()
	{
		Vector<TrangThaiBangChamCong> dsGT= new Vector<TrangThaiBangChamCong>();
		try 
		{
			String sql ="select * from TrangThaiBangChamCong ORDER BY MaTTCC ASC";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiBangChamCong lh = new TrangThaiBangChamCong();
				lh.setMaTTCC(result.getString(1));
				lh.setLyDo(result.getString(2));
				lh.setHeSo(result.getFloat(3));
				dsGT.add(lh);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dsGT;
	}
	public Vector<TrangThaiBangChamCong>HienThiLyDoLenCbo()
	{
		Vector<TrangThaiBangChamCong> vec=new Vector<TrangThaiBangChamCong>();
		try
		{
			String sql="select * from TrangThaiBangChamCong";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				TrangThaiBangChamCong dm=new TrangThaiBangChamCong();
				dm.setMaTTCC(result.getString(1));
				dm.setLyDo(result.getString(2));
				dm.setHeSo(result.getFloat(3));
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<TrangThaiBangChamCong>HienThiHeSoLyDo()
	{
		Vector<TrangThaiBangChamCong> vec=new Vector<TrangThaiBangChamCong>();
		try
		{
			String sql="select * from TrangThaiBangChamCong";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				TrangThaiBangChamCong dm=new TrangThaiBangChamCong();
				dm.setMaTTCC(result.getString(1));
				dm.setLyDo(result.getString(2));
				dm.setHeSo(result.getFloat(3));
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<TrangThaiBangChamCong> HienThiLenCboLyDo(String matg)
	{
		Vector<TrangThaiBangChamCong> vec=new Vector<TrangThaiBangChamCong>();
		try
		{
			String sql="select * from TrangThaiBangChamCong where MaTTCC=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, matg);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiBangChamCong dm=new TrangThaiBangChamCong();
				dm.setMaTTCC(result.getString(1));
				dm.setLyDo(result.getString(2));
				dm.setHeSo(result.getFloat(3));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<TrangThaiBangChamCong> HienThiLenHeSoLyDo(String matg)
	{
		Vector<TrangThaiBangChamCong> vec=new Vector<TrangThaiBangChamCong>();
		try
		{
			String sql="select * from TrangThaiBangChamCong where LyDoNghi=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, matg);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiBangChamCong dm=new TrangThaiBangChamCong();
				dm.setMaTTCC(result.getString(1));
				dm.setLyDo(result.getString(2));
				dm.setHeSo(result.getFloat(3));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public String LayTenLyDo(String lydo) {
		try 
		{
			String sql = "select * from TrangThaiBangChamCong where MaTTCC=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, lydo);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiBangChamCong gt = new TrangThaiBangChamCong();
				gt.setMaTTCC(result.getString(1));
				gt.setLyDo(result.getString(2)); 
				gt.setHeSo(result.getFloat(3));
				return gt.getLyDo();
			}
		}
		catch (Exception e) 
		{
			  
		}
		return ""; 
	}
	public TrangThaiBangChamCong LayMaHeSoLiDo(String lido)
	{
		try 
		{
			String sql = "Select * from TrangThaiBangChamCong where LyDoNghi=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, lido);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiBangChamCong gt = new TrangThaiBangChamCong();
				gt.setMaTTCC(result.getString(1));
				gt.setLyDo(result.getString(2));
				gt.setHeSo(result.getFloat(3));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
	public TrangThaiBangChamCong LayMaLyDo(String ph)
	{
		try 
		{
			String sql = "Select * from TrangThaiBangChamCong ORDER BY MaTTCC DESC";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, ph);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiBangChamCong gt = new TrangThaiBangChamCong();
				gt.setMaTTCC(result.getString(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
}
