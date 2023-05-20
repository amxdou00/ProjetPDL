package model;

public class Absence {
	private String nom_cours;
	private String jour;
	private String heure_debut;
	private String heure_fin;
	private int duree;
	private int justifiee;
	private int id;
	
	public Absence(String nom_cours, String jour, String heure_debut, String heure_fin, int duree, int justifiee, int id) {
		this.nom_cours = nom_cours;
		this.jour = jour;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
		this.duree = duree;
		this.justifiee = justifiee;
		this.id = id;
	}
	
	public Absence(String nom_cours, int duree) {
		this.nom_cours = nom_cours;
		this.duree = duree;
	}
	
	public Absence(int duree) {
		this.duree = duree;
	}
	
	public int getDuree() {
		return duree;
	}
	
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("Indentifiant Absence: ").append(id).append("\n");
        sb.append(nom_cours).append("\n");
        sb.append(jour).append(" ").append(heure_debut).append(" - ").append(heure_fin).append("\n");
        sb.append("Durée: ").append(String.format("%02d:%02d", duree/60, duree%60)).append("h\n");
        sb.append("Justifiée: ").append(convert(justifiee)).append("\n\n");
        return sb.toString();
    }
    
    public String display2() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nom Cours: ").append(nom_cours).append("\n");
        sb.append("Durée Absence: ").append(duree).append("\n\n");
        return sb.toString();
    }
    
    public String convert(int n) {
    	if(n == 0) {
    		return "Non";
    	}
    	else if (n == 1) {
    		return "Oui";
    	}
    	else {
    		return "";
    	}
    }
}
