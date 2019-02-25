package projetChirurgie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		this.sortByDate();
		//Parcours des chirurgies depuis la 1�re jusqu'� l'avant derni�re
		for(this.chirurgies.get(i);i < this.chirurgies.size() - 1;i++) {
			j=i+1;
			//Parcours des chirurgies depuis la i+1�me jusqu'� la derni�re
			for (this.chirurgies.get(j);j < this.chirurgies.size();j++) {
				if(this.chirurgies.get(i).share_horaire(this.chirurgies.get(j))) {
					if(this.chirurgies.get(i).share_salle(this.chirurgies.get(j))) {
						if(this.chirurgies.get(i).share_chirurgien(this.chirurgies.get(j))) 
							this.conflits.add(new Conflit(this.chirurgies.get(i),this.chirurgies.get(j),ConflitType.CHEVAUCHEMENT));
						else 
							this.conflits.add(new Conflit(this.chirurgies.get(i),this.chirurgies.get(j),ConflitType.INTERFERENCE));		
					}
					else{
						if(this.chirurgies.get(i).share_chirurgien(this.chirurgies.get(j))) 
							this.conflits.add(new Conflit(this.chirurgies.get(i),this.chirurgies.get(j),ConflitType.UBIQUITE));	
					}	
				}
			}
		}
	}
	
	public int nbOfConflitsWith(Chirurgie chir) {
		int compteur = 0;
		for(Conflit c:this.getConflits()) {
			if(c.contains(chir))
				compteur = compteur + 1;
		}
		return compteur;
	}
	
	public  Map<String,Integer> NbofConflitsMap(){
		Map<String,Integer> mp = new HashMap<String,Integer>();
		for(Conflit c1:this.getConflits()){
			if (!mp.containsKey(c1.getChira().getId())) 
				mp.put(c1.getChira().getId(),nbOfConflitsWith(c1.getChira()));
			if (!mp.containsKey(c1.getChirb().getId())) 
				mp.put(c1.getChira().getId(),nbOfConflitsWith(c1.getChirb()));
		}
		return mp;
	}
	
	public void solve(Journee j) {
		for (Conflit c:this.getConflits()) {
			//appeler méthode résoudre conflit c
			if (this.projection.getNbConflits()<j.getNbConflits()) {
				solve(this.projection);
			}
		}
	}
	
	public void solve(Conflit c) {
		//gestion du conflit ubiquité
		if(c.getType().equals(ConflitType.UBIQUITE)) {
			for (Chirurgie chir:this.chirurgies) {
				
			}
		}
		//gestion du conflit interference
		else if(c.getType().equals(ConflitType.INTERFERENCE)) {
			
		}

	}
}
