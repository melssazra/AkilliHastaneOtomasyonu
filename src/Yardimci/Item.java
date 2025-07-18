package Yardimci;

public class Item {
	private int id; // secilen doktorun veya hemsirenin id'si
	private String deger;

	// Constructor
	public Item(int id, String deger) {
		super();
		this.id = id;
		this.deger = deger;
	}

	@Override
	public String toString() {
		return this.deger;
	}
	
	// Getter-Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeger() {
		return deger;
	}

	public void setDeger(String deger) {
		this.deger = deger;
	}
	

}
