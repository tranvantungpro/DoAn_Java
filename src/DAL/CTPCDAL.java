/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.CTPCDTO;
import DTO.PhieuChiDTO;
import Service.KetNoiSQL;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Lam Khanh Tuong
 */
public class CTPCDAL  {
    
    DataAccess da = new DataAccess();
    
     public int ThemCTPC(String maphieuchi,String noidung, long tien ) 
    {
        int i=-1;
        
        try {
            try {
                da.open();
                //ResultSet rs = t.Find("Select * from Books where ID=?", "2");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhieuChiDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            da.conn.setAutoCommit(false);

            CallableStatement command = da.conn.prepareCall("{call insertctpc(?,?,?)}");
       
            command.setString(1,maphieuchi);
            command.setString(2,noidung);
            command.setLong(3,tien);
            command.execute();
            i=0;
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
                Logger.getLogger(CTPCDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i;
    } 
    ArrayList<CTPCDTO> list = new ArrayList<CTPCDTO>();
    
    public ArrayList<CTPCDTO> TimKiemCTPC(String maphieuchi) {
        ResultSet dl = null;
        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement preStatement = null;
            preStatement = da.conn.prepareStatement("select * from CTPC where MaPhieuChi=?");

            preStatement.setString(1,maphieuchi);

            dl = preStatement.executeQuery();
            CTPCDTO ctpc;
            while (dl.next()) {
                ctpc = new CTPCDTO();
                ctpc.setMapc(dl.getString("MaPhieuChi"));
                ctpc.setNoidung(dl.getString("NoiDung"));
                ctpc.setTien(dl.getLong("Tien"));
                list.add(ctpc);
            }

        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    
     public Long LayTongTienPhieuChi() {

        ResultSet dl = null;
        long tien=0;

        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CTPCDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            dl = da.GetData("select Tien from CTPC");
            while(dl.next())
            {
                tien=tien +dl.getLong("Tien");            
            }
            
                      
        } catch (SQLException ex) {
            System.out.print(ex);
            System.out.print("chay vao catch");
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(CTPCDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.print("jaja");
        return tien;
    }
    
    public Long LayTongTienChiTheoThoiGian(String ngaybatdau, String ngayketthuc)
    {
        ResultSet dl = null;
        long tien=0;

        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhieuThuDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement preStatement=null;
            //dl = da.GetData("select SoTienTra from PhieuThu where Ngay >=? and Ngay<=?");
            preStatement=da.conn.prepareStatement("select Tien from CTPC,PhieuChi where PhieuChi.MaPhieuChi=CTPC.MaPhieuChi and Ngay >=? and Ngay<=?");
            preStatement.setString(1,ngaybatdau);
            preStatement.setString(2,ngayketthuc);
            dl=preStatement.executeQuery(); 
            while(dl.next())
            {
                tien=tien +dl.getLong("Tien");            
            }
            
                      
        } catch (SQLException ex) {
            System.out.print(ex);
            System.out.print("chay vao catch");
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(PhieuThuDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //System.out.print("jaja");
        return tien;
        
    } 
     
}
