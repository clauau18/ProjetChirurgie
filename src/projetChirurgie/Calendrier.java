package projetChirurgie;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendrier {
	
	private List<Journee> planning;
	private List<Chirurgien> chirurgiens;
	private List<Salle> salles;
	
	public Calendrier() {
		this.planning = new ArrayList<Journee>();
		this.chirurgiens = new ArrayList<Chirurgien>();
		this.salles = new ArrayList<Salle>();
		
	}
	
	public void remplissage() throws IOException {
		//Ouverture d'une boîte de dialogue pour selectionner le fichier csv
	    FileDialog dialog = new FileDialog(new Frame(), "Sélectionner la base de données en csv", FileDialog.LOAD);
	    dialog.setDirectory("../");
	    dialog.setFile("*.csv");
	    dialog.setVisible(true);
	    String file =  dialog.getDirectory() + dialog.getFile();
	    Chirurgien chir;
	    Salle salle;
	    	    
	    String line="";
	    int i = 0;
	    int index = 0; // correspond au numero de la journée actuelle
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null){
				if (i > 0) {
					String[] tab = line.split(",");
					Date date = new Date(tab[1]);
					LocalTime h_deb = LocalTime.parse(tab[2]);
					LocalTime h_fin = LocalTime.parse(tab[3]);
					
					//Ajout d'un chirurgien si pas encore rentré, sinon renvoie le chirurgien correspondant
					if (!this.hasChirurgien(tab[5])) {
						chir = new Chirurgien(tab[5]);
						this.chirurgiens.add(chir);
					}
					else
						chir = this.getChirurgien(tab[5]);
					
					//Même chose mais pour la salle
					if (!this.hasSalle(tab[4])) {
						salle = new Salle(tab[4]);
						this.salles.add(salle);
					}
					else
						salle = this.getSalle(tab[4]);
					
					//Attention premiere iteration planning vide
					if (!this.planning.isEmpty()) {
						if (!this.planning.get(index).isDate(date)) {
							this.planning.add(new Journee(date));
							index++;
						}
					}
					else 
						this.planning.add(new Journee(date));
					//Ajout d'une nouvelle chirurgie à la journée correspondante
					this.planning.get(index).addChirurgie(new Chirurgie((i),date,salle,chir,h_deb,h_fin));
				}
				i++;	
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Chirurgien c:this.chirurgiens) {
			System.out.println(c.getNom());
		}
	}

	public boolean hasChirurgien(String name) {
		if(!this.chirurgiens.isEmpty()) {
			for (Chirurgien c:this.chirurgiens) {
				if (c.hasNom(name))
					return true;
			}
		}
		return false;
	}

	public Chirurgien getChirurgien(String name) {
		for (Chirurgien c:this.chirurgiens) {
			if (c.hasNom(name))
				return c;
		}
		return null;
	}
		
	public boolean hasSalle(String name) {
		if(!this.salles.isEmpty()) {
			for (Salle s:this.salles) {
				if(s.hasNom(name))
					return true;
			}
		}
		return false;
	}
	
	public Salle getSalle(String name) {
		for (Salle s:this.salles) {
			if (s.hasNom(name))
				return s;
		}
		return null;
	}
}
