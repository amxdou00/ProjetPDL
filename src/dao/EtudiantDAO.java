package dao;
import java.sql.*;
import model.*;

public class EtudiantDAO extends ConnectionDAO{
	public EtudiantDAO() {
		super();
	}
	
	public int add(Etudiant etudiant) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO etudiant (filiere, nom, prenom, email, password, numero_groupe, quota, malus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, etudiant.getFiliere());
			ps.setString(2, etudiant.getNom());
			ps.setString(3, etudiant.getPrenom());
			ps.setString(4, etudiant.getEmail());
			ps.setString(5, etudiant.getHashedPassword());
			ps.setInt(6, etudiant.getNumeroGroupe());
			ps.setInt(7, etudiant.getQuota());
			ps.setInt(8, 0);
			returnValue = ps.executeUpdate();
			
			System.out.println("Etudiant ajoute");
			
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
			ps = con.prepareStatement("DELETE FROM etudiant WHERE id = ?");
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
	
	public int update(Etudiant etudiant) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		
		try {

			
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE etudiant set nom = ?, prenom = ?, numero_groupe = ?, filiere = ?, quota = ? WHERE id = ?");
			ps.setString(1, etudiant.getNom());
			ps.setString(2, etudiant.getPrenom());
			ps.setInt(3, etudiant.getNumeroGroupe());
			ps.setString(4, etudiant.getFiliere());
			ps.setInt(5, etudiant.getQuota());
			ps.setInt(6, etudiant.getId());
			
			
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
