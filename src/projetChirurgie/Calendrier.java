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
import java.util.Date;
import java.util.List;

public class Calendrier {
	
	private List<Journee> planning;
	
	public Calendrier() {
		this.planning = null;
	}
	
	public void remplissage() throws IOException {
		//Exception only csv
	    FileDialog dialog = new FileDialog(new Frame(), "Sélectionner la base de données en csv", FileDialog.LOAD);
	    dialog.setDirectory("../");
	    dialog.setFile("*.csv");
	    dialog.setVisible(true);
	    String file =  dialog.getDirectory() + dialog.getFile();
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
					Chirurgien chirurgien = Chirurgien()
					//Attention premiere iteration planning vide
					if (!planning.contains(date)) {
						planning.add(new Journee(date));
						index = index++;
					}
					planning.get(index).addChirurgie(new Chirurgie(tab[0],date,salle,chir,h_deb,h_fin));
					

					/*
					LocalTime h_deb = LocalTime.parse(tab[2]);
					LocalTime h_fin = LocalTime.parse(tab[3]);
					planning.get.addChirurgie(tab[0],date,salle,);
					if (planning.check(date))
							planning.add(new Journee(date);
						*/
				}
				i++;date;
				private List<Ch
					
			}
			}		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		//redefinir contains (equals)
		if (planning.contains(date)
			planning.add(new Journee(date);
		else
			planning.add(new Journee( nvlle date));
		*/
	}

}
