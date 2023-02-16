package Character;

import Item.Item;

public class Hero extends Character{
	private Inventory inventaire;
	private int porte;
	private int exp;
	
	public void prendreArme(Item item) {
		inventaire.addItem(item);
	}
	
	public Inventory getInventaire() {return inventaire;}
	
	public void seDeplacer(char direction) {
		switch (direction) {
			case 'z':
				if (getMap().caseAtteignable(pos.getX(), pos.getY()-1)) pos.addY(-1);
				break;
			case 'd':
				if (getMap().caseAtteignable(pos.getX()+1, pos.getY())) pos.addX(1);
				break;
			case 's':
				if (getMap().caseAtteignable(pos.getX(), pos.getY()+1)) pos.addY(1);
				break;
			case 'q':
				if (getMap().caseAtteignable(pos.getX()-1, pos.getY()+1)) pos.addX(-1);
				break;
		}
	}
}
