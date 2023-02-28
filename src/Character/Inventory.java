package Character;
import Item.Weapon;
import java.util.List;

import Item.Artefact;
import Item.Item;
import Item.Potion;

public class Inventory {
	private List<Weapon> weapons;
	private List<Artefact> artefacts;
	private List<Potion> potions;
	private Weapon equipWeapon = null;
	private Artefact equipArtefact = null;
	
	public Inventory(List<Weapon> weapons, List<Artefact> artefacts, List<Potion> potions) {
		this.weapons = weapons;
		this.artefacts = artefacts;
		this.potions = potions;
	}
	
	public List<Weapon> getWeapons() {
		return weapons;
	}
	
	public List<Artefact> getArtefacts() {
		return artefacts;
	}
	
	public List<Potion> getPotions() {
		return potions;
	}
	
	public Weapon getEquipWeapon() {return equipWeapon;}
	
	public Artefact getEquipArtefact() {return equipArtefact;}
	
	public void equip(Item item) {
		if (item.getClass() == Weapon.class) {
			equipWeapon = (Weapon) item;
			System.out.println("Vous avez équipé " + item.getName());
			return;
		}
		if (item.getClass() == Artefact.class) {
			equipArtefact = (Artefact) item;
			System.out.println("Vous avez équipé " + item.getName());
			return;
		}
		System.out.println("Vous ne pouvez pas équiper cet objet.");
	}
	
	public void show() {
		System.out.println("Votre inventaire contient : ");
		System.out.print("weapons (" + weapons.size() + ") :");
		for (Weapon arme : weapons) {
			System.out.print("\n   - " + arme.getName() + " : ");
			if (arme == equipWeapon) System.out.print(" (équipé)");
		}
		System.out.print("\nartefacts (" + artefacts.size() + "):");
		for (Artefact artefact : artefacts) {
			System.out.print("\n   - " + artefact.getName() + " : " );
		}
		System.out.print("\npotions (" + potions.size() + "):");
		for (Potion potion : potions) {
			System.out.print("\n   - " + potion.getName());
		}
		System.out.print("\n");
	}
	
	public void addItem(Item item) {
		if (item.getClass() == Weapon.class) {	
			if (isSlotFull(item) == false) {
				weapons.add((Weapon) item);
				System.out.println("Vous avez récupéré " + item.getName());
			} else {
				System.out.println("Votre inventaire d'arme est complet.");
			}
		} else if (item.getClass() == Artefact.class) {
			if (isSlotFull(item) == false) {
				artefacts.add((Artefact) item);
				System.out.println("Vous avez récupéré " + item.getName());
			} else {
				System.out.println("Votre inventaire d'artefact est complet.");
			}
		} else if (item.getClass() == Potion.class){
			if (isSlotFull(item) == false) {
				potions.add((Potion) item);
				System.out.println("Vous avez récupéré " + item.getName());
			} else {
				System.out.println("Votre inventaire de potion est complet.");
			}
		}
	}
	
	public void removeItem(Item item) {
		if (item.getClass() == Weapon.class) {
			weapons.remove(item);
			if (equipWeapon == item) equipWeapon = null;
		}
		else if (item.getClass() == Artefact.class) {
			artefacts.remove(item);
			if (equipArtefact == item) equipArtefact = null;
		}
		else if (item.getClass() == Potion.class) potions.remove(item);
	}
	
	public boolean isSlotFull(Item item) {
		if (item.getClass() == Weapon.class) {
			if (weapons.size() >= 2) {
				return true;
			}
		} else if (item.getClass() == Artefact.class) {
			if (artefacts.size() >= 3) {
				return true;
			}
		} else {
			if (potions.size() >= 5) {
				return true;
			}
		}
		return false;
	}
}
