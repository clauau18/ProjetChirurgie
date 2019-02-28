package projetChirurgie;

import java.io.IOException;

public class Execute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendrier cal = new Calendrier();
		try {
			int compteur=2;
			cal.remplissage();
			//cal.visualise(cal.get_planning().get(compteur));
			for(Journee j:cal.get_planning()) {
				j.solve();
				System.out.println(j.getNbConflits());
			}
			cal.exportToCsv("minibose");
			

		
			//cal.get_planning().get(compteur).solve();
			
			//System.out.println(cal.get_planning().get(compteur).getNbConflits());
			//cal.visualise(cal.get_planning().get(compteur));

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
