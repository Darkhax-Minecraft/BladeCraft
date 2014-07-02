package net.epoxide.bladecraft.handler;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ItemIconHandler {

    private static TextureMap map;
    private static IIcon blade;
    private static IIcon hilt;
    private static IIcon inset;

    @SubscribeEvent
    public void textureStitch(TextureStitchEvent.Pre event) {

        if (event.map.getTextureType() == 1) {

            this.map = event.map;
            blade = event.map.registerIcon("bladecraft:overlay_blade");
            hilt = event.map.registerIcon("bladecraft:overlay_hilt");
            inset = event.map.registerIcon("bladecraft:overlay_inset");
        }
    }

    public static TextureMap getTextureMap() {

        return map;
    }

    public static IIcon getIconBlade() {

        return blade;
    }

    public static IIcon getIconHilt() {

        return hilt;
    }

    public static IIcon getIconInset() {

        return inset;
    }
}