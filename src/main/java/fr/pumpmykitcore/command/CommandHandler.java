package fr.pumpmykitcore.command;

import java.util.List;

import fr.pumpmykitcore.config.ConfigUtils;
import fr.pumpmykitcore.obj.Kit;
import fr.pumpmykitcore.utils.KitsUtils;

public class CommandHandler {

	@SuppressWarnings("unlikely-arg-type")
	public boolean createKit(Kit k, String p) {
		
		KitsUtils.createKit(k);
		if(KitsUtils.getAllKit().contains(k.getKitname())) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean deleteKit(String k) {
		
		KitsUtils.deleteKit(k);
		if(KitsUtils.getAllKit().contains(k)) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Kit> kitList() {
		
		return KitsUtils.getAllKit();
	}
	
	public Kit randomKit() {
		
		Kit n = null;
		if(ConfigUtils.getConfigFile().getBoolean("AllowRandom")) {
			List<Kit> allKit = KitsUtils.getAllKit();
			int i = allKit.size();
			int z = 0 + (int)(Math.random() * ((i+ 1)));
			Kit k = allKit.get(z);
		
			return k;
		}
		return n;
	}
	
	
}
