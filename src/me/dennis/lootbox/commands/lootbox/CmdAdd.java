package me.dennis.lootbox.commands.lootbox;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.dennis.lootbox.enums.LootType;
import me.dennis.lootbox.objects.LootCommand;
import me.dennis.lootbox.objects.LootItem;

public class CmdAdd extends LootCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		// Check if sender is console
		if (sender instanceof ConsoleCommandSender) {
			sender.sendMessage(ChatColor.RED + "This is a player only command!");
			return;
		}

		// Check if argument length meets requirements
		if (args.length != 2) {
			sender.sendMessage(ChatColor.RED + "Usage: " + getUsage());
			return;
		}
		
		// Initialize
		Player player = (Player) sender;
		LootType type = LootType.getType(args[1]);
		ItemStack item = player.getInventory().getItemInMainHand();
		
		// Check if loot type matches
		if (type == null) {
			player.sendMessage(ChatColor.RED + "Loot type not found! Type: /lootbox types");
			return;
		}

		// Check for inventory item
		if (item.getType().equals(Material.AIR)) {
			player.sendMessage(ChatColor.RED + "You are not holding anything!");
			return;
		}
		
		// Add item to loot list
		LootItem.addItem(item, type);
		player.sendMessage(ChatColor.GREEN + "Item successfully added to the " + ChatColor.WHITE + type.name() + ChatColor.GREEN + " list!");
	}

	@Override
	public String getLabel() {
		return "ADD";
	}

	@Override
	public String getDescription() {
		return "Add a loot item to list";
	}

	@Override
	public String getPermission() {
		return "lootbox.add";
	}

	@Override
	public String getUsage() {
		return "/lootbox add <loot_type>";
	}

}
