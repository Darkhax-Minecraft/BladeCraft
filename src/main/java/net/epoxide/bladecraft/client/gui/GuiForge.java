package net.epoxide.bladecraft.client.gui;

import net.epoxide.bladecraft.inventory.ContainerForge;
import net.epoxide.bladecraft.network.NetworkManager;
import net.epoxide.bladecraft.network.message.MessageUpdateForgeLayer;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

public class GuiForge extends GuiContainer
{
    private static final ResourceLocation FORGE_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/forge_gui.png");
    private TileEntityForge forge;
    private int layer;
    private GuiButton button; 
    
    public GuiForge(InventoryPlayer inventory, TileEntityForge te)
    {
        super(new ContainerForge(inventory, te));
        FMLLog.severe("Constructing GUI for Forge");
        forge = te;
        button = new GuiButton(0, 175, 85, 80, 20, "Layer: " + getNameStrForLayer(te.getSelectedLayer()));
        this.layer = forge.getSelectedLayer();
    }
    
    private String getNameStrForLayer(int selectedLayer)
    {
        switch(selectedLayer)
        {
            case 0:
                return "Blade";
            case 1:
                return "Hilt";
            case 2: 
                return "Inset";
        }
        return null;
    }

    public void initGui()
    {
        super.initGui();
        this.buttonList.add(button);
    }

    public void actionPerformed(GuiButton button)
    {
        if(button.id == 0)
        {
            // Assuming the item in the slot is an instanceof ItemSword, there are only 3 possible layers
            if(this.forge.getStackInSlot(0) == null) return;
                
            if(this.forge.getStackInSlot(0).getItem() instanceof ItemSword)
            {
                if(this.layer < 2)
                {
                    this.layer++;
                }
                else
                {
                    this.layer = 0;
                }
                
                this.forge.setSelectedLayer(this.layer);
                this.mc.thePlayer.sendQueue.addToSendQueue(NetworkManager.getPacket(new MessageUpdateForgeLayer(this.forge, this.layer)));
            }
            // TODO Implement custom items with this system.
        }
    }
    
    public void updateScreen()
    {
        super.updateScreen();
        
        if(this.forge.getStackInSlot(0) == null && button.enabled)
        {
            button.displayString = "";
            this.button.enabled = false;
            this.layer = 0;
            this.mc.thePlayer.sendQueue.addToSendQueue(NetworkManager.getPacket(new MessageUpdateForgeLayer(this.forge, this.layer)));
        }
        else if(this.forge.getStackInSlot(0) != null && !button.enabled)
        {
            this.button.enabled = true;
        }
        
        ItemStack stack = this.forge.getStackInSlot(0);
        if(stack == null) return;
        
        if(stack.getItem() instanceof ItemSword)
        {
            switch(layer)
            {
                case 0: 
                    button.displayString = "Layer: Blade";
                    break;
                case 1:
                    button.displayString = "Layer: Hilt";
                    break;
                case 2:
                    button.displayString = "Layer: Inset";
                    break;
            }
        }
        
        int xStart = (this.width - this.xSize) / 2;
        int yStart = (this.height - this.ySize) / 2;
        button.xPosition = xStart + 48;
        button.yPosition = yStart + 45;
    }
    
    public void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String s = this.forge.hasCustomInventoryName() ? this.forge.getInventoryName() : I18n.format(this.forge.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 98, 4210752);
    }

    public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURE);
        int xStart = (this.width - this.xSize) / 2;
        int yStart = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, this.xSize, this.ySize);

        if (this.forge.isForging())
        {
            int percentRemaining = this.forge.getForgeProgressScaled(13);
            System.err.println(percentRemaining);
            this.drawTexturedModalRect(xStart + 106, yStart + 29, 176, 0, 1 + percentRemaining, 16);
        }
    }
}
