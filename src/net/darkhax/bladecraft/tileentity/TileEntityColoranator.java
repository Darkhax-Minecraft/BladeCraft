package net.darkhax.bladecraft.tileentity;

import net.darkhax.bladecraft.block.BlockColoranator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityColoranator extends TileEntity implements ISidedInventory
{
	private ItemStack[] coloranatorStacks = new ItemStack[2];
	
	private static final int[] slotAccessibleByAutomation = new int[]{0};
	
	private String localizedInvName;
	
	private boolean requiresUpdate = false;
	
	@Override
	public int getSizeInventory() 
	{
		return this.coloranatorStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return this.coloranatorStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int changeInStackSize) 
	{
		if(this.coloranatorStacks[slot] != null)
		{
			ItemStack itemstack;
			
			if(this.coloranatorStacks[slot].stackSize <= changeInStackSize)
			{
				itemstack = this.coloranatorStacks[slot];
				this.coloranatorStacks[slot] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.coloranatorStacks[slot].splitStack(changeInStackSize);
				
				if(this.coloranatorStacks[slot].stackSize == 0)
				{
					this.coloranatorStacks[slot] = null;
				}
				
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		if(this.coloranatorStacks[slot] != null)
		{
			ItemStack itemstack = this.coloranatorStacks[slot];
			this.coloranatorStacks[slot] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) 
	{
		this.coloranatorStacks[slot] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
    {
        return this.isInvNameLocalized() ? this.localizedInvName : "container.coloranator";
    }

	@Override
    public boolean isInvNameLocalized()
    {
        return this.localizedInvName != null && this.localizedInvName.length() > 0;
    }

	public void setGuiDisplayName(String str)
	{
		this.localizedInvName = str;
	}
	
	public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        NBTTagList nbttaglist = nbtTag.getTagList("Items");
        this.coloranatorStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.coloranatorStacks.length)
            {
                this.coloranatorStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        this.requiresUpdate = nbtTag.getBoolean("RequiresUpdate");

        if (nbtTag.hasKey("CustomName"))
        {
            this.localizedInvName = nbtTag.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbtTag)
    {
        super.writeToNBT(nbtTag);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.coloranatorStacks.length; ++i)
        {
            if (this.coloranatorStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.coloranatorStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtTag.setTag("Items", nbttaglist);
        nbtTag.setBoolean("RequiresUpdate", requiresUpdate);

        if (this.isInvNameLocalized())
        {
        	nbtTag.setString("CustomName", this.localizedInvName);
        }
    }
	
	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public void updateEntity()
	{
		if(requiresUpdate)
		{
			this.onInventoryChanged();
			BlockColoranator.updateBlockState(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityPlayer) 
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) 
	{
		return (slot == 1 ? false : isSword(itemstack));
	}

	private boolean isSword(ItemStack itemstack) 
	{
		return (itemstack.getItem() instanceof ItemSword);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) 
	{
		return slotAccessibleByAutomation;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int j) 
	{
		return this.isItemValidForSlot(slot, itemstack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) 
	{
		return side != 0 || slot != 1 || itemstack.itemID == Item.bucketEmpty.itemID;
	}
}
