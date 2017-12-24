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
public class HocVienDTO {

    private String mahocvien;
    private String tenhocvien;
    private Date ngaysinh;
    private String email;
    private String diachi;
    private String sdt;
    private String MaLH;
    private String trangthai;
    
   
    
    

    /**
     * @return the mahocvien
     */
    public String getMahocvien() {
        return mahocvien;
    }

    /**
     * @param mahocvien the mahocvien to set
     */
    public void setMahocvien(String mahocvien) {
        this.mahocvien = mahocvien;
    }

    /**
     * @return the tenhocvien
     */
    public String getTenhocvien() {
        return tenhocvien;
    }

    /**
     * @param tenhocvien the tenhocvien to set
     */
    public void setTenhocvien(String tenhocvien) {
        this.tenhocvien = tenhocvien;
    }

    /**
     * @return the ngaysinh
     */
    public Date getNgaysinh() {
        return ngaysinh;
    }

    /**
     * @param ngaysinh the ngaysinh to set
     */
    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the MaLH
     */
    public String getMaLH() {
        return MaLH;
    }

    /**
     * @param MaLH the MaLH to set
     */
    public void setMaLH(String MaLH) {
        this.MaLH = MaLH;
    }

    /**
     * @return the trangthai
     */
    public String getTrangthai() {
        return trangthai;
    }

    /**
     * @param trangthai the trangthai to set
     */
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

}
