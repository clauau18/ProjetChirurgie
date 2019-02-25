package projetChirurgie;

import java.util.ArrayList;
import java.util.List;

public class Chirurgien {

	private String nom;
	private static List<String> ListChir = new ArrayList<String>();

	public Chirurgien(String nom) {
		this.nom = nom;
		Chirurgien.ListChir.add(nom);
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	//public static List<String> ListOfAllChir(){
		//return this.ListChir;
	//}
	
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
		Chirurgien other = (Chirurgien) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
