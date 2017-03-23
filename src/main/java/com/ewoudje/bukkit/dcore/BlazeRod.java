package com.ewoudje.bukkit.dcore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by ewoud on 17/09/2016.
 * Note //
 */
public class BlazeRod extends CustomeItem {


    public BlazeRod() {
        super(7, true, false);
    }

    public BlazeRod(ItemStack item) {
        super(7, true, false, item);
    }

    @Override
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getHelmet() != null && e.getPlayer().getInventory().getHelmet().getType() == Material.PUMPKIN) {
            e.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
        } else e.getPlayer().getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
    }

    @Override
    public void onLeftClick(PlayerInteractEvent e) {
        e.getPlayer().launchProjectile(Fireball.class);
    }
}
