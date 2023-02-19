package com.github.codebyxemu.flintandsteel;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

	private FasPlugin plugin = FasPlugin.getPlugin();

	@EventHandler
	public void onItemInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack stack = player.getInventory().getItemInMainHand();

		if (event.getAction() == Action.RIGHT_CLICK_BLOCK && stack.getType() == Material.FLINT_AND_STEEL) {
			boolean chance = Utils.chance(plugin.getConfigManager().getChance());
			int durabilityReducePercent = plugin.getConfigManager().getReduceDurability();

			Bukkit.getLogger().info(Boolean.toString(chance));

			if (chance) {
				player.sendMessage(Utils.color(plugin.getConfigManager().selectRandomSuccessMessage()));
				event.setCancelled(false);
			} else {
				Utils.reduceDurability(stack, durabilityReducePercent);
				player.sendMessage(Utils.color(plugin.getConfigManager().selectRandomFailedMessage()));
				event.setCancelled(true);
			}
		}





	}

}
