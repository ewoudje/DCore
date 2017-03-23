package com.ewoudje.bukkit.dcore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

/**
 * Created by ewoud_000 on 16/08/2016.
 */
public class DataNode {

    private File data;
    private FileConfiguration fc;
    private String name;
    private int id;

    public DataNode(String name, int id,@Nullable String map) {
        File dir = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("%20", " "));
        File di = new File(dir.getParentFile().getPath(),"DCore");
        File d = new File(di.getPath(),map == null ? "Data" : map);
        if(!d.exists()){
            d.mkdirs();
        }
        File f = new File(d, name + ".yml");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.data = f;
        this.fc = YamlConfiguration.loadConfiguration(data);
        this.name = name;
        this.id = id;
    }

    public FileConfiguration getData() {
        return fc;
    }

    public void saveNode() {
        if(data.exists()) {
            try {
                fc.save(data);
            } catch (IOException e) {
                Main.bp.errorConsolePrint("Can't Save File");
            }
        } else {
            Main.bp.errorConsolePrint("Can't Find File");
        }
        fc = null;
        this.fc = YamlConfiguration.loadConfiguration(data);
    }

    public void deleteFile() {
        data.delete();
    }





}
