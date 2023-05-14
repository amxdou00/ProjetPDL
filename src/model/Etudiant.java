package model;

import java.util.ArrayList;


public class Etudiant extends MembreEsigelec {
	
	private String filiere;
	private String email;
	private int quota;
	private boolean horsQuota;
	private String[][] planning;
	private GroupeEtudiant groupe;
	private int id;
	private int numeroGroupe;
	
	public Etudiant(int id, String nom, String prenom, int groupe) {
		super(nom, prenom);
		this.numeroGroupe = groupe;
		this.id = id;
		
	}
	
	// Constructeur utilisés pour la création d'un étudiant
	public Etudiant(String nom, String prenom, int groupe, String filiere, int quota) {
		super(nom, prenom);
		this.numeroGroupe = groupe;
		this.filiere = filiere;
		this.quota = quota;
		this.email = prenom+"."+nom+"@groupe-esigelec.org";
		
	}
	
	// Constructeur utilisés pour la modification d'un étudiant
	public Etudiant(int id, String nom, String prenom, int groupe, String filiere, int quota) {
		super(id, nom, prenom);
		this.numeroGroupe = groupe;
		this.filiere = filiere;
		this.quota = quota;
		this.email = prenom+"."+nom+"@groupe-esigelec.org";
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
	
	
}

