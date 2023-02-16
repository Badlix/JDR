package Dungeon;

import java.util.List;

import Character.Monster;
import Dungeon.Chest;
import Character.Position;

public class Dungeon {
	int [][] map;
	List<Monster> monstres;
	List<Chest> coffres;
	
	// 0 = mur et 1 = espace
	public boolean caseAtteignable(int x, int y) {
		if (map[y][x] == 0) {
			System.out.println("Cette case n'est pas accessible.");
			return false;
		}
		return true;
	}
	
	public boolean coffreIci(Position pos) {
		for (Chest coffre : coffres) {
			if (coffre.getPos() == pos) return true;
		}
		return false;
	}
	
	public boolean monstreIci(Position pos) {
		for (Monster monstre : monstres) {
			if (monstre.getPosition() == pos) return true;
		}
		return false;
	}
}
