package Moudle;

public class PhongHoc {
private String MaPH;
private  String TenPH;
private String TrangThai;
private int SucChua;
private String MaLH;

public String getMaPH() {
	return MaPH;
}
public void setMaPH(String maPH) {
	MaPH = maPH;
}
public String getTenPH() {
	return TenPH;
}
public void setTenPH(String tenPH) {
	TenPH = tenPH;
}
public String getTrangThai() {
	return TrangThai;
}
public void setTrangThai(String trangThai) {
	TrangThai = trangThai;
}
public int getSucChua() {
	return SucChua;
}
public void setSucChua(int sucChua) {
	SucChua = sucChua;
}
public String getMaLH() {
	return MaLH;
}
public void setMaLH(String maLH) {
	MaLH = maLH;
}
public PhongHoc(String maPH, String tenPh, String trangThai, int sucChua, String maLH) {
	super();
	MaPH = maPH;
	TenPH = tenPh;
	TrangThai = trangThai;
	SucChua = sucChua;
	MaLH = maLH;
}
public PhongHoc() {
	super();
}
@Override
public String toString() {
	return this.TenPH;
}

}
