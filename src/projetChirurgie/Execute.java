package projetChirurgie;

import java.io.IOException;
import java.text.ParseException;

public class Execute {

	public static void main(String[] args) throws ParseException, IOException {
		Calendrier cal = new Calendrier();		
		cal.remplissage();
		/*
		 * Si visualisation voulu dans la console changer le paramètre au jour voulu, sinon consulter l'historique
		 */
		cal.visualise(cal.get_planning().get(2));
		cal.launch();
		cal.visualise(cal.get_planning().get(2));
	}
}
