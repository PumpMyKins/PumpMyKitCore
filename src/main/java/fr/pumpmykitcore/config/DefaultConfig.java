package fr.pumpmykitcore.config;

import org.bukkit.configuration.file.FileConfiguration;

public class DefaultConfig {

	static FileConfiguration conf = ConfigUtils.getConfigFile();
	
	public static void defaultConfig() {
		
		conf.addDefault("force.eula", true);
		conf.addDefault("developmentMode", true); //To enable debug mode

		conf.addDefault("AllowRandom", false);
		
	}
}
