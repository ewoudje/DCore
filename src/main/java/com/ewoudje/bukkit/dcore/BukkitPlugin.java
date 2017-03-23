package com.ewoudje.bukkit.dcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import javax.annotation.Nullable;

/**
 * Created by ewoud_000 on 21/07/2016.
 */
public class BukkitPlugin {

    private String name;

    public BukkitPlugin(String name) {
        this.name = name;
    }

    public void normalConsolePrint(String message) {
        System.out.println("[" + name + "] " + message);
    }

    public void normalBroadcast(String message) {
        Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "[" + name + "] " + ChatColor.RESET + message);
    }

    public void errorBroadcast(String message) {
        Bukkit.getServer().broadcastMessage(ChatColor.RED + "[" + name + "] " + "[Error] " + ChatColor.RESET + message);
    }

    public void errorConsolePrint(String message) {
        System.out.println(ChatColor.RED + "[" + name + "] " + "[Error] " + ChatColor.RESET + message);
    }

    public void errorPersonalPrint(String message, Player p) {
        p.sendMessage(ChatColor.RED + "[" + name + "] " + "[Error] " + ChatColor.RESET + message);
    }

    public void normalPersonalPrint(String message, Player p) {
        p.sendMessage(ChatColor.GREEN + "[" + name + "] " + ChatColor.RESET + message);
    }

    public Permission getPermission(@Nullable String group, String name) {
        return new Permission(this.name.toLowerCase() + "." + (group == null ? "." : (group + ".")) + name);
    }

    public void noPermission(Player p) {
        errorPersonalPrint("No Permission", p);
    }

    public boolean runCommand(Permission perm, Player p, String command, String label) {
        if (label.equals(command)) {
            if (p.hasPermission(perm)) {
                return true;
            } else noPermission(p);
        }
        return false;
    }
}
