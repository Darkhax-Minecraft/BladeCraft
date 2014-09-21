package net.epoxide.bladecraft.tileentity;

import net.epoxide.bladecraft.network.NetworkManager;
import net.epoxide.bladecraft.network.message.MessageTileEntityForge;
import net.epoxide.bladecraft.util.Reference;
import net.epoxide.bladecraft.util.Utilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityForge extends TileEntity implements ISidedInventory
{
    public static final int FORGE_TIME = 20 * 20;
    public static final int SWORD_INPUT = 0, ALLOY_INPUT = 1, SWORD_OUTPUT = 2;
    
    private int forgingTime;
    private String customName = "";
    private ItemStack[] forgeStacks = new ItemStack[3];
    private int selectedLayer = 0;
    
    public void updateEntity()
    {
        if(forgeStacks[0] != null && forgeStacks[1] != null)
        {
            forgingTime++;
        }
        else
        {
            forgingTime = 0;
        }
        
        if(!worldObj.isRemote)
        {
            boolean requiresUpdate = false;
            
            if(forgingTime >= this.FORGE_TIME)
            {
                ItemStack stack = forgeStacks[0];
                applySwordColoration(stack);
                requiresUpdate = true;
            }
            
            if(requiresUpdate)
            {
                NetworkManager.sendMessage(new MessageTileEntityForge(this));
                this.markDirty();
            }
        }
    }

    private void applySwordColoration(ItemStack stack)
    {
        Utilities.prepareStack(stack);
        ItemStack alloyStack = this.forgeStacks[1];
        switch(this.selectedLayer)
        {
            case 0: 
                stack.stackTagCompound.setString(Reference.BLADE_HEX_NBT_KEY, alloyStack.stackTagCompound.getString(Reference.ALLOY_COLOR_TAG));
                break;
            case 1: 
                stack.stackTagCompound.setString(Reference.HILT_HEX_NBT_KEY, alloyStack.stackTagCompound.getString(Reference.ALLOY_COLOR_TAG));
                break;
            case 2: 
                stack.stackTagCompound.setString(Reference.INSET_HEX_NBT_KEY, alloyStack.stackTagCompound.getString(Reference.ALLOY_COLOR_TAG));
                break;
        }
        this.forgeStacks[1] = null;
        this.forgeStacks[2] = stack;
        this.forgeStacks[0] = null;
    }

    @Override
    public int getSizeInventory()
    {
        return forgeStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotInd)
    {
        return forgeStacks[slotInd];
    }

    @Override
    public ItemStack decrStackSize(int slotInd, int amtRemoved)
    {
        if(forgeStacks[slotInd] != null)
        {
            ItemStack stack;
            if(forgeStacks[slotInd].stackSize < amtRemoved)
            {
                stack = forgeStacks[slotInd];
                forgeStacks[slotInd] = null;
                return stack;
            }
            else
            {
                stack = forgeStacks[slotInd].splitStack(amtRemoved);
                
                if(forgeStacks[slotInd].stackSize == 0)
                {
                    forgeStacks[slotInd] = null;
                }
                
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotInd)
    {
        if(forgeStacks[slotInd] != null)
        {
            ItemStack stack = forgeStacks[slotInd];
            forgeStacks[slotInd] = null;
            return stack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotInd, ItemStack itemstack)
    {
        forgeStacks[slotInd] = itemstack;
        
        if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return (hasCustomInventoryName() ? customName : "container.bc.forge");
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
    
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        
        NBTTagList itemList = nbtTagCompound.getTagList("Items", 10);
        forgeStacks = new ItemStack[forgeStacks.length];
        
        for(int tag = 0; tag < itemList.tagCount(); tag++)
        {
            NBTTagCompound nbtTag = itemList.getCompoundTagAt(tag);
            byte itemSlot = nbtTag.getByte("Slot");
            
            if(itemSlot >= 0 && itemSlot < forgeStacks.length)
            {
                forgeStacks[itemSlot] = ItemStack.loadItemStackFromNBT(nbtTag);
            }
        }
        
        this.selectedLayer = nbtTagCompound.getInteger("SelectedLayer");
        
        if(nbtTagCompound.hasKey("CustomName", 8))
        {
            customName = nbtTagCompound.getString("CustomName");
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        
        nbtTagCompound.setShort("DyeTime", (short)forgingTime);
        NBTTagList itemList = new NBTTagList();
        for(int stackSlot = 0; stackSlot < forgeStacks.length; stackSlot++)
        {
            if(forgeStacks[stackSlot] != null)
            {
                NBTTagCompound nbtTag = new NBTTagCompound();
                nbtTag.setByte("Slot", (byte)stackSlot);
                forgeStacks[stackSlot].writeToNBT(nbtTag);
                itemList.appendTag(nbtTag);
            }
        }
        
        nbtTagCompound.setTag("Items", itemList);
        nbtTagCompound.setInteger("SelectedLayer", this.selectedLayer);
        
        if(this.hasCustomInventoryName())
        {
            nbtTagCompound.setString("CustomName", customName);
        }
    }
    
    public int getForgingTime()
    {
        return forgingTime;
    }

    public void setForgingTime(int forgingTime)
    {
        this.forgingTime = forgingTime;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public boolean isForging()
    {
        return forgingTime > 0;
    }

    public int getForgeProgressScaled(int i)
    {
        return (this.forgingTime * i / this.FORGE_TIME);
    }

    public int getForgeTimeRemainingScaled(int i)
    {
        return 0;
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        return NetworkManager.getPacket(new MessageTileEntityForge(this));
    }

    public int getSelectedLayer()
    {
        return this.selectedLayer;
    }
    
    public void setSelectedLayer(int selectedLayer)
    {
        this.selectedLayer = selectedLayer;
    }
}