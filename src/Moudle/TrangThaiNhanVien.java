package Moudle;

public class TrangThaiNhanVien {
private String MaTT,LoaiTT;

public String getMaTT() {
	return MaTT;
}

public void setMaTT(String maTT) {
	MaTT = maTT;
}

public String getLoaiTT() {
	return LoaiTT;
}

public void setLoaiTT(String loaiTT) {
	LoaiTT = loaiTT;
}

public TrangThaiNhanVien(String maTT, String loaiTT) {
	super();
	MaTT = maTT;
	LoaiTT = loaiTT;
}

public TrangThaiNhanVien() {
	super();
}

@Override
public String toString() {
	return this.LoaiTT;
}

}
