package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.CoursDAO;
import model.Cours;

import javax.swing.JButton;

public class ModifierCoursGUI {

	private JFrame frame;
	private JTextField textFieldNom;
	private JTextField textFieldMh;
	private JTextField textFieldCours;
	private JTextField textFieldTD;
	private JTextField textFieldTP;
	private JTextField textFieldExamen;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierCoursGUI window = new ModifierCoursGUI();
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
	public ModifierCoursGUI() {
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
		
		JLabel lblModifierCours = new JLabel("Modifier Cours");
		lblModifierCours.setBounds(238, 41, 206, 15);
		frame.getContentPane().add(lblModifierCours);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(26, 116, 70, 15);
		frame.getContentPane().add(lblNom);
		
		JLabel lblMasseHoraire = new JLabel("Masse Horaire");
		lblMasseHoraire.setBounds(26, 143, 103, 15);
		frame.getContentPane().add(lblMasseHoraire);
		
		JLabel lblNombreHeuresCours = new JLabel("Nombre Heures Cours");
		lblNombreHeuresCours.setBounds(26, 181, 174, 15);
		frame.getContentPane().add(lblNombreHeuresCours);
		
		JLabel lblNombreHeuresTd = new JLabel("Nombre Heures TD");
		lblNombreHeuresTd.setBounds(26, 216, 174, 15);
		frame.getContentPane().add(lblNombreHeuresTd);
		
		JLabel lblNombreHeuresTp = new JLabel("Nombre Heures TP");
		lblNombreHeuresTp.setBounds(26, 255, 174, 15);
		frame.getContentPane().add(lblNombreHeuresTp);
		
		JLabel lblNombreHeuresExamen = new JLabel("Nombre Heures Examen");
		lblNombreHeuresExamen.setBounds(26, 292, 174, 15);
		frame.getContentPane().add(lblNombreHeuresExamen);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(114, 112, 114, 19);
		frame.getContentPane().add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldMh = new JTextField();
		textFieldMh.setColumns(10);
		textFieldMh.setBounds(144, 141, 114, 19);
		frame.getContentPane().add(textFieldMh);
		
		textFieldCours = new JTextField();
		textFieldCours.setColumns(10);
		textFieldCours.setBounds(208, 179, 114, 19);
		frame.getContentPane().add(textFieldCours);
		
		textFieldTD = new JTextField();
		textFieldTD.setColumns(10);
		textFieldTD.setBounds(169, 214, 114, 19);
		frame.getContentPane().add(textFieldTD);
		
		textFieldTP = new JTextField();
		textFieldTP.setColumns(10);
		textFieldTP.setBounds(169, 253, 114, 19);
		frame.getContentPane().add(textFieldTP);
		
		textFieldExamen = new JTextField();
		textFieldExamen.setColumns(10);
		textFieldExamen.setBounds(208, 290, 114, 19);
		frame.getContentPane().add(textFieldExamen);
		
		final JLabel label = new JLabel("");
		label.setBounds(12, 425, 642, 15);
		frame.getContentPane().add(label);
		
		JButton btnCreer = new JButton("Modifier");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération des données
				String nom = textFieldNom.getText();
				int masseHoraire = Integer.parseInt(textFieldMh.getText());
				int nbreHeuresCours = Integer.parseInt(textFieldCours.getText());
				int nbreHeuresTD = Integer.parseInt(textFieldTD.getText());
				int nbreHeuresTP = Integer.parseInt(textFieldTP.getText());
				int nbreHeuresExamen = Integer.parseInt(textFieldExamen.getText()); 
				int id = Integer.parseInt(textFieldId.getText());
				
				// Vérification de la somme des nombres d'heures de Cours, TD, TP et Examen
				if((nbreHeuresCours + nbreHeuresTD + nbreHeuresTP + nbreHeuresExamen) != masseHoraire) {
					label.setForeground(Color.RED);
					label.setText("La répartition des heures ne correspond pas à la masse horaire");
					return;
				}
				
				// Création d'un cours à partir des données récupérées
				Cours cours = new Cours(id, nom, masseHoraire, nbreHeuresCours, nbreHeuresTD, nbreHeuresTP, nbreHeuresExamen);
				
				// Création d'une instance de coursDAO
				CoursDAO coursDAO = new CoursDAO();
				
				// Mise à jour du cours
				coursDAO.update(cours);
				
				label.setForeground(Color.GREEN);
				label.setText("Le cours de " + nom + " a bien été modifié");
			}
		});
		btnCreer.setBounds(257, 376, 117, 25);
		frame.getContentPane().add(btnCreer);
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
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(26, 89, 70, 15);
		frame.getContentPane().add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(103, 87, 114, 19);
		frame.getContentPane().add(textFieldId);
		
		
		
		
	}

}
