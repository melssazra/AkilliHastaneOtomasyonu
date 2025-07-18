package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Ilac;
import Yardimci.Yardimci;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GuncelleIlac extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_ilacAdi;
	
	private static Ilac ilac;
	private JTextField txt_stok;
	private JTextField txt_fiyat;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuncelleIlac frame = new GuncelleIlac(ilac);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuncelleIlac(Ilac ilac) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 255, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 221, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(255, 221, 238));
		contentPane_1.setBounds(0, 0, 255, 428);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ilac Adi:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(10, 24, 121, 17);
		contentPane_1.add(lblNewLabel_2);
		
		txt_ilacAdi = new JTextField();
		txt_ilacAdi.setText((String) null);
		txt_ilacAdi.setColumns(10);
		txt_ilacAdi.setBounds(10, 51, 221, 19);
		txt_ilacAdi.setText(ilac.getIlacAdi()); //text kismina var olan ismi getiriyoruz.
		contentPane_1.add(txt_ilacAdi);
		JComboBox cmb_sigorta = new JComboBox();
		cmb_sigorta.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		cmb_sigorta.setModel(new DefaultComboBoxModel(new String[] {"Karşılar(V)", "Karşılamaz(Y)"}));
		cmb_sigorta.setBounds(10, 279, 221, 21);
		contentPane_1.add(cmb_sigorta);
		
		JButton btn_Guncelle = new JButton("GUNCELLE");
		btn_Guncelle.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (ilac == null) {
		            Yardimci.showMessage("error"); // Kullanıcıya hata mesajı göster
		            return;
		        }
		        
		        if (Yardimci.Onay("sure")) {  // Onay alınırsa
		            try {
		                String stokSayisiStr = txt_stok.getText();  // Stok sayısını alıyoruz
		                String ilacFiyatiStr = txt_fiyat.getText(); // Fiyatı alıyoruz
		                
		                boolean isUpdated = ilac.ilacGuncelle(
		                    ilac.getId(),
		                    txt_ilacAdi.getText(), // Yeni ilaç adı
		                    stokSayisiStr,         // Yeni stok sayısı
		                    ilacFiyatiStr,         // Yeni fiyat
		                    cmb_sigorta.getSelectedItem().toString() // Sigorta durumu
		                );
		                
		                if (isUpdated) {
		                    Yardimci.showMessage("success");  // Başarılı işlem mesajı
		                    dispose();  // Pencereyi kapat
		                } else {
		                    Yardimci.showMessage("error");  // Hata mesajı
		                }
		            } catch (ClassNotFoundException | SQLException e1) {
		                e1.printStackTrace();
		                Yardimci.showMessage("error");
		            }
		        }
		    }
		});


		
		btn_Guncelle.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_Guncelle.setBounds(10, 324, 221, 21);
		contentPane_1.add(btn_Guncelle);
		
		txt_stok = new JTextField();
		txt_stok.setText((String) null);
		txt_stok.setColumns(10);
		txt_stok.setBounds(10, 124, 221, 19);
		txt_stok.setText(String.valueOf(ilac.getStokSayisi())); // int değeri String'e dönüştürüyoruz.
        // var olan stok sayisini getiriyoruz.
		contentPane_1.add(txt_stok);
		
		JLabel lblNewLabel_2_1 = new JLabel("Stok Sayisi:");
		lblNewLabel_2_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2_1.setBounds(10, 97, 121, 17);
		contentPane_1.add(lblNewLabel_2_1);
		
		txt_fiyat = new JTextField();
		txt_fiyat.setText((String) null);
		txt_fiyat.setColumns(10);
		txt_fiyat.setBounds(10, 198, 221, 19);
		txt_fiyat.setText(String.valueOf(ilac.getIlacFiyati())); // int değeri String'e dönüştürüyoruz.
        // var olan stok sayisini getiriyoruz.
		contentPane_1.add(txt_fiyat);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Fiyat:");
		lblNewLabel_2_1_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2_1_1.setBounds(10, 171, 121, 17);
		contentPane_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Sigorta Durumu:");
		lblNewLabel_2_1_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2_1_2.setBounds(10, 250, 121, 17);
		contentPane_1.add(lblNewLabel_2_1_2);
		
		
	}
	
	

}
