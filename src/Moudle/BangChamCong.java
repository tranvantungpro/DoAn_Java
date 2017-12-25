package Moudle;

import java.util.Date;

public class BangChamCong {
private String MaCC,MaGV;
private String MaTTCC;

private int MaTG;
private Date NgayCC;

public String getMaTTCC() {
	return MaTTCC;
}
public void setMaTTCC(String maTTCC) {
	MaTTCC = maTTCC;
}
public int getMaTG() {
	return MaTG;
}
public void setMaTG(int maTG) {
	MaTG = maTG;
}
public String getMaCC() {
	return MaCC;
}
public void setMaCC(String maCC) {
	MaCC = maCC;
}
public String getMaGV() {
	return MaGV;
}
public void setMaGV(String maGV) {
	MaGV = maGV;
}

public Date getNgayCC() {
	return NgayCC;
}
public void setNgayCC(Date ngayCC) {
	NgayCC = ngayCC;
}

public BangChamCong(String maCC, String maGV, String maTTCC, int maTG, Date ngayCC) {
	super();
	MaCC = maCC;
	MaGV = maGV;
	MaTTCC = maTTCC;
	MaTG = maTG;
	NgayCC = ngayCC;
}
public BangChamCong() {
	super();
}


}
