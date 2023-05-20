package dao;
import java.sql.*;
import model.*;
import other.MyColor;
import other.ResultMessage;

public class CoursDAO extends ConnectionDAO{
	public CoursDAO() {
		super();
	}

	public ResultMessage add(Cours cours) {
		Connection con = null;
		PreparedStatement ps = null;
		@SuppressWarnings("unused")
		int returnValue = 0;

		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("INSERT INTO cours (nom_cours, masse_horaire, heures_amphi, heures_td, heures_tp, heures_examen) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, cours.getNom());
			ps.setInt(2, cours.getMasseHoraire());
			ps.setInt(3, cours.getHeureCours());
			ps.setInt(4, cours.getHeureTD());
			ps.setInt(5, cours.getHeureTP());
			ps.setInt(6, cours.getHeureExamen());

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
		return new ResultMessage(MyColor.GREEN, "Le cours a bien été ajouté");
	}

	public ResultMessage delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		@SuppressWarnings("unused")
		int returnValue = 0;

		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("DELETE FROM cours WHERE id = ?");
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
		return new ResultMessage(MyColor.GREEN, "Le cours a bien été supprimé");
	}

	public ResultMessage update(Cours cours) {
		Connection con = null;
		PreparedStatement ps = null;
		@SuppressWarnings("unused")
		int returnValue = 0;


		try {


			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("UPDATE cours set nom_cours = ?, masse_horaire = ?, heures_amphi = ?, heures_td = ?, heures_tp = ?, heures_examen = ? WHERE id = ?");
			ps.setString(1, cours.getNom());
			ps.setInt(2, cours.getMasseHoraire());
			ps.setInt(3, cours.getHeureCours());
			ps.setInt(4, cours.getHeureTD());
			ps.setInt(5, cours.getHeureTP());
			ps.setInt(6, cours.getHeureExamen());
			ps.setInt(7, cours.getId());


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
		return new ResultMessage(MyColor.GREEN, "Le cours a bien été mis à jour");
	}


}
