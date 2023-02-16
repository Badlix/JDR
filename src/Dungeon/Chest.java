package Dungeon;

import java.util.List;
import Character.Position;
import Item.Item;

public class Chest {
	private Position pos;
	private List<Item> loot; // max size = 1
	
	public Position getPos() {return pos;}
	
	public List<Item> getLoot() {return loot;}
	
	public void vider() {
		loot.clear();
	}
	
	public Position getPosition() {return pos;}
}
