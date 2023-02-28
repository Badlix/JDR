package jdr;
import Character.Position;
import Character.Rogue;
import Character.Warrior;
import Character.Wizard;
import Dungeon.Dungeon;
import Item.Artefact;
import Item.Item;
import Item.Potion;
import Character.Hero;
import Character.Inventory;
import Character.Monster;
import Dungeon.Chest;
import Item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
// Génération aléatoire des stat des items
	
//	public static Weapon generateWeapon(String nom) {
//		Random random = new Random();
//		int pourcent = 100; // pourcentage de chance d'avoir un attribut
//		List<String> possibleAttribut = List.of("atq", "critique");
//		List<String> currentAttribut = new ArrayList<>();
//		List<Integer> currentValues = new ArrayList<>();
//		for (String attribut : possibleAttribut) {
//			if (random.nextInt(100) < pourcent) {
//				currentAttribut.add(attribut);
//				pourcent = (int)(pourcent * 0.75);
//				if (attribut == "atq") currentValues.add(random.nextInt(80));
//				else currentValues.add(random.nextInt(20));
//			}
//		}
//		return new Weapon(nom, currentAttribut, currentValues);
//	}
//	
//	public static Artefact generateArtefact(String nom) {
//		Random random = new Random();
//		int pourcent = 100; // pourcentage de chance d'avoir un attribut
//		List<String> possibleAttribut = List.of("atq", "critique", "pv", "def", "mana", "esquive");
//		List<String> currentAttribut = new ArrayList<>();
//		List<Integer> currentValues = new ArrayList<>();
//		for (String attribut : possibleAttribut) {
//			if (random.nextInt(100) < pourcent) {
//				currentAttribut.add(attribut);
//				pourcent = (int)(pourcent*0.75);
//				if (attribut == "atq" || attribut == "pv" || attribut == "def") currentValues.add(random.nextInt(80));
//				else currentValues.add(random.nextInt(20));
//			}
//		}
//		return new Artefact(nom, currentAttribut, currentValues);
//	}
	
	public static Dungeon initFirstDungeon() {
		Random random = new Random();
		int[][] map = {
				{1,1,1,0,0,0,0,0,1,1,1},
				{0,1,1,1,1,0,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,1,0,1,1,1,1,0,1,1,1},
				{0,1,0,0,0,0,0,0,0,0,1},
				{1,1,0,1,0,1,1,1,1,0,1},
				{0,0,0,1,0,0,0,0,1,1,1}
		};
		// ----- Monster ----- 
		List<Monster> monstres = new ArrayList<>();
		monstres.add(new Monster("Gobelin", new Position(2,5), 3, 100, 200, 30, 10, 10, 15));
		monstres.add(new Monster("Gobelin", new Position(3,2), 3, 100, 200, 30, 10, 10, 15));
		monstres.add(new Monster("Gobelin", new Position(3,4), 3, 100, 200, 30, 10, 10, 15));
		monstres.add(new Monster("Gobelin", new Position(5,1), 3, 100, 200, 30, 10, 10, 15));
		monstres.add(new Monster("Gobelin", new Position(8,4), 3, 100, 200, 30, 10, 10, 15));
		monstres.add(new Monster("Gobelin Géant", new Position(5,0), 7, 150, 300, 60, 130, 10, 10));
		monstres.add(new Monster("Gobelin Géant", new Position(5,6), 7, 150, 300, 60, 130, 10, 10));
		monstres.add(new Monster("Gobelin Géant", new Position(7,2), 7, 150, 300, 60, 130, 10, 10));
		monstres.add(new Monster("Dragon", new Position(10,2), 25, 200, 500, 100, 150, 10, 5));
		// ----- Weapon -----
		List<Item> allChestItem = new ArrayList<>();
		allChestItem.add(new Weapon("Epee", List.of("atq"), List.of(60)));
		allChestItem.add(new Weapon("Dague", List.of("atq", "critique"), List.of(30, 10)));
		allChestItem.add(new Weapon("Baton", List.of("atq", "mana"), List.of(50, 10)));
		//----- Artefact -----
		allChestItem.add(new Artefact("Coupe", List.of("pv", "mana"), List.of(200, 10)));
		allChestItem.add(new Artefact("Chaussette", List.of("def", "esquive"), List.of(30, 5)));
		allChestItem.add(new Artefact("Baguette", List.of("def"), List.of(60)));
		// ----- Potion ------
		allChestItem.add(new Potion("Grosse Potion de vie", "pv", 500));
		allChestItem.add(new Potion("Grosse Potion de mana", "pv", 60));
		// ----- Coffres -----
		List<Chest> chests = new ArrayList<>();
		List<Position> positionCoffre = List.of(new Position(0,1), new Position(0, 4), new Position(0,6), new Position(3, 0), new Position(4, 0), new Position(6, 6), new Position(7, 0), new Position(7, 6), new Position(9, 5));
		int resultRandom;
		for (int iCoffre = 0; iCoffre < 8; iCoffre++) {
			resultRandom = random.nextInt(allChestItem.size());
			if (resultRandom == allChestItem.size()) {
				chests.add(new Chest(positionCoffre.get(iCoffre), null));
			} else {
				chests.add(new Chest(positionCoffre.get(iCoffre), List.of(allChestItem.get(resultRandom))));
				allChestItem.remove(resultRandom);
			}
		}		
		return new Dungeon(map, new Position(0,2) ,monstres, chests);
	}
	
	public static Hero chooseHero(Dungeon dungeon) {
		List<Artefact> artefacts = new ArrayList<>();
		List<Potion> potions = new ArrayList<>();
		potions.add(new Potion("Potion de vie", "pv", 250));
		potions.add(new Potion("Potion de mana", "pv", 20));
		List<Weapon> weapons = new ArrayList<>();
		weapons.add(new Weapon("Vieille Arme", List.of("atq"), List.of(5)));
		Scanner scan = new Scanner(System.in);
		System.out.println("- Warrior");
		System.out.println("- Rogue");
		System.out.println("- Wizard");
		System.out.print("Choississez une classe :");
		switch (scan.nextLine()) {
		case "Warrior":
			return new Warrior("Guerrier", dungeon.getSpaw(), new Inventory(weapons, artefacts, potions));
		case "Rogue":
			return new Rogue("Voleur", dungeon.getSpaw(), new Inventory(weapons, artefacts, potions));
		case "Wizard":
			return new Wizard("Mage", dungeon.getSpaw(), new Inventory(weapons, artefacts, potions));
		}
		System.out.println("Vous n'avez pas entré un bon nom de classe.");
		return chooseHero(dungeon);
	}
	
	public static void main(String[] args) {
		Dungeon dungeon = initFirstDungeon();
		Game partie = new Game(dungeon, chooseHero(dungeon));
		partie.main(null);
	}
}
