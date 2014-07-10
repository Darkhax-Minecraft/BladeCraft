package net.epoxide.bladecraft.proxy;

import java.util.ArrayList;
import java.util.List;

import net.epoxide.bladecraft.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ProxyCommon implements IGuiHandler{

    public List<Item> dyeableItems = new ArrayList<Item>();
    
    public void registerSidededEvents() {

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
            case Reference.DYER_GUI_ID:
                return null;
            case Reference.MIXER_GUI_ID:
                return null;
            default: return null;
        }
    }

    public void addIconRegistration(ItemStack stack)
    {
        dyeableItems.add(stack.getItem());
    }
}
