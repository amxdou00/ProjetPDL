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

public class ModifierAbsenceGUI {

	private JFrame frame;
	private JTextField textFieldId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierAbsenceGUI window = new ModifierAbsenceGUI();
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
	public ModifierAbsenceGUI() {
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
		
		JLabel lblModifierAbsence = new JLabel("Modifier Absence");
		lblModifierAbsence.setBounds(225, 28, 216, 15);
		frame.getContentPane().add(lblModifierAbsence);
		
		JLabel lblIdentifiantAbsence = new JLabel("Identifiant Absence");
		lblIdentifiantAbsence.setBounds(70, 94, 148, 15);
		frame.getContentPane().add(lblIdentifiantAbsence);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(268, 92, 114, 19);
		frame.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		final JLabel message = new JLabel("");
		message.setForeground(Color.GREEN);
		message.setBounds(70, 425, 572, 15);
		frame.getContentPane().add(message);
		frame.setVisible(true);
		
		JButton btnMarquerJustifie = new JButton("Marquer Justifiée");
		btnMarquerJustifie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				int returnValue = 0;
				String query = "update absence set justifiee = 1 where id = "+id_absence;
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        returnValue = ps.executeUpdate();
			        message.setText("L'absence a été justifiée!");

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
		btnMarquerJustifie.setBounds(268, 173, 273, 25);
		frame.getContentPane().add(btnMarquerJustifie);
		
		JButton btnMarquerNonJustifie = new JButton("Marquer non Justifiée");
		btnMarquerNonJustifie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				int returnValue = 0;
				String query = "update absence set justifiee = 0 where id = "+id_absence;
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        returnValue = ps.executeUpdate();
			        message.setText("L'absence a été marquée non justifiée!");

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
		btnMarquerNonJustifie.setBounds(268, 210, 273, 25);
		frame.getContentPane().add(btnMarquerNonJustifie);
		
		JButton btnMarquerDansQuota = new JButton("Marquer Dans Quota");
		btnMarquerDansQuota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				int returnValue = 0;
				String query = "update absence set hors_quota = 0 where id = "+id_absence;
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        returnValue = ps.executeUpdate();
			        message.setText("L'absence a été marquée 'Dans quota'!");

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
		btnMarquerDansQuota.setBounds(268, 247, 273, 25);
		frame.getContentPane().add(btnMarquerDansQuota);
		
		JButton btnMarquerHorsQuota = new JButton("Marquer Hors Quota");
		btnMarquerHorsQuota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				int returnValue = 0;
				String query = "update absence set hors_quota = 1 where id = "+id_absence;
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        returnValue = ps.executeUpdate();
			        message.setText("L'absence a été marquée hors quota!");

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
		btnMarquerHorsQuota.setBounds(268, 284, 273, 25);
		frame.getContentPane().add(btnMarquerHorsQuota);
		
		JButton btnMarquerLorsDun = new JButton("Marquer lors d'un examen");
		btnMarquerLorsDun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				int returnValue = 0;
				String query = "update absence set lors_examen = 1 where id = "+id_absence;
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        returnValue = ps.executeUpdate();
			        message.setText("L'absence a été marquée comme étant 'lors d'un examen'!");

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
		btnMarquerLorsDun.setBounds(268, 321, 273, 25);
		frame.getContentPane().add(btnMarquerLorsDun);
		
		JButton btnMarquerPasLors = new JButton("Marquer pas lors d'un examen");
		btnMarquerPasLors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textFieldId.getText());
				Connection con = null;
				PreparedStatement ps = null;
				int returnValue = 0;
				String query = "update absence set lors_examen = 0 where id = "+id_absence;
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        returnValue = ps.executeUpdate();
			        message.setText("L'absence a été marquée comme n'étant pas 'lors d'un examen'!");

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
		btnMarquerPasLors.setBounds(268, 358, 273, 25);
		frame.getContentPane().add(btnMarquerPasLors);
		
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
