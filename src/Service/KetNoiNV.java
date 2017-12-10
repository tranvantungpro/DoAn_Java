package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import Moudle.NhanSu;

public class KetNoiNV extends KetNoiSQLQuyen {
	PreparedStatement preStatement=null;
	ResultSet result=null;
	KetNoiSQLQuyen a = new KetNoiSQLQuyen();
	Connection conn = a.getConnect();
	public Vector<NhanSu> docToanBoDanhMuc()
	{
		Vector<NhanSu> vec=new Vector<NhanSu>();
		try
		{
			String sql="select * from NhanSu";
			Statement statement=conn.createStatement();
			ResultSet result=statement.executeQuery(sql);
			while(result.next())
			{
				NhanSu dm=new NhanSu();
				dm.setMaNV(result.getString(1));
				dm.setTenNV(result.getString(2)); 
				dm.setSdt(result.getString(3));
				dm.setDiaChi(result.getString(4));
				dm.setMaphongban(result.getString(5));
				dm.setNgayVL(result.getDate(6));
				dm.setNgayKT(result.getDate(7));
				vec.add(dm);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public ArrayList<NhanSu> LayToanBoLopNS()
	{
		ArrayList<NhanSu> dsGT= new ArrayList<NhanSu>();
		try 
		{
			String sql ="select * from NhanSu ORDER BY MaNS ASC";
			preStatement=conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				NhanSu ns = new NhanSu();
				ns.setMaNV(result.getString(1));
				ns.setTenNV(result.getString(2)); 
				ns.setSdt(result.getString(3));
				ns.setDiaChi(result.getString(4));
				ns.setMaphongban(result.getString(5));
				ns.setNgayVL(result.getDate(6));
				ns.setNgayKT(result.getDate(7));	
				dsGT.add(ns);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return dsGT;
	}
	
	public ArrayList<NhanSu> TimNhanSu(String tenNS)
	{
		ArrayList<NhanSu> dsNS= new ArrayList<NhanSu>();
		try
		{
			String sql= "select * from NhanSu where TenNS like concat('%', ?, '%') collate sql_latin1_general_cp1_ci_as";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, tenNS);
			result=preStatement.executeQuery();
			while(result.next())
			{
				
				NhanSu ns=new NhanSu();
				ns.setMaNV(result.getString(1));
				ns.setTenNV(result.getString(2)); 
				ns.setSdt(result.getString(3));
				ns.setDiaChi(result.getString(4));
				ns.setMaphongban(result.getString(5));
				ns.setNgayVL(result.getDate(6));
				ns.setNgayKT(result.getDate(7));
				dsNS.add(ns);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dsNS;
	}
	public int  ThemMoiNhanSu(NhanSu ns)
	{
		try
		{
			String sql= "insert into NhanSu VALUES (?,?,?,?,?,?,?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, ns.getMaNV());
			preStatement.setString(2, ns.getTenNV());
			preStatement.setString(3, ns.getSdt());
			preStatement.setString(4, ns.getDiaChi());
			preStatement.setString(5, ns.getMaphongban());
			preStatement.setDate(6,(Date)  ns.getNgayVL());
			preStatement.setDate(7, (Date) ns.getNgayKT());
			
			return preStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  XoaNhanSu(NhanSu ns)
	{
		try
		{
			String sql= "delete from NhanSu where MaNS=? ";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, ns.getMaNV());
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int  CapNhatNhanSu(NhanSu ns)
	{
		try
		{
			String sql= "update NhanSu set TenNS=?, Sdt=?, DiaChi=?, MaPB=?,NgayVaoLam=?,NgayKetThuc=? where MaNS=?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, ns.getTenNV());
			preStatement.setString(2, ns.getSdt());
			preStatement.setString(3, ns.getDiaChi());
			preStatement.setString(4, ns.getMaphongban());
			preStatement.setDate(5,(Date)  ns.getNgayVL());
			preStatement.setDate(6, (Date) ns.getNgayKT());	
			preStatement.setString(7,ns.getMaNV()); 
			return preStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	public int KiemTraTonTai(String TenNV)
	{
		try 
		{
			String sql= "select * from NhanSu where TenNS =?";
			preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, TenNV);
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
	public NhanSu LayMaNS() {
		// TODO Auto-generated method stub
		try 
		{
			String sql = "Select TOP 1 MaNS from NhanSu ORDER BY MaNS DESC";
			preStatement = conn.prepareStatement(sql);
			result=preStatement.executeQuery();
			while(result.next())
			{
				NhanSu ns = new NhanSu();
				ns.setMaNV(result.getString(1));
				return ns;
			}
		}
		catch (Exception e) 
		{
			  
		}
		return null;
		
	}
}
