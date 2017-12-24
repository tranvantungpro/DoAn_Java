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
public class HocVien_LopHocDTO {
    
    private String mahocvien;
    private String malophoc;
    private Date ngay;
    //private String manhanvien;
    
    public HocVien_LopHocDTO(String mhv,String mlh, Date n)
    {
        this.mahocvien=mhv;
        this.malophoc=mlh;
        this.ngay=n;
        //this.manhanvien=mnv;
    }

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
     * @return the malophoc
     */
    public String getMalophoc() {
        return malophoc;
    }

    /**
     * @param malophoc the malophoc to set
     */
    public void setMalophoc(String malophoc) {
        this.malophoc = malophoc;
    }

    /**
     * @return the ngay
     */
    public Date getNgay() {
        return ngay;
    }

    /**
     * @param ngay the ngay to set
     */
    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    /**
     * @return the manhanvien
     */
    /*public String getManhanvien() {
        return manhanvien;
    }*/

    /**
     * @param manhanvien the manhanvien to set
     */
    /*public void setManhanvien(String manhanvien) {
        this.manhanvien = manhanvien;
    }*/
    
    
    
}
