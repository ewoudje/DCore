package com.ewoudje.bukkit.dcore;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

import java.util.HashMap;
import java.util.Map;

import static com.ewoudje.bukkit.dcore.BlockEvent.*;

/**
 * Created by ewoud_000 on 21/07/2016.
 */
public class OreChange {
    private static int idcounter = 0;

    public static void setOreDrop(int change, ItemStack drop1, ItemStack drop2, Material ore, Permission permission) {
        idcounter++;
        if (ids.get(ore) != null) {
            Map<Integer, Permission> map = ids.get(ore);
            map.put(idcounter, permission);
            ids.remove(ore);
            ids.put(ore, map);
            ores.put(idcounter, drop1);
            ores2.put(idcounter, drop2);
            boolean b = change == 0;
            oreschange.put(idcounter, change);
            rechange.put(idcounter, b);
        } else {
            Map<Integer, Permission> map = new HashMap<>();
            map.put(idcounter, permission);
            ids.put(ore, map);
            ores.put(idcounter, drop1);
            ores2.put(idcounter, drop2);
            boolean b = change == 0;
            oreschange.put(idcounter, change);
            rechange.put(idcounter, b);
        }
    }

    public static void setOreDrop(int change, ItemStack drop, Material ore, Permission permission) {
        setOreDrop(change, drop, null, ore, permission);
    }

}
