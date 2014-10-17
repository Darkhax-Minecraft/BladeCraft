package net.epoxide.bladecraft.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class BCBlocks
{
    public static Block forgeBlock;
    public static Block mixerBlock;
    
    public static void initialize()
    {
        forgeBlock = new BlockForge().setHardness(3.5F).setBlockName("blockForge").setCreativeTab(CreativeTabs.tabBlock);
        mixerBlock = new BlockMixer().setHardness(3.5F).setBlockName("blockMixer").setCreativeTab(CreativeTabs.tabBlock);
        
        GameRegistry.registerBlock(forgeBlock, forgeBlock.getUnlocalizedName());
        GameRegistry.registerBlock(mixerBlock, mixerBlock.getUnlocalizedName());
        
        GameRegistry.addRecipe(new ItemStack(forgeBlock), new Object[]
                {
                    "BBB", "I I", "I I", 'B', Blocks.iron_block, 'I', Items.iron_ingot
                });
        
        GameRegistry.addRecipe(new ItemStack(mixerBlock), new Object[]
                {
                    "ISI", "IGI", "III", 'I', Items.iron_ingot, 'S', Items.stick, 'G', Blocks.glass
                });
    }
}
