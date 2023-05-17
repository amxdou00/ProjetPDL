package model;

public class Cours {
	private String nom;
	private int masseHoraire;
	private int heureCours;
	private int heureTD;
	private int heureTP;
	private int heureExamen;
	private int id;
	private String heureDebut;
	private String heureFin;
	private String salle;
	private String nomEns;
	private String prenomEns;
	private String typeCours;
	
	// Constructeur pour la modification d'un cours
	public Cours(int id, String nom, int masseHoraire, int heureCours, int heureTD, int heureTP, int heureExamen) {
		this.id = id;
		this.masseHoraire = masseHoraire;
		this.nom = nom;
		this.heureCours = heureCours;
		this.heureTD = heureTD;
		this.heureTP = heureTP;
		this.heureExamen = heureExamen;
	}
	
	// Constructeur pour la création d'un cours
	public Cours(String nom, int masseHoraire, int heureCours, int heureTD, int heureTP, int heureExamen) {
		this.masseHoraire = masseHoraire;
		this.nom = nom;
		this.heureCours = heureCours;
		this.heureTD = heureTD;
		this.heureTP = heureTP;
		this.heureExamen = heureExamen;
	}
	
	public Cours(String nomCours, String heureDebut, String heureFin, String salle, String nomEns, String prenomEns, String typeCours, int id) {
		this.nom = nomCours;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
        this.nomEns = nomEns;
        this.prenomEns = prenomEns;
        this.typeCours = typeCours;
        this.id = id;
	}
	
	public String getNom() {
        return nom;
    }
    
    public int getMasseHoraire() {
        return masseHoraire;
    }
    
    public int getHeureCours() {
        return heureCours;
    }
    
    public int getHeureTD() {
        return heureTD;
    }
    
    public int getHeureTP() {
        return heureTP;
    }
    
    public int getHeureExamen() {
        return heureExamen;
    }
    
    public int getId() {
        return id;
    }
    
    public String getHeureDebut() {
        return heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public String getSalle() {
        return salle;
    }

    public String getNomEns() {
        return nomEns;
    }

    public String getPrenomEns() {
        return prenomEns;
    }

    public String getTypeCours() {
        return typeCours;
    }
    
    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("Indentifiant Planning: ").append(id).append("\n");
        sb.append(typeCours).append(": ").append(nom).append("\n");
        sb.append(heureDebut).append(" - ").append(heureFin).append("\n");
        sb.append("Salle: ").append(salle).append("\n");
        sb.append("Enseignant: ").append(prenomEns+" "+nomEns).append("\n\n");
        return sb.toString();
    }
	
	
}
