package Character;

import Item.Item;
import Item.Potion;
import Item.Weapon;
import Item.Artefact;

public class Hero extends Character{
	private Inventory inventaire;
	
	public Hero(String nom, Position pos, Inventory inventaire, int attaque, int pointDeVieMax, int defense, int velocite, int chanceCritique, int chanceEsquive) {
		super(nom, pos, attaque, pointDeVieMax, defense, velocite, chanceCritique, chanceEsquive);
		super.stat.put("mana", 40);
		super.stat.put("manamax", 40);
		this.inventaire = inventaire;
	}
	
	public int getAttaque() {
		if (inventaire.getEquipWeapon() != null) {
			return super.attaque + inventaire.getEquipWeapon().getStat1();
		}
		return super.attaque;
	}
	
	public int getPV() {
		if (inventaire.getEquipWeapon() != null) {
			return super.pointDeVie + inventaire.getEquipArtefact().getStat1();
		}
		return super.pointDeVie;
	}
	
	public int getChanceCritique() {
		if (inventaire.getEquipWeapon() != null) {
			return super.chanceCritique + inventaire.getEquipWeapon().getStat2();
		}
		return super.chanceCritique;
	}
	
	public int getDef() {
		if (inventaire.getEquipArtefact() != null) {
			return super.defense + inventaire.getEquipArtefact().getStat2();
		}
		return super.defense;
	}
	
	public Weapon getWeapon() {
		return inventaire.getEquipWeapon();
	}
	
	public Artefact getArtefact() {
		return inventaire.getEquipArtefact();
	}
	
	public void equiperItem(Item item) {
		if (item == inventaire.getEquipWeapon() || item == inventaire.getEquipArtefact()) {
			System.out.print("Cet objet est déjà équipé.");
			return;
		}
		inventaire.equiper(item);
	}
	
	public void regarderInventaire() {
		inventaire.show();
	}
	
	public void prendreItem(Item item) {
		inventaire.addItem(item);
	}
	
	public void jeterItem(Item item) {
		inventaire.removeItem(item);
		System.out.print("Vous avez jeté " + item.getNom());
	}
	
	public Inventory getInventaire() {return inventaire;}
	
	public void seDeplacer(char direction) {
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
