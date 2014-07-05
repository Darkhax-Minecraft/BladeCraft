package net.epoxide.bladecraft.proxy;

import java.util.Iterator;

import net.epoxide.bladecraft.handler.ConfigurationHandler;
import net.epoxide.bladecraft.handler.ItemIconHandler;
import net.epoxide.bladecraft.render.RenderItemSword;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ProxyClient extends ProxyCommon {

    @Override
    public void registerSidededEvents() {

        MinecraftForgeClient.registerItemRenderer(Items.wooden_sword, new RenderItemSword());
        MinecraftForgeClient.registerItemRenderer(Items.stone_sword, new RenderItemSword());
        MinecraftForgeClient.registerItemRenderer(Items.iron_sword, new RenderItemSword());
        MinecraftForgeClient.registerItemRenderer(Items.golden_sword, new RenderItemSword());
        MinecraftForgeClient.registerItemRenderer(Items.diamond_sword, new RenderItemSword());
        
        MinecraftForge.EVENT_BUS.register(new ItemIconHandler());
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
            case Reference.DYER_GUI_ID:
                return null;
            case Reference.MIXER_GUI_ID:
                return null;
            default: return null;
        }
    }
}
