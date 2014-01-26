package net.darkhax.bladecraft.client.event;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ItemIconHandler {

	private static TextureMap map;
	private static Icon hilt;
	private static Icon inset;
	
    @ForgeSubscribe
    public void textureStitch(TextureStitchEvent.Pre evt) {

        if(evt.map.textureType == 0) {

        }
        
        if(evt.map.textureType == 1) {

        	this.map = evt.map;
    		hilt = evt.map.registerIcon("bladecraft:overlay_hilt");
    		inset = evt.map.registerIcon("bladecraft:overlay_inset");

        	System.out.println("Ok it's loaded 1010-392-039-3029-2300e9432");
        }
    }
    
    public static TextureMap getTextureMap() {
    	
    	return map;
    }
    
    public static Icon getIconHilt() {
    	
    	return hilt;
    }
    
    public static Icon getIconInset() {
    	
    	return inset;
    }
}