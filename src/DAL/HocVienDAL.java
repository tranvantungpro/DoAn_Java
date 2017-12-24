/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HocVienDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Lam Khanh Tuong
 */
public class HocVienDAL {

    DataAccess da = new DataAccess();
    
    ArrayList<HocVienDTO> list = new ArrayList<HocVienDTO>();

    public ArrayList<HocVienDTO> LayDanhSachHocVien() {

        ResultSet dl = null;

        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            dl = da.GetData("select * from HocVien");
            
            HocVienDTO hv ;
            while(dl.next())
            {
                hv = new HocVienDTO();
                hv.setMahocvien(dl.getString("MaHv"));
                hv.setTenhocvien(dl.getString("TenHV"));
                hv.setEmail(dl.getString("Email"));
                hv.setDiachi(dl.getString("DiaChi"));
                hv.setTrangthai(dl.getString("TrangThai"));
                hv.setNgaysinh(dl.getDate("NgaySinh"));
                hv.setSdt(dl.getString("SDT"));
                hv.setMaLH(dl.getString("MaLH"));                      
                list.add(hv);
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
        //System.out.print("jaja");
        return list;
    }
    
    
    public ArrayList<HocVienDTO> TimKiemHocVienTheoTen(String ten)
    {
         ResultSet dl = null;
         try {

             try {
                 da.open();
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
             }
            PreparedStatement preStatement=null;
            preStatement=da.con.prepareStatement("select * from HocVien where TenHV like ?");
            preStatement.setString(1,ten);
            dl=preStatement.executeQuery(); 
            HocVienDTO hv ;
            while(dl.next())
            {
                hv = new HocVienDTO();
                
                hv = new HocVienDTO();
                hv.setMahocvien(dl.getString("MaHv"));
                hv.setTenhocvien(dl.getString("TenHV"));
                hv.setEmail(dl.getString("Email"));
                hv.setDiachi(dl.getString("DiaChi"));
                hv.setTrangthai(dl.getString("TrangThai"));
                hv.setNgaysinh(dl.getDate("NgaySinh"));
                hv.setSdt(dl.getString("SDT"));
                hv.setMaLH(dl.getString("MaLH"));                      
                list.add(hv);
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

}
