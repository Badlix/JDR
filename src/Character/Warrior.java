package Character;

import java.util.Scanner;

public class Warrior extends Hero {

	public Warrior(String name, Position pos, Inventory inventaire) {
		super(name, pos, inventaire, 100, 430, 70, 80, 5, 5, 40);
	}
	
	public int attaque(Scanner scan, Monster monstre) {
		System.out.println("Vous avez " + getStat("mana") + " mana.");
		System.out.println("0 - utiliser une potion");
		System.out.println("1 - coup d'épee (cout 0)");
		System.out.println("2 - coupe d'épée chargée (cout 5)");
		System.out.println("3 - attaque perce armure (cout 5)");
		System.out.println("4 - coup d'épee ultime (cout 20)");
		System.out.print("Que voulez vous faire (0-1-2-3-4) : ");
		switch (scan.nextLine()) {
		case "0":
			getInventory().show();
			usePotion(scan);
		case "1":
			System.out.print("Vous utilisez coup d'épee. ");
			return critiquePossibility(getStat("atq"));
		case "2":
			if (getStat("mana") >= 5) {
				System.out.print("Vous utilisez coup d'épee chargée.");
				setStat("mana", getStat("mana") - 5);
				return critiquePossibility((int)(getStat("atq")*1.5));
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan, monstre);
			}
		case "3":
			if (getStat("mana") >= 5) {
				System.out.print("Vous utilisez attaque perce armure. ");
				setStat("mana", getStat("mana") - 5);
				return critiquePossibility(getStat("atq") + monstre.getStat("def"));
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan, monstre);
			}
		case "4":
			if (getStat("mana") >= 20) {
				System.out.print("Vous utilisez coup d'épee ultime. ");
				setStat("mana", getStat("mana") - 20);
				return critiquePossibility(getStat("atq")*5);
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan, monstre);
			}
		}
		System.out.println("Vous n'avez pas entré un numéro valide.");
		return attaque(scan, monstre);
	}
}
