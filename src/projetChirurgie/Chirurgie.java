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
	

}
