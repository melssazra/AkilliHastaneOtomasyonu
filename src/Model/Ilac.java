package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Kullanici;

public class Ilac {
	
	private int id;
	private String ilacAdi;
	private int stokSayisi;
	private int ilacFiyati;
	private String sigortaKarsilama;
	
	

	
	Statement st = null;
	ResultSet rs = null;
	Kullanici obj;
	Connection con = null;
	PreparedStatement preparedStatement = null;
	
	public ArrayList<Ilac> getIlacList() throws SQLException, ClassNotFoundException {
		ArrayList<Ilac> list = new ArrayList<>();
		Connection con = jdbc.jdbcExample.createConnection();
		Ilac obj;
		try {
			st=con.createStatement();
			rs= st.executeQuery("SELECT * FROM ilac");
			while(rs.next()) {
				obj = new Ilac();
				obj.setId(rs.getInt("id"));
				obj.setIlacAdi(rs.getString("ilacAdi"));
				obj.setStokSayisi(rs.getInt("ilacStokSayisi"));
				obj.setIlacFiyati(rs.getInt("ilacFiyati"));
				obj.setSigortaKarsilama(rs.getString("sigortaKarsilama"));
				list.add(obj);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
			}
		
		return list;
		
	}
	
	public boolean ilacEkle(String ilacAdi,int stokSayisi,int ilacFiyati,String sigortaKarsilamaStr) throws SQLException, ClassNotFoundException 
	{
		String sorgu = "INSERT INTO ilac (ilacAdi, ilacStokSayisi, ilacFiyati , sigortaKarsilama) VALUES (?,?,?,?)";
		boolean key = false;
		Connection con = jdbc.jdbcExample.createConnection();
		String sigortaKarsilama;
		if(sigortaKarsilamaStr== "Karşılar(V)") {
	    	sigortaKarsilama = "V";
	    }else {
	    	sigortaKarsilama = "Y";
	    }
		try {
			st= con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1,ilacAdi);
			preparedStatement.setInt(2, stokSayisi);
			preparedStatement.setInt(3, ilacFiyati);
			preparedStatement.setString(4, sigortaKarsilama);
			preparedStatement.executeUpdate();
			key=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
	
	public boolean ilacSil (int id) throws ClassNotFoundException, SQLException {
		String sorgu = "DELETE FROM ilac WHERE id = ?";
		boolean key = false;
		Connection con = jdbc.jdbcExample.createConnection();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			key= true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return key;
	}
	
	public boolean ilacGuncelle(int id, String ilacAdi, String stokSayisiStr,String ilacFiyatiStr,String sigortaKarsilamaStr) throws ClassNotFoundException, SQLException {
	    int stokSayisi = 0;  // Başlangıç değeri
	    int ilacFiyati=0;
	    String sigortaKarsilama;
	    try {
	        stokSayisi = Integer.parseInt(stokSayisiStr);  // String'den int'e dönüştürme
	        ilacFiyati=Integer.parseInt(ilacFiyatiStr);
	    } catch (NumberFormatException e) {
	        System.out.println("Geçersiz stok sayısı veya ücretlendirme ");
	        return false;
	    }

	    if(sigortaKarsilamaStr== "Karşılar(V)") {
	    	sigortaKarsilama = "V";
	    }else {
	    	sigortaKarsilama = "Y";
	    }
	    String sorgu = "UPDATE ilac SET ilacAdi =?, ilacStokSayisi=?,ilacFiyati=?,sigortaKarsilama=? WHERE id =?";
	    boolean key = false;
	    Connection con = jdbc.jdbcExample.createConnection();
	    try {
	    	
	        preparedStatement = con.prepareStatement(sorgu);
	        preparedStatement.setString(1, ilacAdi);
	        preparedStatement.setInt(2, stokSayisi);  // int parametreyi burada kullanıyoruz
	        preparedStatement.setInt(3,ilacFiyati);
	        preparedStatement.setString(4,sigortaKarsilama);
	        preparedStatement.setInt(5, id);
	        preparedStatement.executeUpdate();
	        key = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return key;
	}
	public boolean StokSayisiGuncelle(int id,  String stokSayisiStr) throws ClassNotFoundException, SQLException {
	    int stokSayisi = 0;  // Başlangıç değeri
	    String sigortaKarsilama;
	    try {
	        stokSayisi = Integer.parseInt(stokSayisiStr);  // String'den int'e dönüştürme
	        stokSayisi = stokSayisi-1; //ilac bir hastaya verildigi icin onu Stok sayisindan dusuyoruz
	    } catch (NumberFormatException e) {
	        System.out.println("Geçersiz stok sayısı ");
	        return false;
	    }

	    
	    String sorgu = "UPDATE ilac SET  ilacStokSayisi=? WHERE id =?";
	    boolean key = false;
	    Connection con = jdbc.jdbcExample.createConnection();
	    try {
	    	
	        preparedStatement = con.prepareStatement(sorgu);
	        preparedStatement.setInt(1, stokSayisi);  // int parametreyi burada kullanıyoruz
	        preparedStatement.setInt(2, id);
	        preparedStatement.executeUpdate();
	        key = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return key;
	}



	
	public Ilac getFetch(int id) throws ClassNotFoundException, SQLException {
		Connection con = jdbc.jdbcExample.createConnection();
		Ilac ilac = new Ilac();
		st = con.createStatement();
		rs= st.executeQuery("SELECT * FROM ilac WHERE id = "+ id);
		while(rs.next()) {
			ilac.setId(rs.getInt("id"));
			ilac.setIlacAdi(rs.getString("ilacAdi"));
			ilac.setStokSayisi(rs.getInt("ilacStokSayisi"));
			ilac.setIlacFiyati(rs.getInt("ilacFiyati"));
			ilac.setSigortaKarsilama(rs.getString("sigortaKarsilama"));
			break;
		}
		return ilac;
	}
	
	public boolean Gorevlendir(int ilacId, int hastaId, String hastaAdi,String ilacAdi) throws SQLException, ClassNotFoundException {

		String kontrolSorgusu= "SELECT COUNT(*) FROM ilachasta WHERE ilacId =? AND hastaId=? ";
		String eklemeSorgusu="INSERT INTO ilachasta (ilacId,hastaId , hastaAdi,ilacAdi) VALUES (?,?,?,?)";
		boolean key= false;
		
		try(Connection con = jdbc.jdbcExample.createConnection();
			PreparedStatement kontrolStmt = con.prepareStatement(kontrolSorgusu);
			PreparedStatement eklemeStmt = con.prepareStatement(eklemeSorgusu)) {
			
			//Kontol Sorgusu
			kontrolStmt.setInt(1, ilacId);
			kontrolStmt.setInt(2,hastaId);
			try(ResultSet rs= kontrolStmt.executeQuery()){
				rs.next();
				int count = rs.getInt(1);
				
				if( count == 0) { //eger kayit yoksa
					//Ekleme sorgusu
					eklemeStmt.setInt(1,ilacId);
					eklemeStmt.setInt(2, hastaId);
					eklemeStmt.setString(3, hastaAdi);
					eklemeStmt.setString(4, ilacAdi);
					eklemeStmt.executeUpdate();
					key=true;
				}
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return key;
	}
	
	//Constructor
	public Ilac() {}
	public Ilac(int id, String ilacAdi, int stokSayisi) {
		super();
		this.id = id;
		this.ilacAdi = ilacAdi;
		this.stokSayisi = stokSayisi;
	}
	
	
	public Ilac(int id, String ilacAdi, int stokSayisi, int ilacFiyati, String sigortaKarsilama) {
		super();
		this.id = id;
		this.ilacAdi = ilacAdi;
		this.stokSayisi = stokSayisi;
		this.ilacFiyati = ilacFiyati;
		this.sigortaKarsilama = sigortaKarsilama;
	}

	//Getter-Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIlacAdi() {
		return ilacAdi;
	}
	public void setIlacAdi(String ilacAdi) {
		this.ilacAdi = ilacAdi;
	}
	public int getStokSayisi() {
		return stokSayisi;
	}
	public void setStokSayisi(int stokSayisi) {
		this.stokSayisi = stokSayisi;
	}
	public int getIlacFiyati() {
		return ilacFiyati;
	}

	public void setIlacFiyati(int ilacFiyati) {
		this.ilacFiyati = ilacFiyati;
	}

	public String getSigortaKarsilama() {
		return sigortaKarsilama;
	}
	public void setSigortaKarsilama(String sigortaKarsilama) {
		this.sigortaKarsilama = sigortaKarsilama;
	}

}
