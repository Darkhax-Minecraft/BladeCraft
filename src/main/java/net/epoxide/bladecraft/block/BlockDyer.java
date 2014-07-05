package net.epoxide.bladecraft.block;

import net.epoxide.bladecraft.Bladecraft;
import net.epoxide.bladecraft.tileentity.TileEntityDyer;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDyer extends BlockContainer
{
    public BlockDyer()
    {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityDyer();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if(!world.isRemote)
        {
            return true;
        }
        else
        {
            player.openGui(Bladecraft.instance, Reference.DYER_GUI_ID, world, x, y, z);
            return true;
        }
    }
}
