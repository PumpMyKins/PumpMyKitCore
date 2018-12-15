package fr.pumpmykitcore.obj;

import java.util.ArrayList;
import java.util.List;

import fr.pumpmykitcore.obj.Item;

public class Kit {

	private String kitname = null;
	private boolean eula = true;
	private List<String> itemNameList = new ArrayList<String>();
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
	public List<String> getItemNameList() {
		return itemNameList;
	}
	public void setItemNameList(List<String> itemNameList) {
		this.itemNameList = itemNameList;
	} 
	
	
}
