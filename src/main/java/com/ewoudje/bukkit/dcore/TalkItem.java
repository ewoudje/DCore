
package com.ewoudje.bukkit.dcore;


/**
 * Created by ewoud_000 on 15/08/2016.
 *
public class TalkItem extends CustomeItem{


    public TalkItem(boolean r, boolean l, int id, String name, int metadata, String desc, Material material, int count) {
        super(id, r, l, material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (desc != null) {
            List<String> list = new ArrayList<>();
            for (String i : desc.split(",")) {
                list.add(i);
            }
            meta.setLore(list);
        }
        item.setItemMeta(meta);
        item.setData(new MaterialData(material, (byte) metadata));
        item.setAmount(count);
    }

    public TalkItem(boolean r, boolean l, int id, String name, int metadata, String desc, Material material, int count, int damage) {
        super(id, r, l, material, damage);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (desc != null) {
            List<String> list = new ArrayList<>();
            for (String i : desc.split(",")) {
                list.add(i);
            }
            meta.setLore(list);
        }
        item.setItemMeta(meta);
        item.setData(new MaterialData(material, (byte) metadata));
        item.setAmount(count);
    }

    public TalkItem(int id, String name, String desc, Material material) {
        super(id, false, false, material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (desc != null) {
            List<String> list = new ArrayList<>();
            for (String i : desc.split(",")) {
                list.add(i);
            }
            meta.setLore(list);
        }

        item.setItemMeta(meta);
        item.setAmount(1);
    }

    @Override
    public void onRightClick(PlayerInteractEvent e) {

    }

    @Override
    public void onLeftClick(PlayerInteractEvent e) {

    }
}
 */
