/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.ChuongTrinhHocDTO;
import DTO.HocVienDTO;
import Service.KetNoiSQL;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tran Lam Khanh Tuong
 */
public class ChuongTrinhHocDAL  {


    ArrayList<ChuongTrinhHocDTO> list = new ArrayList<ChuongTrinhHocDTO>();
    DataAccess da = new DataAccess();

    public ArrayList<ChuongTrinhHocDTO> LayDanhSachChuongTrinhHoc(){

        ResultSet dl = null;

        
        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            dl = da.GetData("select * from ChuongTrinhHoc");
            
            ChuongTrinhHocDTO ctth ;
            while(dl.next())
            {
                ctth = new ChuongTrinhHocDTO(dl.getString("TenCTH"),dl.getString("MaCTH"),dl.getLong("HocPhi"));
                        
                list.add(ctth);
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
    
    public ArrayList<ChuongTrinhHocDTO> TimChuongTrinhHocTheoTen(String ten)
    {
         ResultSet dl = null;
         try {

             try {
                 da.open();
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
             }
            PreparedStatement preStatement=null;
            preStatement=da.conn.prepareStatement("select * from ChuongTrinhHoc where TenCTH like ?");
            preStatement.setString(1,ten);
            dl=preStatement.executeQuery(); 
            ChuongTrinhHocDTO cth ;
            while(dl.next())
            {
                cth = new ChuongTrinhHocDTO(dl.getString("TenCTH"),dl.getString("MaCTH"),dl.getLong("HocPhi"));                     
                list.add(cth);
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
    
    public int ThemCTH(String tenchuongtrinhhoc,long tien) 
    {
        int i=-1;
        
        try {
            try {
                da.open();
                //ResultSet rs = t.Find("Select * from Books where ID=?", "2");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChuongTrinhHocDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            da.conn.setAutoCommit(false);

            CallableStatement command = da.conn.prepareCall("{call inserchuongtrinhhoc(?,?)}");
       
            command.setString(1,tenchuongtrinhhoc);
            command.setLong(2,tien);
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
                Logger.getLogger(ChuongTrinhHocDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i;
    }
    
    public int XoaTheoMa(String ma)
    {
        int i=-1;
        
        try {
            try {
                da.open();
                //ResultSet rs = t.Find("Select * from Books where ID=?", "2");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChuongTrinhHocDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            da.conn.setAutoCommit(false);

            CallableStatement command = da.conn.prepareCall("{call deletechuongtrinhhoc(?)}");
            command.setString(1, ma);
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
                Logger.getLogger(ChuongTrinhHocDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i;
    }

    public int CapNhatCTH(String machuongtrinhhoc, String tenchuongtrinhhoc, long hocphi)
    {
        int i=-1;
        
        try {
            try {
                da.open();
                //ResultSet rs = t.Find("Select * from Books where ID=?", "2");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChuongTrinhHocDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            da.conn.setAutoCommit(false);

            CallableStatement command = da.conn.prepareCall("{call updatechuongtrinhhoc(?,?,?)}");
       
            command.setString(1,machuongtrinhhoc);
            command.setString(2,tenchuongtrinhhoc);
            command.setLong(3,hocphi);
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
                Logger.getLogger(ChuongTrinhHocDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i;
    }

}
