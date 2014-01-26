package net.darkhax.bladecraft.client.gui;

import net.darkhax.bladecraft.inventory.ContainerColoranator;
import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiColoranator extends GuiContainer
{
	private static final ResourceLocation tecGuiTextures = new ResourceLocation("bladecraft:textures/gui/container/coloranator.png");
	private TileEntityColoranator tec;
	
	public GuiColoranator(InventoryPlayer playerInventory, TileEntityColoranator te) 
	{
		super(new ContainerColoranator(playerInventory, te));
		this.tec = te;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = this.tec.isInvNameLocalized() ? this.tec.getInvName() : I18n.getString(this.tec.getInvName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(tecGuiTextures);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}
}
