package me.ashconnell.Fight;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerInteractEvent;

public class FightSignListener extends PlayerListener {
	
	// Can someone please explain what this does exactly?
	public static Fight plugin;
	
	// Can someone please explain what this does exactly?
	public FightSignListener(Fight instance) {
		plugin = instance;
	}
	
	// Called when player uses an interact event
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		// When Sign is Left Clicked
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			Block block = event.getClickedBlock();
			Player player = event.getPlayer();
			
			if (block.getState() instanceof Sign){
				Sign sign = (Sign) block.getState();
				plugin.fightSigns.put(player.getName(), sign);
				
				// If top line matches a class
				if(plugin.fightClasses.containsKey(sign.getLine(0)) && plugin.fightUsersTeam.containsKey(player.getName())){
					
					// If they already picked a class
					if(plugin.fightUsersClass.containsKey(player.getName())){
						
						// The sign theyre clicking is the same as class they are
						if(plugin.fightUsersClass.get(player.getName()) == sign.getLine(0)){
							
							// Remove them from this class and sign
							plugin.fightUsersClass.remove(player.getName());
							if(sign.getLine(2) == player.getName()){
								sign.setLine(2, "");
								sign.update();
								player.getInventory().clear();
								plugin.clearArmorSlots(player);
							}
							else if(sign.getLine(3) == player.getName()){
								sign.setLine(3, "");
								sign.update();
								player.getInventory().clear();
								plugin.clearArmorSlots(player);
							}
							else {
								player.sendMessage(ChatColor.YELLOW + "[Fight] " + ChatColor.WHITE + "Please tell developer about this bug (#5017).");
							}
							
						}
						else {
							player.sendMessage(ChatColor.YELLOW + "[Fight] " + ChatColor.WHITE + "You must first remove yourself from the other class!");
						}
					}
					
					// If they have no current class, set class and change sign.
					else {
						if(sign.getLine(2).trim().equals("")){
							plugin.fightUsersClass.put(player.getName(), sign.getLine(0));
							sign.setLine(2, player.getName());
							sign.update();
							plugin.giveItems(player);
						}
						else if(sign.getLine(3).trim().equals("")){
							plugin.fightUsersClass.put(player.getName(), sign.getLine(0));
							sign.setLine(3, player.getName());
							sign.update();
							plugin.giveItems(player);
						}
						else {
							player.sendMessage(ChatColor.YELLOW + "[Fight] " + ChatColor.WHITE + "There are too many of this class, pick another class.");
						}
					}
				}
			}
	    }
	}
}
