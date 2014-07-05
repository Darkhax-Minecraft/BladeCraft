package net.epoxide.bladecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDyer extends Slot
{
    private EntityPlayer player;

    public SlotDyer(EntityPlayer entityPlayer, IInventory inventory, int slotInd, int xCoord, int yCoord)
    {
        super(inventory, slotInd, xCoord, yCoord);
        player = entityPlayer;
    }

    public boolean isItemValid(ItemStack p_75214_1_)
    {
        return false;
    }
    
    public ItemStack decrStackSize(int p_75209_1_)
    {
        return super.decrStackSize(p_75209_1_);
    }

    public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
    {
        this.onCrafting(p_82870_2_);
        super.onPickupFromSlot(p_82870_1_, p_82870_2_);
    }
}
