package net.darkhax.bladecraft.client;

import cpw.mods.fml.common.FMLLog;
import net.darkhax.bladecraft.client.event.ItemIconHandler;
import net.darkhax.bladecraft.client.event.ToolTipHandler;
import net.darkhax.bladecraft.client.gui.GuiColoranator;
import net.darkhax.bladecraft.client.render.RenderItemSword;
import net.darkhax.bladecraft.lib.Reference;
import net.darkhax.bladecraft.proxy.CommonProxy;
import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	public void registerItemRenders() {
		
		MinecraftForgeClient.registerItemRenderer(Item.swordWood.itemID, new RenderItemSword());
		MinecraftForgeClient.registerItemRenderer(Item.swordStone.itemID, new RenderItemSword());
		MinecraftForgeClient.registerItemRenderer(Item.swordIron.itemID, new RenderItemSword());
		MinecraftForgeClient.registerItemRenderer(Item.swordGold.itemID, new RenderItemSword());
		MinecraftForgeClient.registerItemRenderer(Item.swordDiamond.itemID, new RenderItemSword());
		
		MinecraftForge.EVENT_BUS.register(new ItemIconHandler());
		MinecraftForge.EVENT_BUS.register(new ToolTipHandler());
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null)
		{
			switch(ID)
			{
			case Reference.COLORANATOR_GUI_ID: 
				return new GuiColoranator(player.inventory, (TileEntityColoranator)te);
			}
		}
		return null;
	}
}
