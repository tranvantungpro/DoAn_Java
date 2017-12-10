package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.GiangVien;
import Moudle.LopHoc;
import Moudle.PhongHoc;

public class KetNoiPhongHoc extends KetNoiSQLQuyen
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();

	
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
	public Vector<LopHoc> hienThilophoctheoMa(String maLH)
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
	
}
