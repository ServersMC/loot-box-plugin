package me.dennis.lootbox.objects;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import me.dennis.lootbox.core.LootBox;
import me.dennis.lootbox.enums.CrateType;
import me.dennis.lootbox.utils.Console;

public class CrateBlock {

	// STATIC //

	private static List<CrateBlock> crates;
	
	public static void init() {
		crates = new ArrayList<CrateBlock>();

		File file = new File(LootBox.PLUGIN.getDataFolder(), "crates.dat");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		for (String node : config.getKeys(false)) {
			Location location = (Location) config.get(node + ".location");
			CrateType type = CrateType.valueOf(config.getString(node + ".type"));
			Boolean looted = config.getBoolean(node + ".looted");
			crates.add(new CrateBlock(location, type, looted));	
		}
	}
	
	public static void save() {
		try {
			String data = "";
			YamlConfiguration yaml = new YamlConfiguration();

			Integer i = 0;
			for (CrateBlock crateBlock : crates) {
				yaml.set(i + ".location", crateBlock.location);
				yaml.set(i + ".type", crateBlock.type.name());
				yaml.set(i + ".looted", crateBlock.looted);
				i++;
			}

			data += yaml.saveToString();

			File file = new File(LootBox.PLUGIN.getDataFolder(), "crates.dat");
			PrintWriter writer = new PrintWriter(file);
			writer.print(data);
			writer.close();
		} catch (Exception e) {
			Console.catchError(e, "LootItem:save()");
		}
	}
	
	public static List<CrateBlock> getCrates() {
		return crates;
	}
	
	public static void logCrate(Location location, CrateType type, boolean looted) {
		crates.add(new CrateBlock(location, type, looted));
	}
	
	public static boolean containsLocation(Location location) {
		for (CrateBlock crate : crates) {
			if ((int) crate.getLocation().distance(location) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static CrateBlock getCrate(Location location) {
		for (CrateBlock crate : crates) {
			if ((int) crate.getLocation().distance(location) == 0) {
				return crate;
			}
		}
		return null;
	}
	
	// OBJECT //
	
	private Location location;
	private CrateType type;
	private boolean looted;
	
	public CrateBlock(Location location, CrateType type, boolean looted) {
		this.location = location;
		this.looted = looted;
		this.type = type;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public CrateType getType() {
		return type;
	}
	
	public boolean isLooted() {
		return looted;
	}
	
	public void setLooted(boolean b) {
		looted = b;
	}

	public void destroy() {
		crates.remove(this);
	}
	
}
