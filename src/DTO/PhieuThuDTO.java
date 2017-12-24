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
public class PhieuThuDTO {
    
    private String mapieuthu;
    private String mahv;
    private String malh;
    private String manhanvien;
    private long sotientra;
    private long sotienno;
    private Date ngay;


    /**
     * @return the mapieuthu
     */
    public String getMapieuthu() {
        return mapieuthu;
    }

    /**
     * @param mapieuthu the mapieuthu to set
     */
    public void setMapieuthu(String mapieuthu) {
        this.mapieuthu = mapieuthu;
    }

    /**
     * @return the mahv
     */
    public String getMahv() {
        return mahv;
    }

    /**
     * @param mahv the mahv to set
     */
    public void setMahv(String mahv) {
        this.mahv = mahv;
    }

    /**
     * @return the malh
     */
    public String getMalh() {
        return malh;
    }

    /**
     * @param malh the malh to set
     */
    public void setMalh(String malh) {
        this.malh = malh;
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
     * @return the sotientra
     */
    public long getSotientra() {
        return sotientra;
    }

    /**
     * @param sotientra the sotientra to set
     */
    public void setSotientra(long sotientra) {
        this.sotientra = sotientra;
    }

    /**
     * @return the sotienno
     */
    public long getSotienno() {
        return sotienno;
    }

    /**
     * @param sotienno the sotienno to set
     */
    public void setSotienno(long sotienno) {
        this.sotienno = sotienno;
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
