package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import Moudle.CTH;
import Moudle.GiangVien;
import Moudle.LopHoc;

public class KetNoiLH extends KetNoiSQL
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	//phần này để demo
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	
	public ArrayList<LopHoc> LayToanBoLopHoc()
	{
		ArrayList<LopHoc> dsGT= new ArrayList<LopHoc>();
		try 
		{
			String sql ="select * from LopHoc ORDER BY MaLH ASC";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LopHoc lh = new LopHoc();
				lh.setMaLH(result.getString(1));
				lh.setTenLH(result.getString(2));
				lh.setLoaiLH(result.getString(3));
				lh.setMaCTH(result.getString(4));
				lh.setSoBuoi(result.getInt(5));
				lh.setNgayBD(result.getDate(6));
				lh.setNgayKT(result.getDate(7));
				lh.setMaGV(result.getString(8));
				dsGT.add(lh);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dsGT;
	}
	
	public ArrayList<LopHoc> TimLopHoc(String tenLH)
	{
		ArrayList<LopHoc> dsGTTim= new ArrayList<LopHoc>();
		try
		{
			String sql= "select * from LopHoc where TenLH like concat('%', ?, '%') collate sql_latin1_general_cp1_ci_as";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenLH);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LopHoc lh = new LopHoc();
				lh.setMaLH(result.getString(1));
				lh.setTenLH(result.getString(2));
				lh.setLoaiLH(result.getString(3));
				lh.setMaCTH(result.getString(4));
				lh.setSoBuoi(result.getInt(5));
				lh.setNgayBD(result.getDate(6));
				lh.setNgayKT(result.getDate(7));
				lh.setMaGV(result.getString(8));
				dsGTTim.add(lh);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsGTTim;
	}
	
	public int  ThemMoiLopHoc(LopHoc lh)
	{
		try
		{
			String sql= "insert into LopHoc VALUES (?,?,?,?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, lh.getMaLH());
			preStatement.setString(2, lh.getTenLH());
			preStatement.setString(3, lh.getLoaiLH());
			preStatement.setString(4, lh.getMaCTH());
			preStatement.setInt(5, lh.getSoBuoi());
			preStatement.setDate(6, (Date) lh.getNgayBD());
			preStatement.setDate(7, (Date) lh.getNgayKT());
			preStatement.setString(8, lh.getMaGV());
			
			return preStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  XoaLopHoc(LopHoc gt)
	{
		try
		{
			String sql= "delete from LopHoc where MaLH=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gt.getMaLH());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  CapNhatLopHoc(LopHoc gt)
	{
		try
		{
			String sql= "update LopHoc set TenLH=?, LoaiLH=?, MaCTH=?, SoBuoi=?, NgayBD=?, NgayKT=?, MaGV=? where MaLH=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gt.getTenLH());
			preStatement.setString(2, gt.getLoaiLH());
			preStatement.setString(3, gt.getMaCTH());
			preStatement.setInt(4, gt.getSoBuoi());
			preStatement.setDate(5, (Date) gt.getNgayBD());
			preStatement.setDate(6, (Date) gt.getNgayKT());
			preStatement.setString(7, gt.getMaGV());
			preStatement.setString(8, gt.getMaLH());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public LopHoc LayMaLH()
	{
		try 
		{
			String sql = "Select TOP 1 MaLH from LopHoc ORDER BY MaLH DESC";
			preStatement = conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LopHoc gt = new LopHoc();
				gt.setMaLH(result.getString(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
	
	public LopHoc LayMalophoc(String tenlh)
	{
		try 
		{
			String sql = "Select * from LopHoc Where TenLH=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenlh);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LopHoc gt = new LopHoc();
				gt.setMaLH(result.getString(1));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
	
	public int KiemTraTonTai(String TenGT)
	{
		try 
		{
			String sql= "select * from LopHoc where TenLH =?";
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
	
	public Vector<LopHoc> docToanBoLopHoc()
	{
		Vector<LopHoc> vec=new Vector<LopHoc>();
		try
		{
			String sql="select * from LopHoc";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				LopHoc dm=new LopHoc();
				dm.setMaLH(result.getString(1));
				dm.setTenLH(result.getString(2)); 
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
			String sql= "update LopHoc set MaGV=? where TenLH=? ";
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

	public Vector<LopHoc> HienThiLopHocTheoMa(String malh)
	{
		Vector<LopHoc> vec=new Vector<LopHoc>();
		try
		{
			String sql="select * from LopHoc where MaLH=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, malh);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LopHoc dm=new LopHoc();
				dm.setMaLH(result.getString(1));
				dm.setTenLH(result.getString(2));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public String LayTenLopHoc1(String malh)
	{
		try 
		{
			
			String sql = "Select * from LopHoc where MaLH=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, malh);
			result=preStatement.executeQuery();
			while(result.next())
			{
				LopHoc gt = new LopHoc();
				gt.setMaLH(result.getString(1));
				gt.setTenLH(result.getString(2)); 
				//JOptionPane.showMessageDialog(null, "ten:"+gt.getTenLH()); // nó chưa chạy tới cái này
				return gt.getTenLH();
			}
		}
		catch (Exception e) 
		{
			  
		}
		return "";
	}
}
