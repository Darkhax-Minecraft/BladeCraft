package net.epoxide.bladecraft.client.render;

import net.epoxide.bladecraft.handler.ItemIconHandler;
import net.epoxide.bladecraft.util.Reference;
import net.epoxide.bladecraft.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderItemSword implements IItemRenderer 
{
    private RenderItem render = new RenderItem();
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        IIcon iconSword;
        IIcon iconBlade = ItemIconHandler.getIconBlade();
        IIcon iconHilt = ItemIconHandler.getIconHilt();
        IIcon iconInset = ItemIconHandler.getIconInset();

        if (!item.hasTagCompound()) {

            item.setTagCompound(new NBTTagCompound());
            item.stackTagCompound.setString(Reference.BLADE_HEX_NBT_KEY, "Unset");
            item.stackTagCompound.setString(Reference.HILT_HEX_NBT_KEY, "Unset");
            item.stackTagCompound.setString(Reference.INSET_HEX_NBT_KEY, "Unset");
        }

        float[] blade = null;
        float[] hilt = null;
        float[] inset = null;

        if (!item.stackTagCompound.getString(Reference.BLADE_HEX_NBT_KEY).equalsIgnoreCase("Unset"))
            blade = Utilities.getRGBFromHex(item.stackTagCompound.getString(Reference.BLADE_HEX_NBT_KEY));

        if (!item.stackTagCompound.getString(Reference.HILT_HEX_NBT_KEY).equalsIgnoreCase("Unset"))
            hilt = Utilities.getRGBFromHex(item.stackTagCompound.getString(Reference.HILT_HEX_NBT_KEY));

        if (!item.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY).equalsIgnoreCase("Unset"))
            inset = Utilities.getRGBFromHex(item.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY));

        iconSword = item.getIconIndex();

        switch (type) {

        case EQUIPPED: {
            
            renderItem(type,item, iconSword, iconBlade, blade, iconHilt, hilt, iconInset, inset, false, true);
            break;
        }

        case EQUIPPED_FIRST_PERSON: {

            renderItem(type,item, iconSword, iconBlade, blade, iconHilt, hilt, iconInset, inset, false, true);
            break;
        }

        case INVENTORY: {

            renderItem(type,item, iconSword, iconBlade, blade, iconHilt, hilt, iconInset, inset, false, false);
            break;
        }

        
        case ENTITY: {
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.56F, -0.3F, 0);
            renderItem(type, item, iconSword, iconBlade, blade, iconHilt, hilt, iconInset, inset, true, true);
            GL11.glPopMatrix();
            break;
        }
        default:
            break;
        }
    }

    private void renderItem(ItemRenderType type, ItemStack item, IIcon itemIcon, IIcon blade, float[] bladergb, IIcon hilt, float[] hiltrgb, IIcon inset, float[] insetrgb, boolean isEntity, boolean is3d) {

        if (is3d) {

            RenderItemHelper.drawIconIn3D(item, itemIcon, isEntity);

            if (bladergb != null)
                RenderItemHelper.drawIconIn3D(item, blade, isEntity, bladergb[0], bladergb[1], bladergb[2]);
            if (hiltrgb != null)
                RenderItemHelper.drawIconIn3D(item, hilt, isEntity, hiltrgb[0], hiltrgb[1], hiltrgb[2]);
            if (insetrgb != null)
                RenderItemHelper.drawIconIn3D(item, inset, isEntity, insetrgb[0], insetrgb[1], insetrgb[2]);
        }

        else {
                boolean hasColor = bladergb != null && hiltrgb != null && insetrgb != null;
                RenderItemHelper.renderIconInInventory(item, itemIcon, type, hasColor);
            
            if (hiltrgb != null)
                RenderItemHelper.renderIconInInventory(hilt, hiltrgb[0], hiltrgb[1], hiltrgb[2]);
            if (insetrgb != null)
                RenderItemHelper.renderIconInInventory(inset, insetrgb[0], insetrgb[1], insetrgb[2]); 
            if (bladergb != null)
                RenderItemHelper.renderIconInInventory(blade, bladergb[0], bladergb[1], bladergb[2]);
            
            if(item.hasEffect(0))
            {
                render.renderEffect(Minecraft.getMinecraft().renderEngine, 0, 0);
            }
        }
    }
}