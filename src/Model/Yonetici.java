package Model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Ilac;
import jdbc.jdbcExample;

//kalitimla olusturulan sinif
public class Yonetici extends Kullanici {
	
	Statement st = null;
	ResultSet rs = null;
	Kullanici obj;
	Connection con = null;
	PreparedStatement preparedStatement = null;
	Ilac ilac = new Ilac();

	public Yonetici(int id, String tcNo, String password, String kullaniciTuru, String kullaniciAdi,String bolum) {
		super(id, tcNo, password, kullaniciTuru, kullaniciAdi,bolum);

		// TODO Auto-generated constructor stub
	}

	public Yonetici() {
	}

	// tabloya veri alma
	public ArrayList<Kullanici> getDoktorList() throws SQLException, ClassNotFoundException {

		ArrayList<Kullanici> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kullanici WHERE KullaniciTuru = 'doktor'");
			while (rs.next()) {
				obj = new Kullanici(rs.getInt("id"), rs.getString("tcNo"), rs.getString("password"),
						rs.getString("kullaniciAdi"), rs.getString("kullaniciTuru"),rs.getString("bolum"));
				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public ArrayList<Kullanici> getPersonelList() throws SQLException, ClassNotFoundException {
		ArrayList<Kullanici> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kullanici WHERE KullaniciTuru = 'personel'");
			while (rs.next()) {
				obj = new Kullanici(rs.getInt("id"), rs.getString("tcNo"), rs.getString("password"),
						rs.getString("kullaniciAdi"), rs.getString("kullaniciTuru"),rs.getString("bolum"));
				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	public ArrayList<Kullanici> getHemsireList() throws SQLException, ClassNotFoundException {
		ArrayList<Kullanici> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kullanici WHERE KullaniciTuru = 'personel' AND bolum ='hemsire'");
			while (rs.next()) {
				obj = new Kullanici(rs.getInt("id"), rs.getString("tcNo"), rs.getString("password"),
						rs.getString("kullaniciAdi"), rs.getString("kullaniciTuru"),rs.getString("bolum"));
				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	
	
	

	// ekleme metodu
	public boolean Ekle(String tcNo, String password, String kullaniciTuru, String kullaniciAdi, String bolum)
			throws SQLException, ClassNotFoundException {
		String sorgu = "INSERT INTO kullanici " + "(tcNo,password,kullaniciTuru,kullaniciAdi,bolum) VALUES"
				+ "(?,?,?,?,?)";
		boolean key = false;
		Connection con = jdbc.jdbcExample.createConnection();

		try {

			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1, tcNo);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, kullaniciTuru);
			preparedStatement.setString(4, kullaniciAdi);
			preparedStatement.setString(5, bolum);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	// Silme methodu
	public boolean Sil(int id) throws SQLException, ClassNotFoundException {
		String sorgu = "DELETE FROM kullanici WHERE id =? ";
		boolean key = false;
		Connection con = jdbc.jdbcExample.createConnection();

		try {

			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	// guncelleme methodu
	public boolean guncelle(int id, String tcNo, String password, String kullaniciAdi)
			throws SQLException, ClassNotFoundException {
		String sorgu = "UPDATE kullanici SET tcNo=?,password =? , kullaniciAdi=? WHERE id=?";
		boolean key = false;
		Connection con = jdbc.jdbcExample.createConnection();

		try {

			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1, tcNo);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, kullaniciAdi);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	// Atama metodu
	public boolean Ata(int doktorId, int klinikId) throws SQLException, ClassNotFoundException {

		String kontrolSorgusu= "SELECT COUNT(*) FROM calisanlar WHERE klinikId =? AND kullaniciId=? ";
		String eklemeSorgusu="INSERT INTO calisanlar (kullaniciId,klinikId) VALUES (?,?)";
		boolean key= false;
		
		try(Connection con = jdbc.jdbcExample.createConnection();
			PreparedStatement kontrolStmt = con.prepareStatement(kontrolSorgusu);
			PreparedStatement eklemeStmt = con.prepareStatement(eklemeSorgusu)) {
			
			//Kontol Sorgusu
			kontrolStmt.setInt(1, klinikId);
			kontrolStmt.setInt(2,doktorId);
			try(ResultSet rs= kontrolStmt.executeQuery()){
				rs.next();
				int count = rs.getInt(1);
				
				if( count == 0) { //eger kayit yoksa
					//Ekleme sorgusu
					eklemeStmt.setInt(1,doktorId);
					eklemeStmt.setInt(2, klinikId);
					eklemeStmt.executeUpdate();
					key=true;
				}
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return key;
	}
	
	//klinik secimine gore doktor listesi
	public ArrayList<Kullanici> getKlinikDkList(int klinikId) throws SQLException, ClassNotFoundException {

		ArrayList<Kullanici> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();

		try {
			st = con.createStatement();
			//kullanici tqablosundaki bilgileri calisanlar tablosunu kullanarak cektik
			rs = st.executeQuery("SELECT k.id, k.tcNo, k.password, k.kullaniciAdi, k.kullaniciTuru, k.bolum " +
				    "FROM calisanlar c " +
				    "LEFT JOIN kullanici k ON c.kullaniciId = k.id " +
				    "WHERE c.klinikId = " + klinikId);
			while (rs.next()) {
				obj = new Kullanici(rs.getInt("k.id"), rs.getString("k.tcNo"),rs.getString("k.password"), rs.getString("k.kullaniciAdi"),
						rs.getString("k.kullaniciTuru"),rs.getString("k.bolum"));
				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
	
	
}



