package com.ewoudje.bukkit.dcore;

import com.ewoudje.bukkit.dcore.Handlers.NBTTagHandler;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ewoud_000 on 15/08/2016.
 */
public abstract class CustomeItem {
    private int id;
    private boolean canright = false;
    private boolean canleft = false;
    public ItemStack item;

    private static DataNode node = new DataNode("CustomeItem", 8, null);


    public CustomeItem(int id, boolean canright, boolean canleft, ItemStack item) {
        this.id = id;
        this.canright = canright;
        this.canleft = canleft;
        FileConfiguration fc = node.getData();
        int idk = fc.getInt("IDs", 0);
        idk++;
        fc.set("IDs", idk);
        fc.set(idk + ".SortId", id);
        this.item = NBTTagHandler.addIdOnItem(idk, item);
        node.saveNode();
    }

    public CustomeItem(int id, boolean canleft, boolean canright) {
        this.id = id;
        this.canleft = canleft;
        this.canright = canright;
    }

    public int getId() {
        return id;
    }

    public boolean isCanright() {
        return canright;
    }

    public boolean isCanleft() {
        return canleft;
    }

    public abstract void onRightClick(PlayerInteractEvent e);

    public abstract void onLeftClick(PlayerInteractEvent e);

    public static void registerItem(int id, CustomeItem item) {
        Main.customeitems.put(id, item);
    }

    public static void registerItem(CustomeItem item) {
        Main.customeitems.put(item.getId(), item);
    }

    public static int getTypeFormId(int id) {
        return node.getData().getInt(id + ".SortId");
    }

    public static void destroyCustomeItem(int id) {
        node.getData().set(id + "", null);
        node.saveNode();
    }

}
