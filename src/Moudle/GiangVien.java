package Moudle;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

public class GiangVien 
{
	private String MaGV;
	private String TenGV;
	private String DiaChi;
	private String sdt;
	private Date NgayVL;
	private Date NgayKT;
	
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

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	

	public GiangVien() {
		super();
	}

	public GiangVien(String maGV, String tenGV, String diaChi, String sdt, Date ngayVL, Date ngayKT) {
		super();
		MaGV = maGV;
		TenGV = tenGV;
		DiaChi = diaChi;
		this.sdt = sdt;
		NgayVL = ngayVL;
		NgayKT = ngayKT;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTenGV();
	}
	
	 
}
