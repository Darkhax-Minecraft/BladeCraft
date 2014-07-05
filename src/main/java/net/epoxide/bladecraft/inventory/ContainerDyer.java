package net.epoxide.bladecraft.inventory;

import net.epoxide.bladecraft.tileentity.TileEntityDyer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerDyer extends Container
{
    private InventoryPlayer playerInv;
    private TileEntityDyer tileEntity;
    
    public ContainerDyer(InventoryPlayer inventory, TileEntityDyer dyerTE)
    {
        playerInv = inventory;
        tileEntity = dyerTE;
        this.addSlotToContainer(new Slot(playerInv, 0, 56, 17));
        this.addSlotToContainer(new Slot(playerInv, 1, 56, 53));
        this.addSlotToContainer(new Slot(playerInv, 2, 40, 53));
        this.addSlotToContainer(new Slot(playerInv, 3, 72, 53));
        this.addSlotToContainer(new SlotDyer(playerInv.player, playerInv, 4, 56, 110));
        
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileEntity.isUseableByPlayer(player);
    }
}
