package Character;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Character {
	private String name;
	protected Position pos;
	protected Map<String, Integer> stat; 
	protected int level;
	
	public Character(String name, Position pos, int level, int attaque, int pointDeVieMax, int defense, int velocite, int chanceCritique, int chanceEsquive) {
		this.name = name;
		this.pos = pos;
		this.level = level;
		stat = new HashMap<>();
		stat.put("atq", attaque);
		stat.put("pv", pointDeVieMax);
		stat.put("pvmax", pointDeVieMax);
		stat.put("def", defense);
		stat.put("velocite", velocite);
		stat.put("critique",chanceCritique);
		stat.put("esquive", chanceEsquive);
	}
	
	public String getName() {return name;}
	
	public Position getPosition() {return pos;}
	
	public int getLevel() {return level;}
	
	public Map<String, Integer> getAllStat() {return stat;}
	
	public Integer getStat(String statName) {
		if (stat.containsKey(statName)) return stat.get(statName);
		System.out.println("\nERREUR DE CLÉ (get) -> " + statName);
		return null;
	}
	
	public void setStat(String statName, Integer value) {
		if (stat.containsKey(statName)) {
			stat.put(statName, value);
			return;
		}
		System.out.print("ERREUR DE CLÉ (set) -> " + statName);
	}
	
	public void recuperePV(int value) {
		if (value < 0) value = 0;
		if (getStat("pv") + value > getStat("pvmax")) {
			value = getStat("pvmax") - getStat("pv");
		}
		setStat("pv", getStat("pv") + value);
		System.out.println("Vous avez récupéré " + value + " pv.");
	}
	
	public void recupereMana(int value) {
		if (value < 0) value = 0;
		if (getStat("mana") + value > getStat("manamax")) {
			value = getStat("manamax") - getStat("mana");
		}
		setStat("mana", getStat("mana") + value);
		System.out.println("Vous avez récupéré " + value + " mana.");
	}
	
	protected int critiquePossibility(int degat) {
		Random random = new Random();
		if (random.nextInt(100) < getStat("critique")) {
			System.out.println(name + " inflige un coup critique.");
			return (int)(degat * 1.5);
		}
		return degat;
	}
	
	public void showStat() {
		System.out.println("ATQ : " + getStat("atq"));
		System.out.println("PV : " + getStat("pv") + "/" + getStat("pvmax"));
		System.out.println("DEF : " + getStat("def"));
		System.out.println("VELOCITE : " + getStat("velocite"));
		System.out.println("CHANCE CRITIQUE : " + getStat("critique"));
		System.out.println("CHANCE ESQUIVE : " + getStat("esquive"));
		System.out.println("MANA : " + getStat("mana") + "/" + getStat("manamax"));
	}
		
	public void takeDamage(int damage) {
		Random random = new Random();
		if (random.nextInt(100) < getStat("esquive")) {
			System.out.println(name + " a esquivé.");
			return;
		}
		int nbDegatPerdu = damage - getStat("def");
		if (nbDegatPerdu < 0) nbDegatPerdu = 0;
		setStat("pv", getStat("pv") - nbDegatPerdu);
		System.out.println(" " + name + " a perdu " + nbDegatPerdu + " points de vie.");
	}
	
	public boolean isDead() {
		if (getStat("pv") <= 0) return true;
		return false;
	}
	
	public int attaque(Scanner scan, Monster monstre) {
		System.out.print(name + " attaque. ");
		return critiquePossibility(getStat("atq"));
	}
	
}
