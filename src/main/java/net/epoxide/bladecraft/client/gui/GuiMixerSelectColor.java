package net.epoxide.bladecraft.client.gui;

import net.epoxide.bladecraft.util.Reference;
import net.epoxide.bladecraft.util.Utilities;
import net.minecraft.client.Minecraft;
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
    private static final int xSize = 146;
    private static final int ySize = 126;
    private static final ResourceLocation MIXER_SELECT_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/display_gui.png");;
    
    private GuiButton buttonSelectionType = new GuiButton(1, 172, 190, 50, 20, "Sliders");
    private GuiButton confirm = new GuiButton(0, 193, 280, 80, 20, "Confirm Colors");
//    private GuiSlider redSlider = new GuiSlider(1, 40, 30, "Red");
//    private GuiSlider greenSlider = new GuiSlider(2, 40, 50, "Green");
//    private GuiSlider blueSlider = new GuiSlider(3, 40, 70, "Blue");
    // Hex String selection.
    private GuiTextField colorValue;
    private int selectionType = 0;
    private GuiMixer parent;
    private String hexStr;
    private int x;
    private int y;
    
    public GuiMixerSelectColor(GuiMixer parent)
    {
        this.mc = parent.mc;
        this.mc.setIngameFocus();
        this.parent = parent;
        this.hexStr = parent.mixer.getHexStr();
    }
    
    public void initGui()
    {    
        super.initGui();
        System.err.println("Width: " + this.width);
        System.err.println("Height: " + this.height);
        this.colorValue = new GuiTextField(parent.mc.fontRenderer, ((this.width - this.xSize) / 2) + 10, ((this.height - this.ySize) / 2) + 35, 60, 20);
        this.colorValue.setMaxStringLength(6);
        this.colorValue.setText(this.hexStr);
        //this.colorValue.setVisible(false);
        this.buttonList.clear();
        this.buttonList.add(confirm);
        this.buttonList.add(buttonSelectionType);
        this.buttonSelectionType.enabled = false;
        this.buttonSelectionType.visible = false;
//        this.buttonList.add(redSlider);
//        this.buttonList.add(greenSlider);
//        this.buttonList.add(blueSlider);
    }
    
    public void updateScreen() 
    {
        this.x = (this.width - this.xSize) / 2;
        this.y = (this.height - this.ySize) / 2;
        
        this.buttonSelectionType.xPosition = this.x + 10;
        this.buttonSelectionType.yPosition = this.y + 10;
        
        this.confirm.xPosition = this.x + 32;
        this.confirm.yPosition = this.y + 100;
        
        // Set selection type to RGB sliders.
        if(this.selectionType == 0)
        {
            // On this gui tick, if the text field is still visible, disable it.
            if(!this.colorValue.getVisible())
            {
                    this.colorValue.setVisible(false);
                    this.colorValue.setEnabled(false);   
            }
        }
        
//        Set selection type to Hexadecimal String field.
//        if(this.selectionType == 1)
//        {
            if(colorValue.getText() == null || !(colorValue.getText().trim().length() == 6 && !Strings.isNullOrEmpty(colorValue.getText()) && Utilities.isValidHexStr(colorValue.getText())) )
            {
                this.confirm.enabled = false;
            }
            else
            {
                this.confirm.enabled = true;
            }
//        }
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
        this.mc.getTextureManager().bindTexture(MIXER_SELECT_GUI_TEXTURE);
        int xStart = (this.width - this.xSize) / 2;
        int yStart = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, this.xSize, this.ySize);
        
        // Check if the color value field is valid, and if it is, set its text as the current hex string.
        this.setColorIfValid();
        // Get the integer value of the hex string, and bitwise-or it to give the first two integer values a value of ff to make the alpha 1.
        int color = Integer.parseInt(this.hexStr, 16) | 0xff000000;
        // Display current color
        this.drawRect(this.x + 90, this.y + 8, this.x + 138, this.y + 56, color);
        
        // Get the starting x and y positions for the GUI.
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        this.colorValue.drawTextBox();
        color = Integer.parseInt("FFFFFF", 16) | 0xff000000;
        this.drawCenteredString(this.mc.fontRenderer, "Select Color:", this.x + 40, this.y + 10, color);
//        this.redSlider.drawButton(Minecraft.getMinecraft(), this.redSlider.xPosition, this.redSlider.yPosition);
//        this.greenSlider.drawButton(Minecraft.getMinecraft(), this.greenSlider.xPosition, this.greenSlider.yPosition);
//        this.blueSlider.drawButton(Minecraft.getMinecraft(), this.blueSlider.xPosition, this.blueSlider.yPosition);
    }

    private void setColorIfValid()
    {
        if(Utilities.isValidHexStr(this.colorValue.getText()))
        {
            this.hexStr = this.colorValue.getText();
        }
    }
}
