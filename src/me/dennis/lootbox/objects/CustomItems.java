package me.dennis.lootbox.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems extends ItemStack {
	
	public static final String KEY_NAME = ChatColor.AQUA + "Crate Key";
	public static final String VOTER_CRATE_NAME = ChatColor.AQUA + "Voter Loot Crate";
	public static final String DONOR_CRATE_NAME = ChatColor.AQUA + "Donator Loot Crate";
	
	public static ItemStack getKey() {
		// Create ItemStack
		ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta meta = item.getItemMeta();
		
		// Set Item display name
		meta.setDisplayName(KEY_NAME);
		
		// Set Item lore
		List<String> lore = new ArrayList<String>();
		lore.add(" ");
		lore.add(ChatColor.LIGHT_PURPLE + "Use this key to open any type of crate!");
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
		lore.add(ChatColor.LIGHT_PURPLE + "Thank you for voting!");
		lore.add(ChatColor.LIGHT_PURPLE + "Place this chest and open it with your key!");
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
		lore.add(ChatColor.LIGHT_PURPLE + "Thank you for supporting the server!");
		lore.add(ChatColor.LIGHT_PURPLE + "Place this chest and open it with your key!");
		meta.setLore(lore);
		
		// Add enchantment glow
		meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
		
		// Hide Enchantment names
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		// Update ItemStack Meta
		item.setItemMeta(meta);

		return item;
	}
	
}
