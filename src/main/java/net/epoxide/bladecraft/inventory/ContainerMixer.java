package net.epoxide.bladecraft.inventory;

import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class ContainerMixer extends Container
{
    public InventoryPlayer playerInv;
    public TileEntityMixer mixer;
    
    public ContainerMixer(InventoryPlayer inv, TileEntityMixer te)
    {
        playerInv = inv;
        mixer = te;
        
        this.addSlotToContainer(new SlotDye(te, 0, 26, 20));
        this.addSlotToContainer(new SlotIronIngot(playerInv.player, te, 1, 87, 30));
        this.addSlotToContainer(new SlotMixer(playerInv.player, te, 2, 139, 30));
        
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 80 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 138));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return mixer.isUseableByPlayer(player);
    }
    
    public ItemStack transferStackInSlot(EntityPlayer player, int slotInd)
    {
        ItemStack stack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotInd);
        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            
            if(slotInd == TileEntityMixer.ALLOY_OUTPUT || slotInd == TileEntityMixer.DYE_INPUT || slotInd == TileEntityMixer.ALLOY_INPUT)
            {
                if(!this.mergeItemStack(stack1, 3, 39, true))
                {
                    return null;
                }
                slot.onSlotChange(stack1, stack);
            }
            else
            if(slotInd != TileEntityMixer.ALLOY_INPUT && slotInd != TileEntityMixer.DYE_INPUT && slotInd != TileEntityMixer.ALLOY_OUTPUT)
            {
                if(stack1.getItem() instanceof ItemDye)
                {
                    if(!this.mergeItemStack(stack1, TileEntityMixer.DYE_INPUT, TileEntityMixer.DYE_INPUT + 1, false))
                    {
                        return null;
                    }
                }
                else
                if(stack1.getItem() == Items.iron_ingot)
                {
                    if(!this.mergeItemStack(stack1, TileEntityMixer.ALLOY_INPUT, TileEntityMixer.ALLOY_INPUT + 1, false))
                    {
                        return null;
                    }
                }
                else
                if(slotInd >= TileEntityMixer.ALLOY_OUTPUT && slotInd < TileEntityMixer.ALLOY_OUTPUT + 28)
                {
                    if(!this.mergeItemStack(stack1, TileEntityMixer.ALLOY_OUTPUT + 28, TileEntityMixer.ALLOY_OUTPUT + 37, false))
                    {
                        return null;
                    }
                }
                else
                if(slotInd >= TileEntityMixer.ALLOY_OUTPUT + 28 && slotInd < TileEntityMixer.ALLOY_OUTPUT + 37 && !this.mergeItemStack(stack1, TileEntityForge.SWORD_OUTPUT + 1, TileEntityForge.SWORD_OUTPUT + 28, false))
                {
                    return null;
                }
            }   
            
            if(stack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
            
            if(stack1.stackSize == stack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(player, stack1);
        }        
        return stack;
    }
}
