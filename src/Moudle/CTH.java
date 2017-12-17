package Moudle;

public class CTH 
{
	private String MaCTH;
	private String TenCTH;
	private int SoBuoi;
	
	public int getSoBuoi() {
		return SoBuoi;
	}
	public void setSoBuoi(int soBuoi) {
		SoBuoi = soBuoi;
	}
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
