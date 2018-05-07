package me.dennis.lootbox.utils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import me.dennis.lootbox.core.LootBox;

public class Config {

	public static YamlConfiguration CONFIG;
	public static File CONFIG_FILE;
	
	public static void init() {
		CONFIG_FILE = new File(LootBox.PLUGIN.getDataFolder(), "config.yml");
	}
	
	public static void loadConfig() {
		CONFIG = YamlConfiguration.loadConfiguration(CONFIG_FILE);
	}
	
	public static Integer getInteger(String node) {
		return CONFIG.getInt(node);
	}
	
}
