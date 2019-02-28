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
	
	public List<Chirurgie> GetChirurgiesConflits(){
		List<Chirurgie> ls = new ArrayList<Chirurgie>();
		for(Conflit c:this.conflits) {
			if(!ls.contains(c.getChira()))
				ls.add(c.getChira());
			if(!ls.contains(c.getChirb()))
				ls.add(c.getChirb());
		}
		return ls;
	}
	
	public List<Chirurgie> GetChirurgiesNoConflits(){
		List<Chirurgie> ls = new ArrayList<Chirurgie>();
		ls.addAll(this.chirurgies);
		for(Conflit c:this.conflits) {
			if(ls.contains(c.getChira()))
				ls.remove(c.getChira());
			if(ls.contains(c.getChirb()))
				ls.remove(c.getChirb());
		}
		return ls;
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
	/**
	 * 
	 * @return liste des chirurgiens n'ayant pas de conflits.
	 */
	public List<Chirurgien> getChirurgiensPresents(List<Chirurgie> listchir){
		List<Chirurgien> ls = new ArrayList<Chirurgien>();
		for (Chirurgie chir:listchir){
			if (!ls.contains(chir.getChirurgien()))
					ls.add(chir.getChirurgien());
		}
		return ls;
	}
	
	public List<Salle> getSallesPresents(List<Chirurgie>listchir){
		List<Salle> ls = new ArrayList<Salle>();
		for (Chirurgie chir:listchir){
			if (!ls.contains(chir.getSalle()))
					ls.add(chir.getSalle());
		}
		return ls;
	}
	
	public void generateConflits() {
		this.conflits = new ArrayList<Conflit>();
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
			if (this.projection.getNbConflits()<j.getNbConflits() && this.projection.getNbConflits()>0) {
				solve(this.projection);
			}
		}
	}
	
	public boolean test_ubi(Conflit c,List<Chirurgien> ls) {
		Chirurgien chir_originel = c.getChira().getChirurgien();
		int i = 0;
		while(i < ls.size() && this.getNbConflits() != 0) {
			c.getChira().setChirurgien(ls.get(i));
			this.generateConflits();
			i++;
		}
		if (this.getNbConflits() != 0 ) {
			c.getChira().setChirurgien(chir_originel);
			chir_originel = c.getChirb().getChirurgien();
			i = 0;
			while(i < ls.size() && this.getNbConflits() != 0) {
				c.getChirb().setChirurgien(ls.get(i));
				this.generateConflits();
				i++;
			}
			if (this.getNbConflits() != 0 ) {
				c.getChirb().setChirurgien(chir_originel);
				return false;
			}
		}
		return true;
	}
	
	public boolean test_interf(Conflit c,List<Salle> ls) {
		Salle salle_originel = c.getChira().getSalle();
		int i = 0;
		while(i < ls.size() && this.getNbConflits() != 0) {
			c.getChira().setSalle(ls.get(i));
			this.generateConflits();
			i++;
		}
		if (this.getNbConflits() != 0 ) {
			c.getChira().setSalle(salle_originel);
			salle_originel = c.getChirb().getSalle();
			i = 0;
			while(i < ls.size() && this.getNbConflits() != 0) {
				c.getChirb().setSalle(ls.get(i));
				this.generateConflits();
				i++;
			}
			if (this.getNbConflits() != 0 ) {
				c.getChirb().setSalle(salle_originel);
				return false;
			}
			
		}
		return true;
	}
	

	
	/**
	 * Résoud les differents types de conflits en fonction de la priorité : chirugiens sans conflits > Chirurgiens en conflits > Chirurgiens non present
	 */
	public void solve() {
		if (this.getNbConflits() == 1) {
				Conflit c = this.getConflits().get(0);
				//gestion du conflit ubiquité
				if(c.getType().equals(ConflitType.UBIQUITE)) {
					if(test_ubi(c,this.getChirurgiensPresents(this.GetChirurgiesNoConflits())) == false) {
						if(test_ubi(c,this.getChirurgiensPresents(this.GetChirurgiesConflits()))== false)
							if(test_ubi(c,Chirurgien.getListChir()) == false);
					}
				}
				else if(c.getType().equals(ConflitType.INTERFERENCE)) {
					if(test_interf(c,this.getSallesPresents(this.GetChirurgiesNoConflits())) == false)
						if(test_interf(c,this.getSallesPresents(this.GetChirurgiesConflits())) == false)
							if(test_interf(c,Salle.getListSalle()) == false);
				}
			}
		}
}
