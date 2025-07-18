import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField txt_tcNo;
	private JTextField txt_sifre;
	private JTextField textField;
	private JTextField txt_dokSifre;
	private JTextField txt_yonUsername;
	private JTextField txt_yonPassword;

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
		lbl_logo.setFont(new Font("Verdana Pro Black", Font.BOLD | Font.ITALIC, 16));
		lbl_logo.setForeground(new Color(255, 27, 127));
		lbl_logo.setBackground(new Color(255, 128, 192));
		lbl_logo.setBounds(59, 68, 350, 13);
		w_pane.add(lbl_logo);
		
		JTabbedPane w_tabbedpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbedpane.setBounds(32, 114, 393, 231);
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
		
		txt_sifre = new JTextField();
		txt_sifre.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		txt_sifre.setBounds(116, 97, 198, 19);
		panel.add(txt_sifre);
		txt_sifre.setColumns(10);
		
		JButton btn_kayitOl = new JButton("Kayit ol");
		btn_kayitOl.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		btn_kayitOl.setBounds(50, 147, 95, 21);
		panel.add(btn_kayitOl);
		
		JButton btn_giris = new JButton("Giris");
		btn_giris.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		btn_giris.setBounds(203, 147, 111, 21);
		panel.add(btn_giris);
		
		JPanel w_doktorlog = new JPanel();
		w_doktorlog.setBackground(new Color(255, 255, 255));
		w_tabbedpane.addTab("Doktor Giris", null, w_doktorlog, null);
		w_doktorlog.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("TC No:");
		lblNewLabel_2.setForeground(new Color(255, 128, 192));
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setBounds(44, 40, 81, 13);
		w_doktorlog.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		textField.setColumns(10);
		textField.setBounds(135, 37, 198, 19);
		w_doktorlog.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sifre:");
		lblNewLabel_1_1.setForeground(new Color(255, 128, 192));
		lblNewLabel_1_1.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1_1.setBounds(44, 93, 81, 13);
		w_doktorlog.add(lblNewLabel_1_1);
		
		txt_dokSifre = new JTextField();
		txt_dokSifre.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		txt_dokSifre.setColumns(10);
		txt_dokSifre.setBounds(135, 90, 198, 19);
		w_doktorlog.add(txt_dokSifre);
		
		JButton btn_dkGiris = new JButton("Giris");
		btn_dkGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_dkGiris.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_dkGiris.setBounds(44, 146, 289, 21);
		w_doktorlog.add(btn_dkGiris);
		
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
		
		txt_yonPassword = new JTextField();
		txt_yonPassword.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		txt_yonPassword.setColumns(10);
		txt_yonPassword.setBounds(182, 88, 180, 19);
		w_yonLog.add(txt_yonPassword);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sifre:");
		lblNewLabel_1_1_1.setForeground(new Color(255, 128, 192));
		lblNewLabel_1_1_1.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1_1_1.setBounds(38, 91, 106, 13);
		w_yonLog.add(lblNewLabel_1_1_1);
		
		JButton btn_yonGiris = new JButton("Giris");
		btn_yonGiris.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_yonGiris.setBounds(38, 144, 289, 21);
		w_yonLog.add(btn_yonGiris);
	}
}
