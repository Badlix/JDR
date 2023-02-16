package jdr;

public class Combat {
	private Personnage perso1;
	private Personnage monstre;
	private boolean tourDePerso1;
	
	private void changerTour() {
		tourDePerso1 = !tourDePerso1;
	}
	
	private void definirPremierQuiJoue() {
		if (perso1.getVelocite() >= perso1.getVelocite()) tourDePerso1 = true;
		else tourDePerso1 = false;
	}
	
	public Combat(Personnage perso1, Personnage monstre) {
		this.perso1 = perso1;
		this.monstre = monstre;
		definirPremierQuiJoue();
		while (!perso1.estMort() || !monstre.estMort()) {
			if (tourDePerso1) perso1.attaquer(monstre);
			else monstre.attaquer(perso1);
			changerTour();
		}
		
	}
}
