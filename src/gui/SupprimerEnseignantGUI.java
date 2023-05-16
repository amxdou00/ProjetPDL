package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import dao.EnseignantDAO;
import other.ResultMessage;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupprimerEnseignantGUI {

	private JFrame frame;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupprimerEnseignantGUI window = new SupprimerEnseignantGUI();
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
	public SupprimerEnseignantGUI() {
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

		JLabel lblSupprimerEnseignant = new JLabel("Supprimer Enseignant");
		lblSupprimerEnseignant.setBounds(223, 34, 234, 15);
		frame.getContentPane().add(lblSupprimerEnseignant);

		textFieldId = new JTextField();
		textFieldId.setBounds(242, 139, 114, 19);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(103, 141, 70, 15);
		frame.getContentPane().add(lblId);

		final JLabel label = new JLabel("");
		label.setBounds(24, 425, 623, 15);
		frame.getContentPane().add(label);
		frame.setVisible(true);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération des données
				int id = Integer.parseInt(textFieldId.getText());

				// Création d'une instance de EnseignantDAO
				EnseignantDAO enseignantDAO = new EnseignantDAO();

				// Mise à jour des données de l'enseignant
				ResultMessage resultMessage = enseignantDAO.delete(id);

				label.setForeground(resultMessage.getColor());
				label.setText(resultMessage.getMessage());
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
