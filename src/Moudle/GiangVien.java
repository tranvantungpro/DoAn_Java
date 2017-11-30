package Moudle;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GiangVien 
{
	private String MaGV;
	private String TenGV;
	private String DiaChi;
	private long TienLuong;
	public String getMaGV() {
		return MaGV;
	}
	public void setMaGV(String maGV) {
		MaGV = maGV;
	}
	public String getTenGV() {
		return TenGV;
	}
	public void setTenGV(String tenGV) {
		TenGV = tenGV;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public long getTienLuong() {
		return TienLuong;
	}
	public void setTienLuong(long tienLuong) {
		TienLuong = tienLuong;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTenGV();
	}
	
	 
}
