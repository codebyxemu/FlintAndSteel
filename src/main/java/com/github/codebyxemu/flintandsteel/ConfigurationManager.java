package com.github.codebyxemu.flintandsteel;

public class ConfigurationManager {

	private FasPlugin plugin = FasPlugin.getPlugin();

	public ConfigurationManager() {
		plugin.getConfigFile().setDefault("Chance", 75);
		plugin.getConfigFile().setDefault("ReduceDurability", 5);
		plugin.getConfigFile().setDefault("Messages.Success", new String[]{
				"&aYou were able to light a fire."
		});
		plugin.getConfigFile().setDefault("Messages.Failed", new String[]{
				"&cCould not light a fire... Too much wind!",
				"&cCould not light a fire, try again with more power."
		});
	}

	public int getChance() {
		return plugin.getConfigFile().getInt("Chance");
	}

	public int getReduceDurability() {
		return plugin.getConfigFile().getInt("ReduceDurability");
	}

	public String selectRandomSuccessMessage() {
		if (plugin.getConfigFile().getStringList("Messages.Success").size() == 1) {
			return plugin.getConfigFile().getStringList("Messages.Success").get(0);
		}
		return plugin.getConfigFile().getStringList("Messages.Success").stream().findAny().get();
	}

	public String selectRandomFailedMessage() {
		if (plugin.getConfigFile().getStringList("Messages.Failed").size() == 1) {
			return plugin.getConfigFile().getStringList("Messages.Failed").get(0);
		}
		return plugin.getConfigFile().getStringList("Messages.Failed").stream().findAny().get();
	}

}
