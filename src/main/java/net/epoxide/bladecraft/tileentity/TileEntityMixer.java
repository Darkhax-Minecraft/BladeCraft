package net.epoxide.bladecraft.tileentity;

import java.util.Map;

import net.epoxide.bladecraft.item.crafting.DyeableItems;
import net.epoxide.bladecraft.item.crafting.RGBEntry;
import net.epoxide.bladecraft.network.NetworkManager;
import net.epoxide.bladecraft.network.message.MessageTileEntityMixer;
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
import net.minecraft.util.ChatComponentText;

public class TileEntityMixer extends TileEntity implements ISidedInventory
{
    private static float maxComponentAmt = 250.0F;
    public static final int DYE_INPUT = 0, ALLOY_INPUT = 1, ALLOY_OUTPUT = 2;
    
    // 5 second split time by default. Considering customizability via
    // Configuration file
    public static final int TIME_TO_SPLIT = 1;
    public static final int TIME_TO_DYE = 30 * 20;

    private int splitTime = 0;
    private int mixTime = 0;
    private float redComponentAmt = 0;
    private float greenComponentAmt = 0;
    private float blueComponentAmt = 0;
    private String customName = "";
    private ItemStack[] mixerStacks = new ItemStack[3];
    private String hexStr;

    public TileEntityMixer()
    {
        super();
        this.hexStr = "FFFFFF";
    }
    
    @Override
    public int getSizeInventory()
    {
        return mixerStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotInd)
    {
        if(slotInd > mixerStacks.length - 1 || slotInd < 0)
            return null;
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

        this.redComponentAmt = nbtTagCompound.getFloat("RedComponentAmount");
        this.greenComponentAmt = nbtTagCompound.getFloat("GreenComponentAmount");
        this.blueComponentAmt = nbtTagCompound.getFloat("BlueComponentAmount");

        this.splitTime = nbtTagCompound.getShort("SplitTime");

        if(nbtTagCompound.hasKey("ColorString"))
        {
            this.hexStr = nbtTagCompound.getString("ColorString");
        }
        else
        {
            this.hexStr = "FFFFFF";
        }
        
        if (nbtTagCompound.hasKey("CustomName", 8))
        {
            this.customName = nbtTagCompound.getString("CustomName");
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
        
        nbtTagCompound.setFloat("RedComponentAmount", this.redComponentAmt);
        nbtTagCompound.setFloat("GreenComponentAmount", this.greenComponentAmt);
        nbtTagCompound.setFloat("BlueComponentAmount", this.blueComponentAmt);
        
        nbtTagCompound.setTag("Items", itemList);
        
        if(this.hexStr == null)
        {
            nbtTagCompound.setString("ColorString", "FFFFFF");
        }
        else
        {
            nbtTagCompound.setString("ColorString", this.hexStr);
        }
        
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
                if (splitTime >= TIME_TO_SPLIT)
                {
                    splitTime = 0;
                    performDyeSplit();
                    requiresUpdate = true;
                }
            }
            else
            {
                splitTime = 0;
            }

            if (mixerStacks[1] != null)
            {
                float[] colorValues = Utilities.getRGBFromHex(this.hexStr);
                if(hasEnoughPigment(colorValues))
                {
                    ++mixTime;
                    System.err.println(mixTime);
                    if (mixTime == TIME_TO_DYE)
                    {
                        mixTime = 0;
                        performItemDyeing();
                        updateComponentAmounts();
                        requiresUpdate = true;
                    }
                }
            }
            else
            {
                this.mixTime = 0;
            }
        }

        if (requiresUpdate)
        {
            NetworkManager.sendMessage(new MessageTileEntityMixer(this));
            markDirty();
        }
    }
    
    private void updateComponentAmounts()
    {
        float[] colorValues = Utilities.getRGBFromHex(this.hexStr);
        this.redComponentAmt -= colorValues[0];
        this.greenComponentAmt -= colorValues[1];
        this.blueComponentAmt -= colorValues[2];
    }

    private boolean hasEnoughPigment(float[] colorValues)
    {
        return (colorValues[0] < this.redComponentAmt && colorValues[1] < this.greenComponentAmt && colorValues[2] < this.blueComponentAmt);
    }

    private void performItemDyeing()
    {
        ItemStack stack = applyDye(mixerStacks[1]);
        mixerStacks[2] = stack;
        mixerStacks[1] = null;
    }

    private ItemStack applyDye(ItemStack itemStack)
    {
        ItemStack stack = itemStack.copy();
        Utilities.prepareStack(stack);
        stack.stackTagCompound.setString(Reference.ALLOY_COLOR_TAG, this.hexStr);
        return stack;
    }

    private NBTTagCompound buildColorTag(NBTTagCompound stackCompound)
    {

        return null;
    }

    private boolean hasSpaceForComponents()
    {
        RGBEntry entry = DyeableItems.getDyeComponentValue(mixerStacks[0]);
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
        System.err.println(String.format("Red: %f, Green: %f, Blue: %f", entry.getRed(), entry.getGreen(), entry.getBlue()));
        redComponentAmt += entry.getRed() * 3;
        greenComponentAmt += entry.getGreen() * 3;
        blueComponentAmt += entry.getBlue() * 3;
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


    public int getDyeProgressScaled(int i)
    {
        return (this.mixTime * i) / this.TIME_TO_DYE;
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

    public int getDyeTimeRemainingScaled(int i)
    {
        // TODO Actually write dye time remaining code.
        return 0;
    }

    public String getCustomName()
    {
        return this.customName;
    }

    public int getSplitTime()
    {
        return this.splitTime;
    }

    public int getMixTime()
    {
        return this.mixTime;
    }
    
    public float getRedComponentAmt()
    {
        return redComponentAmt;
    }

    public void setRedComponentAmt(float redComponentAmt)
    {
        this.redComponentAmt = redComponentAmt;
    }

    public float getGreenComponentAmt()
    {
        return greenComponentAmt;
    }

    public void setGreenComponentAmt(float greenComponentAmt)
    {
        this.greenComponentAmt = greenComponentAmt;
    }

    public float getBlueComponentAmt()
    {
        return blueComponentAmt;
    }

    public void setBlueComponentAmt(float blueComponentAmt)
    {
        this.blueComponentAmt = blueComponentAmt;
    }

    public void setSplitTime(int splitTime)
    {
        this.splitTime = splitTime;
    }

    public void setMixTime(int mixTime)
    {
        this.mixTime = mixTime;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        return NetworkManager.getPacket(new MessageTileEntityMixer(this));
    }
    
    public void displayData(EntityPlayer player)
    {
        player.addChatMessage(new ChatComponentText(String.format("Amount of colors: (Red: %.2f, Green: %.2f, Blue: %.2f)", this.redComponentAmt, this.greenComponentAmt, this.blueComponentAmt)));
    }

    public float getRedComponentPercentage()
    {
        return (this.redComponentAmt / this.maxComponentAmt);
    }

    public float getGreenComponentPercentage()
    {
        return (this.greenComponentAmt / this.maxComponentAmt);
    }
    
    public float getBlueComponentPercentage()
    {
        return (this.blueComponentAmt / this.maxComponentAmt);
    }

    private String[] buildArrayFromMap(Map<Integer, String> layerIDHexMap)
    {
        return layerIDHexMap.values().toArray(new String[layerIDHexMap.values().size()]);
    }

    public void setHexStr(String hexStr)
    {
        this.hexStr = hexStr;
    }

    public String getHexStr()
    {
        return this.hexStr;
    }

    public boolean isDyeing()
    {
        return this.mixTime > 0;
    }
}
