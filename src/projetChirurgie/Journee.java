package projetChirurgie;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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

	public void addChirurgie(Chirurgie chir) {
		this.chirurgies.add(chir);
	}
	
	public List<Chirurgie> getChirurgies(){
		return this.chirurgies;
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
	
	public List<Conflit> getConflits(){
		return this.conflits;
	}
	
	public int getNbConflits() {
		return this.conflits.size();
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
		for(Conflit c1:this.conflits){
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
		int nb_conf = this.getNbConflits();
		int i = 0;
		while(i < ls.size() && this.getNbConflits() >= nb_conf) {
			c.getChira().setChirurgien(ls.get(i));
			this.generateConflits();
			i++;
		}
		if (this.getNbConflits() >= nb_conf ) {
			c.getChira().setChirurgien(chir_originel);
			chir_originel = c.getChirb().getChirurgien();
			i = 0;
			while(i < ls.size() && this.getNbConflits() >= nb_conf) {
				c.getChirb().setChirurgien(ls.get(i));
				this.generateConflits();
				i++;
			}
			if (this.getNbConflits() >= nb_conf ) {
				c.getChirb().setChirurgien(chir_originel);
				this.generateConflits();
				return false;
			}
			else {
				Calendrier.getHistorique().println("UBIQUITE RESOLUE");
				Calendrier.getHistorique().println("Modification chirurgie " + c.getChirb().getId() + " : " + chir_originel.getNom() + " --> " + ls.get(i-1).getNom());
			}
		}
		else {
			Calendrier.getHistorique().println("UBIQUITE RESOLUE");
			Calendrier.getHistorique().println("Modification chirurgie " +c.getChira().getId() + " : " + chir_originel.getNom() + " --> " + ls.get(i-1).getNom());
		}
		return true;
	}
	
	public boolean test_interf(Conflit c,List<Salle> ls) {
		Salle salle_originel = c.getChira().getSalle();
		int nb_conf = this.getNbConflits();
		int i = 0;
		while(i < ls.size() && this.getNbConflits() >= nb_conf) {
			c.getChira().setSalle(ls.get(i));
			this.generateConflits();
			i++;
		}
		if (this.getNbConflits() >= nb_conf ) {
			c.getChira().setSalle(salle_originel);
			salle_originel = c.getChirb().getSalle();
			i = 0;
			while(i < ls.size() && this.getNbConflits() >= nb_conf) {
				c.getChirb().setSalle(ls.get(i));
				this.generateConflits();
				i++;
			}
			if (this.getNbConflits() >= nb_conf ) {
				c.getChirb().setSalle(salle_originel);
				this.generateConflits();
				return false;
			}
			else {
				Calendrier.getHistorique().println("INTERFERENCE RESOLUE");
				Calendrier.getHistorique().println("Modification chirurgie " + c.getChirb().getId() + " : " + salle_originel.getNom() + " --> " + ls.get(i-1).getNom());
			}
		}
		else {
			Calendrier.getHistorique().println("INTERFERENCE RESOLUE");
			Calendrier.getHistorique().println("Modification chirurgie " +c.getChira().getId() + " : " + salle_originel.getNom() + " --> " + ls.get(i-1).getNom());
		}
		return true;
	}
	
	public boolean test_chevauc(Conflit c,List<Salle> ls, List<Chirurgien> lc) {
		Salle salle_originel = c.getChira().getSalle();
		Chirurgien chir_originel = c.getChira().getChirurgien();
		
		int i = 0;
		//resoudre par decalage 
		LocalTime h = LocalTime.of(8,0);
		if(!(c.getChira().getH_deb().isAfter(h)) || !(c.getChira().getH_deb().minus(c.getChira().getH_deb().until(c.getChirb().getH_deb(),ChronoUnit.HOURS),ChronoUnit.HOURS).isAfter(h))) {
			c.getChira().decalage_left(c.getChirb());
		}
		return true;
	}
	
	/**
	 * Résoud les differents types de conflits en fonction de la priorité : chirugiens sans conflits > Chirurgiens en conflits > Chirurgiens non present
	 */
	public void solve() {
		int i = 0;
		boolean solved;
		solved = false;
		this.generateConflits();
		Calendrier.getHistorique().println(("Journée du " + Calendrier.getDateFormat().format(this.date)));
		Calendrier.getHistorique().println(("---------------------"));
		Calendrier.getHistorique().println(this.conflits.size() + " conflit(s)");
		while(this.getNbConflits() > 0 && i < this.getNbConflits()){
				if (solved) {
					this.generateConflits();
					i = 0;
				}
				Conflit c = this.getConflits().get(i);
				solved = true;
				//gestion du conflit ubiquité
				if(c.getType().equals(ConflitType.UBIQUITE)) {
					if(test_ubi(c,this.getChirurgiensPresents(this.GetChirurgiesNoConflits())) == false) {
						if(test_ubi(c,this.getChirurgiensPresents(this.GetChirurgiesConflits()))== false) {
							if(test_ubi(c,Chirurgien.getListChir()) == false) {
								i = i + 1;	
								solved = false;
							}
						}	
					}
				}
				else if(c.getType().equals(ConflitType.INTERFERENCE)) {
					if(test_interf(c,this.getSallesPresents(this.GetChirurgiesNoConflits())) == false) {
						if(test_interf(c,this.getSallesPresents(this.GetChirurgiesConflits())) == false) {
							if(test_interf(c,Salle.getListSalle()) == false) {
								i = i + 1;
								solved = false;
							}
						}	
					}
				}
				else {
					System.out.println(c.getChira().getH_deb().toString());
					System.out.println(c.getChira().getH_deb());
					System.out.println(c.getChira().getH_fin().until(c.getChirb().getH_deb(),ChronoUnit.HOURS));
					System.out.println(c.getChira().getH_deb().minus(c.getChira().getH_deb().until(c.getChirb().getH_deb(),ChronoUnit.HOURS),ChronoUnit.HOURS).toString());

					i = i+1;
					solved = false;
				}
			}
		Calendrier.getHistorique().println('\n');
		}
}
