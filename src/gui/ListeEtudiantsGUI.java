package gui;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.ConnectionDAO;
import model.Etudiant;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ListeEtudiantsGUI {

	private JFrame frame;
	private JTextField textFieldGroupe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeEtudiantsGUI window = new ListeEtudiantsGUI();
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
	public ListeEtudiantsGUI() {
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
		frame.getContentPane().setLayout(null);
		
		final JTextArea text_area = new JTextArea(10, 30);
		text_area.setText("");
		text_area.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(text_area);
		scrollPane.setBounds(new Rectangle(20, 50, 680, 400));
		frame.getContentPane().add(scrollPane);
		
		JLabel lblListeDesEtudiants = new JLabel("Liste des Etudiants");
		lblListeDesEtudiants.setBounds(20, 0, 152, 15);
		frame.getContentPane().add(lblListeDesEtudiants);
		
		JLabel lblNumroGroupe = new JLabel("Numéro Groupe");
		lblNumroGroupe.setBounds(20, 23, 119, 15);
		frame.getContentPane().add(lblNumroGroupe);
		
		textFieldGroupe = new JTextField();
		textFieldGroupe.setBounds(154, 21, 114, 19);
		frame.getContentPane().add(textFieldGroupe);
		textFieldGroupe.setColumns(10);
		
		JButton btnVoirListe = new JButton("Voir Liste");
		btnVoirListe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_area.setText("");
				int numero_groupe = Integer.parseInt(textFieldGroupe.getText());
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				ArrayList<Etudiant> liste = new ArrayList<Etudiant>();
				
				try {
					String query = "select nom, prenom from etudiant where numero_groupe = ?";
					con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
					ps = con.prepareStatement(query);
					ps.setInt(1, numero_groupe);
					rs = ps.executeQuery();
					while(rs.next()) {
						liste.add(new Etudiant(rs.getString("nom"), rs.getString("prenom")));
					}
					
					if(liste.size() == 0) {
						text_area.setText("Aucun Etudiant dans ce groupe");
						return;
					}
					
					for(Etudiant etudiant: liste) {
						text_area.append(etudiant.display());
					}
					
				} catch(Exception ee) {
					ee.printStackTrace();
				} finally {
					
				}
			}
		});
		btnVoirListe.setBounds(286, 18, 117, 25);
		frame.getContentPane().add(btnVoirListe);
		
		JButton btnRetout = new JButton("Retour");
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(537, 15, 117, 25);
		frame.getContentPane().add(btnRetout);

	}
}
