package gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ConnectionDAO;
import model.Etudiant;
import other.MyColor;

public class PanelAbsencePhysique extends JPanel {

	private static final long serialVersionUID = 1L;

	private File selectedFile;
	/**
	 * Create the panel.
	 */
	public PanelAbsencePhysique(final Etudiant etudiant) {
		setBounds(new Rectangle(0, 0, 530, 451));
		setLayout(null);
		
		JLabel titre = new JLabel("Demande d'absence physique");
		titre.setBounds(204, 33, 137, 15);
		add(titre);

		JLabel lblIdentifiantAbsence = new JLabel("Identifiant Planning (Voir planning)");
		lblIdentifiantAbsence.setBounds(22, 86, 148, 15);
		add(lblIdentifiantAbsence);

		final JLabel fileSelectLabel = new JLabel("");
		fileSelectLabel.setForeground(Color.GREEN);
		fileSelectLabel.setBounds(204, 180, 95, 15);
		add(fileSelectLabel);

		final JTextField textField = new JTextField();
		textField.setBounds(204, 84, 114, 19);
		add(textField);
		textField.setColumns(10);

		JButton fileButton = new JButton("Select File");
		fileButton.setBounds(204, 143, 130, 25);
		add(fileButton);
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(new Frame());

				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					fileSelectLabel.setText("Fichier prêt!");
				}
			}
		});

		JLabel lblDeposerJustificatif = new JLabel("Deposer justificatif");
		lblDeposerJustificatif.setBounds(22, 153, 148, 15);
		add(lblDeposerJustificatif);

		final JLabel label = new JLabel("");
		label.setBounds(12, 424, 462, 15);
		add(label);
		
		JButton btnDposer = new JButton("Déposer");
		btnDposer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_planning = Integer.parseInt(textField.getText());
				Connection con = null;
				PreparedStatement ps = null;
				
				try {
					// On vérifie si l'identifiant donné est présent dans la table
					try {
						ResultSet rs = null;
						String query = "select * from planning where id = ? AND id_groupe_etudiant = ?";
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
						ps = con.prepareStatement(query);

						ps.setInt(1, id_planning);
						ps.setInt(2, etudiant.getNumeroGroupe());
						rs = ps.executeQuery();

						if(!rs.next()) {
							label.setForeground(MyColor.RED);
							label.setText("Indentifiant invalide. Veuillez consulter votre planning");
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
					
					// Insertion de la demande dans la base de données
					try {
						@SuppressWarnings("unused")
						int returnValue = 0;
						byte[] fileContent = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
						String query = "insert into absence_physique (id_etudiant, id_planning, nom_justificatif, justificatif, validee) values (?, ?, ?, ?, 0)";
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
						ps = con.prepareStatement(query);

						ps.setInt(1, etudiant.getNumeroGroupe());
						ps.setInt(2, id_planning);
						ps.setString(3, selectedFile.getName());
						ps.setBytes(4, fileContent);
						
						
						
						returnValue = ps.executeUpdate();

						
						label.setForeground(MyColor.GREEN);
						label.setText("Votre demande a été déposée. Vous serez notifié après son traitement");
						
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
		btnDposer.setBounds(182, 323, 117, 25);
		add(btnDposer);

		final JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(22, 403, 496, 15);
		add(errorLabel);
		
		JButton btnVoirPlanning = new JButton("Voir Planning");
		btnVoirPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtudiantGUI.panelPlanning.setVisible(false);
				EtudiantGUI.panelVoirAbsence.setVisible(false);
				EtudiantGUI.panelDepotJustificatif.setVisible(false);
				EtudiantGUI.panelPlanifierAbsence.setVisible(false);
				EtudiantGUI.panelMessages.setVisible(false);
				EtudiantGUI.panelAbsencePhysique.setVisible(false);
				
				EtudiantGUI.panelPlanning.setVisible(true);
			}
		});
		btnVoirPlanning.setBounds(357, 81, 117, 25);
		add(btnVoirPlanning);
		
		
	}
}
