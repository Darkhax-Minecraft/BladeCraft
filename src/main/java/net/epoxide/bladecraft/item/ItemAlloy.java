package net.epoxide.bladecraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
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
}
