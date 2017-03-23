package com.ewoudje.bukkit.dcore.Handlers;

import com.ewoudje.bukkit.dcore.Main;
import com.ewoudje.bukkit.dcore.UpdateBuildingBlock;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ewoud_000 on 22/07/2016.
 */
public class BuildingHandler {
    public static ArrayList<Location> protectedchunks = new ArrayList<>();
    public static JavaPlugin m;

    public BuildingHandler(Main m) {
        this.m = m;
    }

    public boolean isProtected(Location c) {
        return  protectedchunks.contains(c);
    }

    public int buildBuildingAt(int id, int subid, Chunk c, int heigth)  {
        List<Block> skiplist = new ArrayList<>();
        List<Material> droplist = new ArrayList<>();
        droplist.add(Material.CROPS);

        Block[] b = getBuilding(subid);
        int o = 0;
        int i = 0;
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    i++;
                    Block bl = c.getWorld().getBlockAt(new Location(c.getWorld(), (c.getX() * 16) + x, heigth + y, (c.getZ() * 16) + z));
                    if (skiplist.contains(bl)) continue;
                    if (b[i].getType() == Material.AIR) o++;
                    if (b[i].getType() != Material.AIR) bl.setMetadata("protected", new FixedMetadataValue(m, id + "/" + subid));
                    //if (i == 452) {
                        //bl.setType(Material.ENCHANTMENT_TABLE);
                        //UpdateBuildingBlock.registerItem(new UpdateBuildingBlock(bl, 1));
                        //continue;
                    //}
                    if (bl.getRelative(BlockFace.UP).getType() == Material.CROPS) skiplist.add(bl.getRelative(BlockFace.UP));
                    if (droplist.contains(bl.getType())) bl.getWorld().dropItemNaturally(bl.getLocation(), (ItemStack) bl.getDrops().toArray()[0]);
                    BlockDataHandler.setBlockDataOnBlock(b[i], bl);
                }
            }
            if (o == 256) {
                heigth = y;
                break;
            }
            o = 0;
        }
        return heigth;
    }


    public Block[] getBuilding(int id) {
        Block[] b = new Block[50*16*16 + 1];
        int i = 0;
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    i++;
                    if (id < 0) b[i] = m.getServer().getWorld("structure_world").getBlockAt(new Location(m.getServer().getWorld("structure_world"), x, 4 + y, -(((-id - 1)* 16) + z) - 1));
                    if (id > 0) b[i] = m.getServer().getWorld("structure_world").getBlockAt(new Location(m.getServer().getWorld("structure_world"), 16 + x, 4 + y, -(((id - 1)* 16) + z) - 1));
                }
            }
        }
        return b;
    }
}
