package com.ewoudje.bukkit.dcore;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ewoud on 26/10/2016.
 * Note //
 */
public class BasicItem extends CustomeItem {

    public BasicItem(int id, ItemStack item) {
        super(id, false, false, item);
    }

    public BasicItem(int id) {
        super(id, false, false);
    }

    @Override
    public void onRightClick(PlayerInteractEvent e) {

    }

    @Override
    public void onLeftClick(PlayerInteractEvent e) {

    }
}
