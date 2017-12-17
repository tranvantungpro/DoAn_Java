package Moudle;

public class ThoiGian 
{
	private int MaTG;
	private String TenTG;
	public int getMaTG() {
		return MaTG;
	}
	public void setMaTG(int maTG) {
		MaTG = maTG;
	}
	public String getTenTG() {
		return TenTG;
	}
	public void setTenTG(String tenTG) {
		TenTG = tenTG;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTenTG();
	}
}
