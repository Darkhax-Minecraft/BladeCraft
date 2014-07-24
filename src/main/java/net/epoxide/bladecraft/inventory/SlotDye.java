package net.epoxide.bladecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class SlotDye extends Slot
{
    public SlotDye(IInventory inventory, int slotInd, int xCoord, int yCoord)
    {
        super(inventory, slotInd, xCoord, yCoord);
    }

    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemDye;
    }
    
    public ItemStack decrStackSize(int amt)
    {
        return super.decrStackSize(amt);
    }

    public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
    {
        this.onCrafting(stack);
        super.onPickupFromSlot(player, stack);
    }
}
