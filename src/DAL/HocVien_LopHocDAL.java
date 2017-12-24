/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import Service.KetNoiSQL;

/**
 *
 * @author Tran Lam Khanh Tuong
 */
public class HocVien_LopHocDAL  {
    
    DataAccess da = new DataAccess();
    
    public int GhiDanh(String mahocvien,String malophoc,Date ngaydangky) 
    {
        PreparedStatement ps = null;
        int ret=-1;
        try {
            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVien_LopHocDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            da.conn.setAutoCommit(false);
            String insert = "insert into HocVien_LopHoc (MaHV,MaLH,Ngay) values(?, ?, ?)";
            ps = da.conn.prepareStatement(insert);
            ps.setString(1,mahocvien);
            ps.setString(2, malophoc);
            ps.setDate(3, ngaydangky);
            //ps.setString(4,manv);
            ret = ps.executeUpdate();
            da.conn.commit();
        } catch (SQLException ex) {
            try
            {
                da.conn.rollback();
            }
            catch(SQLException ex1)
            {
                ex1.printStackTrace();
            }
            System.out.print(ex);
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(HocVien_LopHocDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
    
}
