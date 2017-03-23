package com.ewoudje.bukkit.dcore;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by ewoud_000 on 16/08/2016.
 */
public class UpdateBuildingBlock extends CustomeBlock implements InventoryHolding {

    private Inventory inv;

    public UpdateBuildingBlock(Block block, int id) {
        super(block, id, false, true);
        inv = Bukkit.createInventory(null, InventoryType.CHEST);
    }


    @Override
    public boolean isSlotProtected(int i, Player p) {
        return 3 < i;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    @Override
    public void onRightClick(PlayerInteractEvent e, int i) {

    }

    @Override
    public void onLeftClick(PlayerInteractEvent e, int i) {

    }
}
