package fr.pumpmykitcore;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import fr.pumpmykitcore.ConfigUtils;

public class KitsUtils {
		
	FileConfiguration kitConf = ConfigUtils.getKitConf();
	
	public void createKit(Kit k) {
		
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
		}
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());
	}
	
	public void deleteKit(String kitname) {
		
		kitConf.set("kit."+kitname, null);
		if(kitConf.contains("kit.listKit")) {
			List<String> listKit = kitConf.getStringList("kit.listKit");
			for(String s : listKit) {
				int i = 0;
				if(s.equalsIgnoreCase(kitname)) {
					listKit.remove(i);
				}
			}
			kitConf.set("kit.listKit", listKit);
		}
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());

	}
	
	public Kit updateKit(Kit k) {
	
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
		
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());
		
		return kitAfter;
		
	}
	
	public Kit getKit(String kitname) {
		
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
			
			return kit;
		}
		
	}
	
	public List<Kit> getAllKit(){
		
		List<Kit> listKit = new ArrayList<Kit>();
		for(String s : kitConf.getStringList("kit.listKit")) {
			
			listKit.add(getKit(s));
		}
		return listKit;
	}

}
