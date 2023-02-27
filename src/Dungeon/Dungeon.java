package Dungeon;

import java.util.List;

import Character.Monster;
import Dungeon.Chest;
import Character.Position;

public class Dungeon {
	private int [][] map;
	private Position spawn;
	private List<Monster> monstres;
	private List<Chest> coffres;
	
	public Dungeon(int [][] map, Position spawn, List<Monster> monstres, List<Chest> coffres) {
		this.map = map;
		this.spawn = spawn;
		this.monstres = monstres;
		this.coffres = coffres;
	}
	
	public int[][] getMap() {
		return map;
	}
	
	public Position getSpaw() {
		return spawn;
	}
	
	public Monster isThereAMonster(Position pos) {
		for (Monster monstre : monstres) {
			if (monstre.getPosition() == pos) return monstre;
		}
		return null;
	}
	
	public Chest isThereAChest(Position pos) {
		for (Chest coffre : coffres) {
			if (coffre.getPosition().getX() == pos.getX() && coffre.getPosition().getY() == pos.getY()) return coffre;
		}
		return null;
	}
	
	// 0 = mur et 1 = espace
	public boolean caseAtteignable(int x, int y) {
		if ((x < 0 || x >= map[0].length) || (y < 0 || y >= map.length)) {
			System.out.println("Cette case n'est pas accessible.");
			return false;
		}
		if (map[y][x] == 1) {
			System.out.println("Cette case n'est pas accessible.");
			return false;
		}
		System.out.println("Deplacement reussi.");
		return true;
	}
}
