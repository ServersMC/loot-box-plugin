package me.dennis.lootbox.enums;

import me.dennis.lootbox.utils.Config;

public enum CrateType {

	VOTER, DONATOR;

	private Integer chestAmount;
	
	public static void loadConfigValues() {
		for (CrateType type : values()) {
			type.chestAmount = Config.getInteger("chest_amount." + type.name().toLowerCase());
		}
	}
	
	public Integer getChestAmount() {
		return chestAmount;
	}
	
}
