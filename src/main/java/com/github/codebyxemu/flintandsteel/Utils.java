package com.github.codebyxemu.flintandsteel;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Utils {

	private static final Random rand = new Random();

	public static boolean chance(int percent) {
		if (percent < 0 || percent > 100) {
			throw new IllegalArgumentException("Percentage must be between 0 and 100");
		}
		return rand.nextInt(100) < percent;
	}

	public static ItemStack reduceDurability(ItemStack item, double percent) {
		if (item == null || item.getType().getMaxDurability() == 0) {
			return item;
		}

		short durability = item.getDurability();
		short maxDurability = item.getType().getMaxDurability();
		double reduceAmount = maxDurability * (percent / 100.0);
		durability += reduceAmount;

		if (durability > maxDurability) {
			durability = maxDurability;
		}

		item.setDurability(durability);

		if (durability <= 0) {
			item.setAmount(0);
		}

		return item;
	}

	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}


}
