package projetChirurgie;

import java.io.IOException;

public class Execute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendrier cal = new Calendrier();
		try {
			cal.remplissage();
			cal.visualise(cal.get_planning().get(0));
			int compteur=0;
			for(Journee j:cal.get_planning()) {
				j.generateConflits();
				compteur = compteur + j.getNbConflits();
			}
			System.out.println(compteur);
			//cal.visualise(cal.get_planning().get(5));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
