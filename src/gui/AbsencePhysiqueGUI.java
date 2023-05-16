package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ConnectionDAO;
import other.MyColor;
import javax.swing.JButton;

public class AbsencePhysiqueGUI {

	private JFrame frame;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AbsencePhysiqueGUI window = new AbsencePhysiqueGUI();
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
	public AbsencePhysiqueGUI() {
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

		JLabel lblTraitementAbsencePhysique = new JLabel("Traitement Demande Absence Physique");
		lblTraitementAbsencePhysique.setBounds(163, 30, 374, 15);
		frame.getContentPane().add(lblTraitementAbsencePhysique);

		JLabel lblIdentifiantDemande = new JLabel("Identifiant Demande");
		lblIdentifiantDemande.setBounds(24, 97, 152, 15);
		frame.getContentPane().add(lblIdentifiantDemande);

		textFieldId = new JTextField();
		textFieldId.setBounds(198, 95, 114, 19);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);

		final JLabel errLabel = new JLabel("");
		errLabel.setBounds(24, 425, 630, 15);
		frame.getContentPane().add(errLabel);

		JButton btnTlchargerJustificatif = new JButton("Télécharger Justificatif");
		btnTlchargerJustificatif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					ps = con.prepareStatement("select justificatif, nom_justificatif from absence_physique where id = ?");
					ps.setInt(1, id_absence);
					rs = ps.executeQuery();

					if (rs.next()) {
						if(rs.getBlob("justificatif") != null) {
							Blob fileData = rs.getBlob("justificatif");


							String fileName = rs.getString("nom_justificatif");

							// Save the file data to a file
							InputStream inputStream = fileData.getBinaryStream();
							FileOutputStream outputStream = new FileOutputStream("./justificatifs" + fileName);

							byte[] buffer = new byte[4096];
							int bytesRead = -1;
							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outputStream.write(buffer, 0, bytesRead);
							}

							// Close the streams
							inputStream.close();
							outputStream.close();

							errLabel.setText("Le justificatif se trouve dans ./justificatifs/"+fileName);
						}
						else {
							errLabel.setText("Cette absence n'a pas de justificatif!");
						}
					}
					else {
						errLabel.setText("Identifiant invalide!");
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
			}
		});
		btnTlchargerJustificatif.setBounds(366, 92, 211, 25);
		frame.getContentPane().add(btnTlchargerJustificatif);

		final JLabel label = new JLabel("");
		label.setBounds(24, 425, 630, 15);
		frame.getContentPane().add(label);

		JButton btnValiderDemande = new JButton("Valider Demande");
		btnValiderDemande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				@SuppressWarnings("unused")
				int returnValue = 0;

				// On vérifie si l'identifiant donné est présent dans la table
				try {
					ResultSet rs = null;
					String query = "select * from absence_physique where id = ?";
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					ps = con.prepareStatement(query);

					ps.setInt(1, id);
					rs = ps.executeQuery();

					if(!rs.next()) {
						label.setForeground(MyColor.RED);
						label.setText("Cet identifiant n'existe pas");
						return;
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

				
				// Validation de la demande
				try {
					String query = "update absence_physique set validee = 1 where id = ?";
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					ps = con.prepareStatement(query);
					ps.setInt(1, id);
					returnValue = ps.executeUpdate();

					label.setForeground(MyColor.GREEN);
					label.setText("L'absence a été validée, l'étudiant recevra un lien");

				} catch(Exception ee) {
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

				// Envoi du lien fictif à l'étudiant concerné
				String link = "https://cours-en-ligne.com/JKDS8GHY84I";
				try {
					ResultSet rs = null;
					int idEtudiant = 0;
					String query1 = "select id_etudiant from absence_physique where id = ?";
					String query2 = "update etudiant set message = CONCAT(message, ?) where id = ?";
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					ps = con.prepareStatement(query1);
					ps.setInt(1, id);
					rs = ps.executeQuery();

					if(rs.next()) {
						idEtudiant = rs.getInt("id_etudiant");
					}

					ps = con.prepareStatement(query2);
					ps.setString(1, "\n\nVotre demande a été validée. Lien: "+link+"\n\n");
					ps.setInt(2, idEtudiant);
					returnValue = ps.executeUpdate();
				} catch(Exception ee) {
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
		});
		btnValiderDemande.setBounds(121, 321, 178, 25);
		frame.getContentPane().add(btnValiderDemande);

		JButton btnInvaliderDemand = new JButton("Invalider Demande");
		btnInvaliderDemand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				
				// On vérifie si l'identifiant donné est présent dans la table
				try {
					ResultSet rs = null;
					String query = "select * from absence_physique where id = ?";
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					ps = con.prepareStatement(query);

					ps.setInt(1, id);
					rs = ps.executeQuery();

					if(!rs.next()) {
						label.setForeground(MyColor.RED);
						label.setText("Cet identifiant n'existe pas");
						return;
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
				
				
				
				// Marquer l'étudiant absent pour le cours en question
				try {
					int idPlanning = 0;
					int idEtudiant = 0;
					ResultSet rs = null;
					String query1 = "select id_planning, id_etudiant from absence_physique where id = ?";
					String query2 = "insert into absence (id_etudiant, id_planning, justifiee, hors_quota, lors_examen) values (?, ?, 0, 0, 0)";
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					ps = con.prepareStatement(query1);
					ps.setInt(1, id);
					rs = ps.executeQuery();
					
					if(rs.next()) {
						idPlanning = rs.getInt("id_planning");
						idEtudiant = rs.getInt("id_etudiant");
					}
					
					ps = con.prepareStatement(query2);
					ps.setInt(1, idEtudiant);
					ps.setInt(2, idPlanning);
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
				
				// Suppression de la demande
				try {
					String query = "delete from absence_physique where id = ?";
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					ps = con.prepareStatement(query);
					ps.setInt(1, id);
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
		});
		btnInvaliderDemand.setBounds(121, 374, 178, 25);
		frame.getContentPane().add(btnInvaliderDemand);
		frame.setVisible(true);

		JButton btnRetout = new JButton("Retour");
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(537, 368, 117, 25);
		frame.getContentPane().add(btnRetout);

		JButton btnVoirLesDemandes = new JButton("Voir Les Demandes");
		btnVoirLesDemandes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				VoirDemandesGUI voirDemandesGUI = new VoirDemandesGUI();
				frame.dispose();
			}
		});
		btnVoirLesDemandes.setBounds(121, 274, 178, 25);
		frame.getContentPane().add(btnVoirLesDemandes);




	}
}
