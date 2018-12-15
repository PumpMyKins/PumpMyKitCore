package fr.pumpmykitcore;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtils {

	public static final String CONFIG_PATH = "plugins/kit/";

	private static File configYml = new File(CONFIG_PATH+"config.yml");
	private static FileConfiguration configFile = YamlConfiguration.loadConfiguration(configYml);

	private static File kitFile = new File(CONFIG_PATH + "kit.yml");
	private static FileConfiguration kitConf = YamlConfiguration.loadConfiguration(kitFile);
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initFolder() {

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
	public boolean initConfigFile() {

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
	
	public boolean initKitFile() {
		
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
	
	
}
