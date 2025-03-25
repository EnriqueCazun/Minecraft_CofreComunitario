package com.Cofre;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class CommunityChest extends JavaPlugin {

    private Inventory communityChest;

    @Override
    public void onEnable() {
        communityChest = Bukkit.createInventory(null, 54, "Cofre Comunitario");
        getLogger().info("Plugin de Cofre Comunitario activado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin de Cofre Comunitario desactivado!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cc")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Solo los jugadores pueden usar este comando.");
                return true;
            }
            Player player = (Player) sender;
            player.openInventory(communityChest);
            return true;
        }
        return false;
    }
}