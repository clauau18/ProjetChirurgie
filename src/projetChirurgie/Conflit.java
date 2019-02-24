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
	
}
