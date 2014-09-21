package net.epoxide.bladecraft.client.gui;

import net.epoxide.bladecraft.util.Utilities;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Strings;

public class GuiMixerSelectColor extends GuiScreen
{
    private static final int xSize = 176;
    private static final int ySize = 166;
    
    private GuiButton buttonSelectionType = new GuiButton(1, 40, 100, 50, 20, "Sliders");
    private GuiButton confirm = new GuiButton(0, 20, 100, 50, 20, "Confirm Colors");
    //private GuiOptionSlider rSlider = new GuiOptionSlider(selectionType, selectionType, selectionType, null);
    // Hex String selection.
    private GuiTextField colorValue;
    private int selectionType = 0;
    private GuiMixer parent;
    private String hexStr;
    
    public GuiMixerSelectColor(GuiMixer parent)
    {
        this.parent = parent;
        this.hexStr = parent.mixer.getHexStr();
    }
    
    public void initGui()
    {    
        super.initGui();
        System.err.println("Width: " + this.width);
        System.err.println("Height: " + this.height);
        this.colorValue = new GuiTextField(parent.mc.fontRenderer, ((this.width - this.xSize) / 2) - 10, ((this.height - this.ySize) / 2) - 10, 60, 20);
        this.colorValue.setMaxStringLength(6);
        this.colorValue.setText(this.hexStr);
        this.colorValue.setVisible(false);
        this.buttonList.clear();
        this.buttonList.add(confirm);
        this.buttonList.add(buttonSelectionType);
    }
    
    public void updateScreen() 
    {
        if(this.selectionType == 0)
        {
            this.confirm.enabled = true;
            
            
        }
        
        if(colorValue.getText() == null || !(colorValue.getText().trim().length() == 6 && !Strings.isNullOrEmpty(colorValue.getText()) && Utilities.isValidHexStr(colorValue.getText())) )
        {
            this.confirm.enabled = false;
        }
        else
        {
            this.confirm.enabled = true;
        }
    }
    
    protected void keyTyped(char c, int p_73869_2_)
    {
        if (this.colorValue.textboxKeyTyped(c, p_73869_2_))
        {
            if(c == 'e') return;
                
        }
        else
        {
            super.keyTyped(c, p_73869_2_);
        }
    }
    
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.colorValue.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }
    
    public void actionPerformed(GuiButton button)
    {
        if(button.id == 0)
        {
                // Pass data to parent
                this.parent.applyChanges(this.colorValue.getText());
                // Reopen the parent GUI
                this.mc.displayGuiScreen(this.parent);
        }
        if(button.id == 1)
        {
            if(this.selectionType < 2)
            {
                this.selectionType++;
            }
            else
            {
                this.selectionType = 0;
            }
        }
    }
    
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        this.colorValue.drawTextBox();
        
        // Check if the color value field is valid, and if it is, set its text as the current hex string.
        this.setColorIfValid();
        // Get the integer value of the hex string, and bitwise-or it to give the first two integer values a value of ff to make the alpha 1.
        int color = Integer.parseInt(this.hexStr, 16) | 0xff000000;
        // Display current color
        this.drawRect(100, 100, 40, 40, color);
        
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        String str1 = "This is GUI is a work in progess!";
        this.drawString(this.fontRendererObj, str1, j - ((this.fontRendererObj.getStringWidth(str1) / 4)), k + 40, Integer.parseInt("982222", 16));
        String str2 = "Basic functionality has been included but aesthetics has been ignored for now!";
        this.drawString(this.fontRendererObj, str2, j - ((this.fontRendererObj.getStringWidth(str2) / 4)), k + 50, Integer.parseInt("982222", 16));
    }

    private void setColorIfValid()
    {
        if(Utilities.isValidHexStr(this.colorValue.getText()))
        {
            this.hexStr = this.colorValue.getText();
        }
    }
}
