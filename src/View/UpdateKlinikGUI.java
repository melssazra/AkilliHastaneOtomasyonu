package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Poliklinikler;
import Yardimci.Yardimci;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateKlinikGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_plkAdi;
	private static Poliklinikler poliklink;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateKlinikGUI frame = new UpdateKlinikGUI(poliklink);
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
	public UpdateKlinikGUI(Poliklinikler poliklinik) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 255, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 221, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Poliklinik Adi:");
		lblNewLabel_2.setFont(new Font("Verdana Pro", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(10, 10, 121, 17);
		contentPane.add(lblNewLabel_2);
		
		txt_plkAdi = new JTextField();
		txt_plkAdi.setColumns(10);
		txt_plkAdi.setBounds(10, 37, 176, 19);
		txt_plkAdi.setText(poliklinik.getPkAdi());
		contentPane.add(txt_plkAdi);
		
		JButton btn_Duzenle = new JButton("Duzenle");
		btn_Duzenle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Yardimci.Onay("sure")) {
					try {
						poliklinik.guncelle(poliklinik.getId(), txt_plkAdi.getText());
						Yardimci.showMessage("success");
						dispose();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_Duzenle.setFont(new Font("Verdana Pro Black", Font.ITALIC, 13));
		btn_Duzenle.setBounds(123, 66, 108, 21);
		contentPane.add(btn_Duzenle);
	}

}
