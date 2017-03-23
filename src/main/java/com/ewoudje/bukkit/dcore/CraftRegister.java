package com.ewoudje.bukkit.dcore;

import net.minecraft.server.v1_10_R1.RecipesFurnace;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Map;

/**
 * Created by ewoud_000 on 16/08/2016.
 */
public class CraftRegister {

    public static void registerShapedCraftingTableRecipe(ItemStack stack, String shape, Map<Character, Material> ingredient){
        ShapedRecipe recip = new ShapedRecipe(stack);
        recip.shape(shape);
        for (int i = 0;  i < ingredient.keySet().size(); i++) {
            recip.setIngredient((char) ingredient.keySet().toArray()[i], ingredient.get(ingredient.keySet().toArray()[i]));
        }
        Bukkit.addRecipe(recip);
    }

    public static void registerFurnaceRecipe(float exp, ItemStack input, ItemStack output) {
        RecipesFurnace.getInstance().customRecipes.put(CraftItemStack.asNMSCopy(input), CraftItemStack.asNMSCopy(output));
        //RecipesFurnace.getInstance().customExperience.put(CraftItemStack.asNMSCopy(input), exp);
    }

    public static void registerFurnaceRecipe(ItemStack input, ItemStack output) {
        registerFurnaceRecipe(0, input, output);
    }

}
