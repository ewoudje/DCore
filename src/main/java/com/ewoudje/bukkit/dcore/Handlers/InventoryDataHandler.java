package com.ewoudje.bukkit.dcore.Handlers;

import com.ewoudje.bukkit.dcore.DataNode;
import com.ewoudje.bukkit.dcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ewoud_000 on 16/08/2016.
 */
public class InventoryDataHandler {

    public static void writeInvInData(Inventory inv, DataNode node, int id) {
        FileConfiguration fc = node.getData();
        fc.set(id + ".inv-name", inv.getName());
        fc.set(id + ".inv-sort", inv.getType().toString());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) continue;
            if (NBTTagHandler.getIdOnItem(inv.getItem(i)) !=  null) {
                String string = NBTTagHandler.getIdOnItem(inv.getItem(i)) + "/" + i;
                list.add(string + "/" + inv.getItem(i).getAmount() + "/");
            } else {
                String a = inv.getItem(i).getType().toString() + "/";
                String b = inv.getItem(i).getAmount() + "/";
                String c = inv.getItem(i).getData().getData() + "/" + i;
                list.add(a + b + c);
            }
        }
        fc.set(id + ".inv", list);
        node.saveNode();
    }

    public static Inventory getInvFormData(DataNode node, int id) {
        FileConfiguration fc = node.getData();
        List<String> list = fc.getStringList(id + ".inv");
        Inventory inv = Bukkit.getServer().createInventory(null, InventoryType.valueOf(fc.getString(id + ".inv-sort")), fc.getString(id + ".inv-name"));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).endsWith("/")) {
                String[] strings = list.get(i).split("/");
                ItemStack item = Main.customeitems.get(Integer.parseInt(strings[0])).item;
                item.setAmount(Integer.parseInt(strings[2]));
                inv.setItem(Integer.parseInt(strings[1]), item);
            } else {
                String[] string = list.get(i).split("/");
                ItemStack item = new ItemStack(Material.getMaterial(string[0]), Integer.parseInt(string[1]));
                item.setData(new MaterialData(Material.getMaterial(string[0]), Byte.parseByte(string[2])));
                inv.setItem(Integer.parseInt(string[3]), item);
            }
        }
        node.saveNode();
        return inv;
    }

}
