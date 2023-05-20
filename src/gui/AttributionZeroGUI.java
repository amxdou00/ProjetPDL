package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dao.ConnectionDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AttributionZeroGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttributionZeroGUI window = new AttributionZeroGUI();
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
	public AttributionZeroGUI() {
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
		
		JLabel lblAttributionZeros = new JLabel("Attribution Zeros");
		lblAttributionZeros.setBounds(257, 31, 191, 15);
		frame.getContentPane().add(lblAttributionZeros);
		
		JLabel lblTousLestudiants = new JLabel("Tous les étudiants ayant une absence non justifiée lors d'un examen, y obtiendront la note 0");
		lblTousLestudiants.setBounds(12, 93, 696, 15);
		frame.getContentPane().add(lblTousLestudiants);
		
		final JLabel label = new JLabel("");
		label.setBounds(12, 411, 579, 15);
		frame.getContentPane().add(label);
		frame.setVisible(true);
		
		JButton btnAttribuerZero = new JButton("Attribuer Zeros");
		btnAttribuerZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				String query = "select a.id from etudiant a, absence b where b.id_etudiant = a.id and b.justifiee = 0 and b.lors_examen = 1";
				ArrayList<Integer> liste_id_etudiants = new ArrayList<Integer>();
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        rs = ps.executeQuery();
			        
			        while(rs.next()) {
			        	liste_id_etudiants.add(rs.getInt("id"));
			        }
			        
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
				
				for(int i = 0; i<liste_id_etudiants.size(); i++) {
					try {
						query = "update note set note = 0 where id = ?";
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
				        ps = con.prepareStatement(query);
				        ps.setInt(1, liste_id_etudiants.get(i));
				        returnValue = ps.executeUpdate();
				        label.setText("Opération terminée!");
				        
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
			}
		});
		btnAttribuerZero.setBounds(270, 192, 167, 25);
		frame.getContentPane().add(btnAttribuerZero);
		
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
