package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import dao.EnseignantDAO;
import model.Enseignant;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AjouterEnseignantGUI {

	private JFrame frame;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterEnseignantGUI window = new AjouterEnseignantGUI();
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
	public AjouterEnseignantGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajout d'un enseignant");
		lblNewLabel.setBounds(-41, 35, 720, 48);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(290, 117, 175, 30);
		panel.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(290, 181, 175, 30);
		panel.add(textFieldPrenom);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(83, 124, 70, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(83, 188, 70, 15);
		panel.add(lblNewLabel_1_1);
		
		final JLabel label = new JLabel("");
		label.setBounds(45, 425, 447, 15);
		panel.add(label);
		
		final JLabel lblNumTel = new JLabel("NumeroTel");
		lblNumTel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumTel.setBounds(83, 250, 114, 15);
		panel.add(lblNumTel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(290, 248, 175, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(348, 315, 117, 25);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Récupération des données
				String nom_enseignant = textFieldNom.getText();
				String prenom_enseignant = textFieldPrenom.getText();
				String num_tel = textField_2.getText();
				
				// Création d'un enseignant à partir des données récupérées
				Enseignant ens = new Enseignant(prenom_enseignant, nom_enseignant, num_tel);
				
				// Création d'une instance de EnseignantDAO
				EnseignantDAO edao = new EnseignantDAO();
				
				// Ajout de l'enseignant
				edao.add(ens);
				
				label.setForeground(Color.green);
				label.setText("L'enseignant a bien été ajouté !");
			}
			
		});
		
		panel.add(btnNewButton);
		
		JButton btnRetout = new JButton("Retour");
		btnRetout.setBackground(new Color(255, 0, 0));
		btnRetout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(290, 390, 117, 25);
		panel.add(btnRetout);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\petit logo esig.jpg"));
		lblNewLabel_2.setBounds(0, 0, 137, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\add-user-icon-256.png"));
		lblNewLabel_3.setBounds(485, 0, 221, 172);
		panel.add(lblNewLabel_3);
		
		
		
		
	}
}
