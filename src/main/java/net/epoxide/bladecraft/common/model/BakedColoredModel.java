package net.epoxide.bladecraft.common.model;

import java.util.List;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.TRSRTransformation;

import com.google.common.collect.ImmutableList;

public class BakedColoredModel implements IFlexibleBakedModel 
{
	private final TRSRTransformation transformation;
	private final VertexFormat format;
	private final ImmutableList<TextureAtlasSprite> textures;
	private final ItemRenderInfo renderInfo;
	
	public BakedColoredModel(TRSRTransformation transformation, VertexFormat format, ImmutableList<TextureAtlasSprite> textures, ItemRenderInfo renderInfo) 
	{
		this.transformation = transformation;
		this.format = format;
		this.textures = textures;
		this.renderInfo = renderInfo;
	}

	@Override
	public boolean isAmbientOcclusion() 
	{
		return false;
	}

	@Override
	public boolean isGui3d() 
	{
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() 
	{
		return false;
	}

	@Override
	public TextureAtlasSprite getTexture() 
	{
		return textures.get(0);
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() 
	{
		return ItemCameraTransforms.DEFAULT;
	}

	@Override
	public List<BakedQuad> getFaceQuads(EnumFacing side) 
	{
		return null;
	}

	@Override
	public List<BakedQuad> getGeneralQuads() 
	{
		return null;
	}

	@Override
	public VertexFormat getFormat() 
	{
		return format;
	}

}
