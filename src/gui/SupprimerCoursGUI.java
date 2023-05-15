package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.CoursDAO;
import dao.EtudiantDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupprimerCoursGUI {

	private JFrame frame;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupprimerCoursGUI window = new SupprimerCoursGUI();
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
	public SupprimerCoursGUI() {
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
		
		JLabel lblSupprimerCours = new JLabel("Supprimer Cours");
		lblSupprimerCours.setBounds(223, 34, 234, 15);
		frame.getContentPane().add(lblSupprimerCours);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(242, 139, 114, 19);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(103, 141, 70, 15);
		frame.getContentPane().add(lblId);
		
		final JLabel label = new JLabel("");
		label.setBounds(12, 425, 635, 15);
		frame.getContentPane().add(label);
		frame.setVisible(true);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération de l'identifiant du cours
				int id = Integer.parseInt(textFieldId.getText());
				
				// Création d'une instance de coursDAO
				CoursDAO coursDAO = new CoursDAO();
				
				// Suppression du cours
				coursDAO.delete(id);
				
				label.setForeground(Color.green);
				label.setText("Le cours a bien été supprimé");
			}
		});
		btnSupprimer.setBounds(223, 360, 117, 25);
		frame.getContentPane().add(btnSupprimer);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetour.setBounds(530, 360, 117, 25);
		frame.getContentPane().add(btnRetour);
		
		
	}
}
