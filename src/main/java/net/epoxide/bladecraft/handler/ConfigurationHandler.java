package net.epoxide.bladecraft.handler;

import java.io.File;

import net.epoxide.bladecraft.util.BladecraftUtilities;
import net.epoxide.bladecraft.util.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

    public static Configuration config;
    public static Property addDefaultSwords;
    public static String key = "Add default swords";
    
    public ConfigurationHandler(File configFile) {

        config = new Configuration(configFile);
        addDefaultSwords = config.get(Configuration.CATEGORY_GENERAL, key, true);
        FMLCommonHandler.instance().bus().register(this);
        syncConfig();
    }

    @SubscribeEvent
    public void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.modID.equals(Reference.MOD_ID))
            syncConfig();
    }

    private void syncConfig() {

        if(config.get(Configuration.CATEGORY_GENERAL, key, true).getBoolean())
        {
            BladecraftUtilities.addSwordCreativeTab();
        }
        else
        {
            BladecraftUtilities.removeSwordCreativeTab();
        }
        config.save();
    }
}
