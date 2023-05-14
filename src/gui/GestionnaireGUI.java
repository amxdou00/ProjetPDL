package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionnaireGUI {

	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionnaireGUI window = new GestionnaireGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GestionnaireGUI() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Ajouter Etudiant");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AjouterEtudiantGUI ajouterEtudiantGUI = new AjouterEtudiantGUI();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(12, 28, 191, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier Etudiant");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ModifierEtudiantGUI modifierEtudiantGUI = new ModifierEtudiantGUI();
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(12, 71, 191, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer Etudiant");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SupprimerEtudiantGUI supprimerEtudiantGUI = new SupprimerEtudiantGUI();
				frame.dispose();
			}
		});
		btnNewButton_2.setBounds(12, 108, 191, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnCreerCours = new JButton("Creer Cours");
		btnCreerCours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				CreerCoursGUI creerCoursGUI = new CreerCoursGUI();
				frame.dispose();
			}
		});
		btnCreerCours.setBounds(12, 184, 191, 25);
		frame.getContentPane().add(btnCreerCours);
		
		JButton btnModifierCours = new JButton("Modifier Cours");
		btnModifierCours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ModifierCoursGUI modifierCoursGUI = new ModifierCoursGUI();
				frame.dispose();
			}
		});
		btnModifierCours.setBounds(12, 221, 191, 25);
		frame.getContentPane().add(btnModifierCours);
		
		JButton btnSupprimerCours = new JButton("Supprimer Cours");
		btnSupprimerCours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SupprimerCoursGUI supprimerCoursGUI = new SupprimerCoursGUI();
				frame.dispose();
			}
		});
		btnSupprimerCours.setBounds(12, 262, 191, 25);
		frame.getContentPane().add(btnSupprimerCours);
		
		JButton btnAjouterEnseignant = new JButton("Ajouter Enseignant");
		btnAjouterEnseignant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AjouterEnseignantGUI ajouterEnseignantGUI = new AjouterEnseignantGUI();
				frame.dispose();
			}
		});
		btnAjouterEnseignant.setBounds(12, 313, 191, 25);
		frame.getContentPane().add(btnAjouterEnseignant);
		
		JButton btnModifierEnseignant = new JButton("Modifier Enseignant");
		btnModifierEnseignant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ModifierEnseignantGUI modifierEnseignantGUI = new ModifierEnseignantGUI();
				frame.dispose();
			}
		});
		btnModifierEnseignant.setBounds(12, 350, 191, 25);
		frame.getContentPane().add(btnModifierEnseignant);
		
		JButton btnSupprimerEnseignant = new JButton("Supprimer Enseignant");
		btnSupprimerEnseignant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SupprimerEnseignantGUI supprimerEnseignantGUI = new SupprimerEnseignantGUI();
				frame.dispose();
			}
		});
		btnSupprimerEnseignant.setBounds(12, 387, 191, 25);
		frame.getContentPane().add(btnSupprimerEnseignant);
		
		JButton btnAffecterCoursEnseignant = new JButton("Affecter Cours Enseignant");
		btnAffecterCoursEnseignant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AffecterCoursEnseignantGUI affecterCoursEnseignantGUI = new AffecterCoursEnseignantGUI();
				frame.dispose();
			}
		});
		btnAffecterCoursEnseignant.setBounds(12, 424, 191, 25);
		frame.getContentPane().add(btnAffecterCoursEnseignant);
		
		JButton btnCreerGroupeEtudiant = new JButton("Creer Groupe Etudiant");
		btnCreerGroupeEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				CreerGroupeEtudiantGUI creerGroupeEtudiantGUI = new CreerGroupeEtudiantGUI();
				frame.dispose();
			}
		});
		btnCreerGroupeEtudiant.setBounds(250, 28, 177, 25);
		frame.getContentPane().add(btnCreerGroupeEtudiant);
		
		JButton btnTraiterJustificatifAbsence = new JButton("Traiter Justificatif Absence");
		btnTraiterJustificatifAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				TraiterJustificatifAbsenceGUI traiterJustificatifAbsenceGUI = new TraiterJustificatifAbsenceGUI();
				frame.dispose();
			}
		});
		btnTraiterJustificatifAbsence.setBounds(250, 71, 177, 25);
		frame.getContentPane().add(btnTraiterJustificatifAbsence);
		
		JButton btnModifierAbsence = new JButton("Modifier Absence");
		btnModifierAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ModifierAbsenceGUI modifierAbsenceGUI = new ModifierAbsenceGUI();
				frame.dispose();
			}
		});
		btnModifierAbsence.setBounds(250, 108, 177, 25);
		frame.getContentPane().add(btnModifierAbsence);
		
		JButton btnDclencherRattrapage = new JButton("Déclencher Rattrapage");
		btnDclencherRattrapage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				DeclencherRattrapageGUI declencherRattrapageGUI = new DeclencherRattrapageGUI();
				frame.dispose();
			}
		});
		btnDclencherRattrapage.setBounds(250, 145, 177, 25);
		frame.getContentPane().add(btnDclencherRattrapage);
		
		JButton btnAttributionZero = new JButton("Attribution Zero");
		btnAttributionZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AttributionZeroGUI attributionZeroGUI = new AttributionZeroGUI();
				frame.dispose();
			}
		});
		btnAttributionZero.setBounds(250, 184, 177, 25);
		frame.getContentPane().add(btnAttributionZero);
		
		JButton btnAttributionPnalits = new JButton("Attribution Pénalités");
		btnAttributionPnalits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AttributionPenalitesGUI attributionPenalitesGUI = new AttributionPenalitesGUI();
				frame.dispose();
			}
		});
		btnAttributionPnalits.setBounds(250, 221, 177, 25);
		frame.getContentPane().add(btnAttributionPnalits);
		
		JButton btnDconnexion = new JButton("Déconnexion");
		btnDconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				LoginPage l = new LoginPage();
				frame.dispose();
			}
		});
		btnDconnexion.setBounds(591, 424, 117, 25);
		frame.getContentPane().add(btnDconnexion);
		frame.setVisible(true);
		
		
		
	}
}
