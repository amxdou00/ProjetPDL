package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dao.ConnectionDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AttributionZeroGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttributionZeroGUI window = new AttributionZeroGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AttributionZeroGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAttributionZeros = new JLabel("Attribution Zeros");
		lblAttributionZeros.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAttributionZeros.setBounds(256, 147, 191, 15);
		frame.getContentPane().add(lblAttributionZeros);
		
		JLabel lblTousLestudiants = new JLabel("Tous les étudiants ayant une absence non justifiée lors d'un examen, y obtiendront la note 0");
		lblTousLestudiants.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTousLestudiants.setBounds(33, 211, 696, 15);
		frame.getContentPane().add(lblTousLestudiants);
		
		final JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(0, 401, 579, 15);
		frame.getContentPane().add(label);
		frame.setVisible(true);
		
		JButton btnAttribuerZero = new JButton("Attribuer Zeros");
		btnAttribuerZero.setBackground(new Color(255, 0, 0));
		btnAttribuerZero.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAttribuerZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				String query = "select a.id from etudiant a, absence b where b.id_etudiant = a.id and b.justifiee = 0 and b.lors_examen = 1";
				ArrayList<Integer> liste_id_etudiants = new ArrayList<Integer>();
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        rs = ps.executeQuery();
			        
			        while(rs.next()) {
			        	liste_id_etudiants.add(rs.getInt("id"));
			        }
			        
				} catch (Exception ee) {
				        ee.printStackTrace();
				} finally {
				        try {
				                if (ps != null) 
				                        ps.close();
				        } catch (Exception ignore) {}
	
				        try {
				                if (con != null) 
				                        con.close();
				        } catch (Exception ignore) {}
				}
				
				for(int i = 0; i<liste_id_etudiants.size(); i++) {
					try {
						query = "update note set note = 0 where id = ?";
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
				        ps = con.prepareStatement(query);
				        ps.setInt(1, liste_id_etudiants.get(i));
				        returnValue = ps.executeUpdate();
				        label.setText("Opération terminée!");
				        
					} catch (Exception ee) {
				        ee.printStackTrace();
					} finally {
					        try {
					                if (ps != null) 
					                        ps.close();
					        } catch (Exception ignore) {}
		
					        try {
					                if (con != null) 
					                        con.close();
					        } catch (Exception ignore) {}
					}
				}
			}
		});
		btnAttribuerZero.setBounds(244, 276, 167, 25);
		frame.getContentPane().add(btnAttribuerZero);
		
		JButton btnRetout = new JButton("Retour");
		btnRetout.setBackground(new Color(255, 0, 0));
		btnRetout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(274, 408, 117, 25);
		frame.getContentPane().add(btnRetout);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\petit logo esig.jpg"));
		lblNewLabel.setBounds(0, 0, 136, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\croix-rouge-logo-symbole-3d-images-photos-gratuites-1560x1732.png"));
		lblNewLabel_1.setBounds(91, -7, 706, 454);
		frame.getContentPane().add(lblNewLabel_1);
		
		
	}

}
