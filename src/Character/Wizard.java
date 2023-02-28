package Character;

import java.util.Scanner;

public class Wizard extends Hero{

	public Wizard(String name, Position pos, Inventory inventaire) {
		super(name, pos, inventaire, 80, 350, 60, 80, 10, 5, 60);
	}
	
	public int attaque(Scanner scan, Monster monstre) {
		System.out.println("Vous avez " + getStat("mana") + " mana.");
		System.out.println("0 - utiliser une potion");
		System.out.println("1 - coup de baton (cout 0)");
		System.out.println("2 - boule de feu (cout 5)");
		System.out.println("3 - regénération (cout 5)");
		System.out.println("4 - boule de feu ultime (cout 20)");
		System.out.print("Que voulez vous faire (0-1-2-3-4) : ");
		switch (scan.nextLine()) {
		case "0":
			getInventory().show();
			usePotion(scan);
		case "1":
			System.out.print("Vous utilisez coup de baton. ");
			return critiquePossibility(getStat("atq"));
		case "2":
			if (getStat("mana") >= 5) {
				System.out.print("Vous utilisez boule de feu. ");
				setStat("mana", getStat("mana") - 5);
				return critiquePossibility((int)(getStat("atq")*1.5));
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan, monstre);
			}
		case "3":
			if (getStat("mana") >= 5) {
				System.out.print("Vous utilisez regénération. ");
				setStat("mana", getStat("mana") - 5);
				recuperePV((int)(getStat("pvmax")*0.3));
				recupereMana((int)(getStat("manamax")*0.05));
				return critiquePossibility(getStat("atq") + monstre.getStat("def"));
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan, monstre);
			}
		case "4":
			if (getStat("mana") >= 20) {
				System.out.print("Vous utilisez boule de feu ultime. ");
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
