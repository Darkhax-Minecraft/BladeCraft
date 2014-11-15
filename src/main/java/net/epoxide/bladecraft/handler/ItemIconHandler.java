package net.epoxide.bladecraft.handler;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Temporary implementation. Need to provide a better means of doing things.
 *      - Ghosrec35
 **/
public class ItemIconHandler 
{
    private static TextureMap map;
    private static IIcon blade;
    private static IIcon hilt;
    private static IIcon inset;
    
    private static IIcon alloyOverlay;

    @SubscribeEvent
    public void textureStitch(TextureStitchEvent.Pre event) 
    {
        if (event.map.getTextureType() == 1) 
        {
            this.map = event.map;
            blade = event.map.registerIcon("bladecraft:overlay_blade");
            hilt = event.map.registerIcon("bladecraft:overlay_hilt");
            inset = event.map.registerIcon("bladecraft:overlay_inset");
            
            alloyOverlay = event.map.registerIcon("bladecraft:overlay_alloy");
        }
        System.err.println("Icon Registration occurring.");
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static TextureMap getTextureMap() 
    {
        return map;
    }

    public static IIcon getIconBlade() 
    {
        return blade;
    }

    public static IIcon getIconHilt() {

        return hilt;
    }

    public static IIcon getIconInset() {

        return inset;
    }
}