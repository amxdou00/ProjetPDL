package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dao.ConnectionDAO;
import model.Absence;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AttributionPenalitesGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttributionPenalitesGUI window = new AttributionPenalitesGUI();
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
	public AttributionPenalitesGUI() {
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
		
		JLabel lblAttributionPnalits = new JLabel("Attribution Pénalités");
		lblAttributionPnalits.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAttributionPnalits.setBounds(254, 0, 257, 34);
		frame.getContentPane().add(lblAttributionPnalits);
		
		JLabel lblNewLabel = new JLabel("20h - 40h -> -0.1 et 40h-60h -> -0.2");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(213, 85, 298, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLestudiantsSe = new JLabel("Les étudiants se verront attribuer une pénalité en fonction de leurs d'absences non justifiées");
		lblLestudiantsSe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLestudiantsSe.setBounds(83, 60, 696, 15);
		frame.getContentPane().add(lblLestudiantsSe);
		
		JButton btnAttribuerPnalits = new JButton("Attribuer Pénalités");
		btnAttribuerPnalits.setBackground(new Color(255, 0, 0));
		btnAttribuerPnalits.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAttribuerPnalits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				String query = "";
				ArrayList<Integer> liste_id_etudiants = new ArrayList<Integer>();
				
				try {
					query = "select id from etudiant";
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
				
				for(int i = 0; i< liste_id_etudiants.size(); i++) {
					int totalAbsence = 0;
					query = "SELECT j.duree FROM c##bdd9_8.planning j, c##bdd9_8.cours i, c##bdd9_8.absence k, c##bdd9_8.etudiant l WHERE i.id = j.id_cours AND j.id = k.id_planning AND l.id = k.id_etudiant AND k.justifiee = 0 AND l.id = "+liste_id_etudiants.get(i);
					ArrayList<Absence> absences = new ArrayList<Absence>();
					try {
						
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
						
						ps = con.prepareStatement(query);
						rs = ps.executeQuery();

						// Ajout des absences a l'arrayList absence
						while(rs.next()) {
							absences.add(new Absence(rs.getInt("duree")));
						}
						
						
						for(Absence absence: absences) {
							totalAbsence += absence.getDuree();
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
					System.out.println(totalAbsence);
					float malus = 0;
					if(totalAbsence > 1200 && totalAbsence < 2400) {
						malus = 0.1f;
					} 
					else if(totalAbsence > 2400 && totalAbsence < 3600) {
						malus = 0.2f;
					}
					else if(totalAbsence > 3600) {
						malus = 0.3f;
					}
					try {
						query = "update etudiant set malus = ? where id = ?";
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
						ps = con.prepareStatement(query);
						System.out.println(malus);
						ps.setFloat(1, malus);
						ps.setInt(2, liste_id_etudiants.get(i));
						returnValue = ps.executeUpdate();

		
						
						
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
		btnAttribuerPnalits.setBounds(440, 110, 169, 25);
		frame.getContentPane().add(btnAttribuerPnalits);
		frame.setVisible(true);
		
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
		btnRetout.setBounds(253, 397, 117, 25);
		frame.getContentPane().add(btnRetout);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\image penalité.png"));
		lblNewLabel_1.setBounds(302, 145, 372, 554);
		frame.getContentPane().add(lblNewLabel_1);
	}

}
