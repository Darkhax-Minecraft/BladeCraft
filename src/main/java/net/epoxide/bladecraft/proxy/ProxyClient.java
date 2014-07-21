package net.epoxide.bladecraft.proxy;

import java.util.HashMap;
import java.util.Map;

import net.epoxide.bladecraft.block.BCBlocks;
import net.epoxide.bladecraft.client.gui.GuiForge;
import net.epoxide.bladecraft.client.gui.GuiMixer;
import net.epoxide.bladecraft.client.model.ModelForge;
import net.epoxide.bladecraft.client.model.ModelMixer;
import net.epoxide.bladecraft.client.render.RenderItemSword;
import net.epoxide.bladecraft.client.render.TileEntityForgeRenderer;
import net.epoxide.bladecraft.client.render.TileEntityItemRenderer;
import net.epoxide.bladecraft.client.render.TileEntityMixerRenderer;
import net.epoxide.bladecraft.client.resource.IconMapping;
import net.epoxide.bladecraft.handler.ItemIconHandler;
import net.epoxide.bladecraft.tileentity.TileEntityForge;
import net.epoxide.bladecraft.tileentity.TileEntityMixer;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLLog;

public class ProxyClient extends ProxyCommon 
{
    public static Map<Item, IconMapping> iconMap = new HashMap<Item, IconMapping>();
    
    @Override
    public void registerSidededEvents() 
    {
        MinecraftForgeClient.registerItemRenderer(Items.wooden_sword, new RenderItemSword());
        MinecraftForgeClient.registerItemRenderer(Items.stone_sword, new RenderItemSword());
        MinecraftForgeClient.registerItemRenderer(Items.iron_sword, new RenderItemSword());
        MinecraftForgeClient.registerItemRenderer(Items.golden_sword, new RenderItemSword());
        MinecraftForgeClient.registerItemRenderer(Items.diamond_sword, new RenderItemSword());
        
        FMLLog.severe("Registered Item Renderers");
        MinecraftForge.EVENT_BUS.register(new ItemIconHandler());
        
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityForge.class, new TileEntityForgeRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMixer.class, new TileEntityMixerRenderer());
    }
    
    public void registerBlockItemRenderers()
    {
        MinecraftForgeClient.registerItemRenderer(Item.getItemById(Block.getIdFromBlock(BCBlocks.forgeBlock)), new TileEntityItemRenderer(new ModelForge(), new ResourceLocation(Reference.MOD_ID, "textures/blocks/forge.png")));
        MinecraftForgeClient.registerItemRenderer(Item.getItemById(Block.getIdFromBlock(BCBlocks.mixerBlock)), new TileEntityItemRenderer(new ModelMixer(), new ResourceLocation(Reference.MOD_ID, "textures/blocks/mixer.png")));
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te == null) return null;
        switch(ID)
        {
            case Reference.FORGE_GUI_ID:
                return new GuiForge(player.inventory, (TileEntityForge)te);
            case Reference.MIXER_GUI_ID:
                return new GuiMixer(player.inventory, (TileEntityMixer)te);
            default: return null;
        }
    }
    
    public void addIconRegistration(ItemStack stack)
    {
        IconMapping mapping = IconMapping.buildMapping(stack);
    }
}
