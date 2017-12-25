package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


import Moudle.GiangVien;



import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
public class KetNoiGV extends KetNoiSQL
{
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	public Vector<GiangVien> docToanBoDanhMuc()
	{
		Vector<GiangVien> vec=new Vector<GiangVien>();
		try
		{
			String sql="select * from GiangVien";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				GiangVien dm=new GiangVien();
				dm.setMaGV(result.getString(1));
				dm.setTenGV(result.getString(2)); 
				dm.setDiaChi(result.getString(3));
				dm.setSdt(result.getString(4));
				dm.setHsl(result.getFloat(5));
				dm.setLuongCb(result.getFloat(6));	
				dm.setNgayVL(result.getDate(7));
				dm.setNgayKT(result.getDate(8));
				dm.setSoLop(result.getInt(9));
				dm.setMaTT(result.getString(10));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public ArrayList<GiangVien> LayToanBoGV()
	{
		ArrayList<GiangVien> vec=new ArrayList<GiangVien>();
		try
		{
			String sql="select * from GiangVien ";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien dm=new GiangVien();
				dm.setMaGV(result.getString(1));
				dm.setTenGV(result.getString(2));
				dm.setSdt(result.getString(3));
				dm.setDiaChi(result.getString(4));
				dm.setHsl(result.getFloat(5));
				dm.setLuongCb(result.getFloat(6));	
				dm.setNgayVL(result.getDate(7));
				dm.setNgayKT(result.getDate(8));
				dm.setSoLop(result.getInt(9));
				dm.setMaTT(result.getString(10));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	
	public ArrayList<GiangVien> TimGiangVien(String tenGV)
	{
		ArrayList<GiangVien> dsGv= new ArrayList<GiangVien>();
		try
		{
			String sql= "select * from GiangVien where TenGV like concat('%', ?, '%') collate sql_latin1_general_cp1_ci_as";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenGV);
			result=preStatement.executeQuery();
			while(result.next())
			{
				
				GiangVien gv=new GiangVien();
				gv.setMaGV(result.getString(1));
				gv.setTenGV(result.getString(2)); 
				gv.setSdt(result.getString(3));
				gv.setDiaChi(result.getString(4));			
				gv.setHsl(result.getFloat(5));
				gv.setLuongCb(result.getFloat(6));	
				gv.setNgayVL(result.getDate(7));
				gv.setNgayKT(result.getDate(8));
				gv.setSoLop(result.getInt(9));
				gv.setMaTT(result.getString(9));
				dsGv.add(gv);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsGv;
	}
	public int  ThemMoiGiangVien(GiangVien gv)
	{
		try
		{
			String sql= "insert into GiangVien VALUES (?,?,?,?,?,?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gv.getMaGV());
			preStatement.setString(2, gv.getTenGV());
			preStatement.setString(3, gv.getSdt());
			preStatement.setFloat(4, gv.getHsl());
			preStatement.setFloat(5, gv.getLuongCb());
			preStatement.setString(6, gv.getDiaChi());
			preStatement.setDate(7,(Date)  gv.getNgayVL());
			preStatement.setDate(8, (Date) gv.getNgayKT());
			preStatement.setInt(9, gv.getSoLop());
			preStatement.setString(10, gv.getMaTT());
			return preStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  XoaGiangVien(GiangVien gv)
	{
		try
		{
			String sql= "delete from GiangVien where MaGV=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, gv.getMaGV());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  CapNhatGiangVien(GiangVien gv)
	{
		try
		{
			
			String sql= "update GiangVien set TenGV=?, Sdt=?, DiaChi=?,HeSoLuong=?,LuongCB=?, NgayVaoLam=?,NgayKetThuc=?,SoLop=?,MaTT=? where MaGV=?";
			preStatement = conn.prepareStatement(sql);			
			preStatement.setString(1, gv.getTenGV());
			preStatement.setString(2, gv.getSdt());
			preStatement.setString(3, gv.getDiaChi());
			preStatement.setFloat(4, gv.getHsl());
			preStatement.setFloat(5, gv.getLuongCb());
			preStatement.setDate(6, (Date) gv.getNgayVL());
			preStatement.setDate(7, (Date) gv.getNgayKT());
			preStatement.setString(8, gv.getMaTT());
			preStatement.setInt(9, gv.getSoLop());
			preStatement.setString(10, gv.getMaGV());		
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	
	public GiangVien LayMaGV(String tengv)
	{
		try 
		{
			String sql = "Select * from GiangVien where TenGV=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tengv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien gt = new GiangVien();
				gt.setMaGV(result.getString(1));
				gt.setSoLop(result.getInt(9));
				return gt;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}
	
	public String LayTenGV(String magv) {
		try 
		{
			String sql = "Select * from GiangVien where MaGV=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, magv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien gt = new GiangVien();
				gt.setMaGV(result.getString(1));
				gt.setTenGV(result.getString(2)); 
				return gt.getTenGV();
			}
		}
		catch (Exception e) 
		{
			  
		}
		return ""; 
	}

	public GiangVien LayMaGV() {
		// TODO Auto-generated method stub
		try 
		{
			String sql = "Select TOP 1 MaGV from GiangVien ORDER BY MaGV DESC";
			preStatement = conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien ns = new GiangVien();
				ns.setMaGV(result.getString(1));
				return ns;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
	}

	public int KiemTraTonTai(String TenGV) {
		// TODO Auto-generated method stub
		try 
		{
			String sql= "select * from GiangVien where TenGV =?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, TenGV);
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

	public Vector<GiangVien> hienThiGVLenCbo(String magv) {
		// TODO Auto-generated method stub
		Vector<GiangVien> vec=new Vector<GiangVien>();
		try
		{
			String sql="select * from GiangVien where MaGV=?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, magv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien dm=new GiangVien();
				dm.setMaGV(result.getString(1));
				dm.setTenGV(result.getString(2)); 
//				dm.setSdt(result.getString(4));
//				dm.setDiaChi(result.getString(3));
//				dm.setHsl(result.getFloat(5));
//				dm.setLuongCb(result.getFloat(6));	
//				dm.setNgayVL(result.getDate(7));
//				dm.setNgayKT(result.getDate(8));
				vec.add(dm);
			}			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
		
	}

	public int CapNhatSoLopGiangVien(String magv, int solop) 
	{
		try
		{
			
			String sql= "update GiangVien set  SoLop=? where MaGV=?";
			preStatement = conn.prepareStatement(sql);			
			preStatement.setInt(1, solop);
			preStatement.setString(2, magv); 
			
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public GiangVien SoLopHT(String tengv) {
		try 
		{
			String sql= "select * from GiangVien where TenGV =?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tengv);
			result=preStatement.executeQuery();
			while(result.next())
			{
				GiangVien a = new GiangVien();
				a.setMaGV(result.getString(1));
				a.setSoLop(result.getInt(9));
				return a;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	

	}
	
	
