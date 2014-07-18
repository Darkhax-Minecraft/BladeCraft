package net.epoxide.bladecraft.util;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BladecraftUtilities
{
    
    public static Item[] swordTypes = new Item[]{Items.wooden_sword, Items.stone_sword, Items.golden_sword, Items.iron_sword, Items.diamond_sword};
    public static CreativeTabs bcTab = null;
    
    public static void addSwordCreativeTab()
    {
        bcTab = new CreativeTabs(CreativeTabs.getNextID(), "bcCreativeTab")
        {
            @Override
            public Item getTabIconItem()
            {
                return Items.diamond_sword;
            }
            
            @SideOnly(Side.CLIENT)
            public void displayAllReleventItems(List itemStackList)
            {
                for(Item itemSword : swordTypes)
                {
                    for(int hiltColor = 0; hiltColor < ItemDye.field_150922_c.length; hiltColor++)
                    {
                        for(int insetColor = 0; insetColor < ItemDye.field_150922_c.length; insetColor++)
                        {
                            for(int bladeColor = 0; bladeColor < ItemDye.field_150922_c.length; bladeColor++)
                            {
                                ItemStack swordStack = new ItemStack(itemSword);
                                Utilities.prepareStack(swordStack);
                                swordStack.stackTagCompound.setString(Reference.BLADE_HEX_NBT_KEY, Integer.toHexString(ItemDye.field_150922_c[bladeColor]));
                                swordStack.stackTagCompound.setString(Reference.HILT_HEX_NBT_KEY, Integer.toHexString(ItemDye.field_150922_c[hiltColor]));
                                swordStack.stackTagCompound.setString(Reference.INSET_HEX_NBT_KEY, Integer.toHexString(ItemDye.field_150922_c[insetColor]));
                                itemStackList.add(swordStack);
                            }
                        }
                    }
                }
            }
        };
    }
    
    public static void removeSwordCreativeTab()
    {
        if(bcTab != null)
        {
            for(int tabID = 0; tabID < CreativeTabs.creativeTabArray.length; tabID++)
            {
                if(bcTab.equals(CreativeTabs.creativeTabArray[tabID]))
                {
                    CreativeTabs.creativeTabArray[tabID] = null;
                }
            }
        }
        
        cleanTabs();
    }

    private static void cleanTabs()
    {
        int numRemovals = 0;
        for (int i = 0; i < CreativeTabs.creativeTabArray.length; i++)
        {
            if (CreativeTabs.creativeTabArray[i] == null)
                numRemovals++;
        }
        CreativeTabs[] newTabArray = new CreativeTabs[CreativeTabs.creativeTabArray.length - numRemovals];
        int nextIndex = 0;
        int index = 0;
        while (index < CreativeTabs.creativeTabArray.length)
        {
            if (CreativeTabs.creativeTabArray[index] != null)
            {
                newTabArray[nextIndex++] = CreativeTabs.creativeTabArray[index];
            }
            index++;
        }

        CreativeTabs.creativeTabArray = newTabArray;        
    }
}
