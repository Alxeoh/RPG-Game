package day04_rpg;

public class Guild {

	static AllGuild allGuild = new AllGuild();
	static int myGuild;

	public void printMenu() {
		System.out.println("================= [길드관리] ====================");
		System.out.println(
				"[1.길드생성]\n[2.길드가입]\n[3.길드목록]\n[4.길드원목록]\n[5.길드원추가]\n[6.길드원삭제]\n[7.파티원제외]\n[8.파티원교체]\n[9.길드탈퇴]\n[0.뒤로가기]");
		System.out.println("===============================================");
		System.out.print("메뉴입력 : ");
	}

	public void setParty() {
		if (allGuild.guildList.get(myGuild).member.memberUnit.size() > 0) {

			for (int i = 0; i < allGuild.guildList.get(myGuild).member.memberUnit.size(); i++) {
				if (allGuild.guildList.get(myGuild).member.memberUnit.size() < 5) {
					allGuild.guildList.get(myGuild).member.memberUnit.get(i).party = true;
				}
			}
		}
	}

	public int guildIdx(String str) {
		int idx = -1;
		for (int i = 0; i < allGuild.guildList.size(); i++) {
			if (str.equals(allGuild.guildList.get(i).name)) {
				idx = i;
			}
		}
		Guild.myGuild = idx;
		return idx;
	}

	public void printAllUnitStaus() {
		if (!Player.myGuild.equals("100")) {
			int idx = guildIdx(Player.myGuild);
			allGuild.guildList.get(idx).member.printMe();
			allGuild.guildList.get(idx).member.printAllMember();
		} else {
			System.out.println("가입한 길드가 없습니다.");
		}
	}

	public void createMyGuild() {
		if (!Player.myGuild.equals("100")) {
			System.out.println("이미 길드가 있습니다.");
		} else {
			System.out.print("길드 이름 : ");
			String guildName = MainGame.scan.next();
			boolean check = true;
			for (int i = 0; i < allGuild.guildList.size(); i++) {
				if (allGuild.guildList.get(i).name.equals(guildName)) {
					check = false;
				}
			}
			if (check) {
				GuildInfo temp = new GuildInfo(guildName, 30, 0);
				allGuild.setGuildes(temp);
				Player.myGuild = guildName;
				int idx = guildIdx(Player.myGuild);
				System.out.printf("['%s' 길드생성완료!]\n", guildName);
				allGuild.guildList.get(idx).member.memberUnit.add(Player.user);
				allGuild.guildList.get(idx).setUser();
				Guild.myGuild = idx;
				Player.mine = true;
			} else {
				System.out.println("중복된 이름의 길드가 있습니다.");
			}
		}
	}

	public void joinGuild() {
		if (!Player.myGuild.equals("100")) {
			System.out.println("이미 길드가 있습니다.");
		} else {
			System.out.println("================ [길드가입] =====================");
			for (int i = 0; i < allGuild.guildList.size(); i++) {
				System.out.printf(" [%d. %s 길드  (%d / %d)]\n", i + 1, allGuild.guildList.get(i).name,
						allGuild.guildList.get(i).user, allGuild.guildList.get(i).maxUser);
			}
			System.out.println("===============================================");
			System.out.print(" 가입할 길드 선택 : ");
			int sel = MainGame.scan.nextInt();
			if (sel > 0 && sel <= allGuild.guildList.size()) {
				allGuild.guildList.get(sel - 1).setUser();
				allGuild.guildList.get(sel - 1).member.memberUnit.add(Player.user);
				Player.myGuild = allGuild.guildList.get(sel - 1).name;
				System.out.printf("[%s] 가입완료!\n", allGuild.guildList.get(sel - 1).name);
				Guild.myGuild = sel - 1;
			} else {
				System.out.println("길드번호를 다시 확인해주세요.");
			}
		}
	}

	public void printGuildList() {
		for (int i = 0; i < allGuild.guildList.size(); i++) {
			System.out.printf(" [%d. %s 길드  (%d / %d)]\n", i + 1, allGuild.guildList.get(i).name,
					allGuild.guildList.get(i).user, allGuild.guildList.get(i).maxUser);
		}
	}

	public void buyUnit() {
		if (Player.myGuild.equals("100")) {
			System.out.println("길드원추가를 위해선 길드가 필요합니다. ");
		} else {
			if (Player.mine) {
				if (Player.money < 5000) {
					System.out.println("골드가 부족합니다. (길드원 추가는 5000G)");
					return;
				}
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

				System.out.println("===============================================");
				System.out.print("[이름 : " + name + "]");
				System.out.print(" [레벨 : " + 1 + "]");
				System.out.print(" [체력 : " + hp);
				System.out.println(" / " + hp + "]");
				System.out.print("[공격력 : " + att + "]");
				System.out.println(" [방어력 : " + def + "]");
				System.out.println("길드원을 추가합니다.");
				System.out.println("===============================================");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				allGuild.guildList.get(myGuild).member.memberUnit.add(temp);
				allGuild.guildList.get(myGuild).setUser();
				Player.money -= 5000;
				if (allGuild.guildList.get(myGuild).member.memberUnit.size() <= 4) {
					setParty();
				}
			} else {
				System.out.println("길드장 권한입니다.");
			}
		}
	}

	public void removeUnit() {
		if (Player.myGuild.equals("100")) {
			System.out.println("길드원삭제를 위해선 길드가 필요합니다. ");
		} else {
			if (Player.mine) {
				printAllUnitStaus();
				System.out.println("삭제할 번호를 입력하세요 ");
				int sel = MainGame.scan.nextInt();
				if (allGuild.guildList.get(myGuild).member.memberUnit.get(sel).party) {
					System.out.println("파티중인 멤버는 삭제할수 없습니다.");
				} else {
					System.out.println("==============================================");
					System.out.print("[이름 : " + allGuild.guildList.get(myGuild).member.memberUnit.get(sel).name + "]");
					System.out.println("길드원을 삭제합니다.");
					System.out.println("==============================================");
					allGuild.guildList.get(myGuild).member.memberUnit.remove(sel);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("길드장 권한입니다.");
			}
		}
	}

	public void printParty() {
		System.out.println("================== [파티원] ====================");
		allGuild.guildList.get(Guild.myGuild).member.printAllMember();
		System.out.println("==============================================");
	}

	public void deletePartyMember() {
		if (Player.myGuild.equals("100")) {
			System.out.println("파티원제외를 하기 위해선 길드가 필요합니다. ");
		} else {
			if (Player.mine) {
				int countPartyMember = CountPartyMember();
				if (countPartyMember < 2) {
					System.out.printf("최소파티원은 한명입니다. (현재파티원 : %d)\n", countPartyMember);
				} else {
					printParty();
					System.out.println("제외할 번호를 입력하세요 ");
					int size = allGuild.guildList.get(Guild.myGuild).member.memberUnit.size();
					int partyNum = MainGame.scan.nextInt();
					if (partyNum > 0 && partyNum <= size) {
						if (allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(partyNum).party == true) {
							allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(partyNum).party = false;
							System.out.printf("[%s] 파티원제외 완료\n",
									allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(partyNum).name);
							System.out.printf("(현재 파티원 수 : %d명)\n", countPartyMember - 1);
						} else {
							System.out.println("파티중인 길드원이 아닙니다.");
						}
					} else {
						System.out.println("선택하신 번호는 없는 번호입니다. ");
					}
				}
			} else {
				System.out.println("길드장 권한입니다.");
			}
		}
	}

	public int CountPartyMember() {
		int size = allGuild.guildList.get(Guild.myGuild).member.memberUnit.size();
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(i).party == true) {
				count++;
			}
		}
		return count;
	}

	public void partyChange() {
		if (Player.myGuild.equals("100")) {
			System.out.println("파티원교체를 위해선 길드가 필요합니다. ");
		} else {
			if (Player.mine) {
				printParty();
				System.out.println("교체할 번호를 입력하세요 ");
				int size = allGuild.guildList.get(Guild.myGuild).member.memberUnit.size();
				int partyNum = MainGame.scan.nextInt();
				if (partyNum > 0 && partyNum <= size) {
					if (allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(partyNum).party == true) {
						printAllUnitStaus();
						System.out.println("참가할 번호를 입력하세요 ");
						int guildNum = MainGame.scan.nextInt();
						if (guildNum > 0 && guildNum <= size) {
							if (allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(guildNum).party == false) {
								allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(partyNum).party = false;
								allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(guildNum).party = true;

								System.out.println("==============================================");
								System.out.print("[이름 : "
										+ allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(partyNum).name
										+ "]");
								System.out.print("에서 ");
								System.out.print("[이름 : "
										+ allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(guildNum).name
										+ "]");
								System.out.println("으로 교체 합니다. ");
								System.out.println("==============================================");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							} else {
								System.out.println("이미 파티중인 길드원 입니다.");
							}
						}
					} else {
						System.out.println("파티중인 길드원이 아닙니다.");
					}
				} else {
					System.out.println("선택하신 번호는 없는 번호입니다. ");
				}
			} else {
				System.out.println("길드장 권한입니다.");
			}
		}
	}

	public void exitGuild() {
		if (!Player.myGuild.equals("100")) {
			if (Player.mine) {
				System.out.println("================ [길드탈퇴] =====================");
				if (allGuild.guildList.get(Guild.myGuild).member.memberUnit.size() > 1) {
					allGuild.guildList.get(Guild.myGuild).member.printAllMember();
					System.out.println("==============================================");
					System.out.print("길드장을 위임할 길드원을 선택하세요 : ");
					int sel = MainGame.scan.nextInt()-1;
					if (sel >= 0 && sel < allGuild.guildList.get(Guild.myGuild).member.memberUnit.size()) {
						allGuild.guildList.get(Guild.myGuild).member.memberUnit.remove(0);
						Unit temp = allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(sel);
						allGuild.guildList.get(Guild.myGuild).member.memberUnit.remove(sel);
						if(allGuild.guildList.get(Guild.myGuild).member.memberUnit.size()>1) {
							allGuild.guildList.get(Guild.myGuild).member.memberUnit.set(0, temp);
						} else {
							allGuild.guildList.get(Guild.myGuild).member.memberUnit.add(temp);
						}
						allGuild.guildList.get(Guild.myGuild).outUser();
						Player.mine = false;
						System.out.printf("길드장을 %s에게 위임하셨습니다.\n",
								allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(0).name);
					} else {
						System.out.println("유효하지 않은 길드원번호입니다.");
					}
				}else {
					allGuild.guildList.remove(Guild.myGuild);
				}
			}else {
				allGuild.guildList.get(Guild.myGuild).outUser();
			}
			System.out.println("[길드탈퇴 완료]");
			System.out.println(allGuild.guildList.get(Guild.myGuild).user);
			Player.myGuild = "100";
		} else {
			System.out.println("소속된 길드가 없습니다.");
		}
	}

	public void guildMenu() {

		while (true) {
			printMenu();
			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
				createMyGuild();
			} else if (sel == 2) {
				joinGuild();
			} else if (sel == 3) {
				printGuildList();
			} else if (sel == 4) {
				printAllUnitStaus();
			} else if (sel == 5) {
				buyUnit();
			} else if (sel == 6) {
				removeUnit();
			} else if (sel == 7) {
				deletePartyMember();
			} else if (sel == 8) {
				partyChange();
			} else if (sel == 9) {
				exitGuild();
			} else if (sel == 0) {
				break;
			} else {
				System.out.println("없는 메뉴입니다.");
			}
		}
	}

}