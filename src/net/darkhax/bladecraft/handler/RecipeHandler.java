package net.darkhax.bladecraft.handler;

import net.darkhax.bladecraft.lib.EnumColorManager;
import net.darkhax.bladecraft.lib.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler {
	
	/**
	 * Generates a recipe using all the vanilla swords.
	 * @param dye: ItemStack representing the dye to be used.
	 * @param color: Color to be placed on the stack.
	 */
	public static void createRecipeWithSwords(ItemStack dye, String color) {
		
		ItemStack[] swords = new ItemStack[] {getStack(Item.swordWood), getStack(Item.swordStone), getStack(Item.swordIron), getStack(Item.swordGold), getStack(Item.swordDiamond)};
		
		for (int i = 0; i < swords.length; i++) {
			
			ItemStack stack = swords[i];
			
			if (!stack.hasTagCompound()) {
				
				stack.setTagCompound(new NBTTagCompound());
				stack.stackTagCompound.setString(Reference.COLOR_HEX_NBT_KEY, color);
				stack.stackTagCompound.setString(Reference.INSET_HEX_NBT_KEY, "Unset");
			}
			
			GameRegistry.addShapelessRecipe(stack, dye, swords[i]);
		}
	}
	
	/**
	 * Creates a recipe using existing methods based on an enum key.
	 * @param color: Key to grab info from enum.
	 */
	public static void createRecipeWithSwordsFromEnum(String color) {

		createRecipeWithSwords(EnumColorManager.getItemStack(color), EnumColorManager.getHexColor(color));
	}
	
	/**
	 * Much lazy, Very Dark, So Hax, wow.
	 * @param item: Item for getting ItemStack.
	 * @return ItemStack based from item input.
	 */
	public static ItemStack getStack(Item item) {
		
		return new ItemStack(item);
	}
}