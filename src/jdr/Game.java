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
	static Dungeon donjon;
	static Hero joueur;
	
	Game(Dungeon donjon, Hero joueur) {
		Game.donjon = donjon;
		Game.joueur = joueur;
	}
	
	public void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean finDuJeu = false;
		while (!finDuJeu) {
			showMap();
			System.out.print("\nQuel action voulez vous faire ? (bouger, inventaire, ouvrir coffre) : ");
			switch (scan.nextLine()) {
			case "bouger":
				seDeplacer(scan);
				break;
			case "inventaire":
				regarderInventaire(scan);
				break;
			case "ouvrir coffre":
				ouvrirCoffre(scan, donjon.isThereAChest(joueur.getPosition()));
				break;
			case "":
				finDuJeu = true;
				break;
			}
		}
	}
	
	public void regarderInventaire(Scanner scan) {
		joueur.regarderInventaire();
		System.out.print("Quelle action voulez vous faire ? (equiper, jeter ou rien) : ");
		switch(scan.nextLine()) {
			case "equiper":
				equiper(scan);
				break;
			case "jeter":
				jeter(scan);
				break;
		}
	}
	
	public void ouvrirCoffre(Scanner scan, Chest chest) {
		if (chest != null) {
			if (chest.getLoot() != null) {
				joueur.prendreItem(chest.getLoot());
				chest.vider();
			}
		} else {
			System.out.print("Il n'y a pas de coffre dans les environs.");
		}
	}
	
	public void showMap() {
		for (int y = 0; y < donjon.getMap().length; y++) {
			System.out.print("\n");
			for (int x = 0; x < donjon.getMap()[0].length; x++) {
				if (x == joueur.getPosition().getX() && y == joueur.getPosition().getY()) {
					System.out.print("x");
				} else if (donjon.isThereAMonster(new Position(x, y)) != null) {
					System.out.print("o");
				} else if (donjon.isThereAChest(new Position(x, y)) != null) {
					System.out.print("c");
				} else if (donjon.getMap()[y][x] == 1){
					System.out.print('#');
				} else {
					System.out.print(' ');
				}
			}
		}
	}
	
	static void jeter(Scanner scan) {
		System.out.print("\nQuel objet voulez vous jeter ? (l'objet ne pourra pas être récupéré) : ");
		String itemName = scan.nextLine();
		for (Weapon weapon : joueur.getInventaire().getWeapons()) {
			if (itemName.contains(weapon.getNom())) {
				joueur.jeterItem(weapon);
				return;
			}
		}
		for (Artefact artefact : joueur.getInventaire().getArtefacts()) {
			if (itemName.contains(artefact.getNom())) {
				joueur.jeterItem(artefact);
				return;
			}
		}
		for (Potion potion : joueur.getInventaire().getPotions()) {
			if (itemName.contains(potion.getNom())) {
				joueur.jeterItem(potion);
				return;
			}
		}
		System.out.print("\nVous ne possedait pas cet objet.");
	}
	
	static void equiper(Scanner scan) {
		System.out.print("\nQuel objet voulez vous équiper ? : ");
		String itemName = scan.nextLine();
		for (Weapon weapon : joueur.getInventaire().getWeapons()) {
			if (itemName.contains(weapon.getNom())) {
				joueur.equiperItem(weapon);
				return;
			}
		}
		for (Artefact artefact : joueur.getInventaire().getArtefacts()) {
			if (itemName.contains(artefact.getNom())) {
				joueur.equiperItem(artefact);
				return;
			}
		}
		System.out.print("\nVous ne possedait pas cet objet.");
	}
	
	static void seDeplacer(Scanner scan) {
		System.out.print("Quel direction ? (z-q-s-d) : ");
		char direction = scan.nextLine().charAt(0);
		switch (direction) {
		case 'z':
			if (donjon.caseAtteignable(joueur.getPosition().getX(), joueur.getPosition().getY()-1)) joueur.seDeplacer(direction);
				break;
		case 'q':
			if (donjon.caseAtteignable(joueur.getPosition().getX()-1, joueur.getPosition().getY())) joueur.seDeplacer(direction);
				break;
		case 's':
			if (donjon.caseAtteignable(joueur.getPosition().getX(), joueur.getPosition().getY()+1)) joueur.seDeplacer(direction);
				break;
		case 'd':
			if (donjon.caseAtteignable(joueur.getPosition().getX()+1, joueur.getPosition().getY())) joueur.seDeplacer(direction);
				break;
		}
	}
}
