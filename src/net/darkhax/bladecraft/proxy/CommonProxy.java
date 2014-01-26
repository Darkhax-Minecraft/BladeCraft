package net.darkhax.bladecraft.proxy;

import net.darkhax.bladecraft.inventory.ContainerColoranator;
import net.darkhax.bladecraft.lib.Reference;
import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	
	public void registerItemRenders() {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if (te != null) {
			
			switch (ID) {
			
			case Reference.COLORANATOR_GUI_ID:
				return new ContainerColoranator(player.inventory, (TileEntityColoranator) te);
			}
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		return null;
	}
}