package net.epoxide.bladecraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMixer extends TileEntity implements ISidedInventory
{
    private static float maxComponentAmt = 18432.0F;
    
    private int timeSinceLastSplit;
    private float redComponentAmt;
    private float greenComponentAmt;
    private float blueComponentAmt;
    private String customName;
    
    private ItemStack[] mixerStacks = new ItemStack[3];
    
    @Override
    public int getSizeInventory()
    {
        return mixerStacks.length;
    }
    
    @Override
    public ItemStack getStackInSlot(int slotInd)
    {
        return mixerStacks[slotInd];
    }
    
    @Override
    public ItemStack decrStackSize(int slotInd, int amtRemoved)
    {
        if(mixerStacks[slotInd] != null)
        {
            ItemStack stack;
            if(mixerStacks[slotInd].stackSize < amtRemoved)
            {
                stack = mixerStacks[slotInd];
                mixerStacks[slotInd] = null;
                return stack;
            }
            else
            {
                stack = mixerStacks[slotInd].splitStack(amtRemoved);
                
                if(mixerStacks[slotInd].stackSize == 0)
                {
                    mixerStacks[slotInd] = null;
                }
                
                return stack;
            }
        }
        return null;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int slotInd)
    {
        if(mixerStacks[slotInd] != null)
        {
            ItemStack stack = mixerStacks[slotInd];
            mixerStacks[slotInd] = null;
            return stack;
        }
        return null;
    }
    
    @Override
    public void setInventorySlotContents(int slotInd, ItemStack itemstack)
    {
        mixerStacks[slotInd] = itemstack;
        
        if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }
    
    @Override
    public String getInventoryName()
    {
        return hasCustomInventoryName() ? customName : "container.bc.mixer";
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
    public boolean isItemValidForSlot(int slotInd, ItemStack stack)
    {
        return slotInd == 2 ? false : (slotInd == 0 && !(stack.getItem() instanceof ItemSword))? false : (slotInd == 1 && (stack.getItem() instanceof ItemDye) ? false : true);
    }
    
    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return new int[]{0, 1, 2};
    }
    
    @Override
    public boolean canInsertItem(int slotInd, ItemStack stack, int side)
    {
        if(slotInd == 0 && side == 0)
            return true;
        return false;
    }
    
    @Override
    public boolean canExtractItem(int slotInd, ItemStack stack, int side)
    {
        if(slotInd == 2 && side == 1)
            return true;
        return false;
    }
}
