package gui;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ConnectionDAO;
import javax.swing.JLabel;

public class VoirDemandesGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VoirDemandesGUI window = new VoirDemandesGUI();
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
	public VoirDemandesGUI() {
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

		JLabel lblListeDesDemandes = new JLabel("Liste des demandes d'absences physique");
		lblListeDesDemandes.setBounds(36, 15, 324, 15);
		frame.getContentPane().add(lblListeDesDemandes);

		// Connexion à la base de données
		Connection con = null;
		PreparedStatement ps =  null;
		ResultSet rs = null;

		try {

			String query = "select a.id, b.nom, b.prenom from absence_physique a, etudiant b where a.id_etudiant = b.id and a.validee = 0";
			con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				String content = "";
				content += "Identifiant demande: "+rs.getInt("id")+"\n";
				content += "Nom etudiant: "+rs.getString("nom")+"\n";
				content += "Prenom etudiant: "+rs.getString("prenom")+"\n\n";
				text_area.append(content);
			}

		} catch(Exception ee) {
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

		JButton btnRetout = new JButton("Retour");
		btnRetout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				AbsencePhysiqueGUI absencePhysiqueGUI = new AbsencePhysiqueGUI();
				frame.dispose();
			}
		});
		btnRetout.setBounds(550, 15, 117, 25);
		frame.getContentPane().add(btnRetout);
	}

}
