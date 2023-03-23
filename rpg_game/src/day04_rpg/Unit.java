package day04_rpg;

public class Unit {
	String name;
	int level;
	int hp;
	int maxHp;
	int att;
	int def;
	int exp;
	boolean party;
	boolean level2;
	boolean level3;
	boolean level4;
	boolean level5;
	boolean level6;
	boolean level7;
	boolean level8;
	boolean level9;
	boolean level10;
	Item weapon;
	Item armor;
	Item ring;

	public Unit(String n, int l, int h, int a, int d, int e) {
		name = n;
		level = l;
		maxHp = h;
		att = a;
		def = d;
		exp = e;
		hp = maxHp;
		party = false;
		weapon = null;
		armor = null;
		ring = null;
	}
	public Unit(String n, int l, int h1, int h2, int a, int d, int e, boolean p, boolean p2, boolean p3, boolean p4, boolean p5, boolean p6, boolean p7, boolean p8, boolean p9, boolean p10) {
		name = n;
		level = l;
		hp = h1;
		maxHp = h2;
		att = a;
		def = d;
		exp = e;
		party = p;
		level2 = p2;
		level3 = p3;
		level4 = p4;
		level5 = p5;
		level6 = p6;
		level7 = p7;
		level8 = p8;
		level9 = p9;
		level10 = p10;
		weapon = null;
		armor = null;
		ring = null;
	}

	public void setItem(Item w, Item a, Item r) {
		weapon = w;
		armor = a;
		ring = r;
	}
//	Unit temp = new Unit(playerName, 1, 120, 7,  11, 0,true);

	public void setExp(int exp) {
		this.exp += exp;
		if (this.exp >= 40 && this.level2 == false) {
			System.out.printf("[%s] Level Up! (Lv1 -> Lv2)\n", this.name);
			this.level = 2;
			this.maxHp += 30;
			this.att += 3;
			this.def += 3;
			this.hp = this.maxHp;
			this.level2 = true;
			this.level2 = true;
		} else if (this.exp >= 300 && this.level3 == false) {
			System.out.printf("[%s] Level Up! (Lv2 -> Lv3)\n", this.name);
			this.level = 3;
			this.maxHp += 40;
			this.att += 4;
			this.def += 4;
			this.hp = this.maxHp;
			this.level3 = true;
		} else if (this.exp >= 600 && this.level4 == false) {
			System.out.printf("[%s] Level Up! (Lv3 -> Lv4)\n", this.name);
			this.level = 4;
			this.maxHp += 50;
			this.att += 5;
			this.def += 5;
			this.hp = this.maxHp;
			this.level4 = true;
		} else if (this.exp >= 1000&& this.level5 == false) {
			System.out.printf("[%s] Level Up! (Lv4 -> Lv5)\n", this.name);
			this.level = 5;
			this.maxHp += 60;
			this.att += 6;
			this.def += 6;
			this.hp = this.maxHp;
			this.level5 = true;
		} else if (this.exp >= 1500 && this.level6 == false) {
			System.out.printf("[%s] Level Up! (Lv5 -> Lv6)\n", this.name);
			this.level = 6;
			this.maxHp += 70;
			this.att += 7;
			this.def += 7;
			this.hp = this.maxHp;
			this.level6 = true;
		} else if (this.exp >= 2000 && this.level7 == false) {
			System.out.printf("[%s] Level Up! (Lv6 -> Lv7)\n", this.name);
			this.level = 7;
			this.maxHp += 80;
			this.att += 8;
			this.def += 8;
			this.hp = this.maxHp;
			this.level7 = true;
		} else if (this.exp >= 2500 && this.level8 == false) {
			System.out.printf("[%s] Level Up! (Lv7 -> Lv8)\n", this.name);
			this.level = 8;
			this.maxHp += 90;
			this.att += 9;
			this.def += 9;
			this.hp = this.maxHp;
			this.level8 = true;
		} else if (this.exp >= 3000 && this.level9 == false) {
			System.out.printf("[%s] Level Up! (Lv8 -> Lv9)\n", this.name);
			this.level = 9;
			this.maxHp += 100;
			this.att += 10;
			this.def += 10;
			this.hp = this.maxHp;
			this.level9 = true;
		} else if (this.exp >= 3500 && this.level10 == false) {
			System.out.printf("[%s] Level Up! (Lv9 -> Lv10)\n", this.name);
			this.level = 10;
			this.maxHp += 110;
			this.att += 11;
			this.def += 11;
			this.hp = this.maxHp;
			this.level10 = true;
		}
	}

	public void printStatus() {
		System.out.print("[길드장]");
		System.out.print(" [이름 : " + this.name + "]");
		System.out.print(" [레벨 : " + this.level + "]");
		if (this.hp <= 0) {
			this.hp = 0;
			System.out.print(" [체력 : [사망]");
		} else {
			System.out.print(" [체력 : " + this.hp);
		}
		System.out.println(" / " + this.maxHp + "]");
		System.out.print("[공격력 : " + this.att + "]");
		System.out.print(" [방어력 : " + this.def + "]");
		System.out.println(" [파티중 : " + this.party + "]");
	}

	public void printEquitedItem() {
		if (weapon == null) {
			System.out.println("[무기 : 없음 ]");
		} else {
			System.out.println("[무기 : " + weapon.name + "]");
		}
		if (armor == null) {
			System.out.println("[방어구 : 없음 ]");
		} else {
			System.out.println("[방어구 : " + armor.name + "]");
		}
		if (ring == null) {
			System.out.println("[반지 : 없음 ]");
		} else {
			System.out.println("[반지 : " + ring.name + "]");
		}
	}
}