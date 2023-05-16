package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ConnectionDAO;

import javax.swing.JButton;
import java.awt.Color;

public class TraiterJustificatifAbsenceGUI {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TraiterJustificatifAbsenceGUI window = new TraiterJustificatifAbsenceGUI();
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
	public TraiterJustificatifAbsenceGUI() {
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
		
		JLabel lblTraiterJustificatif = new JLabel("Traiter Justificatif");
		lblTraiterJustificatif.setBounds(256, 25, 179, 15);
		frame.getContentPane().add(lblTraiterJustificatif);
		
		JLabel lblIdentifiantAbsence = new JLabel("Identifiant Absence");
		lblIdentifiantAbsence.setBounds(37, 92, 149, 15);
		frame.getContentPane().add(lblIdentifiantAbsence);
		
		textField = new JTextField();
		textField.setBounds(235, 90, 171, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JLabel errLabel = new JLabel("");
		errLabel.setForeground(Color.RED);
		errLabel.setBounds(235, 137, 200, 15);
		frame.getContentPane().add(errLabel);
		
		JButton btnTlchargerJustificatif = new JButton("Télécharger Justificatif");
		btnTlchargerJustificatif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textField.getText());
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement("select justificatif, nom_justificatif from absence where id = ?");
			        ps.setInt(1, id_absence);
			        rs = ps.executeQuery();

			        if (rs.next()) {
			        	if(rs.getBlob("justificatif") != null) {
				            Blob fileData = rs.getBlob("justificatif");
				            
				            
				            String fileName = rs.getString("nom_justificatif");
				            
				            // Save the file data to a file
				            InputStream inputStream = fileData.getBinaryStream();
				            FileOutputStream outputStream = new FileOutputStream("/home/kxizen/Downloads/" + fileName);
	
				            byte[] buffer = new byte[4096];
				            int bytesRead = -1;
				            while ((bytesRead = inputStream.read(buffer)) != -1) {
				                outputStream.write(buffer, 0, bytesRead);
				            }
				            
				            // Close the streams
				            inputStream.close();
				            outputStream.close();
				            
			        	}
			        	else {
			        		errLabel.setText("Cette absence n'a pas de justificatif!");
			        	}
			        }
			        else {
			        	errLabel.setText("Identifiant invalide!");
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
	
				
			}
		});
		btnTlchargerJustificatif.setBounds(470, 87, 208, 25);
		frame.getContentPane().add(btnTlchargerJustificatif);
		
		final JLabel labelValide = new JLabel("");
		labelValide.setForeground(Color.GREEN);
		labelValide.setBounds(188, 425, 349, 15);
		frame.getContentPane().add(labelValide);
		
		JButton btnValiderJustificatif = new JButton("Valider Justificatif");
		btnValiderJustificatif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_absence = Integer.parseInt(textField.getText());
				Connection con = null;
				PreparedStatement ps = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				
				try {
			        String query = "update absence set justifiee = 1 where id = ?";
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        ps.setInt(1, id_absence);
			        returnValue = ps.executeUpdate();

			        labelValide.setText("L'absence a été justifiée");




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
		btnValiderJustificatif.setBounds(235, 368, 202, 25);
		frame.getContentPane().add(btnValiderJustificatif);
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
