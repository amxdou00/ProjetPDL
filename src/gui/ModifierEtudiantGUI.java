package gui;

import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.EtudiantDAO;
import model.Etudiant;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifierEtudiantGUI {

	private JFrame frame;
	private JTextField textFieldId;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textFieldQuota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierEtudiantGUI window = new ModifierEtudiantGUI();
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
	public ModifierEtudiantGUI() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblModifierEtudiant = new JLabel("Modifier Etudiant");
		lblModifierEtudiant.setBounds(291, 12, 153, 15);
		frame.getContentPane().add(lblModifierEtudiant);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(32, 89, 70, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(32, 116, 70, 15);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(32, 151, 70, 15);
		frame.getContentPane().add(lblPrenom);
		
		JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setBounds(32, 187, 70, 15);
		frame.getContentPane().add(lblGroupe);
		
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
		
		JLabel lblFilire = new JLabel("Filière");
		lblFilire.setBounds(32, 241, 70, 15);
		frame.getContentPane().add(lblFilire);
		frame.setVisible(true);
		
		final Choice choice = new Choice();
		choice.setBounds(131, 230, 50, 30);
		choice.addItem("FISA");
		choice.addItem("FISE");
		frame.getContentPane().add(choice);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textFieldId.getText());
				String nom = textField.getText();
				String prenom = textField_1.getText();
				int groupe = Integer.parseInt(textField_2.getText());
				String filiere = choice.getItem(choice.getSelectedIndex());
				int quota = Integer.parseInt(textFieldQuota.getText());
				
				Etudiant etudiant = new Etudiant(id, nom, prenom, groupe, filiere, quota);
				EtudiantDAO etudiantDAO = new EtudiantDAO();
				
				etudiantDAO.update(etudiant);
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
		
		JLabel lblQuota = new JLabel("Quota");
		lblQuota.setBounds(32, 290, 70, 15);
		frame.getContentPane().add(lblQuota);
		
		textFieldQuota = new JTextField();
		textFieldQuota.setBounds(131, 288, 114, 19);
		frame.getContentPane().add(textFieldQuota);
		textFieldQuota.setColumns(10);
	}
}