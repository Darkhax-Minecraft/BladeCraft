package net.epoxide.bladecraft.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Utilities {

    public static float[] getRBGFromHex(String hex) {

        float[] rgb = new float[3];
        for (int i = 0; i < 3; i++)
            rgb[i] = Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);

        return rgb;
    }
    
    public static void prepareStack(ItemStack stack) {
        
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
    }
}
