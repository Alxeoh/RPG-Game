package day04_rpg;

import java.util.ArrayList;

public class Inventory {
	ArrayList<Item> MyitemList = new ArrayList<>();
	ArrayList<Item> PotionList = new ArrayList<>();
	Shop shop = new Shop();

	public void inventoryMenu() {
		while (true) {
			System.out.println("================= [인벤메뉴] =====================");
			System.out.println("[1.착용]\n[2.판매]\n[3.포션사용]\n[0.뒤로가기]");
			System.out.println("===============================================");
			System.out.println("메뉴입력 : ");
			int sel = MainGame.scan.nextInt();
			if (sel == 0)
				break;
			if (sel == 1) {
				equipMenu();
			} else if (sel == 2) {
				sellMenu();
			} else if (sel == 3) {
				potion();
			} else {
				System.out.println("없는 메뉴입니다.");
			}
		}
	}

	public void potion() {
		System.out.println("================= [포션사용] ====================");
		if (PotionList.size() > 0) {
			if (Player.myGuild != "100") {
				Guild.allGuild.guildList.get(Guild.myGuild).member.printAllHp();
				System.out.println();
				System.out.print("아이템 착용할 길드원을 선택하세요 : ");
				int selUnit = MainGame.scan.nextInt() - 1;
				int size = Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.size();
				if (selUnit >= 0 && selUnit < size) {
					printPotionList();
					System.out.println("사용할 포션 번호를 입력하세요 [0.뒤로가기]");
					int usePotion = MainGame.scan.nextInt();
					usePotion(usePotion, selUnit);
				} else {
					System.out.println("선택한 길드원 번호를 다시 확인하세요.");
				}
			} else {
				Guild.allGuild.guildList.get(Guild.myGuild).member.printMyHp();
				printPotionList();
				System.out.println("사용할 포션 번호를 입력하세요 [0.뒤로가기]");
				int usePotion = MainGame.scan.nextInt();
				usePotion(usePotion, 0);
			}
		} else {
			System.out.println("아이템이 없습니다.");
		}
	}

	public void usePotion(int usePotion, int selUnit) {
		int check = potionCheck(usePotion);
		if (check <= 0) {
			System.out.println("포션이 없습니다.");
		} else {
			int heal = 0;
			if (usePotion == 1) {
				heal += 20;
			} else if (usePotion == 2) {
				heal += 40;
			} else if (usePotion == 3) {
				heal += 100;
			} else if (usePotion == 0) {
				return;
			}
			if (usePotion > PotionList.size() && usePotion < 0) {
				System.out.println("없는 아이템번호 입니다. ");
			} else {
				if (selUnit == -1) {
					if (Player.user.hp == 0 && usePotion != 1) {
						System.out.println("사망한 인원은 '빨간포션'으로 먼저 살려야 합니다.");
					} else {
						Player.user.hp += heal;
						int itemPower = 0;
						if(Player.user.ring != null) {
							itemPower = Player.user.ring.power; 
						}
						if (Player.user.hp >= Player.user.maxHp + itemPower) {
							Player.user.hp = Player.user.maxHp + itemPower;
						}
						System.out.println("포션사용 완료");
						Player.user.party = true;
						deletePotion(usePotion);
					}
				} else {
					if (Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(selUnit).hp == 0
							&& usePotion != 1) {
						System.out.println("사망한 인원은 '빨간포션'으로 먼저 살려야 합니다.");
					} else {
						Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(selUnit).hp += heal;
						int itemPower = 0;
						if(Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(selUnit).ring != null) {
							itemPower = Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(selUnit).ring.power;
						}
						if (Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit
								.get(selUnit).hp >= Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit
										.get(selUnit).maxHp + itemPower) {
							Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit
									.get(selUnit).hp = Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit
											.get(selUnit).maxHp + itemPower;
						}
						System.out.println("포션사용 완료");
						Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(selUnit).party = true;
						deletePotion(usePotion);
					}
				}
			}
		}
	}

	public int potionCheck(int potionNum) {
		int cnt = 0;
		String name = "";
		if (potionNum == 1) {
			name = "빨간포션";
		} else if (potionNum == 2) {
			name = "버섯볶음";
		} else if (potionNum == 3) {
			name = "장어구이";
		}
		for (int i = 0; i < PotionList.size(); i++) {
			if (PotionList.get(i).name.equals(name)) {
				cnt++;
			}
		}
		return cnt;
	}

	public void equip(int selEquip, Unit unit) {
		selEquip -= 1;
		if (MyitemList.get(selEquip).kind == Item.WEAPON) {
			if (unit.weapon != null) {
				MyitemList.add(unit.weapon);
			}
			unit.weapon = MyitemList.get(selEquip);
		} else if (MyitemList.get(selEquip).kind == Item.ARMOR) {
			if (unit.armor != null) {
				MyitemList.add(unit.armor);
			}
			unit.armor = MyitemList.get(selEquip);
		} else if (MyitemList.get(selEquip).kind == Item.RING) {
			if (unit.ring != null) {
				MyitemList.add(unit.ring);
			}
			unit.ring = MyitemList.get(selEquip);
			unit.hp = unit.maxHp + MyitemList.get(selEquip).power;
		}
		MyitemList.remove(selEquip);
	}

	public void equipMentUnit(int kindNum, Unit unit) {
		if (kindNum == 1) {
			if (unit.weapon != null) {
				String equipment = unit.weapon.name;
				int idx = -1;
				for (int i = 0; i < shop.itemList.size(); i++) {
					if (equipment.equals(shop.itemList.get(i).name)) {
						idx = i;
					}
				}
				MyitemList.add(shop.itemList.get(idx));
				unit.weapon = null;
			}
		} else if (kindNum == 2) {
			if (unit.armor != null) {
				String equipment = unit.armor.name;
				int idx = -1;
				for (int i = 0; i < shop.itemList.size(); i++) {
					if (equipment.equals(shop.itemList.get(i).name)) {
						idx = i;
					}
				}
				MyitemList.add(shop.itemList.get(idx));
				unit.armor = null;
			}
		} else {
			if (unit.ring != null) {
				String equipment = unit.ring.name;
				int idx = -1;
				for (int i = 0; i < shop.itemList.size(); i++) {
					if (equipment.equals(shop.itemList.get(i).name)) {
						idx = i;
					}
				}
				MyitemList.add(shop.itemList.get(idx));
				unit.ring = null;
			}
		}
	}

	public void equipMenu() {
		System.out.println("================= [아이템착용] ===================");
		System.out.println("[1.플레이어] [2.길드원] [0.뒤로가기]");
		System.out.println("===============================================");
		System.out.println("메뉴입력 : ");
		int sel = MainGame.scan.nextInt();
		if (sel == 0) {
			return;
		}
		if (sel == 1) {
			if (MyitemList.size() > 0) {
				System.out.println("=============== [플레이어 장비현황] =================");
				Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(0).printEquitedItem();
				printItemList();
				System.out.println("착용할 아이템 번호를 입력하세요 [0.뒤로가기]");
				int selEquip = MainGame.scan.nextInt();
				if (selEquip == 0) {
					return;
				} else if (selEquip > MyitemList.size()) {
					System.out.println("없는 아이템번호 입니다. ");
				} else {
					String temp = MyitemList.get(selEquip - 1).name;
					if (Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(0).level >= MyitemList.get(selEquip - 1).level) {
						if (!Player.myGuild.equals("-1")) {
							Unit player = Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(0);
							int kindNum = MyitemList.get(selEquip - 1).kind;
							equipMentUnit(kindNum, player);
							equip(selEquip, player);
						} else {
							equip(selEquip, Player.user);
						}
						System.out.printf("'%s' 착용완료!\n", temp);
					} else {
						System.out.println("[착용불가]\n레벨이 낮아서 착용할 수 없습니다.");
					}
				}
			} else {
				System.out.println("아이템이 없습니다.");
			}

		} else if (sel == 2) {
			if (Player.myGuild.equals("100")) {
				System.out.println("가입한 길드가 없습니다.");
				return;
			} else {
				if (MyitemList.size() > 0) {
					Guild.allGuild.guildList.get(Guild.myGuild).member.printAllMember();
					System.out.println("아이템 착용할 길드원을 선택하세요 ");
					int selUnit = MainGame.scan.nextInt();
					int size = Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.size();
					if (selUnit > 0 && selUnit < size) {
						Unit unit = Guild.allGuild.guildList.get(Guild.myGuild).member.memberUnit.get(selUnit);
						unit.printEquitedItem();
						printItemList();
						System.out.println("착용할 아이템 번호를 입력하세요 [0.뒤로가기]");
						int selEquip = MainGame.scan.nextInt();
						if (selEquip == 0) {
							return;
						} else if (selEquip > MyitemList.size()) {
							System.out.println("없는 아이템번호 입니다. ");
						} else {
							String temp = MyitemList.get(selEquip - 1).name;
							if (unit.level >= MyitemList.get(selEquip - 1).level) {
								equipMentUnit(selEquip, unit);
								equip(selEquip, unit);
								System.out.printf("'%s' 착용완료!\n", temp);
							} else {
								System.out.println("[착용불가]\n레벨이 낮아서 착용할 수 없습니다.");
							}
						}
					} else {
						System.out.println("선택한 길드원 번호를 다시 확인하세요.");
					}

				} else {
					System.out.println("아이템이 없습니다.");
				}
			}

		}
	}

	public void printPotionList() {
		System.out.println("=============== [포션 리스트] ====================");
		int a = 0;
		int b = 0;
		int c = 0;
		for (int i = 0; i < PotionList.size(); i++) {
			if (PotionList.get(i).name.equals("빨간포션")) {
				a++;
			} else if (PotionList.get(i).name.equals("버섯볶음")) {
				b++;
			} else if (PotionList.get(i).name.equals("장어구이")) {
				c++;
			}
		}
		System.out.printf("[1.][이름 : 빨간포션] %d개\n", a);
		System.out.printf("[2.][이름 : 버섯볶음] %d개\n", b);
		System.out.printf("[3.][이름 : 장어구이] %d개\n", c);
	}

	public void printItemList() {
		System.out.println("=============== [아이템리스트] ====================");
		for (int i = 0; i < MyitemList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print("[이름 : " + MyitemList.get(i).name + "]");
			System.out.print("[파워 : " + MyitemList.get(i).power + "]");
			System.out.print("[가격 : " + MyitemList.get(i).price + "]");
			System.out.println("");
		}
	}

	public void deletePotion(int potionNum) {
		String name = "";
		if (potionNum == 1) {
			name = "빨간포션";
		} else if (potionNum == 2) {
			name = "버섯볶음";
		} else if (potionNum == 3) {
			name = "장어구이";
		}
			int idx = -1;
			for (int i = 0; i < PotionList.size(); i++) {
				if (PotionList.get(i).name.equals(name)) {
					idx = i;
				}
			}
			PotionList.remove(idx);
	}

	public void sellMenu() {
		while (true) {
			printItemList();
			System.out.println("[골드 : " + Player.money + "]");
			if (MyitemList.size() > 0) {
				System.out.println("판매할 아이템 번호를 입력하세요. (50 % 세금) [0.뒤로가기]");
				int selSell = MainGame.scan.nextInt();
				if (selSell > 0 && selSell <= MyitemList.size()) {
					System.out.println(MyitemList.get(selSell - 1).name + "을 판매합니다.");
				} else if (selSell == 0) {
					break;
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}
				Player.money += (MyitemList.get(selSell - 1).price / 2);
				MyitemList.remove(selSell - 1);
			} else {
				System.out.println("소유하고 있는 아이템이 없습니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void addItem(Item item) {
		MyitemList.add(item);
	}

	public void addPotion(Item item) {
		PotionList.add(item);
	}

}