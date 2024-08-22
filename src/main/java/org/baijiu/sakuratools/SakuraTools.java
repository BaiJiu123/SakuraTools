package org.baijiu.sakuratools;

import me.yic.xconomy.api.XConomyAPI;
import me.yic.xconomy.data.syncdata.PlayerData;
import org.baijiu.sakuratools.player.events.DenyDrop;
import org.baijiu.sakuratools.player.events.Join;
import org.baijiu.sakuratools.player.events.Quit;
import org.baijiu.sakuratools.player.events.WorldDeny;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public final class SakuraTools extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§8[§f❀§8] §bSakura§fTools §aLoad!");
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Quit(), this);
        Bukkit.getPluginManager().registerEvents(new WorldDeny(), this);
        Bukkit.getPluginManager().registerEvents(new DenyDrop(), this);


    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8[§f❀§8] §bSakura§fTools §aClosed!");
    }

    private boolean itemIsEmpty(ItemStack item) {
        return item == null || item.getType() == Material.AIR;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String playerName = sender.getName();
        XConomyAPI xcapi = new XConomyAPI();
        BigDecimal Balance;
        PlayerData PlayerData = xcapi.getPlayerData(player.getUniqueId());
        Balance = PlayerData.getBalance();
        BigDecimal amount = BigDecimal.valueOf(500);

        if (label.equalsIgnoreCase("gm") && player.hasPermission("sakuratools.admin")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("1")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage("§8[§f❀§8] §7已将您的游戏模式设置为 §e创造");
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("0")) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("§8[§f❀§8] §7已将您的游戏模式设置为 §e生存");
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("3")) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("§8[§f❀§8] §7已将您的游戏模式设置为 §e旁观");
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("2")) {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage("§8[§f❀§8] §7已将您的游戏模式设置为 §e冒险");
                return true;
            } else {
                player.sendMessage("§8[§f❀§8] §c用法: /gm < 0 | 1 | 2 | 3 >");
                return false;
            }

        }

        if (label.equalsIgnoreCase("fix") && Balance.compareTo(amount) > 0) {
            ItemStack itemInHand = player.getItemInHand();

            if (itemIsEmpty(itemInHand)) {
                player.sendMessage("§8[§f❀§8] §7你手上没有任何物品!");
                return true;
            }

            if (itemInHand.getDurability() != (short) 0) {
                itemInHand.setDurability((short) 0);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + playerName + " 500");
                Bukkit.getConsoleSender().sendMessage("§8[§f❀§8] §7触发修复事件, 已扣除 §f" + playerName + " §7的 §e500 §7金币");
                player.sendMessage("§8[§f❀§8] §7修复成功!");
            } else if (itemInHand.getDurability() == (short) 0) {
                player.sendMessage("§8[§f❀§8] §7该物品无需修复!");
            }
            return true;
        } else if (Balance.compareTo(amount) < 0) {
            player.sendMessage("§8[§f❀§8] §7你没有足够的货币来修复!");
            return true;
        }

        return false;
    }
}