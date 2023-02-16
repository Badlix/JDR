package jdr;

import Dungeon.Dungeon;
import Dungeon.Chest;
import Character.Hero;

// a rendre le 28/02

public class Game {
	Dungeon donjon;
	Hero joueur;
	
	void ouvrirCoffre(Chest coffre) {
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
