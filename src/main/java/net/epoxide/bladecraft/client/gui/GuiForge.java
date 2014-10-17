package net.epoxide.bladecraft.client.gui;

import net.epoxide.bladecraft.inventory.ContainerForge;
import net.epoxide.bladecraft.network.NetworkManager;
import net.epoxide.bladecraft.network.message.MessageUpdateForgeLayer;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiForge extends GuiContainer
{
    private static final ResourceLocation FORGE_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/forge_gui.png");
    private ISound forgeSound;
    private TileEntityForge forge;
    private int x, y;
    private int layer;
    private GuiButton button; 
    
    public GuiForge(InventoryPlayer inventory, TileEntityForge te)
    {
        super(new ContainerForge(inventory, te));
        forge = te;
        this.x = (this.width - this.xSize) / 2;
        this.y = (this.height - this.ySize) / 2;
        button = new GuiButton(0, x + 48, y + 45, 80, 20, "Gild " + getNameStrForLayer(te.getSelectedLayer()));      
        this.layer = forge.getSelectedLayer();
        
        forgeSound = new ISound()
        {
            @Override
            public ResourceLocation getPositionedSoundLocation()
            {
                return new ResourceLocation("");
            }

            @Override
            public boolean canRepeat()
            {
                return false;
            }

            @Override
            public int getRepeatDelay()
            {
                return 40;
            }

            @Override
            public float getVolume()
            {
                return 2;
            }

            @Override
            public float getPitch()
            {
                return 2;
            }

            @Override
            public float getXPosF()
            {
                return forge.xCoord;
            }

            @Override
            public float getYPosF()
            {
                return forge.yCoord;
            }

            @Override
            public float getZPosF()
            {
                return forge.zCoord;
            }

            @Override
            public AttenuationType getAttenuationType()
            {
                return AttenuationType.LINEAR;
            }
        };
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
                NetworkManager.sendMessage((new MessageUpdateForgeLayer(this.forge, this.layer)));
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
            NetworkManager.sendMessage((new MessageUpdateForgeLayer(this.forge, this.layer)));
        }
        else if(this.forge.getStackInSlot(0) != null && !button.enabled)
        {
            this.button.enabled = true;
        }
        
        this.x = (this.width - this.xSize) / 2;
        this.y = (this.height - this.ySize) / 2;
        button.xPosition = x + 48;
        button.yPosition = y + 45;
        
        ItemStack stack = this.forge.getStackInSlot(0);
        if(stack == null) return;
        
        if(stack.getItem() instanceof ItemSword)
        {
            switch(layer)
            {
                case 0: 
                    button.displayString = "Gild Blade";
                    break;
                case 1:
                    button.displayString = "Gild Hilt";
                    break;
                case 2:
                    button.displayString = "Gild Handle";
                    break;
            }
        }
        
        
        
        if(this.forge.isForging())
        {
            this.mc.getSoundHandler().playSound(forgeSound);
        }
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
            int progress = this.forge.getForgeProgressScaled(12);
            this.drawTexturedModalRect(xStart + 106, yStart + 29, 176, 0, 1 + progress, 15);
        }
    }
}