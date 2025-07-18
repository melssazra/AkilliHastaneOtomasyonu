package View;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.Ilac;
import Model.Poliklinikler;
import Model.Yonetici;
import Yardimci.Item;
import Yardimci.Yardimci;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class YoneticiGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	static Yonetici yonetici = new Yonetici();
	static Ilac ilac = new Ilac();
	private JTable tbl_poliklinik;
	private JTextField txt_plkAdi;
	private DefaultTableModel klinikModel = null;
	private Object[] klinikData = null;
	private JPopupMenu klinikMenu;
	Poliklinikler poliklinik = new Poliklinikler();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YoneticiGUI frame = new YoneticiGUI(yonetici);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public YoneticiGUI(Yonetici yonetici) throws SQLException, ClassNotFoundException {

		klinikModel = new DefaultTableModel();
		Object[] colKlinikName = new Object[2];
		colKlinikName[0] = "ID";
		colKlinikName[1] = "Poliklinik Adi";
		klinikModel.setColumnIdentifiers(colKlinikName);
		klinikData = new Object[2];
		for (int i = 0; i < poliklinik.getList().size(); i++) {
			klinikData[0] = poliklinik.getList().get(i).getId();
			klinikData[1] = poliklinik.getList().get(i).getPkAdi();
			klinikModel.addRow(klinikData);
		}

		// calisan model
		DefaultTableModel clsModel = new DefaultTableModel();
		Object[] colCalisan = new Object[3];
		colCalisan[0] = "ID";
		colCalisan[1] = "Ad Soyad";
		colCalisan[2] = "Bolum";
		clsModel.setColumnIdentifiers(colCalisan);
		Object[] clsData = new Object[3];

		setTitle("AkilliHastaneOtomasyon");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);

		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 221, 238));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		// giris yaptigimiz yoneticinin adini yazdiriryoruz
		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayin " + yonetici.getKullaniciAdi()); 
																							
		lblNewLabel.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		lblNewLabel.setBounds(0, 14, 275, 21);
		w_pane.add(lblNewLabel);

		JButton btn_cikis = new JButton("ÇIKIŞ");
		btn_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cikis.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		btn_cikis.setBounds(181, 421, 362, 28);
		w_pane.add(btn_cikis);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 66, 683, 345);
		w_pane.add(tabbedPane);

		JPanel pnl_Islem = new JPanel();
		pnl_Islem.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Islemler", null, pnl_Islem, null);
		pnl_Islem.setLayout(null);

		JButton btnNewButton = new JButton("Doktor Islemleri");
		btnNewButton.setBounds(117, 87, 357, 28);
		pnl_Islem.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					DoktorEkleGUI doktorEkleGUI = new DoktorEkleGUI(yonetici);
					doktorEkleGUI.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Verdana Pro", Font.ITALIC, 16));

		JButton btnNewButton_1 = new JButton("Personel Islemleri");
		btnNewButton_1.setBounds(117, 149, 356, 28);
		pnl_Islem.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PersonelEkleGUI personelEkleGUI = new PersonelEkleGUI(yonetici);
					personelEkleGUI.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Verdana Pro", Font.ITALIC, 16));

		JLabel lblNewLabel_1 = new JLabel("Yapmak Istediginiz Islemi Seciniz...");
		lblNewLabel_1.setBounds(32, 31, 338, 17);
		pnl_Islem.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		
		JButton btn_Ilac = new JButton("Ilac Yonetimi");
		btn_Ilac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlacGUI ilacGUI;
				try {
					ilacGUI = new IlacGUI(ilac);
					ilacGUI.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btn_Ilac.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		btn_Ilac.setBounds(118, 208, 356, 28);
		pnl_Islem.add(btn_Ilac);

		JPanel pln_poliklinik = new JPanel();
		pln_poliklinik.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Poliklinik", null, pln_poliklinik, null);
		pln_poliklinik.setLayout(null);

		JScrollPane scrollPoliklinik = new JScrollPane();
		scrollPoliklinik.setBounds(390, 10, 252, 278);
		pln_poliklinik.add(scrollPoliklinik);

		// popup menu olusturma
		klinikMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Guncelle");
		klinikMenu.add(updateMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selKlinik = Integer
						.parseInt(tbl_poliklinik.getValueAt(tbl_poliklinik.getSelectedRow(), 0).toString());
				try {
					Poliklinikler selectKlinik = poliklinik.getFetch(selKlinik);
					UpdateKlinikGUI updateKlinikGUI = new UpdateKlinikGUI(selectKlinik);
					updateKlinikGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					updateKlinikGUI.setVisible(true);
					updateKlinikGUI.addWindowListener(new WindowAdapter() {

						public void windowClosed(WindowEvent w) {
							try {
								updateKlinikModel();
							} catch (SQLException ex) {
								ex.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		tbl_poliklinik = new JTable(klinikModel);
		tbl_poliklinik.setComponentPopupMenu(klinikMenu);
		tbl_poliklinik.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint(); // tiklanan noktanin kordinatlarini al
				int selectedRow = tbl_poliklinik.rowAtPoint(point); // aktar
				tbl_poliklinik.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scrollPoliklinik.setViewportView(tbl_poliklinik);

		tbl_poliklinik.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txt_plkAdi.setText(tbl_poliklinik.getValueAt(tbl_poliklinik.getSelectedRow(), 1).toString());
				} catch (Exception ex) {

				}

			}

		});

		JLabel lblNewLabel_2 = new JLabel("Poliklinik Adi:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(39, 157, 121, 17);
		pln_poliklinik.add(lblNewLabel_2);

		txt_plkAdi = new JTextField();
		txt_plkAdi.setBounds(39, 184, 156, 19);
		pln_poliklinik.add(txt_plkAdi);
		txt_plkAdi.setColumns(10);

		JButton btn_Ekle = new JButton("EKLE");
		btn_Ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_plkAdi.getText().length() == 0) {
					Yardimci.showMessage("fill");
				} else {
					try {
						if (poliklinik.Ekle(txt_plkAdi.getText())) {
							Yardimci.showMessage("success");
							txt_plkAdi.setText(null);
							updateKlinikModel();
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btn_Ekle.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_Ekle.setBounds(207, 144, 108, 21);
		pln_poliklinik.add(btn_Ekle);

		JButton btnNewButton_2 = new JButton("SIL");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_plkAdi.getText().length() == 0) {
					Yardimci.showMessage("Gecersiz islem");
				} else {
					if (Yardimci.Onay("sure")) {
						String SelectName = txt_plkAdi.getText();
						try {
							boolean kontrol = poliklinik.Sil(SelectName);
							if (kontrol) {
								Yardimci.showMessage("success");
								// alani bosalt
								txt_plkAdi.setText(null);
								updateKlinikModel(); // guncelle
							}
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton_2.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btnNewButton_2.setBounds(208, 181, 107, 21);
		pln_poliklinik.add(btnNewButton_2);

		JComboBox cmb_doktor = new JComboBox();
		cmb_doktor.setBounds(39, 93, 148, 21);

		// comboBax'ımıza doktor degiskenlerini atama
		for (int i = 0; i < yonetici.getDoktorList().size(); i++) {
			cmb_doktor.addItem(new Item(yonetici.getDoktorList().get(i).getId(),
					yonetici.getDoktorList().get(i).getKullaniciAdi()));

		}

		cmb_doktor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + ":" + item.getDeger());
		});

		pln_poliklinik.add(cmb_doktor);

		JButton btn_dkAta = new JButton("ATA");
		btn_dkAta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_poliklinik.getSelectedRow();
				if (selRow >= 0) {
					String selKlinik = tbl_poliklinik.getModel().getValueAt(selRow, 0).toString();
					int selKlinikId = Integer.parseInt(selKlinik);
					Item secilenDoktor = (Item) cmb_doktor.getSelectedItem();
					try {
						boolean kontrol = yonetici.Ata(secilenDoktor.getId(), selKlinikId);
						if (kontrol) {
							Yardimci.showMessage("success");
						} else {
							Yardimci.showMessage("error");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Yardimci.showMessage("lutfen bir poliklinik seciniz");
				}
			}
		});
		btn_dkAta.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_dkAta.setBounds(207, 91, 108, 21);
		pln_poliklinik.add(btn_dkAta);

		JButton btnNewButton_3 = new JButton("SEC");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = tbl_poliklinik.getSelectedRow();
				if (selectRow >= 0) {
					String selKlinik = tbl_poliklinik.getModel().getValueAt(selectRow, 0).toString();
					int selKlinikId = Integer.parseInt(selKlinik);
					
					try {
						for(int i=0; i< yonetici.getKlinikDkList(selKlinikId).size();i++) {
							clsData[0] = yonetici.getKlinikDkList(selKlinikId).get(i).getId();
							clsData[1] = yonetici.getKlinikDkList(selKlinikId).get(i).getKullaniciAdi();
							clsData[2] = yonetici.getKlinikDkList(selKlinikId).get(i).getBolum();
							clsModel.addRow(clsData);
							
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else {
					Yardimci.showMessage("Lutfen poliklinik seciniz!");
				}

			}
		});
		btnNewButton_3.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btnNewButton_3.setBounds(207, 217, 108, 21);
		pln_poliklinik.add(btnNewButton_3);

		JLabel lblNewLabel_3 = new JLabel("Doktor Adi:");
		lblNewLabel_3.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3.setBounds(39, 70, 107, 13);
		pln_poliklinik.add(lblNewLabel_3);

	}

	public void updateKlinikModel() throws ClassNotFoundException, SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_poliklinik.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < poliklinik.getList().size(); i++) {
			klinikData[0] = poliklinik.getList().get(i).getId();
			klinikData[1] = poliklinik.getList().get(i).getPkAdi();
			klinikModel.addRow(klinikData);
		}
	}
}
