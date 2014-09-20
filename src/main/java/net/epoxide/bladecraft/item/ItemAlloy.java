package net.epoxide.bladecraft.item;

import java.util.List;

import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlloy extends Item
{
    public ItemAlloy()
    {
        super();
        GameRegistry.registerItem(this, "alloyItem");
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        if(stack.hasTagCompound())
            list.add("Applied Color: " + stack.stackTagCompound.getString(Reference.ALLOY_COLOR_TAG));
        else
            list.add("Applied Color: Unset");
    }
    
    public void registerIcons(IIconRegister register)
    {
        this.itemIcon = register.registerIcon("bladecraft:alloy");
    }
}
