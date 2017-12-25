package Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Moudle.DangNhap;
import Moudle.GiaoTrinh;

 

public class KetNoiDangNhap extends KetNoiSQL 
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	public DangNhap login(String user,String pass, String server)
	{
		DangNhap  account =null;
		try
		{
			String sql="select * from DangNhap where Username=? and Password=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, user);
			preStatement.setString(2, pass); 
			ResultSet result=preStatement.executeQuery();
			if(result.next())
			{
				account=new DangNhap();
				account.setUserName(result.getString(1));
				account.setPassword(result.getString(2));
				account.setServer(result.getString(3));
			}
			 
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return account;
	}
}
