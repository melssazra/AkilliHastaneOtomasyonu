package View;
import java.awt.EventQueue;
import Yardimci.*; //Yardimci paketini import ettik
import jdbc.*; //Veritabani baglantisinin oldugu paketi import ettik

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Doktor;
import Model.Hasta;
import Model.Personel;
import Model.Yonetici;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField txt_tcNo;
	private JTextField txt_dkTc;
	private JTextField txt_yonUsername;
	private JPasswordField psw_hastasifre;
	private JPasswordField psw_doktorSifre;
	private JPasswordField psw_yonSifre;
	private  jdbcExample conn = new jdbcExample();
	private JTextField txt_persTc;
	private JPasswordField psw_persSifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("AkilliHastaneOtomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 418);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 221, 238));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel("Akilli Hastane Yönetim Sistemi");
		lbl_logo.setFont(new Font("Verdana Pro Black", Font.BOLD | Font.ITALIC, 17));
		lbl_logo.setForeground(new Color(255, 27, 127));
		lbl_logo.setBackground(new Color(255, 128, 192));
		lbl_logo.setBounds(59, 55, 350, 13);
		w_pane.add(lbl_logo);
		
		JTabbedPane w_tabbedpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbedpane.setBounds(41, 99, 393, 231);
		w_pane.add(w_tabbedpane);
		
		JPanel panel = new JPanel();
		panel.setLocation(73, 24);
		panel.setBackground(new Color(255, 255, 255));
		w_tabbedpane.addTab("Hasta Giris", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TC No:");
		lblNewLabel.setForeground(new Color(255, 128, 192));
		lblNewLabel.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(25, 47, 81, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sifre:");
		lblNewLabel_1.setForeground(new Color(255, 128, 192));
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(25, 100, 81, 13);
		panel.add(lblNewLabel_1);
		
		txt_tcNo = new JTextField();
		txt_tcNo.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		txt_tcNo.setBounds(116, 44, 198, 19);
		panel.add(txt_tcNo);
		txt_tcNo.setColumns(10);
		
		JButton btn_kayitOl = new JButton("Kayit ol");
		btn_kayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HastaKayit hastaKayit = new HastaKayit();
				hastaKayit.setVisible(true);
				dispose();
			}
		});
		btn_kayitOl.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		btn_kayitOl.setBounds(50, 147, 95, 21);
		panel.add(btn_kayitOl);
		
		JButton btn_giris = new JButton("Giris");
		btn_giris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_tcNo.getText().length() == 0 || psw_hastasifre.getText().length()== 0 ) {
					Yardimci.showMessage("fill");
				} else {
					boolean key = true;
					try {
						Connection con = jdbcExample.createConnection();
						Statement st= con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM kullanici");
						while(rs.next()) {
							if(txt_tcNo.getText().equals(rs.getString("tcNo")) && psw_hastasifre.getText().equals(rs.getString("password"))) {
								if(rs.getString("KullaniciTuru").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setTcNo(rs.getString("tcNo"));
									hasta.setPassword("password");
									hasta.setKullaniciAdi(rs.getString("KullaniciAdi"));
									hasta.setKullaniciTuru(rs.getString("KullaniciTuru"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}else {
									Yardimci.showMessage("Hatali Kullanici");
								}
							} 
						}
						
					} catch (SQLException | ClassNotFoundException ex) {
						ex.printStackTrace();
					}
					
					if(key) {
						Yardimci.showMessage("Boyle bir hasta mevcut degil");
					}
				}
				
			}
		});
		btn_giris.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		btn_giris.setBounds(203, 147, 111, 21);
		panel.add(btn_giris);
		
		psw_hastasifre = new JPasswordField();
		psw_hastasifre.setBounds(116, 100, 198, 19);
		panel.add(psw_hastasifre);
		
		JPanel w_doktorlog = new JPanel();
		w_doktorlog.setBackground(new Color(255, 255, 255));
		w_tabbedpane.addTab("Doktor Giris", null, w_doktorlog, null);
		w_doktorlog.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("TC No:");
		lblNewLabel_2.setForeground(new Color(255, 128, 192));
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setBounds(44, 40, 81, 13);
		w_doktorlog.add(lblNewLabel_2);
		
		txt_dkTc = new JTextField();
		txt_dkTc.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		txt_dkTc.setColumns(10);
		txt_dkTc.setBounds(135, 37, 198, 19);
		w_doktorlog.add(txt_dkTc);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sifre:");
		lblNewLabel_1_1.setForeground(new Color(255, 128, 192));
		lblNewLabel_1_1.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1_1.setBounds(44, 93, 81, 13);
		w_doktorlog.add(lblNewLabel_1_1);
		
		JButton btn_dkGiris = new JButton("Giris");
		btn_dkGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_dkTc.getText().length()== 0 || psw_doktorSifre.getText().length() == 0) {
					Yardimci.showMessage("fill");
				}else {
					try {
						Connection con = jdbcExample.createConnection();
						Statement st= con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM kullanici");
						while(rs.next()) {
							if(txt_dkTc.getText().equals(rs.getString("tcNo")) && psw_doktorSifre.getText().equals(rs.getString("password"))) {
								if(rs.getString("KullaniciTuru").equals("doktor")) {
									Doktor doktor = new Doktor();
									doktor.setId(rs.getInt("id"));
									doktor.setTcNo(rs.getString("tcNo"));
									doktor.setPassword("password");
									doktor.setKullaniciAdi(rs.getString("KullaniciAdi"));
									doktor.setKullaniciTuru(rs.getString("KullaniciTuru"));
									DoktorGUI dGUI = new DoktorGUI(doktor);
									dGUI.setVisible(true);
									dispose();
								}else {
									Yardimci.showMessage("Hatali Kullanici");
								}
							} 
						}
						
					} catch (SQLException | ClassNotFoundException ex) {
						ex.printStackTrace();
					}
				}
				
			
			}
		});
		btn_dkGiris.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_dkGiris.setBounds(44, 146, 289, 21);
		w_doktorlog.add(btn_dkGiris);
		
		psw_doktorSifre = new JPasswordField();
		psw_doktorSifre.setBounds(135, 93, 198, 19);
		w_doktorlog.add(psw_doktorSifre);
		
		JPanel w_yonLog = new JPanel();
		w_yonLog.setBackground(new Color(255, 255, 255));
		w_tabbedpane.addTab("Yonetici", null, w_yonLog, null);
		w_yonLog.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Kullanici Adi:");
		lblNewLabel_2_1.setForeground(new Color(255, 128, 192));
		lblNewLabel_2_1.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2_1.setBounds(38, 38, 118, 13);
		w_yonLog.add(lblNewLabel_2_1);
		
		txt_yonUsername = new JTextField();
		txt_yonUsername.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		txt_yonUsername.setColumns(10);
		txt_yonUsername.setBounds(179, 35, 183, 19);
		w_yonLog.add(txt_yonUsername);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sifre:");
		lblNewLabel_1_1_1.setForeground(new Color(255, 128, 192));
		lblNewLabel_1_1_1.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1_1_1.setBounds(38, 91, 106, 13);
		w_yonLog.add(lblNewLabel_1_1_1);
		
		JButton btn_yonGiris = new JButton("Giris");
		btn_yonGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_yonUsername.getText().length()== 0 || psw_yonSifre.getText().length() ==0 ) {
					JOptionPane.showMessageDialog(null,"Lütfen tüm alanlari doldurunuz!");
					Yardimci.showMessage("fill");
				} else {
					try {
						Connection con = jdbcExample.createConnection();
						Statement st = con.createStatement();
						ResultSet rs =st.executeQuery("SELECT * FROM kullanici");
						while(rs.next()) {
							if(txt_yonUsername.getText().equals(rs.getString("tcNo")) && psw_yonSifre.getText().equals(rs.getString("password"))) {
								if(rs.getString("kullaniciTuru").equals("yonetici")) {
									Yonetici yonetici = new Yonetici();
									yonetici.setId(rs.getInt("id"));
									yonetici.setPassword("password");
									yonetici.setKullaniciAdi(rs.getString("kullaniciAdi"));
									yonetici.setKullaniciTuru(rs.getString("kullaniciTuru"));
									yonetici.setTcNo(rs.getString("tcNo"));
									YoneticiGUI yoneticiGUI = new YoneticiGUI(yonetici);
									yoneticiGUI.setVisible(true);
									dispose();
								} else {
									Yardimci.showMessage("Hatali kullanici Girisi");
								}
							} 
						}
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		btn_yonGiris.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_yonGiris.setBounds(38, 144, 326, 21);
		w_yonLog.add(btn_yonGiris);
		
		psw_yonSifre = new JPasswordField();
		psw_yonSifre.setBounds(179, 91, 183, 19);
		w_yonLog.add(psw_yonSifre);
		
		JPanel w_personelLog = new JPanel();
		w_personelLog.setBackground(new Color(255, 255, 255));
		w_tabbedpane.addTab("Personel", null, w_personelLog, null);
		w_personelLog.setLayout(null);
		
		JLabel lblNewLabel_2_2 = new JLabel("TC No:");
		lblNewLabel_2_2.setForeground(new Color(255, 128, 192));
		lblNewLabel_2_2.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2_2.setBounds(47, 44, 81, 13);
		w_personelLog.add(lblNewLabel_2_2);
		
		txt_persTc = new JTextField();
		txt_persTc.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		txt_persTc.setColumns(10);
		txt_persTc.setBounds(138, 41, 198, 19);
		w_personelLog.add(txt_persTc);
		
		psw_persSifre = new JPasswordField();
		psw_persSifre.setBounds(138, 97, 198, 19);
		w_personelLog.add(psw_persSifre);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Sifre:");
		lblNewLabel_1_1_2.setForeground(new Color(255, 128, 192));
		lblNewLabel_1_1_2.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1_1_2.setBounds(47, 97, 81, 13);
		w_personelLog.add(lblNewLabel_1_1_2);
		
		JButton btn_persGiris = new JButton("Giris");
		btn_persGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txt_persTc.getText().length()== 0 || psw_persSifre.getText().length() == 0) {
					Yardimci.showMessage("fill");
				}else {
					try {
						Connection con = jdbcExample.createConnection();
						Statement st= con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM kullanici");
						while(rs.next()) {
							if(txt_persTc.getText().equals(rs.getString("tcNo")) && psw_persSifre.getText().equals(rs.getString("password"))) {
								if(rs.getString("KullaniciTuru").equals("personel")) {
									Personel personel = new Personel();
									personel.setId(rs.getInt("id"));
									personel.setTcNo(rs.getString("tcNo"));
									personel.setPassword("password");
									personel.setKullaniciAdi(rs.getString("KullaniciAdi"));
									personel.setKullaniciTuru(rs.getString("KullaniciTuru"));
									PersonelGUI pGUI = new PersonelGUI(personel);
									pGUI.setVisible(true);
									dispose();
								}else {
									Yardimci.showMessage("Hatali Kullanici");
								}
							} 
						}
						
					} catch (SQLException | ClassNotFoundException ex) {
						ex.printStackTrace();
					}
				}
				
				
			}
		});
		btn_persGiris.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_persGiris.setBounds(47, 150, 289, 21);
		w_personelLog.add(btn_persGiris);
		
		JButton btnNewButton = new JButton("CIKIS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		btnNewButton.setBounds(41, 340, 393, 21);
		w_pane.add(btnNewButton);
	}
}
