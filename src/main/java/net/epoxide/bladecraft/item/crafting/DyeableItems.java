package net.epoxide.bladecraft.item.crafting;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class DyeableItems
{
    private static final RGBEntry NULL_ENTRY = new RGBEntry(0, 0, 0);
    private static Map<Integer, RGBEntry> dyeables = new HashMap<Integer, RGBEntry>();
    
    static 
    {
        registerDyes();
    }
    
    public static RGBEntry getDyeComponentValue(ItemStack itemstack)
    {
        if(dyeables.containsKey(itemstack.getItemDamage()))
            return dyeables.get(itemstack.getItemDamage());
        return NULL_ENTRY;
    }

    private static void registerDyes()
    {
        for(int metadata = 0; metadata < 16; metadata++)
            dyeables.put(metadata, calculateEntryFromColorValue(metadata));
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
    
    public static boolean contains(ItemStack stack)
    {
        return dyeables.containsKey(new DyeEntry(stack.getItem(), stack.getItemDamage()));
    }
}
