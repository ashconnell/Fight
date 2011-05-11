package me.ashconnell.Fight;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerListener;

public class FightDisconnectListener extends PlayerListener {
	// Can someone please explain what this does exactly?
	public static Fight plugin;
	
	// Can someone please explain what this does exactly?
	public FightDisconnectListener(Fight instance) {
		plugin = instance;
	}
	
	// Called when player calls a drop event
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		
		if (plugin.fightUsersTeam.containsKey(player.getName())) {
			if(plugin.fightUsersTeam.get(player.getName()) == "red"){
				plugin.redTeam = plugin.redTeam - 1;
			}
			else {
				plugin.blueTeam = plugin.blueTeam - 1;
			}
			plugin.cleanSigns(player.getName());
			plugin.clearArmorSlots(player);
			player.getInventory().clear();
			plugin.fightUsersTeam.remove(player.getName());
			plugin.fightUsersClass.remove(player.getName());
			plugin.goToWaypoint(player, "exit");
		}
	}
}