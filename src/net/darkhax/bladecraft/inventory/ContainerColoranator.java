package net.darkhax.bladecraft.inventory;

import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerColoranator extends Container {

	private TileEntityColoranator coloranator;

	public ContainerColoranator(InventoryPlayer inventory, TileEntityColoranator coloranator) {

		this.coloranator = coloranator;
		this.addSlotToContainer(new Slot(coloranator, 0, 56, 17));
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {

		return this.coloranator.isUseableByPlayer(entityPlayer);
	}
}
