/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.HocVienDTO;
import DTO.LopHocDTO;
import Service.KetNoiSQL;

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
public class LopHocDAL  {

    DataAccess da= new DataAccess();
    
    ArrayList<LopHocDTO> list = new ArrayList<LopHocDTO>();
    
    public ArrayList<LopHocDTO> TimKiemLopHocTheoChuongTrinhHoc(String ten)    {
         ResultSet dl = null;
         try {

             try {
                 da.open();
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
             }
            //dl = da.Find("select * from HocVien",ten);
            PreparedStatement preStatement=null;
            preStatement=da.conn.prepareStatement("select * from LopHoc where MaCTH=?");
            preStatement.setString(1,ten);
            dl=preStatement.executeQuery();  
            LopHocDTO lh ;
            while(dl.next())
            {
                
                lh = new LopHocDTO(dl.getString("MaLH"),dl.getString("TenLH"),dl.getString("LoaiLH"),dl.getString("MaCTH"),dl.getDate("NgayBD"),dl.getDate("NgayKT"),dl.getString("MaGV"),dl.getString("MaTG"),dl.getString("MaPH"));
                list.add(lh);                
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
