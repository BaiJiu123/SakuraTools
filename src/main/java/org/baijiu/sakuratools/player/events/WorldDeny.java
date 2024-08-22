package org.baijiu.sakuratools.player.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class WorldDeny implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        String Name = "§e" + player.getName();
        String Command = e.getMessage().toLowerCase();
        String CommandDenyMessage = "§c你不能在该世界执行逃逸指令!";
        if (!player.hasPermission("sakuratools.worlddeny.bypass") && Command.startsWith("/2") && player.getWorld().getName().equals("Mine")) {
            e.setCancelled(true);
            player.sendMessage(Name + CommandDenyMessage);
        }
        if (!player.hasPermission("sakuratools.worlddeny.bypass") && Command.startsWith("/p") && player.getWorld().getName().equals("Mine")) {
            e.setCancelled(true);
            player.sendMessage(Name + CommandDenyMessage);
        }
    }
}
