package net.epoxide.bladecraft.client.render;

import net.epoxide.bladecraft.util.Reference;
import net.epoxide.bladecraft.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderItemAlloy implements IItemRenderer
{
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if(!item.hasTagCompound())
        {
            // If this stack doesn't have a tag compound yet, apply one with the default values.
            NBTTagCompound nbtTag = new NBTTagCompound();
            item.setTagCompound(new NBTTagCompound());
            item.stackTagCompound.setString(Reference.ALLOY_COLOR_TAG, "Unset");
        }
        
        IIcon icon = item.getItem().getIcon(item, 0);
        
        String hexStr = item.stackTagCompound.getString(Reference.ALLOY_COLOR_TAG);
        float[] alloyColor = null;
        if(!hexStr.equals("Unset"))
            alloyColor = Utilities.getRGBFromHex(hexStr);
        switch (type) 
        {
            case EQUIPPED: 
            {    
                renderItem(type, item, icon, alloyColor, false, true);
                break;
            }
            case EQUIPPED_FIRST_PERSON: 
            {
                renderItem(type, item, icon, alloyColor, false, true);
                break;
            }
            case INVENTORY: 
            {
                renderItem(type, item, icon, alloyColor, false, false);
                break;
            }
            case ENTITY: 
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(-0.56F, -0.3F, 0);
                renderItem(type, item, icon, alloyColor, true, true);
                GL11.glPopMatrix();
                break;
            }
            default:
                break;
            }
    }
    
    private void renderItem(ItemRenderType type, ItemStack item, IIcon itemIcon, float[] color, boolean isEntity, boolean is3d) 
    {
        RenderItem render = new RenderItem();
        if (is3d) 
        {
            if (color != null)
                RenderItemHelper.drawIconIn3D(item, itemIcon, isEntity, color[0], color[1], color[2]);
            else
                RenderItemHelper.drawIconIn3D(item, itemIcon, isEntity, -1f, -1f, -1f);
        }
        else 
        {   
            Minecraft mc = Minecraft.getMinecraft();

            if (color != null)
            {
                RenderItemHelper.renderIconInInventory(itemIcon, color[0], color[1], color[2]);
            }
            else
            {
                render.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, item, 0, 0);
            }
        }
    }
}