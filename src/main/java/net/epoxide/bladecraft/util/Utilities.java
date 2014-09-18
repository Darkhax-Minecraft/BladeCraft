package net.epoxide.bladecraft.util;

import com.google.common.base.Strings;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Utilities
{

    public static float[] getRGBFromHex(String hex)
    {
        float[] rgb = new float[3];
        for (int i = 0; i < 3; i++)
            rgb[i] = (((float)Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16))) / 255f;
        return rgb;
    }

    public static void prepareStack(ItemStack stack)
    {
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
    }

    public static boolean isValidHexStr(String text)
    {
        // If the string is null or is null or empty, this isn't a valid hex
        // string.
        if (text == null || Strings.isNullOrEmpty(text))
            return false;

        // Attempt to get an RGB value from this string.
        try
        {
            getRGBFromHex(text);
            return true;
        }
        catch (Exception e)
        {
            // If the attempt fails, catch the propagated exception instead of
            // letting it propagate to the top of the stack. Discard it silently and return false.
        }
        return false;
    }
}
