package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.ConnectionDAO;

public class Etudiant extends MembreEsigelec {
	
	private String filiere;
	private String email;
	private int quota;
	private int numeroGroupe;
	
	// Constructeur utilisé pour la liste d'appel
	public Etudiant(String nom, String prenom) {
		super(nom, prenom);
	}
	
	// Constructeur utilisé pour la connexion
	public Etudiant(int id, String nom, String prenom, int groupe) {
		super(nom, prenom);
		this.numeroGroupe = groupe;
		this.id = id;
		
	}
	
	// Constructeur utilisés pour la création d'un étudiant
	public Etudiant(String nom, String prenom, int numeroGroupe, String filiere, int quota) {
		super(nom, prenom);
		this.numeroGroupe = numeroGroupe;
		this.filiere = filiere;
		this.quota = quota;
		
		/* 
		 * Récupération de l'identifiant du dernier étudiant pour la 
		 * création de l'addresse email: Prenom + Nom + id + @groupe-esigelec.org 
		 * (Afin de ne pas avoir de doublons).
		*/
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int idDernierEtudiant = 0;
		
		try {
	        String query = "SELECT * FROM etudiant ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
	        con = DriverManager.getConnection(ConnectionDAO.URL, ConnectionDAO.LOGIN, ConnectionDAO.PASS);
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();

	        if(rs.next()) {
	        	idDernierEtudiant = rs.getInt("id");
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
		
		this.email = prenom + "." + nom + (idDernierEtudiant + 1) + "@groupe-esigelec.org";
		
	}
	
	// Constructeur utilisés pour la modification d'un étudiant
	public Etudiant(int id, String nom, String prenom, int groupe, String filiere, int quota) {
		super(id, nom, prenom);
		this.numeroGroupe = groupe;
		this.filiere = filiere;
		this.quota = quota;
		this.email = prenom + "." + nom + id + "@groupe-esigelec.org";
	}
	
	// Getters
	
	public String getPassword() {
		return super.getPassword();
	}
	
	public String getFiliere() {
		return this.filiere;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public int getNumeroGroupe() {
		return this.numeroGroupe;
	}
	
	public int getQuota() {
		 return this.quota;
	}
	
	public int getId() { 
		return this.id;
	}
	
	public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append(nom.toUpperCase()).append(" ").append(prenom).append("\n");
        return sb.toString();
    }
	
}

