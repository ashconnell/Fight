package me.ashconnell.Fight;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class FightReadyListener extends PlayerListener {
	// Can someone please explain what this does exactly?
	public static Fight plugin;
	
	// Can someone please explain what this does exactly?
	public FightReadyListener(Fight instance) {
		plugin = instance;
	}
	
	// Called when player uses an interact event
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		// When Iron Block is Left Clicked 
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			Block block = event.getClickedBlock();
			Player player = event.getPlayer();
			
			// If Block Is Iron & Player Is Fight User & And Team Is Ready
			if (block.getTypeId() == 42 && plugin.fightUsersTeam.containsKey(player.getName()) && plugin.teamReady(plugin.fightUsersTeam.get(player.getName()))){
				
				// Tell your team they are ready
				String color = plugin.fightUsersTeam.get(player.getName());
				
				if(color == "red"){
					plugin.redTeamIronClicked = true;
					plugin.tellEveryone(ChatColor.RED + "Red " + ChatColor.WHITE + "team is ready!");
					// If both teams ready teleport everyone to the spawns
					if(plugin.teamReady("blue") && plugin.blueTeamIronClicked){
						plugin.teleportAllToSpawn();
						plugin.fightInProgress = true;
						plugin.tellEveryone("Let the Fight begin!");
					}
				}
				
				else if(color == "blue"){
					plugin.blueTeamIronClicked = true;
					plugin.tellEveryone(ChatColor.BLUE + "Blue " + ChatColor.WHITE + "team is ready!");
					// If both teams ready teleport everyone to the spawns
					if(plugin.teamReady("red") && plugin.redTeamIronClicked){
						plugin.teleportAllToSpawn();
						plugin.fightInProgress = true;
						plugin.tellEveryone("Let the Fight begin!");
					}
				}
				
			}
			
			// Player forgot to pick a class
			else if (block.getTypeId() == 42 && plugin.fightUsersTeam.containsKey(player.getName())){
				player.sendMessage(ChatColor.YELLOW + "[Fight] " + ChatColor.WHITE + "Your team have not all picked a class!");
			}
		}
	}

}
