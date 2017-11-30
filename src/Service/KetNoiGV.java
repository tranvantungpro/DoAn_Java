package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Moudle.GiangVien;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
public class KetNoiGV extends KetNoiSQL
{
	public Vector<GiangVien> docToanBoDanhMuc()
	{
		Vector<GiangVien> vec=new Vector<GiangVien>();
		try
		{
			String sql="select * from GiangVien";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				GiangVien dm=new GiangVien();
				dm.setMaGV(result.getString(1));
				dm.setTenGV(result.getString(2)); 
				dm.setDiaChi(result.getString(3));
				dm.setTienLuong(result.getLong(4));
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
