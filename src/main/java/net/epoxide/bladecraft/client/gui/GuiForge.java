package net.epoxide.bladecraft.client.gui;

import net.epoxide.bladecraft.inventory.ContainerForge;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiForge extends GuiContainer
{
    private static final ResourceLocation FORGE_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/forge_gui.png");
    private TileEntityForge forge;
    
    public GuiForge(InventoryPlayer inventory, TileEntityForge te)
    {
        super(new ContainerForge(inventory, te));
        forge = te;
    }

    public void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        String s = this.forge.hasCustomInventoryName() ? this.forge.getInventoryName() : I18n.format(this.forge.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(FORGE_GUI_TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if (this.forge.isDyeing())
        {
            int i1 = this.forge.getDyeTimeRemainingScaled(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
            i1 = this.forge.getDyeProgressScaled(24);
            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
        }
    }
}
