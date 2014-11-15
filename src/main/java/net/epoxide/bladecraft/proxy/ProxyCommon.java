package net.epoxide.bladecraft.proxy;

import java.util.ArrayList;
import java.util.List;

import net.epoxide.bladecraft.inventory.ContainerForge;
import net.epoxide.bladecraft.inventory.ContainerMixer;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;

public class ProxyCommon implements IGuiHandler
{
    public List<Item> dyeableItems = new ArrayList<Item>();

    public void registerSidedEvents()
    {

    }

    public void registerBlockItemRenderers()
    {
    }

    public void addIconRegistration(ItemStack stack)
    {
        dyeableItems.add(stack.getItem());
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te == null) return null;
        switch(ID)
        {
            case Reference.FORGE_GUI_ID:
                return new ContainerForge(player.inventory, (TileEntityForge)te);
            case Reference.MIXER_GUI_ID:
                return new ContainerMixer(player.inventory, (TileEntityMixer)te);
            default: return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
}
