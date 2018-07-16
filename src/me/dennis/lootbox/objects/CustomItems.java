package me.dennis.lootbox.objects;

import static org.bukkit.ChatColor.*;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems extends ItemStack {
	
	public static final String KEY_NAME = AQUA + "Crate Key";
	public static final String VOTER_CRATE_NAME = AQUA + "Voter Loot Crate";
	public static final String DONOR_CRATE_NAME = AQUA + "Donator Loot Crate";
	public static final String PREV_PAGE = AQUA + "Prev Page";
	public static final String NEXT_PAGE = AQUA + "Next Page";
	public static final String EDIT_TYPE_INFO = AQUA + "Info for Editing";
	public static final String ALL_TYPES = "All Items";
	public static final String COMMON_TYPES = "Common Items";
	public static final String RARE_TYPES = "Rare Items";
	public static final String EPIC_TYPES = "Epic Items";
	public static final String LEGENDARY_TYPES = "Legendary Items";
	public static final String DELETE_ITEM = RED + "" + BOLD + "Remove";
	public static final String GIVE_ITEM = GREEN + "Grab Item";
	
	public static ItemStack getKey() {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(KEY_NAME);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(LIGHT_PURPLE + "Use this key to open any type of crate!");
		meta.setLore(lore);
		
		// Add enchantment glow
		meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getVoterCrate() {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(VOTER_CRATE_NAME);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(LIGHT_PURPLE + "Thank you for voting!");
		lore.add(LIGHT_PURPLE + "Place this chest and open it with your key!");
		meta.setLore(lore);
		
		// Add enchantment glow
		meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);
		
		return item;
	}

	public static ItemStack getDonatorCrate() {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(DONOR_CRATE_NAME);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(LIGHT_PURPLE + "Thank you for supporting the server!");
		lore.add(LIGHT_PURPLE + "Place this chest and open it with your key!");
		meta.setLore(lore);
		
		// Add enchantment glow
		meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}
	
	public static ItemStack getPrevPage(Integer pageNum, Integer pageTotal) {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(PREV_PAGE);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(LIGHT_PURPLE + "Flips to the previous page.");
		lore.add(LIGHT_PURPLE + "Page: " + pageNum + " / " + pageTotal);
		meta.setLore(lore);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}
	
	public static ItemStack getNextPage(Integer pageNum, Integer pageTotal) {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(NEXT_PAGE);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(LIGHT_PURPLE + "Flips to the next page.");
		lore.add(LIGHT_PURPLE + "Page: " + pageNum + " / " + pageTotal);
		meta.setLore(lore);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getEditTypeInfo() {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(EDIT_TYPE_INFO);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(LIGHT_PURPLE + "Click on a Loot Type on the");
		lore.add(LIGHT_PURPLE + "right to change the current");
		lore.add(LIGHT_PURPLE + "items loot type.");
		meta.setLore(lore);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getAllTypes(Boolean selected) {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.SNOW_BALL);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(YELLOW + "" + (selected ? BOLD : "") + ALL_TYPES);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add((selected ? (LIGHT_PURPLE + "" + BOLD + " SELECTED") : " "));
		lore.add(LIGHT_PURPLE + "Filters the list to show");
		lore.add(LIGHT_PURPLE + "all loot items.");
		meta.setLore(lore);
		
		// Add enchantment glow
		if (selected) {
			meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		}
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getCommonTypes(Boolean selected) {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.COAL);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(DARK_GRAY + "" + (selected ? BOLD : "") + COMMON_TYPES);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add((selected ? (LIGHT_PURPLE + "" + BOLD + " SELECTED") : " "));
		lore.add(LIGHT_PURPLE + "Filters the list to show");
		lore.add(LIGHT_PURPLE + "all common items.");
		meta.setLore(lore);
		
		// Add enchantment glow
		if (selected) {
			meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		}
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getRareTypes(Boolean selected) {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(GREEN + "" + (selected ? BOLD : "") + RARE_TYPES);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add((selected ? (LIGHT_PURPLE + "" + BOLD + " SELECTED") : " "));
		lore.add(LIGHT_PURPLE + "Filters the list to show");
		lore.add(LIGHT_PURPLE + "all rare items.");
		meta.setLore(lore);
		
		// Add enchantment glow
		if (selected) {
			meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		}
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getEpicTypes(Boolean selected) {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.GOLD_INGOT);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(GOLD + "" + (selected ? BOLD : "") + EPIC_TYPES);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add((selected ? (LIGHT_PURPLE + "" + BOLD + " SELECTED") : " "));
		lore.add(LIGHT_PURPLE + "Filters the list to show");
		lore.add(LIGHT_PURPLE + "all epic items.");
		meta.setLore(lore);
		
		// Add enchantment glow
		if (selected) {
			meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		}
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getLegendaryTypes(Boolean selected) {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.DIAMOND);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(LIGHT_PURPLE + "" + (selected ? BOLD : "") + LEGENDARY_TYPES);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add((selected ? (LIGHT_PURPLE + "" + BOLD + " SELECTED") : " "));
		lore.add(LIGHT_PURPLE + "Filters the list to show");
		lore.add(LIGHT_PURPLE + "all legendary items.");
		meta.setLore(lore);
		
		// Add enchantment glow
		if (selected) {
			meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		}
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getDeleteItem() {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(DELETE_ITEM);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(LIGHT_PURPLE + "Delete this item from");
		lore.add(LIGHT_PURPLE + "the LootList?");
		meta.setLore(lore);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}

	public static ItemStack getGiveItem() {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.CHEST);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(GIVE_ITEM);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(LIGHT_PURPLE + "Sends this item to your");
		lore.add(LIGHT_PURPLE + "inventory.");
		meta.setLore(lore);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}
	
}
