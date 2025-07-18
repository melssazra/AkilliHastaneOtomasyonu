package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Yonetici;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Yardimci.*;

public class DoktorEkleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_Adi;
	private JTextField txt_Soyadi;
	private JTextField txt_TcNo;
	private JTextField txt_Bolumu;
	private JTextField txt_Id;
	private JTable tbl_doktor;

	// Degiskenler
	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;
	private JTextField txt_bolum;
	static Yonetici yonetici = new Yonetici();
	private JTextField txt_Sifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorEkleGUI frame = new DoktorEkleGUI(yonetici);
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
	public DoktorEkleGUI(Yonetici yonetici) throws SQLException, ClassNotFoundException {
		setResizable(false);
		setTitle("AkilliHastaneOtomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);

		doktorModel = new DefaultTableModel();
		Object[] colDoktorName = new Object[4];
		colDoktorName[0] = "ID";
		colDoktorName[1] = "Ad Soyad";
		colDoktorName[2] = "TC";
		colDoktorName[3] = "Sifre";
		doktorModel.setColumnIdentifiers(colDoktorName);
		doktorData = new Object[4];
		for (int i = 0; i < yonetici.getDoktorList().size(); i++) {
			doktorData[0] = yonetici.getDoktorList().get(i).getId();
			doktorData[1] = yonetici.getDoktorList().get(i).getKullaniciAdi();
			doktorData[2] = yonetici.getDoktorList().get(i).getTcNo();
			doktorData[3] = yonetici.getDoktorList().get(i).getPassword();
			doktorModel.addRow(doktorData);
		}

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 221, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(41, 52, 606, 341);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Doktor Islemleri", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Adi:");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(33, 63, 55, 13);
		panel.add(lblNewLabel_1);

		txt_Adi = new JTextField();
		txt_Adi.setColumns(10);
		txt_Adi.setBounds(98, 62, 155, 19);
		panel.add(txt_Adi);

		JLabel lblNewLabel_2 = new JLabel("Soyadi:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(24, 93, 64, 17);
		panel.add(lblNewLabel_2);

		txt_Soyadi = new JTextField();
		txt_Soyadi.setColumns(10);
		txt_Soyadi.setBounds(98, 94, 155, 19);
		panel.add(txt_Soyadi);

		JLabel lblNewLabel_3 = new JLabel("TC No:");
		lblNewLabel_3.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3.setBounds(24, 128, 55, 13);
		panel.add(lblNewLabel_3);

		txt_TcNo = new JTextField();
		txt_TcNo.setColumns(10);
		txt_TcNo.setBounds(98, 127, 155, 19);
		panel.add(txt_TcNo);

		JLabel lblNewLabel_4 = new JLabel("Bolumu:");
		lblNewLabel_4.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_4.setBounds(24, 161, 64, 13);
		panel.add(lblNewLabel_4);

		txt_Bolumu = new JTextField();
		txt_Bolumu.setColumns(10);
		txt_Bolumu.setBounds(98, 160, 155, 19);
		panel.add(txt_Bolumu);

		JLabel lblNewLabel_5 = new JLabel("Sifre:");
		lblNewLabel_5.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblNewLabel_5.setBounds(24, 194, 73, 13);
		panel.add(lblNewLabel_5);

		JButton btn_KayitEkle = new JButton("Kayit Ekle");
		btn_KayitEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_Adi.getText().length() == 0 || txt_Sifre.getText().length() == 0
						|| txt_TcNo.getText().length() == 0 || txt_Soyadi.getText().length() == 0
						|| txt_Bolumu.getText().length() == 0) {
					Yardimci.showMessage("fill");
				} else {
					try {

						boolean kontrol = yonetici.Ekle(txt_TcNo.getText(), txt_Sifre.getText(), "doktor",
								txt_Adi.getText(), txt_Bolumu.getText());
						if (kontrol) {
							Yardimci.showMessage("success");
							// alanlari bosaltma
							txt_Adi.setText(null);
							txt_Soyadi.setText(null);
							txt_Bolumu.setText(null);
							txt_Sifre.setText(null);
							txt_TcNo.setText(null);
							updateDoktorModel();
						}
					} catch (SQLException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_KayitEkle.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_KayitEkle.setBounds(33, 264, 198, 21);
		panel.add(btn_KayitEkle);

		JButton btn_KayitSil = new JButton("Kayit Sil");
		btn_KayitSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_Id.getText().length() == 0) {
					Yardimci.showMessage("Gecersiz islem");
				} else {
					if (Yardimci.Onay("sure")) {
						int SelectID = Integer.parseInt(txt_Id.getText());
						try {
							boolean kontrol = yonetici.Sil(SelectID);
							if (kontrol) {
								Yardimci.showMessage("success");
								// alani bosalt
								txt_Id.setText(null);
								updateDoktorModel(); // guncelle
							}
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});
		btn_KayitSil.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_KayitSil.setBounds(342, 264, 235, 21);
		panel.add(btn_KayitSil);

		JLabel lblNewLabel_6 = new JLabel("Kullanici id:");
		lblNewLabel_6.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_6.setBounds(342, 241, 91, 13);
		panel.add(lblNewLabel_6);

		txt_Id = new JTextField();
		txt_Id.setColumns(10);
		txt_Id.setBounds(443, 240, 134, 19);
		panel.add(txt_Id);

		JScrollPane scrollPersonel = new JScrollPane();
		scrollPersonel.setBounds(291, 36, 286, 182);
		panel.add(scrollPersonel);

		tbl_doktor = new JTable(doktorModel); // tabloda gosterme
		scrollPersonel.setViewportView(tbl_doktor);
		// kayit silmek icin sectigim pesoneli id kismina aktarma
		tbl_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txt_Id.setText(tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}

			}

		});

		// guncelleme
		tbl_doktor.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int SelectId = Integer.parseInt(tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 0).toString());
					String selectName = tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 1).toString();
					String selectTc = tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 2).toString();
					String selectPass = tbl_doktor.getValueAt(tbl_doktor.getSelectedRow(), 3).toString();

					try {
						boolean kontrol = yonetici.guncelle(SelectId, selectTc, selectPass, selectName);
						if (kontrol) {
							Yardimci.showMessage("success");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		});

		txt_Sifre = new JTextField();
		txt_Sifre.setBounds(98, 193, 155, 19);
		panel.add(txt_Sifre);
		txt_Sifre.setColumns(10);

		JButton btnNewButton = new JButton("Cikis");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBounds(528, 403, 119, 34);
		contentPane.add(btnNewButton);

		JButton btn_Geri = new JButton("Geri");
		btn_Geri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					YoneticiGUI yoneticiGUI = new YoneticiGUI(yonetici);
					yoneticiGUI.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btn_Geri.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_Geri.setBounds(41, 403, 112, 34);
		contentPane.add(btn_Geri);
	}

	// Tabloyu guncelleme methodu
	public void updateDoktorModel() throws ClassNotFoundException, SQLException {
		// temizleme
		DefaultTableModel clearModel = (DefaultTableModel) tbl_doktor.getModel();
		clearModel.setRowCount(0);
		// guncel tabloyu yazdima
		for (int i = 0; i < yonetici.getDoktorList().size(); i++) {
			doktorData[0] = yonetici.getDoktorList().get(i).getId();
			doktorData[1] = yonetici.getDoktorList().get(i).getKullaniciAdi();
			doktorData[2] = yonetici.getDoktorList().get(i).getTcNo();
			doktorData[3] = yonetici.getDoktorList().get(i).getPassword();
			doktorModel.addRow(doktorData);
		}
	}

}
