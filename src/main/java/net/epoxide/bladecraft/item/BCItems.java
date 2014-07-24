package net.epoxide.bladecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BCItems
{
    public static Item alloy;
    
    public static void initialize()
    {
        alloy = new ItemAlloy().setTextureName("iron_ingot").setCreativeTab(CreativeTabs.tabMaterials);
    }
}
