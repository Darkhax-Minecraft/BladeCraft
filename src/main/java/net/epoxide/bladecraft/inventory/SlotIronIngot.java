package net.epoxide.bladecraft.inventory;

import net.epoxide.bladecraft.item.ItemAlloy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotIronIngot extends Slot
{
    public SlotIronIngot(EntityPlayer entityPlayer, IInventory inventory, int slotInd, int xCoord, int yCoord)
    {
        super(inventory, slotInd, xCoord, yCoord);
    }

    public boolean isItemValid(ItemStack stack)
    {
        if(stack == null || stack.getItem() == null) return false;
        return stack.getItem() == Items.iron_ingot;
    }
}
