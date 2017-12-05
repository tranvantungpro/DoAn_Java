package Moudle;

public class TrangThai 
{
	private String MaTT;
	private String TenTT;
	public String getMaTT() {
		return MaTT;
	}
	public void setMaTT(String maTT) {
		MaTT = maTT;
	}
	public String getTenTT() {
		return TenTT;
	}
	public void setTenTT(String tenTT) {
		TenTT = tenTT;
	}
	@Override
	public String toString() {
		return this.getTenTT();
	}
}
