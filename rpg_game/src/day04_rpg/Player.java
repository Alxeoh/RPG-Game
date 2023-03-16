package day04_rpg;

import java.util.ArrayList;

public class Player {
	static String myGuild;
	static String log;
	static String id;
	static String pw;
	static int money;
	static Unit user;
	static Guild guild = new Guild();
	static AllGuild AllGuild = new AllGuild();
	static Inventory inven = new Inventory();

	Player() {
		Player.myGuild = "-1";
		Player.money = 1500;
		Player.guild.setGuild();
		Player.log = "-1";
	}

	public void guildMenu() {
		Player.guild.guildMenu();
	}

	public void inventoryMenu() {
		Player.inven.inventoryMenu();
	}

	static public ArrayList<Unit> getGuildList() {
		return Player.guild.guildUnitList;
	}

	static public ArrayList<Item> getItemList() {
		return Player.inven.itemList;
	}

	static public Unit getGuildUnit(int num) {
		return Player.guild.getGuildUnit(num);
	}

	static public int getGuildSize() {
		return Player.guild.guildUnitList.size();
	}

	static public int getItemSize() {
		return Player.inven.itemList.size();
	}
	
}