package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.GiangVien;
import Moudle.TrangThaiBangChamCong;
import Moudle.TrangThaiNhanVien;

public class KetNoiTrangThaiNhanVien extends KetNoiSQLQuyen {
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	public Vector<TrangThaiNhanVien> docToanBoTrangThai()
	{
		Vector<TrangThaiNhanVien> vec=new Vector<TrangThaiNhanVien>();
		try
		{
			String sql="select * from TrangThaiNhanVien";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				TrangThaiNhanVien dm=new TrangThaiNhanVien();
				dm.setMaTT(result.getString(1));
				dm.setLoaiTT(result.getString(2)); 
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public TrangThaiNhanVien HienThiTTTheoTen(String tt)
	{
		//Vector<TrangThaiNhanVien> vec=new Vector<TrangThaiNhanVien>();
		try
		{
			String sql="select * from TrangThaiNhanVien where MaTT=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tt);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiNhanVien dm=new TrangThaiNhanVien();
				dm.setMaTT(result.getString(1));
				dm.setLoaiTT(result.getString(2));
				return dm;
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	public TrangThaiNhanVien LayMaTT(String tentt)
	{
		//Vector<TrangThaiNhanVien> vec=new Vector<TrangThaiNhanVien>();
		try
		{
			String sql="select * from TrangThaiNhanVien where LoaiTT=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tentt);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiNhanVien dm=new TrangThaiNhanVien();
				dm.setMaTT(result.getString(1));
				dm.setLoaiTT(result.getString(2));
				return dm;
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	public String LayTenTrangThai(String lydo) {
		try 
		{
			String sql = "select * from TrangThaiNhanVien where MaTT=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, lydo);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiNhanVien gt = new TrangThaiNhanVien();
				gt.setMaTT(result.getString(1));
				gt.setLoaiTT(result.getString(2)); 	
				return gt.getLoaiTT();
			}
		}
		catch (Exception e) 
		{
			  
		}
		return ""; 
	}
	public Vector<TrangThaiNhanVien> hienThiTTLenCbo(String magv) {
		// TODO Auto-generated method stub
		Vector<TrangThaiNhanVien> vec=new Vector<TrangThaiNhanVien>();
		try
		{
			String sql="select * from TrangThaiNhanVien where MaTT=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, magv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				TrangThaiNhanVien dm=new TrangThaiNhanVien();
				dm.setMaTT(result.getString(1));
				dm.setLoaiTT(result.getString(2)); 
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
