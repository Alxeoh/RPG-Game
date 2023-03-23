package day04_rpg;

public class Item {
	static final int WEAPON = 1;
	static final int ARMOR = 2;
	static final int RING = 3;
    static final int POTION = 4;
	int kind;
	String name;
	int power;
	int price;
	int level;
	
	public void setItem(int k, String n, int p, int pr, int lv)  {
		kind = k;
		name = n;
		power = p;
		price = pr;
		level = lv;
	}

}