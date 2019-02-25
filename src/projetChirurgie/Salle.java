package projetChirurgie;

import java.util.ArrayList;
import java.util.List;

public class Salle {
	
	private String nom;
	private static List<String> ListSalle = new ArrayList<String>();

	public Salle(String nom) {
		this.nom = nom;
		Salle.ListSalle.add(nom);
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salle other = (Salle) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
