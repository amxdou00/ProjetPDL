package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.GroupeEtudiantDAO;
import model.GroupeEtudiant;
import other.ResultMessage;

import javax.swing.JButton;

public class CreerGroupeEtudiantGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerGroupeEtudiantGUI window = new CreerGroupeEtudiantGUI();
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
	public CreerGroupeEtudiantGUI() {
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
		
		JLabel lblCreerGroupeEtudiatn = new JLabel("Creer Groupe Etudiant");
		lblCreerGroupeEtudiatn.setBounds(226, 25, 256, 15);
		frame.getContentPane().add(lblCreerGroupeEtudiatn);
		
		JLabel lblNumroGroupe = new JLabel("Numéro Groupe");
		lblNumroGroupe.setBounds(45, 96, 122, 15);
		frame.getContentPane().add(lblNumroGroupe);
		
		textField = new JTextField();
		textField.setBounds(226, 94, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCapacitMax = new JLabel("Capacité Max");
		lblCapacitMax.setBounds(45, 170, 108, 15);
		frame.getContentPane().add(lblCapacitMax);
		
		textField_1 = new JTextField();
		textField_1.setBounds(226, 168, 114, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		final JLabel label = new JLabel("");
		label.setBounds(23, 425, 631, 15);
		frame.getContentPane().add(label);
		
		JButton btnCreer = new JButton("Creer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération des données
				int num_groupe = Integer.parseInt(textField.getText());
				int capacite_max = Integer.parseInt(textField_1.getText());
				
				// Création d'un groupe d'étudiant à partir des données
				GroupeEtudiant g = new GroupeEtudiant(num_groupe, capacite_max);
				
				// Création d'une instance de GroupeEtudiantDAO
				GroupeEtudiantDAO gdao = new GroupeEtudiantDAO();
				
				// Ajout du groupe
				ResultMessage resultMessage = gdao.add(g);
				
				label.setForeground(resultMessage.getColor());
				label.setText(resultMessage.getMessage());
			}
		});
		btnCreer.setBounds(212, 339, 117, 25);
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
		
	}

}
