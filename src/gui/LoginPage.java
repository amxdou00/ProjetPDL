package gui;

import dao.ConnectionDAO;
import model.Enseignant;
import model.Etudiant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.Choice;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class LoginPage {

	private JFrame frame;
	/**
	 * @wbp.nonvisual location=58,14
	 */
	private final JDesktopPane Page_De_Connexion = new JDesktopPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
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
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		Page_De_Connexion.setToolTipText("");
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 706, 451);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final TextField textField = new TextField();
		textField.setBounds(33, 260, 215, 35);
		panel.add(textField);
		
		final TextField textField_1 = new TextField();
		textField_1.setEchoCharacter('*');
		textField_1.setBounds(419, 260, 215, 35);
		panel.add(textField_1);
		
		final Choice choice = new Choice();
		choice.setBounds(33, 352, 155, 40);
		panel.add(choice);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(102, 342, 275, 15);
		
		Button button = new Button("Connexion");
		button.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 12));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.RED);
		button.setBounds(464, 342, 108, 29);
		
		button.addActionListener(new ActionListener() {

			@SuppressWarnings("resource")
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected_function = choice.getItem(choice.getSelectedIndex());
				
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				
					try {
						
						con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
						ps = con.prepareStatement("SELECT * FROM " + selected_function.toLowerCase() + " WHERE email = ? AND password = ? ");
						ps.setString(1, textField.getText());
						ps.setString(2, hashPassword(textField_1.getText()));
						rs = ps.executeQuery();
						if(rs.next() == true) {
							if(selected_function == "Etudiant") {
								int id = rs.getInt("id");
								String nom = rs.getString("nom");
								String prenom = rs.getString("prenom");
								int numero_groupe = rs.getInt("numero_groupe");
								Etudiant etudiant = new Etudiant(id, nom, prenom, numero_groupe);
								
								
					            @SuppressWarnings("unused")
								EtudiantGUI etudiantGUI = new EtudiantGUI(etudiant);
					            frame.dispose();
							}
							else if(selected_function == "Enseignant") {
								int id = rs.getInt("id");
								String nom = rs.getString("nom");
								String prenom = rs.getString("prenom");
								ArrayList<Integer> liste_id_cours = new ArrayList<Integer>();
								String query = "select * from enseignant_cours where id_enseignant ="+id;
								ps = con.prepareStatement(query);
								rs = ps.executeQuery();
								
								while(rs.next()) {
									liste_id_cours.add(rs.getInt("id_cours"));
								}
								Enseignant enseignant = new Enseignant(id, nom, prenom, liste_id_cours);
								@SuppressWarnings("unused")
								EnseignantGUI enseignantGUI = new EnseignantGUI(enseignant);
								frame.dispose();
								
								
							}
							else if(selected_function == "Gestionnaire") {
								@SuppressWarnings("unused")
								GestionnaireGUI gestionnaireGUI = new GestionnaireGUI();
								frame.dispose();
							}
						} else {
							lblNewLabel.setText("Email ou mot de passe incorrect !");
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
		
		panel.add(button);
		
		Label label = new Label("Email");
		label.setBounds(33, 219, 68, 21);
		panel.add(label);
		
		Label label_1 = new Label("Fonction");
		label_1.setBounds(33, 315, 86, 21);
		panel.add(label_1);
		
		
		
		Label label_1_1 = new Label("Mot de passe");
		label_1_1.setBounds(419, 219, 86, 21);
		panel.add(label_1_1);
		
		
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("./images/logo_esigelec_boite-1.png"));
		lblNewLabel_1.setBounds(68, 23, 567, 190);
		panel.add(lblNewLabel_1);
		choice.add("Etudiant");
		choice.add("Gestionnaire");
		choice.add("Enseignant");
	}
	
	public String hashPassword(String password) {
		try {
			 
            
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            byte[] messageDigest = md.digest(password.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);
 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
}
