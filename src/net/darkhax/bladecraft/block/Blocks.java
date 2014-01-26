package net.darkhax.bladecraft.block;

import net.darkhax.bladecraft.block.BlockColoranator;
import net.darkhax.bladecraft.lib.Config;
import net.minecraft.block.Block;

public class Blocks {

	public static Block coloranatorBlock;

	public Blocks() {

		init();
	}

	private void init() {

		coloranatorBlock = new BlockColoranator(Config.coloranatorBlockID);
	}
}
