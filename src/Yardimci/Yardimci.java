package Yardimci;

import javax.swing.JOptionPane;

public class Yardimci {
	
	public static void showMessage(String str) {
		String mesaj;
		
		switch(str) {
		case "fill":
			mesaj="Lutfen tum alanlari doldurunuz";
			break;
		case "success":
			mesaj="Islem basarili!";
			break;
		case "unique":
			mesaj="Bu poliklinik hastanenizde zaten mevcut!";
			break;
		case "error" :
			mesaj = "Bir hata gerceklesti";
			break;
		default:
			mesaj=str;
				
		}
		
		JOptionPane.showMessageDialog(null,"Mesaj",mesaj, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public static boolean Onay(String str) {
		String msg;
		switch (str) {
		case "sure" :
			msg= "Bu islemi gercekten gerceklestirmek istiyor musun ?";
			break;
		default:
			msg=str;
			break;
		}
		
		int result = JOptionPane.showConfirmDialog(null, msg," Dikkat !",JOptionPane.YES_NO_OPTION);
		if(result == 0) return true;
		else return false;
	}

}
