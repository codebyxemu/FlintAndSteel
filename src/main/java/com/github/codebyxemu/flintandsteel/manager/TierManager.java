package com.github.codebyxemu.flintandsteel.manager;

import com.github.codebyxemu.flintandsteel.FasPlugin;
import com.github.codebyxemu.flintandsteel.model.Tier;
import com.github.codebyxemu.flintandsteel.util.Utils;
import de.leonhard.storage.Yaml;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TierManager {

	private FasPlugin plugin = FasPlugin.getPlugin();
	private Yaml config = plugin.getTiersFile();
	private ArrayList<Tier> tiers;

	public TierManager() {
		this.tiers = new ArrayList<>();
		loadTiersFromConfig(); // Load all the tiers from the configuration file, and add it to the Tiers list.
	}

	public void loadTiersFromConfig() {
		for (String tier : config.getSection("Tiers").singleLayerKeySet()) {
			Tier add = new Tier(
					Integer.valueOf(tier),
					config.getString("Tiers." + tier + ".Display"),
					config.getInt("Tiers." + tier + ".Chance"),
					config.getInt("Tiers." + tier + ".DurabilityReduce")
			);
			tiers.add(add);
		}
	}

	public void give(Player player, Tier tier) {
		player.getInventory().addItem(createItemFromTier(tier));
	}

	public Tier getTier(String tierInt) {
		return tiers.stream().filter(tier -> tier.getTier() == Integer.valueOf(tierInt)).findFirst().get();
	}

	public List<Tier> getTier(Predicate<Tier> predicate) {
		return tiers.stream().filter(predicate).collect(Collectors.toList());
	}

	public ItemStack createItemFromTier(Tier tier) {
		ItemStack stack = new ItemStack(Material.FLINT_AND_STEEL);
		ItemMeta meta = stack.getItemMeta();

		meta.setDisplayName(Utils.color(tier.getDisplay()));
		meta.setLore(Arrays.asList(Utils.color("&7Tier: &f" + tier.getTier())));

		stack.setItemMeta(meta);
		return stack;
	}

 }
