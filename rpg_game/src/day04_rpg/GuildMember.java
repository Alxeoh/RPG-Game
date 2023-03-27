package day04_rpg;

import java.util.ArrayList;

public class GuildMember {
	ArrayList<Unit> memberUnit = new ArrayList<>();
	
	public void printAllHp() {
		if(this.memberUnit.size()>0) {
			System.out.println("=============== [길드원 hp현황] ==================");
			System.out.print("[1. 길드장]");
			System.out.print(" [이름 : " + this.memberUnit.get(0).name + "]");
			System.out.println(" [레벨 : " + this.memberUnit.get(0).level + "]");
			if(this.memberUnit.get(0).hp<=0) {
				this.memberUnit.get(0).hp = 0;
				System.out.print(" [체력 : [사망]");
			}else {
				System.out.print(" [체력 : " + this.memberUnit.get(0).hp);
				if(this.memberUnit.get(0).ring != null) {
					System.out.print(" / " + this.memberUnit.get(0).maxHp + " + "+ this.memberUnit.get(0).ring.power +  "]");
				} else {
					System.out.print(" / " + this.memberUnit.get(0).maxHp+ "]");
				}
			}
			System.out.println(" [파티중 : " + this.memberUnit.get(0).party + "]");
			for(int i = 1; i< this.memberUnit.size();i++) {
				System.out.print("[" + (i+1) + ". 길드원]");
				System.out.print(" [이름 : " + this.memberUnit.get(i).name + "]");
				System.out.println(" [레벨 : " + this.memberUnit.get(i).level + "]");
				if(this.memberUnit.get(i).hp<=0) {
					this.memberUnit.get(i).hp = 0;
					System.out.print(" [체력 : [사망]");
				}else {
					System.out.print(" [체력 : " + this.memberUnit.get(i).hp);
					if(this.memberUnit.get(i).ring != null) {
						System.out.print(" / " + this.memberUnit.get(i).maxHp + " + "+ this.memberUnit.get(i).ring.power +  "]");
					} else {
						System.out.print(" / " + this.memberUnit.get(i).maxHp+ "]");
					}
				}
				System.out.println(" [파티중 : " + this.memberUnit.get(i).party + "]");
			}
		}
	}
	public void printMyHp() {
		System.out.println("=============== [플레이어 hp현황] =================");
		System.out.print(" [이름 : " + Player.user.name + "]");
		System.out.print(" [레벨 : " + Player.user.level + "]");
		if(Player.user.hp<=0) {
			Player.user.hp = 0;
			System.out.print(" [체력 : [사망]");
		}else {
			System.out.print(" [체력 : " + Player.user.hp);
		}
		System.out.print(" / " + Player.user.maxHp + "]");
		System.out.println(" [파티중 : " + Player.user.party + "]");
	}
	
	public void printAllMember() {
		if(this.memberUnit.size()>0) {
			System.out.println("================== [길드원] ====================");
			for(int i = 1; i< this.memberUnit.size();i++) {
				System.out.print("[" + (i) + ".]");
				System.out.print(" [이름 : " + this.memberUnit.get(i).name + "]");
				System.out.print(" [레벨 : " + this.memberUnit.get(i).level + "]");
				if(this.memberUnit.get(i).hp<=0) {
					this.memberUnit.get(i).hp = 0;
					System.out.print(" [체력 : [사망]");
				}else {
					System.out.print(" [체력 : " + this.memberUnit.get(i).hp);
					if(this.memberUnit.get(i).ring != null) {
						System.out.println(" / " + this.memberUnit.get(i).maxHp + " + "+ this.memberUnit.get(i).ring.power +  "]");
					} else {
						System.out.println(" / " + this.memberUnit.get(i).maxHp+ "]");
					}
				}
				if(this.memberUnit.get(i).weapon != null) {
					System.out.print("[공격력 : " + this.memberUnit.get(i).att + " + "+ this.memberUnit.get(i).weapon.power + "]");
				} else {
					System.out.print("[공격력 : " + this.memberUnit.get(i).att + "]");
				}
				if(this.memberUnit.get(i).armor != null) {
					System.out.print(" [방어력 : " + this.memberUnit.get(i).def + " + "+ this.memberUnit.get(i).armor.power + "]");
				} else {
					System.out.print(" [방어력 : " + this.memberUnit.get(i).def + "]");
				}
				System.out.println(" [파티중 : " + this.memberUnit.get(i).party + "]");
			}
		}
	}
	public void printMe() {
		System.out.println("================= [플레이어] ====================");
		System.out.print("[길드장]");
		System.out.print(" [이름 : " + this.memberUnit.get(0).name + "]");
		System.out.print(" [레벨 : " + this.memberUnit.get(0).level + "]");
		if(this.memberUnit.get(0).hp<=0) {
			this.memberUnit.get(0).hp = 0;
			System.out.print(" [체력 : [사망]");
		}else {
			System.out.print(" [체력 : " + this.memberUnit.get(0).hp);
			if(this.memberUnit.get(0).ring != null) {
				System.out.println(" / " + this.memberUnit.get(0).maxHp + " + "+ this.memberUnit.get(0).ring.power +  "]");
			} else {
				System.out.println(" / " + this.memberUnit.get(0).maxHp+ "]");
			}
		}
		
		if(this.memberUnit.get(0).weapon != null) {
			System.out.print("[공격력 : " + this.memberUnit.get(0).att + " + "+ this.memberUnit.get(0).weapon.power + "]");
		} else {
			System.out.print("[공격력 : " + this.memberUnit.get(0).att + "]");
		}
		if(this.memberUnit.get(0).armor != null) {
			System.out.print(" [방어력 : " + this.memberUnit.get(0).def + " + "+ this.memberUnit.get(0).armor.power + "]");
		} else {
			System.out.print(" [방어력 : " + this.memberUnit.get(0).def + "]");
		}
		System.out.println(" [파티중 : " + this.memberUnit.get(0).party + "]");
	}
	

	public void printAllMember(int exp) {
		if(this.memberUnit.size()>0) {
			System.out.println("================= [길드원] =====================");
			for(int i = 1; i< this.memberUnit.size();i++) {
				System.out.print("[" + (i) + ".]");
				System.out.print(" [이름 : " + this.memberUnit.get(i).name + "]");
				System.out.print(" [레벨 : " + this.memberUnit.get(i).level + "]");
				if(this.memberUnit.get(i).hp<=0) {
					this.memberUnit.get(i).hp = 0;
					System.out.print(" [체력 : [사망]");
				}else {
					System.out.print(" [체력 : " + this.memberUnit.get(i).hp);
					if(this.memberUnit.get(i).ring != null) {
						System.out.println(" / " + this.memberUnit.get(i).maxHp + " + "+ this.memberUnit.get(i).ring.power +  "]");
					} else {
						System.out.println(" / " + this.memberUnit.get(i).maxHp+ "]");
					}
				}
				
				if(this.memberUnit.get(i).weapon != null) {
					System.out.print("[공격력 : " + this.memberUnit.get(i).att + " + "+ this.memberUnit.get(i).weapon.power + "]");
				} else {
					System.out.print("[공격력 : " + this.memberUnit.get(i).att + "]");
				}
				if(this.memberUnit.get(i).armor != null) {
					System.out.print(" [방어력 : " + this.memberUnit.get(i).def + " + "+ this.memberUnit.get(i).armor.power + "]");
				} else {
					System.out.print(" [방어력 : " + this.memberUnit.get(i).def + "]");
				}
				if(this.memberUnit.get(i).party == true) {
					System.out.print(" [경험치 : " + this.memberUnit.get(i).exp + " + " + exp +"]");
				}else {
					System.out.print(" [경험치 : " + this.memberUnit.get(i).exp + "]");
				}
				System.out.println(" [파티중 : " + this.memberUnit.get(i).party + "]");
			}
		}
	}
	public void printMe(int exp) {
		System.out.println("================= [플레이어] ====================");
		System.out.print("[길드장]");
		System.out.print(" [이름 : " + this.memberUnit.get(0).name + "]");
		System.out.print(" [레벨 : " + this.memberUnit.get(0).level + "]");
		if(this.memberUnit.get(0).hp<=0) {
			this.memberUnit.get(0).hp = 0;
			System.out.print(" [체력 : [사망]");
		}else {
			System.out.print(" [체력 : " + this.memberUnit.get(0).hp);
		}
		System.out.println(" / " + this.memberUnit.get(0).maxHp + "]");
		System.out.print("[공격력 : " + this.memberUnit.get(0).att + "]");
		System.out.print(" [방어력 : " + this.memberUnit.get(0).def + "]");
		if(this.memberUnit.get(0).party == true) {
			System.out.print(" [경험치 : " + this.memberUnit.get(0).exp + " + " + exp +"]");
		}else {
			System.out.print(" [경험치 : " + this.memberUnit.get(0).exp + "]");
		}
		System.out.println(" [파티중 : " + this.memberUnit.get(0).party + "]");
	}
	
	public void setMember(Unit member) {
		this.memberUnit.add(member);
	}
	
	public int partyPower() {
		int power = 0;
		for(int i = 0; i<this.memberUnit.size();i++) {
			power += this.memberUnit.get(i).att;
			if(this.memberUnit.get(i).weapon != null) {
				power += this.memberUnit.get(i).weapon.power;
				
			}
		}
		return power;
	}

}
