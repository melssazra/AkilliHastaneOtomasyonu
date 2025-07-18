package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Randevu {
	private int id,doktorId,hastaId;
	private String doktorAd, hastaAd,randevuTarihi;
	
	Statement st = null;
	ResultSet rs = null;
	
	//Getter-Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoktorId() {
		return doktorId;
	}
	public void setDoktorId(int doktorId) {
		this.doktorId = doktorId;
	}
	public int getHastaId() {
		return hastaId;
	}
	public void setHastaId(int hastaId) {
		this.hastaId = hastaId;
	}
	public String getDoktorAd() {
		return doktorAd;
	}
	public void setDoktorAd(String doktorAd) {
		this.doktorAd = doktorAd;
	}
	public String getHastaAd() {
		return hastaAd;
	}
	public void setHastaAd(String hastaAd) {
		this.hastaAd = hastaAd;
	}
	public String getRandevuTarihi() {
		return randevuTarihi;
	}
	public void setRandevuTarihi(String randevuTarihi) {
		this.randevuTarihi = randevuTarihi;
	}
	//Constuructor
	public Randevu() {}
	public Randevu(int id, int doktorId, int hastaId, String doktorAd, String hastaAd, String randevuTarihi) {
		super();
		this.id = id;
		this.doktorId = doktorId;
		this.hastaId = hastaId;
		this.doktorAd = doktorAd;
		this.hastaAd = hastaAd;
		this.randevuTarihi = randevuTarihi;
	}
	Connection con = null;
	PreparedStatement preparedStatement = null;
	

}
