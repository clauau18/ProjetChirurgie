package projetChirurgie;



public class Conflit {

	private Chirurgie a;
	private Chirurgie b;
	private ConflitType type;
	
	public Conflit(Chirurgie a, Chirurgie b, ConflitType type) {
		this.a = a;
		this.b = b;
		this.type = type;
	}
	
	public ConflitType getType() {
		return this.type;
	}
	
	public Chirurgie getChira() {
		return this.a;
	}
	
	public Chirurgie getChirb() {
		return this.b;
	}
	
	public boolean contains(Chirurgie chir) {
		if (this.a.equals(chir) || this.b.equals(chir))
			return true;
		return false;
	}
	
	/**
	 * @return Si True le conflit est une intersection : les heures de debut et de fin des conflits se superposent ou juste le debut superposent la fin (Ex : les deux commencent a 8 et finissent a 10h
	 * le but est pouvoir ajouter une duree inter operatoire
	 * Sinon juste une inclusion
	 */
	public boolean isIntersection(Conflit c) {
		if( (this.getChira().getH_deb() == this.getChirb().getH_deb() && this.getChira().getH_fin() == this.getChirb().getH_fin()) || this.getChira().getH_fin() == this.getChirb().getH_deb() ) {
			return true;
		}
		return false;
	}
	
}
