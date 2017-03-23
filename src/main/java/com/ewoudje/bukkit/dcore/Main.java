package com.ewoudje.bukkit.dcore;

import com.ewoudje.bukkit.dcore.Handlers.BuildingHandler;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ewoud_000 on 21/07/2016.
 */
public class Main extends JavaPlugin {
    public static ArrayList<Recipe> disabeledrecips = new ArrayList<>();
    public static ArrayList<CustomRecipe> customRecipes = new ArrayList<>();
    public static Map<String, ItemStack> giveitems = new HashMap<>();
    public static Map<Integer, CustomeItem> customeitems = new HashMap<>();
    public static Map<Integer, CustomeBlock> customeblocks = new HashMap<>();
    public static ArrayList<Player> lockedplayers = new ArrayList<>();
    public static BukkitPlugin bp = new BukkitPlugin("DCore");
    Main intsance = this;
    public static Main inst;

    @Override
    public void onEnable() {
        inst = intsance;
        Bukkit.getServer().getPluginManager().registerEvents(new CraftEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryEvent(), this);
        new BuildingHandler(this);
        CustomeItem.registerItem(7, new BlazeRod());
        CustomeBlock.registerMain(this);
        giveitems.put("wizardwand", new BlazeRod(new ItemStack(Material.TORCH)).item);
        //bp.normalConsolePrint("Loading Worlds");
        //World world = new WorldCreator("structure_world").createWorld();
        //world.setDifficulty(Difficulty.PEACEFUL);
        //world.setAnimalSpawnLimit(0);

        //bp.normalConsolePrint("Worlds Loaded");
        bp.normalBroadcast("DCore Enabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            /**
            if (label.equalsIgnoreCase("money"))
                bp.normalPersonalPrint(ChatColor.GOLD + "" + PlayerHandler.getMoneyFormPlayer(((Player) sender)) + " Gold", (Player) sender);
            if (label.equalsIgnoreCase("structureworld") && p.hasPermission(bp.getPermission("command", "worldteleport")))
                ((Player) sender).teleport(new Location(getServer().getWorld("structure_world"), 0, 0, 0));
            if (label.equalsIgnoreCase("normalworld")&& p.hasPermission(bp.getPermission("command", "worldteleport")))
                ((Player) sender).teleport(new Location(getServer().getWorld("world"), 0, 64, 0));

            if (label.equalsIgnoreCase("hello")) {
                Map<String, Material> map = new HashMap<String, Material>();
                map.put("s", Material.STONE);
                map.put("w", Material.WOOD);
                MultiStructure ms = new MultiStructure(map, "s/s,s/w", "s/w,s/s");
            }
            **/
            if (bp.runCommand(bp.getPermission("command", "cigive"), p, "cigive", label)) {
                if (args.length == 0) {
                    bp.errorPersonalPrint("Use /cigive <item>", p);
                    return true;
                }

                ((Player) sender).getInventory().addItem(ItemIds(args[0]));

            }
        }
        return true;
    }

    public ItemStack ItemIds(String id) {
        return giveitems.get(id) == null ? new BlazeRod(new ItemStack(Material.TORCH)).item : giveitems.get(id);
    }
}
