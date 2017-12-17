package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.PhongHoc;
import Moudle.ThoiGian;
import Moudle.PhongHoc;
import Moudle.GiangVien;
import Moudle.LopHoc;
import Moudle.PhongHoc;

public class KetNoiPhongHoc extends KetNoiSQL
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
//	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
//	Connection conn = a.getConnect();

	
	public ArrayList<PhongHoc> LayToanBoPhongHoc()
	{
		 
		ArrayList<PhongHoc> dsPH= new ArrayList<PhongHoc>();
		try 
		{
			String sql =" select PhongHoc.MaPH,count(MaLH) as SoLopHoc from PhongHoc" + 
					" group by PhongHoc.MaPH  ";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				PhongHoc ph = new PhongHoc();
				ph.setMaPH(result.getString(1));
				ph.setTenPH(result.getString(2));
				ph.setSucChua(result.getInt(3));
				ph.setTrangThai(result.getString(4));				
				ph.setMaLH(result.getString(5));
				dsPH.add(ph);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dsPH;
	}
	
	public ArrayList<PhongHoc> TimPhongHoc(String tenPH)
	{
		ArrayList<PhongHoc> dsPH= new ArrayList<PhongHoc>();
		try
		{
			String sql= "select * from PhongHoc where TenPH like concat('%', ?, '%') collate sql_latin1_general_cp1_ci_as group by MaPH";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenPH);
			result=preStatement.executeQuery();
			while(result.next())
			{
				PhongHoc ph = new PhongHoc();
				ph.setMaPH(result.getString(1));
				ph.setTenPH(result.getString(2));
				ph.setTrangThai(result.getString(3));
				ph.setSucChua(result.getInt(4));
				ph.setMaLH(result.getString(5));
				dsPH.add(ph);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsPH;
	}
	public Vector<LopHoc> hienThilophoPhongHoceoMa(String maLH)
	{
		Vector<LopHoc> vec=new Vector<LopHoc>();
		try
		{
			String sql="select * from LopHoc where MaLH=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, maLH);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LopHoc dm=new LopHoc();
				dm.setMaLH(result.getString(1));
				dm.setTenLH(result.getString(2)); 
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<PhongHoc>HienThiPHCbo()
	{
		Vector<PhongHoc> vec=new Vector<PhongHoc>();
		try
		{
			String sql="select * from PhongHoc";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				PhongHoc dm=new PhongHoc();
				dm.setMaPH(result.getString(1));
				dm.setTenPH(result.getString(2)); 
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<PhongHoc>DocToanBoPhongHoc()
	{
		Vector<PhongHoc> vec=new Vector<PhongHoc>();
		try
		{
			String sql="select * from PhongHoc";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				PhongHoc dm=new PhongHoc();
				dm.setMaPH(result.getString(1));
				dm.setTenPH(result.getString(2)); 
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public Vector<PhongHoc> HienThiPHLenCboThemMa(String magv)
	{
		Vector<PhongHoc> vec=new Vector<PhongHoc>();
		try
		{
			String sql="select * from PhongHoc where MaPH=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, magv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				PhongHoc dm=new PhongHoc();
				dm.setMaPH(result.getString(1));
				dm.setTenPH(result.getString(2));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}

	public String LayTenPH(String maph) {
		try 
		{
			String sql = "Select * from PhongHoc where MaPH=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, maph);
			result=preStatement.executeQuery();
			while(result.next())
			{
				PhongHoc gt = new PhongHoc();
				gt.setMaPH(result.getString(1));
				gt.setTenPH(result.getString(2)); 
				return gt.getTenPH();
			}
		}
		catch (Exception e) 
		{
			  
		}
		return ""; 
	}

	public PhongHoc LayMaPHH(String ph)
	{
		try 
		{
			String sql = "Select * from PhongHoc where TenPH=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, ph);
			result=preStatement.executeQuery();
			while(result.next())
			{
				PhongHoc gt = new PhongHoc();
				gt.setMaPH(result.getString(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
}
