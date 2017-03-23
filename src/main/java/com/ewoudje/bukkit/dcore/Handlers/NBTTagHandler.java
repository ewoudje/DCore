package com.ewoudje.bukkit.dcore.Handlers;



import net.minecraft.server.v1_10_R1.NBTTagCompound;
import net.minecraft.server.v1_10_R1.NBTTagInt;
import net.minecraft.server.v1_10_R1.NBTTagList;
import net.minecraft.server.v1_10_R1.NBTTagString;
import org.bukkit.craftbukkit.v1_10_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ewoud_000 on 16/08/2016.
 */
public class NBTTagHandler {

    public static ItemStack addIdOnItem(int id, ItemStack stackitem) {
        net.minecraft.server.v1_10_R1.ItemStack item = CraftItemStack.asNMSCopy(stackitem);
        NBTTagCompound com = new NBTTagCompound();
        com.setInt("DCCI", id);
        item.setTag(com);
        return CraftItemStack.asCraftMirror(item);
    }

    public static Integer getIdOnItem(ItemStack stackitem) {
        net.minecraft.server.v1_10_R1.ItemStack item = CraftItemStack.asNMSCopy(stackitem);
        try {
            NBTTagCompound com = item.getTag();
            return com.getInt("DCCI");
        } catch (NullPointerException e) {
            return null;
        }

    }

    public static ItemStack setDamageOnItem(ItemStack itemstack, int damage, String attribute) {
        net.minecraft.server.v1_10_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemstack);
        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
            compound = nmsStack.getTag();
        }
        NBTTagList modifiers = new NBTTagList();
        NBTTagCompound healthboost = new NBTTagCompound();
        healthboost.set("AttributeName", new NBTTagString(attribute));
        healthboost.set("Name", new NBTTagString(attribute));
        healthboost.set("Amount", new NBTTagInt(damage));
        healthboost.set("Operation", new NBTTagInt(0));
        healthboost.set("UUIDLeast", new NBTTagInt(894654));
        healthboost.set("UUIDMost", new NBTTagInt(2872));
        modifiers.add(healthboost);
        compound.set("AttributeModifiers", modifiers);
        nmsStack.setTag(compound);
        itemstack = CraftItemStack.asBukkitCopy(nmsStack);
        return itemstack;
    }



}
