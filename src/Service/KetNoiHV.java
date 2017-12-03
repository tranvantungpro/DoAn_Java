package Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.HocVien;
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
				lh.setMaLH(result.getString(7)); 
				dsGT.add(lh);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dsGT;
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
			String sql= "insert into HocVien VALUES (?,?,?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, lh.getMaHV());
			preStatement.setString(2, lh.getTenHV());
			preStatement.setDate(3, (Date) lh.getNgaySinh());
			preStatement.setString(4, lh.getDiaChi());
			preStatement.setString(5, lh.getSDT());
			preStatement.setString(6, lh.getEmail());  
			preStatement.setString(7, "");  
			
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
			String sql= "update HocVien set TenHV=?, NgaySinh=?, DiaChi=?, SDT=?, Email=? where MaHV=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gt.getTenHV());
			preStatement.setDate(2, (Date) gt.getNgaySinh());
			preStatement.setString(3, gt.getDiaChi());
			preStatement.setString(4, gt.getSDT());
			preStatement.setString(5,gt.getEmail()); 
			preStatement.setString(6,gt.getMaHV()); 
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
			String sql= "select * from HocVien where TenLH =?";
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
	
	public int XepLop(String gv, String lh)
	{
		try
		{
			String sql= "update HocVien set MaGV=? where TenLH=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1,  gv);
			preStatement.setString(2, lh); 
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
}
