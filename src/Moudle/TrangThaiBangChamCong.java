package Moudle;

public class TrangThaiBangChamCong {
private String MaTTCC,LyDo;
private float HeSo;



public float getHeSo() {
	return HeSo;
}

public void setHeSo(float heSo) {
	HeSo = heSo;
}

public String getMaTTCC() {
	return MaTTCC;
}

public void setMaTTCC(String maTTCC) {
	MaTTCC = maTTCC;
}

public String getLyDo() {
	return LyDo;
}

public void setLyDo(String lyDo) {
	LyDo = lyDo;
}

public TrangThaiBangChamCong(String maTTCC, String lyDo) {
	super();
	MaTTCC = maTTCC;
	LyDo = lyDo;
}

public TrangThaiBangChamCong() {
	super();
}

@Override
public String toString() {
	return this.LyDo ;
}

}
