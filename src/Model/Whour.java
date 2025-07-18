package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Whour {
	private int  id, calisanId;
	private String calisanAdi,wdate,durum,gorev;
	 
	Statement st = null;
	ResultSet rs = null;
	Kullanici obj;
	Connection con = null;
	PreparedStatement preparedStatement = null;
	
	public ArrayList<Whour> getWhourList(int id) throws SQLException, ClassNotFoundException {
	    con = jdbc.jdbcExample.createConnection();
	    ArrayList<Whour> list = new ArrayList<>();
	    Whour obj;
	    try {
	        st = con.createStatement();
	        // id parametresini WHERE koşuluna doğru şekilde ekliyoruz
	        String query = "SELECT * FROM doktorcalisma WHERE durum='M' AND doktorId = " + id;
	        rs = st.executeQuery(query);
	        while (rs.next()) {
	            obj = new Whour();
	            obj.setId(rs.getInt("id"));
	            obj.setCalisanId(rs.getInt("doktorId"));
	            obj.setCalisanAdi(rs.getString("doktorAd"));
	            obj.setDurum(rs.getString("durum"));
	            obj.setWdate(rs.getString("tarih"));
	            list.add(obj);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return list;
	}

	
	//constructor
	public Whour(int id,int calisanId, String calisanAdi, String wdate, String durum) {
		this.id = id;
		this.calisanId = calisanId;
		this.calisanAdi = calisanAdi;
		this.wdate = wdate;
		this.durum = durum;
	}
	public Whour(int id,int calisanId, String calisanAdi, String wdate, String durum,String gorev) {
		this.id = id;
		this.calisanId = calisanId;
		this.calisanAdi = calisanAdi;
		this.wdate = wdate;
		this.durum = durum;
		this.gorev=gorev;
	}
	
	public Whour(){ }
	//Getter-Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCalisanId() {
		return calisanId;
	}
	public void setCalisanId(int calisanId) {
		this.calisanId = calisanId;
	}
	public String getCalisanAdi() {
		return calisanAdi;
	}
	public void setCalisanAdi(String calisanAdi) {
		this.calisanAdi = calisanAdi;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getDurum() {
		return durum;
	}
	public void setDurum(String durum) {
		this.durum = durum;
	}

	public String getGorev() {
		return gorev;
	}

	public void setGorev(String gorev) {
		this.gorev = gorev;
	}
	
	
	
	

}
