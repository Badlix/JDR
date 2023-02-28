package jdr;

import java.util.Scanner;

import Character.Character;
import Character.Hero;
import Character.Monster;

public class Combat {
	private Hero player;
	private Monster monster;
	private boolean isPlayerTurn;
	
	public Combat(Scanner scan, Hero player, Monster monster) {
		this.player = player;
		this.monster = monster;
		definirPremierQuiJoue();
		while (!player.isDead() && !monster.isDead()) {
//			System.out.print("\033\143");
//			System.out.print("\033[H\033[2J");
//			System.out.flush();
			show();
			if (isPlayerTurn) {
				monster.takeDamage(player.attaque(scan, monster));
			} else {
				player.takeDamage(monster.attaque(scan, monster));
			}
			changeTurn();
		}
		if (monster.isDead()) {
			drawDelimitater();
			System.out.println("Vous avez vaincu " + monster.getName());
			player.gainExp(monster.getLevel()*10);
		}
	}
	
	private void changeTurn() {
		isPlayerTurn = !isPlayerTurn;
	}
	
	private void definirPremierQuiJoue() {
		if (player.getStat("velocite") >= monster.getStat("velocite")) isPlayerTurn = true;
		else isPlayerTurn = false;
	}
	
	private void show() {
		drawDelimitater();
		System.out.println(player.getName() + "               " + monster.getName());
		System.out.println("PV : " + player.getStat("pv") + "               " + monster.getStat("pv"));
		drawDelimitater();
	}
	
	public void drawDelimitater() {;
	for (int i = 0; i < 50; i++) {
		System.out.print("-");
	}
	System.out.print("\n");
}
	
}
