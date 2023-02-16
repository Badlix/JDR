package jdr;
import java.util.List;

public class Donjon {
	int [][] map;
	List<Monstre> monstres;
	List<Coffre> coffres;
	
	// 0 = mur et 1 = espace
	public boolean caseAtteignable(int x, int y) {
		if (map[y][x] == 0) {
			System.out.println("Cette case n'est pas accessible.");
			return false;
		}
		return true;
	}
	
	public boolean coffreIci(Position pos) {
		for (Coffre coffre : coffres) {
			if (coffre.getPos() == pos) return true;
		}
		return false;
	}
	
	public boolean monstreIci(Position pos) {
		for (Monstre monstre : monstres) {
			if (monstre.pos == pos) return true;
		}
		return false;
	}
	
}
