package net.darkhax.bladecraft.client.gui;

import net.darkhax.bladecraft.lib.Utils;
import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderColoranator extends GuiButton
{
	private boolean isDragging;
	private TileEntityColoranator tec;
	private float sliderValue;
	private FontRenderer fontRenderer;
	
	public GuiSliderColoranator(int id, int xCoord, int yCoord, String label, float initialValue, TileEntityColoranator tileEntity, FontRenderer renderer) 
	{
		super(id, xCoord, yCoord, 100, 20, label);
		this.sliderValue = initialValue;
		this.tec = tileEntity;
		this.fontRenderer = renderer;
	}
	
	protected int getHoverState(boolean par1)
    {
        return 0;
    }

    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            if (this.isDragging)
            {
                this.sliderValue = (float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8);

                if (this.sliderValue < 0.0F)
                {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F)
                {
                    this.sliderValue = 1.0F;
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        if (super.mousePressed(par1Minecraft, par2, par3))
        {
            this.sliderValue = (float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8);

            if (this.sliderValue < 0.0F)
            {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F)
            {
                this.sliderValue = 1.0F;
            }

            if(!isDragging)
            	this.isDragging = true;
            else
            	this.isDragging = false;
            
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void mouseReleased(int par1, int par2)
    {
    	FMLLog.severe("MouseReleased Event firing!");
        this.isDragging = false;
    }

	public int getSliderVal() 
	{
		return Math.round(255 * sliderValue);
	}
}
