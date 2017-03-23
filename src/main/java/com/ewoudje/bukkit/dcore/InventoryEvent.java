package com.ewoudje.bukkit.dcore;

import com.ewoudje.bukkit.dcore.Handlers.InventoryDataHandler;
import com.ewoudje.bukkit.dcore.Handlers.NBTTagHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.Set;

/**
 * Created by ewoud_000 on 16/08/2016.
 */
public class InventoryEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (NBTTagHandler.getIdOnItem(e.getWhoClicked().getItemInHand()) != null) {
            CustomeItem ci = Main.customeitems.get(NBTTagHandler.getIdOnItem(e.getWhoClicked().getItemInHand()));
            if (ci instanceof InventoryHolding) {
                InventoryHolding item = (InventoryHolding) ci;
                e.setCancelled(item.isSlotProtected(e.getRawSlot(), (Player) e.getWhoClicked()));
                return;
            }
        }

        if (e.getWhoClicked().getTargetBlock((Set<Material>)null, 6).hasMetadata("customeblock") && e.getInventory().getHolder() == null) {
            CustomeBlock cb = Main.customeblocks.get(CustomeBlock.getTypeFormId(e.getWhoClicked().getTargetBlock((Set<Material>)null, 6).getMetadata("customeblock").get(0).asInt()));
            if (cb instanceof InventoryHolding) {
                InventoryHolding item = (InventoryHolding) cb;
                e.setCancelled(item.isSlotProtected(e.getRawSlot(), (Player) e.getWhoClicked()));
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getPlayer().getTargetBlock((Set<Material>)null, 6).hasMetadata("customeblock") && e.getInventory().getHolder() == null) {
            CustomeBlock cb = Main.customeblocks.get(CustomeBlock.getTypeFormId(e.getPlayer().getTargetBlock((Set<Material>)null, 6).getMetadata("customeblock").get(0).asInt()));
            if (cb instanceof InventoryHolding) {
                InventoryDataHandler.writeInvInData(e.getInventory(), new DataNode("CustomeBlock", 8, null), e.getPlayer().getTargetBlock((Set<Material>)null, 6).getMetadata("customeblock").get(0).asInt());
            }
        }
    }



}
