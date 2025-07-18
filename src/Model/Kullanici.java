package Model;

import jdbc.jdbcExample;

public class Kullanici {
	private int id;
	private String tcNo;
	private String password;
	private String kullaniciTuru;
	private String kullaniciAdi;
	private String bolum;
	private String yapilanIs;
	//jdbcExample conn = new jdbcExample();
	
	//constructorlar
	public Kullanici(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru,String bolum) {	
		this.id = id;
		this.tcNo = tcNo;
		this.password = password;
		this.kullaniciTuru = kullaniciTuru;
		this.kullaniciAdi = kullaniciAdi;
		this.bolum= bolum;
	}
	public Kullanici(int id, String tcNo, String password, String kullaniciAdi, String kullaniciTuru,String bolum , String yapilanIs) {	
		this.id = id;
		this.tcNo = tcNo;
		this.password = password;
		this.kullaniciTuru = kullaniciTuru;
		this.kullaniciAdi = kullaniciAdi;
		this.bolum= bolum;
		this.yapilanIs=yapilanIs;
	}
	
	public Kullanici() {}

	//Getter-Setter methodları
	public String getKullaniciAdi() {
		return kullaniciAdi;
	}
	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}
	public String getKullaniciTuru() {
		return kullaniciTuru;
	}
	public void setKullaniciTuru(String kullaniciTuru) {
		this.kullaniciTuru = kullaniciTuru;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTcNo() {
		return tcNo;
	}
	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getBolum() {
		return bolum;
	}

	public void setBolum(String bolum) {
		this.bolum = bolum;
	}

	public String getYapilanIs() {
		return yapilanIs;
	}

	public void setYapilanIs(String yapilanIs) {
		this.yapilanIs = yapilanIs;
	}
	

}
