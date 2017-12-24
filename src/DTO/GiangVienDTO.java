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
public class GiangVienDTO {
    
    private String magiangvien;
    private String tengiangvien;
    private String sdt;
    private String diachi;
    private float hesoluong;
    private float luongcoban;
    private Date ngayvaolam;
    private Date ngayketthuc;
    
    public GiangVienDTO(String magiangvien, String tengiangvien, String sdt, String diachi, float hesoluong,float luongcoban,Date ngayvaolam, Date ngayketthuc)
    {
        
        this.magiangvien=magiangvien;
        this.tengiangvien=tengiangvien;
        this.sdt=sdt;
        this.diachi=diachi;
        this.hesoluong=hesoluong;
        this.luongcoban=luongcoban;
        this.ngayvaolam=ngayvaolam;
        this.ngayketthuc=ngayketthuc;
        
    }

    /**
     * @return the magiangvien
     */
    public String getMagiangvien() {
        return magiangvien;
    }

    /**
     * @param magiangvien the magiangvien to set
     */
    public void setMagiangvien(String magiangvien) {
        this.magiangvien = magiangvien;
    }

    /**
     * @return the tengiangvien
     */
    public String getTengiangvien() {
        return tengiangvien;
    }

    /**
     * @param tengiangvien the tengiangvien to set
     */
    public void setTengiangvien(String tengiangvien) {
        this.tengiangvien = tengiangvien;
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
     * @return the hesoluong
     */
    public float getHesoluong() {
        return hesoluong;
    }

    /**
     * @param hesoluong the hesoluong to set
     */
    public void setHesoluong(float hesoluong) {
        this.hesoluong = hesoluong;
    }

    /**
     * @return the luongcoban
     */
    public float getLuongcoban() {
        return luongcoban;
    }

    /**
     * @param luongcoban the luongcoban to set
     */
    public void setLuongcoban(float luongcoban) {
        this.luongcoban = luongcoban;
    }

    /**
     * @return the ngayvaolam
     */
    public Date getNgayvaolam() {
        return ngayvaolam;
    }

    /**
     * @param ngayvaolam the ngayvaolam to set
     */
    public void setNgayvaolam(Date ngayvaolam) {
        this.ngayvaolam = ngayvaolam;
    }

    /**
     * @return the ngayketthuc
     */
    public Date getNgayketthuc() {
        return ngayketthuc;
    }

    /**
     * @param ngayketthuc the ngayketthuc to set
     */
    public void setNgayketthuc(Date ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }
}
