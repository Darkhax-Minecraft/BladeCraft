package net.epoxide.bladecraft.item.crafting;

import net.minecraft.item.Item;

public class DyeEntry
{
    private Item dye;
    private int metadata;
    
    public DyeEntry(Item item, int d)
    {
        dye = item;
        metadata = d;
    }
    
    public Item getDye()
    {
        return dye;
    }
    
    public int getMetadata()
    {
        return metadata;
    }
    
    public boolean equals(Object obj)
    {
        if(obj instanceof DyeEntry && ((DyeEntry)obj).dye == this.dye && ((DyeEntry)obj).metadata == metadata)
            return true;
        return false;
    }


}
