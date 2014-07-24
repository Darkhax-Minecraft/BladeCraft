package net.epoxide.bladecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class SlotSword extends Slot
{
    private EntityPlayer player;
    private boolean isOutput;

    public SlotSword(EntityPlayer entityPlayer, IInventory inventory, int slotInd, int xCoord, int yCoord, boolean isOutput)
    {
        super(inventory, slotInd, xCoord, yCoord);
        player = entityPlayer;
        this.isOutput = isOutput;
    }

    public boolean isItemValid(ItemStack stack)
    {
        return !isOutput && stack.getItem() instanceof ItemSword;
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
