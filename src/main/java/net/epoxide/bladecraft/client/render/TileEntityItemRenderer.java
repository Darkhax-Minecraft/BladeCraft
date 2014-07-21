package net.epoxide.bladecraft.client.render;

import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class TileEntityItemRenderer implements IItemRenderer 
{
    private ModelBase model;
    private ResourceLocation texture;

    public TileEntityItemRenderer(ModelBase model, ResourceLocation texture)
    {
        this.model = model;
        this.texture = texture;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
        case EQUIPPED:
            GL11.glPushMatrix();
            GL11.glTranslatef(0.25F, 1.5F, -0.25F);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glPushMatrix();
            GL11.glRotatef(200F, 0.0F, 0.0F, 1.0F);
            model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            break;
        case EQUIPPED_FIRST_PERSON:
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5F, 1.0F, 0.0F);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glRotatef(200.0F, 0.0F, 0.0F, 1.0F);
            model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, 0.0625F);
            GL11.glPopMatrix();
            break;
        case INVENTORY:
            GL11.glPushMatrix();
            GL11.glTranslatef(8.0F, -7.0F, 0.0F);
            GL11.glScalef(15, 15, 15);
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            break;
        default:
            break;
        }
    }
}
