package day04_rpg;

import java.util.ArrayList;

public class AllGuild {
	ArrayList<GuildInfo> guildList = new ArrayList<>();
	GuildInfo guildInfo = new GuildInfo();
	public AllGuild() {
		
		GuildInfo default1 = new GuildInfo("서울",30,0);
		GuildInfo default2 = new GuildInfo("대한",30,0);
		
	guildList.add(default1);
	guildList.add(default2);
	for(int i = 0; i< 5; i ++) {
		String[] n1 = { "박", "이", "김", "최", "유", "지", "오" };
		String[] n2 = { "명", "기", "종", "민", "재", "석", "광" };
		String[] n3 = { "수", "자", "민", "수", "석", "민", "철" };
		
		String name1 = n1[MainGame.ran.nextInt(n1.length)];
		name1 += n2[MainGame.ran.nextInt(n1.length)];
		name1 += n3[MainGame.ran.nextInt(n1.length)];
		String name2 = n1[MainGame.ran.nextInt(n1.length)];
		name2 += n2[MainGame.ran.nextInt(n1.length)];
		name2 += n3[MainGame.ran.nextInt(n1.length)];
		int ran1 = MainGame.ran.nextInt(8) + 2;
		int hp1 = ran1 * 11;
		int att1 = ran1 + 1;
		int def1 = ran1 / 2 + 1;
		int ran2 = MainGame.ran.nextInt(8) + 2;
		int hp2 = ran1 * 11;
		int att2 = ran1 + 1;
		int def2 = ran1 / 2 + 1;
		Unit temp = new Unit(name1, 1, hp1, att1, def1, 0);
		Unit temp2 = new Unit(name2, 1, hp2, att2, def2, 0);
		
		guildList.get(0).member.memberUnit.add(temp);
		guildList.get(1).member.memberUnit.add(temp2);
		guildList.get(0).setUser();
		guildList.get(1).setUser();
	}
	}
	
	public void setGuildes(GuildInfo guildInfo) {
		guildList.add(guildInfo);
	}
	
	public void setMyGuild(GuildInfo guildInfo) {
		guildList.add(0, guildInfo);
	}
	
}