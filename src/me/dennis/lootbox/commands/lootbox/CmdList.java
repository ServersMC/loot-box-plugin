package me.dennis.lootbox.commands.lootbox;

import static org.bukkit.ChatColor.*;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.dennis.lootbox.events.ListCmdEvent;
import me.dennis.lootbox.objects.LootCommand;

public class CmdList extends LootCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		// Check if console is sender
		if (sender instanceof ConsoleCommandSender) {
			sender.sendMessage(RED + "This is a player only command!");
			return;
		}
		
		// Declerations
		Player player = (Player) sender;
		
		// Add player to viewer memory
		ListCmdEvent.initGUI(player);
	}

	@Override
	public String getLabel() {
		return "LIST";
	}

	@Override
	public String getDescription() {
		return "Lists all loot items.";
	}

	@Override
	public String getPermission() {
		return "lootbox.list";
	}

	@Override
	public String getUsage() {
		return "/lootbox list";
	}

}
