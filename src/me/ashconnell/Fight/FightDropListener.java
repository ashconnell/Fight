package me.ashconnell.Fight;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerListener;

public class FightDropListener extends PlayerListener {
	// Can someone please explain what this does exactly?
	public static Fight plugin;
	
	// Can someone please explain what this does exactly?
	public FightDropListener(Fight instance) {
		plugin = instance;
	}
	
	// Called when player calls a drop event
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		
		if (plugin.fightUsersTeam.containsKey(player.getName())) {
			player.sendMessage(ChatColor.YELLOW + "[Fight] " + ChatColor.WHITE + "Not so fast! No Cheating!");
			event.setCancelled(true);
		}
	}
}