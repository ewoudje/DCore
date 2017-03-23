package com.ewoudje.bukkit.dcore;

import com.ewoudje.bukkit.dcore.Handlers.InventoryDataHandler;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by ewoud_000 on 21/07/2016.
 */
public final class BlockEvent implements Listener {
    public static Map<Integer, ItemStack> ores = new HashMap<>();
    public static Map<Integer, ItemStack> ores2 = new HashMap<>();
    public static Map<Integer, Boolean> rechange = new HashMap<>();
    public static Map<Integer, Integer> oreschange = new HashMap<>();
    public static Map<Material, Map<Integer, Permission>> ids = new HashMap<>();

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event){
        event.setCancelled(event.getBlock().hasMetadata("protected"));
    }

    @EventHandler
    public void onBlockBreaks(BlockBreakEvent e) {
        e.setCancelled(e.getBlock().hasMetadata("protected"));
        if (e.getBlock().hasMetadata("customeblock")) {
            if (Main.customeblocks.get(CustomeBlock.getTypeFormId(e.getBlock().getMetadata("customeblock").get(0).asInt())) instanceof InventoryHolding) {
                Inventory inv = InventoryDataHandler.getInvFormData(new DataNode("CustomeBlock", 8, null), e.getBlock().getMetadata("customeblock").get(0).asInt());
                for (int i = 0; i < inv.getSize(); i++) {
                    if (inv.getItem(i) != null) e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), inv.getItem(i));
                }
                CustomeBlock.destroyCustomeBlock(e.getBlock().getMetadata("customeblock").get(0).asInt());
                e.getBlock().removeMetadata("customeblock", Main.inst);
            }
        }
        if (ids.get(e.getBlock().getType()) != null) {
            Map<Integer , Permission> materialmap = ids.get(e.getBlock().getType());
            Set<Integer> gettedids = materialmap.keySet();
            int id = 0;
            for (int i = 0; i < gettedids.size(); i++) {
                id = gettedids.toArray(new Integer[gettedids.size()])[i];
                if (!e.getPlayer().hasPermission(materialmap.get(id)) &&  materialmap.get(id) != null) return;
            }
            ItemStack m = ores.get(id);
            if (ores2.get(id) != null) {
                ItemStack m2 = ores2.get(id);
                int i = new Random().nextInt(oreschange.get(id));
                if (i == 1) {
                    i = 0;//e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.getByName("fortune"));
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), m2);
                    e.getPlayer().getItemInHand().setDurability((short) 1);
                } else {
                    i = 0;//e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.getByName("fortune"));
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), m);
                    e.getPlayer().getItemInHand().setDurability((short) 1);
                }
            } else {
                if (rechange.get(id)) {
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), m);
                    e.getPlayer().getItemInHand().setDurability((short) 1);
                    return;
                }
                int i = new Random().nextInt(oreschange.get(id));
                if (i == 1) {
                    i = 0;//e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.getByName("fortune"));
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), m);
                    e.getPlayer().getItemInHand().setDurability((short) 1);
                }
            }
        }

        /**
        if ((ores.get(idse.getBlock().getType()) != null && permission.get(e.getBlock().getType()) == null) || ores.get(e.getBlock().getType()) != null && PermissionHandler.hasPlayerPermission(e.getPlayer(), permission.get(e.getBlock().getType()))) {
            ItemStack m = ores.get(e.getBlock().getType());
            if (ores2.get(e.getBlock().getType()) != null) {
                ItemStack m2 = ores2.get(e.getBlock().getType());
                int i = new Random().nextInt(oreschange.get(e.getBlock().getType()));
                if (i == 1) {
                    i = 0;//e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.getByName("fortune"));
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), m2);
                } else {
                    i = 0;//e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.getByName("fortune"));
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), m);
                }
            } else {
                int i = new Random().nextInt(oreschange.get(e.getBlock().getType()));
                if (i == 1 || rechange.get(e.getBlock().getType())) {
                    i = 0;//e.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.getByName("fortune"));
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), m);
                }
            }
        }
         */
    }


}