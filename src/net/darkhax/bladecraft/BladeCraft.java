package net.darkhax.bladecraft;

import java.io.File;
import java.util.Arrays;

import net.darkhax.bladecraft.block.Blocks;
import net.darkhax.bladecraft.client.event.ToolTipHandler;
import net.darkhax.bladecraft.command.CommandSetColor;
import net.darkhax.bladecraft.handler.RecipeHandler;
import net.darkhax.bladecraft.lib.Config;
import net.darkhax.bladecraft.lib.EnumColorManager;
import net.darkhax.bladecraft.lib.Reference;
import net.darkhax.bladecraft.proxy.CommonProxy;
import net.darkhax.bladecraft.tileentity.TileEntityColoranator;
import net.minecraft.block.Block;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class BladeCraft {

	@SidedProxy(serverSide = Reference.PROXY_SERVER, clientSide = Reference.PROXY_CLIENT)
	public static CommonProxy proxy;

	@Mod.Instance(Reference.MOD_ID)
	public static BladeCraft instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		proxy.registerItemRenders();

		Config config = new Config(new File(event.getModConfigurationDirectory(), Reference.MOD_NAME + ".cfg"));
		config.init();

		new Blocks();
		GameRegistry.registerTileEntity(TileEntityColoranator.class, "teColoranator");
		GameRegistry.addRecipe(new ItemStack(Blocks.coloranatorBlock), new Object[]{"X", 'X', Block.dirt});
		
		
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		RecipeHandler.createRecipeWithSwordsFromEnum("black");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {

		ServerCommandManager cmdManager = (ServerCommandManager) event.getServer().getCommandManager();
		cmdManager.registerCommand(new CommandSetColor());
	}

	private void getModMeta(ModMetadata meta) {

		meta.authorList = Arrays.asList(new String[] { "Darkhax", "Ghosrec35" });
		meta.logoFile = "null";
		meta.autogenerated = false;
		meta.credits = "Coded by Darkhax and Ghosrec35. Ideas by Thisguy1045";
		meta.description = "Bladecraft allows for extensive customization options for your Minecraft Weapons!";
		meta.url = "www.darkhax.net";
	}
}
