package projetChirurgie;

import java.io.IOException;

public class Execute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendrier cal = new Calendrier();
		try {
			cal.remplissage();
			for (Journee j:cal.get_planning()) {
				j.generateConflits();
				System.out.println(j.getConflits().size());
				for (Conflit c:j.getConflits()) {
					System.out.println(c.getType());
					System.out.println(c.getChira());
				}
			}
			//cal.visualise(cal.get_planning().get(5));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
