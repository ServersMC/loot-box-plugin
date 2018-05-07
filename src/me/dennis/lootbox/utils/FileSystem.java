package me.dennis.lootbox.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import me.dennis.lootbox.core.LootBox;

public class FileSystem {

	public static void init() {
		pluginFolder();
		fileSetup();
		configFile();
	}
	
	private static void pluginFolder() {
		File folder = LootBox.PLUGIN.getDataFolder();
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
	
	private static void fileSetup() {
		List<String> files = new ArrayList<String>();
		files.add("crates.dat");
		files.add("loot.dat");
		for (String fileName : files) {
			File file = new File(LootBox.PLUGIN.getDataFolder(), fileName);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					Console.catchError(e, "FileSystem:fileSetup()");
				}
			}
		}
	}
	
	private static void configFile() {
		if (!Config.CONFIG_FILE.exists()) {
			try {
				Config.CONFIG_FILE.createNewFile();
			} catch (IOException e) {
				Console.catchError(e, "FileSystem:configFile().create_file");
			}
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(LootBox.PLUGIN.getResource("config.yml")));
			PrintWriter writer = new PrintWriter(Config.CONFIG_FILE);
			
			String line;
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
			
			writer.close();
			reader.close();
		} catch (Exception e) {
			Console.catchError(e, "FileSystem:configFile().write_to_file");
		}
	}
	
}
