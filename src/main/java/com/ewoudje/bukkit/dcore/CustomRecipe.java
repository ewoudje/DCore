package com.ewoudje.bukkit.dcore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Map;

/**
 * Created by ewoud on 18/09/2016.
 * Note //
 */
public class CustomRecipe {
    public ShapedRecipe recipe;
    public ItemStack[] craftingstacks = new ItemStack[9];

    public CustomRecipe(ItemStack result, Map<Character, ItemStack> ingredient, String shape1, String shape2, String shape3) {
        recipe = new ShapedRecipe(result);
        recipe.shape(shape1, shape2, shape3);
        for (int i = 0;  i < ingredient.keySet().size(); i++) {
            if ((char) ingredient.keySet().toArray()[i] == ' ') continue;
            recipe.setIngredient((char) ingredient.keySet().toArray()[i], ingredient.get(ingredient.keySet().toArray()[i]).getType());
            for (int z = 0;  z < 9; z++) {
                craftingstacks[z] = ingredient.get((shape1 + shape2 + shape3).charAt(z));
            }
        }
        Main.customRecipes.add(this);

    }
}
