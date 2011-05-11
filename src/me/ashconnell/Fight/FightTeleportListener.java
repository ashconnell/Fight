package me.ashconnell.Fight;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class FightTeleportListener extends PlayerListener{
	// Can someone please explain what this does exactly?
	public static Fight plugin;
	
	// Can someone please explain what this does exactly?
	public FightTeleportListener(Fight instance) {
		plugin = instance;
	}
	
	// Called when player calls a drop event
	public void onPlayerTeleport(PlayerTeleportEvent event){
		Player player = event.getPlayer();
		
		if(plugin.fightUsersTeam.containsKey(player.getName())){
			if(!plugin.fightTelePass.containsKey(player.getName())){
				event.setCancelled(true);
				plugin.tellPlayer(player, "Please use '/Fight Leave' to exit the fight");
			}
		}
	}
}
