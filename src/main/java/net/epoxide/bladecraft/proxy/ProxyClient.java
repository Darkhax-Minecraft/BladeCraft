package net.epoxide.bladecraft.proxy;

import java.util.Iterator;

import net.epoxide.bladecraft.handler.ConfigurationHandler;
import net.epoxide.bladecraft.handler.ItemIconHandler;
import net.epoxide.bladecraft.render.RenderItemSword;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
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
}
