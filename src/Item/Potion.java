package Item;

import java.util.List;

import Character.Hero;

public class Potion extends Item {
	private String statName; // = pv ou mana
	private int statValue;
	
	public Potion(String name, String statName, Integer statValue) {
		super(name, List.of(statName), List.of(statValue));
		this.statName = statName;
		this.statValue = statValue;
	}
	
	public void use(Hero joueur) {
		System.out.println("Vous utilisez " + getName());
		if (statName == "pv") joueur.recuperePV(statValue);
		else if (statName == "mana") joueur.recupereMana(statValue);
	}
	
}
