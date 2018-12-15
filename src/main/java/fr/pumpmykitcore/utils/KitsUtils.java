package fr.pumpmykitcore.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import fr.pumpmykitcore.config.ConfigUtils;
import fr.pumpmykitcore.obj.Item;
import fr.pumpmykitcore.obj.Kit;

public class KitsUtils {
		
	static FileConfiguration kitConf = ConfigUtils.getKitConf();
	
	public static void createKit(Kit k) {
		
		Utils.debug(k); //DEBUG
		String kitname = k.getKitname();
		kitConf.set("kit."+kitname+".eula", k.isEula());
		kitConf.set("kit."+kitname+".itemName", k.getItemNameList());
		for(Item i : k.getItemList()) {
			kitConf.set("kit."+kitname+".item", k.getItemList());
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".id", i.getId());
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".quantity", i.getQuantity());
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".meta", i.getMeta());
		}
		if(kitConf.contains("kit.listKit")) {
			List<String> listKit = kitConf.getStringList("kit.listKit");
			listKit.add(kitname);
			kitConf.set("kit.listKit", listKit);
			Utils.debug(listKit); //DEBUG
		}
		
		Utils.debug(kitConf); //DEBUG
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());
	}
	
	public static void deleteKit(String kitname) {
		
		Utils.debug(kitname); //DEBUG
		kitConf.set("kit."+kitname, null);
		if(kitConf.contains("kit.listKit")) {
			List<String> listKit = kitConf.getStringList("kit.listKit");
			Utils.debug(listKit); //DEBUG
			for(String s : listKit) {
				int i = 0;
				if(s.equalsIgnoreCase(kitname)) {
					listKit.remove(i);
				}
			}
			kitConf.set("kit.listKit", listKit);
			Utils.debug(listKit); //DEBUG
		}
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());

	}
	
	public Kit updateKit(Kit k) {
	
		Utils.debug(k); //DEBUG
		String kitname = k.getKitname();
		Kit kitAfter = k;
		kitConf.set("kit."+kitname+".eula", k.isEula());
		kitConf.set("kit."+kitname+".itemName", k.getItemNameList());
		for(Item i : k.getItemList()) {
			kitConf.set("kit."+kitname+".item", k.getItemList());
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".id", i.getId());
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".quantity", i.getQuantity());
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".meta", i.getMeta());
		}
		Utils.debug(kitConf); //DEBUG
		Utils.debug(k); //DEBUG
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());
		
		return kitAfter;
		
	}
	
	public static Kit getKit(String kitname) {
		
		Utils.debug(kitname); //DEBUG
		if(ConfigUtils.getKitConf().get("kit."+kitname) == null) {
			
			return null; 
		} else {
			
			Kit kit = new Kit();
			kit.setEula(ConfigUtils.getKitConf().getBoolean("kit."+kitname+".eula"));
			List<String> itemName = kitConf.getStringList("kit."+kitname+".itemName");
			for(String name : itemName)	{
				
				Item i = new Item();
				i.setId(kitConf.getString("kit."+kitname+".item."+name+".id"));
				i.setItemName(name);
				i.setMeta(kitConf.getInt("kit."+kitname+".item."+name+".meta"));
				i.setQuantity(kitConf.getInt("kit."+kitname+".item."+name+".quantity"));
				
				kit.getItemList().add(i);
			}
			Utils.debug(kit); //DEBUG
			return kit;
		}
		
	}
	
	public static List<Kit> getAllKit(){
		
		List<Kit> listKit = new ArrayList<Kit>();
		for(String s : kitConf.getStringList("kit.listKit")) {
			
			listKit.add(getKit(s));
		}
		Utils.debug(listKit); //DEBUG

		return listKit;
	}

}