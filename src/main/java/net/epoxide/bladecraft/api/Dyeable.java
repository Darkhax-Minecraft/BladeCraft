package net.epoxide.bladecraft.api;

import net.minecraft.item.Item;

public class Dyeable
{
    public Class<? extends Item> itemClass;
    public String nbtKeyForIcon;
    public String iconLocation;
    
    public Dyeable(Class<? extends Item> itemClass, String nbtKeyForIcon, String iconLocation)
    {
        this.itemClass = itemClass;
    }
}
