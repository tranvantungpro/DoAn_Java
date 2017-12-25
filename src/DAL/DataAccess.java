/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Service.KetNoiSQL;

/**
 *
 * @author Tran Lam Khanh Tuong
 */
public class DataAccess {
    
    public Connection conn=null;
    
    public void open() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       // String url="jdbc:sqlserver://:1433;instance=MSSQLSERVER;databaseName=QuanLyTrungTamTH;user=sa;password=sa";
        String url="jdbc:sqlserver://localhost:1433;instance=MSSQLSERVER;databaseName=QuanLyTrungTamTH;integratedSecurity=true;";

       // String url="jdbc:sqlserver://TRANTUNG\\TRANTUNG:1433;instance=MSSQLSERVER;databaseName=QuanLyTrungTamTH;user=sa;password=sa";
        //String url="jdbc:sqlserver://TRANTUNG\\TRANTUNG:1433;instance=MSSQLSERVER;databaseName=QuanLyTrungTamTH;integratedSecurity=true;";
        conn=DriverManager.getConnection(url);
        System.out.print("Chay vao open");
        
     
    }
    
    public ResultSet GetData(String sql) throws SQLException
    {
        ResultSet kq=null;
        
        Statement statement = this.conn.createStatement();
        
        kq=statement.executeQuery(sql);
        
        return kq;
    }
    
    public ResultSet Find(String sql,String dk) throws SQLException
    {
        PreparedStatement preStatement=null;
        
        ResultSet result=null;
        
        preStatement=this.conn.prepareStatement(sql);
        
        preStatement.setString(1,dk);
        
        result=preStatement.executeQuery();
        
        return result;
    }
    
    public void Close() throws SQLException
    {
        if(this.conn!=null)
        {
            this.conn.close();
        }
    }
    
}
