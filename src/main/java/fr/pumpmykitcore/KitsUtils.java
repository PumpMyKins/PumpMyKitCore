package fr.pumpmykitcore;

import org.bukkit.configuration.file.FileConfiguration;

import fr.pumpmykitcore.ConfigUtils;

public class KitsUtils {
		
	FileConfiguration kitConf = ConfigUtils.getKitConf();
	
	public void createKit(Kit k) {
		
		String kitname = k.getKitname();
		kitConf.set("kit."+kitname+".eula", k.isEula());
		for(Item i : k.getItemList()) {
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".id", i.getId());
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".quantity", i.getQuantity());
			kitConf.set("kit."+kitname+".item."+i.getItemName()+".meta", i.getMeta());
		}
		
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());
	}
	
	public void deleteKit(String kitname) {
		
		kitConf.set("kit."+kitname, null);
		
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());

	}
	
	public Kit updateKit(String kitname) {
	
		FileConfiguration kitConf = ConfigUtils.getKitConf();
		Kit kit = new Kit();
		kit.setEula(kitConf.getBoolean("kit."+kitname+".eula"));
		
		return kit;
	}
}
