package net.epoxide.bladecraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDyer extends TileEntity implements ISidedInventory
{
    
    public static final int timeToSplit = 5 * 20;
    
    private String customName;

    private ItemStack[] dyerStacks = new ItemStack[5];
    
    @Override
    public int getSizeInventory()
    {
        return dyerStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotInd)
    {
        return dyerStacks[slotInd];
    }

    @Override
    public ItemStack decrStackSize(int slotInd, int amtRemoved)
    {
        if(dyerStacks[slotInd] != null)
        {
            ItemStack stack;
            if(dyerStacks[slotInd].stackSize < amtRemoved)
            {
                stack = dyerStacks[slotInd];
                dyerStacks[slotInd] = null;
                return stack;
            }
            else
            {
                stack = dyerStacks[slotInd].splitStack(amtRemoved);
                
                if(dyerStacks[slotInd].stackSize == 0)
                {
                    dyerStacks[slotInd] = null;
                }
                
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotInd)
    {
        if(dyerStacks[slotInd] != null)
        {
            ItemStack stack = dyerStacks[slotInd];
            dyerStacks[slotInd] = null;
            return stack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotInd, ItemStack itemstack)
    {
        dyerStacks[slotInd] = itemstack;
        
        if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return (hasCustomInventoryName() ? "container.bc.dyer" : customName);
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return customName != null && customName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq(xCoord, yCoord, zCoord) <= 64.00D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slotInd, ItemStack itemstack)
    {
        return slotInd == 4 ? false : (slotInd >= 1 && slotInd <= 3 && !isItemDye(itemstack) ? false : (slotInd == 0 && !isDyeable(itemstack)));
    }

    private boolean isDyeable(ItemStack itemstack)
    {
        return itemstack.getItem() instanceof ItemSword;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return new int[]{0, 1, 2, 3, 4};
    }

    @Override
    public boolean canInsertItem(int slotInd, ItemStack itemstack, int side)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int slotInd, ItemStack itemstack, int side)
    {
        return false;
    }
    
    public boolean isItemDye(ItemStack itemstack)
    {
        return itemstack.getItem() instanceof ItemDye;
    }
}