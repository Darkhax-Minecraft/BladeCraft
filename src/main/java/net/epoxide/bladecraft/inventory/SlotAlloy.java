package net.epoxide.bladecraft.inventory;

import net.epoxide.bladecraft.item.BCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAlloy extends Slot
{
    public SlotAlloy(IInventory inventory, int slotInd, int xCoord, int yCoord)
    {
        super(inventory, slotInd, xCoord, yCoord);
    }
    
    public boolean isItemValid(ItemStack stack)
    {
        if(stack == null || stack.getItem() == null) return false;
        return stack.getItem() == BCItems.alloy;
    }
}
