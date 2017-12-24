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
public class HocVien_NoDTO {
    
    private String TenHocVien;
    private long tienno;
    
    public HocVien_NoDTO(String ten, long tienno)
    {
        this.TenHocVien=ten;
        this.tienno=tienno;
    }

    /**
     * @return the TenHocVien
     */
    public String getTenHocVien() {
        return TenHocVien;
    }

    /**
     * @param TenHocVien the TenHocVien to set
     */
    public void setTenHocVien(String TenHocVien) {
        this.TenHocVien = TenHocVien;
    }

    /**
     * @return the tienno
     */
    public long getTienno() {
        return tienno;
    }

    /**
     * @param tienno the tienno to set
     */
    public void setTienno(long tienno) {
        this.tienno = tienno;
    }
    
    
    
    
    
}
