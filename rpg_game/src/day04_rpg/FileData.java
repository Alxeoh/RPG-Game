package day04_rpg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileData {
	void save() throws IOException {
		FileWriter fout = null;
		String path = "gameData.txt";

		fout = new FileWriter(path);
		if (Player.myGuild.equals("-1")) {
			String gameData = "";
		} else {

			String gameData = "";

			gameData += Guild.allGuild.guildList.size()+"";
			gameData += "\r\n";
			gameData += Player.mine;
			gameData += "\r\n";
			gameData += Player.money;
			gameData += "\r\n";
			gameData += Player.myGuild;
			gameData += "\r\n";
			gameData += Player.log;
			gameData += "\r\n";
			gameData += Player.id;
			gameData += "\r\n";
			gameData += Player.pw;
			gameData += "\r\n";
			gameData += Guild.myGuild;
			gameData += "\r\n";
			for (int j = 0; j < Guild.allGuild.guildList.size(); j++) {
				ArrayList<Unit> temp = Guild.allGuild.guildList.get(j).member.memberUnit;
				gameData += temp.size();
				gameData += "\r\n";
				gameData += Guild.allGuild.guildList.get(j).name;
				gameData += "\r\n";
				gameData += Guild.allGuild.guildList.get(j).maxUser;
				gameData += "\r\n";
				gameData += Guild.allGuild.guildList.get(j).user;
				gameData += "\r\n";
				for (int i = 0; i < temp.size(); i++) {
					gameData += temp.get(i).name;
					gameData += "/";
					gameData += temp.get(i).level;
					gameData += "/";
					gameData += temp.get(i).hp;
					gameData += "/";
					gameData += temp.get(i).maxHp;
					gameData += "/";
					gameData += temp.get(i).att;
					gameData += "/";
					gameData += temp.get(i).def;
					gameData += "/";
					gameData += temp.get(i).exp;
					gameData += "/";
					gameData += temp.get(i).party;
					gameData += "/";
					gameData += temp.get(i).level2;
					gameData += "/";
					gameData += temp.get(i).level3;
					gameData += "/";
					gameData += temp.get(i).level4;
					gameData += "/";
					gameData += temp.get(i).level5;
					gameData += "/";
					gameData += temp.get(i).level6;
					gameData += "/";
					gameData += temp.get(i).level7;
					gameData += "/";
					gameData += temp.get(i).level8;
					gameData += "/";
					gameData += temp.get(i).level9;
					gameData += "/";
					gameData += temp.get(i).level10;
					gameData += "\r\n";
					if (temp.get(i).weapon == null) {
						gameData += temp.get(i).weapon;
					} else {
						Item item = temp.get(i).weapon;
						String weaponData = "";
						weaponData += item.kind;
						weaponData += ",";
						weaponData += item.name;
						weaponData += ",";
						weaponData += item.power;
						weaponData += ",";
						weaponData += item.price;
						gameData += weaponData;

					}
					gameData += "/";
					if (temp.get(i).armor == null) {
						gameData += temp.get(i).armor;
					} else {
						Item item = temp.get(i).armor;
						String weaponData = "";
						weaponData += item.kind;
						weaponData += ",";
						weaponData += item.name;
						weaponData += ",";
						weaponData += item.power;
						weaponData += ",";
						weaponData += item.price;
						gameData += weaponData;

					}
					gameData += "/";
					if (temp.get(i).ring == null) {
						gameData += temp.get(i).ring;
					} else {
						Item item = temp.get(i).ring;
						String weaponData = "";
						weaponData += item.kind;
						weaponData += ",";
						weaponData += item.name;
						weaponData += ",";
						weaponData += item.power;
						weaponData += ",";
						weaponData += item.price;
						gameData += weaponData;
					}
					gameData += "\r\n";
				}
			}

			gameData += Player.getItemSize();
			gameData += "\r\n";
			for (int i = 0; i < Player.getItemSize(); i++) {
				Item item = Player.getItemList().get(i);

				gameData += item.kind;
				gameData += "/";
				gameData += item.name;
				gameData += "/";
				gameData += item.power;
				gameData += "/";
				gameData += item.price;
				gameData += "/";
				gameData += item.level;
				gameData += "\r\n";
			}
			gameData += Player.getPotionSize();
			gameData += "\r\n";
			for (int i = 0; i < Player.getPotionSize(); i++) {
				Item item = Player.getPotionList().get(i);
				gameData += item.kind;
				gameData += "/";
				gameData += item.name;
				gameData += "/";
				gameData += item.power;
				gameData += "/";
				gameData += item.price;
				gameData += "/";
				gameData += item.level;
				gameData += "\r\n";
			}
			System.out.println("[저장성공]");
			fout.write(gameData, 0, gameData.length());
			fout.close();

		}

	}

	void loadData() throws IOException {
		File file = null;
		FileReader reader = null;
		BufferedReader br = null;
		String path = "gameData.txt";
		file = new File(path);
		if (file.exists()) {
			reader = new FileReader(path);
			br = new BufferedReader(reader);
			
			int allGuildSize = Integer.parseInt(br.readLine());
			boolean mine = Boolean.parseBoolean(br.readLine());
			Player.mine = mine;
			String money = br.readLine();
			Player.money = Integer.parseInt(money);
			String myGuild = br.readLine();
			Player.myGuild = myGuild;
			String log = br.readLine();
			Player.log = log;
			String id = br.readLine();
			Player.id = id;
			String pw = br.readLine();
			Player.pw = pw;
			int GuildNum = Integer.parseInt(br.readLine());
			Guild.myGuild = GuildNum;
			Guild.allGuild.guildList.clear();
			for(int j = 0; j<allGuildSize;j++) {
				String guildSize = br.readLine();
				int size = Integer.parseInt(guildSize);
				String guildName = br.readLine();
				int maxUser = Integer.parseInt(br.readLine());
				int user = Integer.parseInt(br.readLine());
				GuildInfo temp = new GuildInfo(guildName, maxUser, user);
				Guild.allGuild.guildList.add(temp);
				Guild.allGuild.guildList.get(j).member.memberUnit.clear();
				for (int i = 0; i < size; i++) {
					String unitData = br.readLine();
					String[] unitArr = unitData.split("/");
					String name = unitArr[0];
					int level = Integer.parseInt(unitArr[1]);
					int hp = Integer.parseInt(unitArr[2]);
					int maxhp = Integer.parseInt(unitArr[3]);
					int att = Integer.parseInt(unitArr[4]);
					int def = Integer.parseInt(unitArr[5]);
					int exp = Integer.parseInt(unitArr[6]);
					boolean party = Boolean.parseBoolean(unitArr[7]);
					boolean level2 = Boolean.parseBoolean(unitArr[8]);
					boolean level3 = Boolean.parseBoolean(unitArr[9]);
					boolean level4 = Boolean.parseBoolean(unitArr[10]);
					boolean level5 = Boolean.parseBoolean(unitArr[11]);
					boolean level6 = Boolean.parseBoolean(unitArr[12]);
					boolean level7 = Boolean.parseBoolean(unitArr[13]);
					boolean level8 = Boolean.parseBoolean(unitArr[14]);
					boolean level9 = Boolean.parseBoolean(unitArr[15]);
					boolean level10 = Boolean.parseBoolean(unitArr[16]);
					Unit temp1 = new Unit(name, level, hp, maxhp, att, def, exp, party, level2, level3, level4, level5,
							level6, level7, level8, level9, level10);
					Guild.allGuild.guildList.get(j).member.memberUnit.add(temp1);
					// ==================== item =======================
					String itemData = br.readLine();
					String itemArr[] = itemData.split("/");
					if (itemArr[0].equals("null")) {
						Guild.allGuild.guildList.get(j).member.memberUnit.get(i).weapon = null;
					} else {
						String[] weapon = itemArr[0].split(",");
						int itemKind = Integer.parseInt(weapon[0]);
						String itemName = weapon[1];
						int itemPower = Integer.parseInt(weapon[2]);
						int itemPrice = Integer.parseInt(weapon[3]);
						int itemLevel = Integer.parseInt(weapon[4]);
						Item item = new Item();
						item.setItem(itemKind, itemName, itemPower, itemPrice, itemLevel);
						Guild.allGuild.guildList.get(j).member.memberUnit.get(i).weapon = item;
					}
					if (itemArr[1].equals("null")) {
						Guild.allGuild.guildList.get(j).member.memberUnit.get(i).armor = null;
					} else {
						String[] armor = itemArr[1].split(",");
						int itemKind = Integer.parseInt(armor[0]);
						String itemName = armor[1];
						int itemPower = Integer.parseInt(armor[2]);
						int itemPrice = Integer.parseInt(armor[3]);
						int itemLevel = Integer.parseInt(armor[4]);
						Item item = new Item();
						item.setItem(itemKind, itemName, itemPower, itemPrice, itemLevel);
						Guild.allGuild.guildList.get(j).member.memberUnit.get(i).armor = item;
					}
					if (itemArr[2].equals("null")) {
						Guild.allGuild.guildList.get(j).member.memberUnit.get(i).ring = null;
					} else {
						String[] ring = itemArr[2].split(",");
						int itemKind = Integer.parseInt(ring[0]);
						String itemName = ring[1];
						int itemPower = Integer.parseInt(ring[2]);
						int itemPrice = Integer.parseInt(ring[3]);
						int itemLevel = Integer.parseInt(ring[4]);
						Item item = new Item();
						item.setItem(itemKind, itemName, itemPower, itemPrice, itemLevel);
						Guild.allGuild.guildList.get(j).member.memberUnit.get(i).ring = item;
					}
					
				}
			}
			// ===================== item ============================
			String invenSize = br.readLine();
			int inSize = Integer.parseInt(invenSize);

			Player.inven.MyitemList.clear();
			for (int i = 0; i < inSize; i++) {
				String invenDate = br.readLine();
				String[] invenArr = invenDate.split("/");
				int itemKind = Integer.parseInt(invenArr[0]);
				String itemName = invenArr[1];
				int itemPower = Integer.parseInt(invenArr[2]);
				int itemPrice = Integer.parseInt(invenArr[3]);
				int itemLevel = Integer.parseInt(invenArr[4]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice, itemLevel);
				Player.inven.MyitemList.add(item);
			}
			String potionSize = br.readLine();
			int poSize = Integer.parseInt(potionSize);
			Player.inven.PotionList.clear();
			for (int i = 0; i < poSize; i++) {
				String invenDate = br.readLine();
				String[] invenArr = invenDate.split("/");
				int itemKind = Integer.parseInt(invenArr[0]);
				String itemName = invenArr[1];
				int itemPower = Integer.parseInt(invenArr[2]);
				int itemPrice = Integer.parseInt(invenArr[3]);
				int itemLevel = Integer.parseInt(invenArr[4]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice, itemLevel);
				Player.inven.PotionList.add(item);
			}

		}
		System.out.println("[로드성공]");
	}

}