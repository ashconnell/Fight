package me.ashconnell.Fight;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class FightRespawnListener extends PlayerListener {
	// Can someone please explain what this does exactly?
	public static Fight plugin;
	
	// Can someone please explain what this does exactly?
	public FightRespawnListener(Fight instance) {
		plugin = instance;
	}
	
	// Called when player clicks respawn button
	public void onPlayerRespawn(PlayerRespawnEvent event){
		Player player = event.getPlayer();
		
		// If they are part of the fight team		
		if (plugin.fightUsersRespawn.containsKey(player.getName())){
			
			// Respawn them at the spectator Waypoint
			Location l = plugin.getCoords("spectator");
			event.setRespawnLocation(l);
			plugin.fightUsersRespawn.remove(player.getName());
		}
	}
}
