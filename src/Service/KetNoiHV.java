package Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.GiangVien;
import Moudle.HocVien;
import Moudle.LopHoc;
import Moudle.HocVien;
import Moudle.HocVien;

public class KetNoiHV extends KetNoiSQL
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	
	public ArrayList<HocVien> LayToanBoHocVien()
	{
		ArrayList<HocVien> dsGT= new ArrayList<HocVien>();
		try 
		{ 
			String sql ="select * from HocVien ORDER BY MaHV ASC";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				HocVien lh = new HocVien();
				lh.setMaHV(result.getString(1));
				lh.setTenHV(result.getString(2));
				lh.setNgaySinh(result.getDate(3));
				lh.setDiaChi(result.getString(4));
				lh.setSDT(result.getString(5));
				lh.setEmail(result.getString(6)); 
				lh.setMaLH( result.getString(7)); 
				lh.setTrangThai(result.getString(8)); 
				dsGT.add(lh);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dsGT;
	}
	
	public String LayTenLopHoc(String string) 
	{
		KetNoiLH knlh = new KetNoiLH(); 
		return  knlh.LayTenLopHoc1(string);
				
	}

	public String LayMaHVien()
	{
		try 
		{
			String sql = "Select TOP 1 MaHV from HocVien ORDER BY MaHV DESC";
			preStatement = conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				HocVien gt = new HocVien();
				gt.setMaHV(result.getString(1)); 
				return gt.getMaHV();
			}
		}
		catch (Exception e) 
		{
			  e.printStackTrace();
		}
		return null;
	}

	public int  ThemMoiHocVien(HocVien lh)
	{
		try
		{
			String sql= "insert into HocVien VALUES (?,?,?,?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, lh.getMaHV());
			preStatement.setString(2, lh.getTenHV());
			preStatement.setDate(3, (Date) lh.getNgaySinh());
			preStatement.setString(4, lh.getDiaChi());
			preStatement.setString(5, lh.getSDT());
			preStatement.setString(6, lh.getEmail());  
			preStatement.setString(7, "");  
			preStatement.setString(8, lh.getTrangThai());  
			
			return preStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<HocVien> TimHocVien(String tenLH)
	{
		ArrayList<HocVien> dsGTTim= new ArrayList<HocVien>();
		try
		{
			String sql= "select * from HocVien where TenHV like concat('%', ?, '%') collate sql_latin1_general_cp1_ci_as";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenLH);
			result=preStatement.executeQuery();
			while(result.next())
			{
				HocVien lh = new HocVien();
				lh.setMaHV(result.getString(1));
				lh.setTenHV(result.getString(2));
				lh.setNgaySinh(result.getDate(3));
				lh.setDiaChi(result.getString(4));
				lh.setSDT(result.getString(5));
				lh.setEmail(result.getString(6));
				lh.setMaLH(result.getString(7)); 
				lh.setTrangThai(result.getString(8)); 
				dsGTTim.add(lh);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsGTTim;
	}
	
	public int  XoaHocVien(HocVien gt)
	{
		try
		{
			String sql= "delete from HocVien where MaHV=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gt.getMaHV());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  CapNhatHocVien(HocVien gt)
	{
		try
		{
			String sql= "update HocVien set TenHV=?, NgaySinh=?, DiaChi=?, SDT=?, Email=?, TrangThai=? where MaHV=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gt.getTenHV());
			preStatement.setDate(2, (Date) gt.getNgaySinh());
			preStatement.setString(3, gt.getDiaChi());
			preStatement.setString(4, gt.getSDT());
			preStatement.setString(5,gt.getEmail());
			preStatement.setString(6, gt.getTrangThai()); 
			preStatement.setString(7,gt.getMaHV()); 
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  BaoLuuHoVien(HocVien gt)
	{
		try
		{
			String sql= "update HocVien set  TrangThai=? where MaHV=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gt.getTrangThai()); 
			preStatement.setString(2,gt.getMaHV()); 
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int KiemTraTonTai(String TenGT)
	{
		try 
		{
			String sql= "select * from HocVien where TenHV =?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, TenGT);
			result=preStatement.executeQuery();
			while(result.next())
			{
				return 1;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public Vector<HocVien> docToanBoHocVien()
	{
		Vector<HocVien> vec=new Vector<HocVien>();
		try
		{
			String sql="select * from HocVien";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				HocVien dm=new HocVien();
				dm.setMaHV(result.getString(1));
				dm.setTenHV(result.getString(2)); 
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public int ChuyenLop(String hv, String lh)
	{
		try
		{
			String sql= "update HocVien set MaLH=? where TenHV=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, lh);
			preStatement.setString(2, hv); 
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public HocVien LayMaHvien1(String tengv)
	{
		try 
		{
			String sql = "Select * from HocVien where TenHV=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tengv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				HocVien gt = new HocVien();
				gt.setMaHV(result.getString(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}

	 
}
