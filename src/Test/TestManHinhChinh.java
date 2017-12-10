package Test;

import UI.MHChuyenLop;
import UI.MHQuanLyGiaoTrinh;
import UI.MHQuanLyHocVien;
import UI.MHQuanLyLopHoc;
import UI.MHXepLichGV;
import UI.ManHinhChinh;
import UI.ManHinhLuogGV;
import UI.ManHinhPhongHoc;
import UI.ManHinhQuanLiGiangVien;
import UI.ManHinhQuanLiNhanVien;
import UI.ManHinhXemLichGD;

public class TestManHinhChinh 
{
	public static void main(String[] args) 
	{
//		ManHinhChinh manhinh = new ManHinhChinh("Phần mềm quản lý trung tâm tin học");
//		manhinh.showWindows();
		
//		MHQuanLyGiaoTrinh ui = new MHQuanLyGiaoTrinh("Quản Lý Giáo Trình");
//		ui.showWindow();
//		
		ManHinhQuanLiGiangVien ui = new ManHinhQuanLiGiangVien("Lịch dạy");
				ui.showWindow();
//		MHXepLichGV ui = new MHXepLichGV("Xếp Lịch Giảng Viên");
//		ui.ShowWindow();
		
//		MHQuanLyHocVien hocvien = new MHQuanLyHocVien("Quản Lý học Viên");
//		hocvien.showWindow();
//		MHChuyenLop cl = new MHChuyenLop("Chuyển Lớp");
//		cl.ShowWindow();
	}
}
