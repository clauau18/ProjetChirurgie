package projetChirurgie;

import java.util.Date;
import java.util.List;

public class Journee {
	
	private Date date;
	private List<Chirurgie> chirurgies;
	private List<Conflit> conflits;
	private Journee projection;
	
	public Journee(Date date) {
		this.date = date;
	}
	
	public Journee(Date date, List<Chirurgie> chirurgies) {
		this.date = date;
		this.chirurgies = chirurgies;
	}
	
	public void addChirurgie(Chirurgie chir) {
		chirurgies.add(chir);
	}
	
	public List<Conflit> getConflits(){
		return this.conflits;
	}
}
