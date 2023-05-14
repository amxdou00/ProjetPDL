package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public abstract class MembreEsigelec {
	protected String nom;
	protected String prenom;
	protected String password;
	protected String hashedPassword;
	
	public MembreEsigelec(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = generatePassword(12);
		this.hashedPassword = this.hashPassword();
	}
	
	public MembreEsigelec(int id, String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = generatePassword(12);
		this.hashedPassword = this.hashPassword();
	}
	
	// Getters
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getHashedPassword() {
		return this.hashedPassword;
	}
	
	// Setters
	public void setNom(String newNom) {
		this.nom = newNom;
	}
	
	public void setPrenom(String newPrenom) {
		this.prenom = newPrenom;
	}
	
	public void setPassword(String newPassword) {
		this.password = newPassword;
		this.hashedPassword = this.hashPassword();
	}
	
	// Method generating a random password
	public String generatePassword(int passwordLength) {
		// Generating the password
		Random rand = new Random();
		String[] lower = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] upper = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String[] number = {"0","1","2","3","4","5","6","7","8","9"};
		
		String password = "";
		
		for(int i = 0; i < passwordLength; i++) {
			if(i%3 == 0) {
				password += lower[rand.nextInt(lower.length)];
			}
			if(i%3 == 1) {
				password += upper[rand.nextInt(upper.length)];
			}
			if(i%3 == 2) {
				password += number[rand.nextInt(number.length)];
			}
		}
		
		return password;

	}
	
	public String hashPassword() {
		try {
			 
            
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            byte[] messageDigest = md.digest(this.password.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);
 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
	
}
