package fr.pumpmykitcore.command;

import java.util.List;

import fr.pumpmykitcore.config.ConfigUtils;
import fr.pumpmykitcore.obj.Kit;
import fr.pumpmykitcore.utils.KitsUtils;

public class CommandHandler {

	
	@SuppressWarnings("unlikely-arg-type")
	public static boolean createKit(Kit k, String p) {
		
		if(!KitsUtils.getAllKit().contains(k.getKitname())) {
			KitsUtils.createKit(k);
			if(KitsUtils.getAllKit().contains(k.getKitname())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static boolean deleteKit(String k) {
		
		KitsUtils.deleteKit(k);
		if(KitsUtils.getAllKit().contains(k)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static List<Kit> kitList() {
		
		return KitsUtils.getAllKit();
	}
	
	public static Kit randomKit() {
		
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
