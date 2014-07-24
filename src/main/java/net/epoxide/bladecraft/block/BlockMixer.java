package net.epoxide.bladecraft.block;

import net.epoxide.bladecraft.Bladecraft;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMixer extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon mixerTopIcon;
    @SideOnly(Side.CLIENT)
    private IIcon mixerBottomIcon;

    public BlockMixer()
    {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityMixer();
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        player.openGui(Bladecraft.instance, Reference.MIXER_GUI_ID, world, x, y, z);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (side == 1)
            return this.mixerTopIcon;
        if (side == 0)
            return this.mixerBottomIcon;
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.mixerTopIcon = register.registerIcon(Reference.MOD_ID + ":blockMixerTop");
        this.mixerBottomIcon = register.registerIcon(Reference.MOD_ID + ":blockMixerBottom");
        this.blockIcon = register.registerIcon(Reference.MOD_ID + ":blockMixer");
    }
}
