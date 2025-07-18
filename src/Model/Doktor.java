package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doktor extends Kullanici {
	Statement st = null;
	ResultSet rs = null;
	Kullanici obj;
	Connection con = null;
	PreparedStatement preparedStatement = null;

	
	//Ekleme methodu
	public boolean calismaSaatiEkle(int doktorId, String doktorAdi, String tarih)
			throws SQLException, ClassNotFoundException {

		Connection con = jdbc.jdbcExample.createConnection();

		int key = 0;
		int count = 0;
		String sorgu = "INSERT INTO doktorcalisma" + "(doktorId, doktorAd,tarih) VALUES" + "(?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM doktorcalisma WHERE durum = 'M' AND doktorId  = " + doktorId + " AND tarih = '" + tarih + "'");
			while (rs.next()) {
				count++;
				break; 
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(sorgu);
				preparedStatement.setInt(1, doktorId);
				preparedStatement.setString(2, doktorAdi);
				preparedStatement.setString(3, tarih);
				preparedStatement.executeUpdate();

			}
			key = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}
	
	//Listeleme methodu
	public ArrayList<Whour> getWhourList(int doktorId) throws SQLException, ClassNotFoundException{
		Connection con= jdbc.jdbcExample.createConnection();
		ArrayList<Whour> list = new ArrayList<>();
		Whour obj;
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT *FROM  doktorcalisma WHERE durum= 'M' AND doktorId =" + doktorId);
			while(rs.next()) {
				obj= new Whour();
				obj.setId(rs.getInt("id"));
				obj.setCalisanId(rs.getInt("doktorId"));
				obj.setCalisanAdi(rs.getString("doktorAd"));
				obj.setWdate(rs.getString("tarih"));
				obj.setDurum(rs.getString("durum"));
				list.add(obj);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	
	
	public boolean Sil(int id) throws SQLException, ClassNotFoundException {
		String sorgu = "DELETE FROM doktorcalisma WHERE id =? ";
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
	
	public boolean Gorevlendir(int hemsireId, int hastaId) throws SQLException, ClassNotFoundException {

		String kontrolSorgusu= "SELECT COUNT(*) FROM gorevlihemsire WHERE hemsireId =? AND hastaId=? ";
		String eklemeSorgusu="INSERT INTO gorevlihemsire (hemsireId,hastaId) VALUES (?,?)";
		boolean key= false;
		
		try(Connection con = jdbc.jdbcExample.createConnection();
			PreparedStatement kontrolStmt = con.prepareStatement(kontrolSorgusu);
			PreparedStatement eklemeStmt = con.prepareStatement(eklemeSorgusu)) {
			
			//Kontol Sorgusu
			kontrolStmt.setInt(1, hemsireId);
			kontrolStmt.setInt(2,hastaId);
			try(ResultSet rs= kontrolStmt.executeQuery()){
				rs.next();
				int count = rs.getInt(1);
				
				if( count == 0) { //eger kayit yoksa
					//Ekleme sorgusu
					eklemeStmt.setInt(1,hemsireId);
					eklemeStmt.setInt(2, hastaId);
					eklemeStmt.executeUpdate();
					key=true;
				}
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return key;
	}

	public Doktor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doktor(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru, String bolum,
			String yapilanIs) {
		super(id, tcNo, password, kullaniciAdi, kullaniciTuru, bolum, yapilanIs);
	}

	public Doktor(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru, String bolum) {
		super(id, tcNo, password, kullaniciAdi, kullaniciTuru, bolum);
	}

}
