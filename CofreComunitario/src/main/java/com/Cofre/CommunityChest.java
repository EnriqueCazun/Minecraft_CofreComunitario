package com.Cofre;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class CommunityChest extends JavaPlugin {

    private Inventory communityChest;
    
    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        communityChest = Bukkit.createInventory(null, 54, "Cofre Comunitario");
        loadChest();
        getLogger().info("Plugin de Cofre Comunitario activado!");
    }

    @Override
    public void onDisable() {
    	saveChest();
        getLogger().info("Plugin de Cofre Comunitario desactivado!");
    }
    
    private void saveChest() {
        try {
            File file = new File(getDataFolder(), "chest.yml");
            YamlConfiguration config = new YamlConfiguration();
            
            config.set("chest", communityChest.getContents());
            
            config.save(file);
        } catch (IOException e) {
            getLogger().severe("¡Error al guardar el cofre comunitario!");
            e.printStackTrace();
        }
    }

    private void loadChest() {
        File file = new File(getDataFolder(), "chest.yml");
        if (file.exists()) {
            try {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                
                List<ItemStack> items = (List<ItemStack>) config.getList("chest");
                
                if (items != null) {
                    communityChest.setContents(items.toArray(new ItemStack[0]));
                }
            } catch (Exception e) {
                getLogger().severe("¡Error al cargar el cofre comunitario!");
                e.printStackTrace();
            }
        }
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
