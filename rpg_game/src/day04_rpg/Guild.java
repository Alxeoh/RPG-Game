package day04_rpg;

import java.util.ArrayList;

public class Guild {
	final int PARTY_SIZE = 4;
	
	ArrayList<Unit> guildUnitList = new ArrayList<>();
	AllGuild allGuild = new AllGuild();
	Unit[] partyList;

	public void setGuild() {
		Unit temp = new Unit("호랑이", 1, 100, 10, 5, 0);
		guildUnitList.add(temp);
		temp = new Unit("강아지", 1, 80, 7, 3, 0);
		guildUnitList.add(temp);
		temp = new Unit("사슴", 1, 50, 3, 1, 0);
		guildUnitList.add(temp);
		temp = new Unit("두더지", 1, 70, 5, 2, 0);
		guildUnitList.add(temp);
		temp = new Unit("돼지", 1, 200, 4, 8, 0);
		guildUnitList.add(temp);
		temp = new Unit("사자", 1, 120, 11, 7, 0);
		guildUnitList.add(temp);
		for (int i = 0; i < 4; i++) {
			guildUnitList.get(i).party = true;
		}
		partyList = new Unit[PARTY_SIZE];
		int n = 0;
		for (int i = 0; i < guildUnitList.size(); i++) {
			if (guildUnitList.get(i).party == true) {
				partyList[n] = guildUnitList.get(i);
				n += 1;
			}
		}
	}

	public Unit getGuildUnit(int num) {
		return guildUnitList.get(num);
	}

	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[골드 : " + Player.money + "]");
		System.out.println("============= [길드원] =================");
		for (int i = 0; i < guildUnitList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + guildUnitList.get(i).name + "]");
			System.out.print(" [레벨 : " + guildUnitList.get(i).level + "]");
			System.out.print(" [체력 : " + guildUnitList.get(i).hp);
			System.out.println(" / " + guildUnitList.get(i).maxHp + "]");
			System.out.print("[공격력 : " + guildUnitList.get(i).att + "]");
			System.out.print(" [방어력 : " + guildUnitList.get(i).def + "]");
			System.out.println(" [파티중 : " + guildUnitList.get(i).party + "]");
			System.out.println("");
		}
		System.out.println("=================================");
	}
	public void createMyGuild(){
		if(!Player.myGuild.equals("-1")) {
			System.out.println("이미 길드가 있습니다.");
		} else {
			System.out.print("길드 이름 : ");
			String guildName = MainGame.scan.next();
			GuildInfo temp = new GuildInfo(guildName); 
			allGuild.setGuildes(temp);
		}
		System.out.println("");
	}
	
	
	public void joinGuild() {
		if(!Player.myGuild.equals("-1")) {
			System.out.println("이미 길드가 있습니다.");
		} else {
		System.out.println("============== [길드가입] ================");
		for(int i = 0; i< allGuild.guildList.size();i++) {
			System.out.printf(" [%d. %s  (%d / %d)]\n",i+1,allGuild.guildList.get(i).name, allGuild.guildList.get(i).user, allGuild.guildList.get(i).maxUser);
		}
		System.out.println("========================================");		
		System.out.print(" 가입할 길드 선택 : ");
		int sel = MainGame.scan.nextInt();
		if(sel>0 && sel<allGuild.guildList.size()) {
			allGuild.guildList.get(sel-1).setUser();
			Player.myGuild = allGuild.guildList.get(sel-1).name;
			System.out.printf("[%s] 가입완료!",allGuild.guildList.get(sel-1).name);
		}else {
			System.out.println("길드번호를 다시 확인해주세요.");
		}
		}
	}
	
	
	
	
	public void printUnitStaus(int num) {
		guildUnitList.get(num).printStatus();
	}

	public void printUnitItem(int num) {
		guildUnitList.get(num).printEquitedItem();
	}

	public void buyUnit() {
		if (Player.money < 5000)
			return;
		String[] n1 = { "박", "이", "김", "최", "유", "지", "오" };
		String[] n2 = { "명", "기", "종", "민", "재", "석", "광" };
		String[] n3 = { "수", "자", "민", "수", "석", "민", "철" };

		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0);

		System.out.println("=====================================");
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + 1 + "]");
		System.out.print(" [체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print("[공격력 : " + att + "]");
		System.out.println(" [방어력 : " + def + "]");
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		guildUnitList.add(temp);
		Player.money -= 5000;
	}

	public void removeUnit() {
		printAllUnitStaus();
		System.out.println("삭제할 번호를 입력하세요 ");
		int sel = MainGame.scan.nextInt();
		if (guildUnitList.get(sel - 1).party) {
			System.out.println("파티중인 멤버는 삭제할수 없습니다.");
		} else {
			System.out.println("=================================");
			System.out.print("[이름 : " + guildUnitList.get(sel - 1).name + "]");
			System.out.println("길드원을 삭제합니다.");
			System.out.println("=================================");
			guildUnitList.remove(sel - 1);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void printParty() {
		System.out.println("================ [파티원] ===============");
		for (int i = 0; i < PARTY_SIZE; i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + partyList[i].name + "]");
			System.out.print(" [레벨 : " + partyList[i].level + "]");
			System.out.print(" [체력 : " + partyList[i].hp);
			System.out.println(" / " + partyList[i].maxHp + "]");
			System.out.print("[공격력 : " + partyList[i].att + "]");
			System.out.print(" [방어력 : " + partyList[i].def + "]");
			System.out.println(" [파티중 : " + guildUnitList.get(i).party + "]");
			System.out.println("");
		}
		System.out.println("=====================================");
	}

	public void partyChange() {

		printParty();
		System.out.println("교체할 번호를 입력하세요 ");
		int partyNum = MainGame.scan.nextInt();
		printAllUnitStaus();
		System.out.println("참가할 번호를 입력하세요 ");
		int guildNum = MainGame.scan.nextInt();

		partyList[partyNum - 1].party = false;
		guildUnitList.get(guildNum - 1).party = true;

		System.out.println("====================================");
		System.out.print("[이름 : " + partyList[partyNum - 1].name + "]");
		System.out.print("에서 ");
		System.out.print("[이름 : " + guildUnitList.get(guildNum - 1).name + "]");
		System.out.println("으로 교체 합니다. ");
		System.out.println("====================================");

		int n = 0;
		for (int i = 0; i < guildUnitList.size(); i++) {
			if (guildUnitList.get(i).party) {
				partyList[n] = guildUnitList.get(i);
				n += 1;
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ===============");
			System.out.println("[1.길드생성]\n[2.길드가입]\n[3.길드원목록]\n[4.길드원추가]\n[5.길드원삭제]\n[6.파티원교체]\n[0.뒤로가기]");
			System.out.println("========================================");
			System.out.print("메뉴입력 : ");
			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
					createMyGuild();
			} else if (sel == 2) {
					joinGuild();
			} else if (sel == 3) {
				printAllUnitStaus();
			} else if (sel == 4) {
				buyUnit();
			} else if (sel == 5) {
				removeUnit();
			} else if (sel == 6) {
				partyChange();
			} 
			else if (sel == 0) {
				break;
			}
		}
	}

}