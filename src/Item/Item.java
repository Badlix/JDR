package Item;

public class Item {
	private String nom;
	protected int stat1;
	protected int stat2;
	
	public Item(String nom, int stat1, int stat2) {
		this.nom = nom;
		this.stat1 = stat1;
		this.stat2 = stat2;
	}
	
	public String getNom() {return nom;}
	
	public int getStat1() {return stat1;}
	
	public int getStat2() {return stat2;}	
	
}
