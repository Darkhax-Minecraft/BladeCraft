package net.darkhax.bladecraft.client.render;

import net.darkhax.bladecraft.client.event.ItemIconHandler;
import net.darkhax.bladecraft.lib.Reference;
import net.darkhax.bladecraft.lib.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
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

		Icon iconSword;
		Icon iconHilt = ItemIconHandler.getIconHilt();
		Icon iconInset = ItemIconHandler.getIconInset();
		
		if (!item.hasTagCompound()) {
			
			item.setTagCompound(new NBTTagCompound());
			item.stackTagCompound.setString(Reference.COLOR_HEX_NBT_KEY, "Unset");
			item.stackTagCompound.setString(Reference.INSET_HEX_NBT_KEY, "Unset");
		}

		int[] hilt = null;
		int[] inset = null;
		
		if (!item.stackTagCompound.getString(Reference.COLOR_HEX_NBT_KEY).equalsIgnoreCase("Unset")) {
			
			hilt = Utils.getRGBFromHexStr(item.stackTagCompound.getString(Reference.COLOR_HEX_NBT_KEY));
		}
		
		if (!item.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY).equalsIgnoreCase("Unset")) {
			
			inset = Utils.getRGBFromHexStr(item.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY));
		}

		iconSword = item.getIconIndex();

		switch (type) {

		case EQUIPPED: {

			RenderItemHelper.drawIconIn3D(item, iconSword, false, 1, 1, 1);
			
			if (hilt != null) {
				
				RenderItemHelper.drawIconIn3D(item, iconHilt, false, hilt[0], hilt[1], hilt[2]);
			}
			
			if (inset != null) {
				
				RenderItemHelper.drawIconIn3D(item, iconInset, false, inset[0], inset[1], inset[2]);
			}
			
			break;
		}

		case EQUIPPED_FIRST_PERSON: {

			RenderItemHelper.drawIconIn3D(item, iconSword, false, 1, 1, 1);
			
			if (hilt != null) {
				
				RenderItemHelper.drawIconIn3D(item, iconHilt, false, hilt[0], hilt[1], hilt[2]);
			}
			
			if (inset != null) {
				
				RenderItemHelper.drawIconIn3D(item, iconInset, false, inset[0], inset[1], inset[2]);
			}
			
			break;
		}

		case INVENTORY: {

			RenderItemHelper.renderIconInInventory(iconSword, 1f, 1f, 1f);
			
			if (hilt != null) {
				
				RenderItemHelper.renderIconInInventory(iconHilt, hilt[0], hilt[1], hilt[2]);
			}
			
			if (inset != null) {
				
				RenderItemHelper.renderIconInInventory(iconInset, inset[0], inset[1], inset[2]);
			}
			
			break;
		}

		case ENTITY: {

			RenderItemHelper.drawIconIn3D(item, iconSword, true, 1, 1, 1);
			
			if (hilt != null) {
				
				RenderItemHelper.drawIconIn3D(item, iconHilt, true, hilt[0], hilt[1], hilt[2]);
			}
			
			if (inset != null) {
				
				RenderItemHelper.drawIconIn3D(item, iconInset, true, inset[0], inset[1], inset[2]);
			}
			
			break;
		}

		default: {

			break;
		}
		}
	}
}