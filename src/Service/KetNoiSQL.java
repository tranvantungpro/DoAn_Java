package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class KetNoiSQL 
{
	protected static Connection conn=null;
	public static Connection getConnect(String strServer)
	{ 
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl=
					"jdbc:sqlserver://"+strServer+":1433;databaseName=QuanLyTrungTamTH;integratedSecurity=true;";
			conn= DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			System.out.println("SQL Exception: "+ e.toString());
		} catch (ClassNotFoundException cE) {
			System.out.println("Class Not Found Exception: "+ cE.toString());
		}
		return conn;
	}
	
	
}
