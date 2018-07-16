package me.dennis.lootbox.events;

import static org.bukkit.block.BlockFace.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.dennis.lootbox.enums.CrateType;
import me.dennis.lootbox.enums.LootType;
import me.dennis.lootbox.objects.CrateBlock;
import me.dennis.lootbox.objects.CustomItems;
import me.dennis.lootbox.objects.LootItem;
import me.dennis.lootbox.utils.Console;

public class CrateEvent implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {

		// Declarations
		Player player = event.getPlayer();
		Block block = event.getBlockPlaced();
		ItemStack item = player.getInventory().getItemInMainHand();
		String itemName = item.getItemMeta().getDisplayName() == null ? "" : item.getItemMeta().getDisplayName();

		// Checks if block placed is a chest
		if (!block.getType().equals(Material.CHEST)) {
			return;
		}

		// Checks if block placed is a crate
		if (!(itemName.equals(CustomItems.DONOR_CRATE_NAME) || itemName.equals(CustomItems.VOTER_CRATE_NAME))) {

			// Check for relative crates
			if (isRelativeCrates(block)) {
				event.getPlayer().sendMessage(ChatColor.RED + "Cannot place chest next to other crates");
				event.setCancelled(true);
			}

			return;
		}

		// Check if player is in survival
		if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
			player.sendMessage(ChatColor.RED + "You have to be in survival to do that!");
			event.setCancelled(true);
			return;
		}

		// Check for relative crates
		if (isRelativeChests(block)) {
			event.getPlayer().sendMessage(ChatColor.RED + "Cannot place loot crate next to other chests");
			event.setCancelled(true);
			return;
		}

		// Check which crate type
		CrateType type = null;
		if (itemName.equals(CustomItems.DONOR_CRATE_NAME)) {
			type = CrateType.DONATOR;
		} else if (itemName.equals(CustomItems.VOTER_CRATE_NAME)) {
			type = CrateType.VOTER;
		}

		// Register crate
		CrateBlock.logCrate(block.getLocation(), type, false);
	}

	private boolean isRelativeCrates(Block block) {
		for (BlockFace face : new BlockFace[] { NORTH, WEST, SOUTH, EAST }) {
			if (block.getRelative(face).getState() instanceof Chest) {
				Chest chest = (Chest) block.getRelative(face).getState();
				String name = chest.getInventory().getName();
				if (name.equals(CustomItems.DONOR_CRATE_NAME) || name.equals(CustomItems.VOTER_CRATE_NAME)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isRelativeChests(Block block) {
		for (BlockFace face : new BlockFace[] { NORTH, WEST, SOUTH, EAST }) {
			Material type = block.getRelative(face).getType();
			if (type.equals(Material.CHEST)) {
				return true;
			}
		}
		return false;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		// Check if action is clicked on block
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		
		// Declaration
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		ItemStack handItem = player.getInventory().getItemInMainHand();
		CrateBlock crate = CrateBlock.getCrate(block.getLocation());
		
		// Check if block is not a chest
		if (!block.getType().equals(Material.CHEST)) {
			return;
		}
		
		// Check if chest is not a crate
		if (!CrateBlock.containsLocation(block.getLocation())) {
			
			// Check if player is holding a key
			if (handItem.getType().equals(Material.TRIPWIRE_HOOK) && handItem.getItemMeta().getDisplayName().equals(CustomItems.KEY_NAME)) {

				// Notify player the problem
				player.sendMessage(ChatColor.RED + "You can only open a crate with this key");
				event.setCancelled(true);
				return;
				
			}
			
			return;
		}
		
		// Check if crate has been looted already
		if (crate.isLooted()) {
			return;
		}

		// Check if player is holding a tripwire
		if (!(handItem.getType().equals(Material.TRIPWIRE_HOOK) && handItem.getItemMeta().getDisplayName().equals(CustomItems.KEY_NAME))) {
			
			// Notify player the problem
			player.sendMessage(ChatColor.RED + "You need a key to open this chest!");
			event.setCancelled(true);
			return;
			
		}
		
		// Open Inventory FINISH
		if (handItem.getAmount() == 1) {
			player.getInventory().remove(handItem);
		}
		else {
			handItem.setAmount(handItem.getAmount() - 1);
			player.getInventory().setItemInMainHand(handItem);
		}
		List<ItemStack> crateLoot = new ArrayList<ItemStack>();
		crate.setLooted(true);
		
		for (int i = 0; i < crate.getType().getChestAmount(); i++) {
			if (crate.getType().equals(CrateType.DONATOR)) {
				if (i == 0) {
					ItemStack item = LootItem.random(LootType.RARE);
					if (item == null) {
						Console.warn("You do not have any " + LootType.RARE.name() + " items for a crate!");
					}
					crateLoot.add(item);
					continue;
				}
			}
			Random rand = new SecureRandom();
			Integer chance = (int) (rand.nextDouble() * 100d) + 1;
			for (LootType type : LootType.values()) {
				if (chance <= type.getChance()) {
					ItemStack item = LootItem.random(type);
					if (item == null) {
						Console.warn("You do not have any " + type.name() + " items for a crate!");
					}
					crateLoot.add(item);
					break;
				}
			}
		}
		
		for (int i = 0; i < crateLoot.size(); i++) {
			ItemStack item = crateLoot.get(i);
			((Chest) block.getState()).getBlockInventory().setItem(i, item);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {

		// Decleration
		Player player = event.getPlayer();
		Block block = event.getBlock();
		CrateBlock crate = CrateBlock.getCrate(block.getLocation());

		// Check if block is a chest
		if (!block.getType().equals(Material.CHEST)) {
			return;
		}

		// Declare chest display name
		String displayName = ((Chest) block.getState()).getInventory().getName();

		// Check if chest is a crate
		if (!CrateBlock.containsLocation(block.getLocation())) {
			return;
		}

		// Check if player is in creative
		if (player.getGameMode().equals(GameMode.CREATIVE)) {
			
			// Destroy Crate
			crate.destroy();

			// Check if looted
			if (crate.isLooted()) {
				return;
			}
			
			// Drop Crate
			if (displayName.equals(CustomItems.DONOR_CRATE_NAME)) {
				block.getWorld().dropItemNaturally(block.getLocation(), CustomItems.getDonatorCrate());
			} else if (displayName.equals(CustomItems.VOTER_CRATE_NAME)) {
				block.getWorld().dropItemNaturally(block.getLocation(), CustomItems.getVoterCrate());
			}

			return;
		}

		// Check if block has been looted
		if (crate.isLooted()) {

			// Change item drop to normal chest
			event.setDropItems(false);
			block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.CHEST));
			for (ItemStack item : ((Chest) block.getState()).getBlockInventory().getContents()) {
				if (item != null) {
					block.getWorld().dropItemNaturally(block.getLocation(), item);
				}
			}
			crate.destroy();

		}
		else {

			// Drop Crate
			event.setDropItems(false);
			if (displayName.equals(CustomItems.DONOR_CRATE_NAME)) {
				block.getWorld().dropItemNaturally(block.getLocation(), CustomItems.getDonatorCrate());
			} else if (displayName.equals(CustomItems.VOTER_CRATE_NAME)) {
				block.getWorld().dropItemNaturally(block.getLocation(), CustomItems.getVoterCrate());
			}
		}

	}

}
