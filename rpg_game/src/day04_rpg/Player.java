package day04_rpg;

import java.util.ArrayList;

public class Player {
	static String myGuild;
	static boolean mine;
	static String log;
	static String id;
	static String pw;
	static int money;
	static Unit user;
	static Guild guild = new Guild();
	static Inventory inven = new Inventory();

	Player() {
		Player.mine = false;
		Player.myGuild = "100";
		Player.money = 25000;
		Player.guild.setParty();
		Player.log = "100";
	}

	public void guildMenu() {
		if(!Player.log.equals("100")) {
			Player.guild.guildMenu();
		} else {
			System.out.println("캐릭터생성을 먼저 해주세요.");
		}
	}

	public void inventoryMenu() {
		if(!Player.log.equals("100")) {
			Player.inven.inventoryMenu();
		} else {
			System.out.println("캐릭터생성을 먼저 해주세요.");
		}
	}
	
	static public ArrayList<Item> getPotionList() {
		return Player.inven.PotionList;
	}
	
	static public ArrayList<Item> getItemList() {
		return Player.inven.MyitemList;
	}

	static public int getItemSize() {
		return Player.inven.MyitemList.size();
	}
	
	static public int getPotionSize() {
		return Player.inven.PotionList.size();
	}
	
}