/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.PhieuChiDTO;
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
public class PhieuChiDAL {

    DataAccess da = new DataAccess();

    ArrayList<PhieuChiDTO> list = new ArrayList<PhieuChiDTO>();

    public ArrayList<PhieuChiDTO> TimKiemPhieuChi(String ngaybatdau, String ngayketthuc, String ten) {
        ResultSet dl = null;
        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement preStatement = null;
            preStatement = da.con.prepareStatement("select * from PhieuChi, NhanSu where PhieuChi.MaNhanVien=NhanSu.MaNS and Ngay >? and Ngay<? and MaNhanVien=?");

            preStatement.setString(1, ngaybatdau);
            preStatement.setString(2, ngayketthuc);
            preStatement.setString(3, ten);
            dl = preStatement.executeQuery();
            PhieuChiDTO pc;
            while (dl.next()) {
                pc = new PhieuChiDTO();
                pc.setMaphieuchi(dl.getString("MaPhieuChi"));
                pc.setManv(dl.getString("TenNS"));
                pc.setNgay(dl.getDate("Ngay"));
                list.add(pc);
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

    public int ThemPhieuChi(String maphieuchi, String manv, Date ngay) {
        int i = -1;

        try {
            try {
                da.open();
                //ResultSet rs = t.Find("Select * from Books where ID=?", "2");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhieuChiDAL.class.getName()).log(Level.SEVERE, null, ex);
            }

            da.con.setAutoCommit(false);

            CallableStatement command = da.con.prepareCall("{call insertphieuchi(?,?,?)}");

            command.setString(1, maphieuchi);
            command.setString(2, manv);
            command.setDate(3, ngay);

            command.execute();
            i = 0;
            da.con.commit();
        } catch (SQLException ex) {
            try {
                da.con.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            System.out.print(ex);
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(PhieuChiDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i;
    }

    public ArrayList<PhieuChiDTO> LayDanhSachPhieuChi() {

        ResultSet dl = null;

        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhieuChiDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            dl = da.GetData("select * from PhieuChi, NhanSu where PhieuChi.MaNhanVien=NhanSu.MaNS");

            PhieuChiDTO pc;
            while (dl.next()) {
                pc = new PhieuChiDTO();
                pc.setMaphieuchi(dl.getString("MaPhieuChi"));
                pc.setManv(dl.getString("TenNS"));
                pc.setNgay(dl.getDate("Ngay"));
                list.add(pc);
            }

        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(PhieuChiDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
   

}
