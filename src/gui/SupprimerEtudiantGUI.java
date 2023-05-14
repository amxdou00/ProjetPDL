package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupprimerEtudiantGUI {

	private JFrame frame;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupprimerEtudiantGUI window = new SupprimerEtudiantGUI();
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
	public SupprimerEtudiantGUI() {
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
		
		JLabel lblSupprimerEtudiant = new JLabel("Supprimer Etudiant");
		lblSupprimerEtudiant.setBounds(223, 34, 234, 15);
		frame.getContentPane().add(lblSupprimerEtudiant);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(242, 139, 114, 19);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(103, 141, 70, 15);
		frame.getContentPane().add(lblId);
		
		JButton btnSupprimer = new JButton("Supprimer");
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
		frame.setVisible(true);
	}

}
