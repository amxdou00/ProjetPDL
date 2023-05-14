package model;

import java.util.ArrayList;


public class GroupeEtudiant {
	private int numeroGroupe;
	private int capaciteMax;
	private ArrayList<Cours> coursSuivis;
	
	public GroupeEtudiant(int numeroGroupe, int capaciteMax) {
		this.numeroGroupe = numeroGroupe;
		this.capaciteMax = capaciteMax;
	}
	
	// Getters
	public int getNumeroGroupe() {
		return numeroGroupe;
	}
	
	public int getCapaciteMax() {
		return capaciteMax;
	}
	
	public ArrayList<Cours> getCoursSuivis() {
		return coursSuivis;
	}
	
	// Setters
	public void setNumeroGroupe(int newNumeroGroupe) {
		this.numeroGroupe = newNumeroGroupe;
	}
	
	public void setCapaciteMax(int newCapaciteMax) {
		this.capaciteMax = newCapaciteMax;
	}
	
	// Autres methodes
	public void ajouterCours(Cours coursAAjouter) {
		coursSuivis.add(coursAAjouter);
	}
	
	public void supprimerCours(Cours coursASupprimer) {
		coursSuivis.remove(coursASupprimer);
	}
}
