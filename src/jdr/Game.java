package jdr;

import Dungeon.Dungeon;
import Item.Artefact;
import Item.Potion;
import Item.Weapon;
import Dungeon.Chest;

import java.util.Iterator;
import java.util.Scanner;

import Character.Hero;
import Character.Position;

// a rendre le 28/02

public class Game {
	static Dungeon dungeon;
	static Hero player;
	
	Game(Dungeon dungeon, Hero player) {
		Game.dungeon = dungeon;
		Game.player = player;
	}
	
	public void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean gameEnd = false;
		while (!gameEnd) {
			showMap();
			System.out.print("Quel action voulez vous faire ? (bouger, voir inventaire, voir stat, ouvrir coffre) : ");
			switch (scan.nextLine()) {
			case "bouger":
				move(scan);
				if (dungeon.isThereAMonster(player.getPosition()) != null) {
					System.out.println("Un combat vient de se déclencher.");
					new Combat(scan, player, dungeon.isThereAMonster(player.getPosition()));
					if (dungeon.isThereAMonster(player.getPosition()).isDead()) {
						dungeon.removeMonster(dungeon.isThereAMonster(player.getPosition()));
					}
				}
				break;
			case "voir inventaire":
				regarderInventory(scan);
				break;
			case "voir stat":
				drawDelimitater();
				player.showStat();
				break;
			case "ouvrir coffre":
				openCoffre(scan, dungeon.isThereAChest(player.getPosition()));
				break;
			case "":
				gameEnd = true;
				break;
			}
			if (player.isDead()) {
				System.out.println("Vous êtes mort...");
				gameEnd = true;
			}
		}
	}
	
	public void regarderInventory(Scanner scan) {
		drawDelimitater();
		player.showInventory();
		drawDelimitater();
		System.out.print("Quelle action voulez vous faire ? (equiper, jeter , utiliser ou rien) : ");
		switch(scan.nextLine()) {
			case "equiper":
				equiper(scan);
				break;
			case "jeter":
				throwItem(scan);
				break;
			case "utiliser":
				usePotion(scan);
				break;
		}
	}
	
	public void openCoffre(Scanner scan, Chest chest) {
		if (chest != null) {
			if (chest.getLoot() != null) {
				player.takeItem(chest.getLoot());
				chest.vider();
			}
		} else {
			System.out.println("Il n'y a pas de coffre dans les environs.");
		}
	}
	
	public void showMap() {
		drawDelimitater();
		for (int y = 0; y < dungeon.getMap().length; y++) {
			for (int x = 0; x < dungeon.getMap()[0].length; x++) {
				if (x == player.getPosition().getX() && y == player.getPosition().getY()) {
					System.out.print("x ");
				} else if (dungeon.isThereAMonster(new Position(x, y)) != null) {
					System.out.print("! ");
				} else if (dungeon.isThereAChest(new Position(x, y)) != null) {
					System.out.print("? ");
				} else if (dungeon.getMap()[y][x] == 1){
					System.out.print("# ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.print("\n");
		}
		drawDelimitater();
	}
	
	static void throwItem(Scanner scan) {
		System.out.print("Quel objet voulez vous jeter ? (l'objet ne pourra pas être récupéré) : ");
		String itemName = scan.nextLine();
		for (Weapon weapon : player.getInventory().getWeapons()) {
			if (itemName.contains(weapon.getName())) {
				player.throwItem(weapon);
				return;
			}
		}
		for (Artefact artefact : player.getInventory().getArtefacts()) {
			if (itemName.contains(artefact.getName())) {
				player.throwItem(artefact);
				return;
			}
		}
		for (Potion potion : player.getInventory().getPotions()) {
			if (itemName.contains(potion.getName())) {
				player.throwItem(potion);
				return;
			}
		}
		System.out.println("Vous ne possedait pas cet objet.");
	}
	
	static void equiper(Scanner scan) {
		System.out.print("Quel objet voulez vous équiper ? : ");
		String itemName = scan.nextLine();
		for (Weapon weapon : player.getInventory().getWeapons()) {
			if (itemName.contains(weapon.getName())) {
				player.equipItem(weapon);
				return;
			}
		}
		for (Artefact artefact : player.getInventory().getArtefacts()) {
			if (itemName.contains(artefact.getName())) {
				player.equipItem(artefact);
				return;
			}
		}
		System.out.println("Vous ne possedait pas cet objet.");
	}
	
	private void usePotion(Scanner scan) {
		System.out.print("Quelle potion voulez vous utiliser ? : ");
		String itemName = scan.nextLine();
		for (Potion potion : player.getInventory().getPotions()) {
			if (itemName.contains(potion.getName())) {
				potion.use(player);
				player.getInventory().removeItem(potion);
				return;
			}
		}
	}
	
	static void move(Scanner scan) {
		System.out.print("Quelle direction ? (z-q-s-d) : ");
		char direction = scan.nextLine().charAt(0);
		switch (direction) {
		case 'z':
			if (dungeon.caseAtteignable(player.getPosition().getX(), player.getPosition().getY()-1)) player.move(direction);
				break;
		case 'q':
			if (dungeon.caseAtteignable(player.getPosition().getX()-1, player.getPosition().getY())) player.move(direction);
				break;
		case 's':
			if (dungeon.caseAtteignable(player.getPosition().getX(), player.getPosition().getY()+1)) player.move(direction);
				break;
		case 'd':
			if (dungeon.caseAtteignable(player.getPosition().getX()+1, player.getPosition().getY())) player.move(direction);
				break;
		}
	}
	
	public void drawDelimitater() {;
		for (int i = 0; i < 75; i++) {
			System.out.print("-");
		}
		System.out.print("\n");
	}
}
