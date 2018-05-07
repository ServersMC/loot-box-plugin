package me.dennis.lootbox.commands.lootbox;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.dennis.lootbox.enums.ItemType;
import me.dennis.lootbox.enums.LootType;
import me.dennis.lootbox.objects.LootCommand;

public class CmdTypes extends LootCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(ChatColor.RED + "\tChest Types");
		for (ItemType type : ItemType.values()) {
			sender.sendMessage(ChatColor.GRAY + type.name() + " - " + ChatColor.RED + type.getDescription());
		}
		sender.sendMessage(ChatColor.RED + "\tLoot Types");
		for (LootType type : LootType.values()) {
			sender.sendMessage(ChatColor.GRAY + type.name() + " - " + ChatColor.RED + type.getChance() + "%");
		}
	}

	@Override
	public String getLabel() {
		return "TYPES";
	}

	@Override
	public String getDescription() {
		return "Lists the types of LootBox items.";
	}

	@Override
	public String getPermission() {
		return "lootbox.types";
	}

	@Override
	public String getUsage() {
		return "/lootbox types";
	}

}
