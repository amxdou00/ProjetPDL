package dao;
import java.sql.*;
import model.*;
import other.MyColor;
import other.ResultMessage;

public class EnseignantDAO extends ConnectionDAO{
	public EnseignantDAO() {
		super();
	}

	public ResultMessage add(Enseignant enseignant) {
		Connection con = null;
		PreparedStatement ps = null;
		@SuppressWarnings("unused")
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
			try {
				if (ps != null)
					ps.close();

			} catch (Exception ignore) {}

			try {
				if (con != null) 
					con.close();

			} catch (Exception ignore) {}
		}
		return new ResultMessage(MyColor.GREEN, "L'enseignant a bien été ajouté");
	}

	public ResultMessage delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		@SuppressWarnings("unused")
		int returnValue = 0;

		// On vérifie si l'identifiant donné est présent dans la table enseignant
		try {
			ResultSet rs = null;
			String query = "select * from enseignant where id = ?";
			con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(!rs.next()) {
				return new ResultMessage(MyColor.RED, "L'identifiant " + id + " n'existe pas");
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
		return new ResultMessage(MyColor.GREEN, "L'enseignant a bien été supprimé");
	}

	public ResultMessage update(Enseignant enseignant) {
		Connection con = null;
		PreparedStatement ps = null;
		@SuppressWarnings("unused")
		int returnValue = 0;

		// On vérifie si l'identifiant donné est présent dans la table enseignant
		try {
			ResultSet rs = null;
			String query = "select * from enseignant where id = ?";
			con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
			ps = con.prepareStatement(query);

			ps.setInt(1, enseignant.getId());
			rs = ps.executeQuery();

			if(!rs.next()) {
				return new ResultMessage(MyColor.RED, "L'identifiant " + enseignant.getId() + " n'existe pas");
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


		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE enseignant set nom = ?, prenom = ?, numero_telephone = ? WHERE id = ?");
			ps.setString(1, enseignant.getNom());
			ps.setString(2, enseignant.getPrenom());
			ps.setString(3, enseignant.getNumTel());
			ps.setInt(4, enseignant.getId());
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
		return new ResultMessage(MyColor.GREEN, "L'enseignant a bien été mis à jour");
	}


}
