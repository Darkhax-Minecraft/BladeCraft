package net.epoxide.bladecraft.inventory;

import net.epoxide.bladecraft.item.ItemAlloy;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.common.FMLLog;

public class ContainerForge extends Container
{
    private InventoryPlayer playerInv;
    private TileEntityForge tileEntity;
    
    public ContainerForge(InventoryPlayer inventory, TileEntityForge te)
    {
        super();
        playerInv = inventory;
        tileEntity = te;
        this.addSlotToContainer(new SlotSword(playerInv.player, te, 0, 32, 27, false));
        this.addSlotToContainer(new SlotAlloy(te, 1, 80, 27));
        this.addSlotToContainer(new SlotSword(playerInv.player, te, 2, 128, 27, true));

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
    
    public ItemStack transferStackInSlot(EntityPlayer player, int slotInd)
    {
        ItemStack stack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotInd);
        if(slot != null && slot.getHasStack())
        {
            ItemStack stack1 = slot.getStack();
            stack = stack1.copy();
            
            if(slotInd == TileEntityForge.SWORD_OUTPUT)
            {
                if(!this.mergeItemStack(stack1, 3, 39, true))
                {
                    return null;
                }
                slot.onSlotChange(stack1, stack);
            }
            else
            if(slotInd != TileEntityForge.ALLOY_INPUT && slotInd != TileEntityForge.SWORD_INPUT && slotInd != TileEntityForge.SWORD_OUTPUT)
            {
                if(stack1.getItem() instanceof ItemSword)
                {
                    if(!this.mergeItemStack(stack1, TileEntityForge.SWORD_INPUT, TileEntityForge.SWORD_INPUT + 1, false))
                    {
                        return null;
                    }
                }
                else
                if(stack1.getItem() instanceof ItemAlloy)
                {
                    if(!this.mergeItemStack(stack1, TileEntityForge.ALLOY_INPUT, TileEntityForge.ALLOY_INPUT + 1, false))
                    {
                        return null;
                    }
                }
                else
                if(slotInd >= TileEntityForge.SWORD_OUTPUT && slotInd < TileEntityForge.SWORD_OUTPUT + 28)
                {
                    if(!this.mergeItemStack(stack1, TileEntityForge.SWORD_OUTPUT + 28, TileEntityForge.SWORD_OUTPUT + 37, false))
                    {
                        return null;
                    }
                }
                else
                if(slotInd >= TileEntityForge.SWORD_OUTPUT + 28 && slotInd < TileEntityForge.SWORD_OUTPUT + 37 && !this.mergeItemStack(stack1, TileEntityForge.SWORD_OUTPUT + 1, TileEntityForge.SWORD_OUTPUT + 28, false))
                {
                    return null;
                }
                else
                if(!this.mergeItemStack(stack1, TileEntityForge.SWORD_OUTPUT + 1, TileEntityForge.SWORD_OUTPUT + 37, false))
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
    
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileEntity.isUseableByPlayer(player);
    }
}
