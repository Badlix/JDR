package Character;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Character {
	private String nom;
	protected Position pos;
	protected Map<String, Integer> stat = new HashMap<>(); 
	protected int attaque;
	protected int pointDeVie;
	protected int poitDeVieMax;
	protected int defense;
	protected int velocite;
	protected int chanceCritique; // (%) entre 0 et 100 -> multiplie dégat par 1,5 
	protected int chanceEsquive; // (%) entre 0 et 100
	protected int pointCompetence;
	protected int pointCompetenceMax;
	
	public Character(String nom, Position pos, int attaque, int pointDeVieMax, int defense, int velocite, int chanceCritique, int chanceEsquive) {
		stat.put("atq", attaque);
		stat.put("pv", pointDeVieMax);
		stat.put("pvmax", pointDeVieMax);
		stat.put("def", defense);
		stat.put("velocite", velocite);
		stat.put("critique",chanceCritique);
		stat.put("esquive", chanceEsquive);
		this.nom = nom;
		this.pos = pos;
	}
	
	public Map<String, Integer> getAllStat() {return stat;}
	
	public Integer getStat(String nomDuStat) {
		if (stat.containsKey(nomDuStat)) return stat.get(nomDuStat);
		System.out.print("ERREUR DE CLÉ");
		return null;
	}
	
	protected int critiquePossibility(int degat) {
		Random random = new Random();
		if (random.nextInt(100) < chanceCritique) {
			System.out.print(nom + " inflige un coup critique.");
			return (int)(degat * 1.5);
		}
		return degat;
	}
	
	public String getNom() {return nom;}
	
	public Position getPosition() {return pos;}
	
	public int getAttaque() {
		return attaque;
	}
	
	public int getPV() {
		return pointDeVie;
	}
	
	public int getChanceCritique() {
		return chanceCritique;
	}
	
	public int getDef() {
		return defense;
	}
	
	public int getVelocite() {return velocite;}
	
	public int getCurrentPointCompetence() {return pointCompetence;}
	
	public void prendreDegat(int degat) {
		Random random = new Random();
		if (random.nextInt(100) < chanceEsquive) {
			System.out.print(nom + " a esquivé.");
		}
		int nbDegatPerdu = getDef() - degat;
		if (nbDegatPerdu < 0) nbDegatPerdu = 0;
		System.out.println(nom + " a perdu " + nbDegatPerdu + " points de vie.");
	}
	
	public boolean estMort() {
		if (getPV() <= 0) return true;
		return false;
	}
	
	public int attaque(Scanner scan) {
		return critiquePossibility(getAttaque());
	}

}
