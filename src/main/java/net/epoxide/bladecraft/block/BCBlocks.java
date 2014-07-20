package net.epoxide.bladecraft.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BCBlocks
{
    public static Block forgeBlock;
    public static Block mixerBlock;
    
    public static void initialize()
    {
        forgeBlock = new BlockForge().setBlockName("blockForge").setCreativeTab(CreativeTabs.tabBlock);
        mixerBlock = new BlockMixer().setBlockName("blockMixer").setCreativeTab(CreativeTabs.tabBlock);
        
        GameRegistry.registerBlock(forgeBlock, forgeBlock.getUnlocalizedName());
        GameRegistry.registerBlock(mixerBlock, mixerBlock.getUnlocalizedName());
    }
}
