package Moudle;

import java.util.Vector;

public class PhongBan {
private String MaPB;
private String TenPB;

public String getMaPB() {
	return MaPB;
}
public void setMaPB(String maPB) {
	MaPB = maPB;
}
public String getTenPB() {
	return TenPB;
}
public void setTenPB(String tenPB) {
	TenPB = tenPB;
}

public PhongBan(String maPB, String tenPB) {
	super();
	MaPB = maPB;
	TenPB = tenPB;
}
public PhongBan() {
	super();
}
@Override
public String toString() {
	return this.TenPB;
}

}
