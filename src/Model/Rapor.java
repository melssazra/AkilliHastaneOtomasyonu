package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class Rapor extends Hasta {

	private String hemoglabin;
	private String vitamin;
	private String gebelik;
	private String enfeksiyon;
	private String seker;
	private String mrIsteme;
	private String mrAnaliz;
	private String dkGorus;

	Statement st = null;
	ResultSet rs = null;
	Connection con = null;
	Kullanici obj;
	PreparedStatement preparedStatement = null;

	public ArrayList<Rapor> getHastaSonucList(int hastaId) throws SQLException, ClassNotFoundException {
		ArrayList<Rapor> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();
		Rapor obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM rapor WHERE hastaId =" + hastaId);
			while (rs.next()) {
				obj = new Rapor();
				obj.setHemoglabin(rs.getString("hemoglabin"));
				obj.setVitamin(rs.getString("vitamin"));
				obj.setGebelik(rs.getString("gebelik"));
				obj.setSeker(rs.getString("seker"));
				obj.setMrAnaliz(rs.getString("mrAnaliz"));
				obj.setDkGorus(rs.getString("dkGorus"));
				list.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public boolean randevuEkle(int hastaId, int doktorId, String dkAdi, String hemoglabinStr, String vitamin,
			String gebelikStr, String enfeksiyonStr, String sekerStr, String mrIstemeStr, String mrAnaliz,
			String dkGorus) throws SQLException, ClassNotFoundException {

		String hmg, gb, fk, sk, mr;
		// switch-case yapisiyla tabloda atadigimiz degerleri sql'de tablomuzda
		// tutuldugu sekle göre duzenledik
		hmg = hemoglabinStr.equals("Normal") ? "N" : hemoglabinStr.equals("Dusuk") ? "D" : "Y";
		gb = gebelikStr.equals("Var") ? "V" : "Y";
		fk = enfeksiyonStr.equals("Var") ? "V" : "Y";
		sk = sekerStr.equals("Normal") ? "N" : sekerStr.equals("Dusuk") ? "R" : sekerStr.equals("Yuksek") ? "Y" : "R";
		mr = mrIstemeStr.equals("Var") ? "V" : "Y";

		Connection con = jdbc.jdbcExample.createConnection();
		int key = 0;
		String sorgu = "INSERT INTO rapor"
				+ "(hastaId,doktorId,yazanDoktor,hemoglabin,vitamin,gebelik,enfeksiyon,seker,mrIsteme,"
				+ "mrAnaliz,dkGorus) VALUES" + "(?,?,?,?,?,?,?,?,?,?,?)";

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setInt(1, hastaId);
			preparedStatement.setInt(2, doktorId);
			preparedStatement.setString(3, dkAdi);
			preparedStatement.setString(4, hmg);
			preparedStatement.setString(5, vitamin);
			preparedStatement.setString(6, gb);
			preparedStatement.setString(7, fk);
			preparedStatement.setString(8, sk);
			preparedStatement.setString(9, mr);
			preparedStatement.setString(10, mrAnaliz);
			preparedStatement.setString(11, dkGorus);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else
			return false;
	}

	// Constructorlar
	public Rapor() {

	}

	public Rapor(String hemoglabin, String vitamin, String gebelik, String enfeksiyon, String seker, String mrIsteme,
			String mrAnaliz, String dkGorus) {
		super();
		this.hemoglabin = hemoglabin;
		this.vitamin = vitamin;
		this.gebelik = gebelik;
		this.enfeksiyon = enfeksiyon;
		this.seker = seker;
		this.mrIsteme = mrIsteme;
		this.mrAnaliz = mrAnaliz;
		this.dkGorus = dkGorus;
	}

	// Getter-Setter
	public String getHemoglabin() {
		return hemoglabin;
	}

	public void setHemoglabin(String hemoglabin) {
		this.hemoglabin = hemoglabin;
	}

	public String getVitamin() {
		return vitamin;
	}

	public void setVitamin(String vitamin) {
		this.vitamin = vitamin;
	}

	public String getGebelik() {
		return gebelik;
	}

	public void setGebelik(String gebelik) {
		this.gebelik = gebelik;
	}

	public String getEnfeksiyon() {
		return enfeksiyon;
	}

	public void setEnfeksiyon(String enfeksiyon) {
		this.enfeksiyon = enfeksiyon;
	}

	public String getSeker() {
		return seker;
	}

	public void setSeker(String seker) {
		this.seker = seker;
	}

	public String getMrIsteme() {
		return mrIsteme;
	}

	public void setMrIsteme(String mrIsteme) {
		this.mrIsteme = mrIsteme;
	}

	public String getMrAnaliz() {
		return mrAnaliz;
	}

	public void setMrAnaliz(String mrAnaliz) {
		this.mrAnaliz = mrAnaliz;
	}

	public String getDkGorus() {
		return dkGorus;
	}

	public void setDkGorus(String dkGorus) {
		this.dkGorus = dkGorus;
	}

}
