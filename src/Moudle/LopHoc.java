package Moudle;

import java.util.Date;

public class LopHoc 
{
	private String MaLH;
	private String TenLH;
	private String LoaiLH;
	private String MaCTH; 
	private Date NgayBD;
	private Date NgayKT;
	private String MaGV;
	private int MaTG;
	private String MaPH;
	private int Siso;
	private int SiSoHT;
	 
	public int getSiSoHT() {
		return SiSoHT;
	}
	public void setSiSoHT(int siSoHT) {
		SiSoHT = siSoHT;
	}
	public int getSiso() {
		return Siso;
	}
	public void setSiso(int siso) {
		Siso = siso;
	}
	public int getMaTG() {
		return MaTG;
	}
	public void setMaTG(int i) {
		MaTG = i;
	}
	public String getMaPH() {
		return MaPH;
	}
	public void setMaPH(String maPH) {
		MaPH = maPH;
	}
	public String getMaLH() {
		return MaLH;
	}
	public void setMaLH(String maLH) {
		MaLH = maLH;
	}
	public String getTenLH() {
		return TenLH;
	}
	public void setTenLH(String tenLH) {
		TenLH = tenLH;
	}
	public String getLoaiLH() {
		return LoaiLH;
	}
	public void setLoaiLH(String loaiLH) {
		LoaiLH = loaiLH;
	}
	public String getMaCTH() {
		return MaCTH;
	}
	public void setMaCTH(String maCTH) {
		MaCTH = maCTH;
	} 
	public Date getNgayBD() {
		return NgayBD;
	}
	public void setNgayBD(Date ngayBD) {
		NgayBD = ngayBD;
	}
	public Date getNgayKT() {
		return NgayKT;
	}
	public void setNgayKT(Date ngayKT) {
		NgayKT = ngayKT;
	}
	public String getMaGV() {
		return MaGV;
	}
	public void setMaGV(String maGV) {
		MaGV = maGV;
	}
	
	@Override
	public String toString() {
		return this.getTenLH();
	}
	
	
	
}
