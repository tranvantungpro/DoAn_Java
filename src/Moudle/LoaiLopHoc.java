package Moudle;

public class LoaiLopHoc 
{
	private String MaLoaiLH;
	private String TenLoaiLH;
	public String getMaLoaiLH() {
		return MaLoaiLH;
	}
	public void setMaLoaiLH(String maLoaiLH) {
		MaLoaiLH = maLoaiLH;
	}
	public String getTenLoaiLH() {
		return TenLoaiLH;
	}
	public void setTenLoaiLH(String tenLoaiLH) {
		TenLoaiLH = tenLoaiLH;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTenLoaiLH();
	}
}
