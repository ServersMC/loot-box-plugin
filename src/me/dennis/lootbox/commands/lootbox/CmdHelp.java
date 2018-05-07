package me.dennis.lootbox.commands.lootbox;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.dennis.lootbox.commands.CmdLootbox;
import me.dennis.lootbox.objects.LootCommand;

public class CmdHelp extends LootCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		List<LootCommand> commands = new ArrayList<LootCommand>();
		for (LootCommand command : CmdLootbox.commands) {
			if (sender.hasPermission(command.getPermission())) {
				commands.add(command);
			}
		}
		if (!commands.isEmpty()) {
			for (LootCommand command : commands) {
				sender.sendMessage(ChatColor.GRAY + command.getUsage() + " - " + ChatColor.RED + command.getDescription());
			}
		}
		else {
			sender.hasPermission(ChatColor.RED + "You don't have permmision to any subcommands!");
		}
	}

	@Override
	public String getLabel() {
		return "HELP";
	}

	@Override
	public String getDescription() {
		return "Shows the lootbox help page.";
	}

	@Override
	public String getPermission() {
		return "lootbox.help";
	}

	@Override
	public String getUsage() {
		return "/lootbox help";
	}

}
