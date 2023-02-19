package com.github.codebyxemu.flintandsteel;

import de.leonhard.storage.Yaml;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class FasPlugin extends JavaPlugin {

	private static FasPlugin plugin;

	private ConfigurationManager configManager;
	private Yaml configFile;

	@Override
	public void onEnable() {
		// Plugin startup logic
		plugin = this;


		configFile = new Yaml("config.yml", "plugins/FlintAndSteel");
		configManager = new ConfigurationManager();

		getServer().getPluginManager().registerEvents(new InteractListener(), this);

		getLogger().info("FlintAndSteel-Improved was successfully started.");
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		getLogger().info("FlintAndSteel-Improved was successfully shut down.");
	}

	public static FasPlugin getPlugin() {
		return plugin;
	}
}
