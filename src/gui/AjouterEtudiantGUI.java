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

import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AjouterEtudiantGUI {

	private JFrame frame;
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
		lblNewLabel.setBounds(-14, 192, 720, 48);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(77, 260, 175, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(399, 260, 175, 30);
		panel.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(77, 235, 70, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setBounds(399, 235, 70, 15);
		panel.add(lblNewLabel_1_1);
		
		final Choice choice = new Choice();
		choice.setBounds(399, 342, 175, 18);
		choice.addItem("FISA");
		choice.addItem("FISE");
		panel.add(choice);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Filiere");
		lblNewLabel_1_1_1.setBounds(399, 316, 70, 15);
		panel.add(lblNewLabel_1_1_1);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(288, 424, 209, 15);
		panel.add(lblNewLabel_2);
		
		final JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setBounds(77, 316, 70, 15);
		panel.add(lblGroupe);
		
		textField_2 = new JTextField();
		textField_2.setBounds(77, 341, 175, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(281, 397, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\logo_esigelec_boite-1.png"));
		lblNewLabel_3.setBounds(79, -5, 574, 202);
		panel.add(lblNewLabel_3);
		
		JButton btnRetout = new JButton("Retour");
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(537, 368, 117, 25);
		panel.add(btnRetout);
		
		
		
		
	}
}
