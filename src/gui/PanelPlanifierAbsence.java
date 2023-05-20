package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
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
import javax.swing.SwingConstants;

import dao.ConnectionDAO;
import model.Etudiant;
import javax.swing.JTextField;

public class PanelPlanifierAbsence extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private File selectedFile;

	/**
	 * Create the panel.
	 */
	public PanelPlanifierAbsence(final Etudiant etudiant) {
		setBounds(new Rectangle(0, 0, 530, 451));
		setLayout(null);
		
		
		JLabel lblVeuillezChoisirUne = new JLabel("Plannification Absence");
		lblVeuillezChoisirUne.setFont(new Font("Dialog", Font.BOLD, 20));
		lblVeuillezChoisirUne.setHorizontalAlignment(SwingConstants.CENTER);
		lblVeuillezChoisirUne.setBounds(12, 12, 506, 50);
		add(lblVeuillezChoisirUne);
		
		JLabel lblIdentifiantCoursvoir = new JLabel("Identifiant Cours (Voir Planning)");
		lblIdentifiantCoursvoir.setBounds(28, 102, 227, 15);
		add(lblIdentifiantCoursvoir);
		
		textField = new JTextField();
		textField.setBounds(295, 100, 114, 19);
		add(textField);
		textField.setColumns(10);
		
		final JLabel fileSelectLabel = new JLabel("");
		fileSelectLabel.setForeground(Color.GREEN);
		fileSelectLabel.setBounds(223, 208, 95, 15);
		add(fileSelectLabel);
		
		JButton fileButton = new JButton("Choisir Justificatif");
		fileButton.setBounds(223, 170, 182, 25);
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
		
		
		
		JLabel lblJustificatif = new JLabel("Justificatif");
		lblJustificatif.setBounds(62, 175, 114, 15);
		add(lblJustificatif);
		
		final JLabel errorLabel = new JLabel("");
		errorLabel.setBounds(28, 413, 457, 15);
		add(errorLabel);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				// Taking the user input
    			int identifiantCours = Integer.parseInt(textField.getText());
        		
        		
        		// Connecting to the database to check if the user input is valid
        		Connection con = null;
    			PreparedStatement ps = null;
    			ResultSet rs = null;
    			@SuppressWarnings("unused")
				int returnValue = 0;
    			
    			try {
            		String query = "select * from planning where id = "+identifiantCours+"and id_groupe_etudiant = "+etudiant.getNumeroGroupe();
    				con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
    				ps = con.prepareStatement(query);
    				rs = ps.executeQuery();

    				
					if(rs.next() && selectedFile != null) {
						byte[] fileContent = Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath()));
						ps = con.prepareStatement("insert into absence (id_etudiant, id_planning, justifiee, justificatif) values (?, ?, ?, ?)");
						ps.setInt(1, etudiant.getId());
						ps.setInt(2, identifiantCours);
						ps.setInt(3, 0);
						ps.setBytes(4, fileContent);
						returnValue = ps.executeUpdate();
						
						
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
		btnValider.setBounds(201, 376, 117, 25);
		add(btnValider);
		
		
		
		
		
	}

}
