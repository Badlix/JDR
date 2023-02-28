package Character;

import java.util.Scanner;

public class Rogue extends Hero {
	
	public Rogue(String name, Position pos, Inventory inventaire) {
		super(name, pos, inventaire, 80, 300, 60, 140, 20, 10, 40);
	}
	
	public int attaque(Scanner scan, Monster monstre) {
		System.out.println("Vous avez " + getStat("mana") + " mana.");
		System.out.println("0 - utiliser une potion");
		System.out.println("1 - coup de dague (cout 0)");
		System.out.println("2 - coup de dague empoisonnée (cout 5)");
		System.out.println("3 - vol de vie et de mana (cout 10)");
		System.out.println("4 - coup de dague ultime (cout 20)");
		System.out.print("Que voulez vous faire (0-1-2-3-4) : ");
		switch (scan.nextLine()) {
		case "0":
			getInventory().show();
			usePotion(scan);
		case "1":
			System.out.println("Vous utilisez coup de dague.");
			return critiquePossibility(getStat("atq"));
		case "2":
			if (getStat("mana") >= 5) {
				System.out.print("Vous utilisez coup de dague empoisonnée. ");
				setStat("mana", getStat("mana") - 5);
				return critiquePossibility((int)(getStat("atq")*1.5));
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan, monstre);
			}
		case "3":
			if (getStat("mana") >= 10) {
				System.out.print("Vous utilisez vol de vie et de mana. ");
				setStat("mana", getStat("mana") - 10);
				int degatInflige = critiquePossibility(getStat("atq")) - monstre.getStat("def");
				recuperePV((int)(degatInflige*0.75));
				recupereMana((int)(degatInflige*0.2));
				return degatInflige + monstre.getStat("def");
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan, monstre);
			}
		case "4":
			if (getStat("mana") >= 20) {
				System.out.print("Vous utilisez coup de dague ultime. ");
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
