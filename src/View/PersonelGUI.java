package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import Model.Doktor;
import Model.Personel;
import Yardimci.Yardimci;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class PersonelGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbl_prs;
	
	private static Personel personel = new Personel();
	private JTextField txt_yapilacak;

	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonelGUI frame = new PersonelGUI(personel);
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
	public PersonelGUI(Personel personel) throws ClassNotFoundException, SQLException {
		setBackground(new Color(255, 221, 238));
		setTitle("AkilliHastaneOtomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		
		
		whourModel = new DefaultTableModel();
		whourData = new Object[3];
		Object[] colWhour = new Object[3];
		//tablomdaki sutun adlari
		colWhour[0] = "ID";
		colWhour[1] = "Gorev";
		colWhour[2] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		for(int i =0 ; i<personel.getWhourList(personel.getId()).size();i++) {
			whourData[0] = personel.getWhourList(personel.getId()).get(i).getId();
			whourData[1] = personel.getWhourList(personel.getId()).get(i).getGorev();
			whourData[2] = personel.getWhourList(personel.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
			
		}
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 221, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(38, 75, 627, 319);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Calisma Takvimi ", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tarih:");
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_1.setBounds(36, 23, 69, 13);
		panel.add(lblNewLabel_1);
		
		JDateChooser date_tarih = new JDateChooser();
		date_tarih.setBounds(36, 46, 164, 19);
		panel.add(date_tarih);
		
		JLabel lblNewLabel_2 = new JLabel("Saat:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(36, 85, 69, 13);
		panel.add(lblNewLabel_2);
		
		JComboBox cmb_saat = new JComboBox();
		cmb_saat.setModel(new DefaultComboBoxModel(new String[] {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00"}));
		cmb_saat.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		cmb_saat.setBounds(36, 108, 164, 21);
		panel.add(cmb_saat);
		
		JButton btn_olustur = new JButton("OLUSTUR");
		btn_olustur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // yil-ay-gun
				String date = sdf.format(date_tarih.getDate()); // formata cevirme
				if (date.length() == 0) {
                     Yardimci.showMessage("Lutfen gecerli bir tarih girin");
				}else {
					String time = " /" + cmb_saat.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						
						boolean kontrol = personel.calismaSaatiEkle(personel.getId(),personel.getKullaniciAdi(), txt_yapilacak.getText(),selectDate);
					    if(kontrol) {
					    	Yardimci.showMessage("success");
					    	updateModel(personel);
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
		btn_olustur.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_olustur.setBounds(36, 213, 164, 21);
		panel.add(btn_olustur);
		
		JScrollPane scrollcalisma = new JScrollPane();
		scrollcalisma.setBounds(262, 10, 319, 262);
		panel.add(scrollcalisma);
		
		tbl_prs = new JTable(whourModel);
		scrollcalisma.setViewportView(tbl_prs);
		
		JLabel lblNewLabel_3 = new JLabel("Yapilacak:");
		lblNewLabel_3.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_3.setBounds(36, 150, 98, 13);
		panel.add(lblNewLabel_3);
		
		txt_yapilacak = new JTextField();
		txt_yapilacak.setBounds(36, 173, 164, 19);
		panel.add(txt_yapilacak);
		txt_yapilacak.setColumns(10);
		
		JButton btn_sil = new JButton("SIL");
		btn_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow = tbl_prs.getSelectedRow();
				if(selRow>= 0) {
					String selectRow = tbl_prs.getModel().getValueAt(selRow , 0).toString();
					int selId = Integer.parseInt(selectRow);
					boolean kontrol;
					try {
						kontrol = personel.Sil(selId);
						if(kontrol) {
							Yardimci.showMessage("success");
							updateModel(personel);
						}else {
							Yardimci.showMessage("error");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					Yardimci.showMessage("Lutfen bir tarih seciniz");
				}
			}
		});
		btn_sil.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_sil.setBounds(36, 251, 164, 21);
		panel.add(btn_sil);
		
		JLabel lblNewLabel = new JLabel("Hosgeldiniz Sayin <dynamic>");
		lblNewLabel.setFont(new Font("Verdana Pro", Font.ITALIC, 16));
		lblNewLabel.setBounds(23, 23, 275, 21);
		contentPane.add(lblNewLabel);
		
		JButton btn_cikis = new JButton("CIKIS");
		btn_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cikis.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_cikis.setBounds(520, 404, 140, 21);
		contentPane.add(btn_cikis);
	}
	
	public void updateModel(Personel personel) throws ClassNotFoundException, SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tbl_prs.getModel();
		clearModel.setRowCount(0);
		for(int i =0 ; i<personel.getWhourList(personel.getId()).size();i++) {
			whourData[0] = personel.getWhourList(personel.getId()).get(i).getId();
			whourData[1] = personel.getWhourList(personel.getId()).get(i).getGorev();
			whourData[2] = personel.getWhourList(personel.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
			
		}
	}

}
