package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ConnectionDAO;

import javax.swing.JButton;
import java.awt.Color;

public class DeclencherRattrapageGUI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeclencherRattrapageGUI window = new DeclencherRattrapageGUI();
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
	public DeclencherRattrapageGUI() {
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
		
		JLabel lblDateDesRattrapages = new JLabel("Date des rattrapages");
		lblDateDesRattrapages.setBounds(39, 207, 178, 15);
		frame.getContentPane().add(lblDateDesRattrapages);
		
		textField = new JTextField();
		textField.setBounds(249, 205, 232, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JLabel label = new JLabel("");
		label.setForeground(Color.GREEN);
		label.setBounds(59, 425, 369, 15);
		frame.getContentPane().add(label);
		
		JButton btnDclencher = new JButton("Déclencher");
		btnDclencher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = textField.getText();
				String message = "Vous avez un rattrapage prévu pour le "+date;
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				String query = "select a.id from etudiant a, absence b where b.id_etudiant = a.id and b.justifiee = 1 and b.lors_examen = 1";
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
						query = "update etudiant set message = CONCAT(message, ?) where id = ?";
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
				        ps = con.prepareStatement(query);
				        ps.setString(1, message);
				        ps.setInt(2, liste_id_etudiants.get(i));
				        returnValue = ps.executeUpdate();
				        label.setText("Les rattrapages ont été déclenchés pour le "+date);
				        
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
		btnDclencher.setBounds(276, 369, 117, 25);
		frame.getContentPane().add(btnDclencher);
		
		JLabel lblDclencherRattrapages = new JLabel("Déclencher Rattrapages");
		lblDclencherRattrapages.setBounds(220, 27, 208, 15);
		frame.getContentPane().add(lblDclencherRattrapages);
		
		JLabel lblLestudiantligibles = new JLabel("Les étudiants éligibles recevront un message leur indiquant la date des rattrapages");
		lblLestudiantligibles.setBounds(34, 72, 640, 15);
		frame.getContentPane().add(lblLestudiantligibles);
		frame.setVisible(true);
		
		JButton btnRetout = new JButton("Retour");
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(537, 368, 117, 25);
		frame.getContentPane().add(btnRetout);
		
		
	}

}
