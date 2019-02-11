package projetChirurgie;

import java.io.IOException;

public class Execute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendrier cal = new Calendrier();
		try {
			cal.remplissage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
