package net.epoxide.bladecraft.common.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelCustomData;
import net.minecraftforge.client.model.IModelState;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class ColoredModel implements IModelCustomData
{
	private final ImmutableList<ResourceLocation> textures;
	private final int layers;
	private final Item item;
	private final ItemRenderInfo renderInfo;
	
	public ColoredModel(Item item, List<ResourceLocation> textures)
	{
		this.item = item;
		this.textures = ImmutableList.copyOf(textures);
		this.layers = textures.size();
		this.renderInfo = ItemRenderInfo.getRenderInfo(item);
	}
	
	@Override
	public Collection<ResourceLocation> getDependencies() 
	{
		return Collections.emptySet();
	}

	@Override
	public Collection<ResourceLocation> getTextures() 
	{
		return textures;
	}

	@Override
	public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) 
	{
		List<TextureAtlasSprite> textureSprites = new ArrayList<TextureAtlasSprite>();
		for(ResourceLocation location : textures)
		{
			textureSprites.add(bakedTextureGetter.apply(location));
		}
		return new BakedColoredModel(state.apply(this), format, ImmutableList.copyOf(textureSprites), renderInfo);
	}

	@Override
	public IModelState getDefaultState() 
	{
		return ModelRotation.X0_Y0;
	}

	@Override
	public IModel process(ImmutableMap<String, String> customData) 
	{
		return null;
	}
}
