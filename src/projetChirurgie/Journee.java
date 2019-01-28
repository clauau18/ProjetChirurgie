package projetChirurgie;

import java.util.Date;
import java.util.List;

public class Journee {
	
	private Date date;
	private List<Chirurgie> chirurgies;
	private List<Conflit> conflits;
	private Journee projection;
	
	public Journee(Date date, List<Chirurgie> chirurgies) {
		this.date = date;
		this.chirurgies = chirurgies;
	}
}
