/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Tran Lam Khanh Tuong
 */
public class ChuongTrinhHocDTO {
    
    private String tenchuongtrinhoc;
    private String machuongtrinhhoc;
    private long hocphi;
    
    
    public ChuongTrinhHocDTO(String t,String mcth,long hocphi)
    {
        
        this.machuongtrinhhoc=mcth;
        this.tenchuongtrinhoc=t;
        this.hocphi=hocphi;
    }
      
    public String toString()
    {
        return getTenchuongtrinhoc();
    }

    /**
     * @return the tenchuongtrinhoc
     */
    public String getTenchuongtrinhoc() {
        return tenchuongtrinhoc;
    }

    /**
     * @param tenchuongtrinhoc the tenchuongtrinhoc to set
     */
    public void setTenchuongtrinhoc(String tenchuongtrinhoc) {
        this.tenchuongtrinhoc = tenchuongtrinhoc;
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
     * @return the hocphi
     */
    public long getHocphi() {
        return hocphi;
    }

    /**
     * @param hocphi the hocphi to set
     */
    public void setHocphi(long hocphi) {
        this.hocphi = hocphi;
    }
}
