package Service;

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

import Moudle.CTH;;
public class KetNoiCTH extends KetNoiSQL
{
	public Vector<CTH> docToanBoDanhMuc()
	{
		Vector<CTH> vec=new Vector<CTH>();
		try
		{
			String sql="select * from ChuongTrinhHoc";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				CTH dm=new CTH();
				dm.setMaCTH(result.getString(1));
				dm.setTenCTH(result.getString(2)); 
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