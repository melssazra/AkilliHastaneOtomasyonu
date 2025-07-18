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

import Model.Kullanici;
import Model.Yonetici;
import Yardimci.Yardimci;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonelEkleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField txt_Adi;
	private JTextField txt_Soyadi;
	private JTextField txt_TcNo;
	private JTextField txt_Bolumu;
	private JTextField txt_Id;
	private JTable tbl_personel;
	
	//Degiskenler
		private DefaultTableModel personelModel = null;
		private Object[] personelData =null;
		static Yonetici yonetici = new Yonetici();
		private JTextField txt_Sifre;
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonelEkleGUI frame = new PersonelEkleGUI(yonetici);
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
	public PersonelEkleGUI(Yonetici yonetici) throws SQLException, ClassNotFoundException{
		setTitle("AkilliHastaneOtomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		
		personelModel = new DefaultTableModel();
		Object[] colPersonelName= new Object[5];
		colPersonelName[0]="ID";
		colPersonelName[1]="Ad Soyad";
		colPersonelName[2]="TC";
		colPersonelName[3]="Sifre";
		colPersonelName[4]="Gorev";
		personelModel.setColumnIdentifiers(colPersonelName);
		
		for(int i =0; i<yonetici.getPersonelList().size();i++) {
			personelData = new Object[5];
			personelData[0]=yonetici.getPersonelList().get(i).getId();
			personelData[1]=yonetici.getPersonelList().get(i).getKullaniciAdi();
			personelData[2]=yonetici.getPersonelList().get(i).getTcNo();
			personelData[3]=yonetici.getPersonelList().get(i).getPassword();
			personelData[4]=yonetici.getPersonelList().get(i).getBolum();
			personelModel.addRow(personelData);
		}
		
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 221, 238));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(45, 38, 641, 352);
		w_pane.add(tabbedPane);
		
		JPanel panelPersonel = new JPanel();
		panelPersonel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Personel Islem", null, panelPersonel, null);
		panelPersonel.setLayout(null);
		
		JScrollPane scrollPersonel = new JScrollPane();
		scrollPersonel.setBounds(290, 41, 286, 182);
		panelPersonel.add(scrollPersonel);
		
		tbl_personel = new JTable(personelModel); //tabloda gosterme
		scrollPersonel.setViewportView(tbl_personel);
		
		//kayit silmek icin sectigim pesoneli id kismina aktarma
		tbl_personel.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
					try {
						txt_Id.setText(tbl_personel.getValueAt(tbl_personel.getSelectedRow(),0).toString());
					} catch (Exception ex){
							
					}
						
				}
					
			});
		
			tbl_personel.getModel().addTableModelListener(new TableModelListener() {

				@Override
				public void tableChanged(TableModelEvent e) {
					if(e.getType() == TableModelEvent.UPDATE) {
						int SelectId = Integer.parseInt(tbl_personel.getValueAt(tbl_personel.getSelectedRow(),0).toString());
						String selectName= tbl_personel.getValueAt(tbl_personel.getSelectedRow(),1).toString();
						String selectTc=tbl_personel.getValueAt(tbl_personel.getSelectedRow(),2).toString();
						String selectPass=tbl_personel.getValueAt(tbl_personel.getSelectedRow(),3).toString();
						
						try {
							boolean kontrol =yonetici.guncelle(SelectId, selectTc, selectPass, selectName);
							if(kontrol) {
								Yardimci.showMessage("success");
							}
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				
			
			});
				
				
		JLabel lblNewLabel_1 = new JLabel("Adi:");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(32, 68, 55, 13);
		panelPersonel.add(lblNewLabel_1);
		
		txt_Adi = new JTextField();
		txt_Adi.setColumns(10);
		txt_Adi.setBounds(97, 67, 155, 19);
		panelPersonel.add(txt_Adi);
		
		JLabel lblNewLabel_2 = new JLabel("Soyadi:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(23, 98, 64, 17);
		panelPersonel.add(lblNewLabel_2);
		
		txt_Soyadi = new JTextField();
		txt_Soyadi.setColumns(10);
		txt_Soyadi.setBounds(97, 99, 155, 19);
		panelPersonel.add(txt_Soyadi);
		
		JLabel lblNewLabel_3 = new JLabel("TC No:");
		lblNewLabel_3.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3.setBounds(23, 133, 55, 13);
		panelPersonel.add(lblNewLabel_3);
		
		txt_TcNo = new JTextField();
		txt_TcNo.setColumns(10);
		txt_TcNo.setBounds(97, 132, 155, 19);
		panelPersonel.add(txt_TcNo);
		
		JLabel lblNewLabel_4 = new JLabel("Bolumu:");
		lblNewLabel_4.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_4.setBounds(23, 166, 64, 13);
		panelPersonel.add(lblNewLabel_4);
		
		txt_Bolumu = new JTextField();
		txt_Bolumu.setColumns(10);
		txt_Bolumu.setBounds(97, 165, 155, 19);
		panelPersonel.add(txt_Bolumu);
		
		JLabel lblNewLabel_5 = new JLabel("Sifre:");
		lblNewLabel_5.setFont(new Font("Verdana", Font.ITALIC, 13));
		lblNewLabel_5.setBounds(23, 199, 73, 13);
		panelPersonel.add(lblNewLabel_5);
		
		JButton btn_KayitEkle = new JButton("Kayit Ekle");
		btn_KayitEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txt_Adi.getText().length() == 0 || txt_Sifre.getText().length() == 0 || txt_TcNo.getText().length() == 0 || txt_Soyadi.getText().length() == 0 || txt_Bolumu.getText().length()== 0) {
					Yardimci.showMessage("fill");
				} else {
					try {
					     
						boolean kontrol = yonetici.Ekle(txt_TcNo.getText(),txt_Sifre.getText(),"personel", txt_Adi.getText(), txt_Bolumu.getText());
					    if(kontrol) {
					    	Yardimci.showMessage("success");
					    	//alanlari bosaltma
					    	txt_Adi.setText(null);
					    	txt_Soyadi.setText(null);
					    	txt_Bolumu.setText(null);
					    	txt_Sifre.setText(null);
					    	txt_TcNo.setText(null);
					    	updatePersonelModel();
					    }
					} catch (SQLException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_KayitEkle.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btn_KayitEkle.setBounds(32, 269, 198, 21);
		panelPersonel.add(btn_KayitEkle);
		
		JLabel lblNewLabel_6 = new JLabel("Kullanici id:");
		lblNewLabel_6.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_6.setBounds(341, 246, 91, 13);
		panelPersonel.add(lblNewLabel_6);
		
		txt_Id = new JTextField();
		txt_Id.setColumns(10);
		txt_Id.setBounds(442, 245, 134, 19);
		panelPersonel.add(txt_Id);
		
		JButton btn_KayitSil = new JButton("Kayit Sil");
		btn_KayitSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txt_Id.getText().length()== 0) {
					Yardimci.showMessage("Gecersiz islem");
				} else {
					if(Yardimci.Onay("sure")) {
						int SelectID = Integer.parseInt(txt_Id.getText());
						try {
							boolean kontrol = yonetici.Sil(SelectID);
							if(kontrol) {
							Yardimci.showMessage("success");
							//alani bosalt
							txt_Id.setText(null);
							updatePersonelModel(); //guncelle
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
		btn_KayitSil.setBounds(341, 269, 235, 21);
		panelPersonel.add(btn_KayitSil);
		
		txt_Sifre = new JTextField();
		txt_Sifre.setBounds(97, 194, 155, 19);
		panelPersonel.add(txt_Sifre);
		txt_Sifre.setColumns(10);
		
		JButton btnNewButton = new JButton("Çıkış");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Verdana Pro", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBounds(554, 400, 132, 36);
		w_pane.add(btnNewButton);
		
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
		btn_Geri.setBounds(50, 401, 92, 30);
		w_pane.add(btn_Geri);
	}

	//tabloyu guncelleme methodu
	public void updatePersonelModel() throws ClassNotFoundException, SQLException{
		//temizleme
		DefaultTableModel clearModel = (DefaultTableModel)tbl_personel.getModel();
		clearModel.setRowCount(0);
		//tabloyu guncelleme
		for(int i =0; i<yonetici.getPersonelList().size();i++) {
			personelData = new Object[5];
			personelData[0]=yonetici.getPersonelList().get(i).getId();
			personelData[1]=yonetici.getPersonelList().get(i).getKullaniciAdi();
			personelData[2]=yonetici.getPersonelList().get(i).getTcNo();
			personelData[3]=yonetici.getPersonelList().get(i).getPassword();
			personelData[4]=yonetici.getPersonelList().get(i).getBolum();
			personelModel.addRow(personelData);
		}
	}
}
