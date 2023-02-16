package jdr;

import java.util.List;

public class Inventaire {
	List<Item> armes;
	List<Item> artefacts;
	List<Item> potions;
	
	public void addItem(Item item) {
		switch (item.type) {
		case "arme":
			if (slotRempli(item) == false) armes.add(item);
			break;
		case "artefact":
			if (slotRempli(item) == false) artefacts.add(item);
			break;
		case "potion":
			if (slotRempli(item) == false) potions.add(item);
			break;
		}
	}
	
	public boolean slotRempli(Item item) {
		switch (item.type) {
		case "arme":
			if (armes.size() >= 2) {
				System.out.println("Votre inventaire d'arme est complet.");
				return true;
			}
			break;
		case "artefact":
			if (artefacts.size() >= 3) {
				System.out.println("Votre inventaire d'artefact est complet.");
				return true;
			}
		case "potion":
			if (potions.size() >= 5) {
				System.out.println("Votre inventaire de potion est complet.");
				return true;
			}
		}
		return false;
	}
		
}
