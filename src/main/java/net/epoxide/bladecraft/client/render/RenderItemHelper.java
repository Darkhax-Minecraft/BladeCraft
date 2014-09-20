package net.epoxide.bladecraft.client.render;

import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

public class RenderItemHelper 
{
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    
    public static void drawIconIn3D(ItemStack stack, IIcon icon) 
    {
        drawIconIn3D(stack, icon, false, 1F, 1F, 1F);
    }

    public static void drawIconIn3D(ItemStack stack, IIcon icon, boolean isEntity, float red, float green, float blue) 
    {
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
            GL11.glColor3f(red, green, blue);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
    
    public static void drawIconIn3D(ItemStack stack, IIcon icon, boolean isEntity) 
    {
        Minecraft mc = Minecraft.getMinecraft();

        GL11.glPushMatrix();

        if (icon == null || stack == null) 
        {
            GL11.glPopMatrix();
            return;
        }

        mc.renderEngine.bindTexture(mc.renderEngine.getResourceLocation(stack.getItemSpriteNumber()));
        Tessellator tessellator = Tessellator.instance;
        ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
        GL11.glPopMatrix();
    }

    public static void renderIconInInventory(ItemStack item, IIcon icon, ItemRenderType type, boolean hasColor)
    {
        RenderItem render = new RenderItem();
        Minecraft mc = Minecraft.getMinecraft();
        // This is a very hacky render. Unfortunately, this is the only way I could minimize graphical glitches.
        if(type == ItemRenderType.INVENTORY)
        {
            if(!mc.inGameHasFocus)
            {
                if(mc.currentScreen instanceof GuiContainer)
                {
                    if(isInHotbar(item))
                        if(hasColor(item))
                            render.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, item, 0, 0);
                        else
                            render.renderIcon(0, 0, icon, 16, 16);
                    else
                        if(!hasColor(item))
                            render.renderIcon(0, 0, icon, 16, 16);
                        else
                            render.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, item, 0, 0);
                }
            }
            else
            {
                if(hasColor(item))
                    render.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, item, 0, 0);
                else
                    render.renderIcon(0, 0, icon, 16, 16);
            }
        }
    }
    
    private static boolean hasColor(ItemStack item)
    {
        if(!item.hasTagCompound()) return false;
        return (item.stackTagCompound.getString(Reference.BLADE_HEX_NBT_KEY).equals("Unset") && item.stackTagCompound.getString(Reference.HILT_HEX_NBT_KEY).equals("Unset") && item.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY).equals("Unset"));
    }

    private static boolean isInHotbar(ItemStack item)
    {
        ItemStack[] inventory = Minecraft.getMinecraft().thePlayer.inventory.mainInventory;
        boolean isHotbarStack = false;
        for(int i = 0; i < 9; i++)
        {
            if(inventory[i] == item)
                isHotbarStack = true;
        }
        return isHotbarStack;
    }

    public static void renderIconInInventory(IIcon icon, float red, float green, float blue) 
    {
        RenderItem renderItem = new RenderItem();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        if (red >= 0 && green >= 0 && blue >= 0)
            GL11.glColor3f(red, green, blue);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        renderItem.renderIcon(0, 0, icon, 16, 16);
    }
}