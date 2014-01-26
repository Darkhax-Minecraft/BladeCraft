package net.darkhax.bladecraft.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderItemHelper {

    public static void drawIconIn3D(ItemStack stack, Icon icon) {

        drawIconIn3D(stack, icon, false, 1F, 1F, 1F);
    }

    public static void drawIconIn3D(ItemStack stack, Icon icon, boolean isEntity, float red, float green, float blue) {

        Minecraft mc = Minecraft.getMinecraft();

        GL11.glPushMatrix();

        if(icon == null || stack == null) {

            GL11.glPopMatrix();
            return;
        }

        mc.renderEngine.bindTexture(mc.renderEngine.getResourceLocation(stack.getItemSpriteNumber()));
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);       
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor3f(red, green, blue);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        float f6 = 1F;
        
        if(isEntity) {

            GL11.glTranslatef(-0.5f, -0.2f, 0f);
            GL11.glScalef(f6+1, f6+1, f6+1);
        }
        
        else {
        	
        	GL11.glScalef(f6, f6, f6);
        }

        ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);

        GL11.glPopMatrix();
    }

    public static void renderIconInInventory(Icon icon, float red, float green, float blue) {

        RenderItem renderItem = new RenderItem();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor3f(red, green, blue);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        renderItem.renderIcon(0, 0, icon, 16, 16);
    }
}