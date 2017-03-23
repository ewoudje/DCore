package com.ewoudje.bukkit.dcore.Handlers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.material.Ladder;
import org.bukkit.material.Stairs;
import org.bukkit.material.Torch;

/**
 * Created by ewoud_000 on 14/08/2016.
 */
public class BlockDataHandler {

    public static void setBlockDataOnBlock(Block data, Block target) {
        if (data.getState().getData() instanceof Torch) {
            final Block under = target.getRelative(BlockFace.DOWN);
            final byte originalData = under.getData();
            final Material originalType = under.getType();
            under.setType(Material.BEDROCK, false);
            target.setType(data.getType());
            Torch t = new Torch();
            t.setData(data.getData());
            BlockFace face = ((Torch)data.getState().getData()).getAttachedFace().getOppositeFace();
            t.setFacingDirection(face);
            target.setData(t.getData());
            under.setType(originalType, false);
            under.setData(originalData, false);
            target.getState().update(true);
            t = (Torch) data.getState().getData();
            if (t.getData() == 3) target.setData((byte) 4);
            if (t.getData() == 4) target.setData((byte) 3);
            return;
        }
        target.setType(data.getType());
        target.setData(data.getData());
        if (data.getState() instanceof Sign) {
            Sign state = (org.bukkit.block.Sign) target.getState();
            Sign bstate = (Sign) data.getState();
            state.setData(bstate.getData());
            for (int m = 0; m < bstate.getLines().length; m++) state.setLine(m, bstate.getLine(m));
        } else if (data.getState().getData() instanceof Stairs) {
            Stairs stairs = (Stairs) target.getState().getData();
            if (stairs.getData() == 2) target.setData((byte) 3);
            if (stairs.getData() == 3) target.setData((byte) 2);
        } else if (data.getState().getData() instanceof Ladder) {
            Ladder ladder = (Ladder) target.getState().getData();
            if (ladder.getData() == 2) target.setData((byte) 3);
            if (ladder.getData() == 3) target.setData((byte) 2);
        } else if (data.getState().getData() instanceof  org.bukkit.material.Chest) {
            org.bukkit.material.Chest chest = (org.bukkit.material.Chest) target.getState().getData();
            if (chest.getData() == 2) target.setData((byte) 3);
            if (chest.getData() == 3) target.setData((byte) 2);
        }

        target.getState().update(true);

    }

}
