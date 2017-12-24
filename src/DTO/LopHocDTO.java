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
public class LopHocDTO {
    
    private String malophoc;
    private String tenlophoc;
    private String loailophoc;
    private String machuongtrinhhoc;
    private Date ngaybatdau;
    private Date ngayketthuc;
    private String magiaovien;
    private String MaTG;
    private String MaPH;

    
    
    public LopHocDTO(String mlh,String tlh,String loai,String mct,Date bd,Date kt,String mgv,String MaTG,String MaPH)
    {
        this.malophoc=mlh;
        this.tenlophoc=tlh;
        this.loailophoc=loai;
        this.machuongtrinhhoc=mct;
        this.ngaybatdau=bd;
        this.ngayketthuc=kt;
        this.magiaovien=mgv;
        this.MaTG=MaTG; 
        this.MaPH=MaPH;
        
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
     * @return the tenlophoc
     */
    public String getTenlophoc() {
        return tenlophoc;
    }

    /**
     * @param tenlophoc the tenlophoc to set
     */
    public void setTenlophoc(String tenlophoc) {
        this.tenlophoc = tenlophoc;
    }

    /**
     * @return the loailophoc
     */
    public String getLoailophoc() {
        return loailophoc;
    }

    /**
     * @param loailophoc the loailophoc to set
     */
    public void setLoailophoc(String loailophoc) {
        this.loailophoc = loailophoc;
    }

    /**
     * @return the machuongtrinhhoc
     */
    public String getMachuongtrinhhoc() {
        return machuongtrinhhoc;
    }

    /**
     * @param machuongtrinhhoc the machuongtrinhhoc to set
     */
    public void setMachuongtrinhhoc(String machuongtrinhhoc) {
        this.machuongtrinhhoc = machuongtrinhhoc;
    }

    /**
     * @return the MaTG
     */
    public String getMaTG() {
        return MaTG;
    }

    /**
     * @param MaTG the MaTG to set
     */
    public void setMaTG(String MaTG) {
        this.MaTG = MaTG;
    }

    /**
     * @return the MaPH
     */
    public String getMaPH() {
        return MaPH;
    }

    /**
     * @param MaPH the MaPH to set
     */
    public void setMaPH(String MaPH) {
        this.MaPH = MaPH;
    }

    /**
     * @return the ngaybatdau
     */
    public Date getNgaybatdau() {
        return ngaybatdau;
    }

    /**
     * @param ngaybatdau the ngaybatdau to set
     */
    public void setNgaybatdau(Date ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
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

    /**
     * @return the magiaovien
     */
    public String getMagiaovien() {
        return magiaovien;
    }

    /**
     * @param magiaovien the magiaovien to set
     */
    public void setMagiaovien(String magiaovien) {
        this.magiaovien = magiaovien;
    }
    
    public String toString()
    {
        return tenlophoc;
    }
    
    

    
    
    
}
