package net.darkhax.bladecraft.client.gui;

import net.darkhax.bladecraft.inventory.ContainerColoranator;
import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;

public class GuiColoranator extends GuiContainer
{
	private static final ResourceLocation tecGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
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
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 75, this.height / 2 , 150, 20, "Confirm Sword Coloration"));
		
		this.buttonList.add(new GuiSliderColoranator(1, this.width / 2 + 50, this.height / 2 - 60, "Red", 0.5f, tec, this.fontRenderer));
		this.buttonList.add(new GuiSliderColoranator(2, this.width / 2 + 50, this.height / 2 - 40, "Green", 0.5f, tec, this.fontRenderer));
		this.buttonList.add(new GuiSliderColoranator(3, this.width / 2 + 50, this.height / 2 - 20, "Blue", 0.5f, tec, this.fontRenderer));
		this.buttonList.add(new GuiSliderColoranator(4, this.width / 2 + 50, this.height / 2 + 20, "Red", 0.5f, tec, this.fontRenderer));
		this.buttonList.add(new GuiSliderColoranator(5, this.width / 2 + 50, this.height / 2 + 40, "Green", 0.5f, tec, this.fontRenderer));
		this.buttonList.add(new GuiSliderColoranator(6, this.width / 2 + 50, this.height / 2 + 60, "Blue", 0.5f, tec, this.fontRenderer));
	}
	
	@Override
	public void actionPerformed(GuiButton button)
	{
		if(button.id == 0)
		{
			int[][] rgbs = new int[2][3];
			int insetRed = ((GuiSliderColoranator)this.buttonList.get(1)).getSliderVal();
			int insetGreen = ((GuiSliderColoranator)this.buttonList.get(2)).getSliderVal();
			int insetBlue = ((GuiSliderColoranator)this.buttonList.get(3)).getSliderVal();
			rgbs[0] = new int[]{insetRed, insetGreen, insetBlue};
			int colorRed = ((GuiSliderColoranator)this.buttonList.get(4)).getSliderVal();
			int colorGreen = ((GuiSliderColoranator)this.buttonList.get(5)).getSliderVal();
			int colorBlue = ((GuiSliderColoranator)this.buttonList.get(6)).getSliderVal();
			rgbs[1] = new int[]{insetRed, insetGreen, insetBlue};
			tec.setRGBValues(rgbs);
			tec.setRequiresUpdate();
		}
	}
}
