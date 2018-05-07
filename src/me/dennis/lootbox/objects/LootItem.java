package me.dennis.lootbox.objects;

import java.io.File;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import me.dennis.lootbox.core.LootBox;
import me.dennis.lootbox.enums.LootType;
import me.dennis.lootbox.utils.Console;

public class LootItem {

	// STATIC //

	private static List<LootItem> items;

	public static void init() {
		items = new ArrayList<LootItem>();

		File file = new File(LootBox.PLUGIN.getDataFolder(), "loot.dat");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		for (String node : config.getKeys(false)) {
			ItemStack item = (ItemStack) config.get(node + ".item");
			LootType type = LootType.valueOf(config.getString(node + ".type"));
			addItem(item, type);	
		}
	}

	public static void save() {
		try {
			String data = "";
			YamlConfiguration yaml = new YamlConfiguration();

			Integer i = 0;
			for (LootItem lootItem : items) {
				yaml.set(i + ".item", lootItem.item);
				yaml.set(i + ".type", lootItem.type.name());
				i++;
			}

			data += yaml.saveToString();

			File file = new File(LootBox.PLUGIN.getDataFolder(), "loot.dat");
			PrintWriter writer = new PrintWriter(file);
			writer.print(data);
			writer.close();
		} catch (Exception e) {
			Console.catchError(e, "LootItem:save()");
		}
	}

	public static ItemStack random(LootType type) {
		Random rand = new SecureRandom();
		Integer chance = (int) (rand.nextDouble() * LootItem.getItems(type).size());
		return new ItemStack(getItems(type).get(chance).item);
	}
	
	public static void addItem(ItemStack item, LootType type) {
		items.add(new LootItem(item, type));
	}

	public static List<LootItem> getItems() {
		return items;
	}
	
	public static List<LootItem> getItems(LootType type) {
		List<LootItem> typeList = new ArrayList<LootItem>();
		for (LootItem item : items) {
			if (item.type.equals(type)) {
				typeList.add(item);
			}
		}
		return typeList;
	}

	// OBJECT //

	public ItemStack item;
	public LootType type;

	public LootItem(ItemStack item, LootType type) {
		this.item = item;
		this.type = type;
	}

	public Integer getId() {
		return items.indexOf(this);
	}

}
