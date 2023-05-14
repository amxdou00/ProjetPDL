package gui;

import java.awt.Rectangle;
import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.Calendar;
import dao.ConnectionDAO;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import model.Cours;
import model.Etudiant;

import javax.swing.JTextArea;

public class PanelPlanning extends JLabel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelPlanning(Etudiant etudiant) {
		setBounds(new Rectangle(0, 0, 530, 451));
		setLayout(null);
		final int groupe = etudiant.getNumeroGroupe();
		
		String months[] = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
		
		final Choice choiceJour = new Choice();
		choiceJour.setBounds(65, 50, 59, 21);
		for(int i=0; i<31; i++) {
			choiceJour.addItem(""+(i+1));
		}
		
		add(choiceJour);
		
		final Choice choiceMois = new Choice();
		choiceMois.setBounds(200, 50, 131, 21);
		for(String month: months) {
			choiceMois.addItem(month);
		}
		add(choiceMois);
		
		JLabel lblVeuillezChoisirUne = new JLabel("Veuillez choisir une date");
		lblVeuillezChoisirUne.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVeuillezChoisirUne.setHorizontalAlignment(SwingConstants.CENTER);
		lblVeuillezChoisirUne.setBounds(12, 12, 526, 32);
		add(lblVeuillezChoisirUne);
		
		JLabel lblNewLabel = new JLabel("Jour");
		lblNewLabel.setBounds(22, 56, 37, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mois");
		lblNewLabel_1.setBounds(157, 56, 37, 15);
		add(lblNewLabel_1);
		
		
		final JTextArea text_area = new JTextArea(10, 30);
		
		text_area.setText("");
		text_area.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(text_area);
		scrollPane.setBounds(new Rectangle(22, 100, 486, 350));
		add(scrollPane);
		
		
		JButton btnNewButton = new JButton("Voir Planning");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_area.setText("");
				String jour = choiceJour.getItem(choiceJour.getSelectedIndex());
				String mois = Integer.toString(choiceMois.getSelectedIndex()+1);
				String annee = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
				ArrayList<Cours> planning = new ArrayList<Cours>();
				
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "SELECT c.id, c.nom_cours, d.nom, d.prenom, b.jour, b.heure_debut, b.heure_fin, b.salle, e.type_cours FROM c##bdd9_8.planning b, c##bdd9_8.groupe_etudiant a, c##bdd9_8.cours c, c##bdd9_8.enseignant d, c##bdd9_8.type_cours e WHERE a.id = b.id_groupe_etudiant AND c.id = b.id_cours AND d.id = b.id_enseignant AND e.id = b.id_type_cours AND b.jour = TO_DATE('"+annee+"-"+mois+"-"+jour+"', 'YYYY-MM-DD') AND a.numero_groupe = "+groupe;

				
					try {
						
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
						
						ps = con.prepareStatement(query);
						rs = ps.executeQuery();

						// Ajout des cours a l'arrayList planning
						while(rs.next()) {
							planning.add(
								new Cours(
									rs.getString("nom_cours"),
									rs.getString("heure_debut"),
									rs.getString("heure_fin"),
									rs.getString("salle"),
									rs.getString("nom"),
									rs.getString("prenom"),
									rs.getString("type_cours"),
									rs.getInt("id")
									)
							);
						}
						
						for(Cours cours: planning) {
							text_area.append(cours.display());
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
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(381, 50, 131, 21);
		add(btnNewButton);
		
		
		
	}
}
