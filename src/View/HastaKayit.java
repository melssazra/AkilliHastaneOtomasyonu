package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Hasta;
import Yardimci.Yardimci;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class HastaKayit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_ad;
	private JTextField txt_Tc;
	private JPasswordField psw_Sifre;
	
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaKayit frame = new HastaKayit();
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
	public HastaKayit() {
		setResizable(false);
		setTitle("AkilliHastaOtomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 221, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ad Soyad:");
		lblNewLabel.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel.setBounds(10, 30, 76, 13);
		contentPane.add(lblNewLabel);
		
		txt_ad = new JTextField();
		txt_ad.setBounds(10, 53, 266, 19);
		contentPane.add(txt_ad);
		txt_ad.setColumns(10);
		
		txt_Tc = new JTextField();
		txt_Tc.setBounds(10, 118, 266, 19);
		contentPane.add(txt_Tc);
		txt_Tc.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("TC No:");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(10, 95, 58, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sifre:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(10, 157, 58, 13);
		contentPane.add(lblNewLabel_2);
		
		psw_Sifre = new JPasswordField();
		psw_Sifre.setBounds(10, 180, 266, 19);
		contentPane.add(psw_Sifre);
		
		JButton btn_geri = new JButton("Geri");
		btn_geri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
		    	login.setVisible(true);
		    	dispose();
			}
		});
		btn_geri.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_geri.setBounds(10, 220, 112, 21);
		contentPane.add(btn_geri);
		
		JButton btn_kayitOl = new JButton("Kayit Ol");
		btn_kayitOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_Tc.getText().length() == 0 || psw_Sifre.getText().length() == 0 || txt_ad.getText().length()== 0) {
					Yardimci.showMessage("fill");
				} else {
					try {
						boolean kontrol = hasta.kayit(txt_Tc.getText(),psw_Sifre.getText(),txt_ad.getText());
					    if(kontrol) {
					    	Yardimci.showMessage("success");
					    	LoginGUI login = new LoginGUI();
					    	login.setVisible(true);
					    	dispose();
					    } else {
					    	Yardimci.showMessage("error");
					    }
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_kayitOl.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_kayitOl.setBounds(164, 220, 112, 21);
		contentPane.add(btn_kayitOl);
		
		JButton btnNewButton = new JButton("CIKIS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btnNewButton.setBounds(80, 262, 100, 21);
		contentPane.add(btnNewButton);
	}
}
