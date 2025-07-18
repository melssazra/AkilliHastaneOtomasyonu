package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Yardimci.Yardimci;

public class Hasta extends Kullanici {
	private  String hastaAdi;
	private String ilacAdi;
	private int ilacId;
	private int hastaId;
	

	Statement st = null;
	ResultSet rs = null;
	Connection con = null;
	Kullanici obj;
	PreparedStatement preparedStatement = null;
	
	public ArrayList<Kullanici> getHastaList() throws SQLException, ClassNotFoundException {
		ArrayList<Kullanici> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kullanici WHERE KullaniciTuru = 'hasta'");
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
	
	public ArrayList<Hasta> getHastaReceteList(String hastaAdi) throws SQLException, ClassNotFoundException {
		ArrayList<Hasta> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();
		Hasta obj;
		try {
			st=con.createStatement();
			rs= st.executeQuery("SELECT * FROM ilachasta WHERE hastaAdi ='" +hastaAdi+"'");
			while(rs.next()) {
				obj = new Hasta();
				obj.setId(rs.getInt("id"));
				obj.setIlacId(rs.getInt("ilacId"));
				obj.setHastaId(rs.getInt("hastaId"));
				obj.setHastaAdi(rs.getString("hastaAdi"));
				obj.setIlacAdi(rs.getString("ilacAdi"));
				list.add(obj);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		
		return list;
		
	}

	

	public boolean kayit(String tcNo, String password, String kullaniciAdi)
			throws SQLException, ClassNotFoundException {
		Connection con = jdbc.jdbcExample.createConnection();
		int key = 0;
		boolean tekrar = false;
		String sorgu = "INSERT INTO kullanici" + "(tcNo,password,KullaniciAdi,KullaniciTuru) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM kullanici WHERE tcNo = '" + tcNo + "'");

			while (rs.next()) {
				tekrar = true;
				Yardimci.showMessage("Bu tc numarisina ait daha onceden kayit yapilmistir");
				break;
			}

			if (!tekrar) {
				preparedStatement = con.prepareStatement(sorgu);
				preparedStatement.setString(1, tcNo);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, kullaniciAdi);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else
			return false;
	}

	public boolean randevuEkle(int doktorId, int hastaId, String doktorAd, String hastaAd, String randevuTarihi)
			throws SQLException, ClassNotFoundException {
		Connection con = jdbc.jdbcExample.createConnection();
		int key = 0;
		String sorgu = "INSERT INTO randevu" + "(doktorId,doktorAd,hastaId,hastaAd,randevuTarihi) VALUES" + "(?,?,?,?,?)";

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setInt(1, doktorId);
			preparedStatement.setString(2, doktorAd);
			preparedStatement.setInt(3, hastaId);
			preparedStatement.setString(4, hastaAd);
			preparedStatement.setString(5, randevuTarihi);
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
	
	//Bir hasta randevu aldıginda musaitlik durumunu dolu olarak degistiriyoruz
	public boolean randevuDurumuDegistir(int doktorId,String randevuTarihi)throws SQLException, ClassNotFoundException {
		Connection con = jdbc.jdbcExample.createConnection();
		int key = 0;
		String sorgu = "UPDATE doktorcalisma SET durum = ? WHERE doktorId = ? AND tarih =? " ;

		try {
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1,"D");
			preparedStatement.setInt(2,doktorId);
			preparedStatement.setString(3, randevuTarihi);
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
	public Hasta() {
	}

	public Hasta(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru, String bolum) {
		super(id, tcNo, password, kullaniciAdi, kullaniciTuru, bolum);
	}
	
	

	public Hasta(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru, String bolum,
			String hastaAdi, String ilacAdi, int ilacId, int hastaId) {
		super(id, tcNo, password, kullaniciAdi, kullaniciTuru, bolum);
		this.hastaAdi = hastaAdi;
		this.ilacAdi = ilacAdi;
		this.ilacId = ilacId;
		this.hastaId = hastaId;
	}

	//Getter-Setter
	public String getHastaAdi() {
		return hastaAdi;
	}

	public void setHastaAdi(String hastaAdi) {
		this.hastaAdi = hastaAdi;
	}

	public String getIlacAdi() {
		return ilacAdi;
	}

	public void setIlacAdi(String ilacAdi) {
		this.ilacAdi = ilacAdi;
	}

	public int getIlacId() {
		return ilacId;
	}

	public void setIlacId(int ilacId) {
		this.ilacId = ilacId;
	}

	public int getHastaId() {
		return hastaId;
	}

	public void setHastaId(int hastaId) {
		this.hastaId = hastaId;
	}
	

}
