package projetChirurgie;

import java.time.Instant;
import java.util.Date;

public class Chirurgie {
	
	private int id;
	private Date date;
	private Salle salle;
	private Chirurgien chirurgien;
	private Instant h_deb;
	private Instant h_fin;
	
	public Chirurgie(int id, Date date, Salle salle, Chirurgien chirurgien, Instant h_deb, Instant h_fin) {
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
	public Instant getH_deb() {
		return h_deb;
	}
	public void setH_deb(Instant h_deb) {
		this.h_deb = h_deb;
	}
	public Instant getH_fin() {
		return h_fin;
	}
	public void setH_fin(Instant h_fin) {
		this.h_fin = h_fin;
	}
	

}
