package com.ewoudje.bukkit.dcore;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by ewoud_000 on 16/08/2016.
 */
public interface InventoryHolding {

    //if you wanna add some thing when u click it just do it here it wil run it
    boolean isSlotProtected(int i, Player p);

    Inventory getInventory();
}
