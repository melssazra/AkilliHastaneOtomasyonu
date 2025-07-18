package View;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Ilac;
import Model.Yonetici;
import Yardimci.Yardimci;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class IlacGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel HastaneOtomasyon;
	private JTable tbl_ilac;
	private JTextField txt_ilacAdi;
	private JTextField txt_ilacStok;
	
	private DefaultTableModel ilacModel = null;
	private Object[] ilacData = null;
	private JPopupMenu ilacMenu;
	
	
	static Ilac ilac = new Ilac();
	private JTextField txt_fiyat;
		
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IlacGUI frame = new IlacGUI(ilac);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public IlacGUI(Ilac ilac) throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		HastaneOtomasyon = new JPanel();
		HastaneOtomasyon.setBackground(new Color(255, 221, 238));
		HastaneOtomasyon.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Ilac Model
		ilacModel = new DefaultTableModel();
		Object[] colIlac = new Object [5];
		colIlac[0] = "ID";
		colIlac[1]="Ilac Adi";
		colIlac[2] = "Stok Sayisi";
		colIlac[3]="Fiyati";
		colIlac[4]="Sigorta";
		ilacModel.setColumnIdentifiers(colIlac);
		ilacData = new Object[5];
		for(int i =0 ; i<ilac.getIlacList().size();i++) {
			ilacData[0] = ilac.getIlacList().get(i).getId();
			ilacData[1]= ilac.getIlacList().get(i).getIlacAdi();
			ilacData[2]=  ilac.getIlacList().get(i).getStokSayisi();
			ilacData[3]=ilac.getIlacList().get(i).getIlacFiyati();
			ilacData[4]=ilac.getIlacList().get(i).getSigortaKarsilama();
			ilacModel.addRow(ilacData);
					
		}
		

		setContentPane(HastaneOtomasyon);
		HastaneOtomasyon.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(50, 55, 616, 330);
		HastaneOtomasyon.add(tabbedPane);
		
		JPanel panel_ilac = new JPanel();
		panel_ilac.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Ilac Yonetimi ", null, panel_ilac, null);
		panel_ilac.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 10, 315, 283);
		panel_ilac.add(scrollPane);
		
		//popup menu olusturms
		ilacMenu= new JPopupMenu();
		JMenuItem guncelleMenu = new JMenuItem("GUNCELLE");
		JMenuItem silMenu = new JMenuItem("SIL");
		ilacMenu.add(guncelleMenu);
		ilacMenu.add(silMenu);
		
		guncelleMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selIlac = Integer.parseInt(tbl_ilac.getValueAt(tbl_ilac.getSelectedRow(),0).toString()); //secilen ilacin id'si
				try {
					Ilac selectIlac = ilac.getFetch(selIlac);
					GuncelleIlac  guncelleIlac = new GuncelleIlac(selectIlac);
					guncelleIlac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					guncelleIlac.setVisible(true);
					guncelleIlac.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent w) {
							try {
							updateIlacModel();
							}catch(SQLException ex) {
								ex.printStackTrace();
							}catch(ClassNotFoundException e) {
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
				if(Yardimci.Onay("sure")) {
				
					int selIlac = Integer.parseInt(tbl_ilac.getValueAt(tbl_ilac.getSelectedRow(),0).toString()); //secilen ilacin id'si
					try {
						if(ilac.ilacSil(selIlac)) {
							Yardimci.showMessage("success");
							updateIlacModel();
						}else {
							Yardimci.showMessage("error");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		
		tbl_ilac = new JTable(ilacModel);
		tbl_ilac.setComponentPopupMenu(ilacMenu);
		tbl_ilac.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();  //tikladigim alandaki kordinatlari getirecek
				int selectedRow = tbl_ilac.rowAtPoint(point);
				tbl_ilac.setRowSelectionInterval(selectedRow, selectedRow);
			
			}
		});
		scrollPane.setViewportView(tbl_ilac);
		
		JLabel lblNewLabel = new JLabel("Ilac Adi: ");
		lblNewLabel.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel.setBounds(41, 10, 93, 13);
		panel_ilac.add(lblNewLabel);
		
		txt_ilacAdi = new JTextField();
		txt_ilacAdi.setBounds(41, 40, 213, 19);
		panel_ilac.add(txt_ilacAdi);
		txt_ilacAdi.setColumns(10);
		
		txt_ilacStok = new JTextField();
		txt_ilacStok.setColumns(10);
		txt_ilacStok.setBounds(41, 105, 213, 19);
		panel_ilac.add(txt_ilacStok);
		
		txt_fiyat = new JTextField();
		txt_fiyat.setColumns(10);
		txt_fiyat.setBounds(41, 171, 213, 19);
		panel_ilac.add(txt_fiyat);
		
		JComboBox cmb_sigorta = new JComboBox();
		cmb_sigorta.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		cmb_sigorta.setModel(new DefaultComboBoxModel(new String[] {"Karşılar(V)", "Karşılamaz(Y)"}));
		cmb_sigorta.setBounds(41, 225, 213, 21);
		panel_ilac.add(cmb_sigorta);
		
		JLabel lblIlacStokSayisi = new JLabel("Ilac Stok Sayisi: ");
		lblIlacStokSayisi.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblIlacStokSayisi.setBounds(41, 75, 115, 13);
		panel_ilac.add(lblIlacStokSayisi);
		
		JButton btn_ekle = new JButton("EKLE");
		btn_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_ilacAdi.getText().length() == 0 || txt_ilacStok.getText().length()==0 || txt_fiyat.getText().length() == 0 ) {
					Yardimci.showMessage("fill");
				}else {
					int stokSayisi= Integer.parseInt(txt_ilacStok.getText());
					int ilacFiyati =Integer.parseInt(txt_fiyat.getText());
					try {
						if(ilac.ilacEkle(txt_ilacAdi.getText(),stokSayisi,ilacFiyati,cmb_sigorta.getSelectedItem().toString())) {
							Yardimci.showMessage("success");
							txt_ilacAdi.setText(null);
							txt_ilacStok.setText(null);
							txt_fiyat.setText(null);
							updateIlacModel();
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_ekle.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_ekle.setBounds(41, 272, 213, 21);
		panel_ilac.add(btn_ekle);
		
		JLabel lblFiyat = new JLabel("Fiyat:");
		lblFiyat.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblFiyat.setBounds(41, 141, 115, 13);
		panel_ilac.add(lblFiyat);
		
		
		
		JLabel lblSigortaKarsilama = new JLabel("Sigorta Karsilama");
		lblSigortaKarsilama.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblSigortaKarsilama.setBounds(41, 197, 132, 13);
		panel_ilac.add(lblSigortaKarsilama);
		
		
		
		JButton btnNewButton = new JButton("CIKIS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btnNewButton.setBounds(538, 404, 128, 25);
		HastaneOtomasyon.add(btnNewButton);
	}
	
	public void updateIlacModel() throws ClassNotFoundException, SQLException {
		DefaultTableModel clearModel =(DefaultTableModel) tbl_ilac.getModel();
		clearModel.setRowCount(0);
		for(int i =0 ; i<ilac.getIlacList().size();i++) {
			ilacData[0] = ilac.getIlacList().get(i).getId();
			ilacData[1]= ilac.getIlacList().get(i).getIlacAdi();
			ilacData[2]=  ilac.getIlacList().get(i).getStokSayisi();
			ilacData[3]=ilac.getIlacList().get(i).getIlacFiyati();
			ilacData[4]=ilac.getIlacList().get(i).getSigortaKarsilama();
			ilacModel.addRow(ilacData);
					
		}
	}
}
