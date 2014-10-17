package net.epoxide.bladecraft.util;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import com.google.common.base.Strings;

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
    
    public static ISound buildSound(ResourceLocation location, float volume, float pitch)
    {
    	return new Sound(location, AttenuationType.LINEAR, volume, pitch);
    }
    
    public static class Sound implements ISound
    {
        private final ResourceLocation location;
        private final AttenuationType type;
        private float volume;
        private float pitch;
        private boolean canRepeat;
        private int repeatDelay;
        private float x, y, z;
        
        public Sound(String location)
        {
        	this(location, AttenuationType.LINEAR);
        }
        
        public Sound(ResourceLocation location)
        {
        	this(location, AttenuationType.LINEAR);
        }
        
        public Sound(String location, AttenuationType type)
        {
        	this(new ResourceLocation(location), type);
        }
        
        public Sound(ResourceLocation location, AttenuationType type)
        {
        	this(location, type, 1.0F);
        }
        
        public Sound(ResourceLocation location, AttenuationType type, float volume)
        {
        	this(location, type, volume, 1.0F);
        }
        
        public Sound(ResourceLocation location, AttenuationType type, float volume, float pitch)
        {
        	this(location, type, volume, pitch, -1);
        }
     
        public Sound(ResourceLocation location, AttenuationType type, float volume, float pitch, int repeatDelay)
        {
            this.location = location;
            this.type = type;
            this.volume = volume;
            this.pitch = pitch;
            if(this.repeatDelay < 0)
            {
            	this.canRepeat = false;
            	this.repeatDelay = 0;
            }
            else
            {
            	this.canRepeat = true;
            	this.repeatDelay = repeatDelay;
            }
        }
        
        public void setLocation(float x, float y, float z)
        {
        	this.x = x;
        	this.y = y; 
        	this.z = z;
        }
        
        @Override
        public ResourceLocation getPositionedSoundLocation()
        {
            return this.location;
        }

        @Override
        public boolean canRepeat()
        {
            return this.canRepeat;
        }

        @Override
        public int getRepeatDelay()
        {
            return this.repeatDelay;
        }

        @Override
        public float getVolume()
        {
            return this.volume;
        }

        @Override
        public float getPitch()
        {
            return this.pitch;
        }

        @Override
        public float getXPosF()
        {
            return this.x;
        }

        @Override
        public float getYPosF()
        {
            return this.y;
        }

        @Override
        public float getZPosF()
        {
            return this.z;
        }

        @Override
        public AttenuationType getAttenuationType()
        {
            return this.type;
        }
    }
}
