package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import dao.ConnectionDAO;
import model.Absence;
import model.Etudiant;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelVoirAbsence extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelVoirAbsence(final Etudiant etudiant) {
		setBounds(0, 0, 530, 451);
		setLayout(null);
		
		JLabel lblMesAbsences = new JLabel("Mes Absences");
		lblMesAbsences.setBounds(204, 33, 137, 15);
		add(lblMesAbsences);
		
		final JTextArea text_area = new JTextArea(10, 30);
		
		text_area.setText("");
		text_area.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(text_area);
		scrollPane.setBounds(new Rectangle(22, 100, 486, 350));
		add(scrollPane);
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT j.duree, j.jour, j.heure_debut, j.heure_fin, i.nom_cours, k.justifiee, k.id FROM c##bdd9_8.planning j, c##bdd9_8.cours i, c##bdd9_8.absence k, c##bdd9_8.etudiant l WHERE i.id = j.id_cours AND j.id = k.id_planning AND l.id = k.id_etudiant AND l.id = "+etudiant.getId();
		ArrayList<Absence> absences = new ArrayList<Absence>();
		
		try {
			
			con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			// Ajout des absences a l'arrayList absence
			while(rs.next()) {
				absences.add(
					new Absence(
						rs.getString("nom_cours"),
						rs.getString("jour").substring(0, 10),
						rs.getString("heure_debut"),
						rs.getString("heure_fin"),
						rs.getInt("duree"),
						rs.getInt("justifiee"),
						rs.getInt("id")
						)
				);
			}
			
			int totalAbsence = 0;
			for(Absence absence: absences) {
				totalAbsence += absence.getDuree();
			}
			text_area.setText("Nombre d'heures d'absence total: "+ String.format("%02d:%02d", totalAbsence/60, totalAbsence%60)+"h\n\n");
			
			
			
			for(Absence absence: absences) {
				text_area.append(absence.display());
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
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "SELECT j.duree, j.jour, j.heure_debut, j.heure_fin, i.nom_cours, k.justifiee, k.id FROM c##bdd9_8.planning j, c##bdd9_8.cours i, c##bdd9_8.absence k, c##bdd9_8.etudiant l WHERE i.id = j.id_cours AND j.id = k.id_planning AND l.id = k.id_etudiant AND l.id = "+etudiant.getId();
				ArrayList<Absence> absences = new ArrayList<Absence>();
				
				try {
					
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					// Ajout des absences a l'arrayList absence
					while(rs.next()) {
						absences.add(
							new Absence(
								rs.getString("nom_cours"),
								rs.getString("jour").substring(0, 10),
								rs.getString("heure_debut"),
								rs.getString("heure_fin"),
								rs.getInt("duree"),
								rs.getInt("justifiee"),
								rs.getInt("id")
								)
						);
					}
					
					int totalAbsence = 0;
					for(Absence absence: absences) {
						totalAbsence += absence.getDuree();
					}
					text_area.setText("Nombre d'heures d'absence total: "+ String.format("%02d:%02d", totalAbsence/60, totalAbsence%60)+"h\n\n");
					
					
					
					for(Absence absence: absences) {
						text_area.append(absence.display());
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
		btnActualiser.setBounds(360, 63, 117, 25);
		add(btnActualiser);
		
		
	}
}
