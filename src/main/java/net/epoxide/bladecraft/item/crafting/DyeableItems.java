package net.epoxide.bladecraft.item.crafting;

import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Maps;

public class DyeableItems
{
    private static final RGBEntry NULL_ENTRY = new RGBEntry(0, 0, 0);
    private static Map<DyeEntry, RGBEntry> dyeables = Maps.newHashMap();
    
    static 
    {
        registerDyes();
    }
    
    public static RGBEntry getDyeComponentValue(ItemStack itemstack)
    {
        DyeEntry entry = new DyeEntry(itemstack.getItem(), itemstack.getItemDamage());
        if(dyeables.containsKey(entry))
            return dyeables.get(entry);
        return NULL_ENTRY;
    }

    private static void registerDyes()
    {
        for(int metadata = 0; metadata < 16; metadata++)
            dyeables.put(new DyeEntry(Items.dye, metadata), calculateEntryFromColorValue(metadata));
    }

    /**
     * Calculates the RGBEntry from the dye metadata
     * 
     * @dyeMeta The metadata value for the dye being used for the calculation.
     **/
    public static RGBEntry calculateEntryFromColorValue(int dyeMeta)
    {
        int hex = ItemDye.field_150922_c[dyeMeta];
        float red = (float)((hex & 0xFF0000) >> 16) / 255;
        float green = (float)((hex & 0xFF00) >> 8) / 255;
        float blue = (float)(hex & 0xFF) / 255;
        return new RGBEntry(red, green, blue);
    }
    
    public static boolean isNull(RGBEntry entry)
    {
        return entry == NULL_ENTRY;
    }
}
