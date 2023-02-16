package jdr;

// a rendre le 28/02

public class Game {
	Donjon donjon;
	Hero joueur;
	
	void ouvrirCoffre(Coffre coffre) {
		if (coffre.getLoot().size() == 0) {
			System.out.println("Ce coffre est vide.");
			return;
		}
		if (joueur.getInventaire().slotRempli(coffre.getLoot().get(0)) == false) {
			coffre.vider();
			joueur.getInventaire().addItem(coffre.getLoot().get(0));
		} 
	}
}
