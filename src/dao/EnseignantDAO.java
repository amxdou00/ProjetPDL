package dao;
import java.sql.*;
import model.*;

public class EnseignantDAO extends ConnectionDAO{
	public EnseignantDAO() {
		super();
	}
	
	public int add(Enseignant enseignant) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO enseignant (nom, prenom, numero_telephone, password, email) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, enseignant.getNom());
			ps.setString(2, enseignant.getPrenom());
			ps.setString(3, enseignant.getNumTel());
			ps.setString(4, enseignant.getHashedPassword());
			ps.setString(5, enseignant.getEmail());
			
			returnValue = ps.executeUpdate();
			
		} catch (Exception e) {
				e.printStackTrace();
			
		} finally {
		// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null)
					ps.close();
				
			} catch (Exception ignore) {}
		
			try {
				if (con != null) 
					con.close();
		
			} catch (Exception ignore) {}
		}
		return returnValue;
	}
	
	public int delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM enseignant WHERE id = ?");
			ps.setInt(1, id);

			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public int update(Enseignant enseignant) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		
		try {

			
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE enseignant set nom = ?, prenom = ?, numero_telephone = ? WHERE id = ?");
			ps.setString(1, enseignant.getNom());
			ps.setString(1, enseignant.getPrenom());
			ps.setString(1, enseignant.getNumTel());
			ps.setInt(1, enseignant.getId());
			
			
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	
}
