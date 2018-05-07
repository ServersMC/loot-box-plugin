package me.dennis.lootbox.commands.lootbox;

import static org.bukkit.ChatColor.*;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import me.dennis.lootbox.enums.LootType;
import me.dennis.lootbox.objects.LootCommand;
import me.dennis.lootbox.objects.LootItem;

public class CmdList extends LootCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		List<LootItem> items = null;
		Integer page = 0;

		switch (args.length) {
		case 1:

			items = LootItem.getItems();
			break;

		case 2:

			try {
				page = Integer.parseInt(args[1]) - 1;
				items = LootItem.getItems();
			} catch (Exception e) {
				LootType type = LootType.getType(args[1]);
				if (type == null) {
					sender.sendMessage(RED + "Loot type not found! Type: /lootbox types");
					return;
				}
				items = LootItem.getItems(type);
			}
			break;

		case 3:

			LootType type = LootType.getType(args[1]);
			if (type == null) {
				sender.sendMessage(RED + "Loot type not found! Type: /lootbox types");
				return;
			}
			items = LootItem.getItems(type);
			try {
				page = Integer.parseInt(args[2]) - 1;
			} catch (Exception e) {
				sender.sendMessage(RED + "Please enter a number for page!");
				return;
			}
			break;

		default:
			sender.sendMessage(RED + getUsage());
			return;
		}

		for (int i = page * 10; i < (page + 1) * 10; i++) {
			try {
				LootItem lootItem = items.get(i);
				String typeName = lootItem.type.name();
				String id = Integer.toString(lootItem.getId());
				ItemStack item = new ItemStack(lootItem.item.getType(), lootItem.item.getAmount());
				sender.sendMessage(GRAY + typeName + " - " + id + " - " + RED + item.toString().replaceAll("ItemStack", ""));
			} catch (Exception e) {}
		}
		
		sender.sendMessage(RED + "Page " + GRAY + (page + 1) + RED + " / " + GRAY + (int) Math.ceil((double) items.size() / 10d));
	}

	@Override
	public String getLabel() {
		return "LIST";
	}

	@Override
	public String getDescription() {
		return "Lists all loot items";
	}

	@Override
	public String getPermission() {
		return "lootbox.list";
	}

	@Override
	public String getUsage() {
		return "/lootbox list <loot_type> <page>";
	}

}
