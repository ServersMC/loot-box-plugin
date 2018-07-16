package me.dennis.lootbox.events;

import static org.bukkit.ChatColor.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.dennis.lootbox.enums.LootType;
import me.dennis.lootbox.objects.CustomItems;
import me.dennis.lootbox.objects.LootItem;

public class ListCmdEvent implements Listener {

	private final int SLOT_PREV = 0;
	private final int SLOT_NEXT = 8;
	
	private final int SLOT_ALL = 2;
	private final int SLOT_COMMON = 3;
	private final int SLOT_RARE = 4;
	private final int SLOT_EPIC = 5;
	private final int SLOT_LEGENDARY = 6;
	
	private final int SLOT_ITEM_DISPLAY = 22;
	private final int SLOT_DELETE = 39;
	private final int SLOT_GRAB = 41;
	
	public static HashMap<Player, PageMeta> viewers = new HashMap<Player, PageMeta>();

	public static void initGUI(Player player) {
		viewers.put(player, new PageMeta(0, null));
		player.openInventory(Bukkit.createInventory(null, 54, RED + "LootBox"));
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		// Player init
		Player player = (Player) event.getPlayer();
		
		// Check if player is a viewer
		if (!viewers.containsKey(player)) {
			return;
		}
		
		// Render View
		renderList(event.getInventory(), null, viewers.get(player).page);
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		// Player init
		Player player = (Player) event.getPlayer();
		
		// Check if player is a viewer
		if (viewers.containsKey(player)) {
			
			// Remove player from memory list
			viewers.remove(player);
		}
	}
	
	@EventHandler
	public void onInventory(InventoryEvent event) {
		// Check if player is a viewer
		Bukkit.broadcastMessage(event.getEventName());
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		// Init
		Player player = (Player) event.getWhoClicked();
		Integer slot = event.getSlot();
		
		// Check if player is a viewer
		if (!viewers.containsKey(player)) {
			return;
		}
		
		// Cancel item movement
		event.setCancelled(true);

		// Ignore Outside Click
		if (slot == -999) {
			return;
		}
		
		// Ignore player inventory clicks
		if (event.getClickedInventory().getHolder() != null) {
			return;
		}
		
		// Prev Page Action
		if (slot == SLOT_PREV) {
			
			// Cancel if view is EDIT
			if (viewers.get(player).view.equals(PageView.EDIT)) {
				
				// Update PageMeta
				viewers.get(player).type = null;
				viewers.get(player).item = null;
				viewers.get(player).page = 0;
				viewers.get(player).view = PageView.LIST;
				
				// Render new view
				renderList(event.getInventory(), null, 0);
				
				return;
			}
			
			// Page limit check
			if (viewers.get(player).page == 0) {
				return;
			}
			
			// Render new view
			renderList(event.getInventory(), viewers.get(player).type, --viewers.get(player).page);
		}
		
		// Next Page Action
		if (slot == SLOT_NEXT) {
			
			// Cancel if view is EDIT
			if (viewers.get(player).view.equals(PageView.EDIT)) {
				return;
			}
			
			// LootItems list
			List<LootItem> items;
			if (viewers.get(player).type == null) {
				items = LootItem.getItems();
			}
			else {
				items = LootItem.getItems(viewers.get(player).type);
			}
			
			// Page limit check
			if (viewers.get(player).page == (int) Math.floor((double) items.size() / 36d)) {
				return;
			}
			
			// Render new view
			renderList(event.getInventory(), viewers.get(player).type, ++viewers.get(player).page);
		}
		
		// All Filter Action
		if (slot == SLOT_ALL) {
			
			// Cancel if view is EDIT
			if (viewers.get(player).view.equals(PageView.EDIT)) {
				return;
			}
			
			// Update PageMeta
			viewers.get(player).type = null;
			viewers.get(player).page = 0;
			
			// Render new view
			renderList(event.getInventory(), null, 0);
		}
		
		// Common Filter Action
		if (slot == SLOT_COMMON) {
			
			// Check if PageView is EDIT
			if (viewers.get(player).view.equals(PageView.EDIT)) {
				// Change LootType on LootItem
				viewers.get(player).item.type = LootType.COMMON;
				
				// Render new view
				renderEdit(event.getInventory(), viewers.get(player).item);
				
				// Cancel LIST action
				return;
			}
			
			// Update PageMeta
			viewers.get(player).type = LootType.COMMON;
			viewers.get(player).page = 0;
			
			// Render new view
			renderList(event.getInventory(), LootType.COMMON, 0);
		}
		
		// Common Filter Action
		if (slot == SLOT_RARE) {
			
			// Check if PageView is EDIT
			if (viewers.get(player).view.equals(PageView.EDIT)) {
				// Change LootType on LootItem
				viewers.get(player).item.type = LootType.RARE;
				
				// Render new view
				renderEdit(event.getInventory(), viewers.get(player).item);
				
				// Cancel LIST action
				return;
			}
			
			// Update PageMeta
			viewers.get(player).type = LootType.RARE;
			viewers.get(player).page = 0;
			
			// Render new view
			renderList(event.getInventory(), LootType.RARE, 0);
		}
		
		// Common Filter Action
		if (slot == SLOT_EPIC) {
			
			// Check if PageView is EDIT
			if (viewers.get(player).view.equals(PageView.EDIT)) {
				// Change LootType on LootItem
				viewers.get(player).item.type = LootType.EPIC;
				
				// Render new view
				renderEdit(event.getInventory(), viewers.get(player).item);
				
				// Cancel LIST action
				return;
			}
			
			// Update PageMeta
			viewers.get(player).type = LootType.EPIC;
			viewers.get(player).page = 0;
			
			// Render new view
			renderList(event.getInventory(), LootType.EPIC, 0);
		}
		
		// Common Filter Action
		if (slot == SLOT_LEGENDARY) {
			
			// Check if PageView is EDIT
			if (viewers.get(player).view.equals(PageView.EDIT)) {
				// Change LootType on LootItem
				viewers.get(player).item.type = LootType.LEGENDARY;
				
				// Render new view
				renderEdit(event.getInventory(), viewers.get(player).item);
				
				// Cancel LIST action
				return;
			}
			
			// Update PageMeta
			viewers.get(player).type = LootType.LEGENDARY;
			viewers.get(player).page = 0;
			
			// Render new view
			renderList(event.getInventory(), LootType.LEGENDARY, 0);
		}
		
		if (slot == SLOT_DELETE) {
			
			// Cancel if view is LIST
			if (viewers.get(player).view.equals(PageView.LIST)) {
				return;
			}
			
			// Delete Item
			LootItem.getItems().remove(viewers.get(player).item);
			
			// Update PageMeta
			viewers.get(player).type = null;
			viewers.get(player).item = null;
			viewers.get(player).page = 0;
			viewers.get(player).view = PageView.LIST;
			
			// Render new view
			renderList(event.getInventory(), null, 0);
			return;
		}
		
		if (slot == SLOT_GRAB) {
			
			// Cancel if view is LIST
			if (viewers.get(player).view.equals(PageView.LIST)) {
				return;
			}
			
			// Send to player inventory
			player.getInventory().addItem(viewers.get(player).item.item);
			return;
		}

		// Item Edit Action
		if (slot >= 18 && slot <= 53) {
			
			// Cancel if view is EDIT
			if (viewers.get(player).view.equals(PageView.EDIT)) {
				return;
			}
			
			// Check if theres air
			if (event.getInventory().getItem(slot).getType().equals(Material.AIR)) {
				return;
			}
			
			// Grab LootItem
			LootType type = viewers.get(player).type;
			Integer page = viewers.get(player).page;
			List<LootItem> items;
			if (type == null) {
				items = LootItem.getItems();
			}
			else {
				items = LootItem.getItems(type);
			}
			LootItem item = items.get((slot - 18) + (36 * page));
			
			// Render Edit Page
			viewers.get(player).view = PageView.EDIT;
			viewers.get(player).item = item;
			renderEdit(event.getInventory(), item);
		}
	}
	
	private void renderEdit(Inventory inv, LootItem item) {
		// Clear GUI
		inv.clear();
		
		// Decleration
		LootType type = item.type;
		
		// Add Page Type Selection
		inv.setItem(SLOT_ALL, CustomItems.getEditTypeInfo());
		inv.setItem(SLOT_COMMON, CustomItems.getCommonTypes(type == LootType.COMMON));
		inv.setItem(SLOT_RARE, CustomItems.getRareTypes(type == LootType.RARE));
		inv.setItem(SLOT_EPIC, CustomItems.getEpicTypes(type == LootType.EPIC));
		inv.setItem(SLOT_LEGENDARY, CustomItems.getLegendaryTypes(type == LootType.LEGENDARY));
		
		// Display Loot Item
		inv.setItem(SLOT_ITEM_DISPLAY, item.item);
		
		// Action Button
		inv.setItem(SLOT_DELETE, CustomItems.getDeleteItem());
		inv.setItem(SLOT_GRAB, CustomItems.getGiveItem());
	}
	
	private void renderList(Inventory inv, LootType type, Integer page) {
		// Clear GUI
		inv.clear();
		
		// LootItems list
		List<LootItem> items;
		if (type == null) {
			items = LootItem.getItems();
		}
		else {
			items = LootItem.getItems(type);
		}
		
		// Decleration
		Integer pageMax = (int) Math.ceil((double) items.size() / 36d);
		
		// Add Page Navigation
		inv.setItem(SLOT_PREV, CustomItems.getPrevPage(page + 1, pageMax));
		inv.setItem(SLOT_NEXT, CustomItems.getNextPage(page + 1, pageMax));
		
		// Add Page Type Selection
		inv.setItem(SLOT_ALL, CustomItems.getAllTypes(type == null));
		inv.setItem(SLOT_COMMON, CustomItems.getCommonTypes(type == LootType.COMMON));
		inv.setItem(SLOT_RARE, CustomItems.getRareTypes(type == LootType.RARE));
		inv.setItem(SLOT_EPIC, CustomItems.getEpicTypes(type == LootType.EPIC));
		inv.setItem(SLOT_LEGENDARY, CustomItems.getLegendaryTypes(type == LootType.LEGENDARY));
		
		// Add all items
		Integer slot = 18;
		for (int i = page * 36; i < (page + 1) * 36; i++) {
			try {
				LootItem lootItem = items.get(i);
				ItemStack item = lootItem.item.clone();
				ItemMeta meta = item.getItemMeta();
				List<String> lore = new ArrayList<String>();
				lore.add(lootItem.type.getColor() + BOLD + lootItem.type.name());
				lore.add(RED + "ID: " + GRAY + Integer.toString(lootItem.getId()));
				lore.add(GRAY + "{Click for more}");
				meta.setLore(lore);
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				item.setItemMeta(meta);
				inv.setItem(slot, item);
			} catch (Exception e) {}
			slot++;
		}
	}
	
}
class PageMeta {

	public Integer page = 0;
	public LootType type = null;
	public PageView view = PageView.LIST;
	public LootItem item = null;
	
	public PageMeta(Integer page, LootType type) {
		this.page = page;
		this.type = type;
	}
	
}
enum PageView {
	LIST, EDIT;
}
