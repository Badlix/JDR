package jdr;

public class Personnage {
	private String nom;
	private Donjon map;
	protected Position pos;
	private int pv;
	private int atq;
	private int def;
	private int velocite;
	
	public Donjon getMap() {return map;}
	
	public Position getPosition() {return pos;}
	
	public void attaquer(Personnage cible) {
		System.out.println(nom + " attaque.");
		cible.prendreDegat(atq);
	}
	
	public int getVelocite() {return velocite;};
	
	public void prendreDegat(int degat) {
		int nbDegatPerdu = def - degat;
		if (nbDegatPerdu < 0) nbDegatPerdu = 0;
		System.out.println(nom + " a perdu " + nbDegatPerdu + " points de vie.");
	}
	
	public boolean estMort() {
		if (pv <= 0) return true;
		return false;
	}
}
