package fr.pumpmykitcore.utils;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import fr.pumpmykitcore.config.ConfigUtils;
import fr.pumpmykitcore.obj.Item;
import fr.pumpmykitcore.obj.Kit;

public class KitsUtils {
		
	static FileConfiguration kitConf = ConfigUtils.getKitConf();
	
	static FileConfiguration buyConf = ConfigUtils.getBuyConf();
	
	public static void createKit(Kit k) {
		
		if(!kitConf.getStringList("kit.listKit").contains(k.getKitname())) {
			Utils.debug(k); //DEBUG
			String kitname = k.getKitname();
			if(ConfigUtils.getConfigFile().getBoolean("force.eula")) {
				k.setEula(true);
			}
			kitConf.set("kit."+kitname+".eula", k.isEula());
			kitConf.set("kit."+kitname+".itemName", k.getItemNameList());
			kitConf.set("kit."+kitname+".xp", k.getXp());
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
		} else {
			Utils.error("Kit Already Exist");
		}
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
		} else {
			Utils.error("Kit doesn't exist");
		}
		ConfigUtils.update(kitConf, ConfigUtils.getKitFile());

	}
	
	public Kit updateKit(Kit k) {
	
		Utils.debug(k); //DEBUG
		String kitname = k.getKitname();
		Kit kitAfter = k;
		if(ConfigUtils.getConfigFile().getBoolean("force.eula")) {
			k.setEula(true);
		}
		kitConf.set("kit."+kitname+".eula", k.isEula());
		kitConf.set("kit."+kitname+".itemName", k.getItemNameList());
		kitConf.set("kit."+kitname+".xp", k.getXp());
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
			kit.setKitname(kitname);
			kit.setXp(ConfigUtils.getKitConf().getInt("kit."+kitname+".xp"));
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
			if(ConfigUtils.getConfigFile().getBoolean("force.eula")) {
				kit.setEula(true);
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
	
	public static void kitBuyLog(String buyerUuid, String kitname, Timestamp date, String idPurchase) {
		
		List<String> kitbuylist = buyConf.getStringList("kitbuylist");
		kitbuylist.add(idPurchase);
		buyConf.set("kitbuylist", kitbuylist);
		buyConf.set("kit."+idPurchase+".buyer", buyerUuid);
		buyConf.set("kit."+idPurchase+".kitname", kitname);
		buyConf.set("kit."+idPurchase+".date", date.toString());
		List<String> takenby = new ArrayList<String>();
		buyConf.set("kit."+idPurchase+".takenby", takenby);
		
		ConfigUtils.update(buyConf, ConfigUtils.getBuyFile());
		
	}
	
	public static int getNumberOfUse(String uuid, String kitname) {

		int usability = 0;
		for(String purchaseid : buyConf.getStringList("kitbuylist")) {
			if(buyConf.getString("kit."+purchaseid+".kitname").equals(kitname) && !buyConf.getStringList("kit."+purchaseid+".takenby").contains(uuid)) {
				Timestamp date = Timestamp.valueOf(buyConf.getString("kit."+purchaseid+".date"));
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(date.getTime());
				cal.add(Calendar.HOUR, 2);
				date = new Timestamp(cal.getTime().getTime());
				if(date.before(new Timestamp(System.currentTimeMillis()))) {
					if(getKit(kitname).isEula()) {
						if(buyConf.getString("kit."+purchaseid+".buyer").equals(uuid)) {
							usability++;
						}
					} else {
						usability++;
					}
				}
			}
		}
		return usability;
	}
	
	public static Boolean useKit(String uuid, String kitname) {
		
		for(String purchaseid : buyConf.getStringList("kitbuylist")) {
			if(buyConf.getString("kit."+purchaseid+".kitname").equals(kitname) && !buyConf.getStringList("kit."+purchaseid+".takenby").contains(uuid)) {
				if(getKit(kitname).isEula()) {
					if(buyConf.getString("kit."+purchaseid+".buyer").equals(uuid)) {
						Timestamp date = Timestamp.valueOf(buyConf.getString("kit."+purchaseid+".date"));
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(date.getTime());
						cal.add(Calendar.HOUR, 2);
						date = new Timestamp(cal.getTime().getTime());
						if(date.before(new Timestamp(System.currentTimeMillis()))) {
							
							List<String> kitbuylist = buyConf.getStringList("kit."+purchaseid+".kitbuylist");
							kitbuylist.add(uuid);
							buyConf.set("kit."+purchaseid+".takenby", kitbuylist);
							ConfigUtils.update(buyConf, ConfigUtils.getBuyFile());
							return true;
						}
					}
				} else {
					Timestamp date = Timestamp.valueOf(buyConf.getString("kit."+purchaseid+".date"));
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis(date.getTime());
					cal.add(Calendar.HOUR, 2);
					date = new Timestamp(cal.getTime().getTime());
					if(date.before(new Timestamp(System.currentTimeMillis()))) {
						
						List<String> kitbuylist = buyConf.getStringList("kit."+purchaseid+".kitbuylist");
						kitbuylist.add(uuid);
						buyConf.set("kit."+purchaseid+".takenby", kitbuylist);
						ConfigUtils.update(buyConf, ConfigUtils.getBuyFile());
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static Boolean canUse(String uuid, String kitname) {
		
		if(getNumberOfUse(uuid, kitname) > 0) {
			return true;
		} else {
			return false;
		}
	}

}
