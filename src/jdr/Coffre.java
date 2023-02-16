package jdr;

import java.util.List;

public class Coffre {
	private Position pos;
	private List<Item> loot; // max size = 1
	
	public Position getPos() {return pos;}
	
	public List<Item> getLoot() {return loot;}
	
	public void vider() {
		loot.clear();
	}
	
	public Position getPosition() {return pos;}
}
