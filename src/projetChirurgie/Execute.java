package projetChirurgie;

import java.io.IOException;
import java.text.ParseException;

public class Execute {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Calendrier cal = new Calendrier();
		try {
			int nb_conf=0;
			int nb_res=0;
			cal.remplissage();
			//cal.visualise(cal.get_planning().get(compteur));
			for(Journee j:cal.get_planning()) {
				j.generateConflits();
				nb_conf += j.getNbConflits();
				j.solve();
				nb_res += j.getNbConflits();
			}
			cal.close_Historique();
			cal.exportToCsv("Base_correction");
			System.out.println("Nombre de conflits detectés: " + nb_conf);
			System.out.println("Nombre de conflits corrigés : " + (nb_conf - nb_res));

		
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
