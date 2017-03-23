package com.ewoudje.bukkit.dcore;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by ewoud_000 on 16/08/2016.
 */
public abstract class CustomeBlock {
    private Block block;
    private static Main m;
    private int id;
    private boolean canright = false;
    private boolean canleft = false;

    private static DataNode node = new DataNode("CustomeBlock", 8, null);
    public CustomeBlock(Block block, int id, boolean canleft, boolean canright) {
        this.block = block;
        this.id = id;
        this.canleft = canleft;
        this.canright = canright;
        FileConfiguration fc = node.getData();
        int idk = fc.getInt("IDs", 0);
        idk++;
        fc.set("IDs", idk);
        fc.set(idk + ".SortId", id);
        node.saveNode();
        block.setMetadata("customeblock", new FixedMetadataValue(m, idk));
    }

    public CustomeBlock(int id, boolean canleft, boolean canright) {
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

    public abstract void onRightClick(PlayerInteractEvent e, int i);

    public abstract void onLeftClick(PlayerInteractEvent e, int i);


    public static void registerMain(Main main) {
        m = main;
    }

    public void registerData(String path, String value) {
        node.getData().set(block.getMetadata("customeblock") + path, value);
        node.saveNode();
    }

    public Object getData(String path) {
        return node.getData().get(block.getMetadata("customeblock") + path);
    }

    public static void registerBlock(int id, CustomeBlock block) {
        Main.customeblocks.put(id, block);
    }

    public static void registerBlock(CustomeBlock block) {
        registerBlock(block.getId(), block);
    }

    public static int getTypeFormId(int id) {
        return node.getData().getInt(id + ".SortId");
    }

    public static void destroyCustomeBlock(int id) {
        node.getData().set(id + "", null);
        node.saveNode();
    }
}
