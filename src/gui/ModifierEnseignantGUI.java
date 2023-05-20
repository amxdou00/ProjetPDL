package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.EnseignantDAO;
import model.Enseignant;
import other.ResultMessage;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifierEnseignantGUI {

	private JFrame frame;
	private JTextField textFieldId;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierEnseignantGUI window = new ModifierEnseignantGUI();
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
	public ModifierEnseignantGUI() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblModifierEnseignant = new JLabel("Modifier Enseignant");
		lblModifierEnseignant.setBounds(291, 12, 153, 15);
		frame.getContentPane().add(lblModifierEnseignant);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(32, 89, 70, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(32, 116, 70, 15);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(32, 151, 70, 15);
		frame.getContentPane().add(lblPrenom);
		
		JLabel lblNumTel = new JLabel("Numero Telephone");
		lblNumTel.setBounds(32, 187, 70, 15);
		frame.getContentPane().add(lblNumTel);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(132, 87, 114, 19);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(132, 114, 114, 19);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(132, 149, 114, 19);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(132, 185, 114, 19);
		frame.getContentPane().add(textField_2);
		frame.setVisible(true);
		
		final JLabel label = new JLabel("");
		label.setBounds(32, 425, 607, 15);
		frame.getContentPane().add(label);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération des données
				int id = Integer.parseInt(textFieldId.getText());
				String nom = textField.getText();
				String prenom = textField_1.getText();
				String num= textField_2.getText();
				
				// Création d'un enseignant à partir des données récupérées
				Enseignant enseignant = new Enseignant(id, prenom, nom, num);
				
				// Création d'une instance de EnseignantDAO
				EnseignantDAO enseignantDAO = new EnseignantDAO();
				
				// Mise à jour des données de l'enseignant
				ResultMessage resultMessage = enseignantDAO.update(enseignant);
				
				label.setForeground(resultMessage.getColor());
				label.setText(resultMessage.getMessage());
			}
		});
		btnValider.setBounds(272, 368, 117, 25);
		frame.getContentPane().add(btnValider);
		
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
