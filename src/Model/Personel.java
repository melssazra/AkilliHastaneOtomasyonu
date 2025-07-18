package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Personel extends Kullanici{
	Statement st = null;
	ResultSet rs = null;
	Kullanici obj;
	Connection con = null;
	PreparedStatement preparedStatement = null;
	
	public boolean calismaSaatiEkle(int prsId, String prsAdi,String gorev, String tarih)
			throws SQLException, ClassNotFoundException {

		Connection con = jdbc.jdbcExample.createConnection();

		int key = 0;
		int count = 0;
		String sorgu = "INSERT INTO personelcalisma" + "(prsId, prsAdi,gorev,tarih) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM personelcalisma WHERE durum = 'M' AND prsId=" + prsId + " AND tarih = '" + tarih + "'");
			while (rs.next()) {
				count++;
				break;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(sorgu);
				preparedStatement.setInt(1, prsId);
				preparedStatement.setString(2,prsAdi);
				preparedStatement.setString(3,gorev);
				preparedStatement.setString(4, tarih);
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
		public ArrayList<Whour> getWhourList(int prsId) throws SQLException, ClassNotFoundException{
			Connection con= jdbc.jdbcExample.createConnection();
			ArrayList<Whour> list = new ArrayList<>();
			Whour obj;
			try {
				st=con.createStatement();
				rs=st.executeQuery("SELECT *FROM  personelcalisma WHERE durum= 'M' AND prsId =" + prsId);
				while(rs.next()) {
					obj= new Whour();
					obj.setId(rs.getInt("id"));
					obj.setCalisanId(rs.getInt("prsId"));
					obj.setCalisanAdi(rs.getString("prsAdi"));
					obj.setGorev(rs.getString("gorev"));
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
			String sorgu = "DELETE FROM personelcalisma WHERE id =? ";
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
	
	public Personel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Personel(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru, String bolum,
			String yapilanIs) {
		super(id, tcNo, password, kullaniciAdi, kullaniciTuru, bolum, yapilanIs);
		// TODO Auto-generated constructor stub
	}
	public Personel(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru, String bolum) {
		super(id, tcNo, password, kullaniciAdi, kullaniciTuru, bolum);
		// TODO Auto-generated constructor stub
	}


}
