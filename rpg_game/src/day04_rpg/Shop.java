package day04_rpg;

import java.util.ArrayList;

public class Shop {
	ArrayList<Item> itemList = new ArrayList<>();

	public Shop() {
		Item temp = new Item();
		temp.kind = Item.WEAPON;
		temp.name = "나무검";
		temp.power = 3;
		temp.price = 1000;
		temp.level = 1;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.WEAPON;
		temp.name = "철검";
		temp.power = 5;
		temp.price = 2000;
		temp.level = 3;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.WEAPON;
		temp.name = "레이피어";
		temp.power= 7;
		temp.price = 2500;
		temp.level = 6;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.ARMOR;
		temp.name = "티셔츠";
		temp.power= 1;
		temp.price = 300;
		temp.level = 1;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.ARMOR;
		temp.name = "가죽갑옷";
		temp.power= 4;
		temp.price = 800;
		temp.level = 3;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.ARMOR;
		temp.name = "강철갑옷";
		temp.power= 7;
		temp.price = 1500;
		temp.level = 6;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.RING;
		temp.name = "은반지";
		temp.power= 7;
		temp.price = 3000;
		temp.level = 1;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.RING;
		temp.name = "금반지";
		temp.power = 15;
		temp.price = 6000;
		temp.level = 3;
		itemList.add(temp);

		temp = new Item();
		temp.kind = Item.RING;
		temp.name = "다이아반지";
		temp.power = 20;
		temp.price = 20000;
		temp.level = 6;
		itemList.add(temp);
		
		temp = new Item();
		temp.kind = Item.POTION;
		temp.name = "빨간포션";
		temp.power = 20;
		temp.price = 100;
		temp.level = 1;
		itemList.add(temp);
		
		temp = new Item();
		temp.kind = Item.POTION;
		temp.name = "버섯볶음";
		temp.power = 40;
		temp.price = 500;
		temp.level = 1;
		itemList.add(temp);
		
		temp = new Item();
		temp.kind = Item.POTION;
		temp.name = "장어구이";
		temp.power = 100;
		temp.price = 2000;
		temp.level = 1;
		itemList.add(temp);
		
		}

	public void shopMng() {
		if(!Player.log.equals("100")) {
			while (true) {
				System.out.println("=================== [상점] =====================");
				System.out.println("[1.무기]\n[2.갑옷]\n[3.반지]\n[4.회복포션]\n[0.뒤로가기]");
				System.out.println("===============================================");
				System.out.print("메뉴입력 : ");
				int selKind = MainGame.scan.nextInt();
				if (selKind == 0)
					return;
				while (true) {
					if (selKind == Item.WEAPON)
						System.out.println("=================== [무기] =====================");
					else if (selKind == Item.ARMOR)
						System.out.println("=================== [방어구] ===================");
					else if (selKind == Item.RING)
						System.out.println("=================== [반지] =====================");
					else if (selKind == Item.POTION)
						System.out.println("=================== [회복포션] ==================");
					else {
						System.out.println("없는 메뉴 입니다.");
						break;
					}
					int kindSize = printItems(selKind);
					System.out.println("===============================================");
					System.out.println("[현재골드 : " + Player.money + "G]");
					System.out.println("===============================================");
					System.out.println("구입할 아이템 번호를 입력하세요 [0.뒤로가기]");
				
					int selNum = MainGame.scan.nextInt();
					if (selNum == 0)
						break;
					else if(selNum>0 && selNum <= kindSize) {
						int count = 0;
						for (int i = 0; i < itemList.size(); i++) {
							if (itemList.get(i).kind == selKind) {
								count++;
								if (count == selNum) {
									if(itemList.get(i).kind == Item.POTION) {
										if(Player.money < itemList.get(i).price) {
											System.out.printf("[구매불가]\n%dG 부족합니다..\n",itemList.get(i).price-Player.money);
											break;
										}
											Player.inven.addPotion(itemList.get(i));
											Player.money -= itemList.get(i).price;
											System.out.println("[" + itemList.get(i).name + "] 구입완료!");
											try {
												Thread.sleep(1000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											break;
									}else {
										if(Player.money < itemList.get(i).price) {
											System.out.printf("[구매불가]\n%dG 부족합니다..\n",itemList.get(i).price-Player.money);
											break;
										}
										Player.inven.addItem(itemList.get(i));
										Player.money -= itemList.get(i).price;
										System.out.println("[" + itemList.get(i).name + "] 구입완료!");
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										break;
									}
								}
							}
						}
					} else {
						System.out.println("없는 아이템 번호입니다.");
						break;
					}
				}
			}
		} else {
			System.out.println("캐릭터생성을 먼저 해주세요.");
		}
	}

	public int printItems(int kind) {
		int count = 0;
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).kind != kind)
				continue;
			System.out.print("[" + (count + 1) + "번]");
			System.out.print("[이름 : " + itemList.get(i).name + "]");
			System.out.print("[레벨제한 : " + itemList.get(i).level + "]");
			if(itemList.get(i).kind == Item.POTION) {
				System.out.print("[회복량 : " + itemList.get(i).power + "]");
			} else {
				System.out.print("[파워 : " + itemList.get(i).power + "]");
			}
			System.out.print("[가격 : " + itemList.get(i).price + "]");
			System.out.println("");
			count++;
		}
		if(kind == Item.POTION) {
			System.out.println("[사망한 인원은 빨간포션으로 살릴 수 있습니다.]");
		}
		return count;
	}

}