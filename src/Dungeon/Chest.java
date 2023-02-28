package Dungeon;
import Character.Hero;
import java.util.List;
import Character.Position;
import Item.Item;

public class Chest {
	private Position pos;
	private List<Item> loot; // max size = 1
	
	public Chest(Position pos, List<Item> loot) {
		this.pos = pos;
		this.loot = loot;
	}
	
	public Position getPos() {return pos;}
	
	public Item getLoot() {
		if (loot != null && loot.size() != 0) {
			return loot.get(0);
		}
		System.out.println("Ce coffre est vide.");
		return null;
	}
	
	public void vider() {
		loot = List.of();
	}
	
	public Position getPosition() {return pos;}
	
	public void ouvrir(Hero joueur) {
		if (getLoot() == null) {
			System.out.println("Ce coffre est vide.");
			return;
		}
		if (joueur.getInventory().isSlotFull(getLoot()) == false) {
			vider();
			joueur.getInventory().addItem(getLoot());
			return;
		}
		joueur.getInventory().addItem(getLoot());
	}
}
