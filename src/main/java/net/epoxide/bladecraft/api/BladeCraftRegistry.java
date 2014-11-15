package net.epoxide.bladecraft.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.discovery.ASMDataTable;

public class BladeCraftRegistry 
{
	private static List<IOverlayProvider> providers = new ArrayList<IOverlayProvider>();
	
	public static void retrieveProviders(ASMDataTable data)
	{
		data.getAll("net.epoxide.bladecraft.api.IOverlayProvider");
	}
	
	public static void addProvider(IOverlayProvider	provider)
	{
		if(!(provider instanceof Item))
		{
			FMLLog.warning("BladeCraft dyeing only supports items.");
			return;
		}
		
		if(!providers.contains(provider))
			providers.add(provider);
	}
	
	public static ImmutableList<IOverlayProvider> buildList()
	{
		return ImmutableList.copyOf(providers);	
	}
}
