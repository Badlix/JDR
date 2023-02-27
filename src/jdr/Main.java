package jdr;
import Character.Hero;
import Character.Position;
import Dungeon.Dungeon;
import Item.Artefact;
import Item.Item;
import Item.Potion;
import Character.Inventory;
import Character.Monster;
import Dungeon.Chest;
import Item.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static Dungeon initFirstDungeon() {
		int[][] map = {
				{0,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,1},
				{0,1,1,1,1,1,1,1,1,1},
		};
		List<Monster> monstres = new ArrayList<>();
		List<Chest> coffres = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		items.add(new Weapon("Moyenne Epee", 35, 5));
		coffres.add(new Chest(new Position(1,1), items));
		return new Dungeon(map, new Position(0,1) ,monstres, coffres);
	}
	
	public static void main(String[] args) {
		List<Artefact> artefacts = new ArrayList<>();
		List<Potion> potions = new ArrayList<>();
		List<Weapon> armes = new ArrayList<>();
		armes.add(new Weapon("Grosse Epee", 50, 2));
		Dungeon donjon = initFirstDungeon();
		Hero hero = new Hero("Guerrier", donjon.getSpaw(), new Inventory(armes, artefacts, potions));
		Game partie = new Game(donjon, hero);
		partie.main(null);
	}
}
