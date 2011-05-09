package me.ashconnell.Fight;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

import com.iConomy.*;

public class FightServerListener extends ServerListener {
	private Fight plugin;

	public FightServerListener(Fight instance) {
		plugin = instance;
	}
	
	public void onPluginDisable(PluginDisableEvent event) {
		if (plugin.iConomy != null) {
			if (event.getPlugin().getDescription().getName().equals("iConomy")) {
				plugin.iConomy = null;
				Fight.log.info("[Fight] Un-hooked from iConomy.");
			}
		}
	}
	public void onPluginEnable(PluginEnableEvent event) {
		if (plugin.iConomy == null) {
			Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");
			if (iConomy != null) {
				if (iConomy.isEnabled()) {
					plugin.iConomy = (iConomy) iConomy;
					Fight.log.info("[Fight] Hooked into iConomy!");
				}
			}
		}
	}
}
