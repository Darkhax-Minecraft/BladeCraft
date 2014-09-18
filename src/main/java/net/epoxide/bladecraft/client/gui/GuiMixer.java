package net.epoxide.bladecraft.client.gui;

import java.util.List;

import net.epoxide.bladecraft.inventory.ContainerMixer;
import net.epoxide.bladecraft.network.NetworkManager;
import net.epoxide.bladecraft.network.message.MessageUpdateMixerValues;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiMixer extends GuiContainer
{
    private static final ResourceLocation MIXER_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/mixer_gui.png");
    TileEntityMixer mixer;
    
    public GuiMixer(InventoryPlayer inventory, TileEntityMixer te)
    {
        super(new ContainerMixer(inventory, te));
        mixer = te;
    }

    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String s = this.mixer.hasCustomInventoryName() ? this.mixer.getInventoryName() : I18n.format(this.mixer.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 98, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(MIXER_GUI_TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        // Draw dye component columns
        // Red
        int redDeltaHeight = (int)(18 * (1f - mixer.getRedComponentPercentage()));
        redDeltaHeight = (redDeltaHeight <= 0 ? 1 : redDeltaHeight);
        this.drawTexturedModalRect(k + 16, l + 42 + redDeltaHeight, 176, 0, 6, 18 - redDeltaHeight);
        // Green
        int greenDeltaHeight = (int)(18 * (1f - mixer.getGreenComponentPercentage()));
        greenDeltaHeight = (greenDeltaHeight <= 0 ? 1 : greenDeltaHeight);
        this.drawTexturedModalRect(k + 26, l + 42 + greenDeltaHeight, 182, 0, 6, 18 - greenDeltaHeight);
        // Blue
        int blueDeltaHeight = (int)(18 * (1f - mixer.getBlueComponentPercentage()));
        blueDeltaHeight = (blueDeltaHeight <= 0 ? 1 : blueDeltaHeight);
        this.drawTexturedModalRect(k + 36, l + 42 + blueDeltaHeight, 188, 0, 6, 18 - blueDeltaHeight);
        
        if (this.mixer.isSplitting())
        {
            int i1 = this.mixer.getSplitTimeRemainingScaled(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
            i1 = this.mixer.getSplitProgressScaled(24);
            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        }
        
        // TODO Apply rendering for dye progress in the mixer
    }
    
    public void initGui()
    {
        super.initGui();
        this.buttonList.clear();
        
        this.buttonList.add(new GuiButton(0, 200, 60, 80, 20, "Settings"));
    }
    
    public void actionPerformed(GuiButton button)
    {
        if(button.id == 0)
        {
            this.mc.displayGuiScreen(new GuiMixerSelectColor(this));
        }
    }

    public void applyChanges(String hexStr)
    {
        this.mixer.setHexStr(hexStr);
        this.mc.thePlayer.sendQueue.addToSendQueue(NetworkManager.getPacket(new MessageUpdateMixerValues(this.mixer)));
    }
}
