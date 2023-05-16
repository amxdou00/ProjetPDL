package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ConnectionDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AffecterCoursEnseignantGUI {

	private JFrame frame;
	private JTextField textFieldCours;
	private JTextField textFieldEns;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AffecterCoursEnseignantGUI window = new AffecterCoursEnseignantGUI();
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
	public AffecterCoursEnseignantGUI() {
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
		
		JLabel lblIdentifiantCours = new JLabel("Identifiant Cours");
		lblIdentifiantCours.setBounds(116, 99, 178, 15);
		frame.getContentPane().add(lblIdentifiantCours);
		
		JLabel lblIdentifiantEnseignant = new JLabel("Identifiant Enseignant");
		lblIdentifiantEnseignant.setBounds(116, 181, 178, 15);
		frame.getContentPane().add(lblIdentifiantEnseignant);
		
		JLabel lblAffecterCoursA = new JLabel("Affecter Cours A Un Enseignant");
		lblAffecterCoursA.setBounds(245, 27, 301, 15);
		frame.getContentPane().add(lblAffecterCoursA);
		
		textFieldCours = new JTextField();
		textFieldCours.setBounds(329, 97, 114, 19);
		frame.getContentPane().add(textFieldCours);
		textFieldCours.setColumns(10);
		
		textFieldEns = new JTextField();
		textFieldEns.setBounds(329, 179, 114, 19);
		frame.getContentPane().add(textFieldEns);
		textFieldEns.setColumns(10);
		
		final JLabel label = new JLabel("");
		label.setForeground(Color.GREEN);
		label.setBounds(152, 408, 340, 15);
		frame.getContentPane().add(label);
		frame.setVisible(true);
		
		JButton btnAffecter = new JButton("Affecter");
		btnAffecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_enseignant = Integer.parseInt(textFieldEns.getText());
				int id_cours = Integer.parseInt(textFieldCours.getText());
				
				Connection con = null;
				PreparedStatement ps = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				String query = "insert into enseignant_cours (id_enseignant, id_cours) values ("+id_enseignant+", "+id_cours+")";
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        returnValue = ps.executeUpdate();
			        label.setText("Affectation réussie!");

				} catch (Exception ee) {
				        ee.printStackTrace();
				} finally {
				        try {
				                if (ps != null) 
				                        ps.close();
				        } catch (Exception ignore) {}
	
				        try {
				                if (con != null) 
				                        con.close();
				        } catch (Exception ignore) {}
				}
		
			}
		});
		btnAffecter.setBounds(259, 338, 117, 25);
		frame.getContentPane().add(btnAffecter);
		
		JButton btnRetout = new JButton("Retour");
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(537, 368, 117, 25);
		frame.add(btnRetout);
		
		
	}

}
