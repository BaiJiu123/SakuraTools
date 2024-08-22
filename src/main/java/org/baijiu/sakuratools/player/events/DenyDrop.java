package org.baijiu.sakuratools.player.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class DenyDrop implements Listener {
    private final String UNDROPABLE_LORE = "无法丢弃";

    @EventHandler
    public boolean onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack item = event.getItemDrop().getItemStack();
        if (isUndropable(item)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§c该物品无法被丢弃！");
            return true;
        }
        return false;
    }

    @EventHandler
    public boolean onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        ClickType clicktype = event.getClick();
        if (isUndropable(item)) {
            Inventory clickedInventory = event.getClickedInventory();
            if (clickedInventory != null && clickedInventory.getType() == InventoryType.PLAYER && event.getWhoClicked().getWorld().getName().equals("Plot")) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage("§c该物品无法在该世界被操作！");
                return true;
            }
            if (clicktype.isKeyboardClick() && clickedInventory != null && event.getWhoClicked().getWorld().getName().equals("Plot")) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage("§c该物品无法在该世界被操作！");
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public boolean onPlayerDeath(PlayerDeathEvent event) {
        List<ItemStack> drops = event.getDrops();
        drops.removeIf(this::isUndropable);
        return true;
    }

    private boolean isUndropable(ItemStack item) {
        if (item == null) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasLore()) return false;
        List<String> lore = meta.getLore();
        return lore != null && lore.stream().anyMatch(line -> line.contains(UNDROPABLE_LORE));
    }
}
