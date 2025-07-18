package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sevk extends Hasta{
	private String hastaneAdi;
	private String teshis;
	private String sevkTarihi;
	


	
	Statement st = null;
	ResultSet rs = null;
	Kullanici obj;
	Connection con = null;
	PreparedStatement preparedStatement = null;
	
	
	public ArrayList<Sevk> getHastaSevk() throws SQLException, ClassNotFoundException {
		ArrayList<Sevk> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();
		Sevk obj;
		try {
			st=con.createStatement();
			rs= st.executeQuery("SELECT * FROM hastasevk");
			while(rs.next()) {
				obj = new Sevk();
				obj.setId(rs.getInt("id"));
				obj.setIlacAdi(rs.getString("hastaId"));
				obj.setHastaAdi(rs.getString("hastaAdi"));
				obj.setHastaneAdi(rs.getString("hastaneAdi"));
				obj.setTeshis(rs.getString("teshis"));
				obj.setSevkTarihi(rs.getString("sevkTarihi"));
				list.add(obj);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		
		return list;
		
	}
	//Sevk edilen hastanin kaydini silme islemi
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

	
	public boolean SevkEkle(int hastaId, String hastaAdi, String hastaneAdi, String teshis)
			throws SQLException, ClassNotFoundException {
		Connection con = jdbc.jdbcExample.createConnection();
		int key = 0;
		String sorgu = "INSERT INTO hastaSevk" + "(hastaId,hastaAdi,hastaneAdi,teshis) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setInt(1, hastaId);
			preparedStatement.setString(2, hastaAdi);
			preparedStatement.setString(3, hastaneAdi);
			preparedStatement.setString(4, teshis);
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
	
	//Consturactorlar
	public Sevk() {}
	
	public Sevk(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru, String bolum,
			String hastaAdi, String ilacAdi, int ilacId, int hastaId) {
		super(id, tcNo, password, kullaniciAdi, kullaniciTuru, bolum, hastaAdi, ilacAdi, ilacId, hastaId);
		// TODO Auto-generated constructor stub
	}
	public Sevk(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru, String bolum) {
		super(id, tcNo, password, kullaniciAdi, kullaniciTuru, bolum);
		// TODO Auto-generated constructor stub
	}
	public Sevk(String hastaneAdi, String teshis) {
		super();
		this.hastaneAdi = hastaneAdi;
		this.teshis = teshis;
	}
	//Getter-Setter
	public String getHastaneAdi() {
		return hastaneAdi;
	}
	public void setHastaneAdi(String hastaneAdi) {
		this.hastaneAdi = hastaneAdi;
	}
	public String getTeshis() {
		return teshis;
	}
	public void setTeshis(String teshis) {
		this.teshis = teshis;
	}
	public String getSevkTarihi() {
		return sevkTarihi;
	}
	public void setSevkTarihi(String sevkTarihi) {
		this.sevkTarihi = sevkTarihi;
	}
}
