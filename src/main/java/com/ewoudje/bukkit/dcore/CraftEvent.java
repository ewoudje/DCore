package com.ewoudje.bukkit.dcore;

import com.ewoudje.bukkit.dcore.Handlers.NBTTagHandler;
import com.ewoudje.bukkit.dcore.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ewoud on 18/09/2016.
 * Note //
 */
public class CraftEvent implements Listener {

    @EventHandler
    public void onCraftEvent(PrepareItemCraftEvent event) {
        if (Main.disabeledrecips.contains(event.getRecipe())) event.getInventory().setResult(new ItemStack(Material.AIR));
        if (Main.customeitems.get(CustomeBlock.getTypeFormId(NBTTagHandler.getIdOnItem(event.getRecipe().getResult()))) != null) {
            int y = 0;
            for (int i = 0; i < Main.customRecipes.size(); i++) {
                if (CustomeBlock.getTypeFormId(NBTTagHandler.getIdOnItem(Main.customRecipes.get(i).recipe.getResult())) == (CustomeBlock.getTypeFormId(NBTTagHandler.getIdOnItem(event.getRecipe().getResult())))) {
                    y = i;
                    break;
                }
                if (i + 1 == Main.customRecipes.size()) {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                    return;
                }

            }
            for (int i = 1; i < 10; i++) {
                if (event.getInventory().getItem(i) != null) {
                    ItemStack item = event.getInventory().getItem(i).clone();
                    item.setAmount(1);
                    if (NBTTagHandler.getIdOnItem(item) == null && NBTTagHandler.getIdOnItem(Main.customRecipes.get(y).craftingstacks[i - 1]) != null) {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                        return;
                    }
                    if (NBTTagHandler.getIdOnItem(item) != null && CustomeBlock.getTypeFormId(NBTTagHandler.getIdOnItem(item)) != (CustomeBlock.getTypeFormId(NBTTagHandler.getIdOnItem(Main.customRecipes.get(y).craftingstacks[i - 1])))) {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
                    }
                }
            }
            return;


        }
        for (int i = 1; i < 10; i++) {
            if (NBTTagHandler.getIdOnItem(event.getInventory().getItem(i)) != null) event.getInventory().setResult(new ItemStack(Material.AIR));
        }
    }
}
