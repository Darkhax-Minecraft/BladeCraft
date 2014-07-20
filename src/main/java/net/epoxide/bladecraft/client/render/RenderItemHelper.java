package net.epoxide.bladecraft.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;

public class RenderItemHelper {

    public static void drawIconIn3D(ItemStack stack, IIcon icon) {

        drawIconIn3D(stack, icon, false, 1F, 1F, 1F);
    }

    public static void drawIconIn3D(ItemStack stack, IIcon icon, boolean isEntity, float red, float green, float blue) {

        Minecraft mc = Minecraft.getMinecraft();

        GL11.glPushMatrix();

        if (icon == null || stack == null) {

            GL11.glPopMatrix();
            return;
        }

        mc.renderEngine.bindTexture(mc.renderEngine.getResourceLocation(stack.getItemSpriteNumber()));
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        if (red >= 0 && green >= 0 && blue >= 0)
            GL11.glColor3f(red / 256, green / 256, blue / 256);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void renderIconInInventory(IIcon icon, float red, float green, float blue) {

        RenderItem renderItem = new RenderItem();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        if (red >= 0 && green >= 0 && blue >= 0)
            GL11.glColor3f(red / 256, green / 256, blue / 256);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        renderItem.renderIcon(0, 0, icon, 16, 16);
    }
}