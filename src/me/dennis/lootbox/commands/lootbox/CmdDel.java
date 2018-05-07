package me.dennis.lootbox.commands.lootbox;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import me.dennis.lootbox.objects.LootCommand;
import me.dennis.lootbox.objects.LootItem;

public class CmdDel extends LootCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length != 2) {
			sender.sendMessage(ChatColor.RED + getUsage());
			return;
		}
		
		Integer id = 0;
		try {
			id = Integer.parseInt(args[1]);
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Please enter a number!");
			return;
		}
		
		if (id >= LootItem.getItems().size()) {
			sender.sendMessage(ChatColor.RED + "That id does not exist! Type: /lootbox list");
			return;
		}
		
		LootItem lootItem = LootItem.getItems().get(id);
		ItemStack item = new ItemStack(lootItem.item.getType(), lootItem.item.getAmount());
		LootItem.getItems().remove(lootItem);
		
		sender.sendMessage(ChatColor.GREEN + item.toString().replace("ItemStack", "") + " has been removed from the loot list!");
	}

	@Override
	public String getLabel() {
		return "DEL";
	}

	@Override
	public String getDescription() {
		return "Delete a loot item from list.";
	}

	@Override
	public String getPermission() {
		return "lootbox.del";
	}

	@Override
	public String getUsage() {
		return "/lootbox del <id>";
	}

}
