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
public class PhieuChiDTO {
    
    private String maphieuchi;
    private String manv;
    private Date ngay;

    /**
     * @return the maphieuchi
     */
    public String getMaphieuchi() {
        return maphieuchi;
    }

    /**
     * @param maphieuchi the maphieuchi to set
     */
    public void setMaphieuchi(String maphieuchi) {
        this.maphieuchi = maphieuchi;
    }

    /**
     * @return the manv
     */
    public String getManv() {
        return manv;
    }

    /**
     * @param manv the manv to set
     */
    public void setManv(String manv) {
        this.manv = manv;
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
    
    
    
    
}
