package day04_rpg;

public class Hunt {

	public void huntMenu() {
		if (!Player.myGuild.equals("100")) {
			while (true) {
				System.out.println("================= [사냥터] =====================");
				System.out.println("[1. 토끼굴]");
				System.out.println("[2. 뱀굴]");
				System.out.println("[3. 호랑이굴]");
				System.out.println("[0. 뒤로가기]");
				System.out.println("===============================================");
				System.out.print("메뉴입력 : ");
				int sel = MainGame.scan.nextInt();
				if (sel == 1) {
					rabbitZone();
				} else if (sel == 2) {
					snakeZone();
				} else if (sel == 3) {
					tigerZone();
				} else if (sel == 0) {
					return;
				} else {
					System.out.println("존재하지 않는 사냥터 입니다.");
				}
			}
		} else {
			System.out.println("소속된 길드가 있어야 사냥이 가능합니다.");
		}
	}

	public void rabbitZone() {
		String monster = "토끼";
		int exp = 10;
		int hp = 30;
		int power = 3;
		int num = MainGame.ran.nextInt(5) + 1;
		int money = 250;
		int sel = zone(monster, num);
		if (sel == 1) {
			fight(monster, exp, hp, power, num, money);
		} else if (sel == 2) {
			run(monster,exp, hp, power, num, money);
		}
	}

	public void snakeZone() {
		String monster = "뱀";
		int exp = 15;
		int hp = 60;
		int power = 6;
		int num = MainGame.ran.nextInt(5) + 1;
		int money = 500;
		int sel = zone(monster, num);
		if (sel == 1) {
			fight(monster, exp, hp, power, num, money);
		} else if (sel == 2) {
			run(monster, exp, hp, power, num, money);
		}
	}

	public void tigerZone() {
		String monster = "호랑이";
		int exp = 30;
		int hp = 90;
		int power = 9;
		int num = MainGame.ran.nextInt(5) + 1;
		int money = 1000;
		int sel = zone(monster, num);
		if (sel == 1) {
			fight(monster, exp, hp, power, num, money);
		} else if (sel == 2) {
			run(monster, exp, hp, power, num, money);
		}
	}

	public int zone(String monster, int num) {

		System.out.printf("야생의 %s %d마리가 나타났다..!!\n", monster, num);
		System.out.println("1. 싸운다.");
		System.out.println("2. 도망간다.");
		int sel = MainGame.scan.nextInt();
		return sel;
	}

	public void fight(String monster, int exp, int hp, int power, int num, int money) {
		if (!stopFight()) {
			int monsterMaxHp = hp * num;
			int expTotal = exp * num;
			int deathMemberPower = 0;
			int unitPower = Guild.allGuild.guildList.get(Guild.myGuild).member.partyPower() - deathMemberPower;
			int cnt = num;
			int totalMoney = money * num;
			int powerTotal = power * cnt;
			int monsterHp = monsterMaxHp;

			while (true) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				monsterHp -= unitPower;
				if(monsterHp <=0) {
					monsterHp = 0;
				}
				System.out.printf("%s : 꾸엑! (Hp : %s / %s)\n", monster, monsterHp, monsterMaxHp);
				int fightingMember = fightingMember();
				for (int i = 0; i < Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.size(); i++) {
					if (Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).party == true) {
						(Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).hp) -= (powerTotal
								/ fightingMember);
					}
				}
				System.out.printf("길드원 : 윽.. \n");
				int deadthIdx = deadCheck();
				if (deadthIdx != -1) {
					deathMemberPower = Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(deadthIdx).att;
					if(Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(deadthIdx).weapon != null) {
						deathMemberPower += Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(deadthIdx).weapon.power;
					}
				}
				if (monsterHp <= 0 && !stopFight()) {
					int expShare = expTotal/fightingMember;
					System.out.println("[사냥성공]");
					System.out.printf("[골드 %dG 획득]\n", money);
					System.out.println("=============== [파티원 현황] ====================");
					Guild.allGuild.guildList.get(Guild.myGuild).member.printMe(expShare);
					Guild.allGuild.guildList.get(Guild.myGuild).member.printAllMember(expShare);
					for (int i = 0; i < Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.size(); i++) {
						if (Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).party == true) {
							Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).setExp(expShare);
						}
					}
					Player.money += totalMoney;
					break;
				}
				if (stopFight()) {
					System.out.println("[사냥실패]\n모든 전투원이 사망하였습니다..");
					break;
				}
			}
		} else {
			System.out.println("파티에 전투가능 인원이 없습니다.");
		}
	}

	public int deadCheck() {
		int idx = -1;
		for (int i = 0; i < Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.size(); i++) {
			if (Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).party == true
					&& Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).hp <= 0) {
				Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).party = false;
				System.out.printf("파티원[%s] 사망\n",
						Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).name);
				idx = i;
			}
		}
		return idx;
	}

	public int fightingMember() {
		int member = 0;
		for (int i = 0; i < Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.size(); i++) {
			if (Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).party == true) {
				member++;
			}
		}
		return member;
	}

	public boolean stopFight() {
		boolean check = true;
		for (int i = 0; i < Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.size(); i++) {
			if (Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).party == true) {
				check = false;
			}
		}
		return check;
	}

	public void run(String monster, int exp, int hp, int power, int num, int money) {
		int rNum = MainGame.ran.nextInt(10) + 1;
		if (rNum == 1) {
			System.out.println("도망에 실패했습니다...! ");
			fight(monster, exp, hp, power, num, money);
		}
		System.out.println("도망쳤습니다...");
		return;
	}

}
