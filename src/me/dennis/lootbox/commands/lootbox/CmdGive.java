package me.dennis.lootbox.commands.lootbox;

import java.security.SecureRandom;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.dennis.lootbox.enums.ItemType;
import me.dennis.lootbox.objects.CustomItems;
import me.dennis.lootbox.objects.LootCommand;
import net.md_5.bungee.api.ChatColor;

public class CmdGive extends LootCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		// Check if argument length meets requirements
		if (args.length != 3) {
			sender.sendMessage(ChatColor.RED + "Usage: " + getUsage());
			return;
		}

		// Initialize
		Player player = Bukkit.getPlayer(args[1]);
		ItemType type = ItemType.getType(args[2]);

		// Check if player is not found
		if (player == null) {
			sender.sendMessage(ChatColor.RED + "Player not found!");
			return;
		}

		// Check if chest type exists
		if (type == null) {
			sender.sendMessage(ChatColor.RED + "Item type not found! Type: /lootbox types");
			return;
		}
		
		// Give items acording to type
		switch (type) {
		case DCHEST:
			player.getInventory().addItem(CustomItems.getDonatorCrate());
			break;
		case DONATOR:
			player.getInventory().addItem(CustomItems.getDonatorCrate());
			player.getInventory().addItem(CustomItems.getKey());
			break;
		case KEY:
			player.getInventory().addItem(CustomItems.getKey());
			break;
		case VCHEST:
			player.getInventory().addItem(CustomItems.getVoterCrate());
			break;
		case VOTER:
			Random rand = new SecureRandom();
			Integer i = (int) (rand.nextDouble() * 100d);
			if (i < 50) {
				player.getInventory().addItem(CustomItems.getKey());
			}
			else {
				player.getInventory().addItem(CustomItems.getVoterCrate());
			}
		}
		
		// Finalize message
		sender.sendMessage(ChatColor.GREEN + "Done!");
	}

	@Override
	public String getLabel() {
		return "GIVE";
	}

	@Override
	public String getDescription() {
		return "Gives player a LootBox item(s).";
	}

	@Override
	public String getPermission() {
		return "lootbox.give";
	}

	@Override
	public String getUsage() {
		return "/lootbox give <player> <chest_type>";
	}

}
