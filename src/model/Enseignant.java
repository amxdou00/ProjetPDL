package model;

import java.util.ArrayList;

public class Enseignant extends MembreEsigelec {
	private int id;
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
	}
	
	public Enseignant(String prenom, String nom, String numTel) {
		super(nom, prenom);
		this.numTel = numTel;
		this.email = prenom+"."+nom+"@esigelec.fr";
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
