package Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.LoaiLopHoc;
public class KetNoiLLH extends KetNoiSQL 
{
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
}
