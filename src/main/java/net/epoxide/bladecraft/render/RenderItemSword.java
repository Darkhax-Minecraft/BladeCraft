package net.epoxide.bladecraft.render;

import net.epoxide.bladecraft.handler.ItemIconHandler;
import net.epoxide.bladecraft.util.Reference;
import net.epoxide.bladecraft.util.Utilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemSword implements IItemRenderer {

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
            blade = Utilities.getRBGFromHex(item.stackTagCompound.getString(Reference.BLADE_HEX_NBT_KEY));

        if (!item.stackTagCompound.getString(Reference.HILT_HEX_NBT_KEY).equalsIgnoreCase("Unset"))
            hilt = Utilities.getRBGFromHex(item.stackTagCompound.getString(Reference.HILT_HEX_NBT_KEY));

        if (!item.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY).equalsIgnoreCase("Unset"))
            inset = Utilities.getRBGFromHex(item.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY));

        iconSword = item.getIconIndex();

        switch (type) {

        case EQUIPPED: {

            renderItem(item, iconSword, iconBlade, blade, iconHilt, hilt, iconInset, inset, false, true);
            break;
        }

        case EQUIPPED_FIRST_PERSON: {

            renderItem(item, iconSword, iconBlade, blade, iconHilt, hilt, iconInset, inset, false, true);
            break;
        }

        case INVENTORY: {

            renderItem(item, iconSword, iconBlade, blade, iconHilt, hilt, iconInset, inset, false, false);
            break;
        }

        case ENTITY: {

            renderItem(item, iconSword, iconBlade, blade, iconHilt, hilt, iconInset, inset, true, true);
            break;
        }
        default:
            break;
        }
    }

    private void renderItem(ItemStack item, IIcon itemIcon, IIcon blade, float[] bladergb, IIcon hilt, float[] hiltrgb, IIcon inset, float[] insetrgb, boolean isEntity, boolean is3d) {

        if (is3d) {

            RenderItemHelper.drawIconIn3D(item, itemIcon, isEntity, -1f, -1f, -1f);

            if (bladergb != null)
                RenderItemHelper.drawIconIn3D(item, blade, isEntity, bladergb[0], bladergb[1], bladergb[2]);
            if (hiltrgb != null)
                RenderItemHelper.drawIconIn3D(item, hilt, isEntity, hiltrgb[0], hiltrgb[1], hiltrgb[2]);
            if (insetrgb != null)
                RenderItemHelper.drawIconIn3D(item, inset, isEntity, insetrgb[0], insetrgb[1], insetrgb[2]);
        }

        else {

            RenderItemHelper.renderIconInInventory(itemIcon, -1f, -1f, -1f);

            if (bladergb != null)
                RenderItemHelper.renderIconInInventory(blade, bladergb[0], bladergb[1], bladergb[2]);
            if (hiltrgb != null)
                RenderItemHelper.renderIconInInventory(hilt, hiltrgb[0], hiltrgb[1], hiltrgb[2]);
            if (insetrgb != null)
                RenderItemHelper.renderIconInInventory(inset, insetrgb[0], insetrgb[1], insetrgb[2]);
        }
    }
}