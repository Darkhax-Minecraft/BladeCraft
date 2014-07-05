package net.epoxide.bladecraft.handler;

import java.io.File;

import net.epoxide.bladecraft.util.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

    public static Configuration config;
    public static Property applyDyeToAllSwords;
    
    public ConfigurationHandler(File configFile) {

        config = new Configuration(configFile);
        FMLCommonHandler.instance().bus().register(this);
        syncConfig();
    }

    @SubscribeEvent
    public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.modID.equals(Reference.MOD_ID))
            syncConfig();
    }

    private void syncConfig() {

        config.save();
    }
}
