package Character;

import java.util.Scanner;

public class Warrior extends Hero{

	Warrior(String name, Position pos, Inventory inventaire) {
		super(name, pos, inventaire);
		super.attaque = 120;
		super.pointDeVie = 450;
		super.poitDeVieMax = 450;
		super.defense = 90;
		super.velocite = 80;
		super.chanceCritique = 5; // (%) entre 0 et 100 -> multiplie dégat par 1,5 
		super.chanceEsquive = 5; // (%) entre 0 et 100
		super.pointCompetence = 40;
		super.pointCompetenceMax = 40;
	}
	
	public int attaque(Scanner scan, Monster monstre) {
		System.out.println("Que voulez vous faire (0-1-2-3-4) : ");
		System.out.println("0 - utiliser une potion");
		System.out.println("1 - attaque basique (cout 0)");
		System.out.println("2 - attaque chargée (cout 5)");
		System.out.println("3 - attaque perce armure (cout 10)");
		System.out.println("4 - attaque ultime (cout 20)");
		switch (scan.nextLine()) {
		case "1":
			return critiquePossibility(getAttaque());
		case "2":
			if (getCurrentPointCompetence() >= 5) {
				pointCompetence -= 5;
				return critiquePossibility((int)(getAttaque()*1.5));
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan);
			}
		case "3":
			if (getCurrentPointCompetence() >= 10) {
				pointCompetence -= 10;
				return critiquePossibility(getAttaque() + monstre.getDef());
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan);
			}
		case "4":
			if (getCurrentPointCompetence() >= 20) {
				pointCompetence -= 20;
				return critiquePossibility(getAttaque()*5);
			} else {
				System.out.println("Vous n'avez pas assez de point de compétence.");
				return attaque(scan);
			}
		}
		System.out.println("Vous n'avez pas entré un numéro valide.");
		return attaque(scan);
	}
	
}
