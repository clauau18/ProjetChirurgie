package projetChirurgie;

import java.util.ArrayList;
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
	
	public void generateConflits() {
		int i=0;
		int j;
		for(this.chirurgies.get(i);i <= this.chirurgies.size();i++) {
			j=i+1;
			for (this.chirurgies.get(j);j <= this.chirurgies.size();j++) {
				if(this.chirurgies.get(i).share_horaire(this.chirurgies.get(j)));
				//si oui alors quel type
				//this.conflits.add(new Conflit(this.chirurgies.get(i),this.chirurgies.get(j),type conflit à det))
			}
		}
	}
	
	public void solve(Journee j) {
		for (Conflit c:this.getConflits()) {
			//appeler méthode résoudre conflit c
			if (this.projection.getNbConflits()<j.getNbConflits()) {
				solve(this.projection);
			}
		}
	}
}
