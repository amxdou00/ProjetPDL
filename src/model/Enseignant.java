package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.ConnectionDAO;

public class Enseignant extends MembreEsigelec {
	private ArrayList<Integer> liste_id_cours;
	private String numTel;
	private String email;
	
	public Enseignant(int id, String nom, String prenom, ArrayList<Integer> liste_id_cours) {
		super(id, nom, prenom);
		this.liste_id_cours = liste_id_cours;
	}
	
	public Enseignant(int id, String prenom, String nom, String numTel) {
		super(id, nom, prenom);
		this.numTel = numTel;
		this.email = prenom + "." + nom + id + "@esigelec.fr";
	}
	
	public Enseignant(String prenom, String nom, String numTel) {
		super(nom, prenom);
		this.numTel = numTel;
		
		/* 
		 * Récupération de l'identifiant du dernier étudiant pour la 
		 * création de l'addresse email: Prenom + Nom + id + @esigelec.fr
		 * (Afin de ne pas avoir de doublons).
		*/
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int idDernierEnseignant = 0;
		
		try {
	        String query = "SELECT * FROM enseignant ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
	        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();

	        if(rs.next()) {
	        	idDernierEnseignant = rs.getInt("id");
	        }
		}
		catch (Exception ee) {
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
		
		this.email = prenom + "." + nom + (idDernierEnseignant + 1) + "@esigelec.fr";
	}
	
	
	
	public int getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getNumTel() {
		return this.numTel;
	}
	
	public ArrayList<Integer> getListeIdCours() {
		return this.liste_id_cours;
	}
}
