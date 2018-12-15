package fr.pumpmykitcore;

import org.bukkit.configuration.file.FileConfiguration;

public class DefaultConfig {

	static FileConfiguration conf = ConfigUtils.getConfigFile();
	
	public static void defaultConfig() {
		
		conf.addDefault("force.eula", false);
		conf.addDefault("debug", false); //Enable debug mode in the chat and in the console
		conf.addDefault("adminOnly", false); //Only Op player will be able to take kit
		conf.addDefault("developmentMode", false); //For enable admin only and debug mode
		
		conf.addDefault("maxKit", 10);
		
		ConfigUtils.update(conf, ConfigUtils.getConfigYml());
	}
}
