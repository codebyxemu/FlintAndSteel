package com.github.codebyxemu.flintandsteel;

import com.github.codebyxemu.flintandsteel.manager.ConfigurationManager;
import com.github.codebyxemu.flintandsteel.event.InteractListener;
import com.github.codebyxemu.flintandsteel.manager.TierManager;
import de.leonhard.storage.Yaml;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class FasPlugin extends JavaPlugin {

	private static FasPlugin plugin;

	private int pluginId = 17765;
	private Metrics metrics;

	private ConfigurationManager configManager;
	private TierManager tierManager;
	private Yaml configFile;
	private Yaml tiersFile;

	@Override
	public void onEnable() {
		// Plugin startup logic
		plugin = this;
		// TODO: Move this into seperate method
		tierManager = new TierManager();
		tierManager.loadTiersFromConfig();

		metrics();
		config();
		event();

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

	private void metrics() {
		metrics = new Metrics(this, pluginId);

	}

	private void config() {
		configFile = new Yaml("config.yml", "plugins/FlintAndSteel");
		tiersFile = new Yaml("tiers.yml", "plugins/FlintAndSteel");
		configManager = new ConfigurationManager();
	}

	private void tiers() {
		tierManager = new TierManager();
		tierManager.loadTiersFromConfig();
	}

	private void event() {
		getServer().getPluginManager().registerEvents(new InteractListener(), this);
	}
}
