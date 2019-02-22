package projetChirurgie;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class Chirurgie {
	
	private int id;
	private Date date;
	private Salle salle;
	private Chirurgien chirurgien;
	private LocalTime h_deb;
	private LocalTime h_fin;
	
	public Chirurgie(int id, Date date, Salle salle, Chirurgien chirurgien, LocalTime h_deb, LocalTime h_fin) {
		this.id = id;
		this.date = date;
		this.salle = salle;
		this.chirurgien = chirurgien;
		this.h_deb = h_deb;
		this.h_fin = h_fin;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Salle getSalle() {
		return salle;
	}
	
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	
	public Chirurgien getChirurgien() {
		return chirurgien;
	}
	
	public void setChirurgien(Chirurgien chirurgien) {
		this.chirurgien = chirurgien;
	}
	
	public LocalTime getH_deb() {
		return h_deb;
	}
	
	public void setH_deb(LocalTime h_deb) {
		this.h_deb = h_deb;
	}
	
	public LocalTime getH_fin() {
		return h_fin;
	}
	
	public void setH_fin(LocalTime h_fin) {
		this.h_fin = h_fin;
	}
	
	public long getDuree() {
		return Duration.between(getH_deb(),getH_fin()).toMinutes();
	}
	
	public boolean share_horaire(Chirurgie chir) {
		//Partage de tout ou partie de la plage horaire
		if(!(chir.getH_fin().isBefore(this.getH_deb()) || chir.getH_deb().isAfter(this.getH_fin()))){
			
		}
			//chir.getH_deb().isBefore(this.getH_deb()) && chir.getH_fin().isAfter(this.getH_fin())
			return false;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chirurgie other = (Chirurgie) obj;
		if (chirurgien == null) {
			if (other.chirurgien != null)
				return false;
		} else if (!chirurgien.equals(other.chirurgien))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (h_deb == null) {
			if (other.h_deb != null)
				return false;
		} else if (!h_deb.equals(other.h_deb))
			return false;
		if (h_fin == null) {
			if (other.h_fin != null)
				return false;
		} else if (!h_fin.equals(other.h_fin))
			return false;
		if (id != other.id)
			return false;
		if (salle == null) {
			if (other.salle != null)
				return false;
		} else if (!salle.equals(other.salle))
			return false;
		return true;
	}
}
