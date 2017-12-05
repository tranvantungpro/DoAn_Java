package Moudle;

import java.util.Date;

public class HocVien 
{
	private String MaHV;
	private String TenHV;
	private Date NgaySinh;
	private String DiaChi;
	private String SDT;
	private String Email;
	private String MaLH;
	private String TrangThai;
	public String getTrangThai() {
		return TrangThai;
	}
	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	public String getMaHV() {
		return MaHV;
	}
	public void setMaHV(String maHV) {
		MaHV = maHV;
	}
	public String getTenHV() {
		return TenHV;
	}
	public void setTenHV(String tenHV) {
		TenHV = tenHV;
	}
	public Date getNgaySinh() {
		return NgaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMaLH() {
		return MaLH;
	}
	public void setMaLH(String maLH) {
		MaLH = maLH;
	}
	
	@Override
	public String toString() {
		return this.getTenHV();
	}
}
