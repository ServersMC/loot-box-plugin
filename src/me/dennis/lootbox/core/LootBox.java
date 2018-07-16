package me.dennis.lootbox.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.dennis.lootbox.commands.CmdLootbox;
import me.dennis.lootbox.enums.CrateType;
import me.dennis.lootbox.enums.LootType;
import me.dennis.lootbox.events.CrateEvent;
import me.dennis.lootbox.events.ListCmdEvent;
import me.dennis.lootbox.objects.CrateBlock;
import me.dennis.lootbox.objects.LootItem;
import me.dennis.lootbox.utils.Config;
import me.dennis.lootbox.utils.Console;
import me.dennis.lootbox.utils.FileSystem;

public class LootBox extends JavaPlugin {

	public static LootBox PLUGIN;
	
	@Override
	public void onEnable() {
		// Initializer
		PLUGIN = this;
		Config.init(); // Initializes CONFIG_FILE
		FileSystem.init(); // Creates folders and files
		Config.loadConfig(); // Loads CONFIG_FILE as CONFIG
		LootType.loadConfigValues(); // Loads LOOT_CHANCE config values
		CrateType.loadConfigValues(); // Loads CRATE_AMOUNT config valuces
		CrateBlock.init(); // Loads placed chests
		LootItem.init(); // Loads loot items
		
		// Commands
		getCommand("lootbox").setExecutor(new CmdLootbox());
		CmdLootbox.setupSubCommands();
		
		// Listeners
		Bukkit.getPluginManager().registerEvents(new CrateEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ListCmdEvent(), this);
		
		// Done
		Console.info("Done!");
	}
	
	@Override
	public void onDisable() {
		// Save data files
		LootItem.save();
		CrateBlock.save();
		
		// Done
		Console.info("Disabled!");
	}
	
}
