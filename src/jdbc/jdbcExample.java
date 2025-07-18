package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcExample {
	//Veritabani baglantisi donduren bir metod
	public static Connection createConnection() throws SQLException,ClassNotFoundException{
		//1.Adim:Driver'i yukle
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//2.Adim: baglantiyi olustur
		String url = "jdbc:mysql://localhost:3306/akillihastane";
		String user="root";
		String password="MelisaAzra-08";
		
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void main(String [] args) {
		try {
			//Baglantiyi al
			Connection con = createConnection();
			System.out.println("Baglanti olusturuldu.");
			
			//Baglantiyi kapatma
			con.close();
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC Driver bulunamadi: "+ e.getMessage());
		}catch(SQLException e) {
			System.out.println("Veritabani baglanti hatasi: "+e.getMessage());
		}
	}

	 
	
	/*//bu metodda bir exception firlatilabilir (throws)
	public static void main(String [] args) throws ClassNotFoundException{
		//1. adim driver'i kaydet
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Baglantiyi kur
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/akillihastane","root","MelisaAzra-08");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("connection created");
			} 
*/
}
