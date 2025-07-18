package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import com.toedter.calendar.JDateChooser;

import Model.Doktor;
import Model.Hasta;
import Model.Ilac;
import Model.Poliklinikler;
import Model.Rapor;
import Model.Sevk;
import Model.Yonetici;
import Yardimci.Item;
import Yardimci.Yardimci;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class DoktorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private static Yonetici yonetici = new Yonetici();
	private static Doktor doktor = new Doktor();
	private static Hasta hasta = new Hasta();
	private static Ilac ilac = new Ilac();
	private static Sevk sevk = new Sevk();
	private static Rapor rapor = new Rapor();

	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private JTable tbl_calisma;
	private DefaultTableModel klinikModel = null;
	private Object[] klinikData = null;
	private DefaultTableModel hemsireModel = null;
	private Object[] hemsireData = null;
	private DefaultTableModel ilacModel;
	private Object[] ilacData = null;
	private DefaultTableModel sevkModel = null;
	private Object[] sevkData= null;

	// PopupMenu
	private JPopupMenu hemsireMenu;
	private JPopupMenu ilacMenu;

	Poliklinikler poliklinik = new Poliklinikler();

	private JTextField txt_hemsire;
	private JTable tbl_hemsire;
	private JTextField txt_ilacAdi;
	private JTable tbl_ilac;
	private JTextField txt_hastane;
	private JTextField txt_teshis;
	private JTextField txt_vitamin;
	private JTextField txt_mr;
	private JTextField txt_dkGorusu;
	private JTable tbl_sevk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorGUI frame = new DoktorGUI(doktor);
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
	public DoktorGUI(Doktor doktor) throws ClassNotFoundException, SQLException {
		setTitle("AkilliHastaneOtomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);

		whourModel = new DefaultTableModel();
		whourData = new Object[2];
		Object[] colWhour = new Object[2];
		// tablomdaki sutun adlari
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		for (int i = 0; i < doktor.getWhourList(doktor.getId()).size(); i++) {
			whourData[0] = doktor.getWhourList(doktor.getId()).get(i).getId();
			whourData[1] = doktor.getWhourList(doktor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);

		}

		hemsireModel = new DefaultTableModel();
		hemsireData = new Object[2];
		Object[] colHemsire = new Object[2];
		colHemsire[0] = "ID";
		colHemsire[1] = "Hemsire Adi";
		hemsireModel.setColumnIdentifiers(colHemsire);
		for (int i = 0; i < yonetici.getHemsireList().size(); i++) {
			hemsireData[0] = yonetici.getHemsireList().get(i).getId();
			hemsireData[1] = yonetici.getHemsireList().get(i).getKullaniciAdi();
			hemsireModel.addRow(hemsireData);

		}

		// Ilac Model
		ilacModel = new DefaultTableModel();
		Object[] colIlac = new Object[5];
		colIlac[0] = "ID";
		colIlac[1] = "Ilac Adi";
		colIlac[2] = "Stok Sayisi";
		colIlac[3] = "Fiyati";
		colIlac[4] = "Sigorta";
		ilacModel.setColumnIdentifiers(colIlac);
		ilacData = new Object[5];
		for (int i = 0; i < ilac.getIlacList().size(); i++) {
			ilacData[0] = ilac.getIlacList().get(i).getId();
			ilacData[1] = ilac.getIlacList().get(i).getIlacAdi();
			ilacData[2] = ilac.getIlacList().get(i).getStokSayisi();
			ilacData[3] = ilac.getIlacList().get(i).getIlacFiyati();
			ilacData[4] = ilac.getIlacList().get(i).getSigortaKarsilama();
			ilacModel.addRow(ilacData);

		}
		
		//sevk Model
		sevkModel = new DefaultTableModel();
		Object[] colSevk = new Object[4];
		colSevk[0]="Hasta Ad";
		colSevk[1]="Sevk Edilen Hastane";
		colSevk[2]="Teshis";
		colSevk[3]="Sevk Tarih";
        sevkModel.setColumnIdentifiers(colSevk);
        sevkData = new Object[4];
        for(int i =0; i<sevk.getHastaSevk().size();i++) {
        	sevkData[0] = sevk.getHastaSevk().get(i).getHastaAdi();
        	sevkData[1] = sevk.getHastaSevk().get(i).getHastaneAdi();
        	sevkData[2] = sevk.getHastaSevk().get(i).getTeshis();
        	sevkData[3] = sevk.getHastaSevk().get(i).getSevkTarihi();
        	sevkModel.addRow(sevkData);
        }
        
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 221, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayin " + doktor.getKullaniciAdi());
		lblNewLabel.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		lblNewLabel.setBounds(21, 24, 275, 21);
		contentPane.add(lblNewLabel);

		JButton btn_cikis = new JButton("CIKIS");
		btn_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cikis.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_cikis.setBounds(532, 412, 140, 21);
		contentPane.add(btn_cikis);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(30, 72, 627, 319);
		contentPane.add(tabbedPane);

		JPanel pnl_calisma = new JPanel();
		pnl_calisma.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Calisma Takvimi", null, pnl_calisma, null);
		pnl_calisma.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tarih:");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(23, 34, 69, 13);
		pnl_calisma.add(lblNewLabel_1);

		JDateChooser date_tarih = new JDateChooser();
		date_tarih.setBounds(23, 57, 172, 19);
		pnl_calisma.add(date_tarih);

		JLabel lblNewLabel_2 = new JLabel("Saat:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(23, 109, 69, 13);
		pnl_calisma.add(lblNewLabel_2);

		JComboBox cmb_saat = new JComboBox();
		cmb_saat.setModel(new DefaultComboBoxModel(new String[] { "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
				"11:00", "11:30", "12:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00" }));
		cmb_saat.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		cmb_saat.setBounds(23, 132, 172, 21);
		pnl_calisma.add(cmb_saat);

		JButton btn_olustur = new JButton("OLUSTUR");
		btn_olustur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // yil-ay-gun
				String date = "";
				try {
					date = sdf.format(date_tarih.getDate()); // formata cevirme
				} catch (Exception ex2) {

				}
				if (date.length() == 0) {
					Yardimci.showMessage("Lutfen gecerli bir tarih girin");
				} else {
					String time = " /" + cmb_saat.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {

						boolean kontrol = doktor.calismaSaatiEkle(doktor.getId(), doktor.getKullaniciAdi(), selectDate);
						if (kontrol) {
							Yardimci.showMessage("success");
							updateModel(doktor);
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
		btn_olustur.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_olustur.setBounds(23, 193, 172, 21);
		pnl_calisma.add(btn_olustur);

		JScrollPane scrollcalisma = new JScrollPane();
		scrollcalisma.setBounds(249, 20, 319, 262);
		pnl_calisma.add(scrollcalisma);

		// popup menu olusturms
		ilacMenu = new JPopupMenu();
		JMenuItem guncelleMenu = new JMenuItem("GUNCELLE");
		JMenuItem silMenu = new JMenuItem("SIL");
		ilacMenu.add(guncelleMenu);
		ilacMenu.add(silMenu);

		guncelleMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selIlac = Integer.parseInt(tbl_ilac.getValueAt(tbl_ilac.getSelectedRow(), 0).toString()); // secilen
																												// ilacin
																												// id'si
				try {
					Ilac selectIlac = ilac.getFetch(selIlac);
					GuncelleIlac guncelleIlac = new GuncelleIlac(selectIlac);
					guncelleIlac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					guncelleIlac.setVisible(true);
					guncelleIlac.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent w) {
							try {
								updateIlacModel();
							} catch (SQLException ex) {
								ex.printStackTrace();
							} catch (ClassNotFoundException e) {
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

		silMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Yardimci.Onay("sure")) {

					int selIlac = Integer.parseInt(tbl_ilac.getValueAt(tbl_ilac.getSelectedRow(), 0).toString()); // secilen
																													// ilacin
																													// id'si
					try {
						if (ilac.ilacSil(selIlac)) {
							Yardimci.showMessage("success");
							updateIlacModel();
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

		tbl_calisma = new JTable(whourModel);
		scrollcalisma.setViewportView(tbl_calisma);

		JButton btn_sil = new JButton("SIL");
		btn_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = tbl_calisma.getSelectedRow();
				if (selRow >= 0) {
					String selectRow = tbl_calisma.getModel().getValueAt(selRow, 0).toString();
					int selId = Integer.parseInt(selectRow);
					boolean kontrol;
					try {
						kontrol = doktor.Sil(selId);
						if (kontrol) {
							Yardimci.showMessage("success");
							updateModel(doktor);
						} else {
							Yardimci.showMessage("error");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Yardimci.showMessage("Lutfen bir tarih seciniz");
				}
			}
		});
		btn_sil.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_sil.setBounds(23, 236, 172, 21);
		pnl_calisma.add(btn_sil);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Gorev ", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollHemsire = new JScrollPane();
		scrollHemsire.setBounds(314, 33, 262, 228);
		panel.add(scrollHemsire);

		// popup menu olusturma
		hemsireMenu = new JPopupMenu();

		tbl_hemsire = new JTable(hemsireModel);
		tbl_hemsire.setComponentPopupMenu(hemsireMenu);
		tbl_hemsire.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = tbl_hemsire.rowAtPoint(point);
				tbl_hemsire.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		scrollHemsire.setViewportView(tbl_hemsire);

		tbl_hemsire.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					txt_hemsire.setText((String) tbl_hemsire.getValueAt(tbl_hemsire.getSelectedRow(), 1));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JLabel lblNewLabel_4 = new JLabel("Hemsire Adi:");
		lblNewLabel_4.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_4.setBounds(49, 53, 88, 13);
		panel.add(lblNewLabel_4);

		JComboBox cmb_hasta = new JComboBox();
		cmb_hasta.setBounds(49, 158, 193, 21);
		for (int i = 0; i < hasta.getHastaList().size(); i++) {
			cmb_hasta.addItem(
					new Item(hasta.getHastaList().get(i).getId(), hasta.getHastaList().get(i).getKullaniciAdi()));
		}
		cmb_hasta.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + " : " + item.getDeger());
		});
		panel.add(cmb_hasta);

		JButton btn_gorevlendir = new JButton("Gorevlendir");
		btn_gorevlendir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_hemsire.getSelectedRow();
				if (selRow >= 0) {
					int selHemsireId = Integer.parseInt(tbl_hemsire.getModel().getValueAt(selRow, 0).toString());
					Item hastaItem = (Item) cmb_hasta.getSelectedItem();
					try {
						boolean kontrol = doktor.Gorevlendir(selHemsireId, hastaItem.getId());
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
					Yardimci.showMessage("Lutfen hemsire seciniz");
				}
			}
		});
		btn_gorevlendir.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_gorevlendir.setBounds(49, 240, 193, 21);
		panel.add(btn_gorevlendir);

		JLabel lblNewLabel_3 = new JLabel("Hasta Adi:");
		lblNewLabel_3.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3.setBounds(49, 135, 89, 13);
		panel.add(lblNewLabel_3);

		txt_hemsire = new JTextField();
		txt_hemsire.setBounds(49, 76, 193, 19);
		panel.add(txt_hemsire);
		txt_hemsire.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Ilac", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_4_1 = new JLabel("Ilac Adi:");
		lblNewLabel_4_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_4_1.setBounds(50, 50, 88, 13);
		panel_1.add(lblNewLabel_4_1);

		txt_ilacAdi = new JTextField();
		txt_ilacAdi.setColumns(10);
		txt_ilacAdi.setBounds(50, 73, 193, 19);
		panel_1.add(txt_ilacAdi);

		JLabel lblNewLabel_3_1 = new JLabel("Hasta Adi:");
		lblNewLabel_3_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3_1.setBounds(50, 132, 89, 13);
		panel_1.add(lblNewLabel_3_1);

		JComboBox cmb_ilacHasta = new JComboBox();
		for (int i = 0; i < hasta.getHastaList().size(); i++) {
			cmb_ilacHasta.addItem(
					new Item(hasta.getHastaList().get(i).getId(), hasta.getHastaList().get(i).getKullaniciAdi()));
		}
		cmb_ilacHasta.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + " : " + item.getDeger());
		});
		cmb_ilacHasta.setBounds(50, 155, 193, 21);
		panel_1.add(cmb_ilacHasta);

		JButton btn_gorevlendir_1 = new JButton("Yaz");
		btn_gorevlendir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_ilac.getSelectedRow();
				if (selRow >= 0) {
					int selHemsireId = Integer.parseInt(tbl_ilac.getModel().getValueAt(selRow, 0).toString());
					Item hastaItem = (Item) cmb_ilacHasta.getSelectedItem();
					try {
						boolean kontrol = ilac.Gorevlendir(selHemsireId, hastaItem.getId(),cmb_ilacHasta.getSelectedItem().toString(),txt_ilacAdi.getText());
						if (kontrol) {
							Yardimci.showMessage("success");
							int selIlacId = Integer.parseInt(tbl_ilac.getValueAt(tbl_ilac.getSelectedRow(), 0).toString());
							String selIlacAdi=tbl_ilac.getValueAt(tbl_ilac.getSelectedRow(),2).toString();
							ilac.StokSayisiGuncelle(selIlacId,selIlacAdi);
							updateIlacModel();
						} else {
							Yardimci.showMessage("error");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Yardimci.showMessage("Lutfen hemsire seciniz");
				}
			}
		});
		btn_gorevlendir_1.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_gorevlendir_1.setBounds(50, 237, 193, 21);
		panel_1.add(btn_gorevlendir_1);

		JScrollPane scrollIlac = new JScrollPane();
		scrollIlac.setBounds(273, 30, 339, 228);
		panel_1.add(scrollIlac);

		
		tbl_ilac = new JTable(ilacModel);
		tbl_ilac.setComponentPopupMenu(ilacMenu);
		tbl_ilac.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        Point point = e.getPoint();
		        int selectedRow = tbl_ilac.rowAtPoint(point);
		        if (selectedRow != -1) { // Geçerli bir satır mı?
		            tbl_ilac.setRowSelectionInterval(selectedRow, selectedRow);
		        }
		    }
		});

		scrollIlac.setViewportView(tbl_ilac);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Hasta Sevk ", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Hasta Adi:");
		lblNewLabel_3_1_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3_1_1.setBounds(35, 38, 89, 13);
		panel_2.add(lblNewLabel_3_1_1);
		
		JComboBox cmb_sevkHasta = new JComboBox();
		for (int i = 0; i < hasta.getHastaList().size(); i++) {
			cmb_sevkHasta.addItem(
					new Item(hasta.getHastaList().get(i).getId(), hasta.getHastaList().get(i).getKullaniciAdi()));
		}
		cmb_ilacHasta.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getId() + " : " + item.getDeger());
		});
		cmb_sevkHasta.setBounds(35, 61, 193, 21);
		panel_2.add(cmb_sevkHasta);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Sevk Edileceği Hastane Adı :");
		lblNewLabel_3_1_1_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3_1_1_1.setBounds(35, 111, 193, 13);
		panel_2.add(lblNewLabel_3_1_1_1);
		
		txt_hastane = new JTextField();
		txt_hastane.setBounds(35, 146, 193, 19);
		panel_2.add(txt_hastane);
		txt_hastane.setColumns(10);
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Koyulan Teşhis:");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3_1_1_1_1.setBounds(35, 192, 193, 21);
		panel_2.add(lblNewLabel_3_1_1_1_1);
		
		txt_teshis = new JTextField();
		txt_teshis.setColumns(10);
		txt_teshis.setBounds(35, 227, 193, 19);
		panel_2.add(txt_teshis);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(258, 38, 354, 192);
		panel_2.add(scrollPane);
		
		tbl_sevk = new JTable(sevkModel);
		scrollPane.setViewportView(tbl_sevk);
		
		JButton btn_SevkEt = new JButton("Sevk Et");
		btn_SevkEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (txt_hastane.getText().length()!= 0 || txt_teshis.getText().length()!=0 ) {
					Item sevkHastaItem = (Item) cmb_sevkHasta.getSelectedItem();
					try {
						boolean kontrol = sevk.SevkEkle(sevkHastaItem.getId(), cmb_sevkHasta.getSelectedItem().toString(),txt_hastane.getText(), txt_teshis.getText());
						if (kontrol) {
							sevk.Sil(sevkHastaItem.getId());
							Yardimci.showMessage("success");
							updateSevkModel();
						} else {
							Yardimci.showMessage("error");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					Yardimci.showMessage("Lutfen alanlari doldurunuz");
				}
				
				
				
			}
		});
		btn_SevkEt.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_SevkEt.setBounds(288, 240, 293, 21);
		panel_2.add(btn_SevkEt);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Rapor ", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Kan Tahlili Sonucu :");
		lblNewLabel_5.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_5.setBounds(34, 70, 142, 13);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Hemoglabin: ");
		lblNewLabel_6.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		lblNewLabel_6.setBounds(34, 104, 81, 13);
		panel_3.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Vitamin Eksikligi:");
		lblNewLabel_7.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		lblNewLabel_7.setBounds(34, 139, 109, 13);
		panel_3.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Gebelik:");
		lblNewLabel_8.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_8.setBounds(34, 173, 95, 13);
		panel_3.add(lblNewLabel_8);
		
		JLabel lblNewLabel_6_1 = new JLabel("Enfeksiyon:");
		lblNewLabel_6_1.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		lblNewLabel_6_1.setBounds(34, 209, 81, 13);
		panel_3.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_2 = new JLabel("Seker:");
		lblNewLabel_6_2.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		lblNewLabel_6_2.setBounds(34, 244, 81, 13);
		panel_3.add(lblNewLabel_6_2);
		
		JComboBox cmb_hemoglabin = new JComboBox();
		cmb_hemoglabin.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		cmb_hemoglabin.setModel(new DefaultComboBoxModel(new String[] {"Normal", "Dusuk", "Yuksek"}));
		cmb_hemoglabin.setBounds(147, 101, 136, 21);
		panel_3.add(cmb_hemoglabin);
		
		txt_vitamin = new JTextField();
		txt_vitamin.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		txt_vitamin.setText("Yok");
		txt_vitamin.setBounds(153, 137, 130, 19);
		panel_3.add(txt_vitamin);
		txt_vitamin.setColumns(10);
		
		JComboBox cmb_gebelik = new JComboBox();
		cmb_gebelik.setModel(new DefaultComboBoxModel(new String[] {"Yok", "Var"}));
		cmb_gebelik.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		cmb_gebelik.setBounds(147, 171, 136, 21);
		panel_3.add(cmb_gebelik);
		
		JComboBox cmb_enfeksiyon = new JComboBox();
		cmb_enfeksiyon.setModel(new DefaultComboBoxModel(new String[] {"Yok", "Var"}));
		cmb_enfeksiyon.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		cmb_enfeksiyon.setBounds(147, 206, 136, 21);
		panel_3.add(cmb_enfeksiyon);
		
		JComboBox cmb_seker = new JComboBox();
		cmb_seker.setModel(new DefaultComboBoxModel(new String[] {"Normal", "Dusuk", "Yuksek", "Riskli"}));
		cmb_seker.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		cmb_seker.setBounds(147, 241, 136, 21);
		panel_3.add(cmb_seker);
		
		JLabel lblNewLabel_9 = new JLabel("MR Sonucu");
		lblNewLabel_9.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_9.setBounds(333, 29, 103, 13);
		panel_3.add(lblNewLabel_9);
		
		JComboBox cmb_mr = new JComboBox();
		cmb_mr.setFont(new Font("Verdana Pro", Font.ITALIC, 12));
		cmb_mr.setModel(new DefaultComboBoxModel(new String[] {"Istenmedi", "Istendi"}));
		cmb_mr.setBounds(424, 25, 164, 21);
		panel_3.add(cmb_mr);
		
		JLabel lblNewLabel_10 = new JLabel("MR Analiz:");
		lblNewLabel_10.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_10.setBounds(333, 70, 86, 13);
		panel_3.add(lblNewLabel_10);
		
		txt_mr = new JTextField();
		txt_mr.setBounds(333, 102, 255, 19);
		panel_3.add(txt_mr);
		txt_mr.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Doktor Gorusu:");
		lblNewLabel_11.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_11.setBounds(333, 155, 128, 13);
		panel_3.add(lblNewLabel_11);
		
		txt_dkGorusu = new JTextField();
		txt_dkGorusu.setBounds(331, 183, 257, 19);
		panel_3.add(txt_dkGorusu);
		txt_dkGorusu.setColumns(10);
		JComboBox cmb_raporHasta = new JComboBox();
		for (int i = 0; i < hasta.getHastaList().size(); i++) {
			cmb_raporHasta.addItem(
					new Item(hasta.getHastaList().get(i).getId(), hasta.getHastaList().get(i).getKullaniciAdi()));
		}
		
		cmb_raporHasta.setBounds(139, 27, 144, 21);
		panel_3.add(cmb_raporHasta);
		
		JButton btn_ekle = new JButton("EKLE");
		btn_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item raporItem = (Item) cmb_raporHasta.getSelectedItem();
					try {
						boolean kontrol =rapor.randevuEkle(raporItem.getId(),doktor.getId(), doktor.getKullaniciAdi(), cmb_hemoglabin.getSelectedItem().toString(),txt_vitamin.getText()
						, cmb_gebelik.getSelectedItem().toString(), cmb_enfeksiyon.getSelectedItem().toString(),cmb_seker.getSelectedItem().toString()
						, cmb_mr.getSelectedItem().toString(),txt_mr.getText(),txt_dkGorusu.getText());
						if (kontrol) {
							Yardimci.showMessage("success");
							
						} else {
							Yardimci.showMessage("error");
						}
						txt_vitamin.setText(null);
						txt_mr.setText(null);
						txt_dkGorusu.setText(null);
						cmb_hemoglabin.setSelectedItem(null);
						cmb_gebelik.setSelectedItem(null);
						cmb_enfeksiyon.setSelectedItem(null);
						cmb_seker.setSelectedItem(null);
						cmb_mr.setSelectedItem(null);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				
			}
		});
		btn_ekle.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_ekle.setBounds(333, 239, 255, 21);
		panel_3.add(btn_ekle);
		
		JLabel lblNewLabel_3_1_1_2 = new JLabel("Hasta Adi:");
		lblNewLabel_3_1_1_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3_1_1_2.setBounds(34, 29, 82, 13);
		panel_3.add(lblNewLabel_3_1_1_2);
		
		
		tbl_ilac.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) { // Değişiklik tamamlandı mı?
		            int selectedRow = tbl_ilac.getSelectedRow();
		            if (selectedRow != -1) { // Geçerli bir satır mı?
		                try {
		                    txt_ilacAdi.setText((String) tbl_ilac.getValueAt(selectedRow, 1));
		                } catch (Exception ex) {
		                    ex.printStackTrace(); // Hataları yakalayın, ancak işlemi durdurmayın
		                }
		            } else {
		                txt_ilacAdi.setText(""); // Hiçbir satır seçili değilse temizle
		            }
		        }
		    }
		});

		
	}

	public void updateModel(Doktor doktor) throws ClassNotFoundException, SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_calisma.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < doktor.getWhourList(doktor.getId()).size(); i++) {
			whourData[0] = doktor.getWhourList(doktor.getId()).get(i).getId();
			whourData[1] = doktor.getWhourList(doktor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);

		}
	}

	public void updateIlacModel() throws ClassNotFoundException, SQLException {
	    DefaultTableModel clearModel = (DefaultTableModel) tbl_ilac.getModel();
	    clearModel.setRowCount(0); // Mevcut satırları temizle
	    for (int i = 0; i < ilac.getIlacList().size(); i++) {
	        ilacData[0] = ilac.getIlacList().get(i).getId();
	        ilacData[1] = ilac.getIlacList().get(i).getIlacAdi();
	        ilacData[2] = ilac.getIlacList().get(i).getStokSayisi();
	        ilacData[3] = ilac.getIlacList().get(i).getIlacFiyati();
	        ilacData[4] = ilac.getIlacList().get(i).getSigortaKarsilama();
	        clearModel.addRow(ilacData);
	    }
	    tbl_ilac.setModel(clearModel); // Yeni modeli tabloya bağla
	    tbl_ilac.clearSelection(); // Seçimi temizle
	    txt_ilacAdi.setText(""); // İlgili alanları temizle
	}
	
	public void updateSevkModel() throws ClassNotFoundException, SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_sevk.getModel();
		clearModel.setRowCount(0);
		for(int i =0; i<sevk.getHastaSevk().size();i++) {
        	sevkData[0] = sevk.getHastaSevk().get(i).getHastaAdi();
        	sevkData[1] = sevk.getHastaSevk().get(i).getHastaneAdi();
        	sevkData[2] = sevk.getHastaSevk().get(i).getTeshis();
        	sevkData[3] = sevk.getHastaSevk().get(i).getSevkTarihi();
        	sevkModel.addRow(sevkData);
        }
	}

}
