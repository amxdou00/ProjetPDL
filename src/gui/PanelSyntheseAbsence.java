package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.ConnectionDAO;
import model.Absence;
import model.Enseignant;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelSyntheseAbsence extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelSyntheseAbsence(final Enseignant enseignant) {
		setBounds(new Rectangle(0, 0, 530, 451));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Synthese Absence Etudiant");
		lblNewLabel.setBounds(33, 29, 201, 15);
		add(lblNewLabel);
		
		JLabel lblIdentifiantEtudiant = new JLabel("Identifiant Etudiant");
		lblIdentifiantEtudiant.setBounds(33, 56, 157, 15);
		add(lblIdentifiantEtudiant);
		
		final JTextField textField_1 = new JTextField();
		textField_1.setBounds(211, 56, 114, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		final JTextArea text_area = new JTextArea(10, 30);
		text_area.setText("");
		text_area.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(text_area);
		scrollPane.setBounds(new Rectangle(22, 100, 486, 350));
		add(scrollPane);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_etudiant = Integer.parseInt(textField_1.getText());
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				ArrayList<Absence> absences = new ArrayList<Absence>(); 
				
				for(int i=0; i<enseignant.getListeIdCours().size(); i++) {
					try {
				        String query = "SELECT e.nom_cours, sum(a.duree) FROM c##bdd9_8.absence b, c##bdd9_8.planning a, c##bdd9_8.etudiant c, c##bdd9_8.enseignant d, c##bdd9_8.cours e WHERE a.id = b.id_planning AND c.id = b.id_etudiant AND d.id = a.id_enseignant AND e.id = a.id_cours AND c.id = "+id_etudiant+" AND a.id_cours = "+enseignant.getListeIdCours().get(i)+" GROUP BY e.nom_cours";
				        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
				        ps = con.prepareStatement(query);
				        rs = ps.executeQuery();
				        System.out.println(query);
				        
				        absences.add(new Absence(rs.getString("nom_cours"), rs.getInt("sum(a.duree)")));
				        
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
				
				for(Absence absence: absences) {
					text_area.append(absence.display2());
				}
				
			}
		});
		btnValider.setBounds(386, 51, 117, 25);
		add(btnValider);
		

	}

}
