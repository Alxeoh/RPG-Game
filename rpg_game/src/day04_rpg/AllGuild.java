package day04_rpg;

import java.util.ArrayList;

public class AllGuild {
	ArrayList<GuildInfo> guildList = new ArrayList<>();
	
	public AllGuild() {
		
		GuildInfo default1 = new GuildInfo("서울길드");
		GuildInfo default2 = new GuildInfo("대한길드");
		
	guildList.add(0, default1);
	guildList.add(1, default2);
	}
	
	public void setGuildes(GuildInfo guildInfo) {
		guildList.add(guildInfo);
	}

}
