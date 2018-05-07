package me.dennis.lootbox.enums;

public enum ItemType {

	VOTER("Gives a 50/50 chance of a key or a voter chest."),
	DONATOR("Gives a key and a donator chest."),
	KEY("Gives a key"),
	VCHEST("Gives a voter chest."),
	DCHEST("Gives a donator chest.");
	
	private String description;

	public static ItemType getType(String name) {
		try {
			return valueOf(name.toUpperCase());
		} catch (Exception e) {
			return null;
		}
	}
	
	private ItemType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
