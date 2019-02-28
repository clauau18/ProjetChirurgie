package projetChirurgie;

import java.io.IOException;
import java.text.ParseException;

public class Execute {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Calendrier cal = new Calendrier();
		try {
			int compteur=9;
			cal.remplissage();
			int conf = 0;
			int conf_restant = 0;
			String jour="";
			//cal.visualise(cal.get_planning().get(compteur));
			for(Journee j:cal.get_planning()) {
				j.solve();
			}
			cal.exportToCsv("minibose");
			cal.close_Historique();
			System.out.println("fin");
		
			//cal.get_planning().get(compteur).solve();
			
			//System.out.println(cal.get_planning().get(compteur).getNbConflits());
			//for(Journee j:cal.get_planning()) {
				//j.generateConflits();
				//System.out.println(j.getChirurgies().get(0).getH_deb().compareTo(j.getChirurgies().get(1).getH_deb()));
				//compteur = compteur + j.getNbConflits();
			//}
			//cal.visualise(cal.get_planning().get(5));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
