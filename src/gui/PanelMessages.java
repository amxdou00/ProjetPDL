package gui;

import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ConnectionDAO;
import model.Etudiant;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class PanelMessages extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelMessages(final Etudiant etudiant) {
		setBounds(new Rectangle(0, 0, 530, 451));
		setLayout(null);
		
		JLabel lblMesMessages = new JLabel("Mes messages");
		lblMesMessages.setBounds(158, 32, 139, 15);
		add(lblMesMessages);
		
		final JTextArea text_area = new JTextArea(10, 30);
		
		text_area.setText("");
		text_area.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(text_area);
		scrollPane.setBounds(new Rectangle(22, 100, 486, 350));
		add(scrollPane);
		
		JButton btnToutEffacer = new JButton("Tout effacer");
		btnToutEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement ps = null;
				@SuppressWarnings("unused")
				int returnValue = 0;
				String query = "update etudiant set message = '' where id = ?";
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        ps.setInt(1,  etudiant.getId());
			        returnValue = ps.executeUpdate();






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
		btnToutEffacer.setBounds(369, 63, 139, 25);
		add(btnToutEffacer);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "select message from etudiant where id = ?";
				
				try {
			        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			        ps = con.prepareStatement(query);
			        ps.setInt(1, etudiant.getId());
			        rs = ps.executeQuery();

			        if(rs.next()) {
			        	text_area.append(rs.getString("message"));
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
		btnActualiser.setBounds(240, 63, 117, 25);
		add(btnActualiser);
	}

}
