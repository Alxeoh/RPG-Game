package day04_rpg;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class MainGame {
	static Scanner scan = new Scanner(System.in);
	static Random ran = new Random();

	public String printGameMenu() {
		String str = "";
		if (!Player.log.equals("100")) {
			str = "마이페이지";
		} else {
			str = "아이디생성 및 캐릭터생성";
		}
		System.out.println("================= [메인메뉴] ====================");
		System.out.printf("[1.%s]\n[2.길드관리]\n[3.상점]\n[4.인벤토리]\n[5.사냥]\n[6.저장]\n[7.로드]\n[0.종료]\n", str);
		System.out.println("===============================================");
		System.out.print("메뉴입력 : ");
		return str;
	}

	public void createUser() {
		System.out.print("캐릭터 아이디 : ");
		String playerId = scan.next();
		System.out.print("캐릭터 패스워드 : ");
		String playerPw = scan.next();
		System.out.print("캐릭터 이름: ");
		String playerName = scan.next();
		Unit temp = new Unit(playerName, 1, 120, 120, 7, 11, 0, true, false, false, false, false, false, false, false,
				false, false);
		Player.id = playerId;
		Player.pw = playerPw;
		Player.user = temp;
		Player.log = playerId;
		System.out.printf("[%s님 환영합니다.]\n", playerName);
	}

	public void myPage() {
		Player.user.printStatus();
		Player.user.printEquitedItem();
	}

	public MainGame() {
		Player player = new Player();
		Shop shop = new Shop();
		FileData fileData = new FileData();
		Hunt hunt = new Hunt();
		while (true) {
			String str = printGameMenu();
			int sel = scan.nextInt();
			if (sel == 1) {
				if (!Player.log.equals("100")) {
					myPage();
				} else {
					createUser();
				}
			} else if (sel == 2) {
				player.guildMenu();
			} else if (sel == 3) {
				shop.shopMng();
			} else if (sel == 4) {
				player.inventoryMenu();
			} else if (sel == 5) {
				hunt.huntMenu();
			} else if (sel == 6) {
				try {
					fileData.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (sel == 7) {
				try {
					fileData.loadData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (sel == 0) {
				System.out.println("게임을 종료 합니다.");
				break;
			} else {
				System.out.println("없는 메뉴입니다.");
			}
		}
		MainGame.scan.close();
	}
}

public class _TextRpg {
	public static void main(String[] args) {
		new MainGame();
	}
}