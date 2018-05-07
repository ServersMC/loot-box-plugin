package me.dennis.lootbox.objects;

import org.bukkit.command.CommandSender;

public abstract class LootCommand {
	
	public abstract void execute(CommandSender sender, String[] args);
	public abstract String getLabel();
	public abstract String getDescription();
	public abstract String getPermission();
	public abstract String getUsage();

}
