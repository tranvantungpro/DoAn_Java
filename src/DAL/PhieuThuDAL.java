/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.ChuongTrinhHocDTO;
import DTO.HocVienDTO;
import DTO.HocVien_NoDTO;
import DTO.PhieuThuDTO;
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
public class PhieuThuDAL {
    
    DataAccess da = new DataAccess();
    
    public int ThemPhieuThu(String mahocvien,String malop, String manhanvien,long sotientra,long sotienno, Date ngay) 
    {
        int i=-1;
        
        try {
            try {
                da.open();
                //ResultSet rs = t.Find("Select * from Books where ID=?", "2");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhieuThuDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            da.con.setAutoCommit(false);

            CallableStatement command = da.con.prepareCall("{call insertphieuthu(?,?,?,?,?,?)}");
       
            command.setString(1,mahocvien);
            command.setString(2,malop);
            command.setString(3,manhanvien);
            command.setLong(4, sotientra);
            command.setLong(5, sotienno);
            command.setDate(6, ngay);
            command.execute();
            i=0;
            da.con.commit();
        } catch (SQLException ex) {
            try
            {
                da.con.rollback();
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
                Logger.getLogger(PhieuThuDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i;
    }
    
    public Long LayTongTienPhieuThu() {

        ResultSet dl = null;
        long tien=0;

        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhieuThuDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            dl = da.GetData("select SoTienTra from PhieuThu");
            while(dl.next())
            {
                tien=tien +dl.getLong("SoTienTra");            
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
    
    
    public Long LayTongTienPhieuThuTheoThoiGian(String ngaybatdau, String ngayketthuc)
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
            preStatement=da.con.prepareStatement("select SoTienTra from PhieuThu where Ngay >=? and Ngay<=?");
            preStatement.setString(1,ngaybatdau);
            preStatement.setString(2,ngayketthuc);
            dl=preStatement.executeQuery(); 
            while(dl.next())
            {
                tien=tien +dl.getLong("SoTienTra");            
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
    
    
    public Long LayTongTienHocVienNo() {

        ResultSet dl = null;
        long tien=0;

        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PhieuThuDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            dl = da.GetData("select SoTienNo from PhieuThu");
            while(dl.next())
            {
                tien=tien +dl.getLong("SoTienNo");            
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

    public Long LayTongTienHocVienNoTheoThoiGian(String ngaybatdau, String ngayketthuc)
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
            preStatement=da.con.prepareStatement("select SoTienNo from PhieuThu where Ngay >=? and Ngay<=?");
            preStatement.setString(1,ngaybatdau);
            preStatement.setString(2,ngayketthuc);
            dl=preStatement.executeQuery(); 
            while(dl.next())
            {
                tien=tien +dl.getLong("SoTienNo");            
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

    ArrayList<HocVien_NoDTO> list = new ArrayList<HocVien_NoDTO>();
    
    public ArrayList<HocVien_NoDTO> LayDanhSachHocVienNo(){

        ResultSet dl = null;

        
        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            dl = da.GetData("select HocVien.TenHV as Ten ,b.TienNo as TienNo from HocVien ,(select PhieuThu.MaHV, SUM(PhieuThu.SoTienNo)as TienNo from PhieuThu, HocVien where PhieuThu.SoTienNo>0 group by PhieuThu.MaHV) as b where HocVien.MaHV=b.MaHV");
            
            HocVien_NoDTO hvn ;
            while(dl.next())
            {
                //ctth = new ChuongTrinhHocDTO(dl.getString("TenCTH"),dl.getString("MaCTH"),dl.getLong("HocPhi"));
                hvn=new HocVien_NoDTO(dl.getString("Ten"),dl.getLong("TienNo"));
                
                list.add(hvn);
            }
            
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(PhieuThuDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
        
    }
    
    public ArrayList<HocVien_NoDTO> LayDanhSachHocVienNoNgay(String ngaybatdau, String ngayketthuc){

        ResultSet dl = null;

        
        try {

            try {
                da.open();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HocVienDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String sql= "select HocVien.TenHV as Ten ,b.TienNo as TienNo from HocVien ,(select PhieuThu.MaHV, SUM(PhieuThu.SoTienNo)as TienNo from PhieuThu, HocVien where PhieuThu.SoTienNo>0 and PhieuThu.Ngay >= ? and PhieuThu.Ngay <=? group by PhieuThu.MaHV) as b where HocVien.MaHV=b.MaHV";
            String sql= "select HocVien.TenHV,b.TienNo from HocVien ,(select PhieuThu.MaHV, SUM(PhieuThu.SoTienNo)as TienNo from PhieuThu, HocVien where PhieuThu.SoTienNo>0 and PhieuThu.Ngay >= '2017-12-16' and PhieuThu.Ngay<= '2017-12-21' group by PhieuThu.MaHV) as b where HocVien.MaHV=b.MaHV ";
            
            PreparedStatement preStatement=null;
            //dl = da.GetData("select SoTienTra from PhieuThu where Ngay >=? and Ngay<=?");
            preStatement=da.con.prepareStatement(sql);
            //preStatement.setString(1,ngaybatdau);
            //preStatement.setString(2,ngayketthuc);
            dl=preStatement.executeQuery(); 
            HocVien_NoDTO hvn ;
            

            System.out.print(dl.getString("TenHV"));
            
            while(dl.next())
            {
                //ctth = new ChuongTrinhHocDTO(dl.getString("TenCTH"),dl.getString("MaCTH"),dl.getLong("HocPhi"));
                
                hvn=new HocVien_NoDTO(dl.getString("TenHV"),dl.getLong("TienNo"));
                
                list.add(hvn);
            }
            
        } catch (SQLException ex) {
            System.out.print(ex);
            System.out.print("Chay vo catch");
        } finally {
            try {
                da.Close();
            } catch (SQLException ex) {
                Logger.getLogger(PhieuThuDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
        
    }
    
}
