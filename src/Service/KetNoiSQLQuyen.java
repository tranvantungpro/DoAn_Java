package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class KetNoiSQLQuyen {
	protected static Connection conn=null;
//	public static Connection getConnect(String strServer,String strDatabase)
//	{
//		try 
//		{
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String connectionUrl=
//					"jdbc:sqlserver://"+strServer+":1433;databaseName="+strDatabase+";integratedSecurity=true;";
//			conn= DriverManager.getConnection(connectionUrl);
//		} catch (SQLException e) {
//			System.out.println("SQL Exception: "+ e.toString());
//		} catch (ClassNotFoundException cE) {
//			System.out.println("Class Not Found Exception: "+ cE.toString());
//		}
//		return conn;
//	}
	
	public Connection getConnect( )
	{
		String  a= "desktop-k12emgj";
		String data = "QuanLyTrungTamTH";
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl=
					"jdbc:sqlserver://"+a+":1433;databaseName="+data+";integratedSecurity=true;";
			conn= DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			System.out.println("SQL Exception: "+ e.toString());
		} catch (ClassNotFoundException cE) {
			System.out.println("Class Not Found Exception: "+ cE.toString());
		}
		//JOptionPane.showMessageDialog(null, conn.toString());
		return conn;
	}
}
