package Character;
import Item.Weapon;
import java.util.List;

import Item.Artefact;
import Item.Item;
import Item.Potion;

public class Inventory {
	private List<Weapon> armes;
	private List<Artefact> artefacts;
	private List<Potion> potions;
	private Weapon armeEquiper;
	private Artefact artefactEquiper;
	
	public Inventory(List<Weapon> armes, List<Artefact> artefacts, List<Potion> potions) {
		this.armes = armes;
		this.artefacts = artefacts;
		this.potions = potions;
		this.armeEquiper = armes.get(0); 
	}
	
	public List<Weapon> getWeapons() {
		return armes;
	}
	
	public List<Artefact> getArtefacts() {
		return artefacts;
	}
	
	public List<Potion> getPotions() {
		return potions;
	}
	
	public Weapon getEquipWeapon() {return armeEquiper;}
	
	public Artefact getEquipArtefact() {return artefactEquiper;}
	
	public void equiper(Item item) {
		if (item.getClass() == Weapon.class) {
			armeEquiper = (Weapon) item;
			System.out.print("Vous avez équipé " + item.getNom());
			return;
		}
		if (item.getClass() == Artefact.class) {
			artefactEquiper = (Artefact) item;
			System.out.print("Vous avez équipé " + item.getNom());
			return;
		}
		System.out.print("Vous ne pouvez pas équiper cet objet.");
	}
	
	public void show() {
		System.out.print("Votre inventaire contient : ");
		System.out.print("\narmes (" + armes.size() + ") :");
		for (Weapon arme : armes) {
			System.out.print("\n   - " + arme.getNom() + " : +" + arme.getStat1() + " degat | +" + arme.getStat2() + "% de coup critique");
			if (arme == armeEquiper) System.out.print(" (équipé)");
		}
		System.out.print("\nartefacts (" + artefacts.size() + "):");
		for (Artefact artefact : artefacts) {
			System.out.print("\n   - " + artefact.getNom() + " : +" + artefact.getStat1() + " pv | +" + artefact.getStat2() + " def");
		}
		System.out.print("\npotions (" + potions.size() + "):");
		for (Potion potion : potions) {
			System.out.println("\n   - " + potion.getNom());
		}
	}
	
	public void addItem(Item item) {
		if (item.getClass() == Weapon.class) {	
			if (slotRempli(item) == false) {
				armes.add((Weapon) item);
				System.out.print("Vous avez récupéré " + item.getNom());
			} else {
				System.out.println("Votre inventaire d'arme est complet.");
			}
		} else if (item.getClass() == Artefact.class) {
			if (slotRempli(item) == false) {
				artefacts.add((Artefact) item);
				System.out.print("Vous avez récupéré " + item.getNom());
			} else {
				System.out.println("Votre inventaire d'artefact est complet.");
			}
		} else {
			if (slotRempli(item) == false) {
				potions.add((Potion) item);
				System.out.print("Vous avez récupéré " + item.getNom());
			} else {
				System.out.println("Votre inventaire de potion est complet.");
			}
		}
	}
	
	public void removeItem(Item item) {
		if (item.getClass() == Weapon.class) {
			armes.remove(item);
			if (armeEquiper == item) armeEquiper = null;
		}
		else if (item.getClass() == Artefact.class) {
			artefacts.remove(item);
			if (artefactEquiper == item) artefactEquiper = null;
		}
		else if (item.getClass() == Potion.class) potions.remove(item);
	}
	
	public boolean slotRempli(Item item) {
		if (item.getClass() == Weapon.class) {
			if (armes.size() >= 2) {
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
