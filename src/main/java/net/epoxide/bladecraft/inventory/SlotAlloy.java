package net.epoxide.bladecraft.inventory;

import net.epoxide.bladecraft.item.ItemAlloy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAlloy extends Slot
{
    private EntityPlayer player;

    public SlotAlloy(EntityPlayer entityPlayer, IInventory inventory, int slotInd, int xCoord, int yCoord)
    {
        super(inventory, slotInd, xCoord, yCoord);
        player = entityPlayer;
    }

    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof ItemAlloy;
    }

    public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
    {
        this.onCrafting(stack);
        super.onPickupFromSlot(player, stack);
    }
}
