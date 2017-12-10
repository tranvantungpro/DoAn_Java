package Moudle;

import java.util.Date;

public class Luong {
private String MaHSL,MaGV;
private String TenGV;
private int HsoL;
private int LuongCb;

public String getMaHSL() {
	return MaHSL;
}

public void setMaHSL(String maHSL) {
	MaHSL = maHSL;
}

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

public int getHsoL() {
	return HsoL;
}

public void setHsoL(int hsoL) {
	HsoL = hsoL;
}

public int getLuongCb() {
	return LuongCb;
}

public void setLuongCb(int luongCb) {
	LuongCb = luongCb;
}

public Luong(String maGV, String tenGV, int hsoL, int luongCb) {
	super();
	MaGV = maGV;
	TenGV = tenGV;
	HsoL = hsoL;
	LuongCb = luongCb;
}

public Luong() {
	super();
}

}
