package com.ewoudje.bukkit.dcore;

import org.bukkit.Material;

import java.util.Map;

/**
 * Created by ewoud on 22/09/2016.
 * Note //
 */
public class MultiStructure {
    Material[] blocks;
    private static DataNode data = new DataNode("MultiStructure", 9, null);

    public MultiStructure(Map<String, Material> mean, String... template) {
        int x, y, z;
        y = template.length;
        x = template[0].split(",").length;
        z = template[0].split(",")[0].split("/").length;
        Material[] blocks = new Material[x * y * z];
        int i = 0;
        for (int ay = 0; ay < y; ay++) {
            for (int ax =0; ax < x; ax++) {
                for (int az = 0; az < z; az++) {
                    blocks[i] = mean.get(template[ay].split(",")[ax].split("/")[az]);
                    i++;
                }
            }
        }
        this.blocks = blocks;
    }

}
