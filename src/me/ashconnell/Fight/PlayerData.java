package me.ashconnell.Fight;

public class PlayerData {
	private String fightClass = "none";
	private String team = "none";
	
	public String getFightClass(){
		return fightClass;
	}
	public void setFightClass(String t){
		this.fightClass = t;
	}
	
	public String getTeam(){
		return team;
	}
	public void setTeam(String t){
		this.team = t;
	}
}
