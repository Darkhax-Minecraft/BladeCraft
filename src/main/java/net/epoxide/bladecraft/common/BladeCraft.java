package net.epoxide.bladecraft.common;

import net.epoxide.bladecraft.common.proxy.IProxy;
import net.epoxide.bladecraft.common.ref.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION)
public class BladeCraft 
{
	@SidedProxy(clientSide = "net.epoxide.bladecraft.common.proxy.ClientProxy", serverSide = "net.epoxide.bladecraft.common.proxy.CommonProxy")
	public static IProxy proxy;
	
	@EventHandler
	public void onPreInitialization(FMLPreInitializationEvent event)
	{
		proxy.performSidedRegistration();
	}
}
