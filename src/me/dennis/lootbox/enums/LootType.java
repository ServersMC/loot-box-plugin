package me.dennis.lootbox.enums;

import me.dennis.lootbox.utils.Config;

public enum LootType {

	LEGENDARY,
	EPIC,
	RARE,
	COMMON;
	
	private Integer chance;
	
	public static void loadConfigValues() {
		for (LootType type : values()) {
			if (type.equals(COMMON)) {
				COMMON.chance = 100;
				continue;
			}
			type.chance = Config.getInteger("loot_chance." + type.name().toLowerCase());
		}
	}
	
	public static LootType getType(String name) {
		try {
			return valueOf(name.toUpperCase());
		} catch (Exception e) {
			return null;
		}
	}
	
	public Integer getChance() {
		return chance;
	}
	
}
