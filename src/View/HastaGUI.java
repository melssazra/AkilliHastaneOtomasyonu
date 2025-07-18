package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.Hasta;
import Model.Poliklinikler;
import Model.Rapor;
import Model.Whour;
import Yardimci.Item;
import Yardimci.Yardimci;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

public class HastaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private static Hasta hasta = new Hasta();
	private static Rapor rapor = new Rapor();
	private Poliklinikler klinik = new Poliklinikler();
	private JTable tbl_doktor;
	private DefaultTableModel dkModel;
	private Object[] dkData;
	private JTextField txt_dkAdi;
	private JTable tbl_Randevu;
	private Whour whour = new Whour();

	private DefaultTableModel whourModel;
	private Object[] whourData;
	private DefaultTableModel hastaModel;
	private Object[] hastaData;
	private DefaultTableModel sonucModel;
	private Object[] sonucData;

	private JPopupMenu klinikMenu;

	private int selectDoktorId = 0;
	private String selectDoktorName = null;
	private JTable tbl_rapor;
	private JTable tbl_sonuc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
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
	public HastaGUI(Hasta hasta) throws ClassNotFoundException, SQLException {

		dkModel = new DefaultTableModel();
		Object[] colDkAdi = new Object[2];
		colDkAdi[0] = "ID";
		colDkAdi[1] = "Ad Soyad";
		dkModel.setColumnIdentifiers(colDkAdi);
		dkData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colwhAdi = new Object[2];
		colwhAdi[0] = "ID";
		colwhAdi[1] = "Tarih";
		whourModel.setColumnIdentifiers(colwhAdi);
		whourData = new Object[2];
		
		// Ilac Model
				hastaModel = new DefaultTableModel();
				Object[] colHasta = new Object[5];
				colHasta[0] = "ID";
				colHasta[1] = "Ilac id";
				colHasta[2] = "Hasta id";
				colHasta[3] = "Hasta Adi";
				colHasta[4] = "İlaç Adi";
				hastaModel.setColumnIdentifiers(colHasta);
				hastaData = new Object[5];
				for (int i = 0; i < hasta.getHastaReceteList(hasta.getKullaniciAdi()).size(); i++) {
					hastaData[0] = hasta.getHastaReceteList(hasta.getKullaniciAdi()).get(i).getId();
					hastaData[1] = hasta.getHastaReceteList(hasta.getKullaniciAdi()).get(i).getIlacId();
					hastaData[2] = hasta.getHastaReceteList(hasta.getKullaniciAdi()).get(i).getHastaId();
					hastaData[3] = hasta.getHastaReceteList(hasta.getKullaniciAdi()).get(i).getHastaAdi();
					hastaData[4] = hasta.getHastaReceteList(hasta.getKullaniciAdi()).get(i).getIlacAdi();
					hastaModel.addRow(hastaData);

				}
				
		//sonuc Model
				sonucModel = new DefaultTableModel();
				Object[] colSonuc = new Object[6];
				colSonuc[0] ="Hemoglabin";
				colSonuc[1] ="Vitamin";
				colSonuc[2] ="Gebelik";
				colSonuc[3] ="Seker";
				colSonuc[4] ="Mr Sonuc";
				colSonuc[5] ="Dk Gorus";
				sonucModel.setColumnIdentifiers(colSonuc);
				sonucData = new Object [6];
				for(int i =0; i<rapor.getHastaSonucList(hasta.getId()).size();i++) {
					sonucData[0] = rapor.getHastaSonucList(hasta.getId()).get(i).getHemoglabin();
					sonucData[1] = rapor.getHastaSonucList(hasta.getId()).get(i).getVitamin();
					sonucData[2] = rapor.getHastaSonucList(hasta.getId()).get(i).getGebelik();
					sonucData[3] = rapor.getHastaSonucList(hasta.getId()).get(i).getSeker();
					sonucData[4] = rapor.getHastaSonucList(hasta.getId()).get(i).getMrAnaliz();
					sonucData[5] = rapor.getHastaSonucList(hasta.getId()).get(i).getDkGorus();
					sonucModel.addRow(sonucData);
				}

		setResizable(false);
		setTitle("AkilliHastaneOtomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 221, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayin " + hasta.getKullaniciAdi());
		lblNewLabel.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		lblNewLabel.setBounds(10, 28, 275, 21);
		contentPane.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(29, 74, 664, 348);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Randevu Al ", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollDoktor = new JScrollPane();
		scrollDoktor.setBounds(411, 29, 202, 269);
		panel.add(scrollDoktor);

		tbl_doktor = new JTable(dkModel);
		tbl_doktor.setComponentPopupMenu(klinikMenu);
		tbl_doktor.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint(); // tıklanan noktanın koordinatlarını al
				int selectedRow = tbl_doktor.rowAtPoint(point); // satır index'ini al

				// Seçilen satır geçerli mi kontrol et
				if (selectedRow >= 0 && selectedRow < tbl_doktor.getRowCount()) {
					tbl_doktor.setRowSelectionInterval(selectedRow, selectedRow);
				}
			}
		});

		scrollDoktor.setViewportView(tbl_doktor);

		tbl_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Seçili satırın geçerli olup olmadığını kontrol et
				int selectedRow = tbl_doktor.getSelectedRow();
				if (selectedRow >= 0) { // Satır geçerli ise
					try {
						// Satırda veri var mı kontrol et
						Object value = tbl_doktor.getValueAt(selectedRow, 1);
						if (value != null) {
							txt_dkAdi.setText(value.toString());
						} else {
							txt_dkAdi.setText(""); // Veri yoksa metin kutusunu temizle
						}
					} catch (Exception ex) {
						ex.printStackTrace(); // Hata oluşursa hata logu yazdır
					}
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Doktor");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(411, 6, 74, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Poliklinikler:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(10, 23, 113, 13);
		panel.add(lblNewLabel_2);

		JComboBox cmb_klinik = new JComboBox();
		cmb_klinik.setBounds(118, 21, 206, 21);
		for (int i = 0; i < klinik.getList().size(); i++) {
			cmb_klinik.addItem(new Item(klinik.getList().get(i).getId(), klinik.getList().get(i).getPkAdi()));
		}
		cmb_klinik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox c = (JComboBox) e.getSource();
				Item item = (Item) c.getSelectedItem();
				DefaultTableModel clearModel = (DefaultTableModel) tbl_doktor.getModel();
				clearModel.setRowCount(0);
				try {
					for (int i = 0; i < klinik.getKlinikDkList(item.getId()).size(); i++) {
						dkData[0] = klinik.getKlinikDkList(item.getId()).get(i).getId();
						dkData[1] = klinik.getKlinikDkList(item.getId()).get(i).getKullaniciAdi();
						dkModel.addRow(dkData);
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		panel.add(cmb_klinik);

		JScrollPane scrollRandevu = new JScrollPane();
		scrollRandevu.setBounds(20, 149, 371, 122);
		panel.add(scrollRandevu);

		tbl_Randevu = new JTable(whourModel);
		scrollRandevu.setViewportView(tbl_Randevu);
		tbl_Randevu.getColumnModel().getColumn(0).setPreferredWidth(5); //id bolumunu tabloda kisalttik

		JLabel lblNewLabel_3 = new JLabel("Randevu Takvimi: ");
		lblNewLabel_3.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3.setBounds(10, 126, 134, 13);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_2_1 = new JLabel("Doktor Adi:");
		lblNewLabel_2_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2_1.setBounds(10, 58, 121, 17);
		panel.add(lblNewLabel_2_1);

		txt_dkAdi = new JTextField();
		txt_dkAdi.setColumns(10);
		txt_dkAdi.setBounds(10, 85, 156, 19);
		panel.add(txt_dkAdi);

		JButton btn_dkSec = new JButton("SEC");
		btn_dkSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl_doktor.getSelectedRow();
				if (row >= 0) {
					String value = tbl_doktor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tbl_Randevu.getModel();
					clearModel.setRowCount(0);

					try {
						System.out.println("Fetching data for doctor ID: " + id);
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					selectDoktorId = id;
					selectDoktorName = tbl_doktor.getModel().getValueAt(row, 1).toString();
					System.out.println(selectDoktorId + " :" + selectDoktorName);

				} else {
					Yardimci.showMessage("lutfen bir doktor seciniz");
				}

			}
		});
		btn_dkSec.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_dkSec.setBounds(203, 82, 121, 21);
		panel.add(btn_dkSec);

		JButton btn_randevuAl = new JButton("Randevu Olustur");
		btn_randevuAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tbl_doktor.getSelectedRow();
				if (selRow >= 0) {
					String date = tbl_Randevu.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean kontrol = hasta.randevuEkle(selectDoktorId, hasta.getId(), selectDoktorName,
								hasta.getKullaniciAdi(), date);
						if (kontrol) {
							Yardimci.showMessage("success");
							hasta.randevuDurumuDegistir(selectDoktorId, date);
							updateModel(selectDoktorId);
						} else {
							Yardimci.showMessage("error");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Yardimci.showMessage("Lutfen gecerli bir islem yapiniz");
				}

			}
		});
		btn_randevuAl.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_randevuAl.setBounds(58, 277, 287, 21);
		panel.add(btn_randevuAl);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Recetelerim ", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 26, 556, 264);
		panel_1.add(scrollPane);
		
		tbl_rapor = new JTable(hastaModel);
		scrollPane.setViewportView(tbl_rapor);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Sonuclarim", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(33, 10, 574, 282);
		panel_2.add(scrollPane_1);
		
		tbl_sonuc = new JTable(sonucModel);
		scrollPane_1.setViewportView(tbl_sonuc);

		JButton btnNewButton = new JButton("CIKIS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btnNewButton.setBounds(571, 432, 122, 21);
		contentPane.add(btnNewButton);
	}

	public void updateModel(int doktorId) throws ClassNotFoundException, SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_Randevu.getModel();
		clearModel.setRowCount(0);
		try {
			for (int i = 0; i < whour.getWhourList(doktorId).size(); i++) {
				whourData[0] = whour.getWhourList(doktorId).get(i).getId();
				whourData[1] = whour.getWhourList(doktorId).get(i).getWdate();
				whourModel.addRow(whourData);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
}
