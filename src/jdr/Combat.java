package jdr;

import Character.Character;

public class Combat {
	private Character perso1;
	private Character monstre;
	private boolean tourDePerso1;
	
	private void changerTour() {
		tourDePerso1 = !tourDePerso1;
	}
	
	private void definirPremierQuiJoue() {
		if (perso1.getVelocite() >= perso1.getVelocite()) tourDePerso1 = true;
		else tourDePerso1 = false;
	}
	
	public Combat(Character perso1, Character monstre) {
		this.perso1 = perso1;
		this.monstre = monstre;
		definirPremierQuiJoue();
		while (!perso1.estMort() || !monstre.estMort()) {
			// prendre potions
			if (tourDePerso1) perso1.attaquer(monstre);
			else monstre.attaquer(perso1);
			changerTour();
		}
		
	}
}
