package me.dennis.lootbox.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Console {
    
    public static void info(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[LootBox] " + s);
    }
    
    public static void warn(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[LootBox] " + s);
    }
    
    public static void err(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[LootBox] " + s);
    }
    
    public static void consoleSendMessage(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }

	public static void catchError(Exception e, String loc) {
		// Error exception catch
		err("There was an error in " + loc + ".");
		consoleSendMessage(e.toString());
		for (StackTraceElement elem : e.getStackTrace()) {
			if (elem.toString().startsWith("me.dennis.lootbox")) {
				consoleSendMessage("\t" + elem.toString());
			}
		}
		err("End of error");
	}
    
}
