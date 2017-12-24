/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.GiangVienDTO;
import DTO.HocVienDTO;
import DTO.LopHocDTO;
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
public class GiangVienDAL {

    DataAccess da = new DataAccess();

    ArrayList<GiangVienDTO> list = new ArrayList<GiangVienDTO>();

    public ArrayList<GiangVienDTO> TimGiangVienTheoMaGV(String magv) {
        ResultSet dl = null;
        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement preStatement = null;
            preStatement = da.con.prepareStatement("select * from GiangVien where MaGV=?");
            preStatement.setString(1, magv);
            dl = preStatement.executeQuery();
            GiangVienDTO gv;
            while (dl.next()) {
                gv = new GiangVienDTO(dl.getString("MaGV"),
                        dl.getString("TenGV"),
                        dl.getString("Sdt"),
                        dl.getString("DiaChi"),
                        dl.getFloat("HeSoLuong"),
                        dl.getFloat("LuongCB"),
                        dl.getDate("NgayVaoLam"),
                        dl.getDate("NgayKetThuc")
                );

                list.add(gv);
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

    public GiangVienDTO TimGiangVien(String magv)
    {
        ResultSet dl = null;
        GiangVienDTO kq=null;
        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            PreparedStatement preStatement = null;
            preStatement = da.con.prepareStatement("select * from GiangVien where MaGV=?");
            preStatement.setString(1, magv);
            dl = preStatement.executeQuery();
            GiangVienDTO gv = null;
            while (dl.next()) {
                gv = new GiangVienDTO(dl.getString("MaGV"),
                        dl.getString("TenGV"),
                        dl.getString("Sdt"),
                        dl.getString("DiaChi"),
                        dl.getFloat("HeSoLuong"),
                        dl.getFloat("LuongCB"),
                        dl.getDate("NgayVaoLam"),
                        dl.getDate("NgayKetThuc")
                );
            }
            kq=gv;

        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;

    }
}
