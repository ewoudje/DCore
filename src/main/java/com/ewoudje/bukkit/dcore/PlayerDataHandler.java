package com.ewoudje.bukkit.dcore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by ewoud_000 on 21/07/2016.
 */
public class PlayerDataHandler {

    private FileConfiguration fc;
    private File f;


    public File getDataFolder() {
        File dir = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " "));
        File d = new File(dir.getParentFile().getPath(),"DCore");
        if(!d.exists()){
            d.mkdirs();
        }
        return d;
    }

    public File getFile() {
        f = new File(getDataFolder(), "Players.yml");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    public FileConfiguration getConfig() {
        if(fc == null) {
            fc = YamlConfiguration.loadConfiguration(getFile());
        }
        return fc;
    }

    public void reloadConfig() { fc = null; }

    public void saveConfig() {
        if(f.exists()) {
            try {
                getConfig().save(getFile());
            } catch (IOException e) {
                Main.bp.errorConsolePrint("Can't Save File");
            }
        } else {
            Main.bp.errorConsolePrint("Can't Find File");
        }
    }

    public void playerJoined(Player p) {

    }


}
