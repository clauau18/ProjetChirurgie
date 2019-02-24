package projetChirurgie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Journee {
	
	private Date date;
	private List<Chirurgie> chirurgies;
	private List<Conflit> conflits;
	private Journee projection;
	
	public Journee(Date date) {
		this.date = date;
		this.chirurgies = new ArrayList<Chirurgie>();
		this.conflits = new ArrayList<Conflit>();
	}
	
	public Journee(Date date, List<Chirurgie> chirurgies) {
		this.date = date;
		this.chirurgies = chirurgies;
	}
	
	public void addChirurgie(Chirurgie chir) {
		this.chirurgies.add(chir);
	}
	
	public List<Chirurgie> getChirurgies(){
		return this.chirurgies;
	}
	public List<Conflit> getConflits(){
		return this.conflits;
	}
	
	public int getNbConflits() {
		return this.conflits.size();
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public boolean isDate(Date date) {
		if (this.getDate().equals(date))
			return true;
		return false;
	}
	
	public void sortByDate() {
		Collections.sort(this.chirurgies, Chirurgie.byDate);
	}
	
	public void generateConflits() {
		int i=0;
		int j;
		//Parcours des chirurgies depuis la 1ère jusqu'à l'avant dernière
		for(this.chirurgies.get(i);i < this.chirurgies.size() - 1;i++) {
			j=i+1;
			//Parcours des chirurgies depuis la i+1ème jusqu'à la dernière
			for (this.chirurgies.get(j);j < this.chirurgies.size();j++) {
				if(this.chirurgies.get(i).share_horaire(this.chirurgies.get(j))) {
					if(this.chirurgies.get(i).share_salle(this.chirurgies.get(j))) {
						if(this.chirurgies.get(i).share_chirurgien(this.chirurgies.get(j))) 
							this.conflits.add(new Conflit(this.chirurgies.get(i),this.chirurgies.get(j),ConflitType.CHEVAUCHEMENT));
						else 
							this.conflits.add(new Conflit(this.chirurgies.get(i),this.chirurgies.get(j),ConflitType.INTERFERENCE));		
					}
					else 
						if(this.chirurgies.get(i).share_chirurgien(this.chirurgies.get(j))) {
							this.conflits.add(new Conflit(this.chirurgies.get(i),this.chirurgies.get(j),ConflitType.UBIQUITE));	
					}
				}
			}
		}
	}
	
	
	public void solve(Journee j) {
		for (Conflit c:this.getConflits()) {
			//appeler mÃ©thode rÃ©soudre conflit c
			if (this.projection.getNbConflits()<j.getNbConflits()) {
				solve(this.projection);
			}
		}
	}
}
