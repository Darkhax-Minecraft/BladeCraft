package net.epoxide.bladecraft.asm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.epoxide.bladecraft.api.IOverlayProvider;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class BCLoadingPlugin implements IFMLLoadingPlugin 
{
	private static List<String> overlayClasses = new ArrayList<String>();
	
	@Override
	public String[] getASMTransformerClass() 
	{
		return new String[]{"net.epoxide.bladecraft.asm.BCDataCollector"};
	}

	@Override
	public String getModContainerClass() 
	{
		return null;
	}

	@Override
	public String getSetupClass() 
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {}

	@Override
	public String getAccessTransformerClass() 
	{
		return null;
	}
	
	public static void addProvider(String clazz)
	{
		overlayClasses.add(clazz);
	}

}
