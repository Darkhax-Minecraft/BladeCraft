package net.darkhax.bladecraft.client.event;

import cpw.mods.fml.common.registry.GameRegistry;
import net.darkhax.bladecraft.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentArrowFire;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ToolTipHandler {

	@ForgeSubscribe
	public void onDisplayTooltip(ItemTooltipEvent event) {

		if (event.itemStack.getItem() != null && event.itemStack.getItem() instanceof ItemSword) {

			if (!event.itemStack.hasTagCompound()) {

				event.itemStack.setTagCompound(new NBTTagCompound());
				event.itemStack.stackTagCompound.setString(Reference.COLOR_HEX_NBT_KEY, "Unset");
				event.itemStack.stackTagCompound.setString(Reference.INSET_HEX_NBT_KEY, "Unset");
			}

			if (GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak)) {
				
				event.toolTip.add("Hilt : " + event.itemStack.stackTagCompound.getString(Reference.COLOR_HEX_NBT_KEY));
				event.toolTip.add("Inset : " + event.itemStack.stackTagCompound.getString(Reference.INSET_HEX_NBT_KEY));
			}
		}
	}
}