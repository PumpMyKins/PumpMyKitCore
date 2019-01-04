package fr.pumpmykitcore.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.pumpmykitcore.utils.Utils;

public class ConfigUtils {

	public static final String CONFIG_PATH = "plugins/kit/";

	private static File configYml = new File(CONFIG_PATH+"config.yml");
	private static FileConfiguration configFile = YamlConfiguration.loadConfiguration(configYml);

	private static File kitFile = new File(CONFIG_PATH + "kit.yml");
	private static FileConfiguration kitConf = YamlConfiguration.loadConfiguration(kitFile);

	private static File buyFile = new File(CONFIG_PATH + "buy.yml");
	private static FileConfiguration buyConf = YamlConfiguration.loadConfiguration(buyFile);
	
	public static void init() {
		
		initFolder();
		initConfigFile();
		initKitFile();
		initBuyFile();
		defaultConfig();
	}
	
	private static void defaultConfig() {
		
		configFile.addDefault("force.eula", false);
		configFile.addDefault("developmentMode", false); //To enable debug mode
		configFile.addDefault("AllowRandom", false);
	}

	public void save(FileConfiguration configFile, File configYml) {

		try {
			configFile.save(configYml);
		} catch (IOException e) {

			e.printStackTrace();
			Utils.error(e);
		}
	}

	public static void update(FileConfiguration newConf, File file) {
		
		try {
			newConf.save(file);
		} catch (IOException e) {

			e.printStackTrace();
			Utils.error(e);
		}
	}
	
	public static void initFolder() {

		File file = new File(CONFIG_PATH);
		if(!file.exists()) {
			file.mkdir();
			initConfigFile();
			initKitFile();
		}
		else {
			initConfigFile();
			initKitFile();
		}
	}
	public static boolean initConfigFile() {

		if(configYml.exists()) {
			return true;
		} 
		else {
			try {
				DefaultConfig.defaultConfig();
				configYml.createNewFile();
				return true;
			} catch (IOException e) {

				e.printStackTrace();
				Utils.error(e);
				return false;
			}
		}
	}
	
	public static boolean initKitFile() {
		
		if(kitFile.exists()) {
			return true;
		} else {
			try {
				kitFile.createNewFile();
				return true;
			} catch (IOException e) {

				e.printStackTrace();
				Utils.error(e);
				return false;
			}
		}
	}
	
	public static boolean initBuyFile() {
		
		if(buyFile.exists()) {
			return true;
		} else {
			try {
				buyFile.createNewFile();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Utils.error(e);
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public static File getConfigYml() {
		return configYml;
	}

	public void setConfigYml(File configYml) {
		ConfigUtils.configYml = configYml;
	}

	public static FileConfiguration getConfigFile() {
		return configFile;
	}

	public void setConfigFile(FileConfiguration configFile) {
		ConfigUtils.configFile = configFile;
	}

	public static File getKitFile() {
		return kitFile;
	}

	public void setKitFile(File kitFile) {
		ConfigUtils.kitFile = kitFile;
	}

	public static FileConfiguration getKitConf() {
		return kitConf;
	}

	public void setKitConf(FileConfiguration kitConf) {
		ConfigUtils.kitConf = kitConf;
	}

	public static FileConfiguration getBuyConf() {
		return buyConf;
	}

	public static void setBuyConf(FileConfiguration buyConf) {
		ConfigUtils.buyConf = buyConf;
	}

	public static File getBuyFile() {
		return buyFile;
	}

	public static void setBuyFile(File buyFile) {
		ConfigUtils.buyFile = buyFile;
	}
	
	
	
}
