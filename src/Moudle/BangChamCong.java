package Moudle;

import java.util.Date;

public class BangChamCong {
private String MaCC,MaGV;
private String LyDo;
private int Ca;
private Date NgayCC;
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
public String getLyDo() {
	return LyDo;
}
public void setLyDo(String lyDo) {
	LyDo = lyDo;
}
public int getCa() {
	return Ca;
}
public void setCa(int ca) {
	Ca = ca;
}
public Date getNgayCC() {
	return NgayCC;
}
public void setNgayCC(Date ngayCC) {
	NgayCC = ngayCC;
}
public BangChamCong(String maCC, String maGV, String lyDo, int ca, Date ngayCC) {
	super();
	MaCC = maCC;
	MaGV = maGV;
	LyDo = lyDo;
	Ca = ca;
	NgayCC = ngayCC;
}
public BangChamCong() {
	super();
}
@Override
public String toString() {
	return this.LyDo;
}


}
