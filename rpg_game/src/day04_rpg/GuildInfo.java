package day04_rpg;

public class GuildInfo {
	String name;
	int maxUser;
	int user;
	
	public GuildInfo(String name) {
		this.name = name;
		this.maxUser = 30;
		this.user = 0; 
	}
	
	public int getUser() {
		return this.user;
	}
	
	public void setUser() {
		this.user = getUser()+1;
	}
}
