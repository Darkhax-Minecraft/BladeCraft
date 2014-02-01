package net.darkhax.bladecraft.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.darkhax.bladecraft.block.BlockColoranator;
import net.darkhax.bladecraft.lib.Config;
import net.darkhax.bladecraft.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class Blocks {

	public static Block coloranatorBlock;

	public Blocks() {

		init();
	}

	private void init() {

		coloranatorBlock = new BlockColoranator(Config.coloranatorBlockID).setCreativeTab(CreativeTabs.tabMisc);
		registerBlock(coloranatorBlock, "coloranatorBlock");
	}
	
	/**
	 * Helper method for registering a block. 
	 *
	 * @param Block - Block to be registered
	 * @param String - Name to be registered to Block
	 **/
	private void registerBlock(Block block, String blockName)
	{
		GameRegistry.registerBlock(block, ItemBlock.class, blockName, Reference.MOD_ID);
	}
}
