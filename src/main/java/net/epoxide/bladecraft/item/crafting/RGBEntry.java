package net.epoxide.bladecraft.item.crafting;

import net.epoxide.bladecraft.util.Reference;

public class RGBEntry
{
    private float red;
    private float green;
    private float blue;
    
    public RGBEntry(float r, float g, float b)
    {
        red = r;
        green = g;
        blue = b;
    }
    
    public float getRed()
    {
        return red;
    }
    
    public float getGreen()
    {
        return green;
    }
    
    public float getBlue()
    {
        return blue;
    }
    
    public String toString()
    {
        return "Red: " + red + Reference.LINE_SEPARATOR +
                "Green: " + green + Reference.LINE_SEPARATOR +
                "Blue: " + blue;
    }
}
