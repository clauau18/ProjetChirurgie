package projetChirurgie;

public class Chirurgien {

	private String nom;

	public Chirurgien(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean hasNom(String name) {
		if(this.getNom().equals(name))
			return true;
		else
			return false;
	}
}
