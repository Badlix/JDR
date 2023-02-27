package jdr;

import Character.Character;

public class Combat {
	private Character joueur;
	private Character monstre;
	private boolean tourDuJoueur;
	
	public Combat(Character joueur, Character monstre) {
		this.joueur = joueur;
		this.monstre = monstre;
	}
	
	private void changerTour() {
		tourDuJoueur = !tourDuJoueur;
	}
	
//	private void definirPremierQuiJoue() {
//		if (perso1.getVelocite() >= perso1.getVelocite()) tourDePerso1 = true;
//		else tourDePerso1 = false;
//	}
	
//	public Combat(Character perso1, Character monstre) {
//		this.perso1 = perso1;
//		this.monstre = monstre;
//		definirPremierQuiJoue();
//		while (!perso1.estMort() || !monstre.estMort()) {
//			// prendre potions
//			if (tourDePerso1) perso1.attaquer(monstre);
//			else monstre.attaquer(perso1);
//			changerTour();
//		}
//		
//	}
}
