package net.epoxide.bladecraft.gui;

import net.epoxide.bladecraft.handler.ConfigurationHandler;
import net.epoxide.bladecraft.util.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;

public class BladecraftConfigGui extends GuiConfig {

    static Configuration cfg = ConfigurationHandler.config;

    public BladecraftConfigGui(GuiScreen parent) {

        super(parent, new ConfigElement(cfg.getCategory(cfg.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
    }
}