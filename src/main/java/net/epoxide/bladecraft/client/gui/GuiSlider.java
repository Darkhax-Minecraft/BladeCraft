package net.epoxide.bladecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

public class GuiSlider extends GuiButton
{
    private float currentValue;
    public boolean isDragging;
    private final String color;
    private final float minimum;
    private final float maximum;

    public GuiSlider(int id, int xPosition, int yPosition, String display)
    {
        this(id, xPosition, yPosition, 0.0F, 1.0F, display);
    }

    public GuiSlider(int id, int xPosition, int yPosition, float minimum, float maximum, String display)
    {
        super(id, xPosition, yPosition, 150, 20, "");
        this.currentValue = 1.0F;
        this.minimum = minimum;
        this.maximum = maximum;
        this.color = display;
        this.displayString = String.format("%s: %.2f", this.color, this.currentValue);
    }

    public int getHoverState(boolean state)
    {
        return 0;
    }

    protected void mouseDragged(Minecraft mc, int xPosition, int yPosition)
    {
        if (this.visible)
        {
            if (this.isDragging)
            {
                this.currentValue = (float)(xPosition - (this.xPosition + 4)) / (float)(this.width - 8);

                if (this.currentValue < 0.0F)
                {
                    this.currentValue = 0.0F;
                }

                if (this.currentValue > 1.0F)
                {
                    this.currentValue = 1.0F;
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.currentValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.currentValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
            this.displayString = String.format("%s: %.2f", this.color, this.currentValue);
        }
    }
    
    public boolean mousePressed(Minecraft mc, int xPosition, int yPosition)
    {
        FMLLog.severe("Running");
        if (super.mousePressed(mc, xPosition, yPosition))
        {
            this.currentValue = (float)(xPosition - (this.xPosition + 4)) / (float)(this.width - 8);

            if (this.currentValue < 0.0F)
            {
                this.currentValue = 0.0F;
            }

            if (this.currentValue > 1.0F)
            {
                this.currentValue = 1.0F;
            }

            this.isDragging = true;
            this.displayString = String.format("%s: %.2f", this.color, this.currentValue);
            return true;
        }
        return false;      
    }

    public void mouseReleased(int p_146118_1_, int p_146118_2_)
    {
        this.isDragging = false;
    }
}
