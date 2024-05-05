package org.baijiu.sakuratools.player.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;


public class Death implements Listener {
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        String playerName = player.getName();
        event.setDeathMessage(null);

        Random r = new Random();
        int MS = r.nextInt(3);
        if (MS == 0) {
            Bukkit.broadcastMessage("§8[§f❀§8] §7可怜的 §f" + playerName + " §7离开了人世!");
        } else if (MS == 1) {
            Bukkit.broadcastMessage("§8[§f❀§8] §f" + playerName + " §7完成了他的使命...");
        } else if (MS == 2) {
            Bukkit.broadcastMessage("§8[§f❀§8] §f" + playerName + " §7与世界说了再见~");
        }
    }
}
