package net.darkhax.bladecraft.proxy;

import net.darkhax.bladecraft.client.event.ItemIconHandler;
import net.darkhax.bladecraft.client.render.RenderItemSword;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	public static void registerItemRenders() {
		
		MinecraftForgeClient.registerItemRenderer(Item.swordDiamond.itemID, new RenderItemSword());
		MinecraftForge.EVENT_BUS.register(new ItemIconHandler());
	}
}
