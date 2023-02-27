package Character;

public class Thief extends Hero{

	Thief(String nom, Position pos, Inventory inventaire) {
		super(nom, pos, inventaire);
		super.attaque = 100;
		super.pointDeVie = 250;
		super.poitDeVieMax = 250;
		super.defense = 60;
		super.velocite = 140;
		super.chanceCritique = 25; // (%) entre 0 et 100 -> multiplie dégat par 1,5 
		super.chanceEsquive = 10; // (%) entre 0 et 100
		super.pointCompetence = 40;
		super.pointCompetenceMax = 40;
	}
	
	// ignore la défense
	public int competenceSpeciale(Monster monstre) {
		return critiquePossibility(getAttaque()) + monstre.getDef();
	}
	
}
