/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HocVienDTO;
import DTO.LopHocDTO;
import DTO.NhanVienDTO;
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
public class NhanVienDAL {
    
    DataAccess da = new DataAccess();
    
    ArrayList<NhanVienDTO> list = new ArrayList<NhanVienDTO>();
    
    public ArrayList<NhanVienDTO> LayDanhSachNhanVien()
    {
        ResultSet dl = null;

        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NhanVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            dl = da.GetData("select * from NhanSu");

            NhanVienDTO nv ;
            while(dl.next())
            {
                nv = new NhanVienDTO(
                        dl.getString("MaNS"),
                        dl.getString("TenNS"),
                        dl.getString("Sdt"),
                        dl.getString("DiaChi"),
                        dl.getString("MaPB"),
                        dl.getDate("NgayVaoLam"),
                        dl.getDate("NgayKetThuc")
                );
              
                list.add(nv);                
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    
    
    
}
