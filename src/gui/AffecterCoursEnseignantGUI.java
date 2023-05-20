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
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Font;

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
		lblIdentifiantCours.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdentifiantCours.setBounds(160, 188, 178, 15);
		frame.getContentPane().add(lblIdentifiantCours);
		
		JLabel lblIdentifiantEnseignant = new JLabel("Identifiant Enseignant");
		lblIdentifiantEnseignant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdentifiantEnseignant.setBounds(160, 104, 178, 15);
		frame.getContentPane().add(lblIdentifiantEnseignant);
		
		JLabel lblAffecterCoursA = new JLabel("Affecter Cours A Un Enseignant");
		lblAffecterCoursA.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAffecterCoursA.setBounds(248, 10, 308, 32);
		frame.getContentPane().add(lblAffecterCoursA);
		
		textFieldCours = new JTextField();
		textFieldCours.setBounds(394, 102, 178, 19);
		frame.getContentPane().add(textFieldCours);
		textFieldCours.setColumns(10);
		
		textFieldEns = new JTextField();
		textFieldEns.setBounds(394, 186, 178, 19);
		frame.getContentPane().add(textFieldEns);
		textFieldEns.setColumns(10);
		
		final JLabel label = new JLabel("");
		label.setForeground(Color.GREEN);
		label.setBounds(152, 408, 340, 15);
		frame.getContentPane().add(label);
		frame.setVisible(true);
		
		JButton btnAffecter = new JButton("Affecter");
		btnAffecter.setBackground(new Color(255, 0, 0));
		btnAffecter.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		btnAffecter.setBounds(427, 252, 117, 25);
		frame.getContentPane().add(btnAffecter);
		
		JButton btnRetout = new JButton("Retour");
		btnRetout.setBackground(new Color(255, 0, 0));
		btnRetout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(286, 352, 117, 25);
		frame.getContentPane().add(btnRetout);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\petit logo esig.jpg"));
		lblNewLabel.setBounds(0, 0, 136, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ndeye\\Downloads\\staff-annual-leave-planner-software-1024x677.jpg"));
		lblNewLabel_1.setBounds(0, 0, 706, 443);
		frame.getContentPane().add(lblNewLabel_1);
		
		
	}
}
