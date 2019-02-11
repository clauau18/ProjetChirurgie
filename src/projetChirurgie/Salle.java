package projetChirurgie;

public class Salle {
	
	private String nom;

	public Salle(String nom) {
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
