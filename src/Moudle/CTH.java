package Moudle;

public class CTH 
{
	private String MaCTH;
	private String TenCTH;
	public String getMaCTH() {
		return MaCTH;
	}
	public void setMaCTH(String maCTH) {
		MaCTH = maCTH;
	}
	public String getTenCTH() {
		return TenCTH;
	}
	public void setTenCTH(String tenCTH) {
		TenCTH = tenCTH;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTenCTH();
	}
}
