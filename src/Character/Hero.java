package Character;

import Item.Item;
import Item.Potion;
import Item.Weapon;

import java.util.Map;
import java.util.Scanner;

import Item.Artefact;

public class Hero extends Character{
	private Inventory inventory;
	private int exp; 
	
	public Hero(String name, Position pos, Inventory inventory, int attaque, int pointDeVieMax, int defense, int velocite, int chanceCritique, int chanceEsquive, int mana) {
		super(name, pos, 1, attaque, pointDeVieMax, defense, velocite, chanceCritique, chanceEsquive);
		super.stat.put("mana", mana);
		super.stat.put("manamax", mana);
		this.inventory = inventory;
		this.exp = 0;
	}
	
	// return l'arme équipé
	public Weapon getWeapon() {return inventory.getEquipWeapon();}
	
	// return l'artefact équipé
	public Artefact getArtefact() {return inventory.getEquipArtefact();}
	
	public Inventory getInventory() {return inventory;}
	
	public int getExp() {return exp;}
	
	public void gainExp(int quantite) {
		exp += quantite;
		while (level*10 <= exp && level < 50) {
			exp -= level*10;
			levelUp();
		}
	}
	
	public void levelUp() {
		setStat("atq", getStat("atq") + 10);
		setStat("pv", getStat("pv") + 20);
		setStat("pvmax", getStat("pvmax") + 20);
		setStat("def", getStat("def") + 10);
		++level;
		System.out.println("Vous êtes passé au niveau " + level);
	}
	
	public void desequipItem(Item item) {
		if (item == inventory.getEquipWeapon() || item == inventory.getEquipArtefact()) {
			for (int i = 0; i < item.getStatNames().size(); i++) {
				setStat(item.getStatNames().get(i), getStat(item.getStatNames().get(i)) - item.getStatValues().get(i));
			}
		}
	}
	
	public void equipItem(Item item) {
		if (item == inventory.getEquipWeapon() || item == inventory.getEquipArtefact()) {
			System.out.print("Cet objet est déjà équipé.");
			return;
		}
		if (item.getClass() == Weapon.class && inventory.getEquipWeapon() != null) desequipItem(inventory.getEquipWeapon());
		else if (item.getClass() == Artefact.class && inventory.getEquipArtefact() != null) desequipItem(inventory.getEquipArtefact());
		for (int i = 0; i < item.getStatNames().size(); i++) {
			setStat(item.getStatNames().get(i), getStat(item.getStatNames().get(i)) + item.getStatValues().get(i));
			if (item.getStatNames().get(i) == "pv" || item.getStatNames().get(i) == "mana") {
				setStat(item.getStatNames().get(i) + "max", getStat(item.getStatNames().get(i) + "max") + item.getStatValues().get(i));
			}
		}
		inventory.equip(item);
	}
	
	public void showInventory() {
		inventory.show();
	}
	
	public void takeItem(Item item) {
		inventory.addItem(item);
	}
	
	public void throwItem(Item item) {
		desequipItem(item);
		inventory.removeItem(item);
		System.out.print("Vous avez jeté " + item.getName());
	}
	
	protected void usePotion(Scanner scan) {
		System.out.print("Quelle potion voulez vous use ? : ");
		String itemName = scan.nextLine();
		for (Potion potion : getInventory().getPotions()) {
			if (itemName.contains(potion.getName())) {
				potion.use(this);
				getInventory().removeItem(potion);
				return;
			}
		}
	}
		
	public void move(char direction) {
		switch (direction) {
			case 'z':
				pos.addY(-1);
				break;
			case 'd':
				pos.addX(1);
				break;
			case 's':
				pos.addY(1);
				break;
			case 'q':
				pos.addX(-1);
				break;
		}
	}
	
}
