package net.darkhax.bladecraft.client.event;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ItemIconHandler {

	private static TextureMap map;
	
    @ForgeSubscribe
    public void textureStitch(TextureStitchEvent.Pre evt) {

        if(evt.map.textureType == 0) {

        }
        
        if(evt.map.textureType == 1) {

        	this.map = evt.map;
        	System.out.println("Ok it's loaded 1010-392-039-3029-2300e9432");
        }
    }
    
    public static TextureMap getTextureMap() {
    	
    	return map;
    }
}