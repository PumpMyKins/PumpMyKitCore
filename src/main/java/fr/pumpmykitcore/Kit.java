package fr.pumpmykitcore;

import java.util.ArrayList;
import java.util.List;

import fr.pumpmykitcore.Item;

public class Kit {

	private String kitname = null;
	private boolean eula = true;
	private List<Item> itemList= new ArrayList<Item>();
	
	public String getKitname() {
		return kitname;
	}
	public void setKitname(String kitname) {
		this.kitname = kitname;
	}
	public boolean isEula() {
		return eula;
	}
	public void setEula(boolean eula) {
		this.eula = eula;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	} 
	
	
}
