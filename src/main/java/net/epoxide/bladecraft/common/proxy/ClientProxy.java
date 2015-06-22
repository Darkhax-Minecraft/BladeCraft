package net.epoxide.bladecraft.common.proxy;

import net.epoxide.bladecraft.common.model.ColoredCustomModelLoader;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class ClientProxy implements IProxy 
{
	@Override
	public void performSidedRegistration() 
	{
		ModelLoaderRegistry.registerLoader(new ColoredCustomModelLoader());
		ModelLoader.setCustomMeshDefinition(Items.wooden_sword, new ItemMeshDefinition()
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) 
			{
				return null;
			}
		});
	}
}
