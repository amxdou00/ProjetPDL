package gui;

import javax.swing.JPanel;

import model.Enseignant;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ConnectionDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

public class PanelFaireAppel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public PanelFaireAppel(Enseignant enseignant) {
		setBounds(new Rectangle(0, 0, 530, 451));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Marquer un etudiant absent");
		lblNewLabel.setBounds(33, 29, 201, 15);
		add(lblNewLabel);
		
		JLabel lblIdentifiantCours = new JLabel("Identifiant Planning");
		lblIdentifiantCours.setBounds(33, 72, 140, 15);
		add(lblIdentifiantCours);
		
		textField = new JTextField();
		textField.setBounds(191, 70, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblIdentifiantEtudiant = new JLabel("Identifiant Etudiant");
		lblIdentifiantEtudiant.setBounds(33, 144, 157, 15);
		add(lblIdentifiantEtudiant);
		
		textField_1 = new JTextField();
		textField_1.setBounds(211, 142, 114, 19);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnMarquerAbsent = new JButton("Marquer absent");
		btnMarquerAbsent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_etudiant = Integer.parseInt(textField_1.getText());
				int id_cours = Integer.parseInt(textField.getText());
				
				Connection con = null;
				PreparedStatement ps = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				
				try {
			        String query = "insert into absence (id_etudiant, id_planning, justifiee) values (?, ?, 0)";
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        ps.setInt(1, id_etudiant);
			        ps.setInt(2, id_cours);
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
		btnMarquerAbsent.setBounds(130, 221, 148, 25);
		add(btnMarquerAbsent);
		
		JButton btnVoirPlanning = new JButton("Voir planning");
		btnVoirPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnseignantGUI.panelPlanningEnseignant.setVisible(false);
				EnseignantGUI.panelFaireAppel.setVisible(false);
				EnseignantGUI.panelSyntheseAbsence.setVisible(false);
				
				EnseignantGUI.panelPlanningEnseignant.setVisible(true);
			}
		});
		btnVoirPlanning.setBounds(130, 281, 148, 25);
		add(btnVoirPlanning);
		
		JButton btnVoirListeEtudiant = new JButton("Voir Liste Etudiant");
		btnVoirListeEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ListeEtudiantsGUI listeEtudiantsGUI = new ListeEtudiantsGUI();
				EnseignantGUI.frame.dispose();
			}
		});
		btnVoirListeEtudiant.setBounds(130, 345, 148, 25);
		add(btnVoirListeEtudiant);

	}
}
