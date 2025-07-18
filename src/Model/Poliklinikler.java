package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import Yardimci.Yardimci;

public class Poliklinikler extends Kullanici {
	private int id;
	private String pkAdi;
	Statement st = null;
	ResultSet rs = null;
	Kullanici obj;
	Yardimci yardimci;

	Connection con = null;
	PreparedStatement preparedStatement = null;

	// Consturctorlar
	public Poliklinikler() {
	}

	public Poliklinikler(int id, String pkAdi) {
		super();
		this.id = id;
		this.pkAdi = pkAdi;
	}

	// Veri alma
	public ArrayList<Poliklinikler> getList() throws SQLException, ClassNotFoundException {

		ArrayList<Poliklinikler> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();
		Poliklinikler obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM poliklinikler ");
			while (rs.next()) {
				obj = new Poliklinikler(rs.getInt("id"), rs.getString("poliklinikAdi"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}


	// ekleme metodu
	public boolean Ekle(String plkAdi) throws SQLException, ClassNotFoundException {
		String sorgu = "INSERT INTO poliklinikler " + "(poliklinikAdi) VALUES" + "(?)";
		boolean key = false;
		Connection con = jdbc.jdbcExample.createConnection();

		try {

			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1, plkAdi);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLIntegrityConstraintViolationException ex) {
			// Mevcut polikliniki tekrar ekleme hatasi
			yardimci.showMessage("unique");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}

	// Silme methodu
	public boolean Sil(String Name) throws SQLException, ClassNotFoundException {
		String sorgu = "DELETE FROM poliklinikler WHERE poliklinikAdi =? ";
		boolean key = false;
		Connection con = jdbc.jdbcExample.createConnection();

		try {

			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1, Name);
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
	public boolean guncelle(int id, String plkAdi) throws SQLException, ClassNotFoundException {
		String sorgu = "UPDATE poliklinikler SET  poliklinikAdi=? WHERE id=?";
		boolean key = false;
		Connection con = jdbc.jdbcExample.createConnection();

		try {

			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1, plkAdi);
			preparedStatement.setInt(2, id);
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

	public Poliklinikler getFetch(int id) throws SQLException, ClassNotFoundException {
		Connection con = jdbc.jdbcExample.createConnection();
		Poliklinikler c = new Poliklinikler();
		st = con.createStatement();
		rs = st.executeQuery("SELECT *FROM  poliklinikler WHERE id =" + id);
		while (rs.next()) {
			c.setId(rs.getInt("id"));
			c.setPkAdi(rs.getString("poliklinikAdi"));
			break;
		}
		return c;
	}

	// klinik secimine gore doktor listesi
	public ArrayList<Kullanici> getKlinikDkList(int klinikId) throws SQLException, ClassNotFoundException {

		ArrayList<Kullanici> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();

		try {
			st = con.createStatement();
			// kullanici tqablosundaki bilgileri calisanlar tablosunu kullanarak cektik
			rs = st.executeQuery(
					"SELECT k.id, k.tcNo, k.password, k.kullaniciAdi, k.kullaniciTuru, k.bolum " + "FROM calisanlar c "
							+ "LEFT JOIN kullanici k ON c.kullaniciId = k.id " + "WHERE c.klinikId = " + klinikId);
			while (rs.next()) {
				obj = new Kullanici(rs.getInt("k.id"), rs.getString("k.tcNo"), rs.getString("k.password"),
						rs.getString("k.kullaniciAdi"), rs.getString("k.kullaniciTuru"), rs.getString("k.bolum"));
				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	// Getter-Setter
	public String getPkAdi() {
		return pkAdi;
	}

	public void setPkAdi(String pkAdi) {
		this.pkAdi = pkAdi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
