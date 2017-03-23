package com.ewoudje.bukkit.dcore;

import com.ewoudje.bukkit.dcore.Handlers.NBTTagHandler;
import com.ewoudje.bukkit.dcore.Handlers.PlayerHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Date;

/**
 * Created by ewoud_000 on 21/07/2016.
 */
public final class PlayerEvent implements Listener {

    /*@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        FileConfiguration fc = PlayerHandler.node.getData();
        String string = fc.getString(e.getPlayer().getUniqueId() + ".FirstJoin");
        if (string == null) {
            fc.set(e.getPlayer().getUniqueId() + ".FirstJoin", new Date());
            PlayerHandler.node.saveNode();
        }
    }*/

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        e.setCancelled(Main.lockedplayers.contains(e.getPlayer()));
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {


        if (NBTTagHandler.getIdOnItem(e.getItem()) != null) {

            CustomeItem ci = Main.customeitems.get(CustomeItem.getTypeFormId(NBTTagHandler.getIdOnItem(e.getItem())));
            if (ci == null) return;
            e.setCancelled(ci.isCanleft() && (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)));
            e.setCancelled(ci.isCanright() && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)));
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
                ci.onRightClick(e);
            else ci.onLeftClick(e);

        }

        if (e.getClickedBlock() != null) {
            if (e.getClickedBlock().hasMetadata("customeblock")) {
                CustomeBlock ci = Main.customeblocks.get(CustomeBlock.getTypeFormId(e.getClickedBlock().getMetadata("customeblock").get(0).asInt()));
                if (ci == null) {
                    return;
                }
                e.setCancelled(ci.isCanleft() && (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)));
                e.setCancelled(ci.isCanright() && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)));
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
                    ci.onRightClick(e, e.getClickedBlock().getMetadata("customeblock").get(0).asInt());
                else ci.onLeftClick(e, e.getClickedBlock().getMetadata("customeblock").get(0).asInt());
            }
        }
    }

}