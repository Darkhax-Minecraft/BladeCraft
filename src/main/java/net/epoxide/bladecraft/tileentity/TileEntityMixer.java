package net.epoxide.bladecraft.tileentity;

import net.epoxide.bladecraft.item.crafting.DyeableItems;
import net.epoxide.bladecraft.item.crafting.RGBEntry;
import net.epoxide.bladecraft.util.Utilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMixer extends TileEntity implements ISidedInventory
{
    private static float maxComponentAmt = 18432.0F;

    // 5 second split time by default. Considering customizability via
    // Configuration file
    public static final int timeToSplit = 10 * 20;
    public static final int timeToDye = 2 * 60 * 20;

    private int splitTime;
    private int dyeTime;
    private boolean shouldDye;
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
        if (mixerStacks[slotInd] != null)
        {
            ItemStack stack;
            if (mixerStacks[slotInd].stackSize < amtRemoved)
            {
                stack = mixerStacks[slotInd];
                mixerStacks[slotInd] = null;
                return stack;
            }
            else
            {
                stack = mixerStacks[slotInd].splitStack(amtRemoved);

                if (mixerStacks[slotInd].stackSize == 0)
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
        if (mixerStacks[slotInd] != null)
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

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
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
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagList itemList = nbtTagCompound.getTagList("Items", 10);
        mixerStacks = new ItemStack[mixerStacks.length];

        for (int tag = 0; tag < itemList.tagCount(); tag++)
        {
            NBTTagCompound nbtTag = itemList.getCompoundTagAt(tag);
            byte itemSlot = nbtTag.getByte("Slot");

            if (itemSlot >= 0 && itemSlot < mixerStacks.length)
            {
                mixerStacks[itemSlot] = ItemStack.loadItemStackFromNBT(nbtTag);
            }
        }

        splitTime = nbtTagCompound.getShort("SplitTime");

        if (nbtTagCompound.hasKey("CustomName", 8))
        {
            customName = nbtTagCompound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setShort("SplitTime", (short) splitTime);
        NBTTagList itemList = new NBTTagList();
        for (int stackSlot = 0; stackSlot < mixerStacks.length; stackSlot++)
        {
            if (mixerStacks[stackSlot] != null)
            {
                NBTTagCompound nbtTag = new NBTTagCompound();
                nbtTag.setByte("Slot", (byte) stackSlot);
                mixerStacks[stackSlot].writeToNBT(nbtTag);
                itemList.appendTag(nbtTag);
            }
        }

        nbtTagCompound.setTag("Items", itemList);

        if (this.hasCustomInventoryName())
        {
            nbtTagCompound.setString("CustomName", customName);
        }
    }

    public void updateEntity()
    {
        boolean requiresUpdate = false;

        if (!worldObj.isRemote)
        {
            if (mixerStacks[0] != null && hasSpaceForComponents())
            {
                ++splitTime;
                if (splitTime == timeToSplit)
                {
                    splitTime = 0;
                    performDyeSplit();
                    requiresUpdate = true;
                }
            }
            else
            {
                if (splitTime != 0)
                    splitTime = 0;
            }

            if (mixerStacks[1] != null && shouldDye)
            {
                ++dyeTime;
                if (dyeTime == timeToDye)
                {
                    dyeTime = 0;
                    performItemDyeing();
                    requiresUpdate = true;
                }
            }
        }

        if (requiresUpdate)
            markDirty();
    }

    private void performItemDyeing()
    {
        ItemStack stack = applyDye(mixerStacks[1]);
        mixerStacks[2] = stack;
        mixerStacks[1] = null;
        shouldDye = false;
    }

    private ItemStack applyDye(ItemStack itemStack)
    {
        ItemStack stack = itemStack.copy();
        Utilities.prepareStack(stack);
        stack.setTagCompound(buildColorTag(stack.getTagCompound()));
        return stack;
    }

    private NBTTagCompound buildColorTag(NBTTagCompound stackCompound)
    {

        return null;
    }

    private boolean hasSpaceForComponents()
    {
        RGBEntry entry = DyeableItems.getDyeComponentValue(mixerStacks[1]);
        float tempRed = redComponentAmt + entry.getRed();
        float tempGreen = greenComponentAmt + entry.getGreen();
        float tempBlue = blueComponentAmt + entry.getBlue();

        if (tempRed < maxComponentAmt && tempGreen < maxComponentAmt && tempBlue < maxComponentAmt)
            return true;
        return false;
    }

    private void performDyeSplit()
    {
        RGBEntry entry = DyeableItems.getDyeComponentValue(mixerStacks[0]);
        redComponentAmt += entry.getRed();
        greenComponentAmt += entry.getGreen();
        blueComponentAmt += entry.getBlue();
        mixerStacks[0].stackSize--;
        if (mixerStacks[0].stackSize == 0)
        {
            mixerStacks[0] = null;
        }
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int slotInd, ItemStack stack)
    {
        return slotInd == 2 ? false : (slotInd == 0 && !(stack.getItem() instanceof ItemSword)) ? false : (slotInd == 1 && (stack.getItem() instanceof ItemDye) ? false : true);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return new int[]
        {
        0, 1, 2
        };
    }

    public void setDyeing()
    {
        shouldDye = true;
    }

    @Override
    public boolean canInsertItem(int slotInd, ItemStack stack, int side)
    {
        if (slotInd == 0 && side == 0)
            return true;
        return false;
    }

    @Override
    public boolean canExtractItem(int slotInd, ItemStack stack, int side)
    {
        if (slotInd == 2 && side == 1)
            return true;
        return false;
    }

    public void sendSplitUpdate()
    {
        // TODO Write netty implementation for sending split updates to the
        // server
    }

    public void sendDyeUpdate(String hexKey)
    {
        // TODO Write netty implementation for sending dye updates to the
        // server, I.E. when the player elects to begin dyeing the item.
    }

    public int getSplitProgressScaled(int i)
    {
        // TODO Actually write splitting progress code
        return 0;
    }

    public boolean isSplitting()
    {
        return splitTime > 0;
    }

    public int getSplitTimeRemainingScaled(int i)
    {
        // TODO Actually write split time remaining code
        return 0;
    }

    public int getDyeProgressScaled(int i)
    {
        // TODO Actually write dye progress code
        return 0;
    }

    public int getDyeTimeRemainingScaled(int i)
    {
        // TODO Actually write dye time remaining code.
        return 0;
    }
}
