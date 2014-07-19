package net.epoxide.bladecraft.client.render;

import org.lwjgl.opengl.GL11;

import net.epoxide.bladecraft.client.model.ModelMixer;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityMixerRenderer extends TileEntitySpecialRenderer
{
    private ModelMixer model = new ModelMixer();
    
    @Override
    public void renderTileEntityAt(TileEntity te, double f, double f1, double f2, float f3)
    {
        if(te instanceof TileEntityMixer)
        {
            model.setTileEntity((TileEntityMixer)te);
            
            GL11.glPushMatrix();
            GL11.glTranslatef((float)f + 0.5F, (float)f1 + 1.5F, (float)f2 + 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/blocks/mixer.png"));
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            model.render((Entity)null, (float)f, (float)f1, (float)f2, (float)f3, 0.0625F, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }
}
