package day04_rpg;

public class GuildInfo {
	String name;
	int maxUser;
	int user;
	GuildMember member = new GuildMember();
	
	public GuildInfo() {
		this.name = "";
		this.maxUser = 30;
		this.user = 0; 
	}
	
	public GuildInfo(String name, int mu, int u) {
		this.name = name;
		this.maxUser = mu;
		this.user = u; 
	}
	
	public void printMember() {
		this.member.printAllMember();
	}
	
	public int getUser() {
		return this.user;
	}
	
	public void setUser() {
		this.user = getUser()+1;
	}
	public void outUser() {
		this.user = getUser()-1;
	}
}