package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.GroupeEtudiant;

public class GroupeEtudiantDAO extends ConnectionDAO{
	public GroupeEtudiantDAO() {
		super();
	}
	
	public int add(GroupeEtudiant g) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO groupe_etudiant (numero_groupe, capacite_max) VALUES (?, ?)");
			ps.setInt(1, g.getNumeroGroupe());
			ps.setInt(2, g.getCapaciteMax());
			returnValue = ps.executeUpdate();
			
		} catch (Exception e) {
				e.printStackTrace();
			
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
		return returnValue;
	}
}
