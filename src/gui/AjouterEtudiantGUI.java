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

import dao.EtudiantDAO;
import model.Etudiant;
import other.ResultMessage;

import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AjouterEtudiantGUI {

	private JFrame frame;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldGroupe;
	private JTextField textFieldQuota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterEtudiantGUI window = new AjouterEtudiantGUI();
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
	public AjouterEtudiantGUI() {
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
		
		JLabel lblNewLabel = new JLabel("Ajout d'un étudiant");
		lblNewLabel.setBounds(-70, 50, 720, 48);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(274, 127, 175, 30);
		panel.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(274, 180, 175, 30);
		panel.add(textFieldPrenom);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(59, 134, 70, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(59, 187, 70, 15);
		panel.add(lblNewLabel_1_1);
		
		final Choice choice = new Choice();
		choice.setBounds(274, 231, 175, 18);
		choice.addItem("FISA");
		choice.addItem("FISE");
		panel.add(choice);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Filiere");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(59, 231, 70, 15);
		panel.add(lblNewLabel_1_1_1);
		
		final JLabel label = new JLabel("");
		label.setBounds(25, 424, 209, 15);
		panel.add(label);
		
		final JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGroupe.setBounds(59, 274, 70, 15);
		panel.add(lblGroupe);
		
		textFieldGroupe = new JTextField();
		textFieldGroupe.setBounds(274, 272, 175, 19);
		panel.add(textFieldGroupe);
		textFieldGroupe.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération des données
				String nom = textFieldNom.getText();
				String prenom = textFieldNom.getText();
				int groupe = Integer.parseInt(textFieldGroupe.getText());
				String filiere = choice.getItem(choice.getSelectedIndex());
				int quota = Integer.parseInt(textFieldQuota.getText());
				
				// Création d'un étudiant à partir des données récupérées
				Etudiant etudiant = new Etudiant(nom, prenom, groupe, filiere, quota);
				
				// Création d'une instance de EtudiantDAO
				EtudiantDAO etudiantDAO = new EtudiantDAO();
				
				// Ajout de l'étudiant
				ResultMessage resultMessage = etudiantDAO.add(etudiant);
				
				label.setForeground(resultMessage.getColor());
				label.setText(resultMessage.getMessage());
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(364, 370, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("./images/logo_esigelec_boite-1.png"));
		lblNewLabel_3.setBounds(80, 0, 574, 202);
		panel.add(lblNewLabel_3);
		
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
		btnRetout.setBounds(286, 408, 117, 25);
		panel.add(btnRetout);
		
		JLabel lblQuota = new JLabel("Quota");
		lblQuota.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuota.setBounds(59, 329, 70, 15);
		panel.add(lblQuota);
		
		textFieldQuota = new JTextField();
		textFieldQuota.setBounds(274, 327, 175, 19);
		panel.add(textFieldQuota);
		textFieldQuota.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\petit logo esig.jpg"));
		lblNewLabel_2.setBounds(0, 10, 139, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\Ajout ens-etudiant.png"));
		lblNewLabel_4.setBounds(492, 0, 214, 159);
		panel.add(lblNewLabel_4);
		
		
		
		
	}
}
