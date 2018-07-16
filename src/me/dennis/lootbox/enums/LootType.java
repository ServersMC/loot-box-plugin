package me.dennis.lootbox.enums;

import org.bukkit.ChatColor;

import me.dennis.lootbox.utils.Config;

public enum LootType {

	LEGENDARY(ChatColor.LIGHT_PURPLE),
	EPIC(ChatColor.GOLD),
	RARE(ChatColor.GREEN),
	COMMON(ChatColor.DARK_GRAY);
	
	private Integer chance;
	private ChatColor color;
	
	LootType(ChatColor color) {
		this.color = color;
	}
	
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
	
	public String getColor() {
		return color.toString();
	}
	
}
