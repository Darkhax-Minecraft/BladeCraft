package net.darkhax.bladecraft.inventory;

import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerColoranator extends Container {

	private TileEntityColoranator coloranator;

	public ContainerColoranator(InventoryPlayer playerInventory, TileEntityColoranator coloranator) {

		this.coloranator = coloranator;
		this.addSlotToContainer(new Slot(coloranator, 0, 56, 17));
		this.addSlotToContainer(new Slot(coloranator, 1, 56, 53));
		
		int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 96 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 168));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {

		return this.coloranator.isUseableByPlayer(entityPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotNum)
	{
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNum);
		
		if(slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if(slotNum == 1)
            {
            	if(itemstack1 != null)
            		this.mergeItemStack(itemstack1, 0, 1, true);
            	
            	slot.onSlotChange(itemstack1, itemstack);
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }
		}
		return itemstack;
	}
}
