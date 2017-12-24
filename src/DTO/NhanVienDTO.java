/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Tran Lam Khanh Tuong
 */
public class NhanVienDTO {
    
    private String ten;
    private String manhanvien;
    //private String ten;
    private String sdt;
    private String diachi;
    private String MaPB;
    private Date NgayVaoLam;
    private Date NgayKetThuc;
   // private long luong;
    
    public NhanVienDTO(String manhanvien,String ten,String sdt,String diachi,String MaPB,Date NgayVaoLam,Date NgayKetThuc)
    {
        this.manhanvien=manhanvien;
        this.ten=ten;
        this.sdt=sdt;
        this.diachi=diachi;
        this.MaPB=MaPB;
        this.NgayVaoLam=NgayVaoLam;
        this.NgayKetThuc=NgayKetThuc;
        //this.chucvu=chucvu;
        //this.luong=luong;
    }

    /**
     * @return the manhanvien
     */
    public String getManhanvien() {
        return manhanvien;
    }

    /**
     * @param manhanvien the manhanvien to set
     */
    public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the diachi
     */
    public String getDiachi() {
        return diachi;
    }

    /**
     * @param diachi the diachi to set
     */
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    /**
     * @return the MaPB
     */
    public String getMaPB() {
        return MaPB;
    }

    /**
     * @param MaPB the MaPB to set
     */
    public void setMaPB(String MaPB) {
        this.MaPB = MaPB;
    }

    /**
     * @return the NgayVaoLam
     */
    public Date getNgayVaoLam() {
        return NgayVaoLam;
    }

    /**
     * @param NgayVaoLam the NgayVaoLam to set
     */
    public void setNgayVaoLam(Date NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    /**
     * @return the NgayKetThuc
     */
    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    /**
     * @param NgayKetThuc the NgayKetThuc to set
     */
    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }
    
    
    public String toString()
    {
        return ten;
    }
    
}
