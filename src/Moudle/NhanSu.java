package Moudle;

import java.util.Date;

public class NhanSu {
	private String MaNV;
	private String TenNV;
	private String DiaChi,MaTT;
	private String sdt;
	private Date NgayVL,NgayKT;
	
	public String getMaTT() {
		return MaTT;
	}
	public void setMaTT(String trangThai) {
		MaTT = trangThai;
	}
	public Date getNgayVL() {
		return NgayVL;
	}
	public void setNgayVL(Date ngayVL) {
		NgayVL = ngayVL;
	}
	public Date getNgayKT() {
		return NgayKT;
	}
	public void setNgayKT(Date ngayKT) {
		NgayKT = ngayKT;
	}
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public String getTenNV() {
		return TenNV;
	}
	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	

	
	public NhanSu(String maNV, String tenNV, String diaChi, String sdt,  Date ngayVL, Date ngayKT) {
		super();
		MaNV = maNV;
		TenNV = tenNV;
		DiaChi = diaChi;
		this.sdt = sdt;
		
		NgayVL = ngayVL;
		NgayKT = ngayKT;
	}
	public NhanSu() {
		super();
	}
	@Override
	public String toString() {
		return this.TenNV;
	}
	
}
