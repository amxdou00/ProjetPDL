package gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ConnectionDAO;
import model.Absence;
import model.Etudiant;
import java.awt.Color;

public class PanelDepotJustificatif extends JPanel {
	private File selectedFile;

	/**
	 * Create the panel.
	 */
	public PanelDepotJustificatif(final Etudiant etudiant) {
		setBounds(0, 0, 530, 451);
		setLayout(null);
		
		JLabel titre = new JLabel("Depot justificatif");
		titre.setBounds(204, 33, 137, 15);
		add(titre);
		
		JLabel lblIdentifiantAbsence = new JLabel("Identifiant Absence");
		lblIdentifiantAbsence.setBounds(22, 86, 148, 15);
		add(lblIdentifiantAbsence);
		
		final JLabel fileSelectLabel = new JLabel("");
		fileSelectLabel.setForeground(Color.GREEN);
		fileSelectLabel.setBounds(204, 180, 95, 15);
		add(fileSelectLabel);
		
		final JTextField textField = new JTextField();
		textField.setBounds(204, 84, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		JButton fileButton = new JButton("Select File");
		fileButton.setBounds(204, 143, 130, 25);
		add(fileButton);
		fileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(new Frame());
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    fileSelectLabel.setText("Fichier prêt!");
                }
            }
        });
		
		JLabel lblDeposerJustificatif = new JLabel("Deposer justificatif");
		lblDeposerJustificatif.setBounds(22, 153, 148, 15);
		add(lblDeposerJustificatif);
		
		JButton btnDposer = new JButton("Déposer");
		btnDposer.setBounds(182, 323, 117, 25);
		add(btnDposer);
		
		final JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(22, 403, 496, 15);
		add(errorLabel);
		
		
		
        btnDposer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Taking the user input
    			int identifiantAbsence = Integer.parseInt(textField.getText());
        		
        		
        		// Connecting to the database to check if the user input is valid
        		Connection con = null;
    			PreparedStatement ps = null;
    			ResultSet rs = null;
    			int returnValue = 0;
    			
    			try {
            		String query = "select * from absence where id = "+identifiantAbsence+"and id_etudiant = "+etudiant.getId();
    				con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
    				ps = con.prepareStatement(query);
    				rs = ps.executeQuery();

    				
					if(rs.next() && selectedFile != null) {
						byte[] fileContent = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
						String fileName = selectedFile.getName();
						
						ps = con.prepareStatement("update absence set justificatif = ?, nom_justificatif = ? where id = ?");
						ps.setBytes(1, fileContent);
						ps.setString(2, fileName);
						ps.setInt(2, identifiantAbsence);
						returnValue = ps.executeUpdate();
						
						errorLabel.setForeground(Color.GREEN);
						errorLabel.setText("Votre justificatif a été déposé");
					}
					else if(selectedFile == null) {
						errorLabel.setText("Veuillez d'abord choisir un fichier");
					}
					else {
						errorLabel.setText("Identifiant non valide");
					}
    						
    						
    						
    						
    			} catch (Exception ee) {
    				ee.printStackTrace();
    			}
    			
    			finally {
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
	}
}
