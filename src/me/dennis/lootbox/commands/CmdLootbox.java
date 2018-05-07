package me.dennis.lootbox.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.dennis.lootbox.commands.lootbox.CmdAdd;
import me.dennis.lootbox.commands.lootbox.CmdDel;
import me.dennis.lootbox.commands.lootbox.CmdGive;
import me.dennis.lootbox.commands.lootbox.CmdHelp;
import me.dennis.lootbox.commands.lootbox.CmdList;
import me.dennis.lootbox.commands.lootbox.CmdTypes;
import me.dennis.lootbox.core.LootBox;
import me.dennis.lootbox.objects.LootCommand;

public class CmdLootbox implements CommandExecutor {

	public static List<LootCommand> commands;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(ChatColor.RED + "LootBox " + ChatColor.GRAY + "- v" + LootBox.PLUGIN.getDescription().getVersion());
		if (args.length == 0) {
			commands.get(0).execute(sender, args);
		}
		else {
			LootCommand target = null;
			for (LootCommand command : commands) {
				if (command.getLabel().equalsIgnoreCase(args[0])) {
					target = command;
					break;
				}
			}
			if (target != null) {
				if (target.getPermission() != null) {
					if (!sender.hasPermission(target.getPermission())) {
						sender.sendMessage(ChatColor.RED + "You don't have permission to use this subcommand!");
						return true;
					}
				}
				target.execute(sender, args);
			}
			else {
				sender.sendMessage(ChatColor.RED + "Subcommand not found! Type /lootbox help");
			}
		}
		return true;
	}
	
	public static void setupSubCommands() {
		commands = new ArrayList<LootCommand>();
		commands.add(new CmdHelp());
		commands.add(new CmdGive());
		commands.add(new CmdTypes());
		commands.add(new CmdAdd());
		commands.add(new CmdDel());
		commands.add(new CmdList());
	}

}
