package cn.nukkit.block;

import cn.nukkit.nbt.tag.CompoundTag;

/**
 * @author Pub4Game
 * @since 03.01.2015
 */
public class BlockMushroomRed extends BlockMushroom {

    public BlockMushroomRed() {
        super();
    }

    public BlockMushroomRed(int meta) {
        super(0);
    }

    @Override
    public String getName() {
        return "Red Mushroom";
    }

    @Override
    public int getId() {
        return RED_MUSHROOM;
    }

    @Override
    protected int getType() {
        return 1;
    }
}
