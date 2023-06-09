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
import other.ResultMessage;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;

public class CreerCoursGUI {

	private JFrame frame;
	private JTextField textFieldNom;
	private JTextField textFieldMh;
	private JTextField textFieldCours;
	private JTextField textFieldTD;
	private JTextField textFieldTP;
	private JTextField textFieldExamen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreerCoursGUI window = new CreerCoursGUI();
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
	public CreerCoursGUI() {
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
		
		JLabel lblCreerCours = new JLabel("Creer Cours");
		lblCreerCours.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCreerCours.setBounds(266, 51, 206, 15);
		frame.getContentPane().add(lblCreerCours);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNom.setBounds(163, 108, 70, 15);
		frame.getContentPane().add(lblNom);
		
		JLabel lblMasseHoraire = new JLabel("Masse Horaire");
		lblMasseHoraire.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMasseHoraire.setBounds(163, 143, 103, 15);
		frame.getContentPane().add(lblMasseHoraire);
		
		JLabel lblNombreHeuresCours = new JLabel("Nombre Heures Cours");
		lblNombreHeuresCours.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreHeuresCours.setBounds(163, 181, 174, 15);
		frame.getContentPane().add(lblNombreHeuresCours);
		
		JLabel lblNombreHeuresTd = new JLabel("Nombre Heures TD");
		lblNombreHeuresTd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreHeuresTd.setBounds(163, 218, 174, 15);
		frame.getContentPane().add(lblNombreHeuresTd);
		
		JLabel lblNombreHeuresTp = new JLabel("Nombre Heures TP");
		lblNombreHeuresTp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreHeuresTp.setBounds(163, 255, 174, 15);
		frame.getContentPane().add(lblNombreHeuresTp);
		
		JLabel lblNombreHeuresExamen = new JLabel("Nombre Heures Examen");
		lblNombreHeuresExamen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreHeuresExamen.setBounds(163, 292, 174, 15);
		frame.getContentPane().add(lblNombreHeuresExamen);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(367, 106, 169, 19);
		frame.getContentPane().add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldMh = new JTextField();
		textFieldMh.setColumns(10);
		textFieldMh.setBounds(367, 141, 169, 19);
		frame.getContentPane().add(textFieldMh);
		
		textFieldCours = new JTextField();
		textFieldCours.setColumns(10);
		textFieldCours.setBounds(367, 179, 169, 19);
		frame.getContentPane().add(textFieldCours);
		
		textFieldTD = new JTextField();
		textFieldTD.setColumns(10);
		textFieldTD.setBounds(367, 216, 169, 19);
		frame.getContentPane().add(textFieldTD);
		
		textFieldTP = new JTextField();
		textFieldTP.setColumns(10);
		textFieldTP.setBounds(367, 253, 169, 19);
		frame.getContentPane().add(textFieldTP);
		
		textFieldExamen = new JTextField();
		textFieldExamen.setColumns(10);
		textFieldExamen.setBounds(367, 290, 169, 19);
		frame.getContentPane().add(textFieldExamen);
		
		final JLabel label = new JLabel("");
		label.setBounds(26, 425, 663, 15);
		frame.getContentPane().add(label);
		
		JButton btnCreer = new JButton("Creer");
		btnCreer.setBackground(new Color(255, 0, 0));
		btnCreer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération des données
				String nom = textFieldNom.getText();
				int masseHoraire = Integer.parseInt(textFieldMh.getText());
				int nbreHeuresCours = Integer.parseInt(textFieldCours.getText());
				int nbreHeuresTD = Integer.parseInt(textFieldTD.getText());
				int nbreHeuresTP = Integer.parseInt(textFieldTP.getText());
				int nbreHeuresExamen = Integer.parseInt(textFieldExamen.getText());
				
				// Vérification de la somme des nombres d'heures de Cours, TD, TP et Examen
				if((nbreHeuresCours + nbreHeuresTD + nbreHeuresTP + nbreHeuresExamen) != masseHoraire) {
					label.setForeground(Color.RED);
					label.setText("La répartition des heures ne correspond pas à la masse horaire");
					return;
				}
				
				// Création d'un cours à partir des données
				Cours cours = new Cours(nom, masseHoraire, nbreHeuresCours, nbreHeuresTD, nbreHeuresTP, nbreHeuresExamen);
				
				// Création d'une instance de coursDAO
				CoursDAO coursDAO = new CoursDAO();
				
				// Ajout du cours
				ResultMessage resultMessage = coursDAO.add(cours);
				
				label.setForeground(resultMessage.getColor());
				label.setText(resultMessage.getMessage());
			}
		});
		btnCreer.setBounds(419, 329, 117, 25);
		frame.getContentPane().add(btnCreer);
		frame.setVisible(true);
		
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
		btnRetout.setBounds(280, 408, 117, 25);
		frame.getContentPane().add(btnRetout);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\petit logo esig.jpg"));
		lblNewLabel.setBounds(0, 0, 136, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\planning.png"));
		lblNewLabel_1.setBounds(482, 0, 224, 141);
		frame.getContentPane().add(lblNewLabel_1);
		
		
	}

}
